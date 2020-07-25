//四塔问题-数学归纳
#include <iostream>
using namespace std;
int ans[50001],i,d,t,r;

int main()
{
  freopen("hanoi45.in","r",stdin);
  freopen("hanoi45.ans","w",stdout);    
  ans[1]=1;d=2;t=2;r=2;
  for(i=2;i<=50000;i++)
  {
    ans[i]=(ans[i-1]+d)%10000;
    if(r>1)
      r--;
    else
    {
      d=(d<<1)%10000;
      t++;
      r=t;
    }  
  } 
  cin>>i;
  cout<<ans[i]<<endl;
  return 0;  
}
