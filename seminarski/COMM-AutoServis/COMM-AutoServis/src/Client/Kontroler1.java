/* Kontroler1.java
 * @autor  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */

package Client;


import AbstractProductA.*;
import AbstractProductB.*;
import ConcreteCommand.SO;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;
import DomainClasses.DKNalogZaServis;  // Promenljivo
import DomainClasses.GeneralDObject;
import ConcreteCommand.SOKreiraj;
import ConcreteCommand.SONadji;
import ConcreteCommand.SOObrisi;
import ConcreteCommand.SOPromeni;


import Receiver.KreirajDomenskiObjekat;
import Receiver.NadjiDomenskiObjekat;
import Receiver.ObrisiDomenskiObjekat;
import Receiver.PromeniDomenskiObjekat;


public final class Kontroler1 extends Kontroler{ // Client
   
    
   
    
    public Kontroler1(EkranskaForma ef1,BrokerBazePodataka bbp1,SO so)
    {ef=ef1;bbp=bbp1; Povezi(so);}
    
    void Povezi(SO so)
    {
      
      KreirajDomenskiObjekat kdo = new KreirajDomenskiObjekat(this);
      so.sok = new SOKreiraj(kdo); // povezivanje ConcreteCommand i Receiver
           
      PromeniDomenskiObjekat pdo = new PromeniDomenskiObjekat(this);
      so.sop = new SOPromeni(pdo); 
            
      ObrisiDomenskiObjekat odo = new ObrisiDomenskiObjekat(this);
      so.soo = new SOObrisi(odo); 
            
      
      NadjiDomenskiObjekat ndo = new NadjiDomenskiObjekat(this);
      so.son = new SONadji(ndo); 
          
     
    }
    
// Promenljivo!!!  
    @Override
    public void napuniDomenskiObjekatIzGrafickogObjekta()   {
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
    @Override
    public void napuniGrafickiObjekatIzDomenskogObjekta(GeneralDObject gdo){
       DKNalogZaServis ip = (DKNalogZaServis)gdo;
       ef.getPanel().setSifraNaloga(Integer.toString(ip.getSifraNaloga()));
       ef.getPanel().setRegistarskiBroj(ip.getRegistarskiBroj());
       ef.getPanel().setSifraMehanicara(Integer.toString(ip.getSifraMehanicara()));
       ef.getPanel().setProcenjenoTrajanje(Integer.toString(ip.getProcenjenoTrajanje()));
       ef.getPanel().setDatumPrijema(ip.getDatumPrijema());
      
    }

   
// Promenljivo!!!
    @Override
    public void isprazniGrafickiObjekat(){

 ef.getPanel().setSifraNaloga("");
 ef.getPanel().setRegistarskiBroj("");
 ef.getPanel().setSifraMehanicara("0");
 ef.getPanel().setProcenjenoTrajanje("1");
 ef.getPanel().setDatumPrijema(new java.util.Date());
}

    @Override 
    public void prikaziPoruku(){ 
  ef.getPanel().setPoruka(poruka);
      
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
            if(s != null)
                broj = Integer.parseInt(s);
        }
            catch (NumberFormatException e)
            { broj = 0;}
   
    return broj;
}


}    







