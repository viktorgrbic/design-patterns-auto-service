/* Panel.java
 * @autor  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */


package AbstractProductA;



import java.awt.event.KeyEvent;
import java.util.Date;

// Promenljivo!!!
public abstract class Panel extends javax.swing.JPanel{
       
      public abstract String getSifraNaloga(); // Promenljivo!!!
    public abstract javax.swing.JTextField getSifraNaloga1(); // Promenljivo!!!
    public abstract javax.swing.JFormattedTextField getRegistarskiBroj1(); // Promenljivo!!!
    public abstract String getRegistarskiBroj(); // Promenljivo!!!
    public abstract String getSifraMehanicara(); // Promenljivo!!!
    public abstract String getProcenjenoTrajanje(); // Promenljivo!!!
    public abstract Date getDatumPrijema(); // Promenljivo!!!

    public abstract void setSifraNaloga(String SifraNaloga); // Promenljivo!!!
    public abstract void setRegistarskiBroj(String RegistarskiBroj); // Promenljivo!!!
    public abstract void setSifraMehanicara(String SifraMehanicara); // Promenljivo!!!
    public abstract void setProcenjenoTrajanje(String ProcenjenoTrajanje); // Promenljivo!!!
    public abstract void setDatumPrijema(Date DatumPrijema); // Promenljivo!!!

    public abstract void setPoruka(String Poruka);

    public abstract javax.swing.JButton getKreiraj();
    public abstract javax.swing.JButton getPromeni();
    public abstract javax.swing.JButton getObrisi();
    public abstract javax.swing.JButton getNadji();
       
       
       
}
