package ipd12.java3.project.tankswar;

import java.awt.Rectangle;

public class Bullet implements Runnable {

    public Bullet(int x, int y, int direction, boolean isAlive, int speed) {
        setX(x);
        setY(y);
        setDirection(direction);
        setIsAlive(isAlive);
        setSpeed(speed);
    }

    //HIT Detector.  *bullet inside area of tank
    public boolean hitTankDetector(Tank tank, int bulletCamp) {
        int tankX = tank.getX();
        int tankY = tank.getY();
        int tankCamp = tank.getCamp();
        Rectangle bulletBounds;
        Rectangle tankBounds;
        switch (tank.getDirection()) {
            case Setting.NORTH:
            case Setting.SOUTH:
                bulletBounds = new Rectangle(x, y, Setting.BULLET_WIDTH, Setting.BULLET_HEIGHT);
                tankBounds = new Rectangle(tankX, tankY, Setting.BLOODBATH_LONGITUDINAL_WIDTH, Setting.BLOODBATH_LONGITUDINAL_HEIGHT);
                if (bulletBounds.intersects(tankBounds)) {
                    setIsAlive(false);
                    if (bulletCamp != tankCamp) {
                        tank.setIsAlive(false);
                        return true;
                    }
                }
                break;
            case Setting.EAST:
            case Setting.WEST:
                bulletBounds = new Rectangle(x, y, Setting.BULLET_WIDTH, Setting.BULLET_HEIGHT);
                tankBounds = new Rectangle(tankX, tankY, Setting.BLOODBATH_HORIZONTAL_WIDTH, Setting.BLOODBATH_HORIZONTAL_HEIGHT);
                if (bulletBounds.intersects(tankBounds)) {
                    setIsAlive(false);
                    if (bulletCamp != tankCamp) {
                        tank.setIsAlive(false);
                        return true;
                    }
                }
                break;
        }
        return false;
    }

    @Override
    public void run() {
        
        try {
            while (true) {
                if(Setting.THREAD_Exit){
                    break;
                }
                //eliminate thread
                if (!isAlive) {
                    break;
                }
                Thread.sleep(Setting.PAUSEFOR_50_MILLISECONDS);
                switch (direction) {
                    case Setting.NORTH:
                        setY(y - speed);
                        break;
                    case Setting.SOUTH:
                        setY(y + speed);
                        break;
                    case Setting.EAST:
                        setX(x - speed);
                        break;
                    case Setting.WEST:
                        setX(x + speed);
                        break;
                }
                // The bullet must be inseide The map 
                if (x > Setting.LOWER_RIGHT_CORNER_X
                        || x < Setting.TOP_LEFT_CORNER_X
                        || y > Setting.LOWER_RIGHT_CORNER_Y
                        || y < Setting.TOP_LEFT_CORNER_Y) {
                    setIsAlive(false);
                }
            }
        } catch (InterruptedException ex) {
            Setting.threadMessage("I wasn't done!");
        }
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

    public int getSpeed() {
        return speed;
    }

    public final void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirection() {
        return direction;
    }

    public final void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isIsAlive() {
        return isAlive;
    }

    public final void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    private int x;
    private int y;
    private int speed;
    private int direction;
    private boolean isAlive;
}
