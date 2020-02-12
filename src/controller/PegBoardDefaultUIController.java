package controller;

import domain.Location;
import domain.PegBoard;

import javax.swing.*;
import javax.swing.border.StrokeBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

public class PegBoardDefaultUIController implements MouseListener {

    PegBoard theBoard;
    HashMap<Location, JRadioButton> locationButtonMap;
    boolean firstButtonClicked = false;
    JComponent firstButton;

    public PegBoardDefaultUIController(PegBoard theBoard, HashMap<Location, JRadioButton> locationButtonMap) {
        this.theBoard = theBoard;
        this.locationButtonMap = locationButtonMap;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!firstButtonClicked) {
            firstButton = ((JComponent) e.getSource());
            firstButton.setBackground(Color.RED);
            firstButtonClicked = true;
        } else {
            var firstLocation = locationButtonMap.entrySet().stream().filter(en -> en.getValue().equals(firstButton)).findFirst().get().getKey();
            var secondLocation = locationButtonMap.entrySet().stream().filter(en -> en.getValue().equals(((JComponent) e.getSource()))).findFirst().get().getKey();
            theBoard.movePeg(firstLocation, secondLocation);
            firstButtonClicked = false;
            firstButton.setBackground(null);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
