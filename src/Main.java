import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.print("Enter the dimension : ");
        int n = input.nextInt();
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
                System.out.print((Character.isAlphabetic(board[i][j])||Character.isDigit(board[i][j]))?
                        board[i][j]+" ":"  ");
            }
            System.out.println();
        }
    }
    public static void level1(char[][] board, int n){
        while (true) {
            System.out.print("Enter the brick position and bricks type : ");
            int row = input.nextInt();
            int col = input.nextInt();
            char brick = input.next().charAt(0);
            board[row][col] = brick;
            System.out.print("Do you want to continue (y/n): ");
            if(input.next().charAt(0) == 'n') break;
        }
        printBoard(board,n);
    }

    public static void level2(char[][] board, int n){
        level1(board,n);
        int ballCount = 3;
        System.out.println("Ball Count "+ballCount);
        play(board,n);
        printBoard(board,n);
    }

    public static void play(char[][] board, int n){
        int[] ballPosition = {n-1,n/2};
        int ballCount = 3;
        while(true){
            System.out.print("Enter the direction in which the ball need to traverse : ");
            String choice = input.next();
            if(choice.equals("st")){
                int ballRow = ballPosition[0];
                int ballCol = ballPosition[1];
                while (true){
                    if(ballCount == 0){
                        System.out.println("GAME OVER !");
                        return;
                    }
                    if(board[ballRow][ballCol] == 'W'){
                        ballCount--;
                        break;
                    }
                    else if(Character.isDigit(board[ballRow][ballCol])){
                        if(board[ballRow][ballCol] == '1'){
                            board[ballRow][ballCol] = ' ';
                        }
                        else board[ballRow][ballCol] = (char) (board[ballRow][ballCol] -1);

                        if(checkBoard(board,n)){
                            System.out.println("you win HURRAY");
                            return;
                        }
                        break;
                    }
                    ballRow--;
                }
            }
            else if(choice.equals("ld")){
                int ballRow = ballPosition[0];
                int ballCol = ballPosition[1];
                while (true){
                    if(ballCount == 0){
                        System.out.println("GAME OVER !");
                        return;
                    }
                    if(board[ballRow][ballCol] == 'W'){
                        ballPosition[0] = n-1;
                        ballPosition[1] = n/2;
                        ballCount--;
                        break;
                    }
                    else if(Character.isDigit(board[ballRow][ballCol])){
                        if(board[ballRow][ballCol] == '1'){
                            board[ballRow][ballCol] = ' ';
                            board[ballPosition[0]][ballPosition[1]] = 'G';
                            ballPosition[0] = n-1;
                            ballPosition[1] = ballCol;
                            board[n-1][ballCol] = 'o';
                            break;
                        }
                        else board[ballRow][ballCol] = (char) (board[ballRow][ballCol] -1);

                        if(checkBoard(board,n)){
                            System.out.println("you win HURRAY");
                            return;
                        }

                    }
                    ballRow--;
                    ballCol--;
                }
            }

            else{
                int ballRow = ballPosition[0];
                int ballCol = ballPosition[1];
                while (true){
                    if(ballCount == 0){
                        System.out.println("GAME OVER !");
                        return;
                    }
                    if(board[ballRow][ballCol] == 'W'){
                        ballPosition[0] = n-1;
                        ballPosition[1] = n/2;
                        ballCount--;
                        break;
                    }
                    else if(Character.isDigit(board[ballRow][ballCol])){
                        if(board[ballRow][ballCol] == '1'){
                            board[ballRow][ballCol] = ' ';
                            board[ballPosition[0]][ballPosition[1]] = 'G';
                            ballPosition[0] = n-1;
                            ballPosition[1] = ballCol;
                            board[n-1][ballCol] = 'o';
                            break;
                        }
                        else board[ballRow][ballCol] = (char) (board[ballRow][ballCol] -1);

                        if(checkBoard(board,n)){
                            System.out.println("you win HURRAY");
                            return;
                        }

                    }
                    ballRow--;
                    ballCol++;
                }
            }
        }
    }

    public static boolean checkBoard(char[][] board, int n){
        for(int i = 1;i<n-1;i++){
            for(int j = 1;j<n-1;j++){
                if(Character.isDigit(board[i][j]))
                    return false;
            }
        }
        return true;
    }
}
