<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Servicesm
 *
 * @ORM\Table(name="servicesm")
 * @ORM\Entity
 */
class Servicesm
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_service", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idService;

    /**
     * @var string
     *
     * @ORM\Column(name="departement", type="string", length=100, nullable=false)
     */
    private $departement;

    /**
     * @var int
     *
     * @ORM\Column(name="nbr_employes", type="integer", nullable=false)
     */
    private $nbrEmployes;

    public function getIdService(): ?int
    {
        return $this->idService;
    }

    public function getDepartement(): ?string
    {
        return $this->departement;
    }

    public function setDepartement(string $departement): self
    {
        $this->departement = $departement;

        return $this;
    }

    public function getNbrEmployes(): ?int
    {
        return $this->nbrEmployes;
    }

    public function setNbrEmployes(int $nbrEmployes): self
    {
        $this->nbrEmployes = $nbrEmployes;

        return $this;
    }


}
