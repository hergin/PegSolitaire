package presentation;

import controller.PegBoardDefaultUIController;
import domain.Location;
import domain.PegBoard;
import domain.PegBoardCellEnum;
import domain.PegBoardUpdateListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

public class PegBoardUIViewer extends JFrame implements PegBoardUpdateListener {

    private PegBoard theBoard;

    private HashMap<Location, JRadioButton> allButtons;
    private JTextArea theLoggingArea;

    private PegBoardDefaultUIController controller;

    public PegBoardUIViewer(PegBoard aBoard) {
        this.theBoard = aBoard;
        this.allButtons = new HashMap<>();
        this.controller = new PegBoardDefaultUIController(aBoard, allButtons);
        setSize(900, 300);
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
                    radioButtonWithPeg.addMouseListener(controller);
                    radioButtonWithPeg.setSelected(true);
                    radioButtonWithPeg.setEnabled(false);
                    allButtons.put(theLocation, radioButtonWithPeg);
                    add(radioButtonWithPeg, constraints);
                } else if (theBoard.isBoardEmptyOn(theLocation)) {
                    var radioButtonEmpty = new JRadioButton();
                    radioButtonEmpty.addMouseListener(controller);
                    radioButtonEmpty.setEnabled(false);
                    allButtons.put(theLocation, radioButtonEmpty);
                    add(radioButtonEmpty, constraints);
                }
            }
        }

        theLoggingArea = new JTextArea(10, 50);
        theLoggingArea.setEditable(false);
        var loggingPane = new JScrollPane(theLoggingArea);
        loggingPane.setBounds(10, 60, 780, 500);
        loggingPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        constraints.gridx = 7;
        constraints.gridy = 0;
        constraints.gridwidth = 50;
        constraints.gridheight = 20;
        constraints.insets = new Insets(0, 50, 0, 0);
        add(loggingPane, constraints);
        theLoggingArea.append("MOVEMENTS:\n");

        setVisible(true);
    }

    @Override
    public void onPegBoardUpdated(Location location, PegBoardCellEnum newValue) {
        if (newValue == PegBoardCellEnum.EMPTY) {
            allButtons.get(location).setSelected(false);
        } else if (newValue == PegBoardCellEnum.PEG) {
            allButtons.get(location).setSelected(true);
        }
        theLoggingArea.append("New value of location" + location.toString() + ": " + newValue + "\n");
    }
}
