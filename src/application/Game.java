package application;

/**
 * @author Manuel Schmid, Lewin Rutz und Sven Walser
 * @version 1.0
 *  
 */

import java.util.Date;
import java.util.Scanner;

public class Game {

	/**
	 * Variable für das Grid
	 */
    private Grid grid;

    /**
     * Variable für die Schwierigkeit
     */
	private Difficulty difficulty;

	/**
	 * Variable für die Anzahl übriger Flags
	 */
	private static int flags = 0;

	/**
	 * Erstellt ein BackUp vom Spielstand
	 * @return BackUp-Gamestate
	 */
    public GameState createState() {
        return new GameState(grid.deepCopyFieldList(), flags);
    }

    /**
     * Stellt einen alten Spielstand wieder her und gibt ihn auf die Konsole aus
     * @param state Der BackUp-Spielstand
     */
    public void restore(GameState state) {
        grid.deepRestoreFieldList(state.getFieldList());
        flags = state.getFlags();

        grid.printGrid(true);
    }

    /**
     * Konstruktor für die Klasse Game
     * setzt die flags der Schwierigkeit entsprechend und erstellt ein entsprechendes Grid.
     * @param charDifficulty Parameter für die Schwierigkeit
     */
    public Game(char charDifficulty) {
        setDifficulty(charDifficulty);
        flags = difficulty.getBombCount();
        grid = new Grid(difficulty.getGridSize()[0], difficulty.getGridSize()[1], difficulty.getBombCount(), new Date().getTime());
    }

    /**
     * Setzt die Schwierigkeit mit dem Strategy-Pattern basierend auf einem Parameter
     * @param charDif Parameter für die Schwierigkeit
     */
    private void setDifficulty(char charDif) {
        switch (charDif) {
            case 'E' -> difficulty = new Easy();
            case 'M' -> difficulty = new Medium();
            case 'H' -> difficulty = new Hard();
        }
    }

    /**
     * Variable die anzeigt, ob schon ein Zug gemacht wurde
     */
    private boolean isFirstMove = true;

    /**
     * Sorgt dafür das ein Feld aufgedeckt wird, indem es den Befehl an das Grid weiterleitet
     * @param input Das Feld, das aufgedeckt werden soll
     * @return Gibt an, ob das Spiel weiterlaufen soll, bei false wird es unterbrochen
     */
    public boolean showField(String input) {
        int[] fieldCords = validateCoordinate(input.toUpperCase().replace(" ", ""));
        if(fieldCords == null) { 
        	System.out.println("Invalid Coordinate");
        	return true;
        } else {
            if (!grid.getField(fieldCords[0], fieldCords[1]).isShown()) {
                History.push(createState());
                boolean b = grid.uncoverGridField(fieldCords[0], fieldCords[1], isFirstMove);
                isFirstMove = false;
                return b;
            } else {
                System.out.println("Field is already shown");
            }
        }
        return true;
    }

    /**
     * Sorgt dafür das ein Feld geflagt oder nicht mehr geflagt wird
     * @param input Die Koordinaten des zu verändernden Feldes
     * @param flagstatus Zeit an, ob das Feld geflagt (true) oder nicht mehr geflagt (false) werden soll
     * @return Gibt an, ob das Spiel weiterlaufen soll, bei false wird es unterbrochen
     */
    public boolean flagField(String input, boolean flagstatus) {
        int[] fieldCords = validateCoordinate(input.toUpperCase().replace(" ", ""));
        if(fieldCords == null) System.out.println("Invalid Coordinate");
        else {
            Field field = grid.getField(fieldCords[0], fieldCords[1]);
            if (flagstatus) { // flag (F)
                if (flags > 0) {
                    if (!field.isFlagged() && !field.isShown()) {
                        History.push(createState());
                        field.setFlagged(true);
                        flags--;
                        grid.printGrid(true);
                        if(grid.isVictory()) {
                            Scanner sc = new Scanner(System.in);
                            grid.printVictory();
                            sc.nextLine();
                            sc.close();
                            return false;
                        }
                    } else System.out.println("Field is already flagged or shown");
                } else {
                    System.out.println("No flags left");
                }
            } else { // unflag (UF)
                if (field.isFlagged()) {
                    History.push(createState());
                    field.setFlagged(false);
                    flags++;
                    grid.printGrid(true);
                } else System.out.println("Field is not flagged");
            }
        }
        return true;
    }

    /**
     * Überprüft und formatiert die übergebenen Koordinaten
     * @param input Die eingegebenen Koordinaten
     * @return Gibt die formatierten und geprüften Koordinaten, oder wenn sie ungültig sind 'null' zurück
     */
    private int[] validateCoordinate(String input) {
    	try {
    		
    		char[] xy = input.toCharArray();
	        int x = ((int) xy[0]) - 65;
	        int y;
	        if (input.length() == 3) {
	            String yStr = xy[1] + "" + xy[2];
	            y = Integer.parseInt(yStr) - 1;
	        } else {
	            y = xy[1] - '0' - 1;
	        }
	        if (x > (difficulty.getGridSize()[1] - 1) || x < 0 || y > (difficulty.getGridSize()[0] - 1) || y < 0) {
	            return null;
	        }
	        return new int[] {x, y};
    	} catch (Exception e) {
    		return null;
    	}
    }

    /**
     * Gibt den Befehl für das Ausgeben des Grids an das Grid weiter
     */
    public void printGame() {
        grid.printGrid(true);
        System.out.println();
    }

    /**
     * erhöt die Anzahl der Flags um 1
     */
    public static void incFlags() {
        flags++;
    }

    /**
     * Getter Methode für die Anzahl Flags
     * @return Anzahl übriger Flags
     */
    public static int getFlags() {
        return flags;
    }
    
    /**
     * Getter Methode für die Schwierigkeit
     * @return gibt die aktuelle Schwierigkeitsstufe zurück
     */
    public Difficulty getDifficulty() {
		return difficulty;
	}
    
    /**
     * Getter Methode für das Grid
     * @return Gibt das Grid zurück
     */
    public Grid getGrid() {
		return grid;
	}
    
    /**
     * Setter Methode für die firstMove-Variable
     * @param firstMove Gibt an, welchen Status die Variable fistMove annehmen soll
     */
    public void setFirstMove(boolean firstMove) {
        isFirstMove = firstMove;
    }
}
