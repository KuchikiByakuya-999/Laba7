import java.util.Arrays;
import java.util.ArrayList;

public class main {
    private static boolean isSafe(int[][] adjMatrix, int[] colors, int country, int color) {
        for (int neighbor = 0; neighbor < adjMatrix.length; ++neighbor) {
            if (adjMatrix[country][neighbor] == 1 && colors[neighbor] == color) {
                return false;
            }
        }
        return true;
    }

    private static void backtrack(int[][] adjMatrix, int[] colors, int[] bestColoring, 
                                 int[] minColors, int country, int numUsedColors) {
        if (country == adjMatrix.length) {
            if (numUsedColors < minColors[0]) {
                minColors[0] = numUsedColors;
                System.arraycopy(colors, 0, bestColoring, 0, colors.length);
            }
            return;
        }

        for (int color = 1; color <= Math.min(numUsedColors + 1, minColors[0] - 1); ++color) {
            if (isSafe(adjMatrix, colors, country, color)) {
                colors[country] = color;
                backtrack(adjMatrix, colors, bestColoring, minColors, 
                         country + 1, Math.max(numUsedColors, color));
                colors[country] = 0; // Откат
            }
        }
    }

    // Основная функция для раскраски графа
    public static void colorGraph(int[][] adjMatrix, int[] bestColoring, int[] minColors) {
        int[] colors = new int[adjMatrix.length];
        Arrays.fill(colors, 0);
        minColors[0] = Integer.MAX_VALUE;
        backtrack(adjMatrix, colors, bestColoring, minColors, 0, 0);
    }

    public static void main(String[] args) {
        // Пример матрицы смежности (10 стран)
        int[][] adjacencyMatrix = {
            {0,1,0,0,1,1,0,0,0,0},
            {1,0,1,0,0,0,1,0,0,0},
            {0,1,0,1,0,0,0,1,0,0},
            {0,0,1,0,1,0,0,0,1,0},
            {1,0,0,1,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,1,1,0},
            {0,1,0,0,0,0,0,0,1,1},
            {0,0,1,0,0,1,0,0,0,1},
            {0,0,0,1,0,1,1,0,0,0},
            {0,0,0,0,1,0,1,1,0,0}
        };

        int[] bestColoring = new int[adjacencyMatrix.length];
        int[] minColors = new int[1]; // Используем массив для передачи по ссылке
        
        colorGraph(adjacencyMatrix, bestColoring, minColors);

        System.out.println("Минимальное количество цветов: " + minColors[0]);
        System.out.print("Раскраска стран: ");
        for (int color : bestColoring) {
            System.out.print(color + " ");
        }
        System.out.println();
    }
}