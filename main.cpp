#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

const int WIDTH = 80;
const int HEIGHT = 25;

char canvas[HEIGHT][WIDTH];

void initCanvas() {
    for (int y = 0; y < HEIGHT; y++)
        for (int x = 0; x < WIDTH; x++)
            canvas[y][x] = ' ';
}

void plot(int x, int y) {
    if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT)
        canvas[y][x] = '*';
}

void koch(double x1, double y1, double x2, double y2, int depth) {
    if (depth == 0) {
        int steps = max(abs(x2 - x1), abs(y2 - y1)) + 1;
        for (int i = 0; i <= steps; i++) {
            double t = (double)i / steps;
            int x = x1 + t * (x2 - x1);
            int y = y1 + t * (y2 - y1);
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

    double xC = xA + dx * cos(M_PI / 3) - dy * sin(M_PI / 3);
    double yC = yA + dx * sin(M_PI / 3) + dy * cos(M_PI / 3);

    koch(x1, y1, xA, yA, depth - 1);
    koch(xA, yA, xC, yC, depth - 1);
    koch(xC, yC, xB, yB, depth - 1);
    koch(xB, yB, x2, y2, depth - 1);
}

void printCanvas() {
    for (int y = 0; y < HEIGHT; y++) {
        for (int x = 0; x < WIDTH; x++)
            cout << canvas[y][x];
        cout << "\n";
    }
}

int main() {
    initCanvas();
    int depth;
    cout << "Введите n: ";
    cin >> depth;

    koch(5, HEIGHT / 2, WIDTH - 5, HEIGHT / 2, depth);
    printCanvas();

    return 0;
}