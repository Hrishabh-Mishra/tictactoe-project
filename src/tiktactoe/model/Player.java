package tiktactoe.model;

import tiktactoe.utils.Pair;

public abstract class Player {
    private String name;
    private Symbol symbol;

    public Player(final String name, final Symbol symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {

        return name;
    }

    public void setName(final String name) {

        this.name = name;
    }

    public Symbol getSymbol() {

        return symbol;
    }

    public void setSymbol(final Symbol symbol) {

        this.symbol = symbol;
    }
    public abstract Pair<Integer, Integer> makeMove();
}
