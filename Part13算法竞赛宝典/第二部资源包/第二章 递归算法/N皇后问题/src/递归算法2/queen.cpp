#include <iostream>
using namespace std;
int n,num;
int a[20]={0};
bool lie[31],x1[31],x2[31];

void place(int x)//递归函数
{
 int y;
 if(x>n)
    num++;/*已到末尾结束,调用打印结果的函数*/
 else
 {
    for(y=1;y<=n;y++)/*控制每一列的棋子共试8次*/
    {
       if(lie[y]==0 && x1[x+y]==0 && x2[x-y+n]==0)//如果可以摆放 
       {
         a[x]=y;/*给a[x]赋值*/
         lie[y]=1;
         x1[x+y]=1;
         x2[x-y+n]=1;//使负数+n即可转化 
         place(x+1);/*继续下一列的递归*/
         lie[y]=0;//恢复 
         x1[x+y]=0;
         x2[x-y+n]=0;
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
   cout<<num<<endl;
  // system("pause");
}

