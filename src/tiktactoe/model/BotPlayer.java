package tiktactoe.model;

import tiktactoe.factories.BotPlayerFactory;
import tiktactoe.startegy.botplayingstrategy.BotPlayingStrategy;
import tiktactoe.utils.Pair;

public class BotPlayer extends Player{
    private BotDifficultyLevel botDifficultyLevel;
    BotPlayingStrategy botPlayingStrategy;
    public BotPlayer(final String name, final Symbol symbol, BotDifficultyLevel botDifficultyLevel) {
        super(name, symbol);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = BotPlayerFactory.getBotPlayingStrategy(botDifficultyLevel);
    }
    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(final BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }

    @Override
    public Pair<Integer, Integer> makeMove(Board board) {
        System.out.println(getName()+" will make move now");
        return botPlayingStrategy.makeMove(board);
    }
}
