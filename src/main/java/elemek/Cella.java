/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elemek;

import funkciok.Ellenor;


/**
 * A Cella osztály a {@link Tabla} osztály egy celláját reprezentálja a programban.
 * @author balogh0827
 */
public class Cella {
    
    /**
     * A cella állapotát írja le az {@link Allapot} felsorolásos típus segítségével.
     */
    private Allapot állapot;
    
    /**
     * A cellában tárolt érték.
     * <p>
     * Üres cella esetén üres String, egyébként a számérték String reprezentációja.<br/>
     * Szám esetén a megengedett értékek: { "1" | "2" | "3" | "4" | "5" | "6" | "7" | "8" | "9" }
     * </p>
     */
    private String tartalom = "";
    
    /**
     * A cella rekeszben elfoglalt sorszáma.
     */
    private final int sorszám;
    
    /**
     * A cella rekeszbeli oszlopszáma.
     */
    private final int oszlopszám;
    
    /**
     * A létrehoz egy cellát üres tartalommal és <code>{@link Allapot}.ÜRES</code> állapottal.
     * @param sor   int, a rekeszbeli sorszám
     * @param oszlop    int, a rekeszbeli oszlopszám
     */
    public Cella(int sor, int oszlop) {
        tartalom = "";
        állapot = Allapot.ÜRES;
        sorszám = sor;
        oszlopszám = oszlop;
    }
    
    /**
     * Visszaadja a cella {@link Tabla}-beli sorszámát.
     * @return int, a {@code sorszám} változó értéke
     */
    public int getSorszám(){
        return sorszám;
    }
    
    /**
     * Visszaadja a cella {@link Tabla}-beli oszlopszámát.
     * @return int, az {@code oszlopszám} változó értéke
     */
    public int getOszlopszám(){
        return oszlopszám;
    }
    
    
    /**
     * Visszaadja az aktuális cella állapotát.
     * @return {@link Allapot}, az {@code állapot} változó értéke
     */
    public Allapot getÁllapot(){
        return állapot;
    }
    
    
    /**
     * Visszaadja a cella tartalmát.
     * @return String, a {@code tartalom} változó értéke
     */
    public String getTartalom(){
        return tartalom;
    }
    
    
    /**
     * Visszaadja az aktuális cellát.
     * @return {@code Cella}, az aktuális cella a táblában
     */
    public Cella getAktuálisMező(){
        return this;
    }
    
    /**
     * Beállítja  a {@code Cella} tartalmát az adott értékre, ha az érvényes.
     * @param szám a beírandó érték
     */
    public void setTartalom(String szám){
        if(szám.equals("")){
            tartalom = szám;
            setÁllapot(Allapot.ÜRES);
        }else{
            if(Ellenor.tesztIntervallum(Integer.parseInt(szám))){
                tartalom = szám;
            }
        }
    }
    
    /**
     * Lehetővé teszi a nem <code>{@link Allapot}.MEGADOTT</code> típusú {@code Cella} állapotának módosítását. 
     * @param állapot az újonnan beállítandó állapot
     */
    public void setÁllapot(Allapot állapot){
            if(Ellenor.isMódosíthatóÁllapot(this.állapot, állapot)){
                this.állapot = állapot;
            }
    }
    
    /**
     * Leírást ad a Cella {@code tartalom} változójáról.
     * @return  String, a cella tartalma
     */
    @Override
    public String toString(){
        return tartalom;
    }
    
}
