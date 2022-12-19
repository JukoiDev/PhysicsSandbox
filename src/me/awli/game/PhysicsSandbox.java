package me.awli.game;

import me.awli.game.object.PhysicsObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class PhysicsSandbox extends JPanel implements ActionListener, MouseListener {

    // Constants for the width and height of the panel
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    // Gravity constant(m/s^2)
    public static final double GRAVITY = 9.81;

    // Timer for the game loop
    private Timer timer;
    // Desired frames per second
    private static final int FPS = 60;
    // Interval in milliseconds between updates
    private static final int INTERVAL = 1000 / FPS;

    // List of objects in the sandbox
    private ArrayList<PhysicsObject> objects;

    public PhysicsSandbox() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);

        addMouseListener(this);

        // Create an empty list of objects
        objects = new ArrayList<>();

        // Start the timer
        timer = new Timer(INTERVAL, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update all objects in the sandbox
        for (PhysicsObject obj : objects) {
            obj.handleWallCollisions(WIDTH, HEIGHT);
            obj.update(INTERVAL / 1000.0, GRAVITY);
        }
        // Repaint the screen
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // draw all objects in the sandbox
        for (PhysicsObject obj : objects) {
            obj.draw(g);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Create a new PhysicsObject at the mouse position with a random mass, and size
        double mass = Math.random() * 10 + 1;
        double size = Math.random() * 50 + 10;
        Shape shape = null;

        if (e.getButton() == MouseEvent.BUTTON1)
            shape = new Ellipse2D.Double(0, 0, size, size);
        else if (e.getButton() == MouseEvent.BUTTON3)
            shape = new Rectangle2D.Double(0, 0, size, size);
        else
            return;

        Color color = new Color((int)(Math.random() * 0x1000000));
        double drag = Math.random() * 0.01;
        PhysicsObject obj = new PhysicsObject(e.getX(), e.getY(), 0, 0, mass, shape, color, 0.3, drag);
        // add the object to the list
        System.out.println("Mouse: Left Click on X: " + e.getX() + ", Y: " + e.getY());
        objects.add(obj);
        System.out.println("Current length of Object list: " + objects.size());
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    public static final void main(String... args) {
        // create the physics sandbox game panel
        PhysicsSandbox sandbox = new PhysicsSandbox();

        // create a frame to hold the panel
        JFrame frame = new JFrame("Awli's Physics Sandbox");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(sandbox);
        frame.setResizable(false);
        frame.pack();

        frame.setVisible(true);
    }
}
