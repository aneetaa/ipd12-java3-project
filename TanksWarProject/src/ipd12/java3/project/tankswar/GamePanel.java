package ipd12.java3.project.tankswar;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements KeyListener, Runnable {

    // Definition your tank and bullet
    public static List<Tank> tanks;
    public static List<Bullet> bullets;
    // List<Tank> tank;
    List<Decoration> decorations;
    private BufferedImage background,
            imageBombOne,
            imageBombTwo,
            imageBombThree,
            imageBullet,
            imageTankNightmareNorth,
            imageTankNightmareSouth,
            imageTankNightmareEast,
            imageTankNightmareWest;
    private final Set<Integer> pressedKeyboard;

    // Constructor
    public GamePanel() {
        pressedKeyboard = new HashSet<>();
        tanks = Collections.synchronizedList(new ArrayList<>());
        bullets = Collections.synchronizedList(new ArrayList<>());
        decorations = Collections.synchronizedList(new ArrayList<>());
        // player one
        Hero firstPlayer = new Hero(Settings.PLAYER_TANK_X,
                Settings.PLAYER_TANK_Y,
                Settings.CAMP_GREEN,
                Settings.NORTH,
                Settings.PLAYER_TANK_MOVESPEED,
                Settings.ISALIVE_TRUE,
                Settings.ISCOLLIDE_FALSE);
        tanks.add(firstPlayer);
        // player two
        Hero secondPlayer = new Hero(Settings.PLAYER_SECOND_TANK_X,
                Settings.PLAYER_SECOND_TANK_Y,
                Settings.CAMP_YELLOW,
                Settings.NORTH,
                Settings.PLAYER_TANK_MOVESPEED,
                Settings.ISALIVE_TRUE,
                Settings.ISCOLLIDE_FALSE);
        tanks.add(secondPlayer);
        // Initial enemys
        for (int i = 0; i < Record.enemyNumber; i++) {
            int initX = i * Settings.MAP_WIDTH / Record.enemyNumber + Settings.MAP_TOP_LEFT_CORNER_X;
            Enemy enemy = new Enemy(
                    initX,
                    Settings.MAP_TOP_LEFT_CORNER_Y,
                    Settings.CAMP_GRAY,
                    Settings.SOUTH,
                    Settings.ENEMY_TANK_MOVESPEED,
                    Settings.ISALIVE_TRUE,
                    Settings.ISCOLLIDE_FALSE);
            new Thread(enemy).start();
            tanks.add(enemy);
        }
        // Inital Decoration./src//
        try {
            imageBombOne = ImageIO.read(getClass().getResourceAsStream("/res//bombOne.jpg"));
            imageBombTwo = ImageIO.read(getClass().getResourceAsStream("/res//bombTwo.jpg"));
            imageBombThree = ImageIO.read(getClass().getResourceAsStream("/res//bombThree.jpg"));
            imageBullet = ImageIO.read(getClass().getResourceAsStream("/res//bullet.jpg"));
            imageTankNightmareNorth = ImageIO.read(getClass().getResourceAsStream("/res//tankUp.png"));
            imageTankNightmareSouth = ImageIO.read(getClass().getResourceAsStream("/res//tankDown.png"));
            imageTankNightmareEast = ImageIO.read(getClass().getResourceAsStream("/res//tankLeft.png"));
            imageTankNightmareWest = ImageIO.read(getClass().getResourceAsStream("/res//tankRight.png"));
        } catch (Exception e) {
            throw new IllegalArgumentException("Can't load file" + e.getMessage());
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //Draw a map
        g.drawRect(Settings.MAP_TOP_LEFT_CORNER_X, Settings.MAP_TOP_LEFT_CORNER_Y, Settings.MAP_WIDTH, Settings.MAP_HEIGHT);
        // Draw the tanks
        for (int i = 0; i < tanks.size(); i++) {
            if (!tanks.get(i).equals(null)
                    && tanks.get(i).isIsAlive()) {
                switch (i) {
                    case Settings.FIRST_PlAYER:
                        drawTankBloodBath(tanks.get(i), g);
                        break;
                    case Settings.SECOND_PlAYER:
                        drawTankNightmare(tanks.get(i), g);
                        break;
                    default:
                        drawTankBloodBath(tanks.get(i), g);
                        break;
                }
            }
        }
        //Draw the bullets
        for (int i = 0; i < bullets.size(); i++) {
            if (!bullets.get(i).equals(null)
                    && bullets.get(i).isIsAlive()) {
                g.fill3DRect(bullets.get(i).getX(),
                        bullets.get(i).getY(),
                        Settings.BULLET_WIDTH,
                        Settings.BULLET_HEIGHT,
                        true);
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
        drawRecordTank(640, 100, Settings.CAMP_GREEN, g);
        g.drawString(Record.firstPlayerHP + "", 670, 120);
        g.drawImage(imageBullet, 700, 110, 30, 16, this);
        g.drawString(Settings.PLAYER_TANK_MAXSHOOT_BULLETS - Record.firstPlayerShootCounter + "", 740, 120);

        g.drawImage(imageTankNightmareNorth, 630, 190, 30, 50, this);
        g.drawString(Record.secondPlayerHP + "", 670, 220);
        g.drawImage(imageBullet, 700, 210, 30, 16, this);
        g.drawString(Settings.PLAYER_TANK_MAXSHOOT_BULLETS - Record.secondPlayerShootCounter + "", 740, 220);

        drawRecordTank(660, 300, Settings.CAMP_GRAY, g);
        g.drawString(Record.enemyNumber + "", 700, 320);

        // Destroy the total number of enemies
        g.drawString("Players Total grade count:", 60, 470);
        g.drawString(Record.gradeCount + "", 250, 470);

    }

    //Draw a record tank
    public void drawRecordTank(int x, int y, int camp, Graphics g) {
        switch (camp) {
            case Settings.CAMP_GREEN:
                g.setColor(Color.green);
                break;
            case Settings.CAMP_GRAY:
                g.setColor(Color.lightGray);
                break;
            case Settings.CAMP_YELLOW:
                g.setColor(Color.yellow);
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
        for (Decoration decoration : decorations) {
            Bomb bomb = (Bomb) decoration;
            if (!bomb.equals(null)
                    && bomb.isIsAlive()) {
                if (bomb.getLife() > 6) {
                    g.drawImage(imageBombOne, bomb.getX(), bomb.getY(), 30, 30, this);
                } else if (bomb.getLife() > 3) {
                    g.drawImage(imageBombTwo, bomb.getX(), bomb.getY(), 30, 30, this);
                } else {
                    g.drawImage(imageBombOne, bomb.getX(), bomb.getY(), 30, 30, this);
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
            case Settings.CAMP_GREEN:
                g.setColor(Color.green);
                break;
            case Settings.CAMP_GRAY:
                g.setColor(Color.lightGray);
                break;
            case Settings.CAMP_YELLOW:
                g.setColor(Color.yellow);
                break;
        }
        switch (direction) {
            case Settings.NORTH:
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
            case Settings.SOUTH:
                g.fill3DRect(x, y, 5, 30, false);
                g.fill3DRect(x + 15, y, 5, 30, false);
                g.fill3DRect(x + 5, y + 5, 10, 20, false);
                g.fillOval(x + 5, y + 10, 10, 10);
                g.drawLine(x + 10, y + 15, x + 10, y + 30);
                break;
            case Settings.EAST:
                g.fill3DRect(x, y, 30, 5, false);
                g.fill3DRect(x, y + 15, 30, 5, false);
                g.fill3DRect(x + 5, y + 5, 20, 10, false);
                g.fillOval(x + 10, y + 5, 10, 10);
                g.drawLine(x, y + 10, x + 15, y + 10);
                break;
            case Settings.WEST:
                g.fill3DRect(x, y, 30, 5, false);
                g.fill3DRect(x, y + 15, 30, 5, false);
                g.fill3DRect(x + 5, y + 5, 20, 10, false);
                g.fillOval(x + 10, y + 5, 10, 10);
                g.drawLine(x + 15, y + 10, x + 30, y + 10);
                break;
        }
        g.setColor(Color.black);
    }
    // Draw a tank Nightmare

    public void drawTankNightmare(Tank tank, Graphics g) {
        // Determine what type of tank
        int x = tank.getX();
        int y = tank.getY();
        int direction = tank.getDirection();
        switch (direction) {
            case Settings.NORTH:
                g.drawImage(imageTankNightmareNorth, x, y, 30, 50, this);
                break;
            case Settings.SOUTH:
                g.drawImage(imageTankNightmareSouth, x, y, 30, 50, this);
                break;
            case Settings.EAST:
                g.drawImage(imageTankNightmareEast, x, y, 50, 30, this);
                break;
            case Settings.WEST:
                g.drawImage(imageTankNightmareWest, x, y, 50, 30, this);
                break;
        }
        g.setColor(Color.black);
    }

    //Player contral keyboard
    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeyboard.add(e.getKeyCode());
        for (Integer keyboard : pressedKeyboard) {
            //System.out.println(keyboard + "  " + pressedKeyboard.size());
            Hero fisrtPlayer = (Hero) tanks.get(0);
            Hero secondPlayer = (Hero) tanks.get(1);
            switch (keyboard) {
                //Fisrt player
                case KeyEvent.VK_UP:
                    fisrtPlayer.moveUp();
                    fisrtPlayer.setDirection(Settings.NORTH);
                    break;
                case KeyEvent.VK_DOWN:
                    fisrtPlayer.moveDown();
                    fisrtPlayer.setDirection(Settings.SOUTH);
                    break;
                case KeyEvent.VK_LEFT:
                    fisrtPlayer.moveLeft();
                    fisrtPlayer.setDirection(Settings.EAST);
                    break;
                case KeyEvent.VK_RIGHT:
                    fisrtPlayer.moveRight();
                    fisrtPlayer.setDirection(Settings.WEST);
                    break;
                case KeyEvent.VK_ADD:
                    if (Record.firstPlayerShootCounter < Settings.PLAYER_TANK_MAXSHOOT_BULLETS
                            && fisrtPlayer.isIsAlive()) {
                        fisrtPlayer.shoot();
                    }
                    break;
                // Second player
                case KeyEvent.VK_W:
                    secondPlayer.moveUp();
                    secondPlayer.setDirection(Settings.NORTH);
                    break;
                case KeyEvent.VK_S:
                    secondPlayer.moveDown();
                    secondPlayer.setDirection(Settings.SOUTH);
                    break;
                case KeyEvent.VK_A:
                    secondPlayer.moveLeft();
                    secondPlayer.setDirection(Settings.EAST);
                    break;
                case KeyEvent.VK_D:
                    secondPlayer.moveRight();
                    secondPlayer.setDirection(Settings.WEST);
                    break;
                case KeyEvent.VK_SPACE:
                    if (Record.secondPlayerShootCounter < Settings.PLAYER_TANK_MAXSHOOT_BULLETS
                            && secondPlayer.isIsAlive()) {
                        secondPlayer.shoot();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeyboard.remove(e.getKeyCode());
    }

    //make Thread as a timer
    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(100);
                //Collision detection shoot
                for (int i = 0; i < bullets.size(); i++) {
                    Bullet bullet = bullets.get(i);
                    if (bullet.isIsAlive()) {
                        for (int j = 0; j < tanks.size(); j++) {
                            Tank tank = tanks.get(j);
                            if (tank.isIsAlive()) {
                                if (bullet.hitTankDetector(tank, bullet.getCamp())) {
                                    decorations.add(new Bomb(tank.getX(), tank.getY(), Settings.ISALIVE_TRUE, Settings.DECORATION_LIFE));
                                    if (tank.getCamp() == Settings.CAMP_GREEN) {
                                        Record.reduceFirstPlayerHP();
                                    }
                                    if (tank.getCamp() == Settings.CAMP_YELLOW) {
                                        Record.reduceSecondPlayerHP();
                                    }
                                    if (tank.getCamp() == Settings.CAMP_GRAY) {
                                        Record.reduceEnemyNumber();
                                        Record.addGradeCount();
                                    }
                                }
                            }
                        }
                    }
                }

                // Clean up the battle field(reset Arraylist)
                // maintain ArrayList using 0 and 1 are players,
                for (int i = 2; i < tanks.size(); i++) {
                    if (!tanks.get(i).isIsAlive()
                            && !tanks.get(i).equals(null)) {
                        tanks.remove(tanks.get(i));
                    }
                }
                //clean up bullets
                for (int i = 0; i < bullets.size(); i++) {
                    if (!bullets.get(i).isIsAlive()
                            && !bullets.get(i).equals(null)) {
                        bullets.remove(bullets.get(i));
                    }
                }
                //to decorations
                for (int i = 0; i < decorations.size(); i++) {
                    if (!decorations.get(i).isIsAlive()
                            && !decorations.get(i).equals(null)) {
                        decorations.remove(decorations.get(i));
                    }
                }
                // End cleanup the battle field

                //collision detector
                //*conditional statement can not redundant. it needs refresh.
                //*must be use setCollision() only once
                //between Players and to enemies
                boolean sumCollisionFirst = false;
                boolean sumCollisionSecond = false;
                for (int i = 2; i < tanks.size(); i++) {
                    Tank enemyTank = tanks.get(i);
                    sumCollisionFirst = sumCollisionFirst || tanks.get(0).collisionDetector(enemyTank);
                    sumCollisionSecond = sumCollisionSecond || tanks.get(1).collisionDetector(enemyTank);
                }
                if (tanks.get(1).isIsAlive()) {
                    sumCollisionFirst = sumCollisionFirst || tanks.get(0).collisionDetector(tanks.get(1));
                }
                if (tanks.get(0).isIsAlive()) {
                    sumCollisionSecond = sumCollisionSecond || tanks.get(1).collisionDetector(tanks.get(0));
                }
                tanks.get(0).setCollision(sumCollisionFirst);
                tanks.get(1).setCollision(sumCollisionSecond);
                //collision detector between enemies and to player
                for (int i = 2; i < tanks.size(); i++) {
                    Tank tank = tanks.get(i);
                    boolean sumCollision = false;
                    for (int j = 2; j < tanks.size(); j++) {
                        Tank anotherTank = tanks.get(j);
                        //It's not itself
                        if (!tank.equals(anotherTank)) {
                            sumCollision = sumCollision
                                    || tank.collisionDetector(anotherTank);
                        }
                    }
                    //to plays
                    if (tanks.get(0).isIsAlive()) {
                        sumCollision = sumCollision || tank.collisionDetector(tanks.get(0));
                    }
                    if (tanks.get(1).isIsAlive()) {
                        sumCollision = sumCollision || tank.collisionDetector(tanks.get(1));
                    }
                    tank.setCollision(sumCollision ? true : false);
                }
                //end collision detector
                //player life count
                if (Record.firstPlayerHP > 0
                        && !tanks.get(0).isIsAlive()) {
                    tanks.get(0).setIsAlive(true);
                }
                if (Record.secondPlayerHP > 0
                        && !tanks.get(1).isIsAlive()) {
                    tanks.get(1).setIsAlive(true);
                }
                // Redraw
                repaint();
            }
        } catch (InterruptedException e) {
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
}
