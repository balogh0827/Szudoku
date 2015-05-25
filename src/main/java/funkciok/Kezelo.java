/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funkciok;
import elemek.*;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;





public class Kezelo {
    
    static {
        InputStream in = Kezelo.class.getResourceAsStream("/logging.properties");
        if (in != null) {
            try {
                    LogManager.getLogManager().readConfiguration(in);
            } catch(IOException e) {
                    e.printStackTrace();
            }
        }
    }
    
    protected static Logger	logger = Logger.getLogger(Kezelo.class.getName());
    
    /**
     * Beír egy felhasználó által megadott értéket (ha helyes) a rekesz megfelelő cellájába.
     * @param rekesz a rekesz amiben el kell helyezni a cellát
     * @param sor a cella sorszáma a táblában
     * @param oszlop a cella oszlopszáma a táblában
     * @param érték a beírandó tartalom
     * @return boolean a beírás sikeressége
     * 
     * @exception NumberFormatException ha a az érték nem alakítható számjeggyé
     */
    public static boolean beírRekeszbe(Rekesz rekesz, int sor, int oszlop, String érték){
        logger.entering("Kezelo", "beírRekeszbe", new Object[]{rekesz, sor, oszlop, érték});
       try{
           logger.info("Rekeszbe írás megkísérlése.");
               if(Ellenor.tesztIntervallum(Integer.parseInt(érték)) && Ellenor.nincsARekeszben(rekesz, érték)){
                   
                    rekesz.getRekesz()[IndexSzamito.indexetSzámolRekeszben(sor)][IndexSzamito.indexetSzámolRekeszben(oszlop)].setTartalom(érték);
                    rekesz.getRekesz()[IndexSzamito.indexetSzámolRekeszben(sor)][IndexSzamito.indexetSzámolRekeszben(oszlop)].setÁllapot(Allapot.KITÖLTÖTT);
                    logger.info("Az érték bekerült a rekeszbe.");
                    logger.exiting("Kezelo", "beírRekeszbe", true);
                    return true;
               }
               
        }catch(NumberFormatException e){
            logger.severe("Az érték átalakításakor kivétel váltódott ki.");
            logger.exiting("Kezelo", "beírRekeszbe", false);
            return false;
        }
        logger.warning("Sikertelen művelet.");
        logger.exiting("Kezelo", "beírRekeszbe", false);
        return false;
    }
    
    
    
    /**
     * Betölti a cellát a megadott rekeszbe, ha érvényes értéket tartalmaz <br/>
     * és még nem szerepel ugyanilyen tartalmú cella a rekeszben.
     *  <br/><i> A játék kezdetekor fut le, nem a felhasználó adja meg.</i>
     * @param rekesz a rekesz amibe a cellát kell betenni
     * @param cella az elhelyezendő cella
     * @return boolean a betöltés sikeressége
     * @exception NumberFormatException ha a cella tartalma nem megfelelő
    */
    public static boolean betöltRekeszbe(Rekesz rekesz, Cella cella){
        logger.entering("Kezelo", "betöltRekeszbe", new Object[]{rekesz, cella});
        try{
            logger.info("Cella rekeszbe töltésének megkísérlése.");
               if(Ellenor.tesztIntervallum(Integer.parseInt(cella.getTartalom())) && Ellenor.nincsARekeszben(rekesz, cella.getTartalom())){
                    rekesz.getRekesz()[IndexSzamito.indexetSzámolRekeszben(cella.getSorszám())][IndexSzamito.indexetSzámolRekeszben(cella.getOszlopszám())] = cella;
                    logger.info("Sikeres betöltés.");
                    logger.exiting("Kezelo", "betöltRekeszbe", true);
                    return true;
               }
        }catch(NumberFormatException e){
            logger.severe("Hiba az érték átalakításakor.");
            logger.exiting("Kezelo", "betöltRekeszbe", false);
            return false;
        }
        logger.warning("Sikertelen betöltés.");
        logger.exiting("Kezelo", "betöltRekeszbe", false);
        return false;
    }
    
    
     /**
      * Kiszámítja, hogy melyik számjegyből mennyit kell még a táblába írni.
      * @param tábla a viszgálandó tábla
      */
    public static void frissítDarabszám(Tabla tábla){
        logger.entering("Kezelo", "frissítDarabszám", tábla);
        logger.info("Darabszámok frissítése megkezdődött.");
        int[] szamjegyekDarabszáma = new int[9];
        String tartalom = "";
        
        for(int i = 1; i < 10; i++){
            for (int j = 1; j < 10; j++) {
                tartalom = tábla.getAktuálisCella(i, j).getTartalom();
                if(!tartalom.equals("")){
                    ++szamjegyekDarabszáma[Integer.parseInt(tartalom) - 1];
                }
            }
        }

        for (int i = 0; i < 9; i++) {
            tábla.setCellaDarabszám(i+1, 9 - szamjegyekDarabszáma[i]);
        }
        
        logger.info("Darabszámok frissítve lettek.");
        logger.exiting("Kezelo", "frissítDarabszám");
    }
    
    
    /**
     * A {@link Allapot.MEGADOTT} cellák kivételével minden cella tartalmát törli a megadott rekeszben.
     * @param rekesz a rekesz amelyben a megfelelő cellák tartalma törlésre kerül
     */
    public static void üritRekesz(Rekesz rekesz){
        logger.entering("Kezelo", "üritRekesz", rekesz);
        logger.info("Rekesz kiürítése megkezdődött.");
        for(Cella[] c : rekesz.getRekesz()){
            for (Cella cell : c) {
                if(!cell.getÁllapot().equals(Allapot.MEGADOTT)){
                    cell.setTartalom("");
                }
            }
        }
        logger.info("Rekesz kiürítése sikeresen befejeződött.");
        logger.exiting("Kezelo", "üritRekesz");       
    }
    
    
    /**
     * Törli a táblából a nem {@link Allapot.MEGADOTT} értékeket.
     * <p><i>Az egyes rekeszek kiürítése a {@link üritRekesz()} metódussal történik.</i></p>
     * @param  tábla  a kiürítendő tábla
     */
    public static void kiürit(Tabla tábla){
        logger.entering("Kezelo", "kiürit", tábla);
        logger.info("Tábla kiürítése elkezdődött.");
        for(Rekesz[]  r : tábla.getTabla()){
            for(Rekesz rek : r){
                üritRekesz(rek);
            }
        }
        logger.info("Tábla kiürítése sikeres.");
        logger.exiting("Kezelo", "kiürit");
    }
    
    
    
    /**
     * Kitörli a tábla minden cellájának tartalmát.
     * @param  tábla  a törlendő tábla
     */
    public static void törölTeljesTábla(Tabla tábla){
        logger.entering("Kezelo", "törölTeljesTábla", tábla);
        logger.info("Tábla törlése.");
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++) {
                tábla.getTabla()[i][j] = new Rekesz();
            }
        }
        logger.info("Teljes tábla kitörölve.");
        logger.exiting("Kezelo", "törölTeljesTábla");
    }
    
    
    /**
     * Cella betöltése a tábla megfelelő helyére a cellában tárolt adatok alapján.
     * A játék kezdetén ezzel a metódussal tölti fel a program a táblát.
     * @param tábla  a tábla amibe a cellát kell betölteni
     * @param cella a betöltendő cella
    */
    public static void betöltCella(Tabla tábla, Cella cella){
        logger.entering("Kezelo", "betöltCella", new Object[]{tábla, cella});
        logger.info("Cella betöltése a táblába elkezdődött.");
        Rekesz r = tábla.getTabla()[IndexSzamito.indexetSzámolTáblában(cella.getSorszám())][IndexSzamito.indexetSzámolTáblában(cella.getOszlopszám())];
        betöltRekeszbe(r, cella);
        logger.info("Cella betöltése a táblába befejeződött.");
        logger.exiting("Kezelo", "betöltCella");
    }
    
    
    
    /**
     * Beírja a áblába az értéket a sor és oszlop paraméterekkel megadott cellába, ha nincs hiba.
     * @param tábla a módosítandó tábla
     * @param sor a cella sorszáma
     * @param oszlop  a cella oszlopszáma
     * @param érték a cellába írandó érték
     * @return int  hibakód 
                   <br/> 0, ha nincs hiba
                   <br/>-1, ha van a sorban ugyanolyan érték
                   <br/> -2, ha van az oszlopban ugyanolyan érték
                   <br/>-3, ha van a rekeszben ugyanolyan érték
                   <br/> -4, ha nem módosítható a cella
                   <br/> -5, ha hibás a paraméterként megadott érték
     */
    public static int beír(Tabla tábla, int sor, int oszlop, String érték){
        logger.entering("Kezelo", "beír", new Object[]{tábla, sor, oszlop, érték});
        logger.info("Érték beírása a táblába folamat elkezdődött.");
        if(tábla.getAktuálisCella(sor, oszlop).getÁllapot() == Allapot.MEGADOTT){
            logger.warning("A cella tartalma nem módosítható.");
            logger.exiting("Kezelo", "beír", -4);
            return -4;
        }
        if(!Ellenor.tesztIntervallum(sor) || !Ellenor.tesztIntervallum(oszlop) || !Ellenor.tesztIntervallum(Integer.parseInt(érték))){
            logger.warning("Hibás érték.");
            logger.exiting("Kezelo", "beír", -5);
            return -5;
        }
        if(!Ellenor.ellenőrizSor(tábla, sor, érték)){
            logger.warning("Már van ilyen szám az adott sorban.");
            logger.exiting("Kezelo", "beír", -1);
            return -1;
        }
        if(!Ellenor.ellenőrizOszlop(tábla, oszlop, érték)){
            logger.warning("Már van ilyen szám az adott oszlopban.");
            logger.exiting("Kezelo", "beír", -2);
            return -2;
        }
        
        Rekesz r = tábla.getTabla()[IndexSzamito.indexetSzámolTáblában(sor)][IndexSzamito.indexetSzámolTáblában(oszlop)];
        if(!Kezelo.beírRekeszbe(r, sor, oszlop, érték)){
            logger.warning("Már van ilyen szám az adott rekeszben.");
            logger.exiting("Kezelo", "beír", -3);
            return -3;
        }
        logger.info("Az érték beírásra került a táblába.");
        logger.exiting("Kezelo", "beír", 0);
        return 0;
    }
    
    
    
     /**
      * Egy fájlból kiolvas értékeket, melyekkel feltölti a táblát új játék indításakor.
      * @param tábla a feltöltendő tábla
      */
    public static void init(Tabla tábla){
        logger.entering("Kezelo", "init", tábla);
        logger.info("Tábla inicializálása megkezdődött.");
        
        List<Cella> lista = new ArrayList<>();
      // int randNum = new Random().nextInt();
        
        try (Scanner in = new Scanner(new BufferedInputStream (Kezelo.class.getResourceAsStream("/Sudokuin.txt")))){
            logger.info("Bemeneti fájl beolvasása.");
            while(in.hasNextLine()){
                
                String[] elemek = in.nextLine().split(",\\s+");
                Cella c = new Cella(Integer.parseInt(elemek[0]), Integer.parseInt(elemek[1]));
                c.setTartalom(elemek[2]);               
                c.setÁllapot(Allapot.MEGADOTT);
                
                lista.add(c);
            }
        }catch(Exception e){
            logger.severe("A bementi fájl nem található.");
        }
        logger.info("Sikeres beolvasás, táblázat feltöltése az értékekkel.");
        for(Cella c : lista){
            betöltCella(tábla, c);
        }
        
        logger.info("Adatok betöltése kész.");
        logger.exiting("Kezelo", "init");
    }
    
    

    /**
     * Elmenti az aktuális játékot, hogy később ugyaninnen folytathassuk.
     * @param tábla a kimentendő tábla
     * @param lépésSzám int, az eddig megtett lépések 
     * @return boolean, a mentés sikerességét jelzi
     */
    public static boolean kimentFájlba(Tabla tábla, String lépésSzám){
        logger.entering("Kezelo", "kimentFájlba");
        logger.info("Kísérlet tábla fájlba mentésére.");
        //try(BufferedWriter bf = new BufferedWriter(new FileWriter(new File(System.getProperty("user.home") + "/Sudokuout.txt")))){
        try(BufferedWriter bf = new BufferedWriter(new FileWriter(Kezelo.class.getResource("/Sudokuout.txt").getFile()))){ 
            logger.info("Kimeneti fájl megnyitása sikeres volt.");
            logger.info("Kimentés folyamatban.");
            
            bf.write(lépésSzám);
            bf.newLine();
            
            char tipus = ' ';
            
            for(int i = 1; i < 10; i++){
                for (int j = 1; j < 10; j++) {
                    
                    switch(tábla.getAktuálisCella(i, j).getÁllapot()){
                        case KITÖLTÖTT:
                            tipus = 'K';
                            break;
                        case MEGADOTT:
                            tipus = 'M';
                            break;
                        case ÜRES:
                            tipus = 'U';
                            break;
                    }
                    
                    bf.append(String.valueOf(tipus) + ", " + i + ", " + j + ", " + tábla.getAktuálisCella(i, j).getTartalom());
                    bf.newLine();
                }
            }
            
        } catch (IOException ex) {
            logger.severe("A kimeneti fájl nem található.");
            logger.info("A mentés nem sikerült.");
            logger.exiting("Kezelo", "kimentFájlba", false);
            return false;
        }
        
        logger.info("Tábla kimentve.");
        logger.exiting("Kezelo", "kimentFájlba", true);
        return true;
    }
    
    
    /**
     * Visszatölt egy korábban mentett játékot.
     * @param tábla   tábla amibe betöltődik a korábban mentett játék
     * @param lépésszám a mostani játék lépésszáma
     * @return String hiba esetén az aktuális, egyébként a mentett játék lépésszáma
     */
    public static String betöltFájlból(Tabla tábla, String lépésszám){
        logger.entering("Kezelo", "betöltFájlból", new Object[]{tábla, lépésszám});
        logger.info("korábbi mentés betöltésének megkísérlése.");
        
        //if((System.getProperty("user.home") + "/Sudokuout.txt") == null){
        if((Kezelo.class.getResourceAsStream("/Sudokuout.txt")) == null){
            logger.warning("A fájl nem nyitható meg.");
            logger.exiting("Kezelo", "betoltFájlból", "-1");
                return "-1";
        }
        törölTeljesTábla(tábla);
        String lépésSzám = "";
        
        logger.info("Korábbi mentést tartalmazó fájl megnyitása.");
        //try(Scanner beolvasó = new Scanner(new BufferedInputStream(new FileInputStream(System.getProperty("user.home") + "/Sudokuout.txt")))){
        try(Scanner beolvasó = new Scanner(new BufferedInputStream(Kezelo.class.getResourceAsStream("/Sudokuout.txt")))){
            
            logger.info("Sikeres megnyitás. Fájl feldolgozása.");
            lépésSzám = beolvasó.nextLine();
            
            while(beolvasó.hasNextLine()){
                
                String[] elemek = beolvasó.nextLine().split(",\\s+");
                Allapot típus = null;
                
                switch(elemek[0].charAt(0)){
                    
                    case 'M':
                        típus = Allapot.MEGADOTT;
                        break;
                    case 'K':
                        típus = Allapot.KITÖLTÖTT;
                        break;
                    case 'U':
                        típus = Allapot.ÜRES;
                        break;
                }
                
                Cella akt;
                if(elemek.length == 3){
                    akt = new Cella(Integer.parseInt(elemek[1]), Integer.parseInt(elemek[2]));
                } else {
                    akt = new Cella(Integer.parseInt(elemek[1]), Integer.parseInt(elemek[2]));
                    akt.setTartalom(elemek[3]);
                }
                akt.setÁllapot(típus);
                
                betöltCella(tábla, akt);
                
                logger.info("Korábbi mentés sikeresen visszatöltve.");
            }
            
        } catch (Exception ex) {
            logger.warning("Hiba a fájl megnyitásakor.");
        }
        
        logger.exiting("Kezelo", "betöltFájlból", lépésSzám);
        return lépésSzám;
    }
    
}
