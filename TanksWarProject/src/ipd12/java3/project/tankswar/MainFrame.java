package ipd12.java3.project.tankswar;

import javax.swing.JFrame;

/**
 *
 * @author Aneeta
 */
public class MainFrame extends JFrame {

    public MainFrame() {

        GamePanel game = new GamePanel();
        addKeyListener(game);
        add(game);

        setTitle("Game");
        setSize(626, 520);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public static void main(String[] args) {
        MainFrame m = new MainFrame();

    }

}
