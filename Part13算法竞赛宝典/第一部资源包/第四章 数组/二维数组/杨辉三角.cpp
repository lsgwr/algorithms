//杨辉三角
# include <iostream>
#include <iomanip>
# define N 11
using namespace std;

int main()
{
  int i,j,a[N][N]={0};//注意：a[N][N]必须赋初值
  for(i=1;i<N;i++)   
     a[i][i]=1,a[i][1]=1;//注意此处用了逗号，所以整行算一条语句
  for(i=3;i<N;i++)   //循环赋值
    for(j=2;j<=i-1;j++)
      a[i][j]=a[i-1][j-1]+a[i-1][j];
	
  for(i=1;i<N;i++) //打印
  {
    for(j=1;j<=i;j++)  
      cout<<setw(6)<<left<<a[i][j];
    cout<<"\n";
  }    
  system("pause");
  return 0;
}
