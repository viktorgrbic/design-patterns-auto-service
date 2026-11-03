/* Kontroler.java
 * Napravljeno po uzoru na Kontroler.java (prof. dr Sinisa Vlajic, FON, SILAB)
 * Domen: Auto servis - nalog za servis
 */

package AbstractProductC;

import AbstractProductA.EkranskaForma;
import AbstractProductB.BrokerBazePodataka;
import DomainClasses.DKNalogZaServis;

public abstract class Kontroler {
    EkranskaForma ef;
    BrokerBazePodataka bbp;
    DKNalogZaServis ns;   // Promenljivo!!!
    String poruka;
}
