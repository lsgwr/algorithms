//基数排序－数组法 
#include <stdio.h>
#include <stdlib.h> 
#include <math.h>
#include <cstring>
#include <iostream>
#define MAXN 100001
using namespace std;
 
int N[10],n; 
int array[MAXN];//存放数序 
int bucket[10][MAXN];//桶 

int maxbit(int IntMax)//求最大位数
{
  int digit=1;
  while(IntMax>0)
  {
    digit++;
    IntMax/=10;
  }
  return digit-1;
}
   
void RadixSort(int *array,int Exp)//基数排序 
{ 
  int Where,m=0; 
  for(int i=0;i<10;i++)//将桶计数器清空 
    N[i]=0; 
  for(int i=0;i<n;i++) //分配各数入桶
  {  
    Where=array[i]/(int(pow(10,Exp)+0.001))%10;//取得该位的值并确定属于哪个桶  //加上0.001 
    bucket[Where][N[Where]++]=array[i];//依次往后排 
  }
  for(int i=0;i<10;i++) //顺序收集桶中的数到array[] 
    for(int j=0;j<N[i];j++) 
      array[m++]=bucket[i][j];
} 
 
int main() 
{ 
  freopen("sort.in","r",stdin);
  freopen("sort.out","w",stdout);
  int IntMax=0,len;
  cin>>n; 
  for(int i=0;i<n;i++) 
  {
    cin>>array[i];
    if(array[i]>IntMax)//找出最大数 
      IntMax=array[i];
  }
  len=maxbit(IntMax);//算出最大数的位长 
  for(int i=0;i<len;++i)//依次对每一位进行基数排序 
    RadixSort(array,i); 
  for(int i=0;i<n;i++)//array[M]的元素最大，依次逆向减小  
    printf("%d ",array[i]); 
  return 0;
}  
