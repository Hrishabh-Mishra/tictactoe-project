package tiktactoe.startegy.botplayingstrategy;

import tiktactoe.model.Board;
import tiktactoe.utils.Pair;

public interface BotPlayingStrategy {
    public Pair<Integer, Integer> makeMove(Board board);
}
