//杨辉三角形递归求法
#include<iostream>
#include <iomanip>
using namespace std;/*从第0行开始*/
int c(int x,int y)/*求杨辉三角形中第x行第y列的值*/
{   
   int z;
   if((y==1)||(y==x+1))
      return 1; /*若为两斜边，则输出1*/
   z=c(x-1,y-1)+c(x-1,y);/*否则，其值为前一行中第y-1列与第y列值之和*/
   return z;
}

int main()
{
   int i,j,n=13;
   while(n>12)/*保证输入的值不大于12以保持屏幕效果*/
   { 
      cout<<"请输入n的值:";
      cin>>n;
   }
   for(i=0;i<=n;i++)/*控制输出n行*/
   {
       for(j=0;j<24-2*i;j++)
         cout<<" ";/*控制输出第i行前面的空格*/
       for(j=1;j<i+2;j++)
          cout<<setw(5)<<c(i,j);/*输出第i行的第j个值*/
       cout<<endl;
   }
   system("pause");
}
