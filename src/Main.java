import domain.PegBoard;
import presentation.PegBoardController;
import presentation.PegBoardUIViewer;
import presentation.PegBoardViewer;

public class Main {

    public static void main(String[] args) {

        PegBoard theBoard = new PegBoard();
        PegBoardViewer theViewer = new PegBoardViewer(theBoard);
        PegBoardController theController = new PegBoardController(theBoard);

        while (true) {
            theViewer.displayBoard();
            theController.askForPegToMove();
        }
    }

}
