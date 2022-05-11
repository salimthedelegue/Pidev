<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Serializer\Annotation\Groups;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Marchandise
 *
 * @ORM\Table(name="marchandise", indexes={@ORM\Index(name="fk_fournisseur", columns={"id_fournisseur"})})
 * @ORM\Entity
 */
/**
 * @ORM\Entity(repositoryClass="App\Repository\MarchandiseRepository")
 */
class Marchandise
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_marchandise", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     * @Groups("post:read")
     */
    private $idMarchandise;

    /**
     * @var string
     *
     * @ORM\Column(name="nom_marchandise", type="string", length=50, nullable=false)
     * @Groups("post:read")
     */
    private $nomMarchandise;

    /**
     * @var string
     *
     * @ORM\Column(name="categorie_marchandise", type="string", length=50, nullable=false)
     * @Groups("post:read")
     */
    private $categorieMarchandise;

    /**
     * @var string
     *
     * @ORM\Column(name="date_arrive", type="string", length=50, nullable=false)
     * @Groups("post:read")
     */
    private $dateArrive;

    /**
     * @var int
     *
     * @ORM\Column(name="quantite", type="integer", nullable=false)
     * @Groups("post:read")
     */
    private $quantite;

    /**
     * @var float
     *
     * @ORM\Column(name="prix_unitaire_marchandise", type="float", precision=10, scale=0, nullable=false)
     * @Groups("post:read")
     */
    private $prixUnitaireMarchandise;

    /**
     * @var float
     *
     * @ORM\Column(name="prix_total_marchandise", type="float", precision=10, scale=0, nullable=false)
     * @Groups("post:read")
     */
    private $prixTotalMarchandise;

    /**
     * @var \Fournisseur
     *
     * @ORM\ManyToOne(targetEntity="Fournisseur")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_fournisseur", referencedColumnName="id_fournisseur")
     * })
     * @Groups("post:read")
     */
    private $idFournisseur;

    public function getIdMarchandise(): ?int
    {
        return $this->idMarchandise;
    }

    public function getNomMarchandise(): ?string
    {
        return $this->nomMarchandise;
    }

    public function setNomMarchandise(string $nomMarchandise): self
    {
        $this->nomMarchandise = $nomMarchandise;

        return $this;
    }

    public function getCategorieMarchandise(): ?string
    {
        return $this->categorieMarchandise;
    }

    public function setCategorieMarchandise(string $categorieMarchandise): self
    {
        $this->categorieMarchandise = $categorieMarchandise;

        return $this;
    }

    public function getDateArrive(): ?string
    {
        return $this->dateArrive;
    }

    public function setDateArrive(string $dateArrive): self
    {
        $this->dateArrive = $dateArrive;

        return $this;
    }

    public function getQuantite(): ?int
    {
        return $this->quantite;
    }

    public function setQuantite(int $quantite): self
    {
        $this->quantite = $quantite;

        return $this;
    }

    public function getPrixUnitaireMarchandise(): ?float
    {
        return $this->prixUnitaireMarchandise;
    }

    public function setPrixUnitaireMarchandise(float $prixUnitaireMarchandise): self
    {
        $this->prixUnitaireMarchandise = $prixUnitaireMarchandise;

        return $this;
    }

    public function getPrixTotalMarchandise(): ?float
    {
        return $this->prixTotalMarchandise;
    }

    public function setPrixTotalMarchandise(float $prixTotalMarchandise): self
    {
        $this->prixTotalMarchandise = $prixTotalMarchandise;

        return $this;
    }

    public function getIdFournisseur(): ?Fournisseur
    {
        return $this->idFournisseur;
    }

    public function setIdFournisseur(?Fournisseur $idFournisseur): self
    {
        $this->idFournisseur = $idFournisseur;

        return $this;
    }

    public function __toString()
    {
        return $this->nomMarchandise;
    }


}
