//二维数组的行和列元素互换
#include <iostream>
using namespace std;

int main()
{
  int a[2][3]={{1,2,3},{4,5,6}};
  int b[3][2],i,j;
  cout<<"a数组元素为："<<endl;
  for(i=0;i<=1;i++)//打印a的元素
  {
     for(j=0;j<=2;j++)
     {
        cout<<a[i][j]<<"  ";
        b[j][i]=a[i][j];//元素行列互换
     }
     cout<<endl;
  }
  cout<<"b数组元素为：\n";
  for(j=0;j<=2;j++)  //打印b的元素
  {
    for(i=0;i<=1;i++)
      cout<<b[j][i]<<"  ";
    cout<<endl;
  }
  system("pause");
  return 0;
} 
