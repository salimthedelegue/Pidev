<?php

namespace App\Controller;

use App\Form\MagazineType;
use App\Entity\Magazine;
use App\Repository\MagazineRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\File\File;
use App\Controller\EntityManagerInterface;

class MagazineController extends AbstractController
{
    /**
     * @Route("/magazine", name="magazine")
     */
    public function index(): Response
    {
        return $this->render('magazine/index.html.twig', [
            'controller_name' => 'MagazineController',
        ]);
    }
    /**
     * @Route("/magazine/ajouterMag", name="ajouter_mag")
     */
    public function ajouterMagazine(Request $request)
    {
       $magazine= new Magazine();
       $form=$this->createForm(MagazineType::class,$magazine);
       //$form->add('Save',SubmitType::class);
       $form->handleRequest($request);
       if($form->isSubmitted() && $form->isValid()){
           $file = $form->get('imageMagazine')->getData();
           $fileName = md5(uniqid()).'.'.$file->guessExtension();
           try {
               $file->move($this->getParameter('images_directory'),
                   $fileName
               );
           }catch (FileException $e){
               //....
           }
           $em=$this->getDoctrine()->getManager();
           $magazine->setImageMagazine($fileName);
           $em->persist($magazine);
           $em->flush();
          return  $this->redirectToRoute('consulter_mag');
       }
        return $this->render('magazine/ajouterMagazine.html.twig', [
            'form' => $form->createView()
        ]);;
    }
    /**
     * @Route("/magazine/afficher", name="consulter_mag")
     */
    public function afficher(MagazineRepository $repository)
    {
        $magazines=$repository->findAll();
        return $this->render('magazine/consulterMagazine.html.twig', [
            'magazines' => $magazines
        ]);;
    }
    /**
     * @Route("/magazine/delete/{id}", name="delete_mag")
     */
    public function supprimer($id,MagazineRepository $repository)
    {
        $magazine=$repository->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($magazine);
        $em->flush();
        return $this->redirectToRoute('consulter_mag');
    }
    /**
     * @Route("/magazine/edit/{id}", name="edit_mag")
     */
    public function edit(Request $request,Magazine $magazine)
    {
        $form = $this->createForm(MagazineType::class, $magazine);
        $form->add('Save',SubmitType::class);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $file = $form->get('imageMagazine')->getData();
            $fileName = md5(uniqid()).'.'.$file->guessExtension();
            try {
                $file->move($this->getParameter('images_directory'),
                    $fileName
                );
            }catch (FileException $e){
                //....
            }
            $em=$this->getDoctrine()->getManager();
            $magazine->setImageMagazine($fileName);
            $em->flush();
            return $this->redirectToRoute('consulter_mag');

        }

        return $this->render('magazine/ajouterMagazine.html.twig', [
            'form' => $form->createView()
        ]);;

    }

    }
