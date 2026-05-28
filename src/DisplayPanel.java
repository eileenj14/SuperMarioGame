import javax.swing.*;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Rectangle;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayPanel extends JPanel implements MouseListener, KeyListener, ActionListener {
    private int score;
    private boolean scoreColor;
    private int marioX;
    private int marioY;
    private int luigiX;
    private int luigiY;
    private double goombaX;
    private int goombaY;
    private BufferedImage background;
    private BufferedImage mario;
    private BufferedImage luigi;
    private BufferedImage goomba;
    private boolean[] pressedKeys;
    private Timer timer;

    public DisplayPanel() {
        score = 0;
        scoreColor = true;
        marioX = 50;
        marioY = 435;
        luigiX = 850;
        luigiY = 435;
        goombaX = -50;
        goombaY = 430;
        pressedKeys = new boolean[128];
        timer = new Timer(10, this);
        try {
            background = ImageIO.read(new File("src/background.png"));
        } catch(IOException e) {}
        try {
            mario = ImageIO.read(new File("src/marioright.png"));
        } catch(IOException e) {}
        try {
            luigi = ImageIO.read(new File("src/luigileft.png"));
        } catch(IOException e) {}
        try {
            goomba = ImageIO.read(new File("src/goomba.png"));
        } catch(IOException e) {}

        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(background, 0, 0, null);
        g.drawImage(mario, marioX, marioY, null);
        g.drawImage(luigi, luigiX, luigiY, null);
        g.drawImage(goomba, (int) goombaX, goombaY, null);

        g.setFont(new Font("Arial", Font.BOLD, 16));
        if(scoreColor) g.setColor(Color.YELLOW);
        else g.setColor(Color.BLACK);
        g.drawString("Score: " + score, 50, 30);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            scoreColor = !scoreColor;
        }

        if(e.getButton() == MouseEvent.BUTTON3) {
            scoreColor = !scoreColor;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        pressedKeys[keyCode] = true;

        if(keyCode == KeyEvent.VK_A) {
            try {
                mario = ImageIO.read(new File("src/marioleft.png"));
            } catch (IOException error) {}
        }
        if(keyCode == KeyEvent.VK_D) {
            try {
                mario = ImageIO.read(new File("src/marioright.png"));
            } catch (IOException error) {}
        }

        if(keyCode == KeyEvent.VK_LEFT) {
            try {
                luigi = ImageIO.read(new File("src/luigileft.png"));
            } catch (IOException error) {}
        }
        if(keyCode == KeyEvent.VK_RIGHT) {
            try {
                luigi = ImageIO.read(new File("src/luigiright.png"));
            } catch (IOException error) {}
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        pressedKeys[key] = false;
    }

    private void moveMarioAndLuigi() {
        if (pressedKeys[KeyEvent.VK_A]) {
            if(marioX - 5 >= 0) marioX -= 5;
        }
        if (pressedKeys[KeyEvent.VK_D]) {
            if(marioX + 5 <= 900) marioX += 5;
        }
        if (pressedKeys[KeyEvent.VK_W]) {
            if(marioY - 5 >= 0) marioY -= 5;
        }
        if (pressedKeys[KeyEvent.VK_S]) {
            if(marioY + 5 <= 435) marioY += 5;
        }

        if (pressedKeys[KeyEvent.VK_LEFT]) {
            if(luigiX - 5 >= 0) luigiX -= 5;
        }
        if (pressedKeys[KeyEvent.VK_RIGHT]) {
            if(luigiX + 5 <= 905) luigiX += 5;
        }
        if (pressedKeys[KeyEvent.VK_UP]) {
            if(luigiY - 5 >= 0) luigiY -= 5;
        }
        if (pressedKeys[KeyEvent.VK_DOWN]) {
            if(luigiY + 5 <= 435) luigiY += 5;
        }
    }

    private void moveGoomba() {
        goombaX += 0.5;
        if(goombaX > 1010) goombaX = -50;
    }

    private Rectangle marioRectangle() {
        int imageHeight = mario.getHeight();
        int imageWidth = mario.getWidth();
        Rectangle rect = new Rectangle(marioX, marioY, imageWidth, imageHeight);
        return rect;
    }

    private Rectangle luigiRectangle() {
        int imageHeight = luigi.getHeight();
        int imageWidth = luigi.getWidth();
        Rectangle rect = new Rectangle(luigiX, luigiY, imageWidth, imageHeight);
        return rect;
    }

    private Rectangle goombaRectangle() {
        int imageHeight = goomba.getHeight();
        int imageWidth = goomba.getWidth();
        Rectangle rect = new Rectangle((int) goombaX, goombaY, imageWidth, imageHeight);
        return rect;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moveMarioAndLuigi();
        moveGoomba();

        repaint();
    }
}
