import java.util.Scanner;

    public class Exercise11 {
        public static final int NUM_ROWS = 8;
        public static final int NUM_COLS = 8;
        static char[][] board = new char[NUM_ROWS][NUM_COLS];
        public static final int NUM_SHIPS = 1;
        public static final char SHIP_SYMBOL = 'S';
        public static final char EMPTY_SYMBOL = '.';
        public static final char WATER_SYMBOL = '0';
        public static final char SUNK_SHIP_SYMBOL = 'X';
        private static boolean gameOver = false;
        private static int numShots = 0;
        private static int sunkShips = 0;
        static Scanner input = new Scanner(System.in);

        public static void main(String[] args) {
            int row, col;
            initBoard();
            addShips();
            while (!gameOver) {
                printStatistics();
                printBoard(false);
                row = enterRow();
                col = enterCol();
                shoot(row, col);
                checkGameOver();
            }
            System.out.println("GAME OVER");
            printStatistics();
            printBoard(true);

        }

        private static void checkGameOver() {
            if (sunkShips == NUM_SHIPS) {
                gameOver = true;
            }
        }

        private static void shoot(int row, int col) {
            numShots++;
            if (board[row][col] == SHIP_SYMBOL) {
                board[row][col] = SUNK_SHIP_SYMBOL;
                sunkShips++;
            } else {
                if (board[row][col] != SUNK_SHIP_SYMBOL) {
                    board[row][col] = WATER_SYMBOL;
                }
            }
        }

        private static int enterCol() {
            System.out.print("Enter col: ");
            int col = input.nextInt();
            return col - 1;
        }

        private static int enterRow() {
            System.out.print("Enter row: ");
            String text = input.next().toUpperCase();
            char letter = text.charAt(0);
            int row = letter - 'A';
            return row;
        }

        private static void printStatistics() {
            System.out.println("Shots: " + numShots);
            System.out.println("Sunk ships: " + sunkShips);
        }

        private static void initBoard() {
            for (int row = 0; row < NUM_ROWS; row++) {
                for (int col = 0; col < NUM_COLS; col++) {
                    board[row][col] = EMPTY_SYMBOL;
                }
            }
        }

        private static void printBoard(boolean showShips) {
            printFirstLine();
            char letter = 'A';
            for (int row = 0; row < NUM_ROWS; row++) {
                System.out.print(letter + " ");
                letter++;
                for (int col = 0; col < NUM_COLS; col++) {
                    if (!showShips && board[row][col] == SHIP_SYMBOL) {
                        System.out.print(EMPTY_SYMBOL);
                    } else {
                        System.out.print(board[row][col] + " ");
                    }
                }
                System.out.println();
            }
        }

        private static void printFirstLine() {
            System.out.print("  ");
            for (int row = 1; row <= NUM_ROWS; row++) {
                System.out.print(row + " ");
            }
            System.out.println();
        }

        private static void addShips() {
            int placedShips = 0, randRow, randCol;
            while (placedShips < NUM_SHIPS) {
                randRow = (int) (Math.random() * NUM_ROWS);
                randCol = (int) (Math.random() * NUM_COLS);
                if (board[randRow][randCol] != SHIP_SYMBOL) {
                    board[randRow][randCol] = SHIP_SYMBOL;
                    placedShips++;
                }
            }
        }
    }

