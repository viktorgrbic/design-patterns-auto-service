/* Projektant2.java
 * @autor  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */

package AbstractFactory;

import AbstractProductB.BrokerBazePodataka1;
import AbstractProductB.BrokerBazePodataka;
import AbstractProductA.*;
import AbstractProductB.*;
import AbstractProductC.Kontroler;
import AbstractProductC.Kontroler1;

// Promenljivo!!!
public class Projektant2 implements Projektant {
        
    @Override
    public EkranskaForma kreirajEkranskuFormu() {
        Panel pan = new Panel2(); // Promenljivo!!!
        EkranskaForma1 ip = new EkranskaForma1(); // Promenljivo!!!
        ip.setPanel(pan);
        return ip;
    }   
    
    @Override
    public BrokerBazePodataka kreirajBrokerBazePodataka () 
      { BrokerBazePodataka dbbr = new BrokerBazePodataka1(); // Promenljivo!!!
        return dbbr;}  
    
    @Override
    public Kontroler kreirajKontroler (EkranskaForma ef,BrokerBazePodataka dbbr) {
        Kontroler kon = new Kontroler1(ef,dbbr); // Promenljivo!!!
        return kon;
    }
        
   
}

