<?php

namespace App\Controller;
use App\Entity\Fournisseur;
use App\Entity\Marchandise;
use App\Form\FournisseurType;
use App\Form\MarchandiseType;
use Doctrine\Persistence\ObjectManager;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Form\FormBuilderInterface;
use Gedmo\Sluggable\Util\Urlizer;


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
     * @Route("/ajouter", name="ajouter_marchandises")
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
        return $this->render('marchandises/ajouter.html.twig',[
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
            'marchandises' => $marchandises,
        ]);;
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
    /**
     * @Route("/statmarchandises", name="stat_marchandise")
     */
    public function statmarchandises(): Response
    {
        return $this->render('marchandises/statistiques.html.twig');;
    }

    /**
     * @Route("/ajouterfournisseur", name="ajouter_fournisseur")
     */
    public function ajouterfournisseur(Request $request){
        $fournisseur =new Fournisseur();
        $form=$this->createForm(FournisseurType::class,$fournisseur);
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
     * @Route("/", name="home")
     */
    public function home(): Response
    {
        return $this->render('marchandises/home.html.twig', [
            'controller_name' => 'MarchandisesController',
        ]);;
    }
    /**
     * @Route("/front", name="home_front")
     */
    public function home_front(): Response
    {
        return $this->render('/Front-template/home-front.html.twig', [
            'controller_name' => 'MarchandisesController',
        ]);;
    }

}
