//ÓÅ»¯µİ¹éËã·¨ 
#include <cstdlib>
#include <iostream>
using namespace std;

int z(int a,int b)
{
  int s1,s2,s;  
  if(a==b)
     return 1;   
  else if(a<b)
    s1=a,s2=b;      
  else
   s1=b,s2=a;    
  
  if (s2%s1==0)
    return s2/s1;
  else
  {
    s=s2%s1;  
    return z(s1,s)+s2/s1;
  }
}

int main()
{
  freopen("territory.in","r",stdin);
  freopen("territory.out","w",stdout);
  int x,y;
  scanf("%d %d",&x,&y);
  printf("%d\n",z(x,y)); 
  return 0;  
}
