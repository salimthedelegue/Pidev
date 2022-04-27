<?php

namespace App\Form;

use App\Entity\Marchandise;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class MarchandiseType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('nomMarchandise')
            ->add('categorieMarchandise',ChoiceType::class,['choices'=>[
                'Veuillez choisir' =>null,
                'Streaming related' => 'Streaming related',
                'Hardware components' => 'Hardware components',
                'Gears' => 'Gears',
            ],])
            ->add('dateArrive')
            ->add('quantite')
            ->add('prixUnitaireMarchandise')
            ->add('prixTotalMarchandise')
            ->add('idFournisseur')
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Marchandise::class,
        ]);
    }
}
