package me.awli.game.object;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

public class PhysicsObject {
    // Position and velocity
    protected double positionX, positionY, velocityX, velocityY;
    // Mass and shape
    protected double mass;
    protected Shape shape;
    // Color
    protected Color color;
    // Coefficient of restitution (bounciness) 0(NO BOUNCE) - 1(SUPER BOUNCE)
    protected double restitution;
    // Coefficient of drag (combined air resistance and friction)
    protected double drag;

    public PhysicsObject(double positionX, double positionY, double velocityX, double velocityY, double mass, Shape shape, Color color,
                         double restitution, double drag) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.mass = mass;
        this.shape = shape;
        this.color = color;
        this.restitution = restitution;
        this.drag = drag;
    }

    // Update the object's position and velocity based on time elapsed
    public void update(double dt, double gravity) {
        // Apply gravity
        velocityY += gravity * dt;
        // Apply drag
        velocityX *= Math.pow(1 - drag, dt);
        velocityY *= Math.pow(1 - drag, dt);
        // Update position based on velocity
        positionX += velocityX * dt;
        positionY += velocityY * dt;
    }

    // Handle collisions with walls
    public void handleWallCollisions(double width, double height) {
        // Get the diameter of the object. Should be the radius, but it was not working, so I experimented.
        double diameter = shape.getBounds().getWidth();

        if (positionX - diameter < 0) {
            // Collision with left wall
            positionX = diameter;
            velocityX = -velocityX * restitution;
        } else if (positionX + diameter > width) {
            // Collision with right wall
            positionX = width - diameter;
            velocityX = -velocityX * restitution;
        }

        if (positionY - diameter < 0) {
            // Collision with top wall
            positionY = diameter;
            velocityY = -velocityY * restitution;
        } else if (positionY + diameter > height) {
            // Collision with bottom wall
            positionY = height - diameter;
            velocityY = -velocityY * restitution;
        }
    }

    public void handleCollisionWith(PhysicsObject otherObject) {

    }

    // Method to draw the object
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Set the object's color
        g2d.setColor(color);
        // Translate the object's position to the center of the shape
        g2d.translate(positionX, positionY);
        // Draw the object's shape
        g2d.fill(shape);
        // Reset the graphics context's transformation matrix back to its original state
        g2d.setTransform(new AffineTransform());
    }
}
