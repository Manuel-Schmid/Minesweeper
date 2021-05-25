package application;

/**
 * Diese Klasse kann eine Konstante Startzeit speichern
 * und auf Abruf die verstrichene Zeit seit dem Start zur�ckgeben
 * 
 * @author Many, Sven, Lewin
 * @version 1.0
 */

import java.util.Date;

public class Stopwatch {

	/**
	 * Konstante, die die Stwartzeit der Stopuhr speichert
	 */
    private final long startingTime;

    /**
     * Konstruktor f�r die Klasse Stopwatch
     * Speichert die �bergebene Zeit in der Konstanten startingTime
     * @param startingTime Aktueller Timestamp in Millisekunden
     */
	public Stopwatch(long startingTime) {
        this.startingTime = startingTime;
    }

    /**
     * Gibt die verstrichene Zeit seit der Konstruktor dieser Klasse aufgerufen wurde 
     * in einer lesbaren Formatierung zur�ck
     * @return Gibt formatierte verstrichene Zeit zur�ck
     */
    public String getFormattedTime() {
        long timeInSec = (new Date().getTime() - startingTime) / 1000;
        if (timeInSec < 60) return timeInSec + " seconds";
        else return timeInSec/60 + " minutes, " + timeInSec%60 + " seconds";
    }
    
    /**
     * Getter Methode f�r die Startzeit
     * @return Gibt die Startzeit in Millisekunden zur�ck
     */
    public long getStartingTime() {
		return startingTime;
	}

}
