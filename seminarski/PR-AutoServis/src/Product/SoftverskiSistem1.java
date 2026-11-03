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


public class SoftverskiSistem1 implements SoftverskiSistem// ConcretePrototype
           {  EkranskaForma ef; // AbstractProductA 
              BrokerBazePodataka bbp; // AbstractProductB 
              Kontroler kon; // AbstractProductC 
              public SoftverskiSistem1(EkranskaForma ef1,BrokerBazePodataka bbp1,Kontroler kon1){ef=ef1;bbp=bbp1;kon=kon1;}
              public SoftverskiSistem1(SoftverskiSistem1 ss1){ef=ss1.ef;bbp=ss1.bbp;kon=ss1.kon;}
              @Override
              public void prikaziEkranskuFormu(){ef.prikaziEkranskuFormu();}
              @Override
              public void zatvoriEkranskuFormu(){ef.zatvoriEkranskuFormu();}
              @Override
              public SoftverskiSistem Clone() { return new SoftverskiSistem1(this);}
           }
