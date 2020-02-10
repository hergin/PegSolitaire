import domain.PegBoard;
import presentation.PegBoardController;
import presentation.PegBoardConsoleViewer;
import presentation.PegBoardUIViewer;

public class Main {

    public static void main(String[] args) {

        PegBoard theBoard = new PegBoard();
        PegBoardConsoleViewer theViewer = new PegBoardConsoleViewer(theBoard);
        PegBoardController theController = new PegBoardController(theBoard);

        theBoard.attachSubscriber(theViewer);
        theViewer.displayBoard();

        PegBoardUIViewer theUIViewer = new PegBoardUIViewer(theBoard);
        theBoard.attachSubscriber(theUIViewer);

        while (true) {
            theController.askForPegToMove();
        }
    }

}
