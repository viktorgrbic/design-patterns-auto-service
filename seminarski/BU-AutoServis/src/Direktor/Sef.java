/* Sef.java
 * Napravljeno po uzoru na Sef.java iz PBL2 (prof. dr Sinisa Vlajic, FON, SILAB)
 * Domen: Auto servis - nalog za servis
 */

package Direktor;

import Builder.*;

public class Sef { // Director
        Projektant proj; // Builder

Sef(Projektant proj1) {proj = proj1;}

public static void main(String args[]) {
Sef sef;
// ConcreteBuilder
Projektant proj = new Projektant2(); // promenljivo!!!
sef = new Sef(proj);
sef.Konstruisi();
}

void Konstruisi() {
      proj.kreirajSoftverskiSistem();
      proj.kreirajEkranskuFormu();
      proj.kreirajBrokerBazePodataka();
      proj.kreirajKontroler();
      proj.prikaziEkranskuFormu();
}

}
