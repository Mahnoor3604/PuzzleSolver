import java.util.Random;

public class Board implements Comparable<Board> {
    // You don't have to use these constants, but they do make your code easier to read
    public static final byte UR = 0;
    public static final byte R = 1;
    public static final byte DR = 2;
    public static final byte DL = 3;
    public static final byte L = 4;
    public static final byte UL = 5;

    // You need a random number generator in order to make random moves.  Use rand below
    private static final Random rand = new Random();

    public byte[][] original;
    public byte[][] board;



    public int compareTo(Board other) {
        for (int r = 0; r < this.board.length; r++) {
            for (int c = 0; c < this.board[r].length; c++) {
                if (this.board[r][c] < other.board[r][c]) {
                    return -1;
                } else if (this.board[r][c] > other.board[r][c]) {
                    return 1;
                }
            }
        }
        return 0; // The boards are equal
    }



    public boolean equals (Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        Board otherBoard = (Board) other;
        for (int r = 0; r < this.board.length; r++) {
            for (int c = 0; c < this.board[r].length; c++) {
                if (this.board[r][c] != otherBoard.board[r][c]) {
                    return false;
                }
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (c == 0) {
                    for (int i = r; i < 5; i += 1) {
                        stringBuilder.append(" ");
                    }
                }
                stringBuilder.append(board[r][c]).append(" "); // Append the cell value and a space
            }
            stringBuilder.append("\n"); // Append a new line for the next row
        }

        return stringBuilder.toString();
    }

    /**
     * Construct a puzzle board by beginning with a solved board and then
     * making a number of random moves.  Note that making random moves
     * could result in the board being solved.
     *
     * @param moves the number of moves to make when generating the board.
     */
    public Board(int moves) {
        byte[][] ogBoard = {
                {0},
                {1, 1},
                {1, 1, 1},
                {2, 2, 2, 2},
                {3, 3, 3, 3, 3}
        };
        original = ogBoard;
        this.board = new byte[5][];
        for (int r = 0; r < ogBoard.length; r++) {
            board[r] = ogBoard[r].clone(); //copies whole row instead of doing a nested for loop for both the row and column
        }
        while (moves != 0) {
            byte m = (byte) rand.nextInt(6);
            if (move(m)) {
                moves-=1;
            }
        }
    }

    /**
     * Construct a puzzle board using a 2D array of bytes to indicate the contents
     * of the cells in the triangle.
     *
     * @param b a "triangular array" with 5 rows where row 0 has 1
     * cell, row 1 has 2 cells, etc.
     */
    public Board(byte[][] b) {
        byte[][] ogBoard = {
                {0},
                {1, 1},
                {1, 1, 1},
                {2, 2, 2, 2},
                {3, 3, 3, 3, 3}
        };
        original = ogBoard;
        board = new byte[5][];
        for (int r = 0; r < b.length; r++) {
            board[r] = b[r].clone(); //copies whole row instead of doing a nested for loop for both the row and column
        }
    }


    /**
     * Returns the byte at a particular grid position.
     * @param row the row to look at
     * @param col the col to look at
     * @return the byte at a particular grid position
     */
    public byte cellContents(int row, int col) {
        return board[row][col];
    }

    /**
     * Makes a move on the board by swapping the 0 with one
     * of the items in a neighboring cell.  The board is
     * left unchanged if the move is not valid.
     *
     * @param dir the direction in which the 0 should move
     * @return {@code true} if the move was valid and {@code false}
     * if it wasn't
     */
    public boolean move(byte dir) {
        int r = 0;
        int c = 0;
        while (board[r][c] != 0) {
            if (c == board[r].length-1) {
                r += 1;
                c = 0;
            } else {
                c += 1;
            }
        }

        if (dir == UR) {
            if (c == board[r].length-1) return false;

            board[r][c] = board[r-1][c];
            board[r-1][c] = (byte) 0;
            return true;
        } else if (dir == R) {
            if (c == board[r].length-1) return false;

            board[r][c] = board[r][c+1];
            board[r][c+1] = (byte) 0;
            return true;
        } else if (dir == DR) {
            if (r == board.length-1) return false;

            board[r][c] = board[r+1][c+1];
            board[r+1][c+1] = (byte) 0;
            return true;
        } else if (dir == DL) {
            if (r == board.length-1) return false;

            board[r][c] = board[r+1][c];
            board[r+1][c] = (byte) 0;
            return true;
        } else if (dir == L) {
            if (c == 0) return false;

            board[r][c] = board[r][c-1];
            board[r][c-1] = (byte) 0;
            return true;
        } else if (dir == UL) {
            if (c == 0) return false;

            board[r][c] = board[r-1][c-1];
            board[r-1][c-1] = (byte) 0;
            return true;
        }
        return false;
    }


    public Byte findMove(Board b) {
        Board temp = new Board(this.board);

        if (temp.move(UR)) {
            if (temp.equals(b)) return UR;
            else temp = new Board(this.board);
        }

        if (temp.move(R)) {
            if (temp.equals(b)) return R;
            else temp = new Board(this.board);
        }

        if (temp.move(DR)) {
            if (temp.equals(b)) return DR;
            else temp = new Board(this.board);
        }

        if (temp.move(DL)) {
            if (temp.equals(b)) return DL;
            else temp = new Board(this.board);
        }

        if (temp.move(L)) {
            if (temp.equals(b)) return L;
            else temp = new Board(this.board);
        }

        if (temp.move(UL)) {
            if (temp.equals(b)) return UL;
            else temp = new Board(this.board);
        }

        return null;
    }

    /**
     * Determines if the board is solved
     *
     * @return {@true} if the board is solved and {@code false} otherwise.
     */
    public boolean isSolved() {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (original[r][c] != board[r][c]) {
                    return false;
                }
            }
        }
        return true;
    }
}


