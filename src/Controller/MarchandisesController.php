<?php

namespace App\Controller;
use App\Entity\Fournisseur;
use App\Entity\Marchandise;
use App\Form\FournisseurAjouterType;
use App\Form\FournisseurType;
use App\Form\MarchandiseType;
use App\Repository\MarchandiseRepository;
use Doctrine\Persistence\ObjectManager;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Form\FormBuilderInterface;
use Gedmo\Sluggable\Util\Urlizer;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Knp\Bundle\SnappyBundle\Snappy\Response\PdfResponse;
use Knp\Snappy\Pdf;





class MarchandisesController extends AbstractController
{
    /**
     * @Route("/marchandises", name="marchandises")
     */
    public function index(): Response
    {
        return $this->render('marchandises/index.html.twig', [
            'controller_name' => 'MarchandisesController',
        ]);
    }
    /**
     * @Route("/ajoutermarchandise", name="ajouter_marchandises")
     */

    public function ajoutermarch(Request $request){
        $marchandise =new Marchandise();
        $form=$this->createForm(MarchandiseType::class,$marchandise);
        $form->handleRequest($request);
        if ($form->isSubmitted()&& $form->isValid()){
            $marchandise=$form->getData();
            $em=$this->getDoctrine()->getManager();
            $em->persist($marchandise);
            $em->flush();
            return $this->redirectToRoute('consulter_marchandises');
        }
        return $this->render('marchandises/ajouter-march.html.twig',[
            'form_marchandise' => $form->createView()
        ]);;
    }
    /**
     * @Route("/consultermarch", name="consulter_marchandises")
     */
    public function consultermarchandises(): Response
    {

        // Méthode findBy qui permet de récupérer les données avec des critères de filtre et de tri
        $marchandises = $this->getDoctrine()->getRepository(Marchandise::class)->findAll();

        return $this->render('marchandises/consultermarch.html.twig', [
            'marchandises' => $marchandises
        ]);
    }
    /**
     * @Route("/generatpdf", name="pdf_marchandises")
     */
    public function pdf(): Response
    {
        $marchandises = $this->getDoctrine()->getRepository(Marchandise::class)->findAll();
        $html=$this->renderView('marchandises/pdf.html.twig', [
          'marchandises' => $marchandises,
      ]);
//      return new Response(
//          $this->knpSnappy->getOutputFromHtml($html),
//          200,
//          array(
//              'Content-Type'          => 'application/pdf',
//              'Content-Disposition'   => 'inline; filename="'.'file.pdf"'
//          )
//      );

        return new PdfResponse(
            $this->knpSnappy->getOutputFromHtml([$html]),
            'file.pdf',[]);
    }

    /**
     * @Route("/marchandises/delete/{idMarchandise}", name="delete_marchandise")
     */
    public function deletemarch($idMarchandise): Response
    {
        $em=$this->getDoctrine()->getManager();
        $marchandise=$em->getRepository(Marchandise::class)->find($idMarchandise);
        $em->remove($marchandise);
        $em->flush();
        $this->addFlash('info','Marchandise deleted Sucessfully');
        return $this->redirectToRoute('consulter_marchandises');
    }
    /**
     * @Route("/marchandises/update/{idMarchandise}", name="update_marchandise")
     */
    public function updatemarch($idMarchandise,Request $request): Response
    {
        $em=$this->getDoctrine()->getManager();
        $marchandise=$em->getRepository(Marchandise::class)->find($idMarchandise);
        $marchandise->setNomMarchandise($marchandise->getNomMarchandise());
        $marchandise->setDateArrive($marchandise->getDateArrive());
        $marchandise->setCategorieMarchandise($marchandise->getCategorieMarchandise());
        $marchandise->setQuantite($marchandise->getQuantite());
        $marchandise->setIdFournisseur($marchandise->getIdFournisseur());
        $marchandise->setPrixTotalMarchandise($marchandise->getPrixTotalMarchandise());
        $marchandise->setPrixUnitaireMarchandise($marchandise->getPrixUnitaireMarchandise());
        $form=$this->createForm(MarchandiseType::class,$marchandise);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid())
        {
            $marchandise=$form->getData();
            $em=$this->getDoctrine()->getManager();
            $em->persist($marchandise);
            $em->flush();
            return $this->redirectToRoute('consulter_marchandises');

        }
        return $this->render("marchandises/update-march.html.twig",[
            'form_update' => $form->createView()
        ]);;
    }


    public function quantity(MarchandiseRepository $repository,NormalizerInterface $Normalizer)
    {
        $quantities = $this->getDoctrine()->getRepository(Marchandise::class)->quantitybycategory();
        $jsonContentquant=$Normalizer->normalize($quantities,'json',['groups'=>'post:read']);
        return $this->render('marchandises/statistiques.html.twig',['qunatity'=>$quantities]);;
    }
    /**
     * @Route("/statmarchandises", name="stat_marchandise")
     */
    public function statmarchandises(NormalizerInterface $Normalizer): Response
    {
        $repository=$this->getDoctrine()->getRepository(Marchandise::class);
        $marchandises=$repository->findAll();
        $quantities = $this->getDoctrine()->getRepository(Marchandise::class)->quantitybycategory();
        $jsonContentquant=$Normalizer->normalize($quantities,'json',['groups'=>'post:read']);
        $jsonContent=$Normalizer->normalize($marchandises,'json',['groups'=>'post:read']);
        return $this->render('marchandises/statistiques.html.twig',['data'=>$jsonContent,'qunatity'=>$jsonContentquant]);;
    }
    public function __construct(\Knp\Snappy\Pdf $knpSnappy) { $this->knpSnappy = $knpSnappy; }

    /**
     * @Route("/ajouterfournisseur", name="ajouter_fournisseur")
     */
    public function ajouterfournisseur(Request $request,\Knp\Snappy\Pdf $knpSnappyPdf){
        $fournisseur =new Fournisseur();
        $form=$this->createForm(FournisseurAjouterType::class,$fournisseur);
        $form->handleRequest($request);
        if ($form->isSubmitted()&& $form->isValid()){
            /** @var UploadedFile $uploadedFile */
            $uploadedFile = $form->get('photo')->getData();
            $destination = $this->getParameter('kernel.project_dir').'/public/photo_upload';
            $originalFilename = pathinfo($uploadedFile->getClientOriginalName(), PATHINFO_FILENAME);
            $newFilename =$originalFilename.'-'.uniqid().'.'.$uploadedFile->guessExtension();
            $uploadedFile->move($destination,$newFilename);
            /* photo */
            /*
             $file=$form->get('photo')->getData();
            $fileName = md5(uniqid()).'.'.$file->guessExtension();
             $file->move($this->getParameter('public/photo_upload'), $fileName);
             $fournisseur->setPhoto($fileName);
*/
             /* photo */
            $fournisseur=$form->getData();
            $fournisseur->setPhoto($newFilename);
            /* PDF */
           /* $vars=1;
            $this->get('knp_snappy.pdf')->generateFromHtml(
                $this->renderView('marchandises/consulterfourn.html.twig', array('some'  => $vars)),
                '/public/photo_uplod/file.pdf'

            );*/

            /*return new PdfResponse(
                $this->knpSnappy->getOutputFromHtml([$html]),
                'file.pdf',[]);*/

            // @var Knp\Snappy\Pdf
            //$knpSnappyPdf->generate('http://www.google.fr', '/public/photo_uplod/file.pdf');
            //$knpSnappyPdf->generate(array('http://www.google.fr', 'http://www.knplabs.com', 'http://www.google.com'), '/path/to/the/file.pdf');

            //$snappy = new Pdf('/usr/local/bin/wkhtmltopdf');
            //$knpSnappyPdf->generate(array('http://www.google.fr', 'http://www.knplabs.com', 'http://www.google.com'), 'kernel.project_dir'.'/public/photo_upload/file1.pdf');

            //$snappy->generateFromHtml('<h1>Bill</h1><p>You owe me money, dude.</p>', '/public/photo_upload/bill-123.pdf');
            /* PDF */
            $em=$this->getDoctrine()->getManager();
            $em->persist($fournisseur);
            $em->flush();
            return $this->redirectToRoute('consulter_fournisseurs');
        }
        return $this->render('marchandises/ajouter-fourn.html.twig',[
            'form_fournisseur' => $form->createView()
        ]);;
    }
    /**
     * @Route("/consulterfourn", name="consulter_fournisseurs")
     */
    public function consulterfournisseurs(): Response
    {
        // Méthode findBy qui permet de récupérer les données avec des critères de filtre et de tri
        $fournisseurs = $this->getDoctrine()->getRepository(Fournisseur::class)->findAll();
        return $this->render('marchandises/consulterfourn.html.twig', [
            'fournisseurs' => $fournisseurs,
        ]);;
    }
    /**
     * @Route("/fournisseur/update/{idFournisseur}", name="update_fournisseur")
     */
    public function updatefourn($idFournisseur,Request $request): Response
    {
        $em=$this->getDoctrine()->getManager();
        $fournisseur=$em->getRepository(Fournisseur::class)->find($idFournisseur);
        $fournisseur->setNomFournisseur($fournisseur->getNomFournisseur());
        $fournisseur->setNumtelFournisseur($fournisseur->getNumtelFournisseur());
        $fournisseur->setNumfixeFournisseur($fournisseur->getNumfixeFournisseur());
        $fournisseur->setEmail($fournisseur->getEmail());
        $fournisseur->setMatriculeFiscale($fournisseur->getMatriculeFiscale());
        $fournisseur->setPhoto($fournisseur->getPhoto());
        $form=$this->createForm(FournisseurType::class,$fournisseur);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid())
        {

            $fournisseur=$form->getData();
            $em=$this->getDoctrine()->getManager();
            $em->persist($fournisseur);
            $em->flush();
            return $this->redirectToRoute('consulter_fournisseurs');

        }
        return $this->render("marchandises/update-fourn.html.twig",[
            'form_update_fournisseur' => $form->createView()
        ]);;
    }
    /**
     * @Route("/fournisseur/delete/{idFournisseur}", name="delete_fournisseur")
     */
    public function deletefourn($idFournisseur): Response
    {
        $em=$this->getDoctrine()->getManager();
        $fournisseur=$em->getRepository(Fournisseur::class)->find($idFournisseur);
        $em->remove($fournisseur);
        $em->flush();
        $this->addFlash('info','Fournisseur deleted Sucessfully');
        return $this->redirectToRoute('consulter_fournisseurs');
    }
    /**
     * @Route("/back", name="home")
     */
    public function home(): Response
    {
        return $this->render('marchandises/home.html.twig', [
            'controller_name' => 'MarchandisesController',
        ]);;
    }
    /**
     * @Route("/front", name="welcome_front")
     */
    public function welcomefront(): Response
    {
        return $this->render('Front-template/welcome-front.html.twig',[
        'controller_name' => 'MarchandisesController',
        ]);;
    }
    /**
     * @Route("/browsing", name="browsing_front")
     */
    public function browsing_front(): Response
    {
        return $this->render('Front-template/browsing-front.html.twig',[
            'controller_name' => 'MarchandisesController',
        ]);;
    }


}
