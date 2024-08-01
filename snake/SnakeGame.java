import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {

    static final int SCREEN_WIDTHX = 600; // final - this value is final so we used it
    static final int SCREEN_HEIGHTY = 600;
    static final int SIZE = 20;
    static final int DELAY = 180;

    final int[] x = new int[SCREEN_WIDTHX * SCREEN_HEIGHTY];
    final int[] y = new int[SCREEN_WIDTHX * SCREEN_HEIGHTY];

    int Score = 0;
    int bodyPart = 2;
    int FoodEater;
    int FoodX;
    int FoodY;
    int CurrentDirectionX = 1;
    int CurrentDirectionY = 0;
    boolean Running = false;
    Random random;
    Timer timer;

    public SnakeGame() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_HEIGHTY, SCREEN_WIDTHX));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(this);
        startGame();
    }

    public void startGame() {
        CreateFood();
        Running = true;
        x[0] = SCREEN_WIDTHX / 2;
        y[0] = SCREEN_HEIGHTY / 2;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (Running) {
            // for (int i = 0; i < SCREEN_HEIGHTY; i++) {
            // g.drawLine(i * SIZE, 0, i * SIZE, SCREEN_HEIGHTY);
            // g.drawLine(0, i * SIZE, SCREEN_WIDTHX, i * SIZE);
            // } this is for grid drawing
            // Draw the food
            g.setColor(Color.yellow);
            g.fillOval(FoodX, FoodY, SIZE, SIZE);

            // Draw the snake
            for (int i = 0; i < bodyPart; i++) {
                if (i == 0) {
                    // Draw the head
                    g.setColor(Color.red); // Color for the head
                    g.fillRoundRect(x[i], y[i], SIZE, SIZE, 10, 10); // Rounded rectangle for head
                } else {
                    // Draw the body
                    g.setColor(Color.red); // Color for the body
                    g.fillRect(x[i], y[i], SIZE, SIZE);
                }
            }
        } else {
            GameOver(g);
        }
    }

    public void GameOver(Graphics g) {
        g.setColor(Color.red);
        g.setFont(new Font("Serif", Font.BOLD, 75));
        FontMetrics met = getFontMetrics(g.getFont());
        g.drawString("Score : " + Score, SCREEN_HEIGHTY / 4, SCREEN_WIDTHX / 3);
        g.drawString("GAME OVER", SCREEN_HEIGHTY / 8, SCREEN_WIDTHX / 2);

    }

    public void move() {
        for (int i = bodyPart; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        x[0] = x[0] + CurrentDirectionX * SIZE;
        y[0] = y[0] + CurrentDirectionY * SIZE;
    }

    public void CreateFood() {
        FoodX = random.nextInt(((int) SCREEN_WIDTHX / SIZE)) * SIZE;
        FoodY = random.nextInt(((int) SCREEN_HEIGHTY / SIZE)) * SIZE;
    }

    public void CheckFood() {
        if (x[0] == FoodX && y[0] == FoodY) {
            Score++;
            CreateFood();
            bodyPart++;
        }
    }

    public void CheckCollied() {
        // Check if the head collides with the body
        for (int i = bodyPart; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) { // head and body if snake bites itself
                Running = false;
            }
        }

        // Check if the head collides with the boundaries
        if (x[0] < 0 || x[0] >= SCREEN_WIDTHX || y[0] < 0 || y[0] >= SCREEN_HEIGHTY) { // if snake bites the edge of the
                                                                                       // grid
            Running = false;
        }

        // Stop the timer if the game is no longer running
        if (!Running) {
            timer.stop();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO: Implement logic
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO: Implement logic
        int Key = e.getKeyCode();
        switch (Key) {

            case KeyEvent.VK_LEFT:
                if (CurrentDirectionX != 1) { // stop moving right
                    CurrentDirectionX = -1; // moving left
                    CurrentDirectionY = 0; // stop up and down movement
                    break;
                }

            case KeyEvent.VK_RIGHT:
                if (CurrentDirectionX != -1) { // stop moving left
                    CurrentDirectionX = 1; // moving right
                    CurrentDirectionY = 0; // stop up and down
                    break;
                }

            case KeyEvent.VK_UP:
                if (CurrentDirectionY != 1) { // stop moving down
                    CurrentDirectionY = -1; // moving up
                    CurrentDirectionX = 0; // stop left and right
                    break;
                }

            case KeyEvent.VK_DOWN:
                if (CurrentDirectionY != -1) { // stop moving up
                    CurrentDirectionY = 1; // moving down
                    CurrentDirectionX = 0; // stop left and right
                    break;
                }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO: Implement logic
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO: Implement logic
        if (Running) {
            move();
            CheckFood();
            CheckCollied();
        }
        repaint();
    }
}
