//数组元素作函数实参
# include <iostream>
using namespace std;
void change(int a,int b)
{
  int temp;
  temp=a;  a=b;  b=temp;  //a与b的值互换
}

int main()
{
  int a[]={1,2,3,4,5,6,7,8};
  int b[]={-1,-2,-3,-4,-5,-6,-7,-8};
  int i;
  for(i=0;i<8;i++)
    change(a[i],b[i]);
  for(i=0;i<8;i++)
    cout<<a[i]<<b[i]<<endl;
  system("pause");
  return 0;    
}
