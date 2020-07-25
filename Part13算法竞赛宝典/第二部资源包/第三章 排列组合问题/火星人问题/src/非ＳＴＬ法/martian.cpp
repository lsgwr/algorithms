//火星人问题-方法２ 
#include <iostream>
using namespace std;
int b[10000+1]; 
int i,j,k,m,n,t;

void init()
{
  freopen("martian.in","r",stdin);
  freopen("martian.out","w",stdout);
  cin>>n>>m;
  for(i=1;i<=n;i++)
    cin>>b[i];
}

void out()
{
  for(i=1;i<=n-1;i++)
    cout<<b[i]<<' ';
  cout<<b[n]<<endl;
}

void work()
{
  for(i=1;i<=m;i++)//增1 共M次 
  {
     for(j=n-1;j>=1;j--)//找到最后可增加的位，即定位 
       if(b[j]<b[j+1])//例如12354加1，则只有3可增加位数 
         break;
     for(k=n;k>=1;k--)//找到最小可增加的数字 
       if(b[k]>b[j])//例如12354加1，最小可增加的数字是4 
         break;
     
     t=b[j];//交换，把原排列增大 ，
     b[j]=b[k];//例如12354加1，即3和4交换，交换结果为12453 
     b[k]=t;  //但12453并不是最终结果，而是12435即要排序 
     
     j=j+1;//向后移一位 
     k=n;
     while((j<k))//把后面的逆序，相当于从小到大排序 
     {
       t=b[j];  
       b[j]=b[k];
       b[k]=t;
       j++;
       k--;
     }
  }
}

int main()
{
  init();
  work();
  out();
  return 0;
}
