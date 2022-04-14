<?php

namespace App\Entity;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\ORM\Mapping as ORM;

/**
 * Organisateur
 *
 * @ORM\Table(name="organisateur")
 * @ORM\Entity
 */
/**
 * @ORM\Entity(repositoryClass="App\Repository\OrganisateurRepository")
 */
class Organisateur
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     * @Assert\NotBlank(message="le nom doit etre non vide")
     *
     * @ORM\Column(name="nom", type="string", length=50, nullable=false)
     */
    private $nom;

    /**
     * @var string
     * @Assert\NotBlank(message="le type doit etre non vide")
     *
     * @ORM\Column(name="type", type="string", length=50, nullable=false)
     */
    private $type;

    /**
     * @var string
     * @Assert\NotBlank(message="l email doit etre non vide")
     * @Assert\Email(
     *     message = "The email '{{ value }}' is not a valid email."
     * )
     *
     * @ORM\Column(name="email_organisateur", type="string", length=50, nullable=false)
     */
    private $emailOrganisateur;

    /**
     * @var int
     * @Assert\NotBlank(message="le numero doit etre non vide")
     * @Assert\Length(
     *      min = 8,
     *      max = 8,
     *      minMessage = "la taille de numméro est inférieir a 8",
     *      maxMessage = "la taille de numméro est superieur a 8"
     * )
     * @Assert\Positive(message="le numero est invalide")
     *
     * @ORM\Column(name="numtel_organisateur", type="integer", nullable=false)
     */
    private $numtelOrganisateur;

    /**
     * @ORM\OneToMany(targetEntity=Evenement::class, mappedBy="organisateur")
     */
    private $evenements;

    public function __construct()
    {
        $this->evenements = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
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

    /**
     * @return Collection|Evenement[]
     */
    public function getEvenements(): Collection
    {
        return $this->evenements;
    }

    public function addEvenement(Evenement $evenement): self
    {
        if (!$this->evenements->contains($evenement)) {
            $this->evenements[] = $evenement;
            $evenement->setOrganisateur($this);
        }

        return $this;
    }

    public function removeEvenement(Evenement $evenement): self
    {
        if ($this->evenements->removeElement($evenement)) {
            // set the owning side to null (unless already changed)
            if ($evenement->getOrganisateur() === $this) {
                $evenement->setOrganisateur(null);
            }
        }

        return $this;
    }

    public function __toString()
    {
        return $this->getNom();

    }
}
