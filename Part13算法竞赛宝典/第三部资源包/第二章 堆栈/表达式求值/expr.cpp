//表达式求值
#include<bits/stdc++.h>
using namespace std;
const int MOD=10000;

int Stack[100010];

int main()
{
  freopen("expr.in","r",stdin);
  freopen("expr.out","w",stdout);
  int Top=0,num,Ans=0;
  char ch;
  cin>>Stack[0];
  while(cin>>ch)
  {
    cin>>num;
    if(ch=='*')
      Stack[Top]=(Stack[Top]*num)%MOD;
    if(ch=='+')
      Stack[++Top]=num;
  }
  for(int i=0; i<=Top; ++i)
    Ans=(Ans+Stack[i])%MOD;
  cout<<Ans<<endl;
  return 0;
}

