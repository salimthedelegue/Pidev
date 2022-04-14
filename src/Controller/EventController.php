<?php

namespace App\Controller;

use App\Form\EvenementType;

use App\Entity\Evenement;
use App\Form\SearchEventType;
use App\Repository\EvenementRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\File\File;
use App\Controller\EntityManagerInterface;

class EventController extends AbstractController
{
    /**
     * @Route("/event", name="event")
     */
    public function index(): Response
    {
        return $this->render('event/index.html.twig', [
            'controller_name' => 'EventController',
        ]);
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
    public function afficher(EvenementRepository $repository)
    {
        $events = $repository->findAll();
        return $this->render('event/consulterEvent.html.twig', [
            'events' => $events
        ]);;
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

    public function Rechercher(Request $request, EvenementRepository $repository): Response
    {
        $searchEventForm = $this->createForm(SearchEventType::class);

        if ($searchEventForm->HandleRequest($request)->isSubmitted() && $searchEventForm->isValid()) {
            $criteria = $searchEventForm->getData();

            $events = $repository->SearchEvent($criteria);
            return $this->render('event/consulterEvent.html.twig', [
                'events' => $events
            ]);;
        }
        return $this->render('event/consulterEvent.html.twig', [

            'search_form' => $searchEventForm->createView()

        ]);

    }*/
}
