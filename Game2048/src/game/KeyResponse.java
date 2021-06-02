package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_UP;
import static java.awt.event.KeyEvent.VK_R;
import java.util.HashMap;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;


public class KeyResponse extends KeyAdapter {

    private static final HashMap<Integer, Method> keyMapping = new HashMap<>();

    private static Integer[] KEYS = { VK_UP, VK_DOWN, VK_LEFT, VK_RIGHT, VK_R };

    private static String[] methodName = { "moveUp", "moveDown", "moveLeft", "moveRight", "initBoardTiles" };

    private static GamePanel board;

    private static final KeyResponse INSTANCE = new KeyResponse();
    

    public static KeyResponse getkeySetting(GamePanel b) {
        board = b;
        return INSTANCE;
    }

    private KeyResponse() {
        initKey(KEYS);
    }

    // Initialize Key Setting for arrow keys
    void initKey(Integer[] kcs) {
        for (int i = 0; i < kcs.length; i++) {
            try {
                keyMapping.put(kcs[i], GamePanel.class.getMethod(methodName[i]));
            } catch (NoSuchMethodException | SecurityException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Use reflect to invoke the mapping method.
     */
    @Override
    public void keyPressed(KeyEvent k) {
        super.keyPressed(k);
        Method action = keyMapping.get(k.getKeyCode());
        if (action == null) {
            System.gc();
            return;
        }
        try {
            action.invoke(board);
            board.repaint();
        } catch (InvocationTargetException | IllegalAccessException
                | IllegalArgumentException e) {
            e.printStackTrace();
        }
        if (!board.canMove()) {  // can not move, game over
            board.messageLabel.setText("<html><b>You lost man!</b></html>");
        }

    }

}

