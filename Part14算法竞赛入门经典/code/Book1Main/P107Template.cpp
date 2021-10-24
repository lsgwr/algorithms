#include <iostream>

using namespace std;

template<typename T>
struct Point {
    T x, y;

    Point(T x = 0, T y = 0) : x(x), y(y) {}
};

template<typename T>
Point<T> operator+(const Point<T> &A, const Point<T> &B) {
    return Point<T>(A.x + B.x, A.y + B.y);
}

template<typename T>
ostream &operator<<(ostream &out, const Point<T> &p) {
    out << "(" << p.x << "," << p.y << ")";
    return out;
}

template<typename T>
T sum(T *begin, T *end) {
    T ans = 0;
    for (T *p = begin; p != end; p++) {
        ans = ans + *p;
    }
    return ans;
}

int main() {
    Point<double> c(1.1, 2.2), d(3.3, 4.4);
    cout << c + d << endl;
    Point<int> b[] = {Point<int>(1, 2), Point<int>(3, 4), Point<int>(5, 6), Point<int>(7, 8)};
    cout << sum(b, b + 4) << endl;
    return 0;
}