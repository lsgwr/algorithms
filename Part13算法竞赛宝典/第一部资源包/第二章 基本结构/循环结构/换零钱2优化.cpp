//换零钱2优化
#include<iostream>
using namespace std;
int main()
{
    int count=0,x,i;
    cin>>x;
    x=(x-16)/5+1;//求5元面值张数的最大值，即10元张数=0时的方法数量 
    count=(x+1)*(x+1)/4;//等差求和 
    cout<<count<<endl;
    system("pause");     
}
