package me.awli.game;

import me.awli.game.object.CirclePO;
import me.awli.game.object.PhysicsObject;
import me.awli.game.object.RectanglePO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PhysicsSandbox extends JPanel implements ActionListener, MouseListener {

    // constants for the width and height of the panel
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    // gravity constant
    public static final double GRAVITY = 9.81;

    // Timer for the game loop
    private Timer timer;
    // desired frames per second
    private static final int FPS = 60;
    // interval in milliseconds between updates
    private static final int INTERVAL = 1000 / FPS;

    // list of objects in the sandbox
    private ArrayList<PhysicsObject> objects;

    public PhysicsSandbox() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);

        addMouseListener(this);

        // create an empty list of objects
        objects = new ArrayList<>();

        // start the timer
        timer = new Timer(INTERVAL, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // update all objects in the sandbox
        for (PhysicsObject obj : objects) {
            obj.handleWallCollisions(WIDTH, HEIGHT);
            obj.update(INTERVAL / 1000.0, GRAVITY);
        }
        // repaint the screen
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
        if(e.getButton() == MouseEvent.BUTTON1)
            objects.add(new CirclePO(e.getX(), e.getY(), 0, 0, 128, 16, Color.BLACK, 0.3));
        else if(e.getButton() == MouseEvent.BUTTON3)
            objects.add(new RectanglePO(e.getX(), e.getY(), 0, 0, 128, 16, Color.BLACK, 0.3));
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
