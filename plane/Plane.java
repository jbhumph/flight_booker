package plane;

public class Plane {
    public Plane() {
        // plane has 20 rows and 4 seats per row
        Seat[][] plane = new Seat[20][4];
        // create seats for each row
        boolean window = false;
        String pClass = "";
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 4; j++) {
                // create window seats
                if (j == 0 || j == 3) {
                    window = true;
                } else {
                    window = false;
                }
                // create class
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
}
