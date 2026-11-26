
package Receiver;

import AbstractProductB.BrokerBazePodataka;
import Client.Kontroler;


public class ObrisiDomenskiObjekat {
    Kontroler kon;
    
    
    public ObrisiDomenskiObjekat(Kontroler kon1){kon=kon1;} 
    
    public boolean obrisiDomenskiObjekat(){
     kon.napuniDomenskiObjekatIzGrafickogObjekta();
     kon.getBrokerBazePodataka().makeConnection();
     boolean signal = kon.getBrokerBazePodataka().deleteRecord(kon.getDomenskiObjekat());
     if (signal==true) 
        { kon.getBrokerBazePodataka().commitTransation();
          kon.setPoruka("Sistem je obrisao nalog za servis."); // Promenljivo!!!
          kon.isprazniGrafickiObjekat();
        }
     else
        { kon.getBrokerBazePodataka().rollbackTransation();
          kon.setPoruka("Sistem ne može da obriše nalog za servis."); // Promenljivo!!!
        }
     kon.prikaziPoruku();
    kon.getBrokerBazePodataka().closeConnection();
    return signal;   
  }   
}
