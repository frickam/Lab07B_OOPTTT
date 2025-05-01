import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JPanel {
    private static final int SIZE = 3;
    private JButton[][] cells;
    private Game game;

    public Board(Game game) {
        this.game = game;
        setLayout(new GridLayout(SIZE, SIZE));
        cells = new JButton[SIZE][SIZE];

        // Initialize the cells
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cells[i][j] = new JButton();
                cells[i][j].setFont(new Font("Arial", Font.BOLD, 60));
                cells[i][j].setFocusPainted(false);
                cells[i][j].addActionListener(new CellClickListener(i, j));
                add(cells[i][j]);
            }
        }
    }

    private class CellClickListener implements ActionListener {
        private int row, col;

        public CellClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = cells[row][col];

            // Check if the cell is already occupied
            if (!clickedButton.getText().isEmpty()) {
                JOptionPane.showMessageDialog(Board.this, "Cell already occupied!");
                return;
            }

            // Mark the cell with the current player's symbol
            Player currentPlayer = game.getCurrentPlayer();
            clickedButton.setText(currentPlayer.getSymbol());

            // Check for a winner or draw
            if (checkWinner(currentPlayer.getSymbol())) {
                game.announceWinner(currentPlayer.getName());
            } else if (isBoardFull()) {
                game.announceDraw();
            } else {
                // Switch turn
                game.switchTurn();
            }
        }
    }

    private boolean checkWinner(String symbol) {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < SIZE; i++) {
            // Check row
            if (cells[i][0].getText().equals(symbol) &&
                    cells[i][1].getText().equals(symbol) &&
                    cells[i][2].getText().equals(symbol)) {
                return true;
            }
            // Check column
            if (cells[0][i].getText().equals(symbol) &&
                    cells[1][i].getText().equals(symbol) &&
                    cells[2][i].getText().equals(symbol)) {
                return true;
            }
        }

        // Check diagonals
        if (cells[0][0].getText().equals(symbol) &&
                cells[1][1].getText().equals(symbol) &&
                cells[2][2].getText().equals(symbol)) {
            return true;
        }
        if (cells[0][2].getText().equals(symbol) &&
                cells[1][1].getText().equals(symbol) &&
                cells[2][0].getText().equals(symbol)) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        // Check if all cells are filled
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (cells[i][j].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void resetBoard() {
        // Clear all cells
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cells[i][j].setText("");
            }
        }
    }
}