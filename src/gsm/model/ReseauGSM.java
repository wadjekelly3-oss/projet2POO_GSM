package gsm.model;

import gsm.exception.BTSNotFoundException;
import gsm.exception.MSNotFoundException;
import java.util.ArrayList;

public class ReseauGSM {
    private String nom;
    private double bandeUplink;
    private double bandeDownlink;
    private String typeAccesMultiple;
    private double debitMaxUplink;
    private double debitMaxDownlink;
    private double maxDelai;
    private ArrayList<BTS> btsList;
    
    public ReseauGSM(String nom, double bandeUplink, double bandeDownlink, String typeAccesMultiple,
                     double debitMaxUplink, double debitMaxDownlink, double maxDelai) {
        if (nom == null || nom.isEmpty()) {
            this.nom = "Reseau GSM";
        } else {
            this.nom = nom;
        }
        this.bandeUplink = bandeUplink;
        this.bandeDownlink = bandeDownlink;
        if (typeAccesMultiple == null || typeAccesMultiple.isEmpty()) {
            this.typeAccesMultiple = "FDMA/TDMA";
        } else {
            this.typeAccesMultiple = typeAccesMultiple;
        }
        this.debitMaxUplink = debitMaxUplink;
        this.debitMaxDownlink = debitMaxDownlink;
        this.maxDelai = maxDelai;
        this.btsList = new ArrayList<>();
    }
    
    public void ajouterBTS(BTS bts) {
        btsList.add(bts);
    }
    
    public boolean supprimerBTS(int numero) {
        for (int i = 0; i < btsList.size(); i++) {
            if (btsList.get(i).getNumero() == numero) {
                btsList.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public BTS rechercherBTS(int numero) throws BTSNotFoundException {
        for (int i = 0; i < btsList.size(); i++) {
            if (btsList.get(i).getNumero() == numero) {
                return btsList.get(i);
            }
        }
        throw new BTSNotFoundException("BTS N°" + numero + " introuvable.");
    }
    
    public int getNombreBTS() {
        return btsList.size();
    }
    
    public int getNombreBTSSaturees() {
        int count = 0;
        for (int i = 0; i < btsList.size(); i++) {
            if (btsList.get(i).estSature()) count++;
        }
        return count;
    }
    
    public int getNombreBTSDisponibles() {
        return getNombreBTS() - getNombreBTSSaturees();
    }
    
    public int getNombreAbonnesTotal() {
        int total = 0;
        for (int i = 0; i < btsList.size(); i++) {
            total += btsList.get(i).getNombreUtilisateurs();
        }
        return total;
    }
    
    public BTS localiserMS(String msisdn) throws MSNotFoundException {
        for (int i = 0; i < btsList.size(); i++) {
            try {
                btsList.get(i).rechercherMS(msisdn);
                return btsList.get(i);
            } catch (MSNotFoundException e) {
                // continuer
            }
        }
        throw new MSNotFoundException("MS " + msisdn + " non localise dans le reseau.");
    }
    
    public void afficherPerformances() {
        System.out.println("\n===== PERFORMANCES RESEAU " + nom + " =====");
        System.out.println("Nombre total de BTS : " + getNombreBTS());
        System.out.println("BTS saturees : " + getNombreBTSSaturees());
        System.out.println("BTS disponibles : " + getNombreBTSDisponibles());
        System.out.println("Nombre total d'abonnes : " + getNombreAbonnesTotal());
        System.out.println("Bande Uplink : " + bandeUplink + " MHz");
        System.out.println("Bande Downlink : " + bandeDownlink + " MHz");
        System.out.println("Acces multiple : " + typeAccesMultiple);
        System.out.println("Debit max Uplink : " + debitMaxUplink + " Mbps");
        System.out.println("Debit max Downlink : " + debitMaxDownlink + " Mbps");
        System.out.println("Delai max : " + maxDelai + " ms");
    }
    
    public ArrayList<BTS> getBtsList() { return btsList; }
    public String getNom() { return nom; }
}