<?php

namespace App\Entity;
use DateTime;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints\Date;
use Symfony\Component\Validator\Constraints as Assert;


/**
 * Article
 *
 * @ORM\Table(name="article", indexes={@ORM\Index(name="fk_magazine", columns={"ref_magazine"})})
 * @ORM\Entity
 */
/**
 * @ORM\Entity(repositoryClass="App\Repository\ArticleRepository")
 */
class Article
{
    /**
     * @var int
     *
     * @ORM\Column(name="ref_article", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $refArticle;

    /**
     * @var string
     * @Assert\NotBlank(message="le titre doit etre non vide")
     * @ORM\Column(name="titre_article", type="string", length=50, nullable=false)
     */
    private $titreArticle;

    /**
     * @Assert\NotBlank(message="le contenu doit etre non vide")
     * @var string
     *
     * @ORM\Column(name="contenu_article", type="text", length=65535, nullable=false)
     */
    private $contenuArticle;

    /**
     * @var string
     * @Assert\NotBlank(message="l'auteur doit etre non vide")
     * @ORM\Column(name="auteur_article", type="string", length=50, nullable=false)
     */
    private $auteurArticle;

    /**
     * @var string
    * @Assert\NotBlank(message="le genre doit etre non vide")
     * @ORM\Column(name="genre_article", type="string", length=50, nullable=false)
     */
    private $genreArticle;

    /**
     
     * @var datetime
     * @Assert\Type(
     *      type = "\DateTime",
     *      message = "vacancy.date.valid",
     * )
     * @Assert\LessThanOrEqual(
     *      value = "today",
     *        message="la date doit etre inferieur a aujourhui")

     *
     * @ORM\Column(name="date_article", type="date", length=50, nullable=false)
     */
    private $dateArticle;

    
    /**
     * @var \Magazine
     * @Assert\NotBlank(message="veuillez choisir une magazine")
     * @ORM\ManyToOne(targetEntity="Magazine")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="ref_magazine", referencedColumnName="ref_magazine")
     * })
     */
    private $refMagazine;

    public function getRefArticle(): ?int
    {
        return $this->refArticle;
    }

    public function getTitreArticle(): ?string
    {
        return $this->titreArticle;
    }

    public function setTitreArticle(string $titreArticle): self
    {
        $this->titreArticle = $titreArticle;

        return $this;
    }

    public function getContenuArticle(): ?string
    {
        return $this->contenuArticle;
    }

    public function setContenuArticle(string $contenuArticle): self
    {
        $this->contenuArticle = $contenuArticle;

        return $this;
    }

    public function getAuteurArticle(): ?string
    {
        return $this->auteurArticle;
    }

    public function setAuteurArticle(string $auteurArticle): self
    {
        $this->auteurArticle = $auteurArticle;

        return $this;
    }

    public function getGenreArticle(): ?string
    {
        return $this->genreArticle;
    }

    public function setGenreArticle(string $genreArticle): self
    {
        $this->genreArticle = $genreArticle;

        return $this;
    }

    public function getDateArticle(): ?datetime
    {
        return $this->dateArticle;
    }

    public function setDateArticle(datetime $dateArticle): self
    {
        $this->dateArticle = $dateArticle;

        return $this;
    }

    

    public function getRefMagazine(): ?Magazine
    {
        return $this->refMagazine;
    }

    public function setRefMagazine(?Magazine $refMagazine): self
    {
        $this->refMagazine = $refMagazine;

        return $this;
    }


}
