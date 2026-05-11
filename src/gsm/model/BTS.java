package gsm.model;

import gsm.exception.BTSFullException;
import gsm.exception.MSNotFoundException;
import java.util.ArrayList;

public class BTS {
    private int numero;
    private String emplacement;
    private double hauteur;
    private String typeMilieu;
    private double rayonCouverture;
    private double puissanceEmission;
    private int maxUtilisateurs;
    private ArrayList<MS> utilisateursAttaches;
    
    public BTS(int numero, String emplacement, double hauteur, String typeMilieu,
               double rayonCouverture, double puissanceEmission, int maxUtilisateurs) {
        if (numero <= 0) {
            this.numero = 1;
        } else {
            this.numero = numero;
        }
        if (emplacement == null || emplacement.isEmpty()) {
            this.emplacement = "Inconnu";
        } else {
            this.emplacement = emplacement;
        }
        if (hauteur <= 0) {
            this.hauteur = 30;
        } else {
            this.hauteur = hauteur;
        }
        if (typeMilieu == null || typeMilieu.isEmpty()) {
            this.typeMilieu = "Urbain";
        } else {
            this.typeMilieu = typeMilieu;
        }
        if (rayonCouverture <= 0) {
            this.rayonCouverture = 500;
        } else {
            this.rayonCouverture = rayonCouverture;
        }
        if (puissanceEmission <= 0) {
            this.puissanceEmission = 40;
        } else {
            this.puissanceEmission = puissanceEmission;
        }
        if (maxUtilisateurs <= 0) {
            this.maxUtilisateurs = 100;
        } else {
            this.maxUtilisateurs = maxUtilisateurs;
        }
        this.utilisateursAttaches = new ArrayList<>();
    }
    
    public int getNumero() { return numero; }
    public String getEmplacement() { return emplacement; }
    public double getHauteur() { return hauteur; }
    public String getTypeMilieu() { return typeMilieu; }
    public double getRayonCouverture() { return rayonCouverture; }
    public double getPuissanceEmission() { return puissanceEmission; }
    public int getMaxUtilisateurs() { return maxUtilisateurs; }
    public ArrayList<MS> getUtilisateursAttaches() { return utilisateursAttaches; }
    public int getNombreUtilisateurs() { return utilisateursAttaches.size(); }
    
    public boolean estSature() {
        return utilisateursAttaches.size() >= maxUtilisateurs;
    }
    
    public void attacherMS(MS ms) throws BTSFullException {
        if (estSature()) {
            throw new BTSFullException("BTS " + numero + " saturee. Impossible d'attacher " + ms.getNom());
        }
        utilisateursAttaches.add(ms);
        ms.setAttache(true);
    }
    
    public boolean detacherMS(String msisdn) {
        for (int i = 0; i < utilisateursAttaches.size(); i++) {
            if (utilisateursAttaches.get(i).getMsisdn().equals(msisdn)) {
                utilisateursAttaches.get(i).setAttache(false);
                utilisateursAttaches.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public MS rechercherMS(String msisdn) throws MSNotFoundException {
        for (int i = 0; i < utilisateursAttaches.size(); i++) {
            if (utilisateursAttaches.get(i).getMsisdn().equals(msisdn)) {
                return utilisateursAttaches.get(i);
            }
        }
        throw new MSNotFoundException("MS " + msisdn + " non trouve dans BTS " + numero);
    }
    
    public void afficher() {
        System.out.println("BTS N°" + numero + " | Emplacement : " + emplacement);
        System.out.println("Hauteur : " + hauteur + "m | Milieu : " + typeMilieu);
        System.out.println("Rayon : " + rayonCouverture + "m | Puissance : " + puissanceEmission + "W");
        System.out.println("Utilisateurs : " + utilisateursAttaches.size() + "/" + maxUtilisateurs);
        System.out.println("Etat : " + (estSature() ? "SATURÉE" : "Disponible"));
    }
    
    public void afficherUtilisateurs() {
        System.out.println("\nUtilisateurs attaches a BTS " + numero + " :");
        if (utilisateursAttaches.isEmpty()) {
            System.out.println("  Aucun utilisateur.");
        } else {
            for (int i = 0; i < utilisateursAttaches.size(); i++) {
                System.out.print("  " + (i + 1) + ". ");
                utilisateursAttaches.get(i).afficher();
            }
        }
    }
}