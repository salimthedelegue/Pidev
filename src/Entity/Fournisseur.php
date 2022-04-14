<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Fournisseur
 *
 * @ORM\Table(name="fournisseur")
 * @ORM\Entity
 */
/**
 * @ORM\Entity(repositoryClass="App\Repository\FournisseurRepository")
 */
class Fournisseur
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_fournisseur", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idFournisseur;

    /**
     * @var string
     *
     * @ORM\Column(name="nom_fournisseur", type="string", length=30, nullable=false)
     */
    private $nomFournisseur;

    /**
     * @var string
     *
     * @ORM\Column(name="numtel_fournisseur", type="string", length=20, nullable=false)
     */
    private $numtelFournisseur;

    /**
     * @var string
     *
     * @ORM\Column(name="numFixe_fournisseur", type="string", length=20, nullable=false)
     */
    private $numfixeFournisseur;

    /**
     * @var string
     *
     * @ORM\Column(name="email", type="string", length=255, nullable=false)
     */
    private $email;

    /**
     * @var string
     *
     * @ORM\Column(name="matricule_fiscale", type="string", length=20, nullable=false)
     */
    private $matriculeFiscale;

    /**
     * @var string
     *
     * @ORM\Column(name="photo", type="string", length=255, nullable=false)
     */
    private $photo;

    public function getIdFournisseur(): ?int
    {
        return $this->idFournisseur;
    }

    public function getNomFournisseur(): ?string
    {
        return $this->nomFournisseur;
    }

    public function setNomFournisseur(string $nomFournisseur): self
    {
        $this->nomFournisseur = $nomFournisseur;

        return $this;
    }

    public function getNumtelFournisseur(): ?string
    {
        return $this->numtelFournisseur;
    }

    public function setNumtelFournisseur(string $numtelFournisseur): self
    {
        $this->numtelFournisseur = $numtelFournisseur;

        return $this;
    }

    public function getNumfixeFournisseur(): ?string
    {
        return $this->numfixeFournisseur;
    }

    public function setNumfixeFournisseur(string $numfixeFournisseur): self
    {
        $this->numfixeFournisseur = $numfixeFournisseur;

        return $this;
    }

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(string $email): self
    {
        $this->email = $email;

        return $this;
    }

    public function getMatriculeFiscale(): ?string
    {
        return $this->matriculeFiscale;
    }

    public function setMatriculeFiscale(string $matriculeFiscale): self
    {
        $this->matriculeFiscale = $matriculeFiscale;

        return $this;
    }

    public function getPhoto(): ?string
    {
        return $this->photo;
    }

    public function setPhoto(string $photo): self
    {
        $this->photo = $photo;

        return $this;
    }


}
