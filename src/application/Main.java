package application;

/**
 * Dies ist die Main Klasse des Minesweeper-Programmes. 
 * In ihr werden die meisten User-Interaktionen des Programmes gesteuert.
 * 
 * @author Many, Sven, Lewin
 * @version 1.0
 */

import java.util.Scanner;

public class Main {
	
	/**
	 * Erstellt neues Main und initiiert das Spiel.
	 * @param args
	 */
    public static void main(String[] args) {
        System.out.println();
        System.out.println("------------- { Minesweeper } -------------");
        System.out.println();

        new Main().initiateGame();
    }

    /**
	 * Variable game, in der das Spiel gespeichert wird
	 */
    private Game game;
    
    /**
	 * Diese Methode instanziert einen neuer Scanner, 
	 * mit welchem es die Schwierigkeit ausliest, die der Nutzer eingibt. 
	 * Diese Schwierigkeit wird abgespeichert und ausgewertet. 
	 * Die Methode interact() wird ausgeführt.
	 */
    private void initiateGame() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Difficulty ('E' = Easy, 'M' = Medium, 'H' = Hard) : ");
        String difficulty = sc.next().toUpperCase();
        char charDiff = difficulty.charAt(0);
        if (!(charDiff == 'E') && !(charDiff == 'M') && !(charDiff == 'H')) {
            System.out.println("Invalid Option");
            initiateGame();
        } else {
            History.clear();
            game = new Game(charDiff);
            game.printGame();

            interact();
        }
    }

    /**
  	 * Methode, die den Nutzer die Auswahl des Spielzuges tätigen lässt. 
  	 * Sie Leitet je nach Eingabe die entsprechenden Vorgänge ein.
  	 */
    private void interact() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Do you want to show a field (S), flag a field (F), unflag a field (UF), undo your last Move (U) or start a new Game (N) ? : ");
        String action = sc.nextLine().replace(" ", "").toUpperCase();
        if (action.equals("S")) {
            askForCords("S");
        } else if (action.equals("F")) {
            askForCords("F");
        } else if (action.equals("N")) {
            initiateGame();
        } else if (action.equals("UF")) {
            askForCords("UF");
        } else if (action.equals("U")) {
            try {
                game.restore(History.pop());
                if (History.needsReset()) game.setFirstMove(true);
            } catch (Exception e) {
                game.setFirstMove(true);
                System.out.println("No valid last Move");
            }
            interact();
        } else {
            System.out.println("Invalid Option");
            interact();
        }
    }

	/**
	 * Alle Spielzüge, die eine Koordinate benötigen, laufen über diese Methode.
	 * Mit einem Scanner wird vom Spieler die gewünschte Koordinate eingelesen. 
	 * Viele Fehler werden bereits abgefangen, z.B. Länge der Koorinate, etc.
	 * @param mode Indikator für den gewünschten Spielzug, wird von interact() übergeben.
	 */
    private void askForCords(String mode) {
        String action = null;
        if (mode.equals("S")) action = "show";
        else if (mode.equals("F")) action = "flag";
        else if (mode.equals("UF")) action = "unflag";
        Scanner sc = new Scanner(System.in);
        System.out.print("Which Coordinate do you want to "+ action + "? : ");
        try {
            String coordinateInput = sc.next();
            if(coordinateInput.length() <= 3 && coordinateInput.length() >= 2) {
                if (mode.equals("S")) {
                    if (!game.showField(coordinateInput)) {
                    	undoLastMove();
                    }
                } else if (mode.equals("F")) {
                    if (!game.flagField(coordinateInput, true)) {
                    	undoLastMove();
                    }
                } else if (mode.equals("UF")) {
                    if (!game.flagField(coordinateInput, false)) {
                    	undoLastMove();
                    }
                }
            } else {
                System.out.println("Please enter a coordinate of minimum 2 and maximum 3 Characters and use the format: A1");
            }
            interact();
    	} catch (Exception e){
    		System.out.println("Invalid Coordinate");
    		interact();
        }
    }
    
    /**
     * Diese Methode bietet dem Nutzer die Möglichkeit, den letzen Zug rückgängig gemacht werden. 
     * Zuerst wird mithilfe eines Scanners eingelesen, ob der Nutzer diese Rückgängigmachung überhaupt wünscht. 
     * Falls ja, wird mittels der History und eines Stacks der letze Zug rückgängig gemacht. 
     * Falls eitwas anderes als Y/N eingegeben wird, wird nochmals gefragt. 
     */
    private void undoLastMove() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Do you want to undo your last move? (Y/N) : ");
        String undo = sc.next().toUpperCase().replace(" ", "");
        char undoOption = undo.charAt(0);
        if (undoOption == 'Y') {
            try {
                game.restore(History.pop());
                if (History.needsReset()) game.setFirstMove(true);
            } catch (Exception e) {
                game.setFirstMove(true);
                System.out.println("No valid last Move");
            }
            interact();
        }
        else if (undoOption == 'N') initiateGame();
        else {
            System.out.println("Invalid Option");
            undoLastMove();
        }
    }
}
