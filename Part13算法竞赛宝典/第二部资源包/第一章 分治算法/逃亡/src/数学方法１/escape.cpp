//逃亡－数学算法１ 
#include <iostream>
#include <cstdlib>
#include <cstdio>
using namespace std;

int main()
{
    freopen("escape.in","r",stdin);
    freopen("escape.out","w",stdout);
    double s,v1,v2;
    double t1,t2,t3; 
    cin>>s>>v1>>v2;
    //总路程 走速 车速 
    
    t1 = (s/v2-s/v1) / //甲先乘车时间 
        ((v1+(v2-v1)*v1/(v1+v2))/v2-1-(v2-v1)*(v2/v1+2)/(v1+v2));
     
    t2 = t1*(v2-v1)/(v1+v2); //车回来与乙相遇的时间 
    t3 = (s-v1*t1-v1*t2)/v2; //甲步行到终点的时间 
                             //即 乙乘车到终点的时间 
    
    printf("%.2f\n",t1+t2+t3);
    return 0;
}
