package tiktactoe.model;

import java.util.Scanner;

import tiktactoe.utils.Pair;

public class HumanPlayer extends Player{
    private int totalAllowedUndo;
    public HumanPlayer(final String name, final Symbol symbol, int totalAllowedUndo) {
        super(name, symbol);
        this.totalAllowedUndo = totalAllowedUndo;
    }

    @Override
    public Pair<Integer, Integer> makeMove(Board board) {
        Scanner sc = new Scanner(System.in);
        System.out.println("It's "+this.getName()+"'s turn. Enter row and column.");
        int row = sc.nextInt();
        int col = sc.nextInt();
        //todo check for row and col should be within board range
        return new Pair<>(row, col);
    }
    public int getTotalAllowedUndo() {

        return totalAllowedUndo;
    }

    public void setTotalAllowedUndo(final int totalAllowedUndo) {

        this.totalAllowedUndo = totalAllowedUndo;
    }
}
