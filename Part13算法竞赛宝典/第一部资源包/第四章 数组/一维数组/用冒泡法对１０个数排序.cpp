//用冒泡法对１０个数排序（由小到大）
#include <iostream>
using namespace std;

int main()
{
  int a[11],i,j,temp;
  for(i=1;i<11;i++)
    cin>>a[i];
  cout<<endl;
  for(j=1;j<=10-1;j++)//大循环共９次
    for(i=1;i<=10-j;i++)//每个大循环的步数逐次递减
      if(a[i]>a[i+1])//比较两数，如第１个大于第２个元素,则小数上浮,大数下沉
         { temp=a[i];a[i]=a[i+1];a[i+1]=temp;}
  for(i=1;i<11;i++)
    cout<<a[i]<<"  ";//最后打印已排好序的数组
  system("pause");
  return 0;
}
