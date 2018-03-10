package ipd12.java3.project.tankswar;

import java.awt.Rectangle;

public class Tank {

    // Constructors
    public Tank() {
    }

    public Tank(int x, int y, int camp, int direction, int speed, boolean isAlive, boolean isCollision) {
        setX(x);
        setY(y);
        setCamp(camp);
        setDirection(direction);
        setSpeed(speed);
        setIsAlive(isAlive);
        setCollision(isCollision);
    }

    public Tank(long id, int x, int y, int direction, int speed, int camp, boolean isAlive, boolean isCollision) {
        setId(id);
        setX(x);
        setY(y);
        setCamp(camp);
        setDirection(direction);
        setSpeed(speed);
        setIsAlive(isAlive);
        setCollision(isCollision);
    }

    public void shoot() {
        final Object lock = new Object();
        synchronized (lock) {
            switch (getDirection()) {
                case Settings.NORTH: {
                    Bullet bullet = new Bullet(
                            x + Settings.BLOODBATH_LONGITUDINAL_WIDTH / 2,
                            y,
                            Settings.NORTH,
                            Settings.ISALIVE_TRUE,
                            Settings.BULLET_SPEED,
                            this.camp);
                    Thread thread = new Thread(bullet);
                    thread.start();
                    GamePanel.bullets.add(bullet);
                }
                break;
                case Settings.SOUTH: {
                    Bullet bullet = new Bullet(
                            x + Settings.BLOODBATH_LONGITUDINAL_WIDTH / 2,
                            y + Settings.BLOODBATH_LONGITUDINAL_HEIGHT,
                            Settings.SOUTH,
                            Settings.ISALIVE_TRUE,
                            Settings.BULLET_SPEED,
                            this.camp);
                    Thread thread = new Thread(bullet);
                    thread.start();
                    GamePanel.bullets.add(bullet);
                }
                break;
                case Settings.EAST: {
                    Bullet bullet = new Bullet(
                            x,
                            y + Settings.BLOODBATH_HORIZONTAL_HEIGHT / 2,
                            Settings.EAST,
                            Settings.ISALIVE_TRUE,
                            Settings.BULLET_SPEED,
                            this.camp);
                    Thread thread = new Thread(bullet);
                    thread.start();
                    GamePanel.bullets.add(bullet);
                }
                break;
                case Settings.WEST: {
                    Bullet bullet = new Bullet(
                            x + Settings.BLOODBATH_HORIZONTAL_WIDTH,
                            y + Settings.BLOODBATH_HORIZONTAL_HEIGHT / 2,
                            Settings.WEST,
                            Settings.ISALIVE_TRUE,
                            Settings.BULLET_SPEED,
                            this.camp);
                    Thread thread = new Thread(bullet);
                    thread.start();
                    GamePanel.bullets.add(bullet);
                }
                break;
            }
        }
        // System.out.println(GamePanel.bullets.size());
    }

    // Collision between the tank
    public boolean collisionDetector(Tank anotherTank) {
        int anotherTankX = anotherTank.getX();
        int anotherTankY = anotherTank.getY();
        int anotherTankDirection = anotherTank.getDirection();
        //tank
        int tankLongitudinalWidth = Settings.BLOODBATH_LONGITUDINAL_WIDTH;
        int tankLongitudinalHeight = Settings.BLOODBATH_LONGITUDINAL_HEIGHT;
        int tankHorizontalWidth = Settings.BLOODBATH_HORIZONTAL_WIDTH;
        int tankHorizontalHeight = Settings.BLOODBATH_HORIZONTAL_HEIGHT;
        //another
        int anotherTankLongitudinalWidth = Settings.BLOODBATH_LONGITUDINAL_WIDTH;
        int anotherTankLongitudinalHeight = Settings.BLOODBATH_LONGITUDINAL_HEIGHT;
        int anotherTankHorizontalWidth = Settings.BLOODBATH_HORIZONTAL_WIDTH;
        int anotherTankHorizontalHeight = Settings.BLOODBATH_HORIZONTAL_HEIGHT;
        Rectangle tankBounds;
        Rectangle anotherTankBounds;
        switch (direction) {
            case Settings.NORTH:
            case Settings.SOUTH:
                if (anotherTankDirection == Settings.NORTH || anotherTankDirection == Settings.SOUTH) {
                    tankBounds = new Rectangle(x, y, tankLongitudinalWidth, tankLongitudinalHeight);
                    anotherTankBounds = new Rectangle(anotherTankX, anotherTankY, anotherTankLongitudinalWidth, anotherTankLongitudinalHeight);
                    if (tankBounds.intersects(anotherTankBounds)) {
                        return true;
                    }
                }
                if (anotherTankDirection == Settings.EAST || anotherTankDirection == Settings.WEST) {
                    tankBounds = new Rectangle(x, y, tankLongitudinalWidth, tankLongitudinalHeight);
                    anotherTankBounds = new Rectangle(anotherTankX, anotherTankY, anotherTankHorizontalWidth, anotherTankHorizontalHeight);
                    if (tankBounds.intersects(anotherTankBounds)) {
                        return true;
                    }
                }
                break;
            case Settings.EAST:
            case Settings.WEST:
                if (anotherTankDirection == Settings.NORTH || anotherTankDirection == Settings.SOUTH) {
                    tankBounds = new Rectangle(x, y, tankHorizontalWidth, tankHorizontalHeight);
                    anotherTankBounds = new Rectangle(anotherTankX, anotherTankY, anotherTankLongitudinalWidth, anotherTankLongitudinalHeight);
                    if (tankBounds.intersects(anotherTankBounds)) {
                        return true;
                    }
                }
                if (anotherTankDirection == Settings.EAST || anotherTankDirection == Settings.WEST) {
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
        if (y >= Settings.MAP_TOP_LEFT_CORNER_Y) {
            if (!isCollision()) {
                setY(y - speed);
            } else if (y <= Settings.MAP_LOWER_RIGHT_CORNER_Y - Settings.BLOODBATH_LONGITUDINAL_HEIGHT) {
                //solve collision "sticking" 85%  and in the corner sticker 5%
                setY(y + speed);
            }
        }
    }

    // Move down
    public void moveDown() {
        if (y <= Settings.MAP_LOWER_RIGHT_CORNER_Y - Settings.BLOODBATH_LONGITUDINAL_HEIGHT) {
            if (!isCollision()) {
                setY(y + speed);
            } else if (y >= Settings.MAP_TOP_LEFT_CORNER_Y) {
                setY(y - speed);
            }
        }
    }

    // Move left
    public void moveLeft() {
        if (x >= Settings.MAP_TOP_LEFT_CORNER_X) {
            if (!isCollision()) {
                setX(x - speed);
            } else if (x <= Settings.MAP_LOWER_RIGHT_CORNER_X - Settings.BLOODBATH_HORIZONTAL_WIDTH) {
                setX(x + speed);
            }

        }
    }

    // Move right
    public void moveRight() {
        if (x <= Settings.MAP_LOWER_RIGHT_CORNER_X - Settings.BLOODBATH_HORIZONTAL_WIDTH) {
            if (!isCollision()) {
                setX(x + speed);
            } else if (x >= Settings.MAP_TOP_LEFT_CORNER_X) {
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

    public final synchronized void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean isCollision() {
        return collision;
    }

    public final synchronized void setCollision(boolean collision) {
        this.collision = collision;
    }

    public int getCamp() {
        return camp;
    }

    public final void setCamp(int camp) {
        this.camp = camp;
    }

    public long getId() {
        return id;
    }

    public final void setId(long id) {
        this.id = id;
    }
    private volatile long id;
    private volatile int x;
    private volatile int y;
    private volatile int direction;
    private volatile int speed;
    private volatile int camp;
    private boolean isAlive;
    private boolean collision;
}
