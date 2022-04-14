<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
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

}
