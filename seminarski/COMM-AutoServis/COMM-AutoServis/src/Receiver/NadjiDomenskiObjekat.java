
package Receiver;


import Client.Kontroler;
import DomainClasses.DKNalogZaServis;

 public class NadjiDomenskiObjekat { // Receiver
    
   Kontroler kon;
    
    
    public NadjiDomenskiObjekat(Kontroler kon1){kon=kon1;} 
     
    public boolean nadjiDomenskiObjekat(){
    kon.napuniDomenskiObjekatIzGrafickogObjekta();
    boolean signal;
    kon.getBrokerBazePodataka().makeConnection();
    DKNalogZaServis ip = (DKNalogZaServis)kon.getBrokerBazePodataka().findRecord(kon.getDomenskiObjekat()); // Promenljivo!!!
    if (ip != null) 
        { kon.napuniGrafickiObjekatIzDomenskogObjekta(ip);
          kon.setPoruka("Sistem je našao nalog za servis."); // Promenljivo!!!
          signal = true;
        }
        else
        { kon.isprazniGrafickiObjekat();
          kon.setPoruka("Sistem ne može da nađe nalog za servis."); // Promenljivo!!!
          signal = false;
        }
    kon.prikaziPoruku();
    kon.getBrokerBazePodataka().closeConnection();
    return signal;  
}
    
}
