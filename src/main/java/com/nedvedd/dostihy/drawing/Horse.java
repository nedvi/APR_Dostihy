package com.nedvedd.dostihy.drawing;

import java.awt.*;
import java.awt.geom.Path2D;

/**
 * Trida k vykreslovani kone
 *
 * @author Dominik Nedved
 * @version 24.03.2024
 */
public class Horse {

    private final Color color;

    private final String colorStr;

    private int position;

    private int halfSize;

    /**
     * Konstruktor.
     */
    public Horse(Color color, String colorStr) {
        this.color = color;
        this.colorStr = colorStr;
        this.position = 0;
        this.halfSize = 5;
    }

    public Path2D createHorse() {
        Path2D horse = new Path2D.Double();
        horse.moveTo(halfSize, halfSize);   // pravy dolni roh
        horse.lineTo(-halfSize, halfSize);  // levy dolni roh
        horse.lineTo(-halfSize, halfSize - halfSize / 3.0); // levy vrchol podstavy
        horse.lineTo(-halfSize / 2.0, halfSize - halfSize / 1.5); // "zlom" na zadech
        horse.lineTo(-halfSize, -halfSize / 2.0); // levý roh vrcholu "tela"
        horse.lineTo(-halfSize / 2.0, -halfSize);
        horse.lineTo(-halfSize / 2.0, -halfSize / 1.3);
        horse.lineTo(-halfSize / 6.0, -halfSize / 1.2);

        horse.lineTo(halfSize / 1.3, -halfSize / 2.0); // horni spicka nosu kone
        horse.lineTo(halfSize / 1.5, -halfSize / 8.0); // dolni spicka nosu kone
        horse.lineTo(halfSize / 5.0, -halfSize / 6.0); // krk kone zprava

        horse.lineTo(halfSize / 3.0, halfSize - halfSize / 1.5); // "zlom" na hrudi

        horse.lineTo(halfSize, halfSize - halfSize / 3.0);  // pravy vrchol podstavy
        horse.lineTo(halfSize, halfSize);

        horse.closePath();

        return horse;
    }

    public boolean move(int moveLength, int fieldCount) {
        if (position + moveLength <= fieldCount - 1) {
            this.setPosition(position += moveLength);
            return true;
        } else {
            return false;
        }
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setHalfSize(int halfSize) {
        this.halfSize = halfSize;
    }

    public int getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    public String getColorStr() {
        return colorStr;
    }
}
