package application;

/**
 * Diese Klasse beinhaltet einen Stack der es erm�glicht,
 * Spielst�nde zu speichern, bevor ein Zug gemacht wird
 * Somit kann ein BackUp einfach wiederhergestellt werden.
 * 
 * @author Many, Sven, Lewin
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.List;

public class History {
	
    /**
     * Konstante Liste mit Stack-Aufbau f�r BackUp-Spielst�nde
     */
    private static final List<GameState> states = new ArrayList<>();

	/**
	 * Pusht einen BackUp-Spielstand in den Stack
	 * @param state BackUp-Spielstand
	 */
    public static void push(GameState state) {
        states.add(state);
    }

    /**
     * Popt den obersten BackUp-Spielstand auf dem Stack, also wird er zur�ckgegeben und dann gel�scht
     * @return Oberster BackUp-Spielstand
     */
    public static GameState pop() {
        GameState lastState = states.get(states.size() - 1);
        states.remove(lastState);
        return lastState;
    }

    /**
     * L�scht den gesamten Inhalt des Stacks
     */
    public static void clear() {
        states.clear();
    }

    /**
     * Gibt zur�ck, ob der Stack nurnoch einen BackUp-Spielstand oder weniger hat 
     * damit ein reset des Felder durchgef�hrt werden kann
     * @return Gibt an, ob auf dem Stack nur noch ein GameState liegt
     */
    public static boolean needsReset() {
        return states.size() <= 0;
    }
}