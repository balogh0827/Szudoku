/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elemek;

import funkciok.*;
/**
 * A játék {@code Tabla} oszálya, mely egy Szudoku táblát reprezentál.
 * @author balogh0827
 */
public class Tabla {
    
    /**
     * A Szudoku tábla valójában {@link Rekesz}-ekből álló mátrix.
     */
    private final Rekesz[][] teljesTábla;
    
    /**
     * Ez a tömb tárolja, hogy melyik számjegyből mennyi van a táblában.
     */
    private final int[] db;
    
    /**
     * A {@link Tabla} konstruktora, mely létrehoz egy üres táblát.
     */
    public Tabla() {
        teljesTábla = new Rekesz[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                teljesTábla[i][j] = new Rekesz();
            }
        }
        db  = new int[]{9,9,9,9,9,9,9,9,9};
    }
    
    /**
     * Visszaadja a teljes táblát.
     * @return {@link Rekesz}[][], az aktuális tábla
     */
    public Rekesz[][] getTabla(){
        return teljesTábla;
    }
    
    
    /**
     * A visszaadott érték megmondja, hogy a megadott számjegyből mennyi található a táblában.
     * @param számjegy a vizsgálandó számjegy
     * @return a táblában található {@code számjegy} tartalmú cellák darabszáma
     */
    public int getCellaDarabszám(int számjegy){
            return db[számjegy - 1];
    }
    
    
    /**
     * Beállítja a {@code számjegy} tartalmú {@link Cella} darabszámát a megadott értékre.
     * @param számjegy a vizsgálandó számjegy
     * @param darab a cellák darabszáma
     */
    public void setCellaDarabszám(int számjegy, int darab){
        db[számjegy-1] = darab;
    }
    
    /**
     * Visszaadja a {@code sor} és {@code oszlop} paraméterekkel megadott cellát.
     * @param sor a cella sorszáma
     * @param oszlop a cella oszlopszáma
     * @return {@link Cella}, a kívánt cella
     */
    public Cella getAktuálisCella(int sor, int oszlop){
        return teljesTábla[IndexSzamito.indexetSzámolTáblában(sor)][IndexSzamito.indexetSzámolTáblában(oszlop)].getEgyCellaARekeszben(sor, oszlop);
    }

}
