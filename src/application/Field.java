package application;

public class Field {
	
	/**
	 * Variable f�r die X-Koordinate des Feldes
	 */
    private int x;
    
	/**
	 * Variable f�r die Y-Koordinate des Feldes
	 */
    private int y;
    
	/**
	 * Variable f�r die Anzahl benachbarter Bomben des Feldes
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
     * Konstruktor f�r die Klasse Field
     * Setzt die Koordinaten f�r ein neues Feld
     * @param x X-Koordinate
     * @param y Y-Koordinate
     */
    public Field(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Erweiterter Konstruktor f�r die Klasse Field
     * Setzt s�mtliche Werte f�r ein neues Feld
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
     * Getter Methode f�r isBomb
     * @return Gibt isBomb zur�ck
     */
    public boolean isBomb() {
        return isBomb;
    }

    /**
     * Setter Methode f�r isBomb
     * @param Neuer Status f�r isBomb
     */
    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    /**
     * Getter Methode f�r isShown
     * @return Gibt isShown zur�ck
     */
    public boolean isShown() {
        return isShown;
    }

    /**
     * Setter Methode f�r isShown, falls das Feld geflagt ist wird es 
     * unflagged und die Anzahl Flags wieder um 1 erh�ht
     * @param Neuer Status f�r isShown
     */
    public void setShown(boolean shown) {
        if (isFlagged) {
            isFlagged = false;
            Game.incFlags();
        }
        isShown = shown;
    }

    /**
     * Getter Methode f�r die X-Koordinate
     * @return Gibt X-Koordinate zur�ck
     */
    public int getX() {
        return x;
    }

    /**
     * Setter Methode f�r X-Koordinate
     * @param Neue X-Koordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Getter Methode f�r die Y-Koordinate
     * @return Gibt Y-Koordinate zur�ck
     */
    public int getY() {
        return y;
    }

    /**
     * Setter Methode f�r Y-Koordinate
     * @param Neue Y-Koordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Getter Methode f�r die Nachbarbomben
     * @return Gibt Anzahl Nachbarbomben zur�ck
     */
    public int getNeighbourBombs() {
        return neighbourBombs;
    }

    /**
     * Setter Methode f�r Nachbarbomben
     * @param Neue Anzahl Nachbarbomben
     */
    public void setNeighbourBombs(int neighbourBombs) {
        this.neighbourBombs = neighbourBombs;
    }

    /**
     * Getter Methode f�r isFlagged
     * @return Gibt isFlagged zur�ck
     */
    public boolean isFlagged() {
        return isFlagged;
    }

    /**
     * Setter Methode f�r isFlagged
     * @param Neuer Status f�r isFlagged
     */
    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }
}
