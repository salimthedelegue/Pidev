<?php

namespace App\Entity;

use DateTime;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints\Date;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Evenement
 *
 * @ORM\Table(name="evenement")
 * @ORM\Entity
 */
/**
 * @ORM\Entity(repositoryClass="App\Repository\EvenementRepository")
 */
class Evenement
{
    /**
     * @var int
     *
     * @ORM\Column(name="reference", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $reference;

    /**
     * @var string
     * @Assert\NotBlank(message="le nom doit etre non vide")
     *
     * @ORM\Column(name="nom_evenement", type="string", length=50, nullable=false)
     */
    private $nomEvenement;

    /**
     * @var datetime
    @Assert\Type(
     *      type = "\DateTime",
     *      message = "vacancy.date.valid",
     * )
     * @Assert\GreaterThanOrEqual(
     *      value = "today",
     *      message = "la date inferieur a la date d aujourd hui"
     * )
     *
     * @ORM\Column(name="date_debut", type="date", length=50, nullable=false)
     */
    private $dateDebut;

    /**
     * @var datetime
     * @Assert\Type(
     *      type = "\DateTime",
     *      message = "vacancy.date.valid",
     * )
     * @Assert\GreaterThanOrEqual(
     *      value = "today",

     * )
     * @Assert\Expression(
     *     "this.getDateFin() >= this.getDateDebut()",
     *     message="la date fin est inférieur a la date début"
     * )
     *
     * @ORM\Column(name="date_fin", type="date", length=50, nullable=false)
     */
    private $dateFin;

    /**
     * @var float
    /**
     * @Assert\Positive(message="le prix est négatif")

     * @Assert\NotBlank(message="le prix doit etre non vide")
     *
     * @ORM\Column(name="prix", type="float", precision=10, scale=0, nullable=false)
     */
    private $prix;

    /**
     * @var string

     *
     * @ORM\Column(name="image", type="string", length=255, nullable=false)
     */
    private $image;

    /**
     * @var string
     * @Assert\NotBlank(message="la description doit etre non vide")
     *
     * @ORM\Column(name="description", type="string", length=255, nullable=false)
     */
    private $description;

    /**
     * @var string
     * @Assert\NotBlank(message="l emplacement doit etre non vide")
     *
     * @ORM\Column(name="emplacement", type="string", length=255, nullable=false)
     */
    private $emplacement;



    /**
     * @var int
     *
     * @ORM\Column(name="nbr_participant", type="integer", nullable=false)
     */
    private $nbrParticipant = '0';

    /**
     * @var int
     *
     * @ORM\Column(name="nbr_max_participant", type="integer", nullable=false)
     */
    private $nbrMaxParticipant;

    /**
     * @var int
     *
     * @ORM\Column(name="selected", type="integer", nullable=false)
     */
    private $selected = '0';

    /**
     * @ORM\ManyToOne(targetEntity=Organisateur::class, inversedBy="evenements")
     * @ORM\JoinColumn(nullable=false)
     */
    private $organisateur;

    public function getReference(): ?int
    {
        return $this->reference;
    }

    public function getNomEvenement()
    {
        return $this->nomEvenement;
    }

    public function setNomEvenement( $nomEvenement)
    {
        $this->nomEvenement = $nomEvenement;

        return $this;
    }

    public function getDateDebut(): ?datetime
    {
        return $this->dateDebut;
    }

    public function setDateDebut(datetime $dateDebut): self
    {
        $this->dateDebut = $dateDebut;

        return $this;
    }

    public function getDateFin(): ?datetime
    {
        return $this->dateFin;
    }

    public function setDateFin(datetime $dateFin): self
    {
        $this->dateFin = $dateFin;

        return $this;
    }

    public function getPrix(): ?float
    {
        return $this->prix;
    }

    public function setPrix(float $prix): self
    {
        $this->prix = $prix;

        return $this;
    }

    public function getImage(): ?string
    {
        return $this->image;
    }

    public function setImage(string $image): self
    {
        $this->image = $image;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): self
    {
        $this->description = $description;

        return $this;
    }

    public function getEmplacement(): ?string
    {
        return $this->emplacement;
    }

    public function setEmplacement(string $emplacement): self
    {
        $this->emplacement = $emplacement;

        return $this;
    }



    public function getNbrParticipant(): ?int
    {
        return $this->nbrParticipant;
    }

    public function setNbrParticipant(int $nbrParticipant): self
    {
        $this->nbrParticipant = $nbrParticipant;

        return $this;
    }

    public function getNbrMaxParticipant(): ?int
    {
        return $this->nbrMaxParticipant;
    }

    public function setNbrMaxParticipant(int $nbrMaxParticipant): self
    {
        $this->nbrMaxParticipant = $nbrMaxParticipant;

        return $this;
    }

    public function getSelected(): ?int
    {
        return $this->selected;
    }

    public function setSelected(int $selected): self
    {
        $this->selected = $selected;

        return $this;
    }

    public function __toString()
    {
        return $this->nomEvenement." ".$this->description." ".$this->prix." ".$this->emplacement;
    }

    public function getOrganisateur(): ?Organisateur
    {
        return $this->organisateur;
    }

    public function setOrganisateur(?Organisateur $organisateur): self
    {
        $this->organisateur = $organisateur;

        return $this;
    }
}
