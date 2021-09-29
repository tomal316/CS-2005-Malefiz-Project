import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameSquare extends JButton {
    private int xCoord, yCoord, lineCounter;
    //Replace with piece and barricade classes when they are created
    private String piece, barricade;

    public ArrayList<int[]> barricadeCoordinates, validmoves, redSquares, blueSquares, greenSquares, yellowSquares;

    public GameSquare(int yCoord, int xCoord) {
        super();
        this.xCoord = xCoord;
        this.yCoord = yCoord;

        validmoves = new ArrayList<int[]>();
        barricadeCoordinates = new ArrayList<int[]>();
        redSquares = new ArrayList<int[]>();
        blueSquares = new ArrayList<int[]>();
        greenSquares = new ArrayList<int[]>();
        yellowSquares = new ArrayList<int[]>();

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

        findValidCoordinates(3, 3, 3, new int[]{-1, -1});
        for (int[] i : validmoves){
            System.out.println(String.valueOf(i[0]) + " " + String.valueOf(i[1]));
        }

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
        if (isBarricade(yCoord, xCoord)){
            this.setIcon(new ImageIcon("barricadepiece.jpg"));
        }
    }

    public void squareSelected(String piece) {
        this.setForeground(Color.BLACK);
        this.setEnabled(false);
        if (piece.equals("X1")) {
            System.out.println("X1 has clicked");
        } else {
            System.out.println("X2 has clicked");
        }
    }

    private boolean isRealBox(int yCoord, int xCoord) {
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

    public boolean isBarricade(int yCoord, int xCoord) {
        boolean squareBarriacade = false;
        for (int[]x : barricadeCoordinates){
            if (x[0] == yCoord && x[1] == xCoord){
                return true;
            }
        }
        return squareBarriacade;
    }

    public boolean isLandingSquare(int yCoord, int xCoord) {
        boolean landingSquare = false;
        if (yCoord == 13) {
            if ((xCoord == 2) || (xCoord == 6) || (xCoord == 10) || (xCoord == 14)) {
                landingSquare = true;
            }
        }
        return landingSquare;
    }

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

    public void findvalid(int diceCount, int yCoord, int xCoord, int[] previous) {
        if (diceCount == 0) {
            validmoves.add(new int[]{yCoord, xCoord});
        } else {
            if (isRealBox(Math.max(0, yCoord - 1), xCoord) && (yCoord-1 != previous[0]) && (yCoord != 0) & !(isBarricade(yCoord, xCoord))) {
                findvalid(diceCount - 1, yCoord - 1, xCoord, new int[]{yCoord, xCoord});
            }
            if (isRealBox(Math.min(13, yCoord + 1), xCoord) && (yCoord+1 != previous[0]) && (yCoord != 13) & !(isBarricade(yCoord, xCoord))) {
                findvalid(diceCount - 1, yCoord + 1, xCoord, new int[]{yCoord, xCoord});
            }

            if (isRealBox(yCoord, Math.max(0, xCoord-1)) && (xCoord-1 != previous[1]) && (xCoord != 0) & !(isBarricade(yCoord, xCoord))) {
                findvalid(diceCount - 1, yCoord, xCoord - 1, new int[]{yCoord, xCoord});
            }

            if (isRealBox(yCoord, Math.min(16, xCoord + 1)) && (xCoord+1 != previous[1]) && (xCoord != 16) & !(isBarricade(yCoord, xCoord))) {
                findvalid(diceCount - 1, yCoord, xCoord + 1, new int[]{yCoord, xCoord});
            }
        }
    }

    public void findValidCoordinates(int diceCount, int yCoord, int xCoord, int[]previous){
        validmoves.removeAll(validmoves);
        findvalid(diceCount, yCoord, xCoord, previous);
    }

    public void setnewRedCoordinate(int oldy, int oldx, int newy, int newx){
        redSquares.remove(new int[]{oldx, oldy});
        redSquares.add(new int[]{newy, newx});
        System.out.println("Coordinates Changed");
    }
}
