import java.util.Scanner;

class ATM {

    void atmPinRetry(String correctPin, String[] attempts) {

        int attempt = 0;
        boolean success = false;

        while (attempt < 3 && attempt < attempts.length && !success) {

            // Compare current attempt with correct PIN
            if (attempts[attempt].equals(correctPin)) {

                success = true;
                System.out.println("PIN accepted");

                break;
            }

            attempt++;
        }

        // Check if PIN was never accepted
        if (!success) {
            System.out.println("Card blocked — too many incorrect attempts");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Get correct PIN from user
        System.out.print("Enter correct PIN: ");
        String correctPin = sc.nextLine();

        // Array to store maximum 3 attempts
        String[] attempts = new String[3];

        // Get PIN attempts from user
        for (int i = 0; i < 3; i++) {
            System.out.print("Enter PIN attempt " + (i + 1) + ": ");
            attempts[i] = sc.nextLine();
        }

        // Call method
        ATM atm = new ATM();
        atm.atmPinRetry(correctPin, attempts);

        sc.close();
    }
}