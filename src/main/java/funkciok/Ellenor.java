/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funkciok;

import elemek.Allapot;
import elemek.Cella;
import elemek.Rekesz;
import elemek.Tabla;

/**
 * Statikus metódusokat tartalmazó osztály, mely bizonyos feltételek meglétét ellenőrzi.
 * @author STARLIGHT
 *  
 */
public final class Ellenor {
    
    /**
     * A metódus megvizsgálja, hogy érvényes szám e a paraméter.
     * @param érték     int, a cellába helyezendő érték
     * @return  boolean,
     *          ha a paraméter 1 és 10 közötti szám akkor true, egyébként false
     */
    public static boolean tesztIntervallum(int érték){
        return !(érték <= 0 || érték >= 10);
    }
    
    
    /**
     *  A metódus megvizsgálja, hogy a paraméterként kapott állapot módosítható e.
     * <br/><i>Csak az <code>{@link Allapot}.MEGADOTT</code> cella nem módosítható.</i>
     * @param régi a korábbi állapot, melyet módosítani akarunk
     * @param új az új állapot
     * @return boolean, a módosítás sikeressége
     */
    public static boolean isMódosíthatóÁllapot(Allapot régi, Allapot új){
        return (régi != Allapot.MEGADOTT);
    }
    
    
    /**
     * Megvizsgálja, hogy a paraméterként kapott értéket tralmazza e a szintén paraméterként kapott rekesz.
     * @param rekesz a vizsgálandó rekesz
     * @param tartalom a vizsgált érték
     * @return boolean
     */
    public static boolean nincsARekeszben(Rekesz rekesz, String tartalom){
        
        for (Cella[] c : rekesz.getRekesz()) {
            for (Cella c1 : c) {
                if(c1.getTartalom().equals(tartalom)){
                    //System.err.println("Már van ilyen érték az adott rekeszben!");
                    return false;
                }
            }
        }
        return true;
    }
    
    
    /**
     * Megvizsgálja, hogy a paraméterként kapott rekesz teljesen kitöltött <br/>
     * és van e két ugyanolyan értéket tartalmazó cellája.
     * @param rekesz a vizsgálandó rekesz
     * @return boolean, van e hiba
     */
    public static boolean nicsHibaARekeszben(Rekesz rekesz){
        if(!isKészARekesz(rekesz)){
            return false;
        }
        else{
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    
                    for (int k = i; k < 3; k++) {
                        for (int l = j+1; l < 3; l++) {
                            
                            if(rekesz.getRekesz()[i][j].getTartalom().equals(rekesz.getRekesz()[k][l].getTartalom())){
                                return false;
                            }
                            
                        }
                    }
                    
                }
            }
            
        }
         
        return true;
    }
    
    
    /**
     * Megvizsgálja, hogy a rekeszben minden cellában van e érték.
     * @param rekesz a vizsgálandó rekesz
     * @return boolean 
     */
     public static boolean isKészARekesz(Rekesz rekesz){
        for(Cella[] mt : rekesz.getRekesz()){
            for(Cella m : mt){
                if(m.getÁllapot() == Allapot.ÜRES){
                    return false;
                }
            }
        }
        return true;
    }
     
     
      /**
     * Annak ellenőrzése, hogy az adott sorban nincs ugyanolyan szám  táblában.
     * @param tábla a vizsgálandó tábla
     * @param sor a tábla ellenőrizendő sora
     * @param érték a vizsgálandó érték
      *@return boolean, false ha van, true ha nincs
    */
    public static boolean ellenőrizSor(Tabla tábla, int sor, String érték){
        
        for (int i = 0; i < 3; i++) {
            Rekesz r = tábla.getTabla()[IndexSzamito.indexetSzámolTáblában(sor)][i];
            for (int j = 0; j < 3; j++) {
                if(r.getRekesz()[(sor-1)%3 ][j].getTartalom().equals(érték)){
                    return false;
                }
            }
        }
        return true;
    }
    
    
     /**
     * Annak ellenőrzése, hogy az adott oszlopban nincs ugyanolyan szám a táblában.
     * @param tábla a vizsgálandó tábla
     * @param oszlop az ellenőrizendő oszlop
     * @param érték az érték, aminek a többszöri előfordulását vizsgáljuk a tábla megadott oszlopában
     *   @return boolean, false ha van, true ha nincs
    */
    public static boolean ellenőrizOszlop(Tabla tábla, int oszlop, String érték){
        Rekesz r;
        for (int i = 0; i < 3; i++) {
            r = tábla.getTabla()[i][IndexSzamito.indexetSzámolTáblában(oszlop)];
            for (int j = 0; j < 3; j++) {
                if(r.getRekesz()[j][(oszlop-1)%3].getTartalom().equals(érték)){
                    return false;
                }
            }
        }
        return true;
    }
    
    
    /**
     * Megvizsgálja, hogy a paraméterként kapott tábla megfelel e a játék által meghatározott követelményeknek.<br/>
     * Másszóval eldönti, hogy helyes e a felhasználó által kitöltött tábla.
     * <p>A tábla helyesen kitöltött, ha minden rekesze ki van töltve 
     * (melyet az {@link isKészARekesz()} metódus határoz meg).</p>
     * <p>Ekkor nincs már a táblába írandó számjegy és a játék befejeződik.</p>
     * 
     * @param tábla  a vizsgálandó tábla
     * @return boolean a tábla helyessége
    */
    public static boolean isHelyesATábla(Tabla tábla){
        for (Rekesz[] t : tábla.getTabla()) {
            for (Rekesz rek : t) {
                if(!Ellenor.isKészARekesz(rek)){
                    return false;
                }
            }
        }
        
        for(int i = 1; i < 10; i++){
            if(tábla.getCellaDarabszám(i) != 0){
                return false;
            }
        }
        
        return true;
    }
    
    
}
