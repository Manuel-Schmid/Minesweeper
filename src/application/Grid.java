package application;

/**
 * Dies ist die Grid Klasse des Minesweeper-Programmes. 
 * Sie enth�lt Erstellung des Spielfelds und alle direkten Interaktionen die dieses betreffen
 * 
 * @author Many, Sven, Lewin
 * @version 1.0
 */

import java.util.*;
import java.util.stream.Collectors;

public class Grid {

	/**
	 * Variable f�r die Spielfeld-Liste
	 */
    private Field[][] fieldList;
    
	/**
	 * Konstante f�r die Anzahl Bomben
	 */
    private final int bombCount;
    
	/**
	 * Konstante f�r die Anzahl Reihen
	 */
	private final int rows;
	
	/**
	 * Konstante f�r die Anzahl Spalten
	 */
    private final int columns;
    
	/**
	 * Konstante f�r die Stopuhr
	 */
    private final Stopwatch stopwatch;

	/**
	 * Constructor des Grids. Setzte s�mtliche Werte f�r ein neues Grid
	 * Erstellt anhand dieser Werte das neue Spielfeld
	 * @param columns Anzahl Spalten als int
	 * @param rows Anzahl Reihen als int
	 * @param bombCount Anzahl Bomben als int
	 * @param startingTime Startzeit als long
	 */
    public Grid(int columns, int rows, int bombCount, long startingTime) {
        this.rows = rows;
        this.columns = columns;
        this.bombCount = bombCount;
        this.stopwatch = new Stopwatch(startingTime);
        fieldList = new Field[rows][columns];

        for (int i = 0; i < fieldList.length; i++) {
            for (int j = 0; j < fieldList[0].length; j++) {
                fieldList[i][j] = new Field(i, j);
            }
        }
    }

    /**
     * F�llt das Spielfeld an zuf�lligen Koordinaten mit Bomben
     * Danach wird f�r jedes Feld die Anzahl benachbarter Felder 
     * mit Bomben zusammengez�hlt und gesetzt
     * @param x X-Wert f�r des ersten Spielzugs, hier darf keine Bombe sein
     * @param y Y-Wert f�r des ersten Spielzugs, hier darf keine Bombe sein
     */
    private void fillWithBombs(int x, int y) {
        // Fill fields with random Bombs
        int bx, by;
        Random r = new Random();
        for (int i = 0; i < bombCount; i++) {
            do {
                bx = r.nextInt(rows);
                by = r.nextInt(columns);
            } while (getField(bx,by).isBomb() || (bx == x && by == y));
            getField(bx,by).setBomb(true);
        }

        // Set neighbourBomb-Count
        for (int i = 0; i < fieldList.length; i++) {
            for (int j = 0; j < fieldList[0].length; j++) {
                fieldList[i][j].setNeighbourBombs(calcNeighbourBombs(fieldList[i][j]));
            }
        }
    }
    
	/**
	 * Gibt das Feld mit den �bergebenen Koordinaten aus dem Spielfeld als 'Field' zur�ck
	 * @param x X-Koordinate des Feldes
     * @param y Y-Koordinate des Feldes
     * @return Feld mit den entsprechenden Koordinaten
	 */
    public Field getField(int x, int y) {
        return fieldList[x][y];
    }
    
    /**
     * Deckt das Feld auf, vom welchem die Koordinaten �bergeben werden
     * isFirstMove wird f�r den ersten Spielzug gebraucht, damit das Feld erst nach diesem Zug mit Bomben gef�llt wird 
     * Nachdem das erste Mal ein Feld aufgedeckt wurde, wird das Spielfeld mit Bomben gef�llt
     * Wenn ein Feld mit einer Bombe aufgedeckt wird, stirbt der Spieler und eine entsprechende Meldung wird ausgegeben
	 * @param x X-Koordinate des Feldes
     * @param y Y-Koordinate des Feldes
     * @param isFirstMove Gibt an, ob es sich um den Ersten Zug des Spiels handelt
     * @return Boolean der angibt, ob das Spiel weiterlaufen soll, bei false wird es unterbrochen
     */
    public boolean uncoverGridField(int x, int y, boolean isFirstMove) {
        if (isFirstMove) {
            fillWithBombs(x,y);
        }
        Field field = getField(x, y);
        if (field.isBomb()) { // Death
            field.setShown(true);
            printGrid(false);
            Scanner sc = new Scanner(System.in);
            System.out.print("------------\n");
            System.out.print("YOU DIED !!!\n");
            System.out.print("------------\n");
            System.out.println();
            return false;
        } else {
            uncover(field);
        }
        printGrid(true);
        if(isVictory()) {
            Scanner sc = new Scanner(System.in);
            printVictory();
            sc.nextLine();
            return false;
        }
        return true;
    }

    /**
     * Deckt das �bergebene Feld n auf. Wenn s�mtliche umliegenden Felder keine Bomben sind, decke diese auch auf. (Rekursiv)
     * @param n aufzudeckendes Feld
     */
    private void uncover(Field n) {
        n.setShown(true);
        if(n.getNeighbourBombs() == 0) {
            ArrayList<Field> neighbours = getNeighbours(n);
            for (Field f : neighbours) {
                if (!f.isBomb() && !f.isShown()) {
                    uncover(f);
                }
            }
        }
    }

    /**
     * Berechnet Anzahl der umliegenden Bomben, damit eine Zahl angezeigt werden kann. 
     * Dazu werden die umliegenden felder genommen und bei jedem geschaut, ob es eine Bombe ist. 
     * Dann wird der counter hochgez�hlt.
     * @param field Feld, dessen benachbarte Bomben gez�hlt werden sollen
     * @return count Anzahl benachbarter Bomben
     */
    private int calcNeighbourBombs(Field field) {
        ArrayList<Field> neighbours = getNeighbours(field);
        int count = 0;
        for (Field f : neighbours) {
            if (f.isBomb()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Die Umliegenden Felder des �bergebenen Feldes werden zur�ckgegeben, indem man in der X- und Y-Achsen in alle Richtungen geht.
     * Es wird dabei mit einer checkField()-Methode �berpr�ft, ob diese Felder �berhaupt existieren 
     * @param field Feld, dessen Nachbaren zur�ckgegeben werden sollen
     * @return ArrayList von allen Nachbaren
     */
    private ArrayList<Field> getNeighbours(Field field) {
        int x = field.getX();
        int y = field.getY();
        ArrayList<Field> n = new ArrayList<>();
        n.add(checkField(x, y+1));
        n.add(checkField(x, y-1));
        n.add(checkField(x-1, y));
        n.add(checkField(x-1, y+1)); // --
        n.add(checkField(x-1, y-1)); // --
        n.add(checkField(x+1, y));
        n.add(checkField(x+1, y+1)); // --
        n.add(checkField(x+1, y-1)); // --
        return n.stream().filter(Objects::nonNull).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Gibt zur�ck, ob das Feld auch wirklich existiert. 
	 * @param x X-Koordinate des Feldes
     * @param y Y-Koordinate des Feldes
     * @return Field-Objekt oder, falls es ein Fehler gab beim Finden des Feldes 'null'
     */
    private Field checkField(int x, int y) {
        try {
            Field f = getField(x, y);
            return f;
        } catch(Exception e) {
            return null;
        }
    }

    /**
     * printGrid() gibt das Gesamte Grid aus, indem es durch die fieldList 
     * iteriert und eine Tabellenstruktur darum herum zeichnet
     * Zudem wird auch ausgegeben, wie viele Flags man noch zur Verf�gung hat. 
     * @param alive Boolean-�bergabe, ob der Spieler noch lebt.
     */
    public void printGrid(boolean alive) {
        System.out.println();
        System.out.print("   ");
        for (int i = 1; i <= fieldList[0].length; i++) { // X-Axis coordinates
            System.out.print(" | ");
            System.out.print(String.format("%02d", i));
        }
        // Printing the Grid
        System.out.println(" |");
        for (int i = 0; i < fieldList.length; i++) {
            // New Row
            StringBuilder line = new StringBuilder("");
            line.append("| " + String.valueOf((char) (i + 65)));
            for (int j = 0; j < fieldList[0].length; j++) {
                // New Column
                line.append(paintField(fieldList[i][j], alive));
            }
            line.append(" |");
            System.out.println(getPrintedLine(line.length()));
            System.out.println(String.valueOf(line));
        }
        System.out.println();
        System.out.print("Flags: ");
        System.out.print(String.valueOf(Game.getFlags()));
        System.out.println();
    }

    /**
     * F�llt die Tabelle mit den bestimmten Zeichen jedes Feldes. 
     * @param field �bergabe des gew�nschten Feldes
     * @param alive Boolean-�bergabe, ob der Spieler noch lebt.
     * @return eines Strings, welcher die gew�nschten Zeichen beinhaltet. 
     */
    private String paintField(Field field, boolean alive) {
    	StringBuilder fieldStr = new StringBuilder("");
        if (field.isFlagged()) {
        	fieldStr.append(" | ##");
        } else if (field.isBomb() && field.isShown()) {
        	fieldStr.append(" | XX");
        } else if (field.isBomb() && !alive) {
        	fieldStr.append(" | XX");
        } else if (field.isShown()) {
            if (field.getNeighbourBombs()  > 0) {
            	fieldStr.append(" | " + String.format("%02d", field.getNeighbourBombs()));
            } else {
            	fieldStr.append(" |   ");
            }
        } else {
        	fieldStr.append(" | ??");
        }
        return String.valueOf(fieldStr);
    }

    /**
     * Fragt ab, ob der Spieler gewonnen hat, indem es das Gesamte Grid durchl�uft und die Spielsituation �berpr�ft 
     * Die Anzahl geflaggter Bomben und aufgedeckter Felder werden gez�hlt. Wenn diese den Gewinnvoraussetzungen
     * entsprechen, wird 'true' zur�ckgegeben
     * @return Boolean-Wert, der angibt, ob man das Spiel gewonnen hat oder nicht
     */
    public boolean isVictory() {
        int amountNotShown = 0;
        int amountFlaggedBombs = 0;
        for (int i = 0; i < fieldList.length; i++) {
            for (int j = 0; j < fieldList[0].length; j++) {
                if (!fieldList[i][j].isShown()) amountNotShown++;
                if (fieldList[i][j].isFlagged() && fieldList[i][j].isBomb()) amountFlaggedBombs++;
            }
        }
        return amountNotShown == bombCount || amountFlaggedBombs == bombCount;
    }
	
	/**
	 * Gibt die �bergebene Anzahl an Dashes ("-") in einem String zur�ck, 
	 * dies ist n�tzlich f�r die Erstellung von dynamischen Tabellendesigns.
	 * @param size Anzahl Dashes.
	 * @return Ein String, der aus der gew�nschten Anzahl Dashes besteht.
	 */
    public String getPrintedLine(int size) {
        return "-".repeat(Math.max(0, size));
    }

	/**
	 * Gibt die Meldung, dass man das Spiel gewonnen hat auf die Konsole aus.
	 * Die gebrauchte Zeit wird aus der Stopuhr ausgelesen und leserlich formattiert.
	 */
    void printVictory() {
        System.out.println();
        String time = "Your time: " + stopwatch.getFormattedTime();
        System.out.print(getPrintedLine(time.length()) + "\n");
        System.out.print(" ".repeat(Math.max(0, (time.length() / 2)-5)) + "YOU WON !!!\n");
        System.out.print(time + "\n");
        System.out.print(getPrintedLine(time.length()) + "\n");
        System.out.println();
    }
  
	/**
	 * �berschreibt die fieldList mit den Feldwerten aus einem �bergebenen BackUp.
	 * @param nFieldList BackUp-FieldListe
	 */
    public void deepRestoreFieldList(Field[][] nFieldList) {
  	  fieldList = new Field[rows][columns];
  	  for (int i = 0; i < fieldList.length; i++) {
  	      for (int j = 0; j < fieldList[0].length; j++) {
  	    	  Field f = nFieldList[i][j];
  	    	  fieldList[i][j] = new Field(f.getX(), f.getY(), f.getNeighbourBombs(), f.isBomb(), f.isShown(), f.isFlagged());
  	      }
  	  }
    }
    
	/**
	 * Kopiert die Werte aus der fieldList in eine BackUp-Liste und gibt diese Zur�ck
	 * @return BackUp-fieldList
	 */
    public Field[][] deepCopyFieldList() {
        final Field[][] copy = new Field[rows][columns];
        for (int i = 0; i < copy.length; i++) {
            for (int j = 0; j < copy[0].length; j++) {
            	Field f = getField(i,j);
                copy[i][j] = new Field(f.getX(), f.getY(), f.getNeighbourBombs(), f.isBomb(), f.isShown(), f.isFlagged());
            }
        }
        return copy;
    }
    
	/**
	 * Getter f�r die Variable bombCount
	 * @return bombCount
	 */
    public int getBombCount() {
		return bombCount;
	}

	/**
	 * Getter f�r die Variable rows
	 * @return rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * Getter f�r die Variable columns
	 * @return columns
	 */
	public int getColumns() {
		return columns;
	}
}
