package gsm.service;

import gsm.exception.AuthenticationFailedException;
import gsm.exception.MSNotFoundException;
import gsm.model.BTS;
import gsm.model.MS;
import gsm.model.ReseauGSM;

public class ServiceAppel {
    
    public void appeler(ReseauGSM reseau, String msisdnAppelant, String mdpAppelant,
                        String msisdnAppele) throws MSNotFoundException, AuthenticationFailedException {
        
        BTS btsAppelant = reseau.localiserMS(msisdnAppelant);
        MS appelant = btsAppelant.rechercherMS(msisdnAppelant);
        
        if (!appelant.verifierMotDePasse(mdpAppelant)) {
            throw new AuthenticationFailedException("Authentification echouee pour " + msisdnAppelant);
        }
        
        BTS btsAppele = reseau.localiserMS(msisdnAppele);
        MS appele = btsAppele.rechercherMS(msisdnAppele);
        
        appele.recevoirAppel(appelant.getNom() + " " + appelant.getPrenom() + " (" + msisdnAppelant + ")");
        
        System.out.println("Appel de " + msisdnAppelant + " vers " + msisdnAppele + " effectue avec succes !");
        System.out.println("BTS utilisee (appelant) : BTS N°" + btsAppelant.getNumero());
        System.out.println("BTS utilisee (appele) : BTS N°" + btsAppele.getNumero());
    }
}