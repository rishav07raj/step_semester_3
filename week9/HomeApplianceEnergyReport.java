
import java.util.Scanner;

interface SaverMode {
    double SAVER_FACTOR = 0.75;
}

abstract class Appliance {
    double hours;

    Appliance(double hours) {
        this.hours = hours;
    }

    abstract double power();

    double units() {
        return power() * hours / 1000.0;
    }

    double cost() {
        return units() * 8.0;
    }
}

class Fridge extends Appliance {
    Fridge(double hours) {
        super(hours);
    }

    double power() {
        return 150.0;
    }
}

class AC extends Appliance implements SaverMode {
    boolean saver;

    AC(double hours, boolean saver) {
        super(hours);
        this.saver = saver;
    }

    double power() {
        return 1500.0;
    }

    double units() {
        double u = super.units();
        return saver ? u * SAVER_FACTOR : u;
    }

    double cost() {
        return units() * 8.0;
    }
}

class TV extends Appliance {
    TV(double hours) {
        super(hours);
    }

    double power() {
        return 100.0;
    }
}

class Washer extends Appliance implements SaverMode {
    boolean saver;

    Washer(double hours, boolean saver) {
        super(hours);
        this.saver = saver;
    }

    double power() {
        return 500.0;
    }

    double units() {
        double u = super.units();
        return saver ? u * SAVER_FACTOR : u;
    }

    double cost() {
        return units() * 8.0;
    }
}

public class HomeApplianceEnergyReport {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double hours = sc.nextDouble();
            boolean saver = sc.hasNext("SAVER");

            if (saver) {
                sc.next();
            }

            Appliance a;

            switch (type) {
                case "FRIDGE":
                    if (saver) {
                        System.out.println("FRIDGE: saver mode not supported");
                        continue;
                    }
                    a = new Fridge(hours);
                    break;

                case "AC":
                    a = new AC(hours, saver);
                    break;

                case "TV":
                    if (saver) {
                        System.out.println("TV: saver mode not supported");
                        continue;
                    }
                    a = new TV(hours);
                    break;

                case "WASHER":
                    a = new Washer(hours, saver);
                    break;

                default:
                    throw new IllegalArgumentException("Invalid appliance type");
            }

            double units = a.units();
            double cost = a.cost();

            System.out.printf(
                "%s: Units=%.2f Cost=%.2f%n", type, units, cost
            );
            total += cost;
        }

        System.out.printf("Total Cost: %.2f%n", total);
        sc.close();
    }
}
