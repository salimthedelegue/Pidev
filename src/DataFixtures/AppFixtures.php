<?php

namespace App\DataFixtures;

use Doctrine\Bundle\FixturesBundle\Fixture;
use Doctrine\Persistence\ObjectManager;
use App\Entity\Marchandise;

class AppFixtures extends Fixture
{
    public function load(ObjectManager $manager): void
    {
    // fake data goes here just fill the table
      /*
        for ($i=0;$i<=10;$i++)
        {
            $marchandise=new Marchandise();
            $marchandise->setCategorieMarchandise("<p>categorie num $i</p>")
                        ->setDateArrive("25/25/222")
                        ->setNomMarchandise("<p>nom num $i</p>");

        }
        // $product = new Product();
        // $manager->persist($product);
*/

        $manager->flush();
    }
}
