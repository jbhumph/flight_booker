package plane;

public class Seat {
    private char row;
    private int seat;
    private boolean available;
    private int passenger;
    private boolean window;
    private boolean priority;
    private String pClass;

    public Seat(char row, int seat, boolean window, String pClass) {
        this.row = row;
        this.seat = seat;
        this.available = true;
        this.passenger = 0;
        this.window = window;
        this.priority = false;
        this.pClass = pClass;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setPassenger(int passenger) {
        this.passenger = passenger;
        this.available = false;
    }

    public char getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public String getpClass() {
        return pClass;
    }


}
