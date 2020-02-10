package presentation;

import domain.Location;
import domain.PegBoard;

import java.util.Arrays;
import java.util.Scanner;

public class PegBoardController {

    PegBoard theBoard;

    public PegBoardController(PegBoard aBoard) {
        this.theBoard = aBoard;
    }

    public void askForPegToMove() {
        System.out.println("Enter a peg location (two integers separated by a space):");
        Scanner console = new Scanner(System.in);
        int Y = console.nextInt();
        int X = console.nextInt();
        if (X < 0 || X > 8 || Y < 0 || Y > 8) {
            System.out.println("Numbers are out of limits, please try again!");
            return;
        }
        Location pegLocation = new Location(X, Y);

        System.out.println("Enter an empty location to move the peg (two integers separated by a space):");
        Y = console.nextInt();
        X = console.nextInt();
        if (X < 0 || X > 8 || Y < 0 || Y > 8) {
            System.out.println("Numbers are out of limits, please try again!");
            return;
        }
        Location emptyLocation = new Location(X, Y);

        if (!theBoard.movePeg(pegLocation, emptyLocation)) {
            System.out.println("Something went wrong!");
        }
    }
}
