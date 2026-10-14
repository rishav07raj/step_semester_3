
import java.util.Scanner;

abstract class Room {
    int units;

    Room(int units) {
        this.units = units;
    }

    abstract double calculate();
}

class SingleRoom extends Room {
    SingleRoom(int units) {
        super(units);
    }

    @Override
    double calculate() {
        return units * 8.0;
    }
}

class SharedRoom extends Room {
    int occupants;

    SharedRoom(int units, int occupants) {
        super(units);
        this.occupants = occupants;
    }

    @Override
    double calculate() {
        return (units * 6.0) / occupants;
    }
}

class AcRoom extends Room {
    AcRoom(int units) {
        super(units);
    }

    @Override
    double calculate() {
        return units * 10.0 + 200;
    }
}

public class HostelElectricity {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            int units = sc.nextInt();
            Room r;

            switch (type) {
                case "SINGLE":
                    r = new SingleRoom(units);
                    break;
                case "SHARED":
                    int occupants = sc.nextInt();
                    r = new SharedRoom(units, occupants);
                    break;
                case "AC":
                    r = new AcRoom(units);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid room type");
            }

            double bill = r.calculate();
            System.out.printf("%s: %.2f%n", type, bill);
            total += bill;
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
