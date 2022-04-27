<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Entity\Produit;
use App\Repository\ProduitRepository;
use App\Entity\Commande;
use App\Repository\CommandeRepository;
use App\Repository\UserRepository;

use phpDocumentor\Reflection\Types\Integer;
use Symfony\Component\HttpFoundation\JsonResponse;
use App\Tools\Cart\CartTools;
use Symfony\Component\HttpFoundation\Request;

class CommandeController extends AbstractController
{
    /**
     * @Route("/front", name="welcome_front")
     */
    public function welcomefront(): Response
    {
        return $this->render('Front-template/welcome-front.html.twig',[
        'controller_name' => 'CommandeController',
        ]);;
    }


     /**
     * @Route("/browsing", name="browsing_front")
     */

    public function  browsing_front(ProduitRepository $produitRepository,CartTools $cartTools): Response
    {
        
        return $this->render('Front-template/browsing-front.html.twig',[
        'controller_name' => 'CommandeController',
        'produits' => $produitRepository->produitvalide(),
        'totalItems' => $cartTools->getTotalItem()


        ]);;
    }
    
/**
     * @Route("/browsing/produit/{id}", name="produit_show_client", methods={"GET"})
     */
    public function productpage(Produit $produit): Response
    {
        return $this->render('Front-template/show-produit-client.html.twig',[
        'controller_name' => 'CommandeController',
        'produit'=>$produit,
        

        ]);;
    }

  /**
     * @Route("/home/produits", name="home_produits")
     */
    public function indexhome(): Response
    {
        return $this->render('home.html.twig', [
            'controller_name' => 'CommandeController',
        ]);
    }




/**
 * @Route("/produit/client/", name="produit_client", methods={"GET"})
 */
      
    public function indexproduit(ProduitRepository $produitRepository,CartTools $cartTools): Response
    {
        return $this->render('commande/index.html.twig', [
            'produits' => $produitRepository->produitvalide(),
            'totalItems' => $cartTools->getTotalItem()
        ]);
    }
  
    
/**
 * @Route("/mescommandes", name="affichercommandes", methods={"GET"})
 */
public function affichercommandes(CommandeRepository $cr)
{// 1 : id user connecter
    $produits=null;
    return $this->render('commande/mescommandes.html.twig', [
        'commandes' => $cr->affichermescommandes(1),'produits'=>$produits,'commande1'=>null
    ]);
}


/**
 * @Route("/mescommandes/{id}/mesproduits", name="voirproduitcommande", methods={"GET"})
 */
public function voirproduit(UserRepository $user,CommandeRepository $cr,$id): Response
{
$commande1=$cr->find($id);
$produits=$commande1->getproduits();
    return $this->render('commande/mescommandes.html.twig', [

        'produits' =>$produits,'commande1'=>$commande1,'commandes' => $cr->affichermescommandes(1)
    ]);
}

/**
 * @param Request $request
 * @param CommmandeRepository $repository
 * @Route("/mescommandesaj/mesproduits", name="voirproduitcommandeaj")
 */
public function voirproduitc(CommandeRepository $cr,Request $request): Response
{
    $id=$request->get('q');

$commande1=$cr->find($id);
$produits=$commande1->getproduits();

$jsonData = array();
      $idx = 0;
      foreach($produits as $produit) {
         $temp = array(
            'idp' => $produit->getId_produit(),
            'name' => $produit->getNomProduit(),
            'prix' => $produit->getPrixProduit(),
            'lien' => $produit->getLienProduit(),
            'image'=>$produit->getImageProduit(),
         );
         $jsonData[$idx++] = $temp;
      }

      

      return new Response(json_encode($jsonData)); 


}






}
