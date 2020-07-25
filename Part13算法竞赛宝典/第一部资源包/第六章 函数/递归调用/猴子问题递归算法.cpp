//递归算法
#include <iostream>
using namespace std;
int m,k;
int Josephus(int m)//k为起始报数位置 
{
int ans;
   if(m==1)
    return 1;
   else 
    ans=(Josephus(m-1)+k-1)%m+1;
   //cout<<ans<<' ';  
   return ans; //返回上一轮猴王位置
}
int main()
{   
  cin>>m>>k;
  cout<<Josephus(m)<<endl;
  system("pause");
}   
