package com.tecZarine.ticTacGame;

import java.util.*;

public class TicTacToe {

    static List<Integer> playerPositions = new ArrayList<>();

    static List<Integer> cpuPositions = new ArrayList<>();

    public static void main(String[] args) {

        char[][] gameBoard = {
                { ' ', '|', ' ', '|', ' ' },
                { '-', '+', '-', '+', '-' },
                { ' ', '|', ' ', '|', ' ' },
                { '-', '+', '-', '+', '-' },
                { ' ', '|', ' ', '|', ' ' }
        };

        printGameBoard(gameBoard);

        boolean placedSuccessFully = true;
        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your placement 1 to 9");
            int placement = scanner.nextInt();
            System.out.println("position : " + placement);
            placedSuccessFully = placePiece(placement, gameBoard, "Player");
            while(true) {
                if (!placedSuccessFully) {
                    System.out.println("Sorry, wrong placement, choose again");
                    Scanner scanner2 = new Scanner(System.in);
                    placement = scanner2.nextInt();
                    placedSuccessFully = placePiece(placement, gameBoard, "Player");
                }
                else {
                    playerPositions.add(placement);
                    break;
                }
            }
            String result = checkWinner(gameBoard);
            if(!result.equals("Continue..")){
                System.out.println(result);
                System.out.println();
                System.out.println();
                System.out.println();
                break;
            }
            Random rand = new Random();
            int cpuPlacement = rand.nextInt(9) + 1;
            placedSuccessFully = placePiece(cpuPlacement, gameBoard, "CPU");
            while(true) {
                if(!placedSuccessFully){
                   cpuPlacement = rand.nextInt(9) + 1;
                   placedSuccessFully = placePiece(cpuPlacement, gameBoard, "CPU");
                }
                else {
                    cpuPositions.add(cpuPlacement);
                    break;
                }
            }
            result = checkWinner(gameBoard);
            if(!result.equals("Continue..")){
                System.out.println(result);
                System.out.println();
                System.out.println();
                System.out.println();
                break;
            }
            printGameBoard(gameBoard);
        }

    }

    public static void printGameBoard(char[][] gameBoard){
        for(char[] row: gameBoard){
            for(char column: row){
                System.out.print(column);
            }
            System.out.println();
        }
    }

    private static boolean placePiece(int placement, char[][] gameBoard, String user){
        boolean placesSuccessfully = false;
        char symbol = 'X';
        if(user.equals("Player")){
            symbol = 'X';
        }
        else if(user.equals("CPU")){
            symbol = 'O';
        }
        switch (placement) {
            case 1:
                if(ifBoxEmpty(gameBoard, 0,0)){
                    gameBoard[0][0] = symbol;
                    placesSuccessfully = true;
                }
                break;
            case 2:
                if(ifBoxEmpty(gameBoard, 0,2)){
                    gameBoard[0][2] = symbol;
                    placesSuccessfully = true;
                }
                break;
            case 3: if(ifBoxEmpty(gameBoard, 0,4)){
                        gameBoard[0][4] = symbol;
                        placesSuccessfully = true;
                    }
                break;
            case 4:
                if(ifBoxEmpty(gameBoard, 2,0)){
                    gameBoard[2][0] = symbol;
                    placesSuccessfully = true;
                }
                break;
            case 5: if(ifBoxEmpty(gameBoard, 2,2)){
                        gameBoard[2][2] = symbol;
                        placesSuccessfully = true;
                    }
                break;
            case 6: if(ifBoxEmpty(gameBoard, 2,4)){
                        gameBoard[2][4] = symbol;
                        placesSuccessfully = true;
                    }
                break;
            case 7:  if(ifBoxEmpty(gameBoard, 4,0)){
                        gameBoard[4][0] = symbol;
                        placesSuccessfully = true;
                    }
                break;
            case 8: if(ifBoxEmpty(gameBoard, 4,2)){
                        gameBoard[4][2] = symbol;
                        placesSuccessfully = true;
                        break;
                 }
                break;
            case 9: if(ifBoxEmpty(gameBoard, 4,4)){
                        gameBoard[4][4] = symbol;
                        placesSuccessfully = true;
                        break;
                    }
                break;
        }
        return placesSuccessfully;
    }

    private static boolean ifBoxEmpty(char[][] gameBoard, int row, int column){
        if(gameBoard[row][column] == ' '){
            return true;
        }
        return false;
    }

    private static String checkWinner(char[][] gameBoard){

        List<Integer> topRow = Arrays.asList(1,2,3);
        List<Integer> middleRow = Arrays.asList(4,5,6);
        List<Integer> bottomRow = Arrays.asList(7,8,9);

        List<Integer> leftCol = Arrays.asList(1,4,7);
        List<Integer> midCol = Arrays.asList(2,5,8);
        List<Integer> rightCol = Arrays.asList(3,6,9);

        List<Integer> diag1 = Arrays.asList(1,5,9);
        List<Integer> dia2 =  Arrays.asList(3,5,7);


        List<List<Integer>> winningLst = new ArrayList<>();

        winningLst.add(topRow);
        winningLst.add(middleRow);
        winningLst.add(bottomRow);
        winningLst.add(leftCol);
        winningLst.add(midCol);
        winningLst.add(rightCol);
        winningLst.add(dia2);
        winningLst.add(diag1);

        for(List<Integer> winningBoxes: winningLst){
            if(playerPositions.containsAll(winningBoxes)){
                return "Congratulation, you won!";
            }

            else if(cpuPositions.containsAll(winningBoxes)){
                return "CPU winds, Sorry!";
            }
            else if((playerPositions.size() + cpuPositions.size()) >= 9){
                return "Match draw";
            }
        }
        return "Continue..";

    }

}
