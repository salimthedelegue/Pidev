<?php

namespace App\Controller;



use App\Entity\Reclamation;
use App\Entity\User;
use App\Form\UserType;
use App\Repository\UserRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Routing\Annotation\Route;

class GestionuserController extends AbstractController
{
    /**
     * @Route("/gestionuser", name="gestionuser")
     */
    public function index(): Response
    {
        return $this->render('gestionuser/index.html.twig', [
            'controller_name' => 'GestionuserController',
        ]);
    }
    /**
     * @param UserRepository  $User
     * @return \Symfony\Component\HttpFoundation\Response
     *@Route("/afficheU",name="user_list")
     */
    public function afficher(UserRepository  $repository){

        $User = $this->getDoctrine()->getRepository(User::class)->findAll();
        return $this->render('gestionuser/index.html.twig',['User'=>$User]);

    }
    /**
     * @Route("/supprimer/{id}", name="suppuser")
     */
    public function supprimer(User $user): Response
    {
        $em = $this->getDoctrine()->getManager();
        $em-> remove($user);
        $em->flush();
        return $this->redirectToRoute('user_list');
    }

    /**
     * @Route("/user/edit/{id}", name="edit_USER")
     */
    public function edit(Request $request,User $user)
    {
        $form = $this->createForm(UserType::class, $user);
        $form->add('Save',SubmitType::class);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $manager=$this->getDoctrine()->getManager();
            $manager->flush();
            return $this->redirectToRoute('user_list');

        }

        return $this->render('gestionuser/modifieruser.html.twig', [
            'form' => $form->createView()
        ]);;

    }

    /**
     * @Route("/ajouteruser", name="ajouteruser")
     */


    public function ajouter(Request $request){
        $user =new User();
        $form=$this->createForm(UserType::class,$user);
        $form->add('Save',SubmitType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted()&& $form->isValid()){
            $user=$form->getData();
            $em=$this->getDoctrine()->getManager();
            $em->persist($user);
            $em->flush();

        }
        return $this->render('gestionuser/login-register.html.twig',[
            'formregister' => $form->createView()
        ]);;
    }

}
