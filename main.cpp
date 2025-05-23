#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>

using namespace std;

int maxLectures(vector<vector<int>>& lectures, int currentIndex = 0, int lastEndTime = 0) {
    if (currentIndex == 0 && lastEndTime == 0) {
        if (lectures.empty()) return 0;
        
        sort(lectures.begin(), lectures.end(), [](const vector<int>& a, const vector<int>& b) {
            return a[1] < b[1];
        });
    }

    if (currentIndex >= lectures.size()) {
        return 0;
    }

    int option1 = maxLectures(lectures, currentIndex + 1, lastEndTime);
    int option2 = 0;
    if (lectures[currentIndex][0] >= lastEndTime) {
        option2 = 1 + maxLectures(lectures, currentIndex + 1, lectures[currentIndex][1]);
    }

    return max(option1, option2);
}

int main() {
    int n;
    cout << "Введите количество лекций: ";
    cin >> n;
    
    vector<vector<int>> lectures(n, vector<int>(2));
    
    cout << "Введите время начала и окончания для каждой лекции (в формате 'начало конец'):" << endl;
    for (int i = 0; i < n; i++) {
        cout << "Лекция " << i+1 << ": ";
        cin >> lectures[i][0] >> lectures[i][1];
        
        if (lectures[i][0] < 8 || lectures[i][1] > 21) {
            cout << "Ошибка: лекция должна быть в промежутке 8-21 часов" << endl;
            return 1;
        }
    }
    
    int result = maxLectures(lectures);
    cout << "Максимальное количество лекций, которые можно посетить: " << result << endl;
    
    return 0;
}