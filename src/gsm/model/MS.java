package gsm.model;

import java.util.ArrayList;

public class MS {
    private String nom;
    private String prenom;
    private String motDePasse;
    private String msisdn;
    private String imsi;
    private String typeAppareil;
    private ArrayList<String> appelsRecus;
    private boolean attache;
    
    public MS(String nom, String prenom, String motDePasse, String msisdn, String imsi, String typeAppareil) {
        if (nom == null || nom.isEmpty()) {
            this.nom = "Inconnu";
        } else {
            this.nom = nom;
        }
        if (prenom == null || prenom.isEmpty()) {
            this.prenom = "Inconnu";
        } else {
            this.prenom = prenom;
        }
        if (motDePasse == null || motDePasse.isEmpty()) {
            this.motDePasse = "1234";
        } else {
            this.motDePasse = motDePasse;
        }
        if (msisdn == null || msisdn.isEmpty()) {
            this.msisdn = "0600000000";
        } else {
            this.msisdn = msisdn;
        }
        if (imsi == null || imsi.isEmpty()) {
            this.imsi = "208000000000000";
        } else {
            this.imsi = imsi;
        }
        if (typeAppareil == null || typeAppareil.isEmpty()) {
            this.typeAppareil = "Smartphone";
        } else {
            this.typeAppareil = typeAppareil;
        }
        this.appelsRecus = new ArrayList<>();
        this.attache = false;
    }
    
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getMsisdn() { return msisdn; }
    public String getImsi() { return imsi; }
    public String getTypeAppareil() { return typeAppareil; }
    public boolean isAttache() { return attache; }
    public void setAttache(boolean attache) { this.attache = attache; }
    
    public boolean verifierMotDePasse(String mdp) {
        return this.motDePasse.equals(mdp);
    }
    
    public void recevoirAppel(String appelant) {
        appelsRecus.add(appelant);
    }
    
    public void afficherAppelsRecus() {
        if (appelsRecus.isEmpty()) {
            System.out.println("Aucun appel recu.");
        } else {
            System.out.println("Appels recus par " + nom + " " + prenom + " (" + msisdn + ") :");
            for (int i = 0; i < appelsRecus.size(); i++) {
                System.out.println("  - Appel de : " + appelsRecus.get(i));
            }
        }
    }
    
    public void afficher() {
        System.out.println("Utilisateur : " + nom + " " + prenom);
        System.out.println("MSISDN : " + msisdn + " | IMSI : " + imsi);
        System.out.println("Type : " + typeAppareil + " | Attache : " + (attache ? "Oui" : "Non"));
    }
}