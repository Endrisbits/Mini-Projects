package cinema;

public class CinemaRoom {
    private int m_rows;
    private int m_seatsPerRow;

    private enum seatsState {S, B};
    
     
    private int m_smallRoomLimit = 60; //seats.
    private int m_smallRoomPrice = 10;
    //Prices for Big rooms
    private int m_frontHalfPrice = 10;
    private int m_backHalfPrice = 8;

    private int m_totalBookedTickets;
    private seatsState[][] seatsMatrix;

    CinemaRoom(int rows, int seatsPerRow){
        this.m_rows = rows;
        this.m_seatsPerRow = seatsPerRow;
        this.seatsMatrix = new seatsState[rows][seatsPerRow];
        this.m_totalBookedTickets=0;
        populateSeatsMatrix();
    }

    private void populateSeatsMatrix(){
        for(int i = 0; i<this.m_rows; i++){
            for(int j = 0; j<this.m_seatsPerRow; j++) {
                seatsMatrix[i][j] = seatsState.S;
            }
        }
    }

    private void printHeader(int rows) {
        System.out.printf("%nCinema:%n");
        System.out.print("  ");
        for(int i = 1; i<= m_seatsPerRow; i++) {System.out.printf("%d ", i);}
        System.out.println();
    }

    public void printRoomLayout(){
        printHeader(this.m_rows);
        for(int i = 0; i<this.m_rows; i++) {
            System.out.printf("%d ", i+1);
            for(seatsState s: seatsMatrix[i]) System.out.printf("%s ", s.name());
            System.out.println();
        }
        System.out.println();
    }

    public int getTicketPrice(int rowindex, int colIndex) { //Indexes start from 1
        if(this.m_rows * this.m_seatsPerRow < this.m_smallRoomLimit) return m_smallRoomPrice;
        else { //Big room. Now price depends on row position
            if(rowindex <= this.m_rows /2) return this.m_frontHalfPrice;
            else return this.m_backHalfPrice;
        }
    }

    public boolean bookSeat(int rowindex, int colIndex) {//Indexes start from 1.
        if(rowindex>this.m_rows || colIndex> this.m_seatsPerRow) {
            System.out.println("Wrong input!");
            return false;
        }
        else if(this.seatsMatrix[rowindex-1][colIndex-1] == seatsState.S) {
            this.seatsMatrix[rowindex-1][colIndex-1] = seatsState.B;
            this.m_totalBookedTickets++;
            System.out.printf("Booked! Ticket Price: $%d%n", this.getTicketPrice(rowindex, colIndex));
            return true;
        }
        else {
            System.out.println("That ticket has already been purchased!");
            return false;
        }
    }

    public void printStatistics() {
       System.out.printf("%nNumber of purchased tickets: %d", this.getTotalBookedTickets());
       float percBooked = (float) (100 * this.getTotalBookedTickets()*1.0)/(this.m_rows*this.m_seatsPerRow);
       System.out.printf("%nPercentage: %.2f%%", percBooked, this.m_rows*this.m_seatsPerRow);
       System.out.printf("%nCurrent income: $%d", this.calculateCurrentIncome());
       System.out.printf("%nTotal income: $%d", this.calculateTotalProfit());
    }

    private int calculateCurrentIncome() {
        int currentIncome = 0;
        for(int i=0; i<this.m_rows; i++){
            for(int j=0; j<this.m_seatsPerRow; j++) if(seatsMatrix[i][j]==seatsState.B) currentIncome+=this.getTicketPrice(i+1,j+1);
        }
        return currentIncome;
    }

    private int getTotalBookedTickets() {
        return m_totalBookedTickets;
    }

    public int calculateTotalProfit() {
        int totalSeats = this.m_rows * this.m_seatsPerRow;
        if (totalSeats<=this.m_smallRoomLimit){
            return totalSeats * this.m_smallRoomPrice;
        }
        else {
            int frontHalfRows = this.m_rows/2;
            int backHalfRows = this.m_rows-frontHalfRows;
            return (frontHalfRows * this.m_seatsPerRow * m_frontHalfPrice) + (backHalfRows * this.m_seatsPerRow * m_backHalfPrice);
        }
    }
}

