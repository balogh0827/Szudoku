/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funkciok;

/**
 * Indexek számításával foglalkozó statikus metódusokat tartalmazó osztály.
 * @author STARLIGHT
 */
public final class IndexSzamito {
    
    
    /**
     * Kiszámítja egy cellának a táblán belüli elhelyezkedése alapján a rekeszben elfoglalt pozícióját.
    * @param táblabeliIndex a cella oszlop vagy sor indexe a táblában
    * @return int, rekeszbeli index
    */
    public static int indexetSzámolRekeszben(int táblabeliIndex){
        int rekeszbeliIndex = táblabeliIndex;
        
        rekeszbeliIndex %= 3;
        
        if(rekeszbeliIndex == 0){
            rekeszbeliIndex = 3;
        }
        
        return rekeszbeliIndex - 1;
    }
    
    /**
     * Kiszámolja a cella táblabeli indexet sor- vagy oszlopindexét.
     * <br/>Pontosabban visszaadja annak a rekesznek a sor- vagy oszlopindexét amiben a cella van.<br/>
     * @param szám  a táblabeli sor- vagy oszlopindex 
     * @return új indexérték 
     */
    public static int indexetSzámolTáblában(int szám){
        return (szám-1)/3;
    }
    
    
}
