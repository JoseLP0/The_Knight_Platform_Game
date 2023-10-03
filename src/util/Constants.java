package util;

import main.Game;
import main.GamePanel;

public class Constants {

    public static class Enviroment {
        public static final int CITY_IMG_WIDTH_DEFAULT = 320;
        public static final int CITY_IMG_HEIGHT_DEFAULT = 131;
        public static final int CITY_IMG_WIDTH = (int)(CITY_IMG_WIDTH_DEFAULT * 1.6 * Game.SCALE);
        public static final int CITY_IMG_HEIGHT = (int)(CITY_IMG_HEIGHT_DEFAULT * 1.6 * Game.SCALE);

        public static final int FOG_IMG_WIDTH_DEFAULT = 320;
        public static final int FOG_IMG_HEIGHT_DEFAULT = 30;
        public static final int FOG_IMG_WIDTH = (int)(FOG_IMG_WIDTH_DEFAULT * 1.6 * Game.SCALE);
        public static final int FOG_IMG_HEIGHT = (int)(FOG_IMG_HEIGHT_DEFAULT * 1.6 * Game.SCALE);


    }

    public static class UI {
        public static class Buttons {
            public static final int B_WIDTH_DEFAULT = 156;
            public static final int B_HEIGHT_DEFAULT = 72;
            public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
            public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);
        }

        public static class PauseButtons {
            public static final int SOUND_SIZE_DEFAULT = 44;
            public static final int SOUND_SIZE = (int)(38 * Game.SCALE);
        }

        public static class URMButtons {
            public static final int URM_DEFAULT_SIZE = 44;
            public static final int URM_SIZE = (int)(URM_DEFAULT_SIZE * Game.SCALE);
        }

        public static class VolumeButton {
            public static final int VOLUME_DEFAULT_WIDTH = 25;
            public static final int VOLUME_DEFAULT_HEIGHT = 40;
            public static final int SLIDER_DEFAULT_WIDTH = 195;

            public static final int VOLUME_WIDTH = (int)(VOLUME_DEFAULT_WIDTH * Game.SCALE);
            public static final int VOLUME_HEIGHT = (int)(VOLUME_DEFAULT_HEIGHT * Game.SCALE);
            public static final int SLIDER_WIDTH = (int)(SLIDER_DEFAULT_WIDTH * Game.SCALE);

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
                    return 8;
                case IDLE:
                    return 14;
                case JUMP:
                    return 8;
                case FALLING:
                    return 9;
                case GROUND:
                case HIT:
                case ATTACK_1:
                    return 7;
                case ATTACK_2:
                case JUMP_ATTACK_1:
                default:
                    return 1;
            }
        }


    }
}
