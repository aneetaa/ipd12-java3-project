package ipd12.java3.project.tankswar;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Aneeta
 */
public class Tank {

    private int x = 100;
    private int y = 100;
    private int ya = 1;
    // in order to read an image from pc
    private BufferedImage tankImage;
    // to access JPanel specifications
    private GamePanel game;

    public Tank(GamePanel game) {

        this.game = game;
        // to receive an image file from pc
        try {
            tankImage = ImageIO.read(new File("tankDown.png"));
        } catch (IOException ex) {
            System.err.println("Not found image file!");
        }
    }

    public void move() {
        // tank move from up to down
        y = y + ya;
    }

    public void paint(Graphics g) {
        //super.paint(g);
        Graphics2D g2 = (Graphics2D) g; // casting g
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON); // ضد لغزش

        // draw image on JPanel
        g2.drawImage(tankImage, x, y, null);

    }

}
