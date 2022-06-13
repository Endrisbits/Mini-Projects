
import java.util.Arrays;
import java.util.Scanner;

public class CinemaManager {
    private static Scanner m_scanner=new Scanner(System.in);

    public static void main(String[] args) {
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
                int rowIndex = m_scanner.nextInt() - 1; //Index starts from 1 for the user
                System.out.println("Enter a seat number in that row:");
                int colIndex = m_scanner.nextInt() - 1; //Index starts from 1 for the user
                //Ticket Price
                int ticketPrice = cinRoom.getTicketPrice(rowIndex);
                if(cinRoom.bookSeat(rowIndex, colIndex)) break;
                else {handleInput(2, cinRoom); break;}
            case 3: //statistics
                cinRoom.printStatistics();
                break;
            case 0: //exit
                return false;
            default:
                System.out.println("This option does not exist! Please try again...");
                break;
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
}