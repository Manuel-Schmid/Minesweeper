package application;

import java.util.ArrayList;
import java.util.List;

public class History {
	
    /**
     * Konstante Liste mit Stack-Aufbau für BackUp-Spielstände
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
     * Popt den obersten BackUp-Spielstand auf dem Stack, also wird er zurückgegeben und dann gelöscht
     * @return Oberster BackUp-Spielstand
     */
    public static GameState pop() {
        GameState lastState = states.get(states.size() - 1);
        states.remove(lastState);
        return lastState;
    }

    /**
     * Löscht den gesamten Inhalt des Stacks
     */
    public static void clear() {
        states.clear();
    }

    /**
     * Gibt zurück, ob der Stack nurnoch einen BackUp-Spielstand oder weniger hat 
     * damit ein reset des Felder durchgeführt werden kann
     * @return Gibt an, ob auf dem Stack nur noch ein GameState liegt
     */
    public static boolean needsReset() {
        return states.size() <= 1;
    }
}