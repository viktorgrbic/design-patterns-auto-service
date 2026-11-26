
package Receiver;


import Client.Kontroler;
import DomainClasses.DKNalogZaServis;
import java.util.concurrent.atomic.AtomicInteger;

 public class KreirajDomenskiObjekat { // Receiver
    
   Kontroler kon;
     
    public KreirajDomenskiObjekat(Kontroler kon1){kon=kon1;} 
     
    public boolean kreirajDomenskiObjekat(){
    boolean signal;
    DKNalogZaServis ip= new DKNalogZaServis(); // Promenljivo!!!
    AtomicInteger counter = new AtomicInteger(0);
    
    kon.getBrokerBazePodataka().makeConnection();
    if (!kon.getBrokerBazePodataka().getCounter(ip,counter)) return false;
    if (!kon.getBrokerBazePodataka().increaseCounter(ip,counter)) return false;
          
    ip.setSifraNaloga(counter.get()); // Promenljivo!!!
    signal = kon.getBrokerBazePodataka().insertRecord(ip);
    if (signal==true) 
        { kon.getBrokerBazePodataka().commitTransation();
          kon.napuniGrafickiObjekatIzDomenskogObjekta(ip);
          kon.setPoruka("Sistem je kreirao novi nalog za servis."); // Promenljivo!!!
        }
        else
        { kon.getBrokerBazePodataka().rollbackTransation();
         kon.isprazniGrafickiObjekat();
         kon.setPoruka("Sistem ne može da kreira novi nalog za servis."); // Promenljivo!!!
        }
    kon.prikaziPoruku();
    kon.getBrokerBazePodataka().closeConnection();
    return signal;
}
    
}
