package ipd12.java3.project.tankswar;

public class Enemy extends Tank
        implements Runnable {

    public Enemy() {
        super();
    }

    public Enemy(int x, int y, int camp, int direction, int speed, boolean isAlive, boolean isCollision) {
        super(x, y, camp, direction, speed, isAlive, isCollision);
    }

    @Override
    public void run() {
        while (true) {
            if (!isIsAlive()) {
                break;
            }
            //free-shoot
            this.shoot();
            //move
            switch (getDirection()) {
                case Settings.NORTH:
                    for (int i = 0; i < Settings.ENEMY_MOVE_LENGTH; i++) {
                        moveUp();
                        Utils.delay(Settings.PAUSEFOR_50_MILLISECONDS);
                    }
                    break;
                case Settings.SOUTH:
                    for (int i = 0; i < Settings.ENEMY_MOVE_LENGTH; i++) {
                        moveDown();
                        Utils.delay(Settings.PAUSEFOR_50_MILLISECONDS);
                    }
                    break;
                case Settings.EAST:
                    for (int i = 0; i < Settings.ENEMY_MOVE_LENGTH; i++) {
                        moveLeft();
                        Utils.delay(Settings.PAUSEFOR_50_MILLISECONDS);
                    }
                    break;
                case Settings.WEST:
                    for (int i = 0; i < Settings.ENEMY_MOVE_LENGTH; i++) {
                        moveRight();
                        Utils.delay(Settings.PAUSEFOR_50_MILLISECONDS);
                    }
                    break;
            }
            setDirection((int) (Math.random() * 4));
        }
    }
}
