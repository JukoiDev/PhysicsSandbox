package me.awli.game.object;

import java.awt.*;

public class CirclePO extends PhysicsObject {
    public CirclePO(double x, double y, double vx, double vy, double mass, double size, Color color,
                    double restitution) {
        super(x, y, vx, vy, mass, size, color, restitution, 0); // in our simulation the "ball"/"circle" is perfect
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval((int) (positionX - size / 2), (int) (positionY - size / 2), (int) size, (int) size);
    }
}