<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

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
    public function ajouter(): Response
    {
        return $this->render('marchandises/ajouter.html.twig', [
            'controller_name' => 'MarchandisesController',
        ]);;
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

}
