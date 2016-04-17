import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProblemG {

    static class Point {
        double x;
        double y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    static List<Point> vertices = new ArrayList<>();
    static int numOfA = 0, numOfB = 0;
    static int material1;
    static int material2;
    static int profitA;
    static int profitB;
    static int total_profit = 0;

    public static void main(String[] args) {

        read();

        findVertices(vertices);
        calculateTotalProfit();

        String output = numOfA + "\n" + numOfB + "\n" + total_profit;
        System.out.println(output);

    }

    private static void calculateTotalProfit() {
        for (Point p : vertices) {
            int temp_profit = (int) p.x * profitA + (int) p.y * profitB;
            if (temp_profit > total_profit) {
                total_profit = temp_profit;
                numOfA = (int) p.x;
                numOfB = (int) p.y;
            }
        }
    }

    private static void findVertices(List<Point> vertices) {
        vertices.add(new Point(0, 0));

        double x = (2 * material1 - material2) / 6;
        double y = (2 * material2 - material1) / 6;
        if (x > 0 && y > 0)
            vertices.add(new Point(x, y));

        x = material1 / 4;
        if (material2 / 2 < x)
            x = material2 / 2;
        vertices.add(new Point(x, 0));

        y = material1 / 2;
        if (material2 / 4 < y)
            y = material2 / 4;
        vertices.add(new Point(0, y));
    }

    private static void read() {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] s = line.split("\\s+");
        material1 = Integer.parseInt(s[0]);
        material2 = Integer.parseInt(s[1]);
        line = scanner.nextLine();
        s = line.split("\\s+");
        profitA = Integer.parseInt(s[0]);
        profitB = Integer.parseInt(s[1]);
        scanner.close();
    }


}
