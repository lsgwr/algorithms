#include <iostream>
using namespace std;
int n,number;
int a[20]={0};

int Try(int x,int y)/*测试x行y列可否摆放棋子*/
{
 int j=1;
 while(j<x) /*与数组中已放好的数比较*/
 {
  if((a[j]==y)||(abs(x-j)==abs(a[j]-y)))
    return 0;
  j++;
 }
 return 1;
}

void place(int x)//递归 
{
 int y;
 if(x>n)
    number++;//print();/*已到末尾结束,打印结果*/
 else
 {
    for(y=1;y<=n;y++)/*控制每一列的棋子共试8次*/
    {
       if(Try(x,y))//如果可以摆放 
       {
         a[x]=y;/*给a[x]赋值*/
         place(x+1);/*继续下一列的递归*/
       }
    }
 }
}

int main()
{
  freopen("queen.in","r",stdin);
  freopen("queen.out","w",stdout);
  cin>>n;
  place(1);
  cout<<number<<endl;
}

