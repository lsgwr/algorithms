//Ä§·¨·½Õó
#include <iostream>
using namespace std;
int a[10];

void cut(int x,int k)
{  int a1,a2,a3;
   if(k==1)
     a1=1,a2=2,a3=3;
   else if(k==2)
     a1=4,a2=5,a3=6;
   else
     a1=7,a2=8,a3=9;            
    a[a1]=x/100;
    a[a2]=x/10%10;
    a[a3]=x%100%10; 
}

void out()
{
  int i;   
  cout<<a[1]<<" "<<a[2]<<" "<<a[3]<<endl;
  cout<<a[4]<<" "<<a[5]<<" "<<a[6]<<endl;
  cout<<a[7]<<" "<<a[8]<<" "<<a[9]<<endl;  
  cout<<endl; 
}

int ok()
{
  int i, sum1=0,sum2=1;
  for(i=1;i<=9;i++)
  {
    sum1=sum1+a[i];
    sum2=sum2*a[i];
  }      
  if(sum1==45 && sum2==362880)
    return 1;
  else
    return 0;       
}

int main()
{
  int x,y,z,i;  
  for(i=123;i<=327;i++)
  {
    x=i;
    y=2*i;
    z=3*i;
    cut(x,1);
    cut(y,2);
    cut(z,3);
    if(ok()==1)
      out();                             
  }  
  getchar();  
}
