/* SoftverskiSistem1.java
 * @autor  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */

package Product;

import AbstractProductA.EkranskaForma;
import AbstractProductB.BrokerBazePodataka;
import AbstractProductC.Kontroler;


public class SoftverskiSistem1 implements SoftverskiSistem// Concrete Product
           {  EkranskaForma ef; // AbstractProductA 
              BrokerBazePodataka bbp; // AbstractProductB 
              Kontroler kon; // AbstractProductC 
              public SoftverskiSistem1(EkranskaForma ef1,BrokerBazePodataka bbp1,Kontroler kon1){ef=ef1;bbp=bbp1;kon=kon1;}
              @Override
              public void prikaziEkranskuFormu(){ef.prikaziEkranskuFormu();}
           }
