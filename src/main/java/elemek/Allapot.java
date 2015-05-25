/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elemek;

/**
 *
 * @author STARLIGHT
 */

/**
 * Az Allapot nevű felsorolásos típus a cellák állapotát írja le.
 * <p>Egy cella lehet <br/>
 *    <b>ÜRES</b>, ha nincs benne számérték;<br/>
 *    <b>MEGADOTT</b>, ha kezdőcella (vagyis olyan cella, aminek tartalmát nem a játékos adja meg);<br/>
 *    <b>KITÖLTÖTT</b>, ha tartalmát a játék folyamán a játékos helyesen adja meg.<br/>
 * </p>
 */
public enum Allapot {
    ÜRES, MEGADOTT, KITÖLTÖTT
}
