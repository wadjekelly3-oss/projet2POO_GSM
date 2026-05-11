package gsm.model;

public class Smartphone extends MS {
    private String versionOS;
    private double tailleEcran;
    
    public Smartphone(String nom, String prenom, String motDePasse, String msisdn, String imsi,
                      String versionOS, double tailleEcran) {
        super(nom, prenom, motDePasse, msisdn, imsi, "Smartphone");
        if (versionOS == null || versionOS.isEmpty()) {
            this.versionOS = "Android";
        } else {
            this.versionOS = versionOS;
        }
        if (tailleEcran <= 0) {
            this.tailleEcran = 6.1;
        } else {
            this.tailleEcran = tailleEcran;
        }
    }
    
    public String getVersionOS() { return versionOS; }
    public double getTailleEcran() { return tailleEcran; }
    
    @Override
    public void afficher() {
        super.afficher();
        System.out.println("OS : " + versionOS + " | Ecran : " + tailleEcran + " pouces");
    }
}