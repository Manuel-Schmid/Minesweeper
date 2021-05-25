package application;

/**
 * Diese Klasse dient als Memento-Klasse, in der die wichtigsten Spiel-
 * Infos gespeichert werden, damit sie später wiederhergestellt werden können
 * 
 * @author Many, Sven, Lewin
 * @version 1.0
 */

public class GameState {

	/**
	 * Konstante Liste, der Felder in der Tabelle des BackUps
	 */
	private final Field[][] fieldList;
	
	/**
	 * Konstante, die die Anzahl flags anzeigt
	 */
	private final int flags;
	
    /**
     * Konstruktor für die Klasse GameState
     * Setzt die Konstanten, um den Spielstand zu speichern
     * @param nFieldList Zu speicherndes Spielfeld
     * @param flags Zu speichernde Anzahl Flags
     */
	public GameState(Field[][] nFieldList, int flags) {
		fieldList = deepCopyStateFieldList(nFieldList);
		this.flags = flags;
	}
	
    /**
     * Getter Methode für die Flags
     * @return Gibt die Anzahl Flags zurück
     */
	public int getFlags() {
		return flags;
	}

    /**
     * Getter Methode für das Spielfeld
     * @return Gibt das gespeicherte Spielfeld zurück
     */
	public Field[][] getFieldList() {
		return fieldList;
	}
  
    /**
     * Kopiert die übergebene Liste und gibt diese Kopie zurück
     * @param nFieldList Zu kopierende Spielfeld-Liste
     * @return Gibt die kopierte Spielfeld-Liste zurück
     */
	private Field[][] deepCopyStateFieldList(Field[][] nFieldList) {
		final Field[][] copy = new Field[nFieldList.length][nFieldList[0].length];
		for (int i = 0; i < copy.length; i++) {
			for (int j = 0; j < copy[0].length; j++) {
				Field f = nFieldList[i][j];
				copy[i][j] = new Field(f.getX(), f.getY(), f.getNeighbourBombs(), f.isBomb(), f.isShown(), f.isFlagged());
			}
		}
		return copy;
	}	
}
