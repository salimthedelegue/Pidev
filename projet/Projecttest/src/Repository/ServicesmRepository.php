<?php

namespace App\Repository;

use App\Entity\Servicesm;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Servicesm|null find($id, $lockMode = null, $lockVersion = null)
 * @method Servicesm|null findOneBy(array $criteria, array $orderBy = null)
 * @method Servicesm[]    findAll()
 * @method Servicesm[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ServicesmRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Servicesm::class);
    }

    // /**
    //  * @return Servicesm[] Returns an array of Servicesm objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('s')
            ->andWhere('s.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('s.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Servicesm
    {
        return $this->createQueryBuilder('s')
            ->andWhere('s.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
