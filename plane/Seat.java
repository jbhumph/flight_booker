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


}
