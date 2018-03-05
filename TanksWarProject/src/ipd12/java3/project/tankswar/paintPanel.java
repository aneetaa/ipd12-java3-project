package ipd12.java3.project.tankswar;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class paintPanel extends JPanel implements KeyListener, Runnable {

    // Definition your Tank
    Hero hero;
    // Definition your enemy
    List<Enemy> enemys;
    // List<Tank> tank;
    List<Decoration> decorations;
    private BufferedImage imageOne, imageTwo, imageThree;

    // Constructor
    public paintPanel() {

        // Initial hero
        hero = new Hero(Setting.PLAYER_TANK_X,
                Setting.PLAYER_TANK_Y,
                Setting.CAMP_GREEN,
                Setting.NORTH,
                Setting.PLAYER_TANK_MOVESPEED,
                Setting.ISALIVE_TRUE,
                Setting.ISCOLLIDE_FALSE,
                Collections.synchronizedList(new ArrayList<>()));

        // Initial enemys
        enemys = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < Record.getEnemyNumber(); i++) {
            int initX = i * Setting.MAP_WIDTH / Record.getEnemyNumber() + Setting.TOP_LEFT_CORNER_X;
            Enemy enemy = new Enemy(
                    initX,
                    Setting.TOP_LEFT_CORNER_Y,
                    Setting.CAMP_GRAY,
                    Setting.SOUTH,
                    Setting.ENEMY_TANK_MOVESPEED,
                    Setting.ISALIVE_TRUE,
                    Setting.ISCOLLIDE_FALSE,
                    Collections.synchronizedList(new ArrayList<>()));
            new Thread(enemy).start();
            enemys.add(enemy);
        }
        // Inital Decoration./src//
        decorations = Collections.synchronizedList(new ArrayList<>());
        try {
            imageOne = ImageIO.read(getClass().getResourceAsStream("/res//bombOne.jpg"));
            imageTwo = ImageIO.read(getClass().getResourceAsStream("/res//bombTwo.jpg"));
            imageThree = ImageIO.read(getClass().getResourceAsStream("/res//bombThree.jpg"));
        } catch (Exception e) {
            throw new IllegalArgumentException("Can't load file" + e.getMessage());
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //Draw a map
        g.drawRect(Setting.TOP_LEFT_CORNER_X, Setting.TOP_LEFT_CORNER_Y, Setting.MAP_WIDTH, Setting.MAP_HEIGHT);
        // Draw the hero
        if (hero.isIsAlive()) {
            drawTankBloodBath(hero, g);
        }
        // Draw the hero's bullets
        for (Bullet bullet : hero.getBullets()) {
            if (bullet != null
                    && bullet.isIsAlive()) {
                g.fill3DRect(bullet.getX(),
                        bullet.getY(),
                        Setting.BULLET_WIDTH,
                        Setting.BULLET_HEIGHT,
                        true);
            }
        }

        // Draw the enemys
        for (Enemy enemy : enemys) {
            if (enemy != null
                    && enemy.isIsAlive()) {
                drawTankBloodBath(enemy, g);
            }
            // Draw each enemy bullets
            for (Bullet bullet : enemy.getBullets()) {
                if (bullet != null
                        && bullet.isIsAlive()) {
                    g.fill3DRect(bullet.getX(),
                            bullet.getY(),
                            Setting.BULLET_WIDTH,
                            Setting.BULLET_HEIGHT,
                            true);
                }
            }
        }
        // Draw bomb
        drawBomb(g);
        //record
        drawRcord(g);
    }

    //Draw a record
    public void drawRcord(Graphics g) {
        Font f = new Font("Arial Narrow ", Font.BOLD, 15);
        g.setFont(f);
        drawRecordTank(660, 100, Setting.CAMP_GREEN, g);
        g.drawString(Record.getHeroHP() + "", 700, 120);
        drawRecordTank(660, 200, Setting.CAMP_GRAY, g);
        g.drawString(Record.getEnemyNumber() + "", 700, 220);

        // Destroy the  total number of enemies
        g.drawString("Destroy the total number of enemies:", 90, 470);
        drawRecordTank(370, 450, Setting.CAMP_GRAY, g);
        g.drawString(Record.getDestroyAllEnemies() + "", 400, 470);

    }

    //Draw a record tank
    public void drawRecordTank(int x, int y, int camp, Graphics g) {
        switch (camp) {
            case Setting.CAMP_GREEN:
                g.setColor(Color.green);
                break;
            case Setting.CAMP_GRAY:
                g.setColor(Color.lightGray);
                break;
        }
        g.fill3DRect(x, y, 5, 30, false);
        g.fill3DRect(x + 15, y, 5, 30, false);
        g.fill3DRect(x + 5, y + 5, 10, 20, false);
        g.fillOval(x + 5, y + 10, 10, 10);
        g.drawLine(x + 10, y + 15, x + 10, y);
        g.setColor(Color.black);
    }

    //Draw a bomb
    public void drawBomb(Graphics g) {
        for (Decoration decorations : decorations) {
            Bomb bomb = (Bomb) decorations;
            if (bomb != null
                    && bomb.isIsAlive()) {
                if (bomb.getLife() > 6) {
                    g.drawImage(imageOne, bomb.getX(), bomb.getY(), 30, 30, this);
                } else if (bomb.getLife() > 3) {
                    g.drawImage(imageTwo, bomb.getX(), bomb.getY(), 30, 30, this);
                } else {
                    g.drawImage(imageOne, bomb.getX(), bomb.getY(), 30, 30, this);
                }
                bomb.reduceBombLife();
            }
        }
    }

    // Draw a tank
    public void drawTankBloodBath(Tank tank, Graphics g) {
        // Determine what type of tank
        int x = tank.getX();
        int y = tank.getY();
        int camp = tank.getCamp();
        int direction = tank.getDirection();
        //logical center
        switch (camp) {
            case Setting.CAMP_GREEN:
                g.setColor(Color.green);
                break;
            case Setting.CAMP_GRAY:
                g.setColor(Color.lightGray);
                break;
        }
        switch (direction) {
            case Setting.NORTH:
                // 1.Draw left Rectangle
                g.fill3DRect(x, y, 5, 30, false);
                // 2.Draw right Rectangle
                g.fill3DRect(x + 15, y, 5, 30, false);
                // 3.Center Rectangle
                g.fill3DRect(x + 5, y + 5, 10, 20, false);
                // 4.Center Circle
                g.fillOval(x + 5, y + 10, 10, 10);
                // 5.Draw line
                g.drawLine(x + 10, y + 15, x + 10, y);
                break;
            case Setting.SOUTH:
                g.fill3DRect(x, y, 5, 30, false);
                g.fill3DRect(x + 15, y, 5, 30, false);
                g.fill3DRect(x + 5, y + 5, 10, 20, false);
                g.fillOval(x + 5, y + 10, 10, 10);
                g.drawLine(x + 10, y + 15, x + 10, y + 30);
                break;
            case Setting.EAST:
                g.fill3DRect(x, y, 30, 5, false);
                g.fill3DRect(x, y + 15, 30, 5, false);
                g.fill3DRect(x + 5, y + 5, 20, 10, false);
                g.fillOval(x + 10, y + 5, 10, 10);
                g.drawLine(x, y + 10, x + 15, y + 10);
                break;
            case Setting.WEST:
                g.fill3DRect(x, y, 30, 5, false);
                g.fill3DRect(x, y + 15, 30, 5, false);
                g.fill3DRect(x + 5, y + 5, 20, 10, false);
                g.fillOval(x + 10, y + 5, 10, 10);
                g.drawLine(x + 15, y + 10, x + 30, y + 10);
                break;
        }
        g.setColor(Color.black);
    }

    //Player 
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                // case KeyEvent.VK_W:
                hero.moveUp();
                hero.setDirection(Setting.NORTH);
                break;
            case KeyEvent.VK_DOWN:
                // case KeyEvent.VK_S:
                hero.moveDown();
                hero.setDirection(Setting.SOUTH);
                break;
            case KeyEvent.VK_LEFT:
                // case KeyEvent.VK_A:
                hero.moveLeft();
                hero.setDirection(Setting.EAST);
                break;
            case KeyEvent.VK_RIGHT:
                // case KeyEvent.VK_D:
                hero.moveRight();
                hero.setDirection(Setting.WEST);
                break;
            case KeyEvent.VK_SPACE:
                // case KeyEvent.VK_J:
                // issues bullets
                if (hero.getBullets().size() < Setting.PLAYER_TANK_MAXSHOOT_BULLETS
                        && hero.isIsAlive()) {
                    hero.shoot();
                }
                break;
            default:
                break;
        }
        //The timer has repaint(). 
    }

    //make Thread as a timer
    @Override
    public void run() {
        try {
            while (true) {
                if (Setting.THREAD_Exit) {
                    break;
                }
                Thread.sleep(100);

                //Collision detection shoot
                //hero to enemys
                for (Bullet bullet : hero.getBullets()) {
                    //to enemy
                    if (bullet.isIsAlive()) {
                        for (Enemy enemy : enemys) {
                            if (enemy.isIsAlive()) {
                                if (bullet.hitTankDetector(enemy, hero.getCamp())) {
                                    decorations.add(new Bomb(enemy.getX(), enemy.getY(), Setting.ISALIVE_TRUE, Setting.DECORATION_LIFE));
                                    Record.reduceEnemyNumber();
                                    Record.addDestroyAllEnemies();
                                }
                            }
                        }
                    }
                }
                //enemys
                for (int i = 0; i < enemys.size(); i++) {
                    if (enemys.get(i).isIsAlive()) {
                        for (int j = 0; j < enemys.get(i).getBullets().size(); j++) {
                            if (enemys.get(i).getBullets().get(j).isIsAlive()) {
                                //to hero
                                if (hero.isIsAlive()) {
                                    if (enemys.get(i).getBullets().get(j).hitTankDetector(hero, enemys.get(i).getCamp())) {
                                        decorations.add(new Bomb(hero.getX(), hero.getY(), Setting.ISALIVE_TRUE, Setting.DECORATION_LIFE));
                                        Record.reduceHeroHP();
                                    }
                                }
                                //to enemys
                                for (int k = 0; k < enemys.size(); k++) {
                                    if (enemys.get(k).isIsAlive()) {
                                        if (enemys.get(i).getBullets().get(j).hitTankDetector(enemys.get(k), enemys.get(i).getCamp())) {
                                            decorations.add(new Bomb(enemys.get(k).getX(), enemys.get(k).getY(), Setting.ISALIVE_FALSE, Setting.DECORATION_LIFE));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                //end collision detection shoot
                /* ? for each throws current modify exception ?????
                {for (Enemy enemy : enemys) {
                    for (Bullet bullet : enemy.getBullets()) {
                        //to hero 
                        if (hero.isIsAlive()) {
                            if (bullet.hitTankDetector(hero, enemy.getCamp())) {
                                //       decorations.add(new Bomb(hero.getX(), hero.getY(), Setting.ISALIVE_TRUE, Setting.DECORATION_LIFE));
                            }
                        }
                        //to enemys
                        for (Enemy enemyAnother : enemys) {
                            if (bullet.hitTankDetector(enemyAnother, enemy.getCamp())) {
                                //      decorations.add(new Bomb(enemyAnother.getX(), enemyAnother.getY(), Setting.ISALIVE_TRUE, Setting.DECORATION_LIFE));
                            }
                        }
                    }
                }}*/

                // Clean up the battle field(reset Arraylist)
                //to bullets
                for (int i = 0; i < enemys.size(); i++) {
                    for (int j = 0; j < enemys.get(i).getBullets().size(); j++) {
                        if (!enemys.get(i).getBullets().get(j).isIsAlive()) {
                            enemys.get(i).getBullets().remove(enemys.get(i).getBullets().get(j));
                        }
                    }
                }
                //to enemys
                for (int i = 0; i < enemys.size(); i++) {
                    if (!enemys.get(i).isIsAlive()
                            //Solve the bullets disappear
                            && enemys.get(i).getBullets().isEmpty()) {
                        enemys.remove(enemys.get(i));
                    }
                }
                //to bullets
                for (int i = 0; i < hero.getBullets().size(); i++) {
                    if (!hero.getBullets().get(i).isIsAlive()) {
                        hero.getBullets().remove(hero.getBullets().get(i));
                    }
                }
                //to decorations
                for (int i = 0; i < decorations.size(); i++) {
                    if (!decorations.get(i).isIsAlive()) {
                        decorations.remove(decorations.get(i));
                    }
                }
                // End cleanup the battle field

                //collision detector
                //between hero and enemys
                boolean sumCollision = false;
                for (int i = 0; i < enemys.size(); i++) {
                    sumCollision = sumCollision
                            || hero.collisionDetector(enemys.get(i));
                }
                if (sumCollision) {
                    hero.setCollision(true);
                } else {
                    hero.setCollision(false);
                }

                // Collision detector, between enemys 
                for (int i = 0; i < enemys.size(); i++) {
                    Enemy enemyFirst = enemys.get(i);

                    boolean sumCollisionEnemy = false;
                    for (int j = 0; j < enemys.size(); j++) {
                        Enemy enemySecond = enemys.get(j);
                        // Not self and collision
                        if (enemyFirst != enemySecond) {
                            sumCollisionEnemy = sumCollisionEnemy
                                    || enemyFirst.collisionDetector(enemySecond);
                        }
                    }
                    // enemyTank collision to player
                    sumCollisionEnemy = sumCollisionEnemy
                            || enemyFirst.collisionDetector(hero);

                    if (sumCollisionEnemy) {
                        enemyFirst.setCollision(true);

                    } else {
                        enemyFirst.setCollision(false);
                    }
                }
                //end collision detector
                // Redraw
                repaint();
            }
        } catch (InterruptedException e) {
            Setting.threadMessage("I wasn't done!");
        }
    }

    public void pause() {
        //to bullets
        for (int i = 0; i < enemys.size(); i++) {
            for (int j = 0; j < enemys.get(i).getBullets().size(); j++) {
                if (!enemys.get(i).getBullets().get(j).isIsAlive()) {
                    enemys.get(i).getBullets().get(j).setSpeed(Setting.SPEED_ZERO);
                }
            }
        }
        //to enemys
        for (int i = 0; i < enemys.size(); i++) {
            if (!enemys.get(i).isIsAlive()) {
                enemys.get(i).setSpeed(Setting.SPEED_ZERO);
            }
        }
        //to bullets
        for (int i = 0; i < hero.getBullets().size(); i++) {
            if (!hero.getBullets().get(i).isIsAlive()) {
                hero.getBullets().get(i).setSpeed(Setting.SPEED_ZERO);
            }
        }
        if (hero.isIsAlive()) {
            hero.setSpeed(Setting.SPEED_ZERO);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
