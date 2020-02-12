import domain.PegBoard;
import controller.PegBoardConsoleController;
import presentation.PegBoardConsoleViewer;
import presentation.PegBoardImageUIViewer;
import presentation.PegBoardUIViewer;

public class Main {

    public static void main(String[] args) {

        PegBoard theBoard = new PegBoard();
        PegBoardConsoleViewer theViewer = new PegBoardConsoleViewer(theBoard);
        PegBoardConsoleController theController = new PegBoardConsoleController(theBoard);

        theBoard.attachSubscriber(theViewer);
        theViewer.displayBoard();

        PegBoardImageUIViewer theImageUIViwer = new PegBoardImageUIViewer(theBoard);
        theBoard.attachSubscriber(theImageUIViwer);

        while (true) {
            theController.askForPegToMove();
        }
    }

}
