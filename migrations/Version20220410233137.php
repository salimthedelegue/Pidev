<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220410233137 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs

    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE article CHANGE titre_article titre_article VARCHAR(50) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE contenu_article contenu_article TEXT NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE auteur_article auteur_article VARCHAR(50) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE genre_article genre_article VARCHAR(50) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE date_article date_article VARCHAR(50) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE commande CHANGE adresse_livraison adresse_livraison TEXT DEFAULT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE evenement CHANGE nom_evenement nom_evenement VARCHAR(50) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE image image VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE description description VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE emplacement emplacement VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE fournisseur CHANGE nom_fournisseur nom_fournisseur VARCHAR(30) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE numtel_fournisseur numtel_fournisseur VARCHAR(20) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE numFixe_fournisseur numFixe_fournisseur VARCHAR(20) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE email email VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE matricule_fiscale matricule_fiscale VARCHAR(20) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE photo photo VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE magazine CHANGE titre_magazine titre_magazine VARCHAR(50) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE genre_magazine genre_magazine VARCHAR(50) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE image_magazine image_magazine VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE description_magazine description_magazine VARCHAR(100) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE marchandise CHANGE nom_marchandise nom_marchandise VARCHAR(50) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE categorie_marchandise categorie_marchandise VARCHAR(50) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE date_arrive date_arrive VARCHAR(50) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE organisateur CHANGE nom nom VARCHAR(50) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE type type VARCHAR(50) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE email_organisateur email_organisateur VARCHAR(50) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE produit CHANGE nom_produit nom_produit VARCHAR(50) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE description_produit description_produit TEXT NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE image_produit image_produit VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE lien_produit lien_produit VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE categorie_produit categorie_produit VARCHAR(50) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE reclamation CHANGE description_reclamation description_reclamation VARCHAR(100) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE reservation CHANGE date_reservation date_reservation VARCHAR(50) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE service_de_maintenance CHANGE departement departement VARCHAR(50) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE servicesm CHANGE departement departement VARCHAR(100) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE user CHANGE mdp mdp VARCHAR(50) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE nom nom VARCHAR(50) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE prenom prenom VARCHAR(50) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE email email VARCHAR(50) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE role role VARCHAR(50) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE adresse_user adresse_user VARCHAR(50) NOT NULL COLLATE `utf8mb4_unicode_ci`');
    }
}
