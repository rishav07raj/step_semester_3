
import java.util.Scanner;

interface Insurable {
    double insurance(double declaredValue);
}

abstract class Parcel {
    double weight, value;

    Parcel(double weight, double value) {
        this.weight = weight;
        this.value = value;
    }

    abstract double charge();

    double insurance() {
        if (this instanceof Insurable) {
            return ((Insurable) this).insurance(value);
        }
        return 0.0;
    }

    double total() {
        return charge() + insurance();
    }
}

class StandardParcel extends Parcel {
    StandardParcel(double w, double v) {
        super(w, v);
    }

    double charge() {
        return 40 + 10 * weight;
    }
}

class ExpressParcel extends Parcel implements Insurable {
    ExpressParcel(double w, double v) {
        super(w, v);
    }

    double charge() {
        return 80 + 15 * weight;
    }

    public double insurance(double value) {
        return value * 0.02;
    }
}

class FragileParcel extends Parcel implements Insurable {
    FragileParcel(double w, double v) {
        super(w, v);
    }

    double charge() {
        return 40 + 10 * weight + 50;
    }

    public double insurance(double value) {
        return value * 0.02;
    }
}

public class ParcelShippingDesk {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double grandTotal = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double weight = sc.nextDouble();
            double value = sc.nextDouble();
            Parcel p;

            switch (type) {
                case "STANDARD":
                    p = new StandardParcel(weight, value);
                    break;
                case "EXPRESS":
                    p = new ExpressParcel(weight, value);
                    break;
                case "FRAGILE":
                    p = new FragileParcel(weight, value);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid parcel type");
            }

            double charge = p.charge();
            double insurance = p.insurance();
            double total = p.total();

            System.out.printf(
                "%s: Charge=%.2f Insurance=%.2f Total=%.2f%n",
                type, charge, insurance, total
            );
            grandTotal += total;
        }

        System.out.printf("Grand Total: %.2f%n", grandTotal);
        sc.close();
    }
}
