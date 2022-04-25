<?php

namespace App\Controller;
use App\Entity\Produit;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class MailController extends AbstractController
{
    /**
     * @Route("/mail", name="app_mail")
     */
    public function index(): Response
    {
        return $this->render('mail/index.html.twig', [
            'controller_name' => 'MailController',
        ]);
    }


/**
     * @Route("/mailer", name="app_mail")
     */
    public function mail(\Swift_Mailer $mailer)
    {

        
        //o cree un msg
        $message =(new \Swift_Message('Activation de votre compte'))
        //on attribut l'expediteur
        ->setFrom('testutilisateurs1@gmail.com')
        //on atribut le destinataire
        ->setTo("ahmedaminenafti76@gmail.com")
        // on met le contenu
        ->setBody(
            $this->renderView('mail/index.html.twig'),
            'text/html'
        );
    //on envoie le mail
    $mailer->send($message);
    $this->addFlash('message','Un email de verification de compte a ete envoyee');


    return $this->render('mail/index.html.twig', [
        'controller_name' => 'MailController',
    ]);
}
    }

    


