<?php

namespace App\Controller;

use App\Tools\Cart\CartTools;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\CommandeRepository;

use App\Entity\Commande;


class CartController extends AbstractController
{
    /**
     * @Route("/panier", name="cart_index")
     */
    public function index(CartTools $cartTools)
    {
        $produits=null;
        return $this->render('cart/index.html.twig', [
            'items' => $cartTools->getFullCart(),
            'totalTVA' => 0,
            'tva' => $cartTools->getTva(),
            'totalItems' => $cartTools->getTotalItem(),
            'totalTTC' => $cartTools->getTotalTTC(),
            'produits'=>$produits ,
            'commande'=>null           
        ]);
    }

    /**
     * @Route("/panier/add/{id}", name="cart_add")
     */
    public function add($id, CartTools $cartTools)
    {
        $cartTools->add($id);

        return $this->redirectToRoute('browsing_front');
    }

    /**
     * @Route("/panier/remove/{id}", name="cart_remove")
     */
    public function remove($id, CartTools $cartTools)
    {
         $cartTools->remove($id);       

        return $this->redirectToRoute('cart_index');
    }

    /**
     * @Route("/panier/comfirm", name="comfirmcom")
     */
    public function addc(CartTools $cartTools,CommandeRepository $cr)
    {
        $Commande=new Commande();
      $tab=$cartTools->getFullCart();

      for ($i=0; $i < count($tab) ; $i++) { 
          $Commande->addProduit($tab[$i]['product']);
      }
      
      $Commande->settype(1);
      $date=date("d/m/Y");
      $chdate=\DateTime::createFromFormat('d/m/Y', $date);
      $Commande->setDateCommande($chdate);
      $Commande->setAdresseLivraison("aaaa");
      $Commande->setPrixTotale($cartTools->getTotalTTC());
      $Commande->setIdClient(1);

      $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($Commande);
            $entityManager->flush();
            for ($i=0; $i < count($tab) ; $i++) { 
                //$Commande->addProduit($tab[$i]['product']);
                $produits[$i]=$tab[$i]['product'];

            }  
            for ($i=0; $i < count($tab) ; $i++) { 
                //$Commande->addProduit($tab[$i]['product']);
                $cartTools->remove($tab[$i]['product']->getId_produit());

            }  

 /*       
if($produits==null)
{
    return $this->render('cart/index.html.twig', [
        'items' => null,
        'totalTVA' => null,
        'tva' => null,
        'totalItems' => null,
        'totalTTC' => null,
        'produits'=>null
    ]);  
}*/
            return $this->render('cart/index.html.twig', [
                'items' => null,
                'totalTVA' => null,
                'tva' => null,
                'totalItems' => null,
                'totalTTC' => null,
                'produits'=>$produits,
                'commande'=>$Commande
            ]);   
        
        }
}
