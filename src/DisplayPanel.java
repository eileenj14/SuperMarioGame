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
    private boolean scoreColor;
    private int marioX;
    private int marioY;
    private int luigiX;
    private int luigiY;
    private BufferedImage background;
    private BufferedImage mario;
    private BufferedImage luigi;

    public DisplayPanel() {
        score = 0;
        scoreColor = true;
        marioX = 50;
        marioY = 435;
        luigiX = 850;
        luigiY = 435;
        try {
            background = ImageIO.read(new File("src/background.png"));
        } catch(IOException e) {}
        try {
            mario = ImageIO.read(new File("src/marioright.png"));
        } catch(IOException e) {}
        try {
            luigi = ImageIO.read(new File("src/luigileft.png"));
        } catch(IOException e) {}

        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(background, 0, 0, null);
        g.drawImage(mario, marioX, marioY, null);
        g.drawImage(luigi, luigiX, luigiY, null);

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
            marioX = e.getX();
            marioY = e.getY();
            if(marioX > 900) marioX = 900;
            if(marioY > 435) marioY = 435;
        }

        if(e.getButton() == MouseEvent.BUTTON3) {
            scoreColor = !scoreColor;
            luigiX = e.getX();
            luigiY = e.getY();
            if(luigiX > 905) luigiX = 905;
            if(luigiY > 435) luigiY = 435;
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

        if(keyCode == KeyEvent.VK_A) {
            if(marioX - 5 >= 0) marioX -= 5;
            try {
                mario = ImageIO.read(new File("src/marioleft.png"));
            } catch(IOException error) {}
            repaint();
        }
        if(keyCode == KeyEvent.VK_D) {
            if(marioX + 5 <= 900) marioX += 5;
            try {
                mario = ImageIO.read(new File("src/marioright.png"));
            } catch(IOException error) {}
            repaint();
        }
        if(keyCode == KeyEvent.VK_W) {
            if(marioY - 5 >= 0) marioY -= 5;
            repaint();
        }
        if(keyCode == KeyEvent.VK_S) {
            if(marioY + 5 <= 435) marioY += 5;
            repaint();
        }

        if(keyCode == KeyEvent.VK_LEFT) {
            if(luigiX - 5 >= 0) luigiX -= 5;
            try {
                luigi = ImageIO.read(new File("src/luigileft.png"));
            } catch(IOException error) {}
            repaint();
        }
        if(keyCode == KeyEvent.VK_RIGHT) {
            if(luigiX + 5 <= 905) luigiX += 5;
            try {
                luigi = ImageIO.read(new File("src/luigiright.png"));
            } catch(IOException error) {}
            repaint();
        }
        if(keyCode == KeyEvent.VK_UP) {
            if(luigiY - 5 >= 0) luigiY -= 5;
            repaint();
        }
        if(keyCode == KeyEvent.VK_DOWN) {
            if(luigiY + 5 <= 435) luigiY += 5;
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
