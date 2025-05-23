package second.second_java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class main {
    public static int maxLectures(ArrayList<int[]> lectures, int currentIndex, int lastEndTime) {

        if (currentIndex == 0 && lastEndTime == 0) {
            if (lectures.isEmpty()) return 0;
            
            Collections.sort(lectures, new Comparator<int[]>() {
                @Override
                public int compare(int[] a, int[] b) {
                    return Integer.compare(a[1], b[1]);
                }
            });
        }

        if (currentIndex >= lectures.size()) {
            return 0;
        }

        int option1 = maxLectures(lectures, currentIndex + 1, lastEndTime);
        int option2 = 0;
        if (lectures.get(currentIndex)[0] >= lastEndTime) {
            option2 = 1 + maxLectures(lectures, currentIndex + 1, lectures.get(currentIndex)[1]);
        }
        return Math.max(option1, option2);
    }

    public static int maxLectures(ArrayList<int[]> lectures) {
        return maxLectures(lectures, 0, 0);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество лекций: ");
        int n = scanner.nextInt();
        
        ArrayList<int[]> lectures = new ArrayList<>();
        
        System.out.println("Введите время начала и окончания для каждой лекции (в формате 'начало конец'):");
        for (int i = 0; i < n; i++) {
            System.out.print("Лекция " + (i+1) + ": ");
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            
            if (start < 8 || end > 21) {
                System.out.println("Ошибка: лекция должна быть в промежутке 8-21 часов");
                return;
            }
            
            lectures.add(new int[]{start, end});
        }
        
        int result = maxLectures(lectures);
        System.out.println("Максимальное количество лекций, которые можно посетить: " + result);
        
        scanner.close();
    }
}