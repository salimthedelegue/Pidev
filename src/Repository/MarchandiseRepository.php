<?php

namespace App\Repository;

use App\Entity\Marchandise;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Marchandise|null find($id, $lockMode = null, $lockVersion = null)
 * @method Marchandise|null findOneBy(array $criteria, array $orderBy = null)
 * @method Marchandise[]    findAll()
 * @method Marchandise[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class MarchandiseRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Marchandise::class);
    }
   public function quantitybycategory(){
         /*$entityManager=$this->getEntityManager();
         $query=$entityManager->createQuery('SELECT SUM(quantite) AS "quantity",categorie AS "categorie_marchandise" FROM App\Entity\Marchandise GROUP BY categorie_marchandise');
         return $query->getResult();
*/

     return $this->CreateQueryBuilder('m')
         ->select('SUM(m.quantite) as sum,m.categorieMarchandise as categorie_marchandise')
         ->groupBy('m.categorieMarchandise')
         ->getQuery()
         ->getResult();
       }


    // /**
    //  * @return Marchandise[] Returns an array of Marchandise objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('m')
            ->andWhere('m.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('m.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Marchandise
    {
        return $this->createQueryBuilder('m')
            ->andWhere('m.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
