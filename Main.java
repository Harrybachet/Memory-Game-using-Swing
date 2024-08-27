import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameBoard(4); // Creates a 4x4 grid
            }
        });
    }
}
