import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GameSquare extends JButton {
    private int xCoord, yCoord, lineCounter;
    //Replace with piece and barricade classes when they are created
    private String piece, barricade;
    private final int initx, inity;

    public static ArrayList<int[]> barricadeCoordinates, redSquares, blueSquares, greenSquares, yellowSquares, validmoves;
    public GameSquare(int yCoord, int xCoord) {
        super();
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.initx = xCoord;
        this.inity = yCoord;

        // Initializing all the arraylists
        validmoves = new ArrayList<int[]>();
        barricadeCoordinates = new ArrayList<int[]>();
        redSquares = new ArrayList<int[]>();
        blueSquares = new ArrayList<int[]>();
        greenSquares = new ArrayList<int[]>();
        yellowSquares = new ArrayList<int[]>();

        // Setting up the Arraylist containing the positions of all the squares of different pieces
        redSquares.add(new int[]{14, 1});
        redSquares.add(new int[]{14, 2});
        redSquares.add(new int[]{14, 3});
        redSquares.add(new int[]{15, 1});
        redSquares.add(new int[]{15, 3});

        greenSquares.add(new int[]{14, 5});
        greenSquares.add(new int[]{14, 6});
        greenSquares.add(new int[]{14, 7});
        greenSquares.add(new int[]{15, 5});
        greenSquares.add(new int[]{15, 7});

        blueSquares.add(new int[]{14, 9});
        blueSquares.add(new int[]{14, 10});
        blueSquares.add(new int[]{14, 11});
        blueSquares.add(new int[]{15, 9});
        blueSquares.add(new int[]{15, 11});

        yellowSquares.add(new int[]{14, 13});
        yellowSquares.add(new int[]{14, 14});
        yellowSquares.add(new int[]{14, 15});
        yellowSquares.add(new int[]{15, 13});
        yellowSquares.add(new int[]{15, 15});

        barricadeCoordinates.add(new int[]{1, 8});
        barricadeCoordinates.add(new int[]{11, 0});
        barricadeCoordinates.add(new int[]{11, 4});
        barricadeCoordinates.add(new int[]{11, 8});
        barricadeCoordinates.add(new int[]{11, 12});
        barricadeCoordinates.add(new int[]{11, 16});
        barricadeCoordinates.add(new int[]{3, 8});
        barricadeCoordinates.add(new int[]{4, 8});
        barricadeCoordinates.add(new int[]{5, 8});
        barricadeCoordinates.add(new int[]{7, 6});
        barricadeCoordinates.add(new int[]{7, 10});

        /*
        findValidCoordinates(3, 3, 3, new int[]{-1, -1});
        for (int[] i : validmoves){
            System.out.println(String.valueOf(i[0]) + " " + String.valueOf(i[1]));
        }

         */

        // Setting the game icons and setting up the board
        Icon icon_regular = new ImageIcon("RegularSquare.jpg");
        Icon icon_landing = new ImageIcon("landingsquare.jpg");
        Icon red_piece = new ImageIcon("redpiece.jpg");
        this.setPreferredSize(new Dimension(40, 40));
        if (isRealBox(yCoord, xCoord)) {
            this.setIcon(icon_regular);
            this.setOpaque(false);
            this.setContentAreaFilled(false);
            this.setBorderPainted(false);
            this.setFocusPainted(false);
        } else {
            this.setEnabled(false);
            this.setOpaque(false);
            this.setVisible(false);
        }
        if (isLandingSquare(yCoord, xCoord)) {
            this.setIcon(icon_landing);
        }
        if (isRedStartSquare()){
            this.setIcon(new ImageIcon("redpiece.jpg"));
        }
        if (isGreenStartSquare()){
            this.setIcon(new ImageIcon("greenpiece.jpg"));
        }
        if (isBlueStartSquare()){
            this.setIcon(new ImageIcon("bluepiece.jpg"));
        }
        if (isYellowStartSquare()){
            this.setIcon(new ImageIcon("yellowpiece.jpg"));
        }
        if (isBarricade()){
            this.setIcon(new ImageIcon("barricadepiece.jpg"));
        }
    }

    // This method was not used but only used in testing!
    public void squareSelected(String piece) {
        this.setForeground(Color.BLACK);
        this.setEnabled(false);
        if (piece.equals("X1")) {
            System.out.println("X1 has clicked");
        } else {
            System.out.println("X2 has clicked");
        }
    }

    // Checks if a square is a valid square for the game
    public boolean isRealBox(int yCoord, int xCoord) {
        ArrayList<String[]> lines = new ArrayList<String[]>();
        try {
            File myObj = new File("Layout.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                lines.add(data.split(" "));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return lines.get(yCoord)[xCoord].equals("1");
    }

    // Checks if the selected square has a barricade in it
    public boolean isBarricade() {
        boolean squareBarriacade = false;
        for (int[]x : barricadeCoordinates){
            if (x[0] == yCoord && x[1] == xCoord){
                return true;
            }
        }
        return squareBarriacade;
    }

    // Checks if the square a piece is moving into has a barricade in it.
    public boolean isBarricadeValidMoves(int yCoord, int xCoord) {
        boolean squareBarriacade = false;
        for (int[]x : barricadeCoordinates){
            if (x[0] == yCoord && x[1] == xCoord){
                return true;
            }
        }
        return squareBarriacade;
    }

    // Checks if a square a piece is going into has a coloured piece
    public boolean isRedValidMoves(int yCoord, int xCoord) {
        boolean squareRed = false;
        for (var x : redSquares)
            if (Arrays.equals(x, new int[]{yCoord, xCoord})) {
                return true;
            }
        return squareRed;
    }

    public boolean isGreenValidMoves(int yCoord, int xCoord) {
        boolean squareGreen = false;
        for (var x : greenSquares)
            if (Arrays.equals(x, new int[]{yCoord, xCoord})) {
                return true;
            }
        return squareGreen;
    }

    public boolean isBlueValidMoves(int yCoord, int xCoord) {
        boolean squareBlue = false;
        for (var x : blueSquares)
            if (Arrays.equals(x, new int[]{yCoord, xCoord})) {
                return true;
            }
        return squareBlue;
    }

    public boolean isYellowValidMoves(int yCoord, int xCoord) {
        boolean squareYellow = false;
        for (var x : yellowSquares)
            if (Arrays.equals(x, new int[]{yCoord, xCoord})) {
                return true;
            }
        return squareYellow;
    }

    // Checks if a square is the initial square for a certain colour
    public boolean isLandingSquare(int yCoord, int xCoord) {
        boolean landingSquare = false;
        if (yCoord == 13) {
            if ((xCoord == 2) || (xCoord == 6) || (xCoord == 10) || (xCoord == 14)) {
                landingSquare = true;
            }
        }
        return landingSquare;
    }

    // Methods that check if a square contains a certain coloured piece
    public boolean isRedStartSquare() {
        boolean squareRed = false;
        for (int[]x : redSquares){
            if (x[0] == yCoord && x[1] == xCoord){
                return true;
            }
        }
        return squareRed;
    }

    public boolean isGreenStartSquare() {
        boolean squareGreen = false;
        for (int[]x : greenSquares){
            if (x[0] == yCoord && x[1] == xCoord){
                return true;
            }
        }
        return squareGreen;
    }


    public boolean isBlueStartSquare() {
        boolean squareBlue = false;
        for (int[] x : blueSquares) {
            if (x[0] == yCoord && x[1] == xCoord) {
                return true;
            }
        }
        return squareBlue;
    }

    public boolean isYellowStartSquare() {
        boolean squareYellow = false;
        for (int[]x : yellowSquares){
            if (x[0] == yCoord && x[1] == xCoord){
                return true;
            }
        }
        return squareYellow;
    }

    // Recursive method to check for the valid squares in a certain general direction
    public void findvalid(int diceCount, int yCoord, int xCoord, int[] previous) {
        if (diceCount == 0) {
            validmoves.add(new int[]{yCoord, xCoord});
        } else {
            if (isRealBox(Math.max(0, yCoord - 1), xCoord) && (yCoord-1 != previous[0]) && (yCoord != 0) & !(isBarricadeValidMoves(yCoord, xCoord)) & !(isRedValidMoves(yCoord, xCoord)) & !(isGreenValidMoves(yCoord, xCoord)) & !(isBlueValidMoves(yCoord, xCoord)) & !(isYellowValidMoves(yCoord, xCoord)) ){
                findvalid(diceCount - 1, yCoord - 1, xCoord, new int[]{yCoord, xCoord});
            }
            if (isRealBox(Math.min(13, yCoord + 1), xCoord) && (yCoord+1 != previous[0]) && (yCoord != 13) & !(isBarricadeValidMoves(yCoord, xCoord))& !(isRedValidMoves(yCoord, xCoord)) & !(isGreenValidMoves(yCoord, xCoord)) & !(isBlueValidMoves(yCoord, xCoord)) & !(isYellowValidMoves(yCoord, xCoord)) ) {
                findvalid(diceCount - 1, yCoord + 1, xCoord, new int[]{yCoord, xCoord});
            }
            if (isRealBox(yCoord, Math.max(0, xCoord-1)) && (xCoord-1 != previous[1]) && (xCoord != 0) & !(isBarricadeValidMoves(yCoord, xCoord))& !(isRedValidMoves(yCoord, xCoord)) & !(isGreenValidMoves(yCoord, xCoord)) & !(isBlueValidMoves(yCoord, xCoord)) & !(isYellowValidMoves(yCoord, xCoord)) ) {
                findvalid(diceCount - 1, yCoord, xCoord - 1, new int[]{yCoord, xCoord});
            }
            if (isRealBox(yCoord, Math.min(16, xCoord + 1)) && (xCoord+1 != previous[1]) && (xCoord != 16) & !(isBarricadeValidMoves(yCoord, xCoord))& !(isRedValidMoves(yCoord, xCoord)) & !(isGreenValidMoves(yCoord, xCoord)) & !(isBlueValidMoves(yCoord, xCoord)) & !(isYellowValidMoves(yCoord, xCoord)) ) {
                findvalid(diceCount - 1, yCoord, xCoord + 1, new int[]{yCoord, xCoord});
            }
        }
    }

    // Method to find if the next move for a piece is valid
    public void findValidCoordinates(int diceCount, int[]previous, int turn) {
        validmoves.removeAll(validmoves);
        if (yCoord > 13) {
            if (turn == 1) {
                findvalid(diceCount-1, 13, 2, previous);
            }else if (turn == 2){
                findvalid(diceCount-1, 13, 6, previous);
            }else if (turn == 3){
                findvalid(diceCount-1, 13, 10, previous);
            }else if (turn == 4){
                findvalid(diceCount-1, 13, 14, previous);
            }
        } else {
            findvalid(diceCount, yCoord, xCoord, previous);
        }
        if (validmoves.isEmpty()){
            JOptionPane.showMessageDialog(this, "No Valid Moves For You! " +
                    "Pass over to the next player");
        }
    }
    // Methods to change the coordinates of a position of a coloured piece

    public void setnewRedCoordinate(int oldy, int oldx){
        redSquares.removeIf(x -> Arrays.equals(x, new int[]{oldy, oldx}));
        redSquares.add(new int[]{yCoord, xCoord});
        System.out.println("Coordinates Changed");
    }

    public void setnewGreenCoordinate(int oldy, int oldx){
        greenSquares.removeIf(i -> Arrays.equals(i, new int[]{oldy, oldx}));
        greenSquares.add(new int[]{yCoord, xCoord});
        System.out.println("Coordinates Changed");
    }

    public void setnewBlueCoordinate(int oldy, int oldx){
        blueSquares.removeIf(i -> Arrays.equals(i, new int[]{oldy, oldx}));
        blueSquares.add(new int[]{yCoord, xCoord});
        System.out.println("Coordinates Changed");
    }

    public void setnewYellowCoordinate(int oldy, int oldx){
        yellowSquares.removeIf(i -> Arrays.equals(i, new int[]{oldy, oldx}));
        yellowSquares.add(new int[]{yCoord, xCoord});
        System.out.println("Coordinates Changed");
    }

    // Method to set the new coordinates of a barricade that has been moved in the internal arraylist
    public void setnewBarricadeCoordinate(int oldy, int oldx){
        barricadeCoordinates.removeIf(i -> Arrays.equals(i, new int[]{oldy, oldx}));
        barricadeCoordinates.add(new int[]{yCoord, xCoord});
        System.out.println("Coordinates Changed");
    }

    // Getters for the current coordinates of a piece
    public int getxCoord(){
        return xCoord;
    }

    public int getyCoord(){
        return yCoord;
    }

    // Method to check if a square is a valid square on the board or not
    public boolean isValidNextMove(){
        int counter = 0;
        while (counter < validmoves.size()){
            if (validmoves.get(counter)[1] == this.getxCoord() && validmoves.get(counter)[0] == this.getyCoord()){
                return true;
            }
            counter++;
        }
        return false;
    }

    // Returns the amount of valid moves for a piece
    public int getvalidmovesSize(){
        return validmoves.size();
    }

    public void resetRedPiece(int currentY, int currentX) {
        redSquares.removeIf(x -> Arrays.equals(x, new int[]{currentY, currentX}));
        redSquares.add(new int[]{inity, initx});
        this.xCoord = initx;
        this.yCoord = inity;
    }

    // Getters for the initial coordinates of a piece
    public int getInitx(){
        return initx;
    }

    public int getInity(){
        return inity;
    }

}
