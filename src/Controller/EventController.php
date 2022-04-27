<?php

namespace App\Controller;

use App\Form\EvenementType;
use App\Repository\OrganisateurRepository;
use Stripe\Checkout\Session;
use Stripe\Stripe;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Routing\Generator\UrlGeneratorInterface;
use App\Entity\Evenement;
use App\Form\SearchEventType;
use App\Form\TriEventType;
use App\Repository\EvenementRepository;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\File\File;
use App\Controller\EntityManagerInterface;
use Dompdf\Dompdf;
use Dompdf\Options;

class EventController extends AbstractController
{
    /**
     * @Route("/full", name="full")
     */
    public function fullcalendar(EvenementRepository $repository,OrganisateurRepository $repo): Response
    {

        $events = $repository->findAll();
        $evenements=[];
        foreach($events as $event){
            $evenements[] = [
                'id' => $event->getReference(),
                'start' => $event->getDateDebut()->format('Y-m-d H:i:s'),
                'end' => $event->getDateFin()->format('Y-m-d H:i:s'),
                'title' => $event->getNomEvenement(),
                'description' => $event->getDescription(),
                'backgroundColor' => '#FFD700'
            ];
        }
        $data = json_encode($evenements);
        return $this->render('event/fullcalendar.html.twig', [
            'data'=>$data
        ]);

    }


    /**
     * @Route("/eventFront", name="eventFront")
     */
    public function index(EvenementRepository $repository,OrganisateurRepository $repo): Response
    {
        $org=$repo->findAll();
        $events = $repository->findAll();
        return $this->render('event/frontEvents.html.twig', [
            'events' => $events,
            'organisateurs' => $org
        ]);;

    }
    /**
     * @Route("/eventOverview/{id}", name="eventOverview")
     */
    public function eventOverview($id,EvenementRepository $repository): Response
    {
        $event = $repository->find($id);
        return $this->render('event/eventOverview.html.twig', [
            'event' => $event
        ]);;

    }

    /**
     * @Route("/event/ajouterEv", name="ajouter_event")
     */
    public function ajouterEvent(Request $request)
    {
        $event = new Evenement();
        $form = $this->createForm(EvenementType::class, $event);
        $form->add('Save', SubmitType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $file = $form->get('image')->getData();
            $fileName = md5(uniqid()) . '.' . $file->guessExtension();
            try {
                $file->move($this->getParameter('images_directory'),
                    $fileName
                );
            } catch (FileException $e) {
                //....
            }
            $em = $this->getDoctrine()->getManager();
            $event->setImage($fileName);
            $em->persist($event);
            $em->flush();
            return $this->redirectToRoute('consulter_event');
        }
        return $this->render('event/ajouterEvent.html.twig', [
            'form' => $form->createView()
        ]);;
    }

    /**
     * @Route("/event/afficher", name="consulter_event")
     */
    public function afficher(Request $request,EvenementRepository $repository)
    {
        $triEventForm = $this->createForm(TriEventType::class);
        $triEventForm->add('Trier', SubmitType::class);
        $searchEventForm = $this->createForm(SearchEventType::class);
        $searchEventForm->add('Rechercher', SubmitType::class);
        $events = $repository->findAll();
        if ($searchEventForm->HandleRequest($request)->isSubmitted() && $searchEventForm->isValid()) {
            $criteria = $searchEventForm->getData();

            $events = $repository->SearchEvent($criteria);
            return $this->render('event/consulterEvent.html.twig', [
                'search_form' => $searchEventForm->createView(),
                'tri_form' => $triEventForm->createView(),
                'events' => $events
            ]);;
        }
        if ($triEventForm->HandleRequest($request)->isSubmitted() && $triEventForm->isValid()) {
            $criteria = $triEventForm->getData();

            $events = $repository->TrierEvent($criteria);
            return $this->render('event/consulterEvent.html.twig', [
                'search_form' => $searchEventForm->createView(),
                'tri_form' => $triEventForm->createView(),
                'events' => $events
            ]);;
        }

        return $this->render('event/consulterEvent.html.twig', [

            'search_form' => $searchEventForm->createView(),
            'tri_form' => $triEventForm->createView(),
            'events' => $events


        ]);
    }

    /**
     * @Route("/event/afficher2", name="consulter2_event")
     */
    public function afficherEvAujourdhui(EvenementRepository $repository)
    {
        $value = new \DateTime();

        $time = $value->format('Y-m-d');
        $events = $repository->Eventaujourdhui($time);
        return $this->render('event/consulterEventAujourdhui.html.twig', [
            'events' => $events
        ]);;
    }
    /**
     * @Route("/event/participerEvent/{id}", name="participer_event")
     */
    public function participer($id,EvenementRepository $repository)
    {

        $event=$repository->find($id);
        return $this->render('event/participer_event.html.twig', [
            'event' => $event
        ]);;
    }
    /**
     * @Route("/checkout/{id}", name="checkout")
     */
    public function checkout($stripeSK,$id,EvenementRepository $repository): Response
    {
        $event=$repository->find($id);
        Stripe::setApiKey($stripeSK);

        $session = Session::create([
            'payment_method_types' => ['card'],
            'line_items'           => [
                [
                    'price_data' => [
                        'currency'     => 'usd',
                        'product_data' => [
                            'name' => $event->getNomEvenement(),
                        ],
                        'unit_amount'  => $event->getPrix(),
                    ],
                    'quantity'   => 1,
                ]
            ],
            'mode'                 => 'payment',
            'success_url'          => $this->generateUrl('success_url', ['id'=>$event->getReference()], UrlGeneratorInterface::ABSOLUTE_URL,array('id' => '25')),
            'cancel_url'           => $this->generateUrl('cancel_url', [], UrlGeneratorInterface::ABSOLUTE_URL),
        ]);
        return $this->redirect($session->url, 303);
    }


    /**
     * @Route("/success_url", name="success_url")
     */
    public function successUrl(EvenementRepository $repository): Response
    {
        $id=$_GET['id'];
        $event=$repository->find($id);
        $em = $this->getDoctrine()->getManager();
        $event->setNbrParticipant($event->getNbrParticipant()+1);
        $em->flush();
        return $this->render('event/success.html.twig', []);
    }


    /**
     * @Route("/cancel_url", name="cancel_url")
     */
   public function cancelUrl(): Response
    {
        return $this->render('event/cancel.html.twig', []);
    }

    /**
     * @Route("/event/delete/{id}", name="delete_event")
     */
    public function supprimer($id, EvenementRepository $repository)
    {
        $event = $repository->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($event);
        $em->flush();
        return $this->redirectToRoute('consulter_event');
    }


    /**
     * @Route("/event/edit/{id}", name="edit_event")
     */
    public function edit(Request $request, Evenement $evenement)
    {
        $form = $this->createForm(EvenementType::class, $evenement);
        $form->add('Save', SubmitType::class);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $file = $form->get('image')->getData();
            $fileName = md5(uniqid()) . '.' . $file->guessExtension();
            try {
                $file->move($this->getParameter('images_directory'),
                    $fileName
                );
            } catch (FileException $e) {
                //....
            }
            $em = $this->getDoctrine()->getManager();
            $evenement->setImage($fileName);
            $em->flush();
            return $this->redirectToRoute('consulter_event');

        }

        return $this->render('event/ajouterEvent.html.twig', [
            'form' => $form->createView()
        ]);;

    }

    /**
     * @Route("/event/rechercher", name="rechercher_event")
**/
    public function Rechercher(Request $request, EvenementRepository $repository): Response
    {
        $searchEventForm = $this->createForm(SearchEventType::class);
        $searchEventForm->add('Rechercher', SubmitType::class);
        $events = $repository->findAll();
        if ($searchEventForm->HandleRequest($request)->isSubmitted() && $searchEventForm->isValid()) {
            $criteria = $searchEventForm->getData();

            $events = $repository->SearchEvent($criteria);
            return $this->render('event/consulterEvent.html.twig', [
                'search_form' => $searchEventForm->createView(),
                'events' => $events
            ]);;
        }

        return $this->render('event/consulterEvent.html.twig', [

            'search_form' => $searchEventForm->createView(),
            'events' => $events


        ]);

    }
    /**
     * @Route("/event/ticket", name="ticket")
     */
    public function imprimerPDF()
    {
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);

        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('event/ticket.html.twig', [
            'title' => "Welcome to our PDF Test"
        ]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (inline view)
        $dompdf->stream("mypdf.pdf", [
            "Attachment" => false
        ]);
    }
}
