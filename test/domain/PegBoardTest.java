package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PegBoardTest {

    PegBoard theBoard;

    @BeforeEach
    void createInitialBoard() {
        theBoard = new PegBoard();
    }

    @Test
    void initialBoardIsCreatedWithEmptyCenter() {
        assertEquals(PegBoardCellEnum.EMPTY, theBoard.getBoard()[PegBoard.BOARD_LENGTH / 2][PegBoard.BOARD_LENGTH / 2]);
        assertEquals(PegBoardCellEnum.PEG, theBoard.getBoard()[5][2]);
        assertEquals(PegBoardCellEnum.UNUSED, theBoard.getBoard()[0][0]);
    }

    @Test
    void movePegFrom6_4to4_4succesfully() {
        assertTrue(theBoard.movePeg(new Location(5, 3), new Location(3, 3)));
        assertTrue(theBoard.isBoardEmptyOn(new Location(5, 3)));
        assertTrue(theBoard.isBoardEmptyOn(new Location(4, 3)));
        assertFalse(theBoard.isBoardEmptyOn(new Location(3, 3)));
        assertTrue(theBoard.isTherePegOn(new Location(3, 3)));
    }

    @Test
    void pegsMoveToOtherCellsEmptyAnd2levelAround() {
        assertTrue(theBoard.canPegMove(new Location(5, 3), new Location(3, 3)));
        assertTrue(theBoard.canPegMove(new Location(3, 5), new Location(3, 3)));
        assertTrue(theBoard.canPegMove(new Location(1, 3), new Location(3, 3)));
        assertTrue(theBoard.canPegMove(new Location(3, 1), new Location(3, 3)));
    }

    @Test
    void pegsCantMoveToNonEmptyCells() {
        assertFalse(theBoard.canPegMove(new Location(5, 4), new Location(3, 4)));
    }

    @Test
    void boardShouldBeEmptyInTheCenter() {
        assertTrue(theBoard.isBoardEmptyOn(new Location(3, 3)));
    }

    @Test
    void boardShouldNOTBeEmptyOn5_4() {
        assertFalse(theBoard.isBoardEmptyOn(new Location(5, 4)));
    }

    @Test
    void thereIsAPegOn5_4() {
        assertTrue(theBoard.isTherePegOn(new Location(5, 4)));
    }
}