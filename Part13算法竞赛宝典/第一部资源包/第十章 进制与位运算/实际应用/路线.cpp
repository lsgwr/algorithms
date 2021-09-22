//Â·Ïß 
#include<iostream>
#include<string>
#include<iomanip>
using namespace std;

int i,j,t,a;
int len,r,r0,r1;
string b,jg;

int main()
{
  t=0;
  for(i=7;i<=112;++i)
  {
    a=i,r0=0,r1=0,jg="";
    do
    {
      r=a%2;
      if(r==1) 
        r1++,b='1';
      else 
        r0++,b='0';
      jg=b+jg;
      a=a/2;
    }while(a!=0);
        
    len=jg.size();
    if(len<7)
      for(j=1;j<=7-len;++j)jg='0'+jg;
        if(r1==3 && r0+7-len==4)
        {
          t=t+1;
          cout<<jg;
          for(j=8;j<=10;++j)
            cout<<' ';
        }
  }
  cout<<"\n";
  cout<<"t="<<t<<"\n";
  getchar();
  getchar();
  return 0;
}
