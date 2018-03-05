package ipd12.java3.project.tankswar;

import java.awt.Rectangle;
import java.util.List;

public class Tank {

    // Constructors
    public Tank() {
    }

    public Tank(int x, int y, int camp, int direction, int speed, boolean isAlive, boolean isCollision, List<Bullet> bullets) {
        setX(x);
        setY(y);
        setCamp(camp);
        setDirection(direction);
        setSpeed(speed);
        setIsAlive(isAlive);
        setCollision(isCollision);
        setBullets(bullets);
    }

    public void shoot() {
        //Initial position of bullet. new a bullet and add into List list
        switch (getDirection()) {
            case Setting.NORTH:
                bullets.add(new Bullet(x + Setting.BLOODBATH_LONGITUDINAL_WIDTH / 2, y, Setting.NORTH, true, Setting.BULLET_SPEED));
                break;
            case Setting.SOUTH:
                bullets.add(new Bullet(x + Setting.BLOODBATH_LONGITUDINAL_WIDTH / 2, y + Setting.BLOODBATH_LONGITUDINAL_HEIGHT, Setting.SOUTH, true, Setting.BULLET_SPEED));
                break;
            case Setting.EAST:
                bullets.add(new Bullet(x, y + Setting.BLOODBATH_HORIZONTAL_HEIGHT / 2, Setting.EAST, true, Setting.BULLET_SPEED));
                break;
            case Setting.WEST:
                bullets.add(new Bullet(x + Setting.BLOODBATH_HORIZONTAL_WIDTH, y + Setting.BLOODBATH_HORIZONTAL_HEIGHT / 2, Setting.WEST, true, Setting.BULLET_SPEED));
                break;
        }
        //Thread.Start of last bullet(new) in vector list
        new Thread(bullets.get(bullets.size() - 1)).start();
    }

    // Collision between the tank
    public boolean collisionDetector(Tank anotherTank) {
        int anotherTankX = anotherTank.getX();
        int anotherTankY = anotherTank.getY();
        int anotherTankDirection = anotherTank.getDirection();
        //tank
        int tankLongitudinalWidth = Setting.BLOODBATH_LONGITUDINAL_WIDTH;
        int tankLongitudinalHeight = Setting.BLOODBATH_LONGITUDINAL_HEIGHT;
        int tankHorizontalWidth = Setting.BLOODBATH_HORIZONTAL_WIDTH;
        int tankHorizontalHeight = Setting.BLOODBATH_HORIZONTAL_HEIGHT;
        //another
        int anotherTankLongitudinalWidth = Setting.BLOODBATH_LONGITUDINAL_WIDTH;
        int anotherTankLongitudinalHeight = Setting.BLOODBATH_LONGITUDINAL_HEIGHT;
        int anotherTankHorizontalWidth = Setting.BLOODBATH_HORIZONTAL_WIDTH;
        int anotherTankHorizontalHeight = Setting.BLOODBATH_HORIZONTAL_HEIGHT;
        Rectangle tankBounds;
        Rectangle anotherTankBounds;
        switch (direction) {
            case Setting.NORTH:
            case Setting.SOUTH:
                if (anotherTankDirection == Setting.NORTH || anotherTankDirection == Setting.SOUTH) {
                    tankBounds = new Rectangle(x, y, tankLongitudinalWidth, tankLongitudinalHeight);
                    anotherTankBounds = new Rectangle(anotherTankX, anotherTankY, anotherTankLongitudinalWidth, anotherTankLongitudinalHeight);
                    if (tankBounds.intersects(anotherTankBounds)) {
                        return true;
                    }
                }
                if (anotherTankDirection == Setting.EAST || anotherTankDirection == Setting.WEST) {
                    tankBounds = new Rectangle(x, y, tankLongitudinalWidth, tankLongitudinalHeight);
                    anotherTankBounds = new Rectangle(anotherTankX, anotherTankY, anotherTankHorizontalWidth, anotherTankHorizontalHeight);
                    if (tankBounds.intersects(anotherTankBounds)) {
                        return true;
                    }
                }
                break;
            case Setting.EAST:
            case Setting.WEST:
                if (anotherTankDirection == Setting.NORTH || anotherTankDirection == Setting.SOUTH) {
                    tankBounds = new Rectangle(x, y, tankHorizontalWidth, tankHorizontalHeight);
                    anotherTankBounds = new Rectangle(anotherTankX, anotherTankY, anotherTankLongitudinalWidth, anotherTankLongitudinalHeight);
                    if (tankBounds.intersects(anotherTankBounds)) {
                        return true;
                    }
                }
                if (anotherTankDirection == Setting.EAST || anotherTankDirection == Setting.WEST) {
                    tankBounds = new Rectangle(x, y, tankHorizontalWidth, tankHorizontalHeight);
                    anotherTankBounds = new Rectangle(anotherTankX, anotherTankY, anotherTankHorizontalWidth, anotherTankHorizontalHeight);
                    if (tankBounds.intersects(anotherTankBounds)) {
                        return true;
                    }
                }
                break;
        }
        return false;
    }

    // Move up
    public void moveUp() {
        if (y >= Setting.TOP_LEFT_CORNER_Y) {
            if (!isCollision()) {
                setY(y - speed);
            } else if (y <= Setting.LOWER_RIGHT_CORNER_Y - Setting.BLOODBATH_LONGITUDINAL_HEIGHT) {
                //solve collision "sticking" 95%  and  5% sometimes in the corner sticker
                setY(y + speed);
            }
        }
    }

    // Move down
    public void moveDown() {
        if (y <= Setting.LOWER_RIGHT_CORNER_Y - Setting.BLOODBATH_LONGITUDINAL_HEIGHT) {
            if (!isCollision()) {
                setY(y + speed);
            } else if (y >= Setting.TOP_LEFT_CORNER_Y) {
                setY(y - speed);
            }
        }
    }

    // Move left
    public void moveLeft() {
        if (x >= Setting.TOP_LEFT_CORNER_X) {
            if (!isCollision()) {
                setX(x - speed);
            } else if (x <= Setting.LOWER_RIGHT_CORNER_X - Setting.BLOODBATH_HORIZONTAL_WIDTH) {
                setX(x + speed);
            }

        }
    }

    // Move right
    public void moveRight() {
        if (x <= Setting.LOWER_RIGHT_CORNER_X - Setting.BLOODBATH_HORIZONTAL_WIDTH) {
            if (!isCollision()) {
                setX(x + speed);
            } else if (x >= Setting.TOP_LEFT_CORNER_X) {
                setX(x - speed);
            }
            //  System.out.println(x + " " + y);
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

    public int getDirection() {
        return direction;
    }

    public final void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public final void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isIsAlive() {
        return isAlive;
    }

    public final void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean isCollision() {
        return collision;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public final void setBullets(List<Bullet> bullets) {
        this.bullets = bullets;
    }

    public final void setCollision(boolean collision) {
        this.collision = collision;
    }

    public int getCamp() {
        return camp;
    }

    public final void setCamp(int camp) {
        this.camp = camp;
    }
    private int x;
    private int y;
    private int direction;
    private int speed;
    private int camp;
    private boolean isAlive;
    private boolean collision;
    private List<Bullet> bullets;
}
