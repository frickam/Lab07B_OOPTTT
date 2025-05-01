import javax.swing.*;

public class Game {
    private JFrame frame;
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    public Game() {
        // Initialize Players
        player1 = new Player("Player 1", "X");
        player2 = new Player("Player 2", "O");
        currentPlayer = player1;

        // Initialize JFrame
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // Initialize Board
        board = new Board(this);
        frame.add(board);

        // Display the JFrame
        frame.setVisible(true);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchTurn() {
        // Switch to the other player
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public void announceWinner(String winner) {
        JOptionPane.showMessageDialog(frame, winner + " wins!");
        resetGame();
    }

    public void announceDraw() {
        JOptionPane.showMessageDialog(frame, "It's a draw!");
        resetGame();
    }

    private void resetGame() {
        board.resetBoard();
        currentPlayer = player1;
    }

    public static void main(String[] args) {
        new Game(); // Start the game
    }
}