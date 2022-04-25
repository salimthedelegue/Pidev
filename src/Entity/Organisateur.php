<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Organisateur
 *
 * @ORM\Table(name="organisateur")
 * @ORM\Entity
 */
class Organisateur
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_organisateur", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idOrganisateur;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=50, nullable=false)
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="type", type="string", length=50, nullable=false)
     */
    private $type;

    /**
     * @var string
     *
     * @ORM\Column(name="email_organisateur", type="string", length=50, nullable=false)
     */
    private $emailOrganisateur;

    /**
     * @var int
     *
     * @ORM\Column(name="numtel_organisateur", type="integer", nullable=false)
     */
    private $numtelOrganisateur;

    public function getIdOrganisateur(): ?int
    {
        return $this->idOrganisateur;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getType(): ?string
    {
        return $this->type;
    }

    public function setType(string $type): self
    {
        $this->type = $type;

        return $this;
    }

    public function getEmailOrganisateur(): ?string
    {
        return $this->emailOrganisateur;
    }

    public function setEmailOrganisateur(string $emailOrganisateur): self
    {
        $this->emailOrganisateur = $emailOrganisateur;

        return $this;
    }

    public function getNumtelOrganisateur(): ?int
    {
        return $this->numtelOrganisateur;
    }

    public function setNumtelOrganisateur(int $numtelOrganisateur): self
    {
        $this->numtelOrganisateur = $numtelOrganisateur;

        return $this;
    }


}
