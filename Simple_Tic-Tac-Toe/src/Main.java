import java.util.Scanner;

public class Main {
    private static Scanner m_Scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //Prepare for new game
        TicTacToe game = new TicTacToe();
        game.reset();
        game.printField();

        //Ask user for input
        while(!game.gameEnded()) {
            boolean invalidInput = true;
            while (invalidInput) {//handle user input (2 int must be entered, representing the coordinates for the move
                System.out.print(game.getCurrentPlayerTurn() + " Enter the coordinates: ");
                if (!m_Scanner.hasNextInt()) System.out.println("You should enter numbers!");
                else {
                    int x = m_Scanner.nextInt();
                    if (!m_Scanner.hasNextInt()) {
                        System.out.println("You should enter numbers!");
                    } else { //2 numbers as input
                        int y = m_Scanner.nextInt();
                        if ((x < 1 || x > 3) || (y < 1 || y > 3)) {
                            System.out.println("Coordinates should be from 1 to 3!");
                        } else { //input withing bounds. Check for empty cell.
                            if (game.validMove(x, y)) {
                                game.printField();
                                game.analyzeGameState();
                                invalidInput = false;
                            } else {
                                System.out.println("This cell is occupied! Choose another one!");
                            }
                        }
                    }
                }
            }
        }
        game.printState();
    }
}
