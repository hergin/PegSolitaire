package presentation;

import domain.Location;
import domain.PegBoard;
import domain.PegBoardCellEnum;
import domain.PegBoardUpdateListener;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class PegBoardImageUIViewer extends JFrame implements PegBoardUpdateListener {

    private PegBoard theBoard;

    private HashMap<Location, JLabel> allLabels;

    private final ImageIcon pegIcon = new ImageIcon(getClass().getClassLoader().getResource("peg.png")),
            emptyIcon = new ImageIcon(getClass().getClassLoader().getResource("empty.png"));

    public PegBoardImageUIViewer(PegBoard aBoard) {
        this.theBoard = aBoard;
        this.allLabels = new HashMap<>();
        setSize(400, 400);
        setAlwaysOnTop(true);
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        for (int i = 0; i < PegBoard.BOARD_LENGTH; i++) {
            for (int j = 0; j < PegBoard.BOARD_LENGTH; j++) {
                var theLocation = new Location(i, j);
                constraints.gridx = j;
                constraints.gridy = i; // These locations are reverse because the GridBagLayout and board X/Y coordinates are reverse.
                if (theBoard.isTherePegOn(theLocation)) {
                    var pegLabel = new JLabel(pegIcon);
                    allLabels.put(theLocation, pegLabel);
                    add(pegLabel, constraints);
                } else if (theBoard.isBoardEmptyOn(theLocation)) {
                    var emptyLabel = new JLabel(emptyIcon);
                    allLabels.put(theLocation, emptyLabel);
                    add(emptyLabel, constraints);
                }
            }
        }

        setVisible(true);
    }

    @Override
    public void onPegBoardUpdated(Location location, PegBoardCellEnum newValue) {
        if (newValue == PegBoardCellEnum.EMPTY) {
            allLabels.get(location).setIcon(emptyIcon);
        } else if (newValue == PegBoardCellEnum.PEG) {
            allLabels.get(location).setIcon(pegIcon);
        }
    }

}
