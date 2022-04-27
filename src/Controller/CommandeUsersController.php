<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\CommandeRepository;
use App\Repository\UserRepository;



class CommandeUsersController extends AbstractController
{
    /**
     * @Route("/commandes/users", name="app_commande_users")
     */
    public function index( UserRepository $user): Response
    {
       // $commandes=$com->commande_valide();
        //dd($commandes);

  
$users=$user->findClient();
           



        return $this->render('commande_users/index.html.twig', [
'users'=>$users
        ]);
    }

    /**
 * @Route("/commandesuser/{id}", name="usercommandes", methods={"GET"})
 */
public function voirproduit(UserRepository $user,CommandeRepository $cr,$id): Response
{
    $user1=$user->find($id);
$commande1=$cr->affichermescommandes($id);

    return $this->render('commande_users/commandesusers.html.twig', [

       'commande1'=>$commande1,'user1'=>$user1
    ]);
}




/**
 * @Route("/commandesuser/{id}/mesproduits", name="voirproduitcommandeuser", methods={"GET"})
 */
public function voirproduituser(UserRepository $user,CommandeRepository $cr,$id): Response
{
$commande1=$cr->find($id);
$produits=$commande1->getproduits();
    return $this->render('commande_users/produitsuser.html.twig', [

        'produits' =>$produits,'commande1'=>$commande1
    ]);
}



}
