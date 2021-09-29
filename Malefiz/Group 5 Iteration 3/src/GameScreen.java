import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class GameScreen extends JPanel implements ActionListener {
    private int x, y;
    private GameSquare[][] gameSquares;

    public GameScreen(int x, int y) {
        this.x = x;
        this.y = y;
        this.setLayout(new GridLayout(y, x, 1, 2));
        gameSquares = new GameSquare[y][x];
        for (int row = 0; row < y; row++){
            for (int column = 0; column < x; column++){
                gameSquares[row][column] = new GameSquare(row, column);
                gameSquares[row][column].addActionListener(this);
                this.add(gameSquares[row][column]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Square Selected");
    }
}
