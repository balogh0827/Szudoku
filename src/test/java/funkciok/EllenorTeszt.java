/*
 * Copyright 2015 Debreceni Egyetem, Informatikai Kar.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package funkciok;

import elemek.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author balogh0827
 */
public class EllenorTeszt {
    
    public EllenorTeszt() { 
        //Ellenor.ellenőrizOszlop(null, oszlop, null);
        //Ellenor.ellenőrizSor(null, sor, null);
       // Ellenor.isHelyesATábla(null);
        
        
    }
    
    @Test
    public void ellenőrizTesztIntervallumNegatívval(){
        assertFalse(Ellenor.tesztIntervallum(-10));
    }
    
    @Test
    public void ellenőrizTesztIntervallumNullával(){
        assertFalse(Ellenor.tesztIntervallum(0));
    }
    
    @Test
    public void ellenőrizTesztIntervallumEggyel(){
        assertTrue(Ellenor.tesztIntervallum(1));
    }

    @Test
    public void ellenőrizTesztIntervallumKilenccel(){
        assertTrue(Ellenor.tesztIntervallum(9));
    }
    
    @Test
    public void ellenőrizTesztIntervallumTízzel(){
        assertFalse(Ellenor.tesztIntervallum(10));
    }
    
    
    @Test
    public void ellenőrizÁllapotMódosításÜressel(){
        assertTrue(Ellenor.isMódosíthatóÁllapot(Allapot.KITÖLTÖTT, Allapot.ÜRES));
    }
    
    @Test
    public void ellenőrizÁllapotMódosításMegadottat(){
        assertFalse(Ellenor.isMódosíthatóÁllapot(Allapot.MEGADOTT, Allapot.ÜRES));
    }
    
    @Test
    public void ellenőrizÁllapotMódosításMegadottalNemMegadottat(){
        assertTrue(Ellenor.isMódosíthatóÁllapot(Allapot.ÜRES, Allapot.MEGADOTT)); 
    }
    
    
    @Test
    public void ellenőrizNincsARekeszbenNincsBenne(){
        elemek.Rekesz  rekesz = new Rekesz();
        
        rekesz.getRekesz()[0][0].setTartalom("1");
        assertTrue(Ellenor.nincsARekeszben(rekesz, "3"));
    }
    
    
    @Test
    public void ellenőrizNincsARekeszbenBenneVan(){
        Rekesz  rekesz = new Rekesz();
        
        rekesz.getRekesz()[0][0].setTartalom("1");
        assertFalse(Ellenor.nincsARekeszben(rekesz, "1"));
    }
    
    
    
    @Test
    public void ellenőrizIsKészARekeszKész(){
        Rekesz r = new Rekesz();
        int szamlalo = 9;
        for(Cella[] c : r.getRekesz()){
            for(Cella cell  : c){
                cell.setTartalom(String.valueOf(szamlalo));
                cell.setÁllapot(Allapot.KITÖLTÖTT);
                szamlalo--;
            }
        }
        
        assertTrue(Ellenor.isKészARekesz(r));
    }

    
    @Test
    public void ellenőrizIsKészARekeszNincsKész(){
        Rekesz r = new Rekesz();
        int szamlalo = 9;
        for(Cella[] c : r.getRekesz()){
            for(Cella cell  : c){
                cell.setTartalom(String.valueOf(szamlalo));
                cell.setÁllapot(Allapot.KITÖLTÖTT);
                szamlalo--;
            }
        }
        
        r.getRekesz()[0][0].setTartalom("");
        
        assertFalse(Ellenor.isKészARekesz(r));
    }
    
    @Test
    public void ellenőrizNincsHibaARekeszbenNincsHiba(){
        Rekesz r = new Rekesz();
        int szamlalo = 9;
        for(Cella[] c : r.getRekesz()){
            for(Cella cell  : c){
                cell.setTartalom(String.valueOf(szamlalo));
                cell.setÁllapot(Allapot.KITÖLTÖTT);
                szamlalo--;
            }
        }
        assertTrue(Ellenor.nicsHibaARekeszben(r));
    }
    
    @Test
    public void ellenőrizNincsHibaARekeszbenVanHiba(){
        Rekesz r = new Rekesz();
        int szamlalo = 9;
        
        
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
               r.getRekesz()[i][j].setTartalom(String.valueOf(szamlalo));
               r.getRekesz()[i][j].setÁllapot(Allapot.KITÖLTÖTT);
            }
        }
        
        assertFalse(Ellenor.nicsHibaARekeszben(r));
    }
    
    
    
    
    
    
}
