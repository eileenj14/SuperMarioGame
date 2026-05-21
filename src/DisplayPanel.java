import javax.swing.JPanel;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DisplayPanel extends JPanel implements MouseListener, KeyListener {
    private int score;
    private boolean yellowColor;
    private int marioX;
    private int marioY;
    private BufferedImage background;
    private BufferedImage mario;

    public DisplayPanel() {
        score = 0;
        yellowColor = true;
        marioX = 50;
        marioY = 435;
        try {
            background = ImageIO.read(new File("background.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            mario = ImageIO.read(new File("marioright.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        addMouseListener(this);
        addKeyListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(background, 0, 0, null);
        g.drawImage(mario, marioX, marioY, null);

        // set font and color of text
        g.setFont(new Font("Arial", Font.BOLD, 16));
        if (yellowColor) {
            g.setColor(Color.YELLOW);
        } else {
            g.setColor(Color.BLACK);
        }

        // display red text at position x = 50, y = 30
        g.drawString("Score: " + score, 50, 30);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            yellowColor = !yellowColor;
        }
        repaint();
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
        if (keyCode == KeyEvent.VK_A) {  // A key; VK_A equals 65
            marioX -= 5;
            try {
                mario = ImageIO.read(new File("src/marioleft.png"));
            } catch (IOException error) {}
            repaint();
        }
        if (keyCode == KeyEvent.VK_D) {  // D key; VK_D equals 65
            marioX += 5;
            try {
                mario = ImageIO.read(new File("src/marioright.png"));
            } catch (IOException error) {}
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
