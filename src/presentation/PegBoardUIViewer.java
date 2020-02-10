package presentation;

import domain.Location;
import domain.PegBoard;
import domain.PegBoardCellEnum;
import domain.PegBoardUpdateListener;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class PegBoardUIViewer extends JFrame implements PegBoardUpdateListener {

    private PegBoard theBoard;

    private HashMap<Location, JRadioButton> allButtons;

    public PegBoardUIViewer(PegBoard aBoard) {
        this.theBoard = aBoard;
        this.allButtons = new HashMap<>();
        setSize(300, 300);
        setAlwaysOnTop(true);
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        for (int i = 0; i < PegBoard.BOARD_LENGTH; i++) {
            for (int j = 0; j < PegBoard.BOARD_LENGTH; j++) {
                var theLocation = new Location(i, j);
                constraints.gridx = j;
                constraints.gridy = i; // These locations are reverse because the GridBagLayout and board X/Y coordinates are reverse.
                if (theBoard.isTherePegOn(theLocation)) {
                    var radioButtonWithPeg = new JRadioButton();
                    radioButtonWithPeg.setSelected(true);
                    radioButtonWithPeg.setEnabled(false);
                    allButtons.put(theLocation, radioButtonWithPeg);
                    add(radioButtonWithPeg, constraints);
                } else if (theBoard.isBoardEmptyOn(theLocation)) {
                    var radioButtonEmpty = new JRadioButton();
                    radioButtonEmpty.setEnabled(false);
                    allButtons.put(theLocation, radioButtonEmpty);
                    add(radioButtonEmpty, constraints);
                }
            }
        }

        setVisible(true);
    }

    @Override
    public void onPegBoardUpdated(Location location, PegBoardCellEnum newValue) {
        if (newValue == PegBoardCellEnum.EMPTY) {
            allButtons.get(location).setSelected(false);
        } else if (newValue == PegBoardCellEnum.PEG) {
            allButtons.get(location).setSelected(true);
        }
    }
}
