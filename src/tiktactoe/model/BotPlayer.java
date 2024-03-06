package tiktactoe.model;

import tiktactoe.utils.Pair;

public class BotPlayer extends Player{
    private BotDifficultyLevel botDifficultyLevel;
    public BotPlayer(final String name, final Symbol symbol, BotDifficultyLevel botDifficultyLevel) {
        super(name, symbol);
        this.botDifficultyLevel = botDifficultyLevel;
    }
    public BotDifficultyLevel getBotDifficultyLevel() {

        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(final BotDifficultyLevel botDifficultyLevel) {

        this.botDifficultyLevel = botDifficultyLevel;
    }

    @Override
    public Pair<Integer, Integer> makeMove() {
        return null;
    }
}
