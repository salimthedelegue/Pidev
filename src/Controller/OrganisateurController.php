<?php

namespace App\Controller;
use App\Entity\Organisateur;
use App\Form\OrganisateurType;
use App\Repository\OrganisateurRepository;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class OrganisateurController extends AbstractController
{
    /**
     * @Route("/organisateur", name="organisateur")
     */
    public function index(): Response
    {
        return $this->render('organisateur/index.html.twig', [
            'controller_name' => 'OrganisateurController',
        ]);
    }

    /**
     * @Route("/organisateur/afficher", name="consulter_organisateur")
     */
    public function afficherOrg(OrganisateurRepository $repository)
    {
        $organisateurs = $repository->findAll();
        return $this->render('organisateur/consulterOrganisateur.html.twig', [
            'organisateurs' => $organisateurs
        ]);;
    }
    /**
     * @Route("/organisateur/afficherFront", name="organisateurFront")
     */
    public function afficherOrgFront(OrganisateurRepository $repository)
    {
        $organisateurs = $repository->findAll();
        return $this->render('organisateur/OrganisateurFront.html.twig', [
            'organisateurs' => $organisateurs
        ]);;
    }

    /**
     * @Route("/organisateur/delete/{id}", name="delete_organisateur")
     */
    public function supprimerOrg($id, OrganisateurRepository $repository)
    {
        $organisateur = $repository->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($organisateur);
        $em->flush();
        return $this->redirectToRoute('consulter_organisateur');
    }

    /**
     * @Route("/organisateur/ajouterEv", name="ajouter_organisateur")
     */
    public function ajouterOrg(Request $request,\Swift_Mailer $mailer)
    {
        $organisateur = new Organisateur();
        $form = $this->createForm(OrganisateurType::class, $organisateur);
        $form->add('Save', SubmitType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {

            $em = $this->getDoctrine()->getManager();
            $em->persist($organisateur);
            $em->flush();

            $message=( new \Swift_Message('You got Mail'))
                ->setFrom('noursen.amamo@esprit.tn')
                ->setTo($form->get('emailOrganisateur')->getData())
                ->setBody('Bienvenu dans notre equipe '.$form->get('nom')->getData(),'text/plain');
            $mailer->send($message);

            return $this->redirectToRoute('consulter_organisateur');
        }
        return $this->render('organisateur/ajouterOrganisateur.html.twig', [
            'form' => $form->createView()
        ]);

    }

    /**
     * @Route("/organisateur/edit/{id}", name="edit_organisateur")
     */
    public function edit(Request $request, Organisateur $organisateur)
    {
        $form = $this->createForm(OrganisateurType::class, $organisateur);
        $form->add('Save', SubmitType::class);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $manager = $this->getDoctrine()->getManager();
            $manager->flush();
            return $this->redirectToRoute('consulter_organisateur');

        }
        return $this->render('organisateur/ajouterOrganisateur.html.twig', [
            'form' => $form->createView()
        ]);
    }
}

