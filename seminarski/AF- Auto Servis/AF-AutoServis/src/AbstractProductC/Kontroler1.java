/* Kontroler1.java
 * Napravljeno po uzoru na Kontroler1.java (prof. dr Sinisa Vlajic, FON, SILAB)
 * Domen: Auto servis - nalog za servis
 */

package AbstractProductC;

import AbstractProductA.*;
import AbstractProductB.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Timer;
import java.util.TimerTask;
import DomainClasses.DKNalogZaServis;  // Promenljivo

public final class Kontroler1 extends Kontroler {

    public Kontroler1(EkranskaForma ef1, BrokerBazePodataka bbp1) {ef = ef1; bbp = bbp1; Povezi();}

    void Povezi()
    {javax.swing.JButton Kreiraj = ef.getPanel().getKreiraj();
     javax.swing.JButton Promeni = ef.getPanel().getPromeni();
     javax.swing.JButton Obrisi = ef.getPanel().getObrisi();
     javax.swing.JButton Nadji = ef.getPanel().getNadji();
     Kreiraj.addActionListener(new OsluskivacKreiraj(this));
     Promeni.addActionListener(new OsluskivacPromeni(this));
     Obrisi.addActionListener(new OsluskivacObrisi(this));
     Nadji.addActionListener(new OsluskivacNadji(this));

     javax.swing.JTextField SifraNaloga = ef.getPanel().getSifraNaloga1(); // Promenljivo!!!
     SifraNaloga.addFocusListener(new OsluskivacNadji1(this));
    }

// Promenljivo!!!
void napuniDomenskiObjekatIzGrafickogObjekta() {
       ns = new DKNalogZaServis();
       ns.setSifraNaloga(getInteger(ef.getPanel().getSifraNaloga()));
       ns.setRegistarskiBroj(ef.getPanel().getRegistarskiBroj());
       ns.setSifraMehanicara(getInteger(ef.getPanel().getSifraMehanicara()));
       ns.setProcenjenoTrajanje(getInteger(ef.getPanel().getProcenjenoTrajanje()));
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       java.util.Date DatumPrijema = java.sql.Date.valueOf(sdf.format(ef.getPanel().getDatumPrijema()));
       ns.setDatumPrijema(DatumPrijema);
    }

// Promenljivo!!!
void napuniGrafickiObjekatIzDomenskogObjekta(DKNalogZaServis ns) {
       ef.getPanel().setSifraNaloga(Integer.toString(ns.getSifraNaloga()));
       ef.getPanel().setRegistarskiBroj(ns.getRegistarskiBroj());
       ef.getPanel().setSifraMehanicara(Integer.toString(ns.getSifraMehanicara()));
       ef.getPanel().setProcenjenoTrajanje(Integer.toString(ns.getProcenjenoTrajanje()));
       ef.getPanel().setDatumPrijema(ns.getDatumPrijema());
    }

// Promenljivo!!!
void isprazniGrafickiObjekat() {
 ef.getPanel().setSifraNaloga("");
 ef.getPanel().setRegistarskiBroj("");
 ef.getPanel().setSifraMehanicara("0");
 ef.getPanel().setProcenjenoTrajanje("1");
 ef.getPanel().setDatumPrijema(new java.util.Date());
}

void prikaziPoruku()
{ ef.getPanel().setPoruka(poruka);

  Timer timer = new Timer();

  timer.schedule(new TimerTask() {
  @Override
  public void run() {
    ef.getPanel().setPoruka("");
  }
}, 3000);

}

public int getInteger(String s) {
    int broj = 0;
    try
        {
            if (s != null)
                broj = Integer.parseInt(s);
        }
            catch (NumberFormatException e)
            { broj = 0;}

    return broj;
}

boolean zapamtiDomenskiObjekat() {

    bbp.makeConnection();
    boolean signal = bbp.insertRecord(ns);
    if (signal == true)
        { bbp.commitTransation();
          poruka = "Sistem je zapamtio novi nalog za servis."; // Promenljivo!!!
        }
        else
        { bbp.rollbackTransation();
          poruka = "Sistem ne može da zapamti novi nalog za servis."; // Promenljivo!!!
        }
    prikaziPoruku();
    bbp.closeConnection();
    return signal;

    }

boolean kreirajDomenskiObjekat() {
    boolean signal;
    ns = new DKNalogZaServis(); // Promenljivo!!!
    AtomicInteger counter = new AtomicInteger(0);

    bbp.makeConnection();
    if (!bbp.getCounter(ns, counter)) return false;
    if (!bbp.increaseCounter(ns, counter)) return false;

    ns.setSifraNaloga(counter.get()); // Promenljivo!!!
    signal = bbp.insertRecord(ns);
    if (signal == true)
        { bbp.commitTransation();
          napuniGrafickiObjekatIzDomenskogObjekta(ns);
          poruka = "Sistem je kreirao novi nalog za servis."; // Promenljivo!!!
        }
        else
        { bbp.rollbackTransation();
          isprazniGrafickiObjekat();
          poruka = "Sistem ne može da kreira novi nalog za servis."; // Promenljivo!!!
        }
    prikaziPoruku();
    bbp.closeConnection();
    return signal;
}

boolean obrisiDomenskiObjekat() {
    bbp.makeConnection();
    boolean signal = bbp.deleteRecord(ns);
    if (signal == true)
        { bbp.commitTransation();
          poruka = "Sistem je obrisao nalog za servis."; // Promenljivo!!!
          isprazniGrafickiObjekat();
        }
        else
        { bbp.rollbackTransation();
          poruka = "Sistem ne može da obriše nalog za servis."; // Promenljivo!!!
        }
    prikaziPoruku();
    bbp.closeConnection();
    return signal;
}

boolean promeniDomenskiObjekat() {
    bbp.makeConnection();
    boolean signal = bbp.updateRecord(ns);
    if (signal == true)
        { bbp.commitTransation();
          poruka = "Sistem je promenio nalog za servis."; // Promenljivo!!!
        }
        else
        { bbp.rollbackTransation();
          isprazniGrafickiObjekat();
          poruka = "Sistem ne može da promeni nalog za servis."; // Promenljivo!!!
        }
    prikaziPoruku();
    bbp.closeConnection();
    return signal;
}

boolean nadjiDomenskiObjekat() {
    boolean signal;
    bbp.makeConnection();
    ns = (DKNalogZaServis) bbp.findRecord(ns); // Promenljivo!!!
    if (ns != null)
        { napuniGrafickiObjekatIzDomenskogObjekta(ns);
          poruka = "Sistem je našao nalog za servis."; // Promenljivo!!!
          signal = true;
        }
        else
        { isprazniGrafickiObjekat();
          poruka = "Sistem ne može da nađe nalog za servis."; // Promenljivo!!!
          signal = false;
        }
    prikaziPoruku();
    bbp.closeConnection();
    return signal;
}

}

class OsluskivacZapamti implements ActionListener
{   Kontroler1 kon;

    OsluskivacZapamti(Kontroler1 kon1) {kon = kon1;}

@Override
    public void actionPerformed(ActionEvent e) {
         kon.napuniDomenskiObjekatIzGrafickogObjekta();
         kon.zapamtiDomenskiObjekat();
    }
}

class OsluskivacKreiraj implements ActionListener
{   Kontroler1 kon;

    OsluskivacKreiraj(Kontroler1 kon1) {kon = kon1;}

@Override
    public void actionPerformed(ActionEvent e) {
         kon.kreirajDomenskiObjekat();
    }
}

class OsluskivacObrisi implements ActionListener
{   Kontroler1 kon;

    OsluskivacObrisi(Kontroler1 kon1) {kon = kon1;}

@Override
    public void actionPerformed(ActionEvent e) {
         kon.napuniDomenskiObjekatIzGrafickogObjekta();
         kon.obrisiDomenskiObjekat();
    }
}

class OsluskivacPromeni implements ActionListener
{   Kontroler1 kon;

    OsluskivacPromeni(Kontroler1 kon1) {kon = kon1;}

@Override
    public void actionPerformed(ActionEvent e) {
         kon.napuniDomenskiObjekatIzGrafickogObjekta();
         kon.promeniDomenskiObjekat();
    }
}

class OsluskivacNadji implements ActionListener
{   Kontroler1 kon;

    OsluskivacNadji(Kontroler1 kon1) {kon = kon1;}

@Override
    public void actionPerformed(ActionEvent e) {
         kon.napuniDomenskiObjekatIzGrafickogObjekta();
         kon.nadjiDomenskiObjekat();
    }
}

class OsluskivacNadji1 implements FocusListener
{   Kontroler1 kon;

    OsluskivacNadji1(Kontroler1 kon1) {kon = kon1;}

    @Override
    public void focusLost(java.awt.event.FocusEvent e) {
         kon.napuniDomenskiObjekatIzGrafickogObjekta();
         kon.nadjiDomenskiObjekat();
    }

    @Override
    public void focusGained(FocusEvent e) {
    }
}
