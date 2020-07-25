//N进制小数转换为十进制
#include <iostream>
using namespace std;

double x,t,j;
double fun(double n,string s)
{
 int i;

    for(i=s.size();i>=1;i--)
     {
    if(s[i]>='0' && s[i]<='9')
      t=t/n+s[i]-48;
     else if(s[i]>='A' && s[i]<='F')
      t=t/n+s[i]-55;
     else if(s[i]>='a' && s[i]<='f')
      t=t/n+s[i]-97;
      j=t/n;
     } 
      return j;    
}

int main()
{
 int n;
 string s;
 cin>>n;
 cin>>s;
 cout<<fun(n,s);
 system("pause");    
}
