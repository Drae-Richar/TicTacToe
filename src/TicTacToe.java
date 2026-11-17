import java.util.Scanner;

public class TicTacToe {
    private static final int ROWS=3;
    private static final int COLS=3;
    private static String board[][] = new String [ROWS][COLS];
    private static String currentPlayer = "X";

    private static void clearBoard(){
        for (int row=0;row < ROWS;row++){
            for (int col=0; col< COLS; col++){
                board[row][col]=" ";
            }
        }
    }
    private static void display(){
        for (int row=0;row < ROWS;row++){
            for (int col=0; col< COLS; col++){
                System.out.print(board[row][col]);
                if(col<2){
                    System.out.print("|");
                }
            }
            // move to a new line after each row
            System.out.println();
            if (row<2){
                System.out.print("-----");
            }
            System.out.println();
        }
    }
    private static boolean isValidMove(int row, int col){
        if(board[row][col].equals(" ")){
            return true;
        }
        return false;
    }
    private static boolean isWin(String player){
        return isColWin(player) || isRowWin(player) || isDiagnalWin(player);
    }
    private static boolean isColWin(String player){
        for(int col=0; col<COLS; col++){
            if(board[0][col].equals(player) &&
                    board[1][col].equals(player) &&
                    board[2][col].equals(player)){
                return true;
            }
        }
        return false;
    }
    private static boolean isRowWin(String player){
        for(int row=0; row<ROWS; row++){
            if(board[row][0].equals(player) &&
                    board[row][1].equals(player) &&
                    board[row][2].equals(player)){
                return true;
            }
        }
        return false;
    }
    private static boolean isDiagnalWin(String player){
        return (board[0][0].equals(player) &&
                board[1][1].equals(player) &&
                board[2][2].equals(player)) ||
                (board[0][2].equals(player) &&
                board[1][1].equals(player) &&
                board[2][0].equals(player));
    }

    private static boolean isTie(){
        boolean allSpacesFilled = true;
        for(int row=0; row<ROWS; row++){
            for(int col=0; col<COLS; col++){
                if(board[row][col].equals(" ")){
                    allSpacesFilled = false;
                }
            }
        }

        boolean allBlocked = true;
        for(int row=0; row<ROWS; row++){
            if(!hasBothPlayers(board[row][0], board[row][1], board[row][2])){
                allBlocked = false;
            }
        }
        for(int col=0; col<COLS; col++){
            if(!hasBothPlayers(board[0][col], board[1][col], board[2][col])){
                allBlocked = false;
            }
        }
        if(!hasBothPlayers(board[0][0], board[1][1], board[2][2])){
            allBlocked = false;
        }
        if(!hasBothPlayers(board[0][2], board[1][1], board[2][0])){
            allBlocked = false;
        }

        return allSpacesFilled || allBlocked;
    }

    private static boolean hasBothPlayers(String first, String second, String third) {
        boolean hasX = first.equals("X") || second.equals("X") || third.equals("X");
        boolean hasO = first.equals("O") || second.equals("O") || third.equals("O");
        return hasX && hasO;
    }

    public static void main(String[] args) {
        clearBoard();
        display();
        Scanner in = new Scanner(System.in);
        int row, col;
        String rowPrompt,colPrompt;
        boolean done=false;
        do{
            while(true){
                rowPrompt= "Player "+currentPlayer+" enter row move";
                colPrompt= "Player "+currentPlayer+" enter col move";
                row= SafeInput.getRangedInt(in, rowPrompt, 1,3) -1;
                col= SafeInput.getRangedInt(in, colPrompt, 1,3) -1;
                if (isValidMove(row, col)) {
                    board[row][col]=currentPlayer;
                    break;
                }
                else{
                    System.out.println("Enter a valid move");
                }
            }
            display();
            if(isWin(currentPlayer)){
                System.out.println("Player "+currentPlayer+" wins!");
                done=true;
            }
            else if(isTie()){
                System.out.println("It's a tie!");
                done=true;
            }
            if(done){
                continue;
            }
            // toggle current player
            if(currentPlayer.equals("X")){
                currentPlayer="O";
            }
            else{
                currentPlayer="X";
            }
        }while(!done);
    }
}