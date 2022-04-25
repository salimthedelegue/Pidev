<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Magazine
 *
 * @ORM\Table(name="magazine")
 * @ORM\Entity
 */
class Magazine
{
    /**
     * @var int
     *
     * @ORM\Column(name="ref_magazine", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $refMagazine;

    /**
     * @var string
     *
     * @ORM\Column(name="titre_magazine", type="string", length=50, nullable=false)
     */
    private $titreMagazine;

    /**
     * @var string
     *
     * @ORM\Column(name="genre_magazine", type="string", length=50, nullable=false)
     */
    private $genreMagazine;

    /**
     * @var string
     *
     * @ORM\Column(name="image_magazine", type="string", length=255, nullable=false)
     */
    private $imageMagazine;

    /**
     * @var string
     *
     * @ORM\Column(name="description_magazine", type="string", length=100, nullable=false)
     */
    private $descriptionMagazine;

    /**
     * @var int
     *
     * @ORM\Column(name="selected", type="integer", nullable=false)
     */
    private $selected = '0';

    public function getRefMagazine(): ?int
    {
        return $this->refMagazine;
    }

    public function getTitreMagazine(): ?string
    {
        return $this->titreMagazine;
    }

    public function setTitreMagazine(string $titreMagazine): self
    {
        $this->titreMagazine = $titreMagazine;

        return $this;
    }

    public function getGenreMagazine(): ?string
    {
        return $this->genreMagazine;
    }

    public function setGenreMagazine(string $genreMagazine): self
    {
        $this->genreMagazine = $genreMagazine;

        return $this;
    }

    public function getImageMagazine(): ?string
    {
        return $this->imageMagazine;
    }

    public function setImageMagazine(string $imageMagazine): self
    {
        $this->imageMagazine = $imageMagazine;

        return $this;
    }

    public function getDescriptionMagazine(): ?string
    {
        return $this->descriptionMagazine;
    }

    public function setDescriptionMagazine(string $descriptionMagazine): self
    {
        $this->descriptionMagazine = $descriptionMagazine;

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


}
