<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220320213703 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE marchandise (id INT AUTO_INCREMENT NOT NULL, id_marchandise INT AUTO_INCREMENT NOT NULL, nom_marchandise VARCHAR(255) NOT NULL, categorie_marchandise VARCHAR(255) NOT NULL, date_arrive VARCHAR(255) NOT NULL, quantite DOUBLE PRECISION NOT NULL, prix_unitaire_marchandise DOUBLE PRECISION NOT NULL, prix_total_marchandise DOUBLE PRECISION NOT NULL, id_fournisseur INT NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('DROP TABLE marchandise');
    }
}
