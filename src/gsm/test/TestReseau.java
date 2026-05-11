package gsm.test;

import gsm.exception.*;
import gsm.model.*;
import gsm.service.ServiceAppel;

public class TestReseau {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  PROJET RESEAU GSM - Realise par WADJE");
        System.out.println("  Licence IRT - 2025-2026");
        System.out.println("========================================\n");
        
        // Creation du reseau
        ReseauGSM reseau = new ReseauGSM("Orange Cameroun", 890.2, 935.2, "FDMA/TDMA", 100, 150, 30);
        
        // Creation des BTS
        BTS bts1 = new BTS(101, "Yaounde Centre", 45, "Urbain", 800, 60, 5);
        BTS bts2 = new BTS(102, "Douala Bonanjo", 50, "Urbain", 1000, 80, 3);
        BTS bts3 = new BTS(103, "Bafoussam Rural", 25, "Rural", 5000, 40, 4);
        
        reseau.ajouterBTS(bts1);
        reseau.ajouterBTS(bts2);
        reseau.ajouterBTS(bts3);
        
        // Creation des utilisateurs
        MS user1 = new Smartphone("Kamga", "Paul", "1234", "690000001", "208010000000001", "Android 13", 6.5);
        MS user2 = new Smartphone("Mvondo", "Marie", "5678", "690000002", "208010000000002", "iOS 16", 6.1);
        MS user3 = new Tablette("Tata", "Jean", "abcd", "690000003", "208010000000003", true, true);
        MS user4 = new Smartphone("Fotso", "Anne", "9999", "690000004", "208010000000004", "Android 12", 6.0);
        MS user5 = new MS("Eto'o", "David", "1111", "690000005", "208010000000005", "Smartphone");
        MS user6 = new Smartphone("Biya", "Rose", "2222", "690000006", "208010000000006", "Android 14", 6.7);
        
        // Attachement des utilisateurs aux BTS
        try {
            System.out.println("=== Attachement des utilisateurs ===\n");
            
            bts1.attacherMS(user1);
            System.out.println("✓ " + user1.getNom() + " attache a BTS " + bts1.getNumero());
            
            bts1.attacherMS(user2);
            System.out.println("✓ " + user2.getNom() + " attache a BTS " + bts1.getNumero());
            
            bts2.attacherMS(user3);
            System.out.println("✓ " + user3.getNom() + " attache a BTS " + bts2.getNumero());
            
            bts2.attacherMS(user4);
            System.out.println("✓ " + user4.getNom() + " attache a BTS " + bts2.getNumero());
            
            bts3.attacherMS(user5);
            System.out.println("✓ " + user5.getNom() + " attache a BTS " + bts3.getNumero());
            
            bts3.attacherMS(user6);
            System.out.println("✓ " + user6.getNom() + " attache a BTS " + bts3.getNumero());
            
        } catch (BTSFullException e) {
            System.out.println("✗ Erreur : " + e.getMessage());
        }
        
        // Affichage des BTS
        System.out.println("\n=== ETAT DES BTS ===\n");
        bts1.afficher();
        System.out.println();
        bts2.afficher();
        System.out.println();
        bts3.afficher();
        
        // Affichage des utilisateurs
        bts1.afficherUtilisateurs();
        bts2.afficherUtilisateurs();
        bts3.afficherUtilisateurs();
        
        // Test des appels
        System.out.println("\n=== TEST DES APPELS ===\n");
        ServiceAppel serviceAppel = new ServiceAppel();
        
        try {
            serviceAppel.appeler(reseau, "690000001", "1234", "690000003");
        } catch (MSNotFoundException | AuthenticationFailedException e) {
            System.out.println("✗ Erreur d'appel : " + e.getMessage());
        }
        
        try {
            serviceAppel.appeler(reseau, "690000004", "9999", "690000006");
        } catch (MSNotFoundException | AuthenticationFailedException e) {
            System.out.println("✗ Erreur d'appel : " + e.getMessage());
        }
        
        try {
            serviceAppel.appeler(reseau, "690000001", "mauvais_mdp", "690000002");
        } catch (MSNotFoundException | AuthenticationFailedException e) {
            System.out.println("✗ Erreur d'appel : " + e.getMessage());
        }
        
        try {
            serviceAppel.appeler(reseau, "690000001", "1234", "699999999");
        } catch (MSNotFoundException | AuthenticationFailedException e) {
            System.out.println("✗ Erreur d'appel : " + e.getMessage());
        }
        
        // Appels reçus
        System.out.println("\n=== APPELS RECUS ===\n");
        user3.afficherAppelsRecus();
        System.out.println();
        user6.afficherAppelsRecus();
        
        // Performances
        reseau.afficherPerformances();
        
        // Recherche
        System.out.println("\n=== RECHERCHE ===\n");
        try {
            BTS btsTrouve = reseau.rechercherBTS(102);
            System.out.println("BTS trouvee :");
            btsTrouve.afficher();
        } catch (BTSNotFoundException e) {
            System.out.println("✗ " + e.getMessage());
        }
        
        try {
            BTS localisation = reseau.localiserMS("690000005");
            System.out.println("\nMS 690000005 localise dans BTS N°" + localisation.getNumero() + " (" + localisation.getEmplacement() + ")");
        } catch (MSNotFoundException e) {
            System.out.println("✗ " + e.getMessage());
        }
        
        System.out.println("\n========================================");
        System.out.println("  FIN DU TEST - Projet WADJE");
        System.out.println("========================================");
    }
}