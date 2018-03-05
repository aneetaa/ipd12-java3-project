package ipd12.java3.project.tankswar;

import java.util.List;

public class Enemy extends Tank
        implements Runnable {

    public Enemy() {
        super();
    }

    public Enemy(int x, int y, int camp, int direction, int speed, boolean isAlive, boolean isCollision, List<Bullet> bullets) {
        super(x, y, camp, direction, speed, isAlive, isCollision, bullets);
    }

    @Override
    public void run() {
        try {
            while (true) {
                if(Setting.THREAD_Exit){
                    break;
                }
                if (!isIsAlive()) {
                    break;
                }
                //free-shoot
                this.shoot();
                //move
                switch (getDirection()) {
                    case Setting.NORTH:
                        for (int i = 0; i < Setting.ENEMY_MOVE_LENGTH; i++) {
                            moveUp();
                            Thread.sleep(Setting.PAUSEFOR_50_MILLISECONDS);
                        }
                        break;
                    case Setting.SOUTH:
                        for (int i = 0; i < Setting.ENEMY_MOVE_LENGTH; i++) {
                            moveDown();
                            Thread.sleep(Setting.PAUSEFOR_50_MILLISECONDS);
                        }
                        break;
                    case Setting.EAST:
                        for (int i = 0; i < Setting.ENEMY_MOVE_LENGTH; i++) {
                            moveLeft();
                            Thread.sleep(Setting.PAUSEFOR_50_MILLISECONDS);
                        }
                        break;
                    case Setting.WEST:
                        for (int i = 0; i < Setting.ENEMY_MOVE_LENGTH; i++) {
                            moveRight();
                            Thread.sleep(Setting.PAUSEFOR_50_MILLISECONDS);
                        }
                        break;
                }
                setDirection((int) (Math.random() * 4));
            }
        } catch (InterruptedException e) {
            Setting.threadMessage("I wasn't done!");
        }
    }
}
