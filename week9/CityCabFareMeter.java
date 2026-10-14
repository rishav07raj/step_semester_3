
import java.util.Scanner;

interface NightService {
    double nightFare(double fare);
}

abstract class Cab {
    double km;

    Cab(double km) {
        this.km = km;
    }

    abstract double rate();

    double fare() {
        return Math.max(100.0, km * rate());
    }
}

class Mini extends Cab {
    Mini(double km) {
        super(km);
    }

    double rate() {
        return 10.0;
    }
}

class Sedan extends Cab implements NightService {
    Sedan(double km) {
        super(km);
    }

    double rate() {
        return 14.0;
    }

    public double nightFare(double fare) {
        return fare * 1.20;
    }
}

class SUV extends Cab implements NightService {
    SUV(double km) {
        super(km);
    }

    double rate() {
        return 18.0;
    }

    public double nightFare(double fare) {
        return fare * 1.20;
    }
}

public class CityCabFareMeter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double km = sc.nextDouble();
            String time = sc.next();
            Cab c;

            switch (type) {
                case "MINI": c = new Mini(km); break;
                case "SEDAN": c = new Sedan(km); break;
                case "SUV": c = new SUV(km); break;
                default: throw new IllegalArgumentException("Invalid cab type");
            }

            if (time.equals("NIGHT") && !(c instanceof NightService)) {
                System.out.println(type + ": night service not available");
                continue;
            }

            double fare = c.fare();

            if (time.equals("NIGHT")) {
                fare = ((NightService) c).nightFare(fare);
            }

            System.out.printf("%s: %.2f%n", type, fare);
            total += fare;
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
