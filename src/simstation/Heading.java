package simstation;

import mvc.Utilities;

public enum Heading {
    NORTH, SOUTH, EAST, WEST;

    public static Heading random() {
        int index = Utilities.rng.nextInt(values().length);
        return values()[index];
    }
}