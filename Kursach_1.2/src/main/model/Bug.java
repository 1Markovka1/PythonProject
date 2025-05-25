package main.model;

import java.awt.Point;

public class Bug {
    private Point position;

    public Bug(int startX, int startY) {
        this.position = new Point(startX, startY);
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
}