package first.first_java;
import java.util.Scanner;

public class main {
    private static final int WIDTH = 80;
    private static final int HEIGHT = 25;
    private static char[][] canvas = new char[HEIGHT][WIDTH];

    private static void initCanvas() {
        for (int y = 0; y < HEIGHT; y++)
            for (int x = 0; x < WIDTH; x++)
                canvas[y][x] = ' ';
    }

    private static void plot(int x, int y) {
        if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT)
            canvas[y][x] = '*';
    }

    private static void kochCurve(double x1, double y1, double x2, double y2, int depth) {
        if (depth == 0) {
            int steps = Math.max((int)Math.abs(x2 - x1), (int)Math.abs(y2 - y1)) + 1;
            for (int i = 0; i <= steps; i++) {
                double t = (double)i / steps;
                int x = (int)(x1 + t * (x2 - x1));
                int y = (int)(y1 + t * (y2 - y1));
                plot(x, y);
            }
            return;
        }

        double dx = (x2 - x1) / 3;
        double dy = (y2 - y1) / 3;

        double xA = x1 + dx;
        double yA = y1 + dy;
        double xB = x2 - dx;
        double yB = y2 - dy;

        double xC = xA + dx * Math.cos(Math.PI / 3) - dy * Math.sin(Math.PI / 3);
        double yC = yA + dx * Math.sin(Math.PI / 3) + dy * Math.cos(Math.PI / 3);

        kochCurve(x1, y1, xA, yA, depth - 1);
        kochCurve(xA, yA, xC, yC, depth - 1);
        kochCurve(xC, yC, xB, yB, depth - 1);
        kochCurve(xB, yB, x2, y2, depth - 1);
    }

    private static void printCanvas() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++)
                System.out.print(canvas[y][x]);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        initCanvas();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Глубина рекурсии (n): ");
        int depth = scanner.nextInt();

        kochCurve(5, HEIGHT / 2, WIDTH - 5, HEIGHT / 2, depth);
        printCanvas();

        scanner.close();
    }
}