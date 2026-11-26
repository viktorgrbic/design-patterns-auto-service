/* Kontroler2.java
 * @autor  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
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
import DomainClasses.DKNalogZaServis;


public final class Kontroler2 extends Kontroler{
    
    
      
    public Kontroler2(EkranskaForma ef1,BrokerBazePodataka bbp1){ef=ef1;bbp=bbp1; Povezi(); osveziFormu();}
    
    void Povezi()
    {javax.swing.JButton Kreiraj = ef.getPanel().getKreiraj();
     javax.swing.JButton Promeni = ef.getPanel().getPromeni();
     javax.swing.JButton Obrisi = ef.getPanel().getObrisi();
     javax.swing.JButton Nadji = ef.getPanel().getNadji();
     Kreiraj.addActionListener( new OsluskivacKreiraj1(this));
     Promeni.addActionListener( new OsluskivacPromeni1(this));
     Obrisi.addActionListener( new OsluskivacObrisi1(this));
     Nadji.addActionListener( new OsluskivacNadji11(this));
     
     javax.swing.JTextField SifraPrijave = ef.getPanel().getSifraNaloga1(); // Promenljivo!!!
     SifraPrijave.addFocusListener( new OsluskivacNadji12(this));
    }
    
// Promenljivo!!!    
void napuniDomenskiObjekatIzGrafickogObjekta()   {
       ip = new DKNalogZaServis();
       ip.setSifraNaloga(getInteger(ef.getPanel().getSifraNaloga()));
       ip.setRegistarskiBroj(ef.getPanel().getRegistarskiBroj());
       ip.setSifraMehanicara(getInteger(ef.getPanel().getSifraMehanicara()));
       ip.setProcenjenoTrajanje(getInteger(ef.getPanel().getProcenjenoTrajanje()));
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       java.util.Date DatumPrijema = java.sql.Date.valueOf(sdf.format(ef.getPanel().getDatumPrijema()));
       ip.setDatumPrijema(DatumPrijema);

    
    }

// Promenljivo!!!
void napuniGrafickiObjekatIzDomenskogObjekta(DKNalogZaServis ip){
     
       ef.getPanel().setSifraNaloga(Integer.toString(ip.getSifraNaloga()));
       ef.getPanel().setRegistarskiBroj(ip.getRegistarskiBroj());
       ef.getPanel().setSifraMehanicara(Integer.toString(ip.getSifraMehanicara()));
       ef.getPanel().setProcenjenoTrajanje(Integer.toString(ip.getProcenjenoTrajanje()));
       ef.getPanel().setDatumPrijema(ip.getDatumPrijema());
      
    }

// Promenljivo!!!
void isprazniGrafickiObjekat(){

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


void prikaziPoruku(String poruka) 
{ ef.getPanel().setPoruka(poruka);
      
  Timer timer = new Timer();
  
  timer.schedule(new TimerTask() {
  @Override
  public void run() {
    ef.getPanel().setPoruka(""); 
  }
}, 1000);
  
}

void osveziFormu() 
{    
  Timer timer = new Timer();
  
  timer.scheduleAtFixedRate(new TimerTask() {
  @Override
  public void run() {
      napuniDomenskiObjekatIzGrafickogObjekta();
      nadjiDomenskiObjekat();
      prikaziPoruku("Osvežavanje forme!!!");
  }
}, 0,10000);
  
}

public int getInteger(String s) {
    int broj = 0;
    try
        {
            if(s != null)
                broj = Integer.parseInt(s);
        }
            catch (NumberFormatException e)
            { broj = 0;}
   
    return broj;
}


 
boolean zapamtiDomenskiObjekat(){ 
    
    bbp.makeConnection();
    boolean signal = bbp.insertRecord(ip);
    if (signal==true) 
        { bbp.commitTransation();
          poruka ="Sistem je zapamtio novi nalog za servis."; // Promenljivo!!!
        }
        else
        { bbp.rollbackTransation();
          poruka ="Sistem ne može da zapamti novi nalog za servis."; // Promenljivo!!!  
        }
    prikaziPoruku();
    bbp.closeConnection();
    return signal; 
       
    }
    
boolean kreirajDomenskiObjekat(){
    boolean signal;
    ip= new DKNalogZaServis(); // Promenljivo!!!
    AtomicInteger counter = new AtomicInteger(0);
    
    bbp.makeConnection();
    if (!bbp.getCounter(ip,counter)) return false;
    if (!bbp.increaseCounter(ip,counter)) return false;
          
    ip.setSifraNaloga(counter.get()); // Promenljivo!!!
    signal = bbp.insertRecord(ip);
    if (signal==true) 
        { bbp.commitTransation();
          napuniGrafickiObjekatIzDomenskogObjekta(ip);
          poruka = "Sistem je kreirao novi nalog za servis."; // Promenljivo!!!
        }
        else
        { bbp.rollbackTransation();
        isprazniGrafickiObjekat();
        poruka ="Sistem ne može da kreira novi nalog za servis."; // Promenljivo!!!
        }
    prikaziPoruku();
    bbp.closeConnection();
    return signal;
}

boolean obrisiDomenskiObjekat(){
    bbp.makeConnection();
    boolean signal = bbp.deleteRecord(ip);
    if (signal==true) 
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

boolean promeniDomenskiObjekat(){
    bbp.makeConnection();
    boolean signal = bbp.updateRecord(ip);
    if (signal==true) 
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


boolean nadjiDomenskiObjekat(){
    boolean signal;
    bbp.makeConnection();
    ip = (DKNalogZaServis)bbp.findRecord(ip); // Promenljivo!!!
    if (ip != null) 
        { napuniGrafickiObjekatIzDomenskogObjekta(ip);
          poruka = "Sistem je našao nalog za servis.";  // Promenljivo!!!
          signal = true;
        }
        else
        { isprazniGrafickiObjekat();
          poruka ="Sistem ne može da nađe nalog za servis."; // Promenljivo!!!
          signal = false;
        }
    prikaziPoruku();
    bbp.closeConnection();
    return signal;   
}

}

class OsluskivacZapamti1 implements ActionListener
{   Kontroler2 kon;
 
    OsluskivacZapamti1(Kontroler2 kon1) {kon = kon1;}
    
@Override
    public void actionPerformed(ActionEvent e) {
         kon.napuniDomenskiObjekatIzGrafickogObjekta();
         kon.zapamtiDomenskiObjekat();
        
    }
}

class OsluskivacKreiraj1 implements ActionListener
{   Kontroler2 kon;
 
    OsluskivacKreiraj1(Kontroler2 kon1) {kon = kon1;}
    
@Override
    public void actionPerformed(ActionEvent e) {
         kon.kreirajDomenskiObjekat();
         
        
    }
}

class OsluskivacObrisi1 implements ActionListener
{   Kontroler2 kon;
 
    OsluskivacObrisi1(Kontroler2 kon1) {kon = kon1;}
    
@Override
    public void actionPerformed(ActionEvent e) {
         kon.napuniDomenskiObjekatIzGrafickogObjekta();
         kon.obrisiDomenskiObjekat();
        
    }
}

class OsluskivacPromeni1 implements ActionListener
{   Kontroler2 kon;
 
    OsluskivacPromeni1(Kontroler2 kon1) {kon = kon1;}
    
@Override
    public void actionPerformed(ActionEvent e) {
         kon.napuniDomenskiObjekatIzGrafickogObjekta();
         kon.promeniDomenskiObjekat();
        
    }
}

class OsluskivacNadji11 implements ActionListener
{   Kontroler2 kon;
 
    OsluskivacNadji11(Kontroler2 kon1) {kon = kon1;}
    
@Override
    public void actionPerformed(ActionEvent e) {
         kon.napuniDomenskiObjekatIzGrafickogObjekta();
         kon.nadjiDomenskiObjekat();
        
    }
}

class OsluskivacNadji12 implements FocusListener
{   Kontroler2 kon;
 
    OsluskivacNadji12(Kontroler2 kon1) {kon = kon1;}
    

    public void focusLost(java.awt.event.FocusEvent e) {
         kon.napuniDomenskiObjekatIzGrafickogObjekta();
         kon.nadjiDomenskiObjekat();
        
    }

    @Override
    public void focusGained(FocusEvent e) {
       
    }
}

