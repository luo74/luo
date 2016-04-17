import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProblemC {
    static class Point {
        int x;
        int y;
        double distance;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        private void setDistance(double distance) {
            this.distance = distance;
        }
    }

    private static ArrayList<Point> map = new ArrayList<>();
    private static ArrayList<Point> secondMap = new ArrayList<>();

    public static void main(String[] args) {
        read();
        setDistances(map);
        setDistances(secondMap);
        int index = catchDistanceSequence();
        if (index != -1) {
            System.out.println(index);
        }
    }

    private static int catchDistanceSequence() {
        int index = -1;
        for (int i = 0; i < map.size(); i++) {
            if (secondMap.get(0).distance == map.get(i).distance) {
                boolean allChecked = true;
                index = i + 1;
                for (int j = 0; j < map.size(); j++) {
                    if (secondMap.get(j).distance != map.get((i + j) % map.size()).distance) {
                        index = -1;
                        allChecked = false;
                        break;
                    }
                }
                if (allChecked)
                    return index;
            }
        }
        return index;
    }

    private static void setDistances(List<Point> map) {
        for (int i = 0; i < map.size(); i++) {
            int x0 = map.get(i).x;
            int y0 = map.get(i).y;
            int x1 = map.get((i + 1) % map.size()).x;
            int y1 = map.get((i + 1) % map.size()).y;

            double distance = Math.pow(x1 - x0, 2) + Math.pow(y1 - y0, 2);
            distance = Math.sqrt(distance);

            map.get(i).setDistance(distance);
        }
    }

    private static void read() {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        String line = scanner.nextLine();
        if (line != null) {
            String[] s = line.split("\\s+");
            for (int i = 0; i < s.length; i++) {
                Point p = new Point(Integer.parseInt(s[i]), Integer.parseInt(s[++i]));
                map.add(p);
            }
        }
        line = scanner.nextLine();
        if (line != null) {
            String[] s = line.split("\\s+");
            for (int i = 0; i < s.length; i++) {
                Point p = new Point(Integer.parseInt(s[i]), Integer.parseInt(s[++i]));
                secondMap.add(p);
            }
        }
        scanner.close();
    }
}
