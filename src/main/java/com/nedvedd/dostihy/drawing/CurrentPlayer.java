package com.nedvedd.dostihy.drawing;

import com.nedvedd.dostihy.model.DataModel;

import javax.swing.*;
import java.awt.*;

/**
 * Trida k vykreslovani aktualniho hrace
 *
 * @author Dominik Nedved
 * @version 24.03.2024
 */
public class CurrentPlayer extends JPanel {

    private final DataModel dataModel;

    private final int dimensionSize = 50;

    private final int horseHalfLength;

    /**
     * Konstruktor.
     * Inicializace datoveho modelu + uprava preferovanych rozmer JPanelu + nastaveni poloviny sirky vykreslovani kone (kun se kresli od stredu).
     */
    public CurrentPlayer(DataModel dataModel) {
        this.dataModel = dataModel;

        this.setPreferredSize(new Dimension(dimensionSize + 1, dimensionSize + 1));
        this.horseHalfLength = dimensionSize / 2;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;

        drawCurrentHorse(g2);
    }

    public void drawCurrentHorse(Graphics2D g2) {

        g2.translate(horseHalfLength, horseHalfLength);

        Horse currentPlayer = dataModel.getCurrentPlayer();

        currentPlayer.setHalfSize(horseHalfLength);


        g2.setColor(currentPlayer.getColor());
        g2.fill(currentPlayer.createHorse());

        g2.setColor(Color.BLACK);
        g2.draw(currentPlayer.createHorse());
    }
}
