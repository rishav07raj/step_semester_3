
import java.util.Scanner;

public class ClassAttendanceTracker {

    static void attendanceSummary(int[] days) {
        int present = 0, streak = 0, longest = 0;

        for (int day : days) {
            if (day == 1) {
                present++;
                streak++;

                if (streak > longest) {
                    longest = streak;
                }
            } else {
                streak = 0;
            }
        }

        System.out.println("Present: " + present +
                           ", Longest streak: " + longest);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] days = new int[n];

        for (int i = 0; i < n; i++) {
            days[i] = sc.nextInt();
        }

        attendanceSummary(days);
        sc.close();
    }
}
