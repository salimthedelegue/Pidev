<?php

namespace App\DataFixtures;

use App\Entity\Marchandise;
use App\Entity\Fournisseur;
use Doctrine\Bundle\FixturesBundle\Fixture;
use Doctrine\Persistence\ObjectManager;


class MarchandiseFixtures extends Fixture
{
    public function load(ObjectManager $manager): void
    {
        $faker=\Faker\Factory::create('fr_FR');
        //create 3 marchandises
        for ($i=1 ;$i<=3;$i++) {
            $marchandise = new Marchandise();
            $marchandise->setNomMarchandise($faker->sentence())
                ->setCategorieMarchandise($faker->sentence())
                ->setDateArrive($faker->date())
                ->setQuantite($faker->randomDigit())
                ->setPrixUnitaireMarchandise($faker->randomDigit())
                ->setPrixTotalMarchandise($faker->randomDigit)
                ->setIdFournisseur(null);

            $manager->persist($marchandise);
        }
            for ($j=0;$j<=9;$j++){
            $fournisseur=new Fournisseur();
            $fournisseur->setNomFournisseur($faker->sentence())
                ->setNumtelFournisseur($faker->e164PhoneNumber())
                ->setNumfixeFournisseur($faker->e164PhoneNumber())
                ->setEmail($faker->companyEmail())
                ->setPhoto($faker->imageUrl())
                ->setMatriculeFiscale($faker->randomDigit());
                $manager->persist($fournisseur);

        }


        $manager->flush();
    }
}
