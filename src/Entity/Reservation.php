<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Reservation
 *
 * @ORM\Table(name="reservation")
 * @ORM\Entity
 */
class Reservation
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_reservation", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idReservation;

    /**
     * @var int
     *
     * @ORM\Column(name="ref_evenement", type="integer", nullable=false)
     */
    private $refEvenement;

    /**
     * @var int
     *
     * @ORM\Column(name="id_user", type="integer", nullable=false)
     */
    private $idUser;

    /**
     * @var string
     *
     * @ORM\Column(name="date_reservation", type="string", length=50, nullable=false)
     */
    private $dateReservation;

    public function getIdReservation(): ?int
    {
        return $this->idReservation;
    }

    public function getRefEvenement(): ?int
    {
        return $this->refEvenement;
    }

    public function setRefEvenement(int $refEvenement): self
    {
        $this->refEvenement = $refEvenement;

        return $this;
    }

    public function getIdUser(): ?int
    {
        return $this->idUser;
    }

    public function setIdUser(int $idUser): self
    {
        $this->idUser = $idUser;

        return $this;
    }

    public function getDateReservation(): ?string
    {
        return $this->dateReservation;
    }

    public function setDateReservation(string $dateReservation): self
    {
        $this->dateReservation = $dateReservation;

        return $this;
    }


}
