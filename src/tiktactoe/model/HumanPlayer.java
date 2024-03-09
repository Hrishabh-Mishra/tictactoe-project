package tiktactoe.model;

import java.util.Scanner;

import tiktactoe.utils.Pair;

public class HumanPlayer extends Player{
    public HumanPlayer(final String name, final Symbol symbol) {
        super(name, symbol);
    }

    @Override
    public Pair<Integer, Integer> makeMove(Board board) {
        Scanner sc = new Scanner(System.in);
        System.out.println("It's "+this.getName()+"'s turn. Enter row and column.");
        int row = sc.nextInt();
        int col = sc.nextInt();
        return new Pair<>(row, col);
    }
}
