import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProblemB {

    static class Friend {
        int red, green, blue, unhappiness;

        Friend(int red, int green, int blue) {
            this.red = red;
            this.green = green;
            this.blue = blue;
        }
    }

    private static ArrayList<Friend> friends = new ArrayList<Friend>();

    public static void main(String[] args) {

        read();
        ArrayList<Integer> optimals = calculateOptimal(friends);
        int total_unhappiness = calculateUnhappiness(friends, optimals);
        System.out.println(total_unhappiness);

    }

    private static ArrayList<Integer> calculateOptimal(List<Friend> friends) {
        int totalRed = 0, totalGreen = 0, totalBlue = 0;
        ArrayList<Integer> optimals = new ArrayList<Integer>();

        for (int i = 0; i < friends.size(); i++) {
            totalRed = totalRed + friends.get(i).red;
            totalGreen = totalGreen + friends.get(i).green;
            totalBlue = totalBlue + friends.get(i).blue;
        }

        optimals.add(Math.round((float) totalRed / friends.size()));
        optimals.add(Math.round((float) totalGreen / friends.size()));
        optimals.add(Math.round((float) totalBlue / friends.size()));

        return optimals;
    }

    private static int calculateUnhappiness(List<Friend> Friends, List<Integer> optimals) {
        int optimalRed = optimals.get(0);
        int optimalGreen = optimals.get(1);
        int optimalBlue = optimals.get(2);

        for (int i = 0; i < Friends.size(); i++) {
            Friends.get(i).unhappiness = Math.abs(optimalRed - Friends.get(i).red)
                    + Math.abs(optimalGreen - Friends.get(i).green)
                    + Math.abs(optimalBlue - Friends.get(i).blue);
        }

        int total = 0;  // total unhappiness
        for (int j = 0; j < Friends.size(); j++) {
            total = total + Friends.get(j).unhappiness;
        }
        return total;
    }

    private static void read() {
        Scanner scanner = new Scanner(System.in);
        int N = Integer.parseInt(scanner.nextLine());
        String line = scanner.nextLine();
        while (line != null) {
            String[] s = line.split("\\s+");
            int red = Integer.parseInt(s[0]);
            int green = Integer.parseInt(s[1]);
            int blue = Integer.parseInt(s[2]);
            friends.add(new Friend(red, green, blue));
            line = scanner.nextLine();
        }
        scanner.close();
    }
}


