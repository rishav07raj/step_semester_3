
import java.util.Scanner;

abstract class Ticket {
    static final double FEE = 20.0;
    int count;

    Ticket(int count) {
        this.count = count;
    }

    abstract double price();

    double amount() {
        return (price() + FEE) * count;
    }
}

class Regular extends Ticket {
    Regular(int count) {
        super(count);
    }

    double price() {
        return 150.0;
    }
}

class Premium extends Ticket {
    Premium(int count) {
        super(count);
    }

    double price() {
        return 250.0;
    }
}

class Recliner extends Ticket {
    Recliner(int count) {
        super(count);
    }

    double price() {
        return 400.0;
    }
}

public class MovieTicketCounter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            int count = sc.nextInt();
            Ticket t;

            switch (type) {
                case "REGULAR": t = new Regular(count); break;
                case "PREMIUM": t = new Premium(count); break;
                case "RECLINER": t = new Recliner(count); break;
                default: throw new IllegalArgumentException("Invalid seat type");
            }

            double amount = t.amount();
            System.out.printf("%s: %.2f%n", type, amount);
            total += amount;
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
