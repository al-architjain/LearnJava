package game;

import java.awt.BorderLayout;
import javax.swing.JLabel;

public class MainApp {

    private static AppFrame appFrame;
    private static ScorePanel scorePanel;
    private static GamePanel gamePanel;
    private static JLabel messageLabel;
    private static KeyResponse keyResponse;

    MainApp() {
        appFrame = new AppFrame();
        messageLabel = appFrame.getStatusBar();
        scorePanel = new ScorePanel(appFrame);
        gamePanel = new GamePanel(scorePanel, messageLabel);
        keyResponse = KeyResponse.getkeySetting(gamePanel);
        gamePanel.addKeyListener(keyResponse);
    }

    void addComponents() {
        appFrame.add(scorePanel, BorderLayout.NORTH);
        appFrame.add(gamePanel, BorderLayout.CENTER);
        appFrame.add(messageLabel, BorderLayout.SOUTH);
    }

    void beginGame() {
        appFrame.beginGame();
    }

    public static void main (String args[]) {
        MainApp app = new MainApp();
        app.addComponents();
        app.beginGame();
    }
}
