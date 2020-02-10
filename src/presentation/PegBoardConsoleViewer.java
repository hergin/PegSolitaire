package presentation;

import domain.Location;
import domain.PegBoard;
import domain.PegBoardCellEnum;
import domain.PegBoardUpdateListener;

public class PegBoardConsoleViewer implements PegBoardUpdateListener {
    private PegBoard theBoard;

    public PegBoardConsoleViewer(PegBoard aBoard) {
        this.theBoard = aBoard;
    }

    public void displayBoard() {
        System.out.println("  0 1 2 3 4 5 6");
        for (int i = 0; i < theBoard.getBoard().length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < theBoard.getBoard()[i].length; j++) {
                System.out.print(cellValueToString(theBoard.getBoard()[i][j]) + " ");
            }
            System.out.println();
        }
    }

    private String cellValueToString(PegBoardCellEnum pegBoardCellEnum) {
        switch (pegBoardCellEnum) {
            case EMPTY:
                return "o";
            case PEG:
                return "X";
            default:
                return " ";
        }

    }

    @Override
    public void onPegBoardUpdated(Location location, PegBoardCellEnum newValue) {
        displayBoard();
    }
}
