/* Projektant.java
 * @autor  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */

package Creator;

import AbstractProductA.*;
import AbstractProductB.*;
import AbstractProductC.*;
import Product.*;


public abstract class  Projektant { // Creator
       SoftverskiSistem ss; 
    
       abstract EkranskaForma kreirajEkranskuFormu();   
       abstract BrokerBazePodataka kreirajBrokerBazePodataka ();
       abstract Kontroler kreirajKontroler (EkranskaForma ef,BrokerBazePodataka bbp);
       
       public void Kreiraj()
        { EkranskaForma ef =kreirajEkranskuFormu();
          BrokerBazePodataka bbp = kreirajBrokerBazePodataka();
          Kontroler kon= kreirajKontroler(ef,bbp);
          ss = new SoftverskiSistem1(ef,bbp,kon);
          ss.prikaziEkranskuFormu();
        }
}
