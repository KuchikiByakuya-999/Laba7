#include <iostream>
#include <vector>
#include <climits>

using namespace std;

bool isSafe(const vector<vector<int>>& adjMatrix, const vector<int>& colors, int country, int color) {
    for (int neighbor = 0; neighbor < adjMatrix.size(); ++neighbor) {
        if (adjMatrix[country][neighbor] == 1 && colors[neighbor] == color) {
            return false;
        }
    }
    return true;
}

void backtrack(const vector<vector<int>>& adjMatrix, vector<int>& colors, vector<int>& bestColoring, 
               int& minColors, int country, int numUsedColors) {
    if (country == adjMatrix.size()) {
        if (numUsedColors < minColors) {
            minColors = numUsedColors;
            bestColoring = colors;
        }
        return;
    }

    for (int color = 1; color <= min(numUsedColors + 1, minColors - 1); ++color) {
        if (isSafe(adjMatrix, colors, country, color)) {
            colors[country] = color;
            backtrack(adjMatrix, colors, bestColoring, minColors, 
                     country + 1, max(numUsedColors, color));
            colors[country] = 0;
        }
    }
}

void colorGraph(const vector<vector<int>>& adjMatrix, vector<int>& bestColoring, int& minColors) {
    vector<int> colors(adjMatrix.size(), 0);
    minColors = INT_MAX;
    backtrack(adjMatrix, colors, bestColoring, minColors, 0, 0);
}

int main() {
    vector<vector<int>> adjacencyMatrix = {
        {1, 0, 0, 1},
        {0, 0, 1, 0},
        {0, 1, 1, 1},
        {1, 1, 0, 1}

    };

    vector<int> bestColoring;
    int minColors;
    
    colorGraph(adjacencyMatrix, bestColoring, minColors);

    cout << "Минимальное количество цветов: " << minColors << endl;
    cout << "Раскраска стран: ";
    for (int color : bestColoring) {
        cout << color << " ";
    }
    cout << endl;

    return 0;
}