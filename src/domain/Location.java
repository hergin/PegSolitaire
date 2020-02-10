package domain;

import java.util.Objects;

public class Location {

    private int X, Y;

    public Location(int x, int y) {
        X = x;
        Y = y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return X == location.X &&
                Y == location.Y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(X, Y);
    }
}
