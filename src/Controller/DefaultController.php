<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Dompdf\Dompdf;
use Dompdf\Options;
use App\Repository\CommandeRepository;


class DefaultController extends AbstractController
{
    /**
     * @Route("/default/{id}", name="app_default",methods={"GET"})
     */
    public function index(CommandeRepository $cr,$id)
    {
        $commande1=$cr->find($id);
       // dd($commande1->getPrixTotale());
$produits=$commande1->getproduits();
        // Configure Dompdf according to your needs
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');
        
        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);
        
        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('default/facture.html.twig', [
            'title' => "Welcome to our PDF Test",'produits' =>$produits,'commande1'=>$commande1
        ]);
        
        // Load HTML to Dompdf
        $dompdf->loadHtml($html);
        
        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A3', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("facture.pdf", [
            "Attachment" => true
        ]);


    }

}
