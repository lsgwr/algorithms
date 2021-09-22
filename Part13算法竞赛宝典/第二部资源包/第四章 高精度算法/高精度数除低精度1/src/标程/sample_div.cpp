//高精度数除以低精度数1
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
using namespace std;
#define MAXN 5001

int main()
{
  freopen("sample_div.in","r",stdin);
  freopen("sample_div.out","w",stdout);
  int a[MAXN],c[MAXN];
  int x=0,b,i,j=1,len;
  string str;
  cin>>str>>b;
  len=str.size();
  for(i=1;i<=len;++i)
  {
    a[i]=str[-1+i]-'0';//先将字符转换为整数 
    c[i]=(x*10+a[i])/b;//模拟除法，注意x初始时为零 
    x=(x*10+a[i])%b;//余数乘以10后累计到x 
  }
  while((c[j]==0)&&(j<len))//消去前导无效的0 
    j++;
  for(i=j;i<=len;++i)
    cout<<c[i];
  cout<<'\n';
  return 0;
}
