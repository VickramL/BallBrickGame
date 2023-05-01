import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static int ballCount = 3;
    static int n;
    public static void main(String[] args) {
        System.out.print("Enter the dimension : ");
        n = input.nextInt();
        char[][] board = new char[n][n];
        for(int i = 0;i<n;i++){
            board[0][i] = 'W';
            board[i][0] = 'W';
            board[i][n-1] = 'W';
            if(i!=0 && i != n-1)
                board[n-1][i] = 'G';
        }
        board[n-1][n/2] = 'o';

//        level1(board,n);
        level2(board,n);
    }
    public static void printBoard(char[][] board,int n){
        for(int i = 0;i<n;i++){
            for(int j = 0;j<n;j++){
                System.out.print((Character.isLetterOrDigit(board[i][j]))?
                        board[i][j]+" ":"  ");
            }
            System.out.println();
        }
        System.out.println("Ball Count "+ballCount);
    }
    public static void level1(char[][] board, int n){
        while (true) {
            System.out.print("Enter the brick position and bricks type : ");
            int row = input.nextInt();
            int col = input.nextInt();
            char brick = input.next().charAt(0);
            if(row < 0 || col < 0 || row>=n || col >= n || board[row][col] != 0){
                System.out.println("Invalid position !!!");
                continue;
            }
            board[row][col] = brick;
            System.out.print("Do you want to continue (y/n): ");
            if(input.next().charAt(0) == 'n') break;
        }
        printBoard(board,n);
    }

    public static void level2(char[][] board, int n){
        level1(board,n);
        play(board,n);
        printBoard(board,n);
    }

    public static void play(char[][] board, int n){
        int[] ballPosition = {n-1,n/2};
        ballCount = 3;
        while(true){
            if(ballCount == 0){
                System.out.println("Game Over !");
                return;
            }
            int ballRow = ballPosition[0];
            int ballCol = ballPosition[1];
            System.out.print("Enter the direction in which the ball need to traverse : ");
            String choice = input.next();

            move(board,ballRow,ballCol,ballPosition,choice);
            printBoard(board,n);
        }
    }

    public static boolean checkBoard(char[][] board, int n){
        for(int i = 1;i<n-1;i++){
            for(int j = 1;j<n-1;j++){
                if(Character.isLetterOrDigit(board[i][j]))
                    return false;
            }
        }
        return true;
    }

    public static void move(char[][] board, int ballRow, int ballCol,int[] ballPosition,String choice){
        while (true){
            if(board[ballRow][ballCol] == 'W'){
                board[ballPosition[0]][ballPosition[1]] = 'G';
                ballPosition[0] = n-1;
                ballPosition[1] = n/2;
                board[ballPosition[0]][ballPosition[1]] = 'o';
                ballCount--;
                return;
            }
            else if(Character.isDigit(board[ballRow][ballCol])){
                if(board[ballRow][ballCol] == '1'){
                    board[ballRow][ballCol] = ' ';
                }
                else board[ballRow][ballCol] = (char) (board[ballRow][ballCol] -1);

                break;
            }
            else if(ballRow!= n-1 && Character.isAlphabetic(board[ballRow][ballCol])){
                isCharacter(board,ballRow,ballCol);
                break;
            }
            ballRow--;
            if(choice.equals("ld")) ballCol--;
            else if(choice.equals("rd")) ballCol++;

        }
        board[ballPosition[0]][ballPosition[1]] = 'G';
        ballPosition[0] = n-1;
        ballPosition[1] = ballCol;
        board[ballPosition[0]][ballPosition[1]] = 'o';
        if(checkBoard(board,n)){
            System.out.println("you win HURRAY");
            printBoard(board,n);
            System.exit(1);
        }
    }

    public static void isCharacter(char[][] board, int ballRow,int ballCol){
        if(board[ballRow][ballCol] == 'e'){
            for(int i = 1;i<n-1;i++){
                board[ballRow][i] = ' ';
            }
        }
        else if(board[ballRow][ballCol] == 's'){
            board[ballRow][ballCol] = ' ';
            if(ballRow-1 != 0) board[ballRow-1][ballCol] = ' ';
            if(ballCol-1 != 0) board[ballRow][ballCol-1] = ' ';
            if(ballRow+1 != n-1) board[ballRow+1][ballCol] = ' ';
            if(ballCol+1 != n-1) board[ballRow][ballCol+1] = ' ';
        }
        else if(board[ballRow][ballCol] == 'b'){
            board[ballRow][ballCol] = ' ';
            ballCount += 1;
        }
    }
}
