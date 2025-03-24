package com.nedvedd.dostihy.model;

import com.nedvedd.dostihy.drawing.Horse;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Datovy model
 *
 * @author Dominik Nedved
 * @version 24.03.2024
 */
public class DataModel {
    private int currentRoll;

    private int fieldCount;

    private int numberOfPlayers;

    private Map<Integer, Horse> players;

    private Horse currentPlayer;

    public DataModel() {
        initData();
        this.currentPlayer = this.players.get(1);
    }

    private void initData() {
        this.currentRoll = 6;
        this.fieldCount = 50;
        this.numberOfPlayers = 4;

        this.players = new HashMap<>();
        this.players.put(1, new Horse(Color.BLUE, "modrá"));
        this.players.put(2, new Horse(Color.GREEN, "zelená"));
        this.players.put(3, new Horse(Color.YELLOW, "žluá"));
        this.players.put(4, new Horse(Color.RED, "červená"));
    }

    public void rollTheDice() {
        Random random = new Random();
        this.currentRoll = random.nextInt(1, 6);
    }

    public void setCurrentPlayer(Horse currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public int getCurrentRoll() {
        return currentRoll;
    }

    public int getFieldCount() {
        return fieldCount;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public Map<Integer, Horse> getPlayers() {
        return players;
    }

    public Horse getCurrentPlayer() {
        return currentPlayer;
    }
}
