package application;

/**
 * Diese Klasse kann eine Konstante Startzeit speichern
 * und auf Abruf die verstrichene Zeit seit dem Start zurückgeben
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
     * Konstruktor für die Klasse Stopwatch
     * Speichert die übergebene Zeit in der Konstanten startingTime
     * @param startingTime Aktueller Timestamp in Millisekunden
     */
	public Stopwatch(long startingTime) {
        this.startingTime = startingTime;
    }

    /**
     * Gibt die verstrichene Zeit seit der Konstruktor dieser Klasse aufgerufen wurde 
     * in einer lesbaren Formatierung zurück
     * @return Gibt formatierte verstrichene Zeit zurück
     */
    public String getFormattedTime() {
        long timeInSec = (new Date().getTime() - startingTime) / 1000;
        if (timeInSec < 60) return timeInSec + " seconds";
        else return timeInSec/60 + " minutes, " + timeInSec%60 + " seconds";
    }
    
    /**
     * Getter Methode für die Startzeit
     * @return Gibt die Startzeit in Millisekunden zurück
     */
    public long getStartingTime() {
		return startingTime;
	}

}
