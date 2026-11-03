/* Sef.java
 * Napravljeno po uzoru na Sef.java iz PFM2 (prof. dr Sinisa Vlajic, FON, SILAB)
 * Domen: Auto servis - nalog za servis
 */

package Client;

import Creator.*;

public class Sef { // Client
        Projektant proj; // Creator

Sef(Projektant proj1) {proj = proj1;}

public static void main(String args[]) {
Sef sef;
// ConcreteCreator
Projektant proj = new Projektant2(); // Promenljivo!!!
sef = new Sef(proj);
sef.Kreiraj();
}

void Kreiraj() // Sef nema nikakve nadleznosti u procesu izrade softverskog sistema
{ proj.Kreiraj();
}

}
