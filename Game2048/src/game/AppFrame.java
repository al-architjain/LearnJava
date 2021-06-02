package game;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;

public class AppFrame extends JFrame {

    private static String TITLE = "2048 Java Game";

    public static JLabel statusBar;
    
    public AppFrame() {
        setTitle(TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(new Dimension(350, 530));
        setResizable(false);
    }

    public void beginGame() {
        setLocationRelativeTo(null);
        setVisible(true);
    }

    JLabel getStatusBar(){
        statusBar = new JLabel(" ");
        statusBar.setPreferredSize(new Dimension(350, 50));
        statusBar.setBackground(new Color(0x282C31));
        statusBar.setForeground(new Color(0xFDBC3B));
        statusBar.setOpaque(true);
        statusBar.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));
		statusBar.setHorizontalAlignment(JLabel.CENTER);
		statusBar.setFont(new Font("Britannic Bold", Font.PLAIN, 14));
        return statusBar;
    }
}
