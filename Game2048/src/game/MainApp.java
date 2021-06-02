package game;
public class MainApp {
    public static void main (String args[]) {
        
        AppFrame appFrame = new AppFrame();
        GamePanel gamePanel = new GamePanel(appFrame);
        if (args.length != 0 && args[0].matches("[0-9]*")) {
            GamePanel.GOAL = GameTileValue.of(Integer.parseInt(args[0]));
        }
        KeyResponse kb = KeyResponse.getkeySetting(gamePanel);
        gamePanel.addKeyListener(kb);               // Add KeyAdapter to JPanel.
        appFrame.add(gamePanel);                    // Add JPanel to JFrame.
        appFrame.beginGame();
    }
}
