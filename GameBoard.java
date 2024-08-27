import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class GameBoard extends JFrame {
    private ArrayList<Tile> tiles;
    private Tile firstTile;
    private Tile secondTile;
    private int pairsFound;
    private Timer timer;

    public GameBoard(int gridSize) {
        setTitle("Memory Game");
        setLayout(new GridLayout(gridSize, gridSize));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);

        tiles = new ArrayList<>();
        int numPairs = (gridSize * gridSize) / 2;
        ArrayList<Integer> values = new ArrayList<>();

        for (int i = 1; i <= numPairs; i++) {
            values.add(i);
            values.add(i);
        }

        Collections.shuffle(values);

        for (int value : values) {
            Tile tile = new Tile(value);
            tile.addActionListener(new TileClickListener());
            tiles.add(tile);
            add(tile);
        }

        pairsFound = 0;

        setVisible(true);
    }

    private class TileClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Tile clickedTile = (Tile) e.getSource();

            if (clickedTile.isFlipped() || secondTile != null) {
                return;
            }

            clickedTile.flip();

            if (firstTile == null) {
                firstTile = clickedTile;
            } else {
                secondTile = clickedTile;
                checkForMatch();
            }
        }

        private void checkForMatch() {
            if (firstTile.getValue() == secondTile.getValue()) {
                firstTile.setEnabled(false);
                secondTile.setEnabled(false);
                firstTile = null;
                secondTile = null;
                pairsFound++;

                if (pairsFound == tiles.size() / 2) {
                    JOptionPane.showMessageDialog(GameBoard.this, "You won!");
                }
            } else {
                timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        firstTile.flip();
                        secondTile.flip();
                        firstTile = null;
                        secondTile = null;
                        timer.stop();
                    }
                });
                timer.start();
            }
        }
    }
}
