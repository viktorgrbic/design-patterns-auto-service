/* EkranskaForma.java
 * @autor  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */

package AbstractProductA;

import javax.swing.JFrame;


public abstract class EkranskaForma extends JFrame{
    //Panel pan;
    
    public Panel getPanel() {return (Panel)getContentPane();}  
    public void setPanel(Panel pan1) {setContentPane(pan1); pack(); setLocationRelativeTo(null); // prozor se prilagodjava velicini panela
        
    }
    public void prikaziEkranskuFormu(){setVisible(true);}
    abstract public void odrediDekoracijuForme();
}
