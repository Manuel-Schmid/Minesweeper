package application;

public class Field {
	
	/**
	 * Variable für die X-Koordinate des Feldes
	 */
    private int x;
    
	/**
	 * Variable für die Y-Koordinate des Feldes
	 */
    private int y;
    
	/**
	 * Variable für die Anzahl benachbarter Bomben des Feldes
	 */
    private int neighbourBombs;
    
	/**
	 * Variable die angibt, ob das Feld eine Bombe ist
	 */
    private boolean isBomb = false;
    
	/**
	 * Variable die angibt, ob das Feld aufgedeckt ist
	 */
    private boolean isShown = false;
    
	/**
	 * Variable die angibt, ob das Feld geflagt ist
	 */
    private boolean isFlagged = false;
    
    /**
     * Konstruktor für die Klasse Field
     * Setzt die Koordinaten für ein neues Feld
     * @param x X-Koordinate
     * @param y Y-Koordinate
     */
    public Field(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Erweiterter Konstruktor für die Klasse Field
     * Setzt sämtliche Werte für ein neues Feld
     * @param x X-Koordinate
     * @param y Y-Koordinate
     * @param neighboursBombs Anzahl Nachbarbomben
     * @param isBomb Ist das Feld eine Bombe?
     * @param isShown Ist das Feld aufgedeckt?
     * @param isFlagged Ist das Feld geflagt?
     */
    public Field(int x, int y, int neighboursBombs, boolean isBomb, boolean isShown, boolean isFlagged) {
        this.x = x;
        this.y = y;
        this.neighbourBombs = neighboursBombs;
        this.isBomb = isBomb;
        this.isShown = isShown;
        this.isFlagged = isFlagged;
    }

    /**
     * Getter Methode für isBomb
     * @return Gibt isBomb zurück
     */
    public boolean isBomb() {
        return isBomb;
    }

    /**
     * Setter Methode für isBomb
     * @param Neuer Status für isBomb
     */
    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    /**
     * Getter Methode für isShown
     * @return Gibt isShown zurück
     */
    public boolean isShown() {
        return isShown;
    }

    /**
     * Setter Methode für isShown, falls das Feld geflagt ist wird es 
     * unflagged und die Anzahl Flags wieder um 1 erhöht
     * @param Neuer Status für isShown
     */
    public void setShown(boolean shown) {
        if (isFlagged) {
            isFlagged = false;
            Game.incFlags();
        }
        isShown = shown;
    }

    /**
     * Getter Methode für die X-Koordinate
     * @return Gibt X-Koordinate zurück
     */
    public int getX() {
        return x;
    }

    /**
     * Setter Methode für X-Koordinate
     * @param Neue X-Koordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Getter Methode für die Y-Koordinate
     * @return Gibt Y-Koordinate zurück
     */
    public int getY() {
        return y;
    }

    /**
     * Setter Methode für Y-Koordinate
     * @param Neue Y-Koordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Getter Methode für die Nachbarbomben
     * @return Gibt Anzahl Nachbarbomben zurück
     */
    public int getNeighbourBombs() {
        return neighbourBombs;
    }

    /**
     * Setter Methode für Nachbarbomben
     * @param Neue Anzahl Nachbarbomben
     */
    public void setNeighbourBombs(int neighbourBombs) {
        this.neighbourBombs = neighbourBombs;
    }

    /**
     * Getter Methode für isFlagged
     * @return Gibt isFlagged zurück
     */
    public boolean isFlagged() {
        return isFlagged;
    }

    /**
     * Setter Methode für isFlagged
     * @param Neuer Status für isFlagged
     */
    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }
}
