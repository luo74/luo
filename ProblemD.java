import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ProblemD {

    static int[][] graph;   // Graph consists of edges. An edge contains two connected vertices.
    static List<int[]> cycles = new ArrayList<int[]>();

    public static void main(String[] args) {

        read();
        for (int[] edge : graph)
            for (int vertex : edge) {
                findCycle(new int[]{vertex});
            }

        findLongestCycle();
    }

    static void findCycle(int[] path) {
        int keyOfCurrentVertex = path[0];
        int x;
        int[] sub_path = new int[path.length + 1];

        for (int[] edge : graph)
            for (int y = 0; y <= 1; y++)
                if (edge[y] == keyOfCurrentVertex)
                //  edge refers to our current node
                {
                    x = edge[(y + 1) % 2];
                    if (!visited(x, path))
                    //  neighbor node not on path yet
                    {
                        sub_path[0] = x;
                        System.arraycopy(path, 0, sub_path, 1, path.length);
                        //  explore extended path
                        findCycle(sub_path);
                    } else if ((path.length > 2) && (x == path[path.length - 1]))
                    //  cycle found
                    {
                        int[] p = arrangePath(path);
                        int[] inv = invert(path);
                        if (isNew(p) && isNew(inv)) {
                            cycles.add(p);
                        }
                    }
                }
    }

    //  check of both arrays have same lengths and contents
    static Boolean isEqual(int[] a, int[] b) {
        if (a.length != b.length)
            return false;

        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    //  create a path array with reversed order
    static int[] invert(int[] path) {
        int[] p = new int[path.length];

        for (int i = 0; i < path.length; i++) {
            p[i] = path[path.length - 1 - i];
        }

        return arrangePath(p);
    }

    //  rotate cycle path such that it begins with the smallest node
    static int[] arrangePath(int[] path) {
        int[] p = new int[path.length];
        int x = smallest(path);
        int n;

        System.arraycopy(path, 0, p, 0, path.length);

        while (p[0] != x) {
            n = p[0];
            System.arraycopy(p, 1, p, 0, p.length - 1);
            p[p.length - 1] = n;
        }

        return p;
    }

    //  compare path against known cycles
    //  return true, iff path is not a known cycle
    static Boolean isNew(int[] path) {
        for (int[] p : cycles) {
            if (isEqual(p, path)) {
                return false;
            }
        }
        return true;
    }

    private static void findLongestCycle() {
        int[] longestCycle = new int[0];
        int size = 0;
        for (int[] cycle : cycles) {
            if (cycle.length > size) {
                size = cycle.length;
                longestCycle = cycle;
            }
        }
        Arrays.sort(longestCycle);
        String output = "";
        for (int v : longestCycle) {
            output += v + " ";
        }
        System.out.println(longestCycle.length);
        System.out.println(output);
    }

    //  return the int of the array which is the smallest
    static int smallest(int[] path) {
        int min = path[0];

        for (int p : path) {
            if (p < min)
                min = p;
        }
        return min;
    }

    //  check if vertex is contained in path
    static Boolean visited(int key, int[] path) {
        for (int vertex : path) {
            if (vertex == key)
                return true;
        }
        return false;
    }

    private static void read() {
        Scanner scanner = new Scanner(System.in);
        int N = Integer.parseInt(scanner.nextLine());
        graph = new int[N][2];
        for (int i = 0; i < N; i++) {
            String line = scanner.nextLine();
            String[] s = line.split("\\s+");
            graph[i][0] = Integer.parseInt(s[0]);
            graph[i][1] = Integer.parseInt(s[1]);
        }
        scanner.close();
    }
}