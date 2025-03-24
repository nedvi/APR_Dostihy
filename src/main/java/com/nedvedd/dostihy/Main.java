package com.nedvedd.dostihy;

import com.nedvedd.dostihy.drawing.CurrentPlayer;
import com.nedvedd.dostihy.drawing.Dice;
import com.nedvedd.dostihy.drawing.Board;
import com.nedvedd.dostihy.model.DataModel;

import javax.swing.*;
import java.awt.*;

/**
 * Spousteci trida programu Dostihy.
 *
 * @author Dominik Nedved
 * @version 24.03.2024
 */
public class Main {

    private static JFrame mainFrame;

    private static DataModel dataModel;

    private static Board board;

    private static Dice dice;

    private static CurrentPlayer currentPlayerPanel;

    private static JLabel currentRollLabel;

    private static int currentPlayerID = 1;

    /**
     * Sposteci metoda programu
     *
     * @param args parametry prikazove radky
     */
    public static void main(String[] args) {

        mainFrame = new JFrame();

        mainFrame.setTitle("APR – Dostihy (nedvedd, IT2)");
        mainFrame.setMinimumSize(new Dimension(480, 480));

        dataModel = new DataModel();
        dataModel.setCurrentPlayer(dataModel.getPlayers().get(currentPlayerID));

        board = new Board(dataModel);

        currentPlayerPanel = new CurrentPlayer(dataModel);

        JPanel diceSection = new JPanel();

        JButton diceRollBTN = new JButton("Hodit kostkou");
        dice = new Dice(dataModel);
        currentRollLabel = new JLabel("Hod: " + dataModel.getCurrentRoll());

        diceSection.add(diceRollBTN, BorderLayout.WEST);
        diceSection.add(dice, BorderLayout.CENTER);
        diceSection.add(currentRollLabel, BorderLayout.EAST);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(currentPlayerPanel, BorderLayout.EAST);
        bottomPanel.add(diceSection, BorderLayout.CENTER);

        mainFrame.add(board, BorderLayout.CENTER);
        mainFrame.add(bottomPanel, BorderLayout.SOUTH);

        diceRollBTN.addActionListener(e -> updateDiceRoll());

        mainFrame.pack(); // Udela resize okna dle komponent
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null); // Vycentrovani okna na obrazovce
        mainFrame.setVisible(true);
    }

    private static void updateDiceRoll() {
        dataModel.rollTheDice();
        boolean validMove = dataModel.getCurrentPlayer().move(dataModel.getCurrentRoll(), dataModel.getFieldCount());

        dice.repaint();
        board.repaint();
        currentRollLabel.setText("Hod: " + dataModel.getCurrentRoll());

        if (validMove && checkWin()) {
            showWinMessage();
        } else {
            if (currentPlayerID == 4) {
                currentPlayerID = 1;
            } else {
                currentPlayerID++;
            }
            dataModel.setCurrentPlayer(dataModel.getPlayers().get(currentPlayerID));
            currentPlayerPanel.repaint();
        }
    }

    private static boolean checkWin() {
        return dataModel.getCurrentPlayer().getPosition() == dataModel.getFieldCount() - 1;
    }

    private static void showWinMessage() {
        if (dataModel.getCurrentPlayer().getPosition() == dataModel.getFieldCount() - 1) {
            JOptionPane.showMessageDialog(mainFrame, String.format("Vyhrává hráč č. %d (%s)!", currentPlayerID, dataModel.getCurrentPlayer().getColorStr()));
        }
    }
}
