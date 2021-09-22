#include<iostream>
using namespace std;
#define random(num)  (rand()%(num))
#define randomize()  srand((unsigned)time(NULL))

int main()
{
    randomize();
    cout<<random(1000000000)<<" "<<random(1000000000)<<endl;
}
