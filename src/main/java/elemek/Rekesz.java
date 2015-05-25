/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elemek;
import funkciok.IndexSzamito;
/**
 * Egy 3x3-as területet reprezentál a programban.
 * <br/><i>Tulajdonképpen 9 {@link Cella} található benne 3 sorba és 3 oszlopba elrendezve.</i>
 * @author balogh0827
 */
public class Rekesz {
    
    
    /**
     * Egy teljes rekesz, ami valójában {@link Cella}-kból álló 3x3-as mátrix.
     */
    private final Cella[][] egyRekesz;
    
    
    /**
     * A {@link Rekesz} konstruktora, mely létrehoz egy üres rekeszt.
     */
    public Rekesz() {
        egyRekesz = new Cella[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                egyRekesz[i][j] = new Cella(i+1, j+1);
            }
        }
    }
    
    
    /**
     * Visszaadja az aktuális {@code Rekesz}-t.
     * @return {@link Cella}[][], az aktuális rekesz cellák mátrixaként
     */
    public Cella[][] getRekesz(){
        return egyRekesz;
    }
    
    
    /**
     * Visszaad egy cellát a rekeszben.
     * @param sor a cella sorszáma
     * @param oszlop  a cella oszlopszáma
     * @return {@link Cella} a kívánt cella
     */
    public Cella getEgyCellaARekeszben(int sor, int oszlop){
        return egyRekesz[IndexSzamito.indexetSzámolRekeszben(sor)][IndexSzamito.indexetSzámolRekeszben(oszlop)];
    }
    
}
