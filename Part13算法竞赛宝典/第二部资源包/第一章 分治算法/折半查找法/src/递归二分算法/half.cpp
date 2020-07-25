//递归的折半查找法
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#define Max 10001
using namespace std;
int a[Max],key; 

int search(int bot,int top)
{
  int mid;
  if(top>=bot)
  {
    mid=(top+bot)/2;//取中间值mid 
    if(key==a[mid]) //如果相等,则打印该数 
    {
      cout<<mid<<endl;
      return 0;
    }   
    else if(key<a[mid])  //如x小于中间值, 则取前半段 
      search(bot,mid-1);
    else               //如x大于中间值,则取后半段 
      search(mid+1,top);
  }      
  else 
  {
      printf("-1\n");
      return 0;
  }    
}

int main()
{
   freopen("half.in","r",stdin);
   freopen("half.out","w",stdout); 
   int n; 
   cin>>n;
   for(int i=1;i<=n;++i)
     cin>>a[i]; 
   cin>>key;
   search(1,n);
   return 0;
}
