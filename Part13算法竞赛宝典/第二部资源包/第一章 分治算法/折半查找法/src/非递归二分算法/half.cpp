//非递归的折半查找法
#include <iostream>
#include <cstdlib>
#define MAXN 10001
using namespace std;
int key,top,bot,mid,n,a[MAXN];

void half()//二分查找法 
{
  top=1;
  bot=n;
  while (top<=bot)
  {
    mid=(bot+top)/2;
    if (key==a[mid])//如果正好找到 
    {
      cout<<mid<<endl;
      exit(0);
    }
    else if (key<a[mid])//选择右半段 
      bot=mid-1;
    else               //选择左半段 
      top=mid+1;
  }
  cout<<-1<<endl;
}

int main () 
{
  freopen("half.in","r",stdin);
  freopen("half.out","w",stdout);  
  cin>>n;
  for(int i=1;i<=n;i++)
    cin>>a[i]; 
  cin>>key;
  if (key<a[1] || key>a[n])
    cout<<-1<<endl;
  else  
    half();
  return 0;
}
