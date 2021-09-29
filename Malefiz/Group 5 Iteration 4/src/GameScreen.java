import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

/*
Panel containing the gameplay
 */
public class GameScreen extends JPanel implements ActionListener {
    private int x, y, turn, round, oldx, oldy, diceValue;
    private GameSquare[][] gameSquares;
    public boolean freshDice, movingBarricade;

    public GameScreen(int x, int y, int numPlayers, int diceValue) {
        // Initializing the variables
        this.x = x;
        this.y = y;
        this.turn = 1;
        this.round = 1;
        this.freshDice = false;
        this.diceValue = diceValue;
        this.movingBarricade = false;
        // Setting up the grid for the squares
        this.setLayout(new GridLayout(y, x, 1, 2));
        gameSquares = new GameSquare[y][x];
        // Setting up all the different squares of the board
        for (int row = 0; row < y; row++) {
            for (int column = 0; column < x; column++) {
                gameSquares[row][column] = new GameSquare(row, column);
                gameSquares[row][column].addActionListener(this);
                this.add(gameSquares[row][column]);
            }
        }

    }

    // Sets the dice value for the player
    public void setDiceValue(int newDiceValue){
        this.diceValue = newDiceValue;
    }

    // Actions to be taken when a square is selected
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof GameSquare) {
            // Checks if a barricade is being moved and if so lets the player move the barricade
            if (movingBarricade){
                ((GameSquare) source).setIcon(new ImageIcon("barricadepiece.jpg"));
                int ycounter = 0;
                while (ycounter < 14){
                    int xcounter = 0;
                    while (xcounter < 17) {
                        if (((GameSquare) source).isRealBox(ycounter, xcounter)) {
                            gameSquares[ycounter][xcounter].setBorderPainted(false);
                        }
                        xcounter++;
                    }
                    ycounter++;
                }
                ((GameSquare) source).setnewBarricadeCoordinate(oldy, oldx);
                movingBarricade = false;
            }
            // Checks if a new piece is being lifted and if a new dice has been rolled
            if (round == 1  && freshDice) {
                if (((GameSquare) source).isRedStartSquare()) {
                    if (turn == 1) {
                        System.out.println("Got Red Right!");
                        // Finds out all the valid coordinates for a piece
                        ((GameSquare) source).findValidCoordinates(diceValue, new int[]{-1, -1}, 1);
                        int counter = 0;
                        while (counter < ((GameSquare) source).validmoves.size()) {
                            gameSquares[((GameSquare) source).validmoves.get(counter)[0]][((GameSquare) source).validmoves.
                                    get(counter)[1]].setIcon(null);
                            gameSquares[((GameSquare) source).validmoves.get(counter)[0]][((GameSquare) source).
                                    validmoves.get(counter)[1]].setBorderPainted(true);
                            counter++;
                        }
                        // Sets the current coordinates of a piece as the previous coordinates
                        oldx = ((GameSquare) source).getxCoord();
                        oldy = ((GameSquare) source).getyCoord();
                        round++;
                        if (((GameSquare) source).getvalidmovesSize() == 0){
                            turn++;
                            if (turn == 5){
                                turn = 1;
                            }
                            round = 1;
                        }
                    } else {
                        System.out.println("Got Red Wrong!");
                    }
                    // Repeats the same procedure for all other colours
                } else if (((GameSquare) source).isGreenStartSquare()) {
                    if (turn == 2) {
                        System.out.println("Got Green Right!");
                        ((GameSquare) source).findValidCoordinates(diceValue, new int[]{-1, -1}, 2);
                        int counter = 0;
                        while (counter < ((GameSquare) source).validmoves.size()) {
                            gameSquares[((GameSquare) source).validmoves.get(counter)[0]][((GameSquare) source).validmoves.
                                    get(counter)[1]].setIcon(null);
                            gameSquares[((GameSquare) source).validmoves.get(counter)[0]][((GameSquare) source).
                                    validmoves.get(counter)[1]].setBorderPainted(true);
                            counter++;
                        }
                        oldx = ((GameSquare) source).getxCoord();
                        oldy = ((GameSquare) source).getyCoord();
                        round++;

                        System.out.println(((GameSquare) source).getvalidmovesSize());
                        if (((GameSquare) source).getvalidmovesSize() == 0){
                            turn++;
                            if (turn == 5){
                                turn = 0;
                            }
                            round = 1;
                        }
                    } else {
                        System.out.println("Got Green Wrong!");
                    }
                } else if (((GameSquare) source).isBlueStartSquare()) {
                    if (turn == 3) {
                        System.out.println("Got Blue Right!");
                        ((GameSquare) source).findValidCoordinates(diceValue, new int[]{-1, -1}, 3);
                        int counter = 0;
                        while (counter < ((GameSquare) source).validmoves.size()) {
                            gameSquares[((GameSquare) source).validmoves.get(counter)[0]][((GameSquare) source).validmoves.
                                    get(counter)[1]].setIcon(null);
                            gameSquares[((GameSquare) source).validmoves.get(counter)[0]][((GameSquare) source).
                                    validmoves.get(counter)[1]].setBorderPainted(true);
                            counter++;
                        }
                        oldx = ((GameSquare) source).getxCoord();
                        oldy = ((GameSquare) source).getyCoord();
                        round++;

                        System.out.println(((GameSquare) source).getvalidmovesSize());
                        if (((GameSquare) source).getvalidmovesSize() == 0){
                            turn++;
                            if (turn == 5){
                                turn = 0;
                            }
                            round = 1;
                        }
                    } else {
                        System.out.println("Got Blue Wrong!");
                    }
                } else if (((GameSquare) source).isYellowStartSquare()) {
                    if (turn == 4) {
                        System.out.println("Got Yellow Right!");
                        ((GameSquare) source).findValidCoordinates(diceValue, new int[]{-1, -1}, 4);
                        int counter = 0;
                        while (counter < ((GameSquare) source).validmoves.size()) {
                            gameSquares[((GameSquare) source).validmoves.get(counter)[0]][((GameSquare) source).validmoves.
                                    get(counter)[1]].setIcon(null);
                            gameSquares[((GameSquare) source).validmoves.get(counter)[0]][((GameSquare) source).
                                    validmoves.get(counter)[1]].setBorderPainted(true);
                            counter++;
                        }
                        oldx = ((GameSquare) source).getxCoord();
                        oldy = ((GameSquare) source).getyCoord();
                        round++;

                        System.out.println(((GameSquare) source).getvalidmovesSize());
                        if (((GameSquare) source).getvalidmovesSize() == 0){
                            turn++;
                            if (turn == 5){
                                turn = 0;
                            }
                            round = 1;
                        }
                    } else {
                        System.out.println("Got Yellow Wrong!");
                    }
                }
                // Checks if a selected piece is being placed in a new location
            } else if ((round == 2)) {
                // Checks if the new location is within the valid moves
                if (((GameSquare) source).isValidNextMove()) {
                    int counter = 0;
                    while (counter < ((GameSquare) source).validmoves.size()) {
                        gameSquares[((GameSquare) source).validmoves.get(counter)[0]][((GameSquare) source).validmoves.
                                get(counter)[1]].setIcon(new ImageIcon("RegularSquare.jpg"));
                        gameSquares[((GameSquare) source).validmoves.get(counter)[0]][((GameSquare) source).
                                validmoves.get(counter)[1]].setBorderPainted(false);
                        counter++;
                    }
                    // Moves the piece and moves any existing pieces in the current location of the piece into their original location
                    if (turn == 1) {
                        if (((GameSquare) source).isGreenStartSquare()) {
                            if (!((GameSquare) source).isGreenValidMoves(14, 5)){
                                gameSquares[14][5].setIcon(new ImageIcon("greenpiece.jpg"));
                            }else if (!((GameSquare) source).isGreenValidMoves(14, 6)){
                                gameSquares[14][6].setIcon(new ImageIcon("greenpiece.jpg"));
                            }else if (!((GameSquare) source).isGreenValidMoves(14, 7)){
                                gameSquares[14][7].setIcon(new ImageIcon("greenpiece.jpg"));
                            }else if (!((GameSquare) source).isGreenValidMoves(15, 5)){
                                gameSquares[15][5].setIcon(new ImageIcon("greenpiece.jpg"));
                            }else if (!((GameSquare) source).isGreenValidMoves(15, 7)){
                                gameSquares[15][7].setIcon(new ImageIcon("greenpiece.jpg"));
                            }
                        }
                        if (((GameSquare) source).isBlueStartSquare()) {
                            if (!((GameSquare) source).isBlueValidMoves(14, 9)){
                                gameSquares[14][9].setIcon(new ImageIcon("bluepiece.jpg"));
                            }else if (!((GameSquare) source).isBlueValidMoves(14, 10)){
                                gameSquares[14][10].setIcon(new ImageIcon("bluepiece.jpg"));
                            }else if (!((GameSquare) source).isBlueValidMoves(14, 11)){
                                gameSquares[14][11].setIcon(new ImageIcon("bluepiece.jpg"));
                            }else if (!((GameSquare) source).isBlueValidMoves(15, 9)){
                                gameSquares[15][9].setIcon(new ImageIcon("bluepiece.jpg"));
                            }else if (!((GameSquare) source).isBlueValidMoves(15, 11)){
                                gameSquares[15][11].setIcon(new ImageIcon("bluepiece.jpg"));
                            }
                        }

                        if (((GameSquare) source).isYellowStartSquare()) {
                            if (!((GameSquare) source).isYellowValidMoves(14, 13)){
                                gameSquares[14][13].setIcon(new ImageIcon("yellowpiece.jpg"));
                            }else if (!((GameSquare) source).isYellowValidMoves(14, 14)){
                                gameSquares[14][14].setIcon(new ImageIcon("yellowpiece.jpg"));
                            }else if (!((GameSquare) source).isYellowValidMoves(14, 15)){
                                gameSquares[14][15].setIcon(new ImageIcon("yellowpiece.jpg"));
                            }else if (!((GameSquare) source).isYellowValidMoves(15, 13)){
                                gameSquares[15][13].setIcon(new ImageIcon("yellowpiece.jpg"));
                            }else if (!((GameSquare) source).isYellowValidMoves(15, 15)){
                                gameSquares[15][15].setIcon(new ImageIcon("yellowpiece.jpg"));
                            }
                        }
                        ((GameSquare) source).setnewRedCoordinate(oldy, oldx);
                        ((GameSquare) source).setIcon(new ImageIcon("redpiece.jpg"));
                        gameSquares[oldy][oldx].setIcon(new ImageIcon("RegularSquare.jpg"));
                        turn++;
                        round = 1;
                        oldx = ((GameSquare) source).getxCoord();
                        oldy = ((GameSquare) source).getyCoord();
                        if (((GameSquare) source).getxCoord() == 0 && ((GameSquare) source).getyCoord() == 8){
                            JOptionPane.showMessageDialog(this, "Player 1 Wins!!!.");
                        }
                        // Repeat the same procedure for all colours
                    } else if (turn == 2) {
                        if (((GameSquare) source).isRedStartSquare()) {
                            if (!((GameSquare) source).isRedValidMoves(14, 1)){
                                gameSquares[14][1].setIcon(new ImageIcon("redpiece.jpg"));
                            }else if (!((GameSquare) source).isRedValidMoves(14, 2)){
                                gameSquares[14][2].setIcon(new ImageIcon("redpiece.jpg"));
                            }else if (!((GameSquare) source).isRedValidMoves(14, 3)){
                                gameSquares[14][3].setIcon(new ImageIcon("redpiece.jpg"));
                            }else if (!((GameSquare) source).isRedValidMoves(15, 1)){
                                gameSquares[15][1].setIcon(new ImageIcon("redpiece.jpg"));
                            }else if (!((GameSquare) source).isRedValidMoves(15, 3)){
                                gameSquares[15][3].setIcon(new ImageIcon("redpiece.jpg"));
                            }
                        }
                        if (((GameSquare) source).isBlueStartSquare()) {
                            if (!((GameSquare) source).isBlueValidMoves(14, 9)){
                                gameSquares[14][9].setIcon(new ImageIcon("bluepiece.jpg"));
                            }else if (!((GameSquare) source).isBlueValidMoves(14, 10)){
                                gameSquares[14][10].setIcon(new ImageIcon("bluepiece.jpg"));
                            }else if (!((GameSquare) source).isBlueValidMoves(14, 11)){
                                gameSquares[14][11].setIcon(new ImageIcon("bluepiece.jpg"));
                            }else if (!((GameSquare) source).isBlueValidMoves(15, 9)){
                                gameSquares[15][9].setIcon(new ImageIcon("bluepiece.jpg"));
                            }else if (!((GameSquare) source).isBlueValidMoves(15, 11)){
                                gameSquares[15][11].setIcon(new ImageIcon("bluepiece.jpg"));
                            }
                        }

                        if (((GameSquare) source).isYellowStartSquare()) {
                            if (!((GameSquare) source).isYellowValidMoves(14, 13)){
                                gameSquares[14][13].setIcon(new ImageIcon("yellowpiece.jpg"));
                            }else if (!((GameSquare) source).isYellowValidMoves(14, 14)){
                                gameSquares[14][14].setIcon(new ImageIcon("yellowpiece.jpg"));
                            }else if (!((GameSquare) source).isYellowValidMoves(14, 15)){
                                gameSquares[14][15].setIcon(new ImageIcon("yellowpiece.jpg"));
                            }else if (!((GameSquare) source).isYellowValidMoves(15, 13)){
                                gameSquares[15][13].setIcon(new ImageIcon("yellowpiece.jpg"));
                            }else if (!((GameSquare) source).isYellowValidMoves(15, 15)){
                                gameSquares[15][15].setIcon(new ImageIcon("yellowpiece.jpg"));
                            }
                        }
                        ((GameSquare) source).setnewGreenCoordinate(oldy, oldx);
                        ((GameSquare) source).setIcon(new ImageIcon("greenpiece.jpg"));
                        gameSquares[oldy][oldx].setIcon(new ImageIcon("RegularSquare.jpg"));
                        turn++;
                        round = 1;
                        oldx = ((GameSquare) source).getxCoord();
                        oldy = ((GameSquare) source).getyCoord();
                        if (((GameSquare) source).getxCoord() == 0 && ((GameSquare) source).getyCoord() == 8){
                            JOptionPane.showMessageDialog(this, "Player 2 Wins!!!.");
                        }
                    } else if (turn == 3) {
                        if (((GameSquare) source).isRedStartSquare()) {
                            if (!((GameSquare) source).isRedValidMoves(14, 1)){
                                gameSquares[14][1].setIcon(new ImageIcon("redpiece.jpg"));
                            }else if (!((GameSquare) source).isRedValidMoves(14, 2)){
                                gameSquares[14][2].setIcon(new ImageIcon("redpiece.jpg"));
                            }else if (!((GameSquare) source).isRedValidMoves(14, 3)){
                                gameSquares[14][3].setIcon(new ImageIcon("redpiece.jpg"));
                            }else if (!((GameSquare) source).isRedValidMoves(15, 1)){
                                gameSquares[15][1].setIcon(new ImageIcon("redpiece.jpg"));
                            }else if (!((GameSquare) source).isRedValidMoves(15, 3)){
                                gameSquares[15][3].setIcon(new ImageIcon("redpiece.jpg"));
                            }
                        }
                        if (((GameSquare) source).isGreenStartSquare()) {
                            if (!((GameSquare) source).isGreenValidMoves(14, 5)){
                                gameSquares[14][5].setIcon(new ImageIcon("greenpiece.jpg"));
                            }else if (!((GameSquare) source).isGreenValidMoves(14, 6)){
                                gameSquares[14][6].setIcon(new ImageIcon("greenpiece.jpg"));
                            }else if (!((GameSquare) source).isGreenValidMoves(14, 7)){
                                gameSquares[14][7].setIcon(new ImageIcon("greenpiece.jpg"));
                            }else if (!((GameSquare) source).isGreenValidMoves(15, 5)){
                                gameSquares[15][5].setIcon(new ImageIcon("greenpiece.jpg"));
                            }else if (!((GameSquare) source).isGreenValidMoves(15, 7)){
                                gameSquares[15][7].setIcon(new ImageIcon("greenpiece.jpg"));
                            }
                        }
                        if (((GameSquare) source).isYellowStartSquare()) {
                            if (!((GameSquare) source).isYellowValidMoves(14, 13)){
                                gameSquares[14][13].setIcon(new ImageIcon("yellowpiece.jpg"));
                            }else if (!((GameSquare) source).isYellowValidMoves(14, 14)){
                                gameSquares[14][14].setIcon(new ImageIcon("yellowpiece.jpg"));
                            }else if (!((GameSquare) source).isYellowValidMoves(14, 15)){
                                gameSquares[14][15].setIcon(new ImageIcon("yellowpiece.jpg"));
                            }else if (!((GameSquare) source).isYellowValidMoves(15, 13)){
                                gameSquares[15][13].setIcon(new ImageIcon("yellowpiece.jpg"));
                            }else if (!((GameSquare) source).isYellowValidMoves(15, 15)){
                                gameSquares[15][15].setIcon(new ImageIcon("yellowpiece.jpg"));
                            }
                        }
                        ((GameSquare) source).setnewBlueCoordinate(oldy, oldx);
                        ((GameSquare) source).setIcon(new ImageIcon("bluepiece.jpg"));
                        gameSquares[oldy][oldx].setIcon(new ImageIcon("RegularSquare.jpg"));
                        turn++;
                        round = 1;
                        oldx = ((GameSquare) source).getxCoord();
                        oldy = ((GameSquare) source).getyCoord();
                        if (((GameSquare) source).getxCoord() == 0 && ((GameSquare) source).getyCoord() == 8){
                            JOptionPane.showMessageDialog(this, "Player 3 Wins!!!.");
                        }
                    } else if (turn == 4) {
                        if (((GameSquare) source).isRedStartSquare()) {
                            if (!((GameSquare) source).isRedValidMoves(14, 1)){
                                gameSquares[14][1].setIcon(new ImageIcon("redpiece.jpg"));
                            }else if (!((GameSquare) source).isRedValidMoves(14, 2)){
                                gameSquares[14][2].setIcon(new ImageIcon("redpiece.jpg"));
                            }else if (!((GameSquare) source).isRedValidMoves(14, 3)){
                                gameSquares[14][3].setIcon(new ImageIcon("redpiece.jpg"));
                            }else if (!((GameSquare) source).isRedValidMoves(15, 1)){
                                gameSquares[15][1].setIcon(new ImageIcon("redpiece.jpg"));
                            }else if (!((GameSquare) source).isRedValidMoves(15, 3)){
                                gameSquares[15][3].setIcon(new ImageIcon("redpiece.jpg"));
                            }
                        }
                        if (((GameSquare) source).isGreenStartSquare()) {
                            if (!((GameSquare) source).isGreenValidMoves(14, 5)){
                                gameSquares[14][5].setIcon(new ImageIcon("greenpiece.jpg"));
                            }else if (!((GameSquare) source).isGreenValidMoves(14, 6)){
                                gameSquares[14][6].setIcon(new ImageIcon("greenpiece.jpg"));
                            }else if (!((GameSquare) source).isGreenValidMoves(14, 7)){
                                gameSquares[14][7].setIcon(new ImageIcon("greenpiece.jpg"));
                            }else if (!((GameSquare) source).isGreenValidMoves(15, 5)){
                                gameSquares[15][5].setIcon(new ImageIcon("greenpiece.jpg"));
                            }else if (!((GameSquare) source).isGreenValidMoves(15, 7)){
                                gameSquares[15][7].setIcon(new ImageIcon("greenpiece.jpg"));
                            }
                        }
                        if (((GameSquare) source).isBlueStartSquare()) {
                            if (!((GameSquare) source).isBlueValidMoves(14, 9)){
                                gameSquares[14][9].setIcon(new ImageIcon("bluepiece.jpg"));
                            }else if (!((GameSquare) source).isBlueValidMoves(14, 10)){
                                gameSquares[14][10].setIcon(new ImageIcon("bluepiece.jpg"));
                            }else if (!((GameSquare) source).isBlueValidMoves(14, 11)){
                                gameSquares[14][11].setIcon(new ImageIcon("bluepiece.jpg"));
                            }else if (!((GameSquare) source).isBlueValidMoves(15, 9)){
                                gameSquares[15][9].setIcon(new ImageIcon("bluepiece.jpg"));
                            }else if (!((GameSquare) source).isBlueValidMoves(15, 11)){
                                gameSquares[15][11].setIcon(new ImageIcon("bluepiece.jpg"));
                            }
                        }
                        ((GameSquare) source).setnewYellowCoordinate(oldy, oldx);
                        ((GameSquare) source).setIcon(new ImageIcon("yellowpiece.jpg"));
                        gameSquares[oldy][oldx].setIcon(new ImageIcon("RegularSquare.jpg"));
                        turn = 1;
                        round = 1;
                        oldx = ((GameSquare) source).getxCoord();
                        oldy = ((GameSquare) source).getyCoord();
                        if (((GameSquare) source).getxCoord() == 0 && ((GameSquare) source).getyCoord() == 8){
                            JOptionPane.showMessageDialog(this, "Player 4 Wins!!!.");
                        }
                    }
                    // Checks if a fresh dice is being rolled
                    freshDice = false;
                    // Checks if the piece selected is a barricade
                    if (((GameSquare) source).isBarricade()) {
                        movingBarricade = true;
                        int ycounter = 0;
                        while (ycounter < 14) {
                            int xcounter = 0;
                            while (xcounter < 17) {
                                if (((GameSquare) source).isRealBox(ycounter, xcounter)) {
                                    gameSquares[ycounter][xcounter].setBorderPainted(true);
                                }
                                xcounter++;
                            }
                            ycounter++;
                        }
                    }
                }
            }
        }
    }
}
