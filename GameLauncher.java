import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import TicTacToe.TicTacToe;
import TicTacToe.ttt;
import WhacAMole.App;
import WhacAMole.WhacAMole;

public class GameLauncher {

    JFrame frame = new JFrame("Game Launcher");
    JPanel panel = new JPanel();

    JButton snakeButton = new JButton("Snake Game");
    JButton ticTacToeButton = new JButton("Tic Tac Toe");
    JButton whacAMoleButton = new JButton("Whac-A-Mole");

    public GameLauncher() {
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        panel.setLayout(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        snakeButton.setFont(new Font("Arial", Font.PLAIN, 20));
        snakeButton.setFocusable(false);
        ticTacToeButton.setFont(new Font("Arial", Font.PLAIN, 20));
        ticTacToeButton.setFocusable(false);
        whacAMoleButton.setFont(new Font("Arial", Font.PLAIN, 20));
        whacAMoleButton.setFocusable(false);

        snakeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Launch Snake Game
                JFrame snakeFrame = new JFrame("Snake Game");
                SnakeGame snakeGame = new SnakeGame();
                snakeFrame.add(snakeGame);
                snakeFrame.setSize(SnakeGame.SCREEN_WIDTHX, SnakeGame.SCREEN_HEIGHTY);
                snakeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                snakeFrame.setLocationRelativeTo(null);
                snakeFrame.setVisible(true);
                // Ensure SnakeGame class has a startGame() method if needed
                // snakeGame.startGame();
            }
        });

        ticTacToeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Launch Tic Tac Toe Game
                JFrame tictactoeFrame = new JFrame("Tic Tac Toe");
                TicTacToe ttt = new TicTacToe();
                tictactoeFrame.add(ttt);
                tictactoeFrame.setSize(600, 650); // Adjust size if needed
                tictactoeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                tictactoeFrame.setLocationRelativeTo(null);
                tictactoeFrame.setVisible(true);
            }
        });

        whacAMoleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Launch Whac-A-Mole Game
                JFrame whacamoleFrame = new JFrame("Whac A Mole");
                WhacAMole wamPanel = new WhacAMole(); // Directly use the constructor
                whacamoleFrame.add(wamPanel);
                whacamoleFrame.setSize(600, 650); // Set the size according to your game dimensions
                whacamoleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                whacamoleFrame.setLocationRelativeTo(null);
                whacamoleFrame.setVisible(true);
            }
        });

        panel.add(snakeButton);
        panel.add(ticTacToeButton);
        panel.add(whacAMoleButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GameLauncher();
    }
}
