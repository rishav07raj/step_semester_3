import java.util.Arrays;

public class P5_FantasyLeagueAutoDraft implements Comparable<P5_FantasyLeagueAutoDraft> {

    String name;
    int matchesPlayed;
    double battingAverage;
    boolean injured;

    public P5_FantasyLeagueAutoDraft(String name, int matchesPlayed,
                                     double battingAverage, boolean injured) {
        this.name = name;
        this.matchesPlayed = matchesPlayed;
        this.battingAverage = battingAverage;
        this.injured = injured;
    }

    static boolean isDraftable(int matchesPlayed) {
        return matchesPlayed >= 10;
    }

    static boolean isDraftable(int matchesPlayed, boolean injured) {
        return matchesPlayed >= 5 && !injured;
    }

    @Override
    public int compareTo(P5_FantasyLeagueAutoDraft other) {
        return Double.compare(other.battingAverage, this.battingAverage);
    }

    static String draftAndRank(P5_FantasyLeagueAutoDraft[] players) {

        P5_FantasyLeagueAutoDraft[] draftable =
                new P5_FantasyLeagueAutoDraft[players.length];

        int count = 0;

        for (P5_FantasyLeagueAutoDraft player : players) {

            if (isDraftable(player.matchesPlayed) ||
                isDraftable(player.matchesPlayed, player.injured)) {

                draftable[count] = player;
                count++;
            }
        }

        P5_FantasyLeagueAutoDraft[] result =
                Arrays.copyOf(draftable, count);

        Arrays.sort(result);

        String output = "";

        for (int i = 0; i < result.length; i++) {

            output += (i + 1) + ". " + result[i].name;

            if (i < result.length - 1)
                output += " | ";
        }

        return output;
    }

    public static void main(String[] args) {

        P5_FantasyLeagueAutoDraft[] players = {

            new P5_FantasyLeagueAutoDraft(
                "Virat", 15, 48.0, false),

            new P5_FantasyLeagueAutoDraft(
                "Rahul", 7, 55.0, false),

            new P5_FantasyLeagueAutoDraft(
                "Sameer", 3, 60.0, false),

            new P5_FantasyLeagueAutoDraft(
                "Dev", 12, 20.0, true)
        };

        System.out.println(draftAndRank(players));
    }
}