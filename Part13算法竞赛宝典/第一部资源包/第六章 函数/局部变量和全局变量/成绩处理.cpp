//成绩处理
# include <iostream>
using namespace std;

float Max=0,Min=0;//全局变量
float fun(float array[],int n)
{
  int i;
  float average,sum=array[0];
  Max=Min=array[0];    
  for(i=1;i<n;i++)
  {
    if(array[i]>Max) 
        Max=array[i];
    else if(array[i]<Min)
        Min=array[i];
    sum=sum+array[i];              
  }
  average=sum/n;
  return average;
}

int main()
{
  float ave;
  float score[]={80.2,45.6,78,43,90.5,88.5,76,65,91,54};
  ave=fun(score,10);
  cout<<"最高分为"<<Max<<"最低分为"<<Min<<"平均分为"<<ave;
  system("pause");
  return 0;
}
