package domain;

import java.util.ArrayList;
import java.util.List;

public class PegBoard {

    public static final int BOARD_LENGTH = 7;

    private PegBoardCellEnum[][] board;

    public PegBoard() {
        String[] initializer = new String[]{
                "UUPPPUU",
                "UUPPPUU",
                "PPPPPPP",
                "PPPEPPP",
                "PPPPPPP",
                "UUPPPUU",
                "UUPPPUU"};

        board = new PegBoardCellEnum[BOARD_LENGTH][BOARD_LENGTH];

        for (int i = 0; i < initializer.length; i++) {
            for (int j = 0; j < initializer[i].length(); j++) {
                board[i][j] = convertLetterToEnum(initializer[i].charAt(j));
            }
        }
    }

    private PegBoardCellEnum convertLetterToEnum(Character letter) {
        switch (letter) {
            case 'U':
                return PegBoardCellEnum.UNUSED;
            case 'P':
                return PegBoardCellEnum.PEG;
            default:
                return PegBoardCellEnum.EMPTY;
        }
    }

    public PegBoardCellEnum[][] getBoard() {
        return board;
    }

    public boolean movePeg(Location from, Location to) {
        if (isTherePegOn(from) && isBoardEmptyOn(to)) {
            if (canPegMove(from, to)) {
                board[from.getX()][from.getY()] = PegBoardCellEnum.EMPTY;
                board[(from.getX() + to.getX()) / 2][(from.getY() + to.getY()) / 2] = PegBoardCellEnum.EMPTY;
                board[to.getX()][to.getY()] = PegBoardCellEnum.PEG;
                return true;
            }
        }
        return false;
    }

    public boolean canPegMove(Location from, Location to) {
        if (!isBoardEmptyOn(to))
            return false;
        if (from.getX() == to.getX()) {
            if (Math.abs(from.getY() - to.getY()) == 2 && isTherePegOn(new Location(from.getX(), (from.getY() + to.getY()) / 2))) {
                return true;
            }
        }
        if (from.getY() == to.getY()) {
            if (Math.abs(from.getX() - to.getX()) == 2 && isTherePegOn(new Location((from.getX() + to.getX()) / 2, from.getY()))) {
                return true;
            }
        }
        return false;
    }

    public boolean isBoardEmptyOn(Location location) {
        if (board[location.getX()][location.getY()] == PegBoardCellEnum.EMPTY) {
            return true;
        }
        return false;
    }

    public boolean isTherePegOn(Location location) {
        if (board[location.getX()][location.getY()] == PegBoardCellEnum.PEG) {
            return true;
        }
        return false;
    }

}
