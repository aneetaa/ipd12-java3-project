package ipd12.java3.project.tankswar;

public class Decoration extends Object {

    public Decoration(int x, int y, boolean isAlive) {
        super();
        setX(x);
        setY(y);
        setIsAlive(isAlive);
    }

    public int getX() {
        return x;
    }

    public final void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public final void setY(int y) {
        this.y = y;
    }

    public boolean isIsAlive() {
        return isAlive;
    }

    public final void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    private volatile int x;
    private volatile int y;
    private volatile boolean isAlive;
}

class Bomb extends Decoration {

    public Bomb(int x, int y, boolean isAlive, int life) {
        super(x, y, isAlive);
        setLife(life);
    }

    public void reduceBombLife() {
        if (life > 0) {
            life--;
        } else {
            setIsAlive(false);
        }
    }

    public int getLife() {
        return life;
    }

    public final void setLife(int life) {
        this.life = life;
    }

    private volatile int life;
}
