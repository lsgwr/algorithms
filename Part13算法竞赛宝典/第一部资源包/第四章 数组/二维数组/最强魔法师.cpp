//最强魔法师
#include <iostream>
using namespace std;

int main()
{
  int a[3][4]={{1,2,3,4},{5,6,7,8},{-10,-3,-4,4}};
  int i,j,row=0,colum=0,MAX=a[0][0];
  cout<<"a数组元素为：\n";
  for(i=0;i<=2;i++)
     for(j=0;j<=3;j++)
        if(a[i][j]>MAX)
        {
          MAX=a[i][j];
          row=i;
          colum=j;
        }
  cout<<"max="<<MAX<<" row="<<row<<" colum="<<colum;
  system("pause");
  return 0;
} 
