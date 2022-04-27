<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Article
 *
 * @ORM\Table(name="article", indexes={@ORM\Index(name="fk_magazine", columns={"ref_magazine"})})
 * @ORM\Entity
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
     *
     * @ORM\Column(name="titre_article", type="string", length=50, nullable=false)
     */
    private $titreArticle;

    /**
     * @var string
     *
     * @ORM\Column(name="contenu_article", type="text", length=65535, nullable=false)
     */
    private $contenuArticle;

    /**
     * @var string
     *
     * @ORM\Column(name="auteur_article", type="string", length=50, nullable=false)
     */
    private $auteurArticle;

    /**
     * @var string
     *
     * @ORM\Column(name="genre_article", type="string", length=50, nullable=false)
     */
    private $genreArticle;

    /**
     * @var string
     *
     * @ORM\Column(name="date_article", type="string", length=50, nullable=false)
     */
    private $dateArticle;

    /**
     * @var int
     *
     * @ORM\Column(name="ref_magazine", type="integer", nullable=false)
     */
    private $refMagazine;

    /**
     * @var int|null
     *
     * @ORM\Column(name="selected", type="integer", nullable=true)
     */
    private $selected = '0';

    /**
     * @var int
     *
     * @ORM\Column(name="vues", type="integer", nullable=false)
     */
    private $vues = '0';

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

    public function getDateArticle(): ?string
    {
        return $this->dateArticle;
    }

    public function setDateArticle(string $dateArticle): self
    {
        $this->dateArticle = $dateArticle;

        return $this;
    }

    public function getRefMagazine(): ?int
    {
        return $this->refMagazine;
    }

    public function setRefMagazine(int $refMagazine): self
    {
        $this->refMagazine = $refMagazine;

        return $this;
    }

    public function getSelected(): ?int
    {
        return $this->selected;
    }

    public function setSelected(?int $selected): self
    {
        $this->selected = $selected;

        return $this;
    }

    public function getVues(): ?int
    {
        return $this->vues;
    }

    public function setVues(int $vues): self
    {
        $this->vues = $vues;

        return $this;
    }


}
