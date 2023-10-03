package entities;

public abstract class Enemy extends Entity {
    private int animationIndex;
    private int enemyState;
    public Enemy(float x, float y, int width, int height) {
        super(x, y, width, height);
    }
}
