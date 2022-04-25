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
    public function index(CommandeRepository $com,UserRepository $user): Response
    {
        $commandes=$com->commande_valide();
       // dd($commandes);
        foreach($commandes as $command)
            {
                 $idu=$command->getIdClient();
            }

            dd($idu);

            foreach($idu as $id)
            {
                $usersoncomande=$user->find($id);
            }

            var_dump($idu);

dd($usersoncomande);
        return $this->render('commande_users/index.html.twig', [
            'controller_name' => 'CommandeUsersController',
        ]);
    }

    /**
 * @Route("/mescommandes/{id}/mesproduits", name="voirproduitcommande", methods={"GET"})
 */
/*public function voirproduit(UserRepository $user,CommandeRepository $cr,$id): Response
{
$commande1=$cr->find($id);
//dd($commande1->getIdClient());
dd($user->find($commande1->getIdClient())->getEmail());
$produits=$commande1->getproduits();
    return $this->render('commande/mescommandes.html.twig', [

        'produits' =>$produits,'commande1'=>$commande1,'commandes' => $cr->affichermescommandes(1)
    ]);
}*/
}
