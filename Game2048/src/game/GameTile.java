package game;

import java.util.HashMap;

import static game.GameTileValue._0;
import static game.GameTileValue._2;
import static game.GameTileValue._4;

public class GameTile {
    private final GameTileValue tileValueObj;
    private final static HashMap<Integer, GameTile> cache = new HashMap<>();

    public final static GameTile ZERO = new GameTile(_0);
    
    static {
        for (GameTileValue v: GameTileValue.values()) {
            if (v == _0) {
                cache.put(v.getValue(), ZERO);
            }
            else {
                cache.put(v.getValue(), new GameTile(v));
            }
        }
    }

    public GameTile(GameTileValue tvObj) {
        tileValueObj = tvObj;
    }

    public static GameTile valueOf(int num) {
        return cache.get(num);
    }

    GameTileValue getValue() {
        return tileValueObj;
    }

    public GameTile getDoubleTile() {
        return valueOf(tileValueObj.getValue() << 1);
    }

    boolean empty() {
        return tileValueObj == _0;
    }

    @Override
    public String toString() {
        return String.format("%1$4d", tileValueObj.getValue());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((tileValueObj == null) ? 0 : tileValueObj.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)    return true;
        if (!(obj instanceof GameTile))    return false;
        GameTile other = (GameTile) obj;
        if (tileValueObj != other.tileValueObj) return false;

        return true;
    }

    static GameTile randomTile() {
        return Math.random() < 0.15 ? new GameTile(_2) : new GameTile(_4);
    }
}
