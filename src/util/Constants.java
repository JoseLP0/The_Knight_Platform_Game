package util;

import main.Game;
import main.GamePanel;

public class Constants {

    public static class UI {
        public static class Buttons {
            public static final int B_WIDTH_DEFAULT = 156;
            public static final int B_HEIGHT_DEFAULT = 72;
            public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
            public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);
        }
    }
    public static class PlayerConstant {
        public static final int IDLE = 0;
        public static final int ATTACK_1 = 1;
        public static final int RUNNING = 2;
        public static final int JUMP = 3;
        public static final int GROUND = 6;
        public static final int HIT = 5;
        public static final int FALLING = 4;
        public static final int ATTACK_2 = 7;
        public static final int JUMP_ATTACK_1 = 8;

        public static class Directions {
            public static final int LEFT = 0;
            public static final int UP = 1;
            public static final int RIGHT = 2;
            public static final int DOWN = 3;
        }

        public static int getSpriteAmount(int player_action) {

            switch(player_action) {

                case RUNNING:
                    return 10;
                case IDLE:
                    return 10;
                case JUMP:
                    return 3;
                case FALLING:
                    return 3;
                case GROUND:
                case HIT:
                case ATTACK_1:
                    return 4;
                case ATTACK_2:
                case JUMP_ATTACK_1:
                default:
                    return 1;
            }
        }


    }
}
