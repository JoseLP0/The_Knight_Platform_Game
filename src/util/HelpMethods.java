package util;

import main.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class HelpMethods {

    public static boolean canMoveHere(float x, float y, float width, float height, int[][] lvlData) {

        if (!isSolid(x, y, lvlData)) {
            if (!isSolid(x + width, y + height, lvlData)) {
                if (!isSolid(x + width, y, lvlData)) {
                    if (!isSolid(x, y + height, lvlData)) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    private static boolean isSolid(float x, float y, int[][] lvlData) {
        int maxWidth = lvlData[0].length * Game.TILES_SIZE;
        if (x < 0 || x >= maxWidth) {
            return true;
        }
        if (y < 0 || y >= Game.GAME_HEIGHT) {
            return true;
        }

        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        int value = lvlData[(int)yIndex][(int)xIndex];

        if (value >= 52 || value < 0 || value != 12 && value != 25 && value != 38) {
            return true;
        } else {
            return false;
        }
    }

    public static float getEntityXPositionNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
        int currentTile = (int)(hitbox.x / Game.TILES_SIZE);
        if (xSpeed > 0) {
            int tileXPosition = currentTile * Game.TILES_SIZE;
            int xOffset = (int)(Game.TILES_SIZE - hitbox.width);
            return tileXPosition + xOffset -1;
        } else {
            return currentTile * Game.TILES_SIZE;
        }
    }

    public static float getEntityYPositionUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
        int currentTile = (int)(hitbox.y / Game.TILES_SIZE);
        if (airSpeed > 0) {
            int tileYPosition = currentTile * Game.TILES_SIZE;
            int yOffSet = (int)(Game.TILES_SIZE - hitbox.height);
            return tileYPosition + yOffSet -1;
        } else {
            return currentTile * Game.TILES_SIZE;
        }
    }

    public static boolean isEntityOnFloor(Rectangle2D.Float hitbox, int[][] lvlData) {
        if (!isSolid(hitbox.x, hitbox.y + hitbox.height + 1, lvlData)) {
            if (!isSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvlData)) {
                return false;
            }

        }
        return true;
    }
}


