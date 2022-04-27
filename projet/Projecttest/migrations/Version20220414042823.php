<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220414042823 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE article (ref_article INT AUTO_INCREMENT NOT NULL, ref_magazine INT DEFAULT NULL, titre_article VARCHAR(50) NOT NULL, contenu_article TEXT NOT NULL, auteur_article VARCHAR(50) NOT NULL, genre_article VARCHAR(50) NOT NULL, date_article VARCHAR(50) NOT NULL, selected INT DEFAULT NULL, vues INT NOT NULL, INDEX IDX_23A0E66B19BE963 (ref_magazine), PRIMARY KEY(ref_article)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE commade_produit (id INT AUTO_INCREMENT NOT NULL, id_produit INT DEFAULT NULL, id_commande INT DEFAULT NULL, INDEX IDX_C6963B4EF7384557 (id_produit), INDEX IDX_C6963B4E3E314AE8 (id_commande), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE commande (id_commande INT AUTO_INCREMENT NOT NULL, id_client INT DEFAULT NULL, type INT NOT NULL, date_commande DATE DEFAULT NULL, adresse_livraison TEXT DEFAULT NULL, prix_totale DOUBLE PRECISION NOT NULL, INDEX IDX_6EEAA67DE173B1B8 (id_client), PRIMARY KEY(id_commande)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE evenement (reference INT AUTO_INCREMENT NOT NULL, organisateur_id INT NOT NULL, nom_evenement VARCHAR(50) NOT NULL, date_debut DATE NOT NULL, date_fin DATE NOT NULL, prix DOUBLE PRECISION NOT NULL, image VARCHAR(255) NOT NULL, description VARCHAR(255) NOT NULL, emplacement VARCHAR(255) NOT NULL, nbr_participant INT NOT NULL, nbr_max_participant INT NOT NULL, selected INT NOT NULL, INDEX IDX_B26681ED936B2FA (organisateur_id), PRIMARY KEY(reference)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE fournisseur (id_fournisseur INT AUTO_INCREMENT NOT NULL, nom_fournisseur VARCHAR(30) NOT NULL, numtel_fournisseur VARCHAR(20) NOT NULL, numFixe_fournisseur VARCHAR(20) NOT NULL, email VARCHAR(255) NOT NULL, matricule_fiscale VARCHAR(20) NOT NULL, photo VARCHAR(255) NOT NULL, PRIMARY KEY(id_fournisseur)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE magazine (ref_magazine INT AUTO_INCREMENT NOT NULL, titre_magazine VARCHAR(50) NOT NULL, genre_magazine VARCHAR(50) NOT NULL, image_magazine VARCHAR(255) NOT NULL, description_magazine VARCHAR(100) NOT NULL, selected INT NOT NULL, PRIMARY KEY(ref_magazine)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE marchandise (id_marchandise INT AUTO_INCREMENT NOT NULL, id_fournisseur INT DEFAULT NULL, nom_marchandise VARCHAR(50) NOT NULL, categorie_marchandise VARCHAR(50) NOT NULL, date_arrive VARCHAR(50) NOT NULL, quantite INT NOT NULL, prix_unitaire_marchandise DOUBLE PRECISION NOT NULL, prix_total_marchandise DOUBLE PRECISION NOT NULL, INDEX IDX_D9A871DE2E8C07C5 (id_fournisseur), PRIMARY KEY(id_marchandise)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE organisateur (id INT AUTO_INCREMENT NOT NULL, nom VARCHAR(50) NOT NULL, type VARCHAR(50) NOT NULL, email_organisateur VARCHAR(50) NOT NULL, numtel_organisateur INT NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE produit (id_produit INT AUTO_INCREMENT NOT NULL, nom_produit VARCHAR(50) NOT NULL, description_produit TEXT NOT NULL, image_produit VARCHAR(255) NOT NULL, lien_produit VARCHAR(255) NOT NULL, prix_produit DOUBLE PRECISION NOT NULL, categorie_produit VARCHAR(50) NOT NULL, valide INT DEFAULT 1 NOT NULL, PRIMARY KEY(id_produit)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE reclamation (id_reclamation INT AUTO_INCREMENT NOT NULL, date_reclamation DATE NOT NULL, description_reclamation VARCHAR(100) NOT NULL, id_service1 INT NOT NULL, PRIMARY KEY(id_reclamation)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE reservation (id_reservation INT AUTO_INCREMENT NOT NULL, ref_evenement INT NOT NULL, id_user INT NOT NULL, date_reservation VARCHAR(50) NOT NULL, PRIMARY KEY(id_reservation)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE service_de_maintenance (id_service INT AUTO_INCREMENT NOT NULL, departement VARCHAR(50) NOT NULL, nbr_employes INT NOT NULL, PRIMARY KEY(id_service)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE servicesm (id_service INT AUTO_INCREMENT NOT NULL, departement VARCHAR(100) NOT NULL, nbr_employes INT NOT NULL, PRIMARY KEY(id_service)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE user (id_user INT AUTO_INCREMENT NOT NULL, mdp VARCHAR(50) NOT NULL, nom VARCHAR(50) NOT NULL, prenom VARCHAR(50) NOT NULL, email VARCHAR(50) NOT NULL, role VARCHAR(50) NOT NULL, numtel_user INT NOT NULL, adresse_user VARCHAR(50) NOT NULL, connected INT NOT NULL, PRIMARY KEY(id_user)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE article ADD CONSTRAINT FK_23A0E66B19BE963 FOREIGN KEY (ref_magazine) REFERENCES magazine (ref_magazine)');
        $this->addSql('ALTER TABLE commade_produit ADD CONSTRAINT FK_C6963B4EF7384557 FOREIGN KEY (id_produit) REFERENCES produit (id_produit)');
        $this->addSql('ALTER TABLE commade_produit ADD CONSTRAINT FK_C6963B4E3E314AE8 FOREIGN KEY (id_commande) REFERENCES commande (id_commande)');
        $this->addSql('ALTER TABLE commande ADD CONSTRAINT FK_6EEAA67DE173B1B8 FOREIGN KEY (id_client) REFERENCES user (id_user)');
        $this->addSql('ALTER TABLE evenement ADD CONSTRAINT FK_B26681ED936B2FA FOREIGN KEY (organisateur_id) REFERENCES organisateur (id)');
        $this->addSql('ALTER TABLE marchandise ADD CONSTRAINT FK_D9A871DE2E8C07C5 FOREIGN KEY (id_fournisseur) REFERENCES fournisseur (id_fournisseur)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE commade_produit DROP FOREIGN KEY FK_C6963B4E3E314AE8');
        $this->addSql('ALTER TABLE marchandise DROP FOREIGN KEY FK_D9A871DE2E8C07C5');
        $this->addSql('ALTER TABLE article DROP FOREIGN KEY FK_23A0E66B19BE963');
        $this->addSql('ALTER TABLE evenement DROP FOREIGN KEY FK_B26681ED936B2FA');
        $this->addSql('ALTER TABLE commade_produit DROP FOREIGN KEY FK_C6963B4EF7384557');
        $this->addSql('ALTER TABLE commande DROP FOREIGN KEY FK_6EEAA67DE173B1B8');
        $this->addSql('DROP TABLE article');
        $this->addSql('DROP TABLE commade_produit');
        $this->addSql('DROP TABLE commande');
        $this->addSql('DROP TABLE evenement');
        $this->addSql('DROP TABLE fournisseur');
        $this->addSql('DROP TABLE magazine');
        $this->addSql('DROP TABLE marchandise');
        $this->addSql('DROP TABLE organisateur');
        $this->addSql('DROP TABLE produit');
        $this->addSql('DROP TABLE reclamation');
        $this->addSql('DROP TABLE reservation');
        $this->addSql('DROP TABLE service_de_maintenance');
        $this->addSql('DROP TABLE servicesm');
        $this->addSql('DROP TABLE user');
    }
}
