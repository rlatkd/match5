package site.match5.global.common;

import lombok.Getter;

@Getter
public enum Level {

    BRONZE(1),
    SILVER(2),
    GOLD(3),
    PLATINUM(4),
    DIAMOND(5);

    private final int value;

    Level(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getLevelTier(int levelId) {
        switch (levelId) {
            case 1:
                return Level.BRONZE.name();
            case 2:
                return Level.SILVER.name();
            case 3:
                return Level.GOLD.name();
            case 4:
                return Level.PLATINUM.name();
            case 5:
                return Level.DIAMOND.name();
            default:
                return "UNKNOWN";
        }
    }

}

