package application;

public interface Difficulty {
	
	/**
	 * Methode, die die Grösse des Grids als Liste zurückgibt
	 * @return Integer-Array, an erster Stelle steht die X- und an zweiter Stelle die Y-Koordinate
	 */
    int[] getGridSize();
    
	/**
	 * Methode, die die Anzahl Bomben zurückgibt
	 * @return Anzahl Bomben
	 */
    int getBombCount();
}

class Easy implements Difficulty {
    @Override
    public int[] getGridSize() {
        return new int[] {8, 8};
    }
    @Override
    public int getBombCount() {
        return 10;
    }
}

class Medium implements Difficulty {
    @Override
    public int[] getGridSize() {
        return new int[] {16, 16};
    }
    @Override
    public int getBombCount() {
        return 40;
    }
}

class Hard implements Difficulty {
    @Override
    public int[] getGridSize() {
        return new int[] {30, 16};
    }
    @Override
    public int getBombCount() {
        return 99;
    }
}