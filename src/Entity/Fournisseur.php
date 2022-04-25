<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

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
     * @Assert\NotBlank(
     *     message="veuillez remplir le champ"
     * )
     * @Assert\Regex (
     *     pattern= "/([A-Z][a-z]+([ ]?[a-z]?['-]?[A-Z][a-z]+)*)$/",
     *     match=true,
     *     message="Le nom doit etre un nom Majus"
     * )
     */
    private $nomFournisseur;

    /**
     * @var string
     *
     * @ORM\Column(name="numtel_fournisseur", type="string", length=20, nullable=false)
     * @Assert\NotBlank (
     *     message="veuillez remplir le champ"
     * )
     * @Assert\Regex (
     *     pattern="/^(\\+\\d{1,2}\\s?)?1?\\-?\\.?\\s?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$/",
     *     message="+216 + 8 "
     * )
     */
    private $numtelFournisseur;

    /**
     * @var string
     *
     * @ORM\Column(name="numFixe_fournisseur", type="string", length=20, nullable=false)
     * @Assert\NotBlank (
     *     message="veuillez remplir le champ"
     * )
     * @Assert\Regex (
     *     pattern="/^(\\+\\d{1,2}\\s?)?1?\\-?\\.?\\s?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$/",
     *     message="+216 + 8 "
     * )
     */
    private $numfixeFournisseur;

    /**
     * @var string
     *
     * @ORM\Column(name="email", type="string", length=255, nullable=false)
     * @Assert\NotBlank(
     *     message="veuillez remplir le champ"
     * )
     * @Assert\Email(
     *     message="email invalid"
     * )
     */
    private $email;

    /**
     * @var string
     *
     * @ORM\Column(name="matricule_fiscale", type="string", length=20, nullable=false)
     * @Assert\NotBlank ()
     * @Assert\Regex (
     *     pattern="/^[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{4}$/",
     *     message="matricule invalide"
     * )
     */
    private $matriculeFiscale;

    /**
     * @var string
     *
     * @ORM\Column(name="photo", type="string", length=255, nullable=false)
     * @Assert\NotBlank(message="selectez une image")
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

    public function getPhoto()
    {
        return $this->photo;
    }

    public function setPhoto(string $photo)
    {
        $this->photo = $photo;

        return $this;
    }
    public function __toString()
    {
        return $this->nomFournisseur;
    }




}
