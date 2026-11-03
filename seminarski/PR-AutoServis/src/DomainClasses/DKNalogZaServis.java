/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainClasses;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author vikto
 */
public class DKNalogZaServis  implements  Serializable,GeneralDObject{

    private int SifraNaloga;
    private String RegistarskiBroj;
    private int SifraMehanicara;
    private int ProcenjenoTrajanje;
    private Date DatumPrijema;

    public DKNalogZaServis() {
         SifraNaloga = 0;
        RegistarskiBroj = "BG-000-AA";
        SifraMehanicara = 0;
        ProcenjenoTrajanje = 1;
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        Date dDatum = new Date();
        DatumPrijema = java.sql.Date.valueOf(sm.format(dDatum));   // danasnji datum
    }

    public DKNalogZaServis(int SifraNaloga, String RegistarskiBroj, int SifraMehanicara, int ProcenjenoTrajanje, Date DatumPrijema) {
        this.SifraNaloga = SifraNaloga;
        this.RegistarskiBroj = RegistarskiBroj;
        this.SifraMehanicara = SifraMehanicara;
        this.ProcenjenoTrajanje = ProcenjenoTrajanje;
        this.DatumPrijema = DatumPrijema;
    }
    
    

    // 3. Konstruktor sa primarnim kljucem – za pretragu i brisanje
public DKNalogZaServis(int SifraNaloga) { 
    this.SifraNaloga = SifraNaloga;
}


public int getSifraNaloga() {return SifraNaloga;}
public String getRegistarskiBroj() {return RegistarskiBroj;}
public int getSifraMehanicara() {return SifraMehanicara;}
public int getProcenjenoTrajanje() {return ProcenjenoTrajanje;}
public Date getDatumPrijema() {return DatumPrijema;}

public void setSifraNaloga(int SifraNaloga) {this.SifraNaloga = SifraNaloga;}
public void setRegistarskiBroj(String RegistarskiBroj) {this.RegistarskiBroj = RegistarskiBroj;}
public void setSifraMehanicara(int SifraMehanicara) {this.SifraMehanicara = SifraMehanicara;}
public void setProcenjenoTrajanje(int ProcenjenoTrajanje) {this.ProcenjenoTrajanje = ProcenjenoTrajanje;}
public void setDatumPrijema(Date DatumPrijema) {this.DatumPrijema = DatumPrijema;}



    
    
 
    
    
    
    @Override
    public String getAtrValue() {
        return SifraNaloga + ", " +
               (RegistarskiBroj == null ? null : "'" + RegistarskiBroj + "'") + ", " +
               SifraMehanicara + ", " +
               ProcenjenoTrajanje + ", " +
               "'" + DatumPrijema + "'";
    }

    @Override
    public String setAtrValue() {
        return "SifraNaloga=" + SifraNaloga + ", " +
               "RegistarskiBroj=" + (RegistarskiBroj == null ? null : "'" + RegistarskiBroj + "'") + ", " +
               "SifraMehanicara=" + SifraMehanicara + ", " +
               "ProcenjenoTrajanje=" + ProcenjenoTrajanje + ", " +
               "DatumPrijema=" + "'" + DatumPrijema + "'";
    }


     @Override
    public String getClassName() {
        return "DKNalogZaServis";
    }

    @Override
    public String getWhereCondition() {
        return "SifraNaloga = " + SifraNaloga;
    }

    @Override
    public String getNameByColumn(int column) {
        String names[] = {"SifraNaloga", "RegistarskiBroj", "SifraMehanicara", "ProcenjenoTrajanje", "DatumPrijema"};
        return names[column];
    }

   @Override
    public GeneralDObject getNewRecord(ResultSet rs) throws SQLException {
        return new DKNalogZaServis(
                rs.getInt("SifraNaloga"),
                rs.getString("RegistarskiBroj"),
                rs.getInt("SifraMehanicara"),
                rs.getInt("ProcenjenoTrajanje"),
                rs.getDate("DatumPrijema"));
    }
}
