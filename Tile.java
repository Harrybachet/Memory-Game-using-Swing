import javax.swing.JButton;

public class Tile extends JButton {
    private int value;
    private boolean isFlipped;

    public Tile(int value) {
        this.value = value;
        this.isFlipped = false;
        this.setText("?");
    }

    public int getValue() {
        return value;
    }

    public boolean isFlipped() {
        return isFlipped;
    }

    public void flip() {
        isFlipped = !isFlipped;
        if (isFlipped) {
            this.setText(String.valueOf(value));
        } else {
            this.setText("?");
        }
    }

    public void reveal() {
        this.setText(String.valueOf(value));
        this.isFlipped = true;
    }

    public void hide() {
        this.setText("?");
        this.isFlipped = false;
    }
}
