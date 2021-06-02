package game;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class AppFrame extends JFrame {

    private static String TITLE = "2048 Java Game";
    public static String WIN_MSG = "Victory!";
    public static String LOSE_MSG = "Loss! You ran out of possible Moves.";

    JLabel statusBar;
    
    public AppFrame() {
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setResizable(false);

        statusBar = new JLabel("Status Bar text");
        this.add(statusBar, BorderLayout.NORTH);
    }

    public void beginGame() {
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    void win() {
        statusBar.setText(WIN_MSG);
    }
    
    void lose() {
        statusBar.setText(LOSE_MSG);
    }

    void clearStatusBar() {
        statusBar.setText(" ");
    }
}
