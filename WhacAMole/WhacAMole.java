package WhacAMole;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.Border;

public class WhacAMole extends JPanel {

    int borderWidth = 600;
    int borderHeight = 650;

    JFrame frame = new JFrame("Mario: Whac A Mole");
    JLabel TextLabel = new JLabel(); // Text inside the window
    JPanel TextPanel = new JPanel(); // Holding the text/label
    JPanel boardPanel = new JPanel();

    JButton[] board = new JButton[9];

    ImageIcon molteIcon;
    ImageIcon plantIcon;

    JButton currMoleTile;
    JButton currPlant1Tile;
    JButton currPlant2Tile;

    Random random = new Random();
    Timer setMoleTimer;
    Timer setPlant1Timer;
    Timer setPlant2Timer;

    int Score = 0;

    public WhacAMole() {
        // frame.setVisible(true);
        frame.setSize(borderWidth, borderHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        TextLabel.setFont(new Font("Arial", Font.PLAIN, 50));
        TextLabel.setHorizontalAlignment(JLabel.CENTER);
        TextLabel.setText("Score: 0");
        TextLabel.setOpaque(true);

        TextPanel.setLayout(new BorderLayout());
        TextPanel.add(TextLabel);
        frame.add(TextPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        // boardPanel.setBackground(Color.black);
        frame.add(boardPanel);

        // plantIcon = new ImageIcon(getClass().getResource("./piranha.png")); icon size
        // is much bigger so we need to use another method
        Image plantImg = new ImageIcon(getClass().getResource("./piranha.png")).getImage();
        plantIcon = new ImageIcon(plantImg.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH)); // this two line
                                                                                                      // for perfect
                                                                                                      // image size for
                                                                                                      // button

        Image moleImg = new ImageIcon(getClass().getResource("./monty.png")).getImage();
        molteIcon = new ImageIcon(moleImg.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH)); // this two line
                                                                                                     // for perfect
                                                                                                     // image size for
                                                                                                     // button

        for (int i = 0; i < 9; i++) {
            JButton tile = new JButton();
            board[i] = tile;
            boardPanel.add(tile);
            tile.setFocusable(false); // to hide the rectangle surrounded by image/icon.
            // tile.setIcon(molteIcon);
            tile.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton tile = (JButton) e.getSource();
                    if (tile == currMoleTile) {
                        Score += 10;
                        TextLabel.setText("Score: " + Integer.toString(Score));
                    } else if (tile == currPlant1Tile || tile == currPlant2Tile) {
                        TextLabel.setText("Game Over: " + Integer.toString(Score));
                        setMoleTimer.stop();
                        setPlant1Timer.stop();
                        setPlant2Timer.stop();
                        for (int i = 0; i < 9; i++) {
                            board[i].setEnabled(false);// after game over we need to disable all the button
                        }
                    }

                }
            });

        }

        setMoleTimer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currMoleTile != null) {
                    currMoleTile.setIcon(null);
                    currMoleTile = null;
                }

                int num = random.nextInt(9); // 0-8 limit
                JButton tile = board[num];

                // if tile is occupied by the plant, then skip tile for this turn
                if (tile == currPlant1Tile || tile == currPlant2Tile) {
                    return;
                }

                currMoleTile = tile;
                currMoleTile.setIcon(molteIcon);
            }
        });

        setPlant1Timer = new Timer(1500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currPlant1Tile != null) {
                    currPlant1Tile.setIcon(null);
                    currPlant1Tile = null;
                }

                int num = random.nextInt(9); // 0-8 limit
                JButton tile = board[num];

                // if tile is occupied by the mole, then skip tile for this turn
                if (currMoleTile == tile) {
                    return;
                }

                currPlant1Tile = tile;
                currPlant1Tile.setIcon(plantIcon);
            }
        });

        setPlant2Timer = new Timer(1500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currPlant2Tile != null) {
                    currPlant2Tile.setIcon(null);
                    currPlant2Tile = null;
                }

                int num = random.nextInt(9); // 0-8 limit
                JButton tile = board[num];

                // if tile is occupied by the mole, then skip tile for this turn
                if (tile == currMoleTile || tile == currPlant1Tile) {
                    return;
                }

                currPlant2Tile = tile;
                currPlant2Tile.setIcon(plantIcon);
            }
        });

        setMoleTimer.start();
        setPlant1Timer.start();
        setPlant2Timer.start();
        frame.setVisible(true);
    }

}
