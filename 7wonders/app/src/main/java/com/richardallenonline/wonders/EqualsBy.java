package com.richardallenonline.wonders;

/**
 * Created by Laptop on 09/03/2016.
 */
public enum EqualsBy {
    ID(0),
    EveryThing(10);

    private final int value;
    EqualsBy(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static EqualsBy getId(int value) {
        switch (value) {
            case 0:
                return EqualsBy.ID;
            case 1:
            default:
                return EqualsBy.EveryThing;
        }
    }
}
