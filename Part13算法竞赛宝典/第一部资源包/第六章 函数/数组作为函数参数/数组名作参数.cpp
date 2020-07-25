//数组名作参数
# include <iostream>
using namespace std;

void change(int x[],int y[])
{
  int temp,i;
  for(i=0;i<8;i++)
  {
    temp=x[i]; x[i]=y[i];  y[i]=temp;  
  }  
}

int main()
{
  int a[]={1,2,3,4,5,6,7,8};
  int b[]={-1,-2,-3,-4,-5,-6,-7,-8};
  int i;
  change(a,b);//数组名a,b作参数 
  for(i=0;i<8;i++)
    cout<<a[i]<<b[i]<<endl; 
  //system("pause");
  return 0; 
}
