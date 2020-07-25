//一次查找两元素 
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
using namespace std;
#define MAX 1000000
int Prisoner[MAX+1];

int main()
{
  freopen("Manhunt.in","r",stdin);
  freopen("Manhunt.out","w",stdout);  
  std::ios::sync_with_stdio(false);//取消cin与stdin的同步
  int N,m1,m2,temp;
  m1=m2=2147483647; 
  cin>>N;
  for(int i=1;i<=N;++i)
  {
     cin>>temp;             
     if(m2>=m1 && temp<m2)
	    m2=temp;
     else if(m2<m1 && temp<m1)
		m1=temp; 
  }
  if(m1<m2)
    printf("%d %d\n",m1,m2); 
  else    
    printf("%d %d\n",m2,m1); 
  return 0;
}
