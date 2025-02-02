package plane;

import pQueue.PQueue;

public class Plane {
    Seat[][] plane;
    private int cost = 0;
    private boolean priority = false;
    PQueue<Seat> pQueue;
    
    public Plane() {
        plane = new Seat[20][4];
        boolean window = false;
        String pClass = "";
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 4; j++) {
                if (j == 0 || j == 3) {
                    window = true;
                } else {
                    window = false;
                }
                if (i < 5) {
                    pClass = "First";
                } else if (i < 15) {
                    pClass = "Business";
                } else {
                    pClass = "Economy";
                }
                plane[i][j] = new Seat((char) (i + 65), j + 1, window, pClass);
            }
        }

    }

    public void populateSeats() {
        // populate seats with passengers
        for (int i = 0; i < 63; i++) {
            generatePassenger();
        }
        
    }

    public void generatePassenger() {
        // generate random number between 0 and 19
        int row = (int) (Math.random() * 20);
        int seat = (int) (Math.random() * 4);
        if (plane[row][seat].isAvailable()) {
            int passenger = (int) (Math.random() * 1000);
            plane[row][seat].setPassenger(passenger);
        } else {
            generatePassenger();
        }
    }

    public void displaySeats() {
        // display available seats on plane
        System.out.println("Available Seats:");
        System.out.println("FIRST CLASS:");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                if (plane[i][j].isAvailable()) {
                    System.out.println("      Seat " + plane[i][j].getRow() + plane[i][j].getSeat() + " - " + plane[i][j].getpClass());
                }
            }
        }
        System.out.println("BUSINESS CLASS:");
        for (int i = 5; i < 15; i++) {
            for (int j = 0; j < 4; j++) {
                if (plane[i][j].isAvailable()) {
                    System.out.println("      Seat " + plane[i][j].getRow() + plane[i][j].getSeat() + " - " + plane[i][j].getpClass());
                }
            }
        }
        System.out.println("ECONOMY CLASS:");
        for (int i = 15; i < 20; i++) {
            for (int j = 0; j < 4; j++) {
                if (plane[i][j].isAvailable()) {
                    System.out.println("      Seat " + plane[i][j].getRow() + plane[i][j].getSeat() + " - " + plane[i][j].getpClass());
                }
            }
        }
    }

    public boolean valid(char row, int seat) {
        // check if seat is available
        int r = (int) row - 65;
        if (plane[r][seat - 1].isAvailable()) {
            System.out.println("You have selected seat " + row + seat);
            return true;
        } else {
            System.out.println("Seat " + row + seat + " is not available. Please select another seat.");
            return false;
        }
    }   

    public void buyPriority() {
        // buy priority boarding
        cost += 50;
        priority = true;
        System.out.println("Priority boarding purchased for $50.");
    }

    public String getPriority() {
        // return priority boarding status
        if (priority) {
            return "with priority boarding";
        } else {
            return "without priority boarding";
        }
    }

    public String getpClass(int row, int seat) {
        return plane[row][seat].getpClass();
    }

    public int setPassenger(char row, int seat) {
        int r = (int) row - 65;
        int id = (int) (Math.random() * 1000);
        plane[r][seat - 1].setPassenger(id);
        return id;
    }

    public Seat getSeat(int row, int seat) {
        return plane[row][seat];
    }

    public void boardingOrder() {
        pQueue = new PQueue<>();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 4; j++) {
                if (!plane[i][j].isAvailable()) {
                    if (plane[i][j].priority() > 0) {
                        pQueue.enqueuePriority(plane[i][j]);
                    } else {
                        pQueue.enqueue(plane[i][j]);
                    }
                }
            }
        }
    }

    public void displayBoardingOrder() {
        System.out.println("Boarding order:");
        while (pQueue.getSize() > 0) {
            System.out.println("SEAT: " + pQueue.peek().getRow() + pQueue.peek().getSeat() + " - " + pQueue.peek().getpClass());
            pQueue.dequeue();
        }
    }

}
