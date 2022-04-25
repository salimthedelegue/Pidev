<?php

namespace App\Entity;

use App\Repository\ProduitRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Validator\Constraints\MinLength;

/**
 * @ORM\Entity(repositoryClass=ProduitRepository::class)
 */
class Produit
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     * 
     */
    private $id_produit;

    /**
     * @ORM\Column(type="string", length=50)
     * @Assert\NotBlank(message="nom is required")
     */
    private $nom_produit;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="des is required")
     * @Assert\Length(
    *   min=8,
    *   minMessage="Votre mot de passe doit contenir au moins {{limit}} cacactères"
    * )
     */

    private $description_produit;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="img is required")
     */
    private $image_produit;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $lien_produit;

    /**
     * @ORM\Column(type="float")
     * @Assert\NotBlank(message="lien is required")
     */
    private $prix_produit;

    /**
     * @ORM\Column(type="string", length=50)
      *@Assert\NotBlank(message="prix is required")

     */
    private $categorie_produit;

       /**
     * @ORM\Column(type="float")
     */
    private $valide;

    /**
     * @ORM\ManyToMany(targetEntity=Commande::class)
     */
    private $commandes;

    public function __construct()
    {
        $this->commandes = new ArrayCollection();
    }

    

    

    public function getNomProduit(): ?string
    {
        return $this->nom_produit;
    }

    public function setNomProduit(string $nom_produit): self
    {
        $this->nom_produit = $nom_produit;

        return $this;
    }

    public function getDescriptionProduit(): ?string
    {
        return $this->description_produit;
    }

    public function setDescriptionProduit(string $description_produit): self
    {
        $this->description_produit = $description_produit;

        return $this;
    }

    public function getImageProduit(): ?string
    {
        return $this->image_produit;
    }

    public function setImageProduit(string $image_produit): self
    {
        $this->image_produit = $image_produit;

        return $this;
    }

    public function getLienProduit(): ?string
    {
        return $this->lien_produit;
    }

    public function setLienProduit(string $lien_produit): self
    {
        $this->lien_produit = $lien_produit;

        return $this;
    }

    public function getPrixProduit(): ?float
    {
        return $this->prix_produit;
    }

    public function setPrixProduit(float $prix_produit): self
    {
        $this->prix_produit = $prix_produit;

        return $this;
    }

    public function getCategorieProduit(): ?string
    {
        return $this->categorie_produit;
    }

    public function setCategorieProduit(string $categorie_produit): self
    {
        $this->categorie_produit = $categorie_produit;

        return $this;
    }

    /**
     * Get the value of valide
     */ 
    public function getValide()
    {
        return $this->valide;
    }

    /**
     * Set the value of valide
     *
     * @return  self
     */ 
    public function setValide($valide)
    {
        $this->valide = $valide;

        return $this;
    }

    /**
     * Get the value of id_produit
     */ 
    public function getId_produit()
    {
        return $this->id_produit;
    }

    /**
     * Set the value of id_produit
     *
     * @return  self
     */ 
    public function setId_produit($id_produit)
    {
        $this->id_produit = $id_produit;

        return $this;
    }

    /**
     * @return Collection<int, Commande>
     */
    public function getCommandes(): Collection
    {
        return $this->commandes;
    }

    public function addCommande(Commande $commande): self
    {
        if (!$this->commandes->contains($commande)) {
            $this->commandes[] = $commande;
            $commande->addProduit($this);
        }

        return $this;
    }

    public function removeCommande(Commande $commande): self
    {
        if ($this->commandes->removeElement($commande)) {
            $commande->removeProduit($this);
        }

        return $this;
    }

    
    }
