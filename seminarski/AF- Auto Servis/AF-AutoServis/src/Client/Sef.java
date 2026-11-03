/* Sef.java
 * Napravljeno po uzoru na Sef.java (prof. dr Sinisa Vlajic, FON, SILAB)
 * Domen: Auto servis - nalog za servis
 */

package Client;

import AbstractFactory.*;
import AbstractProductA.*;
import AbstractProductB.*;
import AbstractProductC.*;

public class Sef { // Client
        Projektant proj; // Abstract Factory
        class SoftverskiSistem // Complex Product
           {  EkranskaForma ef; // AbstractProductA
              BrokerBazePodataka bbp; // AbstractProductB
              Kontroler kon; // AbstractProductC
              SoftverskiSistem(EkranskaForma ef1, BrokerBazePodataka bbp1, Kontroler kon1) {ef = ef1; bbp = bbp1; kon = kon1;}
              void prikaziEkranskuFormu() {ef.prikaziEkranskuFormu();}
           }
        SoftverskiSistem ss;

Sef(Projektant proj1) {proj = proj1;}


public static void main(String args[]) {
Sef sef;
// ConcreteFactory2
Projektant proj = new Projektant2(); // Promenljivo!!!
sef = new Sef(proj);
sef.Kreiraj();
}

void Kreiraj() {
      EkranskaForma ef = proj.kreirajEkranskuFormu();
      BrokerBazePodataka bbp = proj.kreirajBrokerBazePodataka();
      Kontroler kon = proj.kreirajKontroler(ef, bbp);
      ss = new SoftverskiSistem(ef, bbp, kon);
      ss.prikaziEkranskuFormu();
}

}
