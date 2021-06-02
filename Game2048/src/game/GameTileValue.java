package game;

import java.awt.Color;

public enum GameTileValue {
    _0   (0,    0x9F9280, 0xF4F5F7),
    _2   (2,    0x21170D, 0xFFE5B7),
    _4   (4,    0x21170D, 0xFED39D),
    _8   (8,    0x21170D, 0xFECE8B),
    _16  (16,   0x21170D, 0xFEC763),
    _32  (32,   0x282C31, 0xFDBC3B),
    _64  (64,   0xFFE5B7, 0xC58A2D),
    _128 (128,  0xFFE5B7, 0x956828),
    _256 (256,  0xFFE5B7, 0x503815),
    _512 (512,  0xFFE5B7, 0x21170D),
    _1024(1024, 0x282C31, 0x93CFA1),
    _2048(2048, 0x282C31, 0x49B970);

    private final int value;
    private final Color color;
    private final Color fontColor;

    GameTileValue(int v, int f, int c) {
        value = v;
        color = new Color(c);
        fontColor = new Color(f);
    }

    static GameTileValue of(int num) {
        return GameTileValue.valueOf("_" + num);
    }

    public Color getFontColor() {
        return fontColor;
    }

    public Color getColor() {
        return color;
    }
    
    public int getValue() {
        return value;
    }
}
