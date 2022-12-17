package me.awli.game.object;

import java.awt.*;

public class RectanglePO extends PhysicsObject {
    public RectanglePO(double x, double y, double vx, double vy, double mass, double size, Color color, double restitution) {
        super(x, y, vx, vy, mass, size, color, restitution, 0.3);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect((int) (positionX - size / 2), (int) (positionY - size / 2), (int) size, (int) size);
    }
}