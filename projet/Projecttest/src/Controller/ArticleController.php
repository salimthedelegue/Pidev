<?php

namespace App\Controller;

use App\Form\ArticleType;
use App\Entity\Article;
use App\Repository\ArticleRepository;
use App\Repository\MagazineRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\File\File;
use App\Controller\EntityManagerInterface;
use Dompdf\Dompdf;
use Dompdf\Options;

class ArticleController extends AbstractController
{
    /**
     * @Route("article/PDF/{id}", name="articlePDF")
     */
    public function generatePDF($id,ArticleRepository $repository,MagazineRepository $repo)
    {
        $article=$repository->find($id);
        $magazine=$repo->find($article->getRefMagazine());
        // Configure Dompdf according to your needs
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);

        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('article/PDF.html.twig', [
            'article' => $article,
            'magazine' => $magazine
        ]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (inline view)
        $dompdf->stream($article->getTitreArticle().".pdf", [
            "Attachment" => false
        ]);
    }

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
     * @Route("/article/afficherFront", name="consulter_art_front")
     */
    public function afficher1(ArticleRepository $repository)
    {
        $articles=$repository->findAll();
        return $this->render('article/articlefront.html.twig', [
            'articles' => $articles
        ]);;
    }
    /**
     * @Route("/article/articleDetails/{id}", name="articleDetails")
     */
    public function afficherArtDet($id,ArticleRepository $repository,MagazineRepository $repo)
    {
        $article=$repository->find($id);
        $magazine=$repo->find($article->getRefMagazine());
        return $this->render('article/articleDetails.html.twig', [
            'article' => $article,
            'magazine' => $magazine
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
