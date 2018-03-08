package ipd12.java3.project.tankswar;

import java.awt.Rectangle;

public class Bullet implements Runnable {

    public Bullet() {
    }

    public Bullet(int x, int y, int direction, boolean isAlive, int speed, int camp) {
        setX(x);
        setY(y);
        setDirection(direction);
        setIsAlive(isAlive);
        setSpeed(speed);
        setCamp(camp);
    }

    public Bullet(long id, int x, int y, int speed, int direction, boolean isAlive, int camp) {
        setId(id);
        setX(x);
        setY(y);
        setDirection(direction);
        setIsAlive(isAlive);
        setSpeed(speed);
        setCamp(camp);
    }

    //HIT Detector.  *bullet inside area of tank
    public boolean hitTankDetector(Tank tank, int bulletCamp) {
        int tankX = tank.getX();
        int tankY = tank.getY();
        int tankCamp = tank.getCamp();
        Rectangle bulletBounds;
        Rectangle tankBounds;
        switch (tank.getDirection()) {
            case Settings.NORTH:
            case Settings.SOUTH:
                bulletBounds = new Rectangle(x, y, Settings.BULLET_WIDTH, Settings.BULLET_HEIGHT);
                tankBounds = new Rectangle(tankX, tankY, Settings.BLOODBATH_LONGITUDINAL_WIDTH, Settings.BLOODBATH_LONGITUDINAL_HEIGHT);
                if (bulletBounds.intersects(tankBounds)) {
                    setIsAlive(false);
                    if (bulletCamp != tankCamp) {
                        tank.setIsAlive(false);
                        return true;
                    }
                }
                break;
            case Settings.EAST:
            case Settings.WEST:
                bulletBounds = new Rectangle(x, y, Settings.BULLET_WIDTH, Settings.BULLET_HEIGHT);
                tankBounds = new Rectangle(tankX, tankY, Settings.BLOODBATH_HORIZONTAL_WIDTH, Settings.BLOODBATH_HORIZONTAL_HEIGHT);
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
                //eliminate thread
                if (!isAlive) {
                    break;
                }
                Thread.sleep(Settings.PAUSEFOR_50_MILLISECONDS);
                switch (direction) {
                    case Settings.NORTH:
                        setY(y - speed);
                        break;
                    case Settings.SOUTH:
                        setY(y + speed);
                        break;
                    case Settings.EAST:
                        setX(x - speed);
                        break;
                    case Settings.WEST:
                        setX(x + speed);
                        break;
                }
                // The bullet must be inseide The map
                if (x > Settings.MAP_LOWER_RIGHT_CORNER_X
                        || x < Settings.MAP_TOP_LEFT_CORNER_X
                        || y > Settings.MAP_LOWER_RIGHT_CORNER_Y
                        || y < Settings.MAP_TOP_LEFT_CORNER_Y) {
                    setIsAlive(false);
                }
            }
        } catch (InterruptedException ex) {
            threadMessage("I wasn't done!");
        }
    }

    public void threadMessage(String message) {
        String threadName
                = Thread.currentThread().getName();
        System.out.format("%s: %s%n",
                threadName,
                message);
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

    public int getCamp() {
        return camp;
    }

    public final void setCamp(int camp) {
        if (camp == Settings.CAMP_GREEN) {
            Record.firstPlayerShootCounter++;
        }
        if (camp == Settings.CAMP_YELLOW) {
            Record.secondPlayerShootCounter++;
        }
        this.camp = camp;
    }

    public long getId() {
        return id;
    }

    public final void setId(long id) {
        this.id = id;
    }
    private long id;
    private int x;
    private int y;
    private int speed;
    private int direction;
    private boolean isAlive;
    private int camp;
}
