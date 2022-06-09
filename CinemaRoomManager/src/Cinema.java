package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {
    private static Scanner m_scanner=new Scanner(System.in);
    public static void main(String[] args) {
        // Write your code here
        int rows;
        int seatsPerRow;
        //Define Room params
        System.out.println("Enter the number of rows:");
        rows = m_scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seatsPerRow = m_scanner.nextInt();
        CinemaRoom cinRoom = new CinemaRoom(rows, seatsPerRow);

        //User Menu
        boolean repeat = true;
        while(repeat) {
            printMenu();
            int usrInput = m_scanner.nextInt();
            repeat = handleInput(usrInput, cinRoom);
        }
    }

    private static boolean handleInput(int usrInput, CinemaRoom cinRoom) {
        switch (usrInput) {
            case 1: //Show seats
                cinRoom.printRoomLayout();
                break;
            case 2: //Buy a ticket
                System.out.println("Enter a row number:");
                int rowIndex = m_scanner.nextInt();
                System.out.println("Enter a seat number in that row:");
                int colIndex = m_scanner.nextInt();
                //Ticket Price
                int ticketPrice = cinRoom.getTicketPrice(rowIndex, colIndex);
                if(cinRoom.bookSeat(rowIndex, colIndex)) break;
                else {handleInput(2, cinRoom); break;}
            case 3: //statistics
                cinRoom.printStatistics();
                break;
            case 0: //exit
                return false;
        }
        return true;
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    private void task1() {
        int rows = 7;
        int cols = 8;

        System.out.println("Cinema:");
        System.out.print("  ");
        int[] header = {1,2,3,4,5,6,7,8};
        printRow(header);
        for(int i = 0; i< rows; i++) {
            System.out.printf("%d ", i+1);
            String str = new String("SSSSSSSS");
            printRow(str);
        }
    }

    private static void printRow(String s) {
        int i = 0;
        while(i<s.length()){
            System.out.printf("%c ", s.charAt(i));
            i++;
        }
        System.out.printf("%n");
    }

    private static void printRow(int[] arr) {
        int i = 0;
        while(i<arr.length){
            System.out.printf("%d ", arr[i]);
            i++;
        }
        System.out.printf("%n");
    }
}