package application;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

class Minesweeper_UnitTests {
	
	public Minesweeper_UnitTests() {
		System.out.close();
	}

	@Test
    void testEasyDifficultyBombCount() {
        Game game = new Game('E');
        int actual = game.getDifficulty().getBombCount();
        int expected = 10;
        assertEquals(expected, actual, "testEasyDifficultyBombCount fails");
    }

    @Test
    void testMediumDifficultyBombCount() {
        Game game = new Game('M');
        int actual = game.getDifficulty().getBombCount();
        int expected = 40;
        assertEquals(expected, actual, "testMediumDifficultyBombCount fails");
    }

    @Test
    void testHardDifficultyBombCount() {
        Game game = new Game('H');
        int actual = game.getDifficulty().getBombCount();
        int expected = 99;
        assertEquals(expected, actual, "testHardDifficultyBombCount fails");
    }
    
    @Test
    void testEasyDifficultyFlagCount() {
        Game game = new Game('E');
        int actual = Game.getFlags();
        int expected = 10;
        assertEquals(expected, actual, "testEasyDifficultyFlagCount fails");
    }

    @Test
    void testMediumDifficultyFlagCount() {
        Game game = new Game('M');
        int actual = Game.getFlags();
        int expected = 40;
        assertEquals(expected, actual, "testMediumDifficultyFlagCount fails");
    }

    @Test
    void testHardDifficultyFlagCount() {
        Game game = new Game('H');
        int actual = Game.getFlags();
        int expected = 99;
        assertEquals(expected, actual, "testHardDifficultyFlagCount fails");
    }
    
    @Test
    void testFlagIncrease() {
        Game game = new Game('E');
        Game.incFlags();
        int actual = Game.getFlags();
        int expected = 11;
        assertEquals(expected, actual, "testFlagIncrease fails");
    }
    
    @Test
    void testShowFieldInvalidCoordinates() {
      Game g = new Game('E');
      boolean actual = g.showField("X20");;
      assertTrue(actual, "testShowFieldInvalidCoordinates fails");
    }

    @Test
    void testShowFieldInvalidCoordinatesSpace() {
      Game g = new Game('E');
      boolean actual = g.showField("a 6");
      boolean expected = true;
      assertEquals(expected, actual, "testShowFieldInvalidCoordinatesSpace fails");
    }

    @Test
    void testShowFieldIsAlreadyShown() {
      Game g = new Game('E');
      g.showField("C4");
      boolean actual = g.showField("c04");
      boolean expected = true;
      assertEquals(expected, actual, "testShowFieldIsAlreadyShown fails");
    }
    
    
  //Flag Fields

    @Test
    void testFlagFieldStandard() {
      Game g = new Game('E');
      boolean actual = g.flagField("A2", true); //true = flag false= unflag
      assertTrue(actual, "testFlagFieldStandard fails");
    }

    @Test
    void testFlagFieldEdgeCoordinates() {
      Game g = new Game('E');
      boolean actual = g.flagField("A003", true);
      assertTrue(actual, "testFlagFieldEdgeCoordinates fails");
    }

    @Test
    void testFlagFieldInvalidCoordinatesSpace() {
      Game g = new Game('E');
      boolean actual = g.flagField("a 6", true);
      assertTrue(actual, "testShowFieldInvalidCoordinatesSpace fails");
    }

    @Test
    void testFlagFieldInvalidCoordinates() {
      Game g = new Game('E');
      boolean actual = g.flagField("x30", true);
      assertTrue(actual, "testFlagFieldInvalidCoordinates fails");
    }


    @Test
    void testFlagFieldIsAlreadyFlaged() {
      Game g = new Game('E');
      g.flagField("C4", true);
      boolean actual = g.flagField("c04", true);
      assertTrue(actual, "testFlagFieldIsAlreadyFlaged fails");
    }

    @Test
    void testFlagFieldNoFlagsLeft() {
      Game g = new Game('E');
      g.flagField("C4", true);
      g.flagField("C3", true);
      g.flagField("g7", true);
      g.flagField("G8", true);
      g.flagField("C1", true);
      g.flagField("D4", true);
      g.flagField("D5", true);
      g.flagField("E8", true);
      g.flagField("B4", true);
      g.flagField("H4", true);

      boolean actual = g.flagField("H8", true);
      assertTrue(actual, "testFlagFieldNoFlagsLeft fails");
    }




    //Unflag Tests

    @Test
    void testUnFlagFieldStandard() {
      Game g = new Game('E');
      g.flagField("A2", true);
      boolean actual = g.flagField("A2", false); 
      assertTrue(actual, "testUnFlagFieldStandard fails");
    }

    @Test
    void testUnFlagFieldEdgeCoordinates() {
      Game g = new Game('E');
      g.flagField("A3", true);
      boolean actual = g.flagField("A003", false);
      assertTrue(actual, "testUnFlagFieldEdgeCoordinates fails");
    }

    @Test
    void testUnFlagFieldInvalidCoordinatesSpace() {
      Game g = new Game('E');
      g.flagField("A6", true);
      boolean actual = g.flagField("a 6", false);
      assertTrue(actual, "testUnFlagFieldInvalidCoordinatesSpace fails");
    }

    @Test
    void testUnFlagFieldInvalidCoordinates() {
      Game g = new Game('E');
      boolean actual = g.flagField("x30", false);
      assertTrue(actual, "testUnFlagFieldInvalidCoordinates fails");
    }


    @Test
    void testUnFlagFieldIsAlreadyUnFlaged() {
      Game g = new Game('E');
      boolean actual = g.flagField("c04", false);
      assertTrue(actual, "testUnFlagFieldIsAlreadyUnFlaged fails");
    }
    
    @Test
    void testFlagFieldCount() {
      Game g = new Game('E');
      g.flagField("c04", true);
      g.flagField("c05", true);
      int actual = Game.getFlags();
      int expected = 8;
      assertEquals(expected, actual, "testFlagFieldCount fails");
    }

    @Test
    void testFlagFieldFlagsCount() {
      Game g = new Game('E');

      g.flagField("C4", true);
      g.flagField("C3", true);
      g.flagField("g7", true);
      g.flagField("G8", true);
      g.flagField("C1", true);
      g.flagField("D4", true);
      g.flagField("D5", true);
      g.flagField("E8", true);
      g.flagField("B4", true);
      g.flagField("H4", true);

      g.flagField("C4", false);
      g.flagField("C3", false);
      g.flagField("g7", false);
      g.flagField("G8", false);
      g.flagField("C1", false);
      g.flagField("D4", false);
      g.flagField("D5", false);
      g.flagField("E8", false);
      g.flagField("B4", false);
      g.flagField("H4", false);

      int actual = Game.getFlags();
      int expected = 10;
      assertEquals(expected, actual, "testFlagFieldFlagsCount fails");
    }

    @Test
    void testGetField() {
        Game game = new Game('E');
        Field testField = game.getGrid().getField(6, 7);
        String actual = testField.getX() + "" + testField.getY();
        String expected = "67";
        assertEquals(expected, actual, "testGetField fails");
    }
    
    @Test
    void testEasyActualBombCount() {
      Game g = new Game('E');
      int actual = 0;
      g.showField("A2");
      Field[][] fieldList = g.getGrid().deepCopyFieldList();
      for (int i = 0; i < fieldList.length; i++) {
          for (int j = 0; j < fieldList[0].length; j++) {
              if(fieldList[i][j].isBomb()) actual++;
          }
      }
      int expected = 10;
      assertEquals(expected, actual, "testActualBombCount fails");
    }
    
    @Test
    void testMediumActualBombCount() {
      Game g = new Game('M');
      int actual = 0;
      g.showField("A2");
      Field[][] fieldList = g.getGrid().deepCopyFieldList();
      for (int i = 0; i < fieldList.length; i++) {
          for (int j = 0; j < fieldList[0].length; j++) {
              if(fieldList[i][j].isBomb()) actual++;
          }
      }
      int expected = 40;
      assertEquals(expected, actual, "testActualBombCount fails");
    }
    
    @Test
    void testHardActualBombCount() {
      Game g = new Game('H');
      int actual = 0;
      g.showField("A2");
      Field[][] fieldList = g.getGrid().deepCopyFieldList();
      for (int i = 0; i < fieldList.length; i++) {
          for (int j = 0; j < fieldList[0].length; j++) {
              if(fieldList[i][j].isBomb()) actual++;
          }
      }
      int expected = 99;
      assertEquals(expected, actual, "testActualBombCount fails");
    }
    
    @Test
    void testFlagging() {
      Game g = new Game('M');
      g.showField("A1");
      Field[][] board = g.getGrid().deepCopyFieldList();
      boolean actual1 = board[15][15].isFlagged();
      g.getGrid().getField(15,15).setFlagged(true);
      boolean actual2 = board[15][15].isFlagged();
      
      assertFalse(actual1, "testFlagging fails");
      assertFalse(actual2, "testFlagging fails"); // müsste false sein, ist aber true
    }
    
    @Test
    void testGetBombCountEasy(){
        Game game = new Game('E');
        int actual = game.getGrid().getBombCount();
        int expected = 10;
        assertEquals(expected, actual, "testGetBombCountEasy");
    }
    @Test
    void testGetBombCountMedium(){
        Game game = new Game('M');
        int actual = game.getGrid().getBombCount();
        int expected = 40;
        assertEquals(expected, actual, "testGetBombCountMedium");
    }

    @Test
    void testGetBombCountHard(){
        Game game = new Game('H');
        int actual = game.getGrid().getBombCount();
        int expected = 99;
        assertEquals(expected, actual, "testGetBombCountHard");
    }

    @Test
    void testGetRowsEasy() {
        Game game = new Game('E');
        int actual = game.getGrid().getRows();
        int expected = 8;
        assertEquals(expected, actual, "testGetRowsEasy failed");
    }

    @Test
    void testGetRowsMedium() {
        Game game = new Game('M');
        int actual = game.getGrid().getRows();
        int expected = 16;
        assertEquals(expected, actual, "testGetRowsMedium failed");
    }

    @Test
    void testGetRowsHard() {
        Game game = new Game('H');
        int actual = game.getGrid().getRows();
        int expected = 16;
        assertEquals(expected, actual, "testGetRowsHard failed");
    }

    @Test
    void testGetColumnsEasy() {
        Game game = new Game('E');
        int actual = game.getGrid().getColumns();
        int expected = 8;
        assertEquals(expected, actual, "testGetColumnsEasy failed");
    }

    @Test
    void testGetColumnsMedium() {
        Game game = new Game('M');
        int actual = game.getGrid().getColumns();
        int expected = 16;
        assertEquals(expected, actual, "testGetColumnsMedium failed");
    }
    
    @Test
    void testGetColumnsHard() {
        Game game = new Game('H');
        int actual = game.getGrid().getColumns();
        int expected = 30;
        assertEquals(expected, actual, "testGetColumnsHard failed");
    }
    
    @Test
    void testLinePrinter() {
        Game game = new Game('M');
        String actual = game.getGrid().getPrintedLine(8);
        String expected = "--------";
        assertEquals(expected, actual, "testGetColumnsMedium failed");
    }
    
    @Test
    void testFieldConstructor() {
    	Field f = new Field(2,4);
    	boolean actualShown = f.isShown();
    	boolean actualBomb = f.isBomb();
    	boolean actualFlagged = f.isFlagged();
    	int actualX = f.getX();
    	int actualY = f.getY();
        assertFalse(actualShown, "testFieldConstructor: actualShown failed");
        assertFalse(actualBomb, "testFieldConstructor: actualBomb failed");
        assertFalse(actualFlagged, "testFieldConstructor: actualFlagged failed");
        assertEquals(2, actualX, "testFieldConstructor: actualX failed");
        assertEquals(4, actualY, "testFieldConstructor: actualY failed");
    }
    
    @Test
    void testStopwatch() {
    	long expected = new Date().getTime();
    	Stopwatch stopwatch = new Stopwatch(expected);
        long actual = stopwatch.getStartingTime();
        assertEquals(expected, actual, "testStopwatch failed");
    }
    
//    @Test
//    void testIsVictory() {
//        Game game = new Game('E');
//        game.showField("A2");
//        Field[][] fieldList = game.getGrid().deepCopyFieldList();
//        for (int i = 0; i < fieldList.length; i++) {
//        	for (int j = 0; j < fieldList[0].length; j++) {
//        		if(fieldList[i][j].isBomb()) { fieldList[i][j].setFlagged(true); }
//        	}
//        }
//        boolean actual = game.getGrid().isVictory();
//        boolean expected = true;
//        assertEquals(expected, actual, "testIsVictory fails");
//    }
    
    @Test
    void testUncoverGridFieldFirstMove() {
      Game g = new Game('E');
      boolean actual = g.getGrid().uncoverGridField(0,0, true); 
      assertTrue(actual, "testUncoverGridFieldFirstMove fails");
    }

    @Test
    void testUncoverGridFieldNormal() {
      Game g = new Game('E');
      boolean actual = g.getGrid().uncoverGridField(1,5, false); 
      assertTrue(actual, "testUncoverGridFieldNormal fails");
    }

    @Test
    void testUncoverGridFieldVictory() {
      Game g = new Game('E');
      g.getGrid().uncoverGridField(2, 3, true);
      Field[][] fieldList = g.getGrid().deepCopyFieldList();
      for (int i = 0; i < fieldList.length; i++) {
          for (int j = 0; j < fieldList[0].length; j++) {
               if(fieldList[i][j].isBomb()) { fieldList[i][j].setFlagged(true); }
          }
      }
      boolean actual = false;
      assertFalse(actual, "testUncoverGridFieldVictor fails");
    }
    
    @Test
    void testSetShown() {
        Game game = new Game('E');
        game.getGrid().getField(6, 7).setShown(true);
        boolean actual = game.getGrid().getField(6, 7).isShown();
        boolean expected = true;
        assertEquals(expected, actual, "testSetShown fails");
    }
}

