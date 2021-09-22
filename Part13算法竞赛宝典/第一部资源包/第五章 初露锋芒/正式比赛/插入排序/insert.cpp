//插入排序数,请自己根据例程修改 
#include <iostream>
using namespace std;

int main()
{
  int a[11]={1,4,6,9,13,16,19,28,40,100};
  int temp1,temp2,number,end,i,j;
  cin>>number;
  if(number>a[9])
    a[10]=number;
  else
  {
     for(i=0;i<10;i++)//从左往右扫描 
     {
       if(a[i]>number)//找到比插入数大的数时 
       {
         temp1=a[i];//将该数先暂存到temp1 
         a[i]=number;//把插入数插入 
         for(j=i+1;j<11;j++)//后面的数依次后移 
         {
           temp2=a[j];//后移时，先将a[j]的值暂存到temp2 
           a[j]=temp1;//插入数 
           temp1=temp2;//temp2的值存到temp1,继续下一次的插入 
         }    
         break;
        }
     }
  }
  for(i=0;i<11;i++)
    cout<<a[i]<<' ';    
  return 0;      
}           
