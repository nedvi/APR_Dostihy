package com.nedvedd.dostihy.drawing;

import com.nedvedd.dostihy.model.DataModel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

/**
 * Trida k vykreslovani hraciho pole
 *
 * @author Dominik Nedved
 * @version 24.03.2024
 */
public class Board extends JPanel {

    private final DataModel dataModel;

    /**
     * Konstruktor.
     * Inicializace datoveho modelu + uprava preferovanych rozmer JPanelu.
     */
    public Board(DataModel dataModel) {
        this.dataModel = dataModel;

        this.setPreferredSize(new Dimension(1368, 720));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;

        drawBoard(g2);
        drawHorses(g2);
    }

    private void drawBoard(Graphics2D g2) {
        g2.setColor(Color.BLACK);

        int fieldCount = dataModel.getFieldCount();
        int fieldLength = (this.getWidth() - 1) / fieldCount;
        int fieldHeight = this.getHeight() / dataModel.getNumberOfPlayers();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < fieldCount; j++) {
                g2.draw(new Rectangle2D.Double(j * fieldLength, i * fieldHeight, fieldLength, fieldHeight - 1));
            }
        }
    }

    private void drawHorses(Graphics2D g2) {
        int fieldCount = dataModel.getFieldCount();
        int fieldLength = (this.getWidth() - 1) / fieldCount;
        int fieldHeight = this.getHeight() / dataModel.getNumberOfPlayers();

        for (int i = 0; i < dataModel.getNumberOfPlayers(); i++) {
            Horse currentPlayer = dataModel.getPlayers().get(i + 1);
            int currentPos = currentPlayer.getPosition();

            currentPlayer.setHalfSize(fieldLength / 3);

            AffineTransform old = g2.getTransform();
            g2.translate(fieldLength * currentPos + fieldLength / 2.0, fieldHeight * i + fieldHeight / 2.0);

            g2.setColor(currentPlayer.getColor());
            g2.fill(currentPlayer.createHorse());

            g2.setColor(Color.BLACK);
            g2.draw(currentPlayer.createHorse());

            g2.setTransform(old);
        }
    }

}
