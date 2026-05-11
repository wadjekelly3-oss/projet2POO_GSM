package gsm.model;

public class Tablette extends MS {
    private boolean supportStylet;
    private boolean supportClavier;
    
    public Tablette(String nom, String prenom, String motDePasse, String msisdn, String imsi,
                    boolean supportStylet, boolean supportClavier) {
        super(nom, prenom, motDePasse, msisdn, imsi, "Tablette");
        this.supportStylet = supportStylet;
        this.supportClavier = supportClavier;
    }
    
    public boolean isSupportStylet() { return supportStylet; }
    public boolean isSupportClavier() { return supportClavier; }
    
    @Override
    public void afficher() {
        super.afficher();
        System.out.println("Stylet : " + (supportStylet ? "Oui" : "Non") + " | Clavier : " + (supportClavier ? "Oui" : "Non"));
    }
}