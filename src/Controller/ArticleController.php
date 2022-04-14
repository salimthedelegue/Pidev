<?php

namespace App\Controller;

use App\Form\ArticleType;
use App\Entity\Article;
use App\Repository\ArticleRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\File\File;
use App\Controller\EntityManagerInterface;

class ArticleController extends AbstractController
{
    /**
     * @Route("/article", name="article")
     */
    public function index(): Response
    {
        return $this->render('article/index.html.twig', [
            'controller_name' => 'articleController',
        ]);
    }
    /**
     * @Route("/article/ajouterArt", name="ajouter_art")
     */
    public function ajouterArticle(Request $request)
    {
       $article= new Article();
       $form=$this->createForm(ArticleType::class,$article);
       $form->handleRequest($request);
       if($form->isSubmitted() && $form->isValid()){
          
           $em=$this->getDoctrine()->getManager();
           $em->persist($article);
           $em->flush();
          return  $this->redirectToRoute('consulter_art');
       }
        return $this->render('article/ajouterArticle.html.twig', [
            'form' => $form->createView()
        ]);;
    }
    /**
     * @Route("/article/afficher", name="consulter_art")
     */
    public function afficher(ArticleRepository $repository)
    {
        $articles=$repository->findAll();
        return $this->render('article/consulterArticle.html.twig', [
            'articles' => $articles
        ]);;
    }
    /**
     * @Route("/article/delete/{id}", name="delete_art")
     */
    public function supprimer($id,ArticleRepository $repository)
    {
        $article=$repository->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($article);
        $em->flush();
        return $this->redirectToRoute('consulter_art');
    }
    /**
     * @Route("/article/edit/{id}", name="edit_art")
     */
    public function edit(Request $request,Article $article)
    {
        $form = $this->createForm(ArticleType::class, $article);
        $form->add('Save',SubmitType::class);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
          
            $em=$this->getDoctrine()->getManager();
           
            $em->flush();
            return $this->redirectToRoute('consulter_art');

        }

        return $this->render('article/ajouterArticle.html.twig', [
            'form' => $form->createView()
        ]);;

    }

    }
