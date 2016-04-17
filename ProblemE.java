import java.util.Arrays;
import java.util.Scanner;

public class ProblemE {

    static int N;
    static int K;
    static int[] doors;
    static int[] brushes;

    public static void main(String[] args) {
        read();
        int beauty = 0;
        Arrays.sort(doors);
        Arrays.sort(brushes);
        for (int i = N - 1; i >= 0; i--) {
            beauty += doors[i] * brushes[K - 1];
            brushes[K - 1]--;
            Arrays.sort(brushes);
        }
        System.out.println(beauty);
    }

    private static void read() {
        Scanner scanner = new Scanner(System.in);
        String[] s = scanner.nextLine().split("\\s+");
        N = Integer.parseInt(s[0]);
        K = Integer.parseInt(s[1]);
        doors = new int[N];
        brushes = new int[K];
        s = scanner.nextLine().split("\\s+");
        for (int i = 0; i < s.length; i++) {
            doors[i] = Integer.parseInt(s[i]);
        }
        s = scanner.nextLine().split("\\s+");
        for (int i = 0; i < s.length; i++) {
            brushes[i] = Integer.parseInt(s[i]);
        }
    }

}
