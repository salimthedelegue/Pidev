<?php

namespace App\Entity;

use App\Repository\MarchandiseRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=MarchandiseRepository::class)
 */
class Marchandise
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="integer")
     */
    private $id_marchandise;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $nom_marchandise;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $categorie_marchandise;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $date_arrive;

    /**
     * @ORM\Column(type="float")
     */
    private $quantite;

    /**
     * @ORM\Column(type="float")
     */
    private $prix_unitaire_marchandise;

    /**
     * @ORM\Column(type="float")
     */
    private $prix_total_marchandise;

    /**
     * @ORM\Column(type="integer")
     */
    private $id_fournisseur;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getIdMarchandise(): ?int
    {
        return $this->id_marchandise;
    }

    public function setIdMarchandise(int $id_marchandise): self
    {
        $this->id_marchandise = $id_marchandise;

        return $this;
    }

    public function getNomMarchandise(): ?string
    {
        return $this->nom_marchandise;
    }

    public function setNomMarchandise(string $nom_marchandise): self
    {
        $this->nom_marchandise = $nom_marchandise;

        return $this;
    }

    public function getCategorieMarchandise(): ?string
    {
        return $this->categorie_marchandise;
    }

    public function setCategorieMarchandise(string $categorie_marchandise): self
    {
        $this->categorie_marchandise = $categorie_marchandise;

        return $this;
    }

    public function getDateArrive(): ?string
    {
        return $this->date_arrive;
    }

    public function setDateArrive(string $date_arrive): self
    {
        $this->date_arrive = $date_arrive;

        return $this;
    }

    public function getQuantite(): ?float
    {
        return $this->quantite;
    }

    public function setQuantite(float $quantite): self
    {
        $this->quantite = $quantite;

        return $this;
    }

    public function getPrixUnitaireMarchandise(): ?float
    {
        return $this->prix_unitaire_marchandise;
    }

    public function setPrixUnitaireMarchandise(float $prix_unitaire_marchandise): self
    {
        $this->prix_unitaire_marchandise = $prix_unitaire_marchandise;

        return $this;
    }

    public function getPrixTotalMarchandise(): ?float
    {
        return $this->prix_total_marchandise;
    }

    public function setPrixTotalMarchandise(float $prix_total_marchandise): self
    {
        $this->prix_total_marchandise = $prix_total_marchandise;

        return $this;
    }

    public function getIdFournisseur(): ?int
    {
        return $this->id_fournisseur;
    }

    public function setIdFournisseur(int $id_fournisseur): self
    {
        $this->id_fournisseur = $id_fournisseur;

        return $this;
    }
}
