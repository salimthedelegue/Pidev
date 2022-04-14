<?php

namespace App\Repository;

use App\Entity\Evenement;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Evenement|null find($id, $lockMode = null, $lockVersion = null)
 * @method Evenement|null findOneBy(array $criteria, array $orderBy = null)
 * @method Evenement[]    findAll()
 * @method Evenement[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class EvenementRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Evenement::class);
    }

    // /**
    //  * @return Evenement[] Returns an array of Evenement objects
    //  */

    public function Eventaujourdhui($value)
    {
        return $this->createQueryBuilder('e')
            ->andWhere('e.dateDebut = :val')
            ->setParameter('val', $value)

            ->getQuery()
            ->getResult()
        ;
    }
    public function SearchEvent($criteria)
    {

        if($criteria['selon']=='Nom'){
            return $this->createQueryBuilder('e')
                ->andwhere('e.nomEvenement like :nom')
                ->setParameter('nom', $criteria['rech'].'%')
                ->getQuery()
                ->getResult()
                ;
        }}

    /*
    public function findOneBySomeField($value): ?Evenement
    {
        return $this->createQueryBuilder('e')
            ->andWhere('e.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
