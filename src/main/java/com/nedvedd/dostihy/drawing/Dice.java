package com.nedvedd.dostihy.drawing;

import com.nedvedd.dostihy.model.DataModel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * Trida k vykreslovani hraci kostky
 *
 * @author Dominik Nedved
 * @version 24.03.2024
 */
public class Dice extends JPanel {

    private final DataModel dataModel;

    private final int rectangleLength = 100;

    /**
     * Konstruktor.
     * Upravuje se pouze preferovany rozmer JPanelu
     */
    public Dice(DataModel dataModel) {
        this.dataModel = dataModel;

        this.setPreferredSize(new Dimension(rectangleLength + 1, rectangleLength + 1));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;

        drawDice(g2);
    }

    private void drawDice(Graphics2D g2) {
        g2.setColor(Color.BLACK);

        g2.draw(new Rectangle2D.Double(0, 0, rectangleLength, rectangleLength));

        int r = 5;

        switch (dataModel.getCurrentRoll()) {
            case 1 -> g2.fill(new Ellipse2D.Double(rectangleLength / 2.0 - r, rectangleLength / 2.0 - r, 2 * r, 2 * r));
            case 2 -> {
                g2.fill(new Ellipse2D.Double(rectangleLength / 4.0 - r, rectangleLength / 4.0 - r, 2 * r, 2 * r));
                g2.fill(new Ellipse2D.Double((rectangleLength / 4.0) * 3 - r, (rectangleLength / 4.0) * 3 - r, 2 * r, 2 * r));
            }
            case 3 -> {
                for (int i = 1; i <= 3; i++) {
                    g2.fill(new Ellipse2D.Double((rectangleLength / 4.0) * i - r, (rectangleLength / 4.0) * i - r, 2 * r, 2 * r));
                }
            }
            case 4 -> {
                g2.fill(new Ellipse2D.Double(rectangleLength / 4.0 - r, rectangleLength / 4.0 - r, 2 * r, 2 * r));
                g2.fill(new Ellipse2D.Double(rectangleLength / 4.0 - r, (rectangleLength / 4.0) * 3 - r, 2 * r, 2 * r));
                g2.fill(new Ellipse2D.Double((rectangleLength / 4.0) * 3 - r, rectangleLength / 4.0 - r, 2 * r, 2 * r));
                g2.fill(new Ellipse2D.Double((rectangleLength / 4.0) * 3 - r, (rectangleLength / 4.0) * 3 - r, 2 * r, 2 * r));
            }
            case 5 -> {
                for (int i = 1; i <= 3; i++) {
                    g2.fill(new Ellipse2D.Double((rectangleLength / 4.0) * i - r, (rectangleLength / 4.0) * i - r, 2 * r, 2 * r));
                }
                g2.fill(new Ellipse2D.Double((rectangleLength / 4.0) * 3 - r, rectangleLength / 4.0 - r, 2 * r, 2 * r));
                g2.fill(new Ellipse2D.Double(rectangleLength / 4.0 - r, (rectangleLength / 4.0) * 3 - r, 2 * r, 2 * r));
            }
            case 6 -> {
                for (int i = 1; i <= 3; i *= 3) {
                    for (int j = 1; j <= 3; j++) {
                        g2.fill(new Ellipse2D.Double((rectangleLength / 4.0) * i - r, (rectangleLength / 4.0) * j - r, 2 * r, 2 * r));
                    }
                }
            }
        }
    }


}
