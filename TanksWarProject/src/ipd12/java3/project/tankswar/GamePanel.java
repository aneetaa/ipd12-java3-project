package ipd12.java3.project.tankswar;

/**
 *
 * @author Aneeta
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

/**
 *
 * @author Aneeta
 */
public class GamePanel extends JPanel implements Runnable, KeyListener {

    private Thread tankThread;
    // to show tank on JPanel
    Tank tankObject = new Tank(this);

    public GamePanel() {
    }

    @Override
    public void addNotify() {
        super.addNotify();
        tankThread = new Thread(this);
        tankThread.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g; // casting g
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON); // ضد لغزش
        // we add/call all paint methods existing in other classes
        tankObject.paint(g2);

    }

    // we add/call all move methods existing in other classes
    public void move() {
        tankObject.move();

    }

    // run the Game
    @Override
    public void run() {

        while (true) {
            // redo paint(Graphics g)
            repaint();
            move();
            try {
                //speed control
                Thread.sleep(10);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }

        }
    }

    @Override
    public void keyTyped(KeyEvent ke
    ) {
    }

    @Override
    public void keyPressed(KeyEvent ke
    ) {
    }

    @Override
    public void keyReleased(KeyEvent ke
    ) {
    }

}
