package tiktactoe.factories;

import tiktactoe.model.BotDifficultyLevel;
import tiktactoe.startegy.botplayingstrategy.BotPlayingStrategy;
import tiktactoe.startegy.botplayingstrategy.EasyBotPlayingStrategy;

public class BotPlayerFactory {

    public static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel botDifficultyLevel) {
        switch (botDifficultyLevel){
            case EASY:
            case MEDIUM:
            case DIFFICULT:
                return new EasyBotPlayingStrategy();
            default:
                throw new UnsupportedOperationException("There is no implementation for the selected bot difficulty level");
        }
    }
}
