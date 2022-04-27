<?php

namespace App\Repository;

use App\Entity\ServiceDeMaintenance;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method ServiceDeMaintenance|null find($id, $lockMode = null, $lockVersion = null)
 * @method ServiceDeMaintenance|null findOneBy(array $criteria, array $orderBy = null)
 * @method ServiceDeMaintenance[]    findAll()
 * @method ServiceDeMaintenance[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ServiceDeMaintenanceRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, ServiceDeMaintenance::class);
    }

    // /**
    //  * @return ServiceDeMaintenance[] Returns an array of ServiceDeMaintenance objects
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
    public function findOneBySomeField($value): ?ServiceDeMaintenance
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
