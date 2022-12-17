package me.awli.game.object;

import java.awt.*;

// abstract base class for physics objects
public abstract class PhysicsObject {
    // position and velocity
    protected double positionX, positionY, velocityX, velocityY;
    // mass and size
    protected double mass, size;
    // color
    protected Color color;
    // coefficient of restitution (bounciness) 0(NO BOUNCE) - 1(SUPER BOUNCE)
    protected double restitution;
    // coefficient of drag (combined air resistance and friction)
    protected double drag;

    public PhysicsObject(double positionX, double positionY, double velocityX, double velocityY, double mass, double size, Color color,
                         double restitution, double drag) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.mass = mass;
        this.size = size;
        this.color = color;
        this.restitution = restitution;
        this.drag = drag;
    }

    // update the object's position and velocity based on time elapsed
    public void update(double dt, double gravity) {
        // apply gravity
        velocityY += gravity * dt;
        // apply drag
        velocityX *= Math.pow(1 - drag, dt);
        velocityY *= Math.pow(1 - drag, dt);
        // update position based on velocity
        positionX += velocityX * dt;
        positionY += velocityY * dt;
    }

    // handle collisions with walls
    public void handleWallCollisions(double width, double height) {
        /* if (Math.abs(positionX) < size / 2) {
            // collision with left or right wall
            positionX = size / 2;
            velocityX = -velocityX * restitution;
        } else if (positionX > width - size / 2) {
            // collision with right wall
            positionX = width - size / 2;
            velocityX = -velocityX * restitution;
        } commented out because it was useless and laggy todo: optimise */

        if (Math.abs(positionY) < size / 2) {
            // collision with top wall
            positionY = size / 2;
            velocityY = -velocityY * restitution;
        } else if (positionY > height - size / 2) {
            // collision with bottom wall
            positionY = height - size / 2;
            velocityY = -velocityY * restitution;
        }
    }

    // abstract method to draw the object
    public abstract void draw(Graphics g);
}
