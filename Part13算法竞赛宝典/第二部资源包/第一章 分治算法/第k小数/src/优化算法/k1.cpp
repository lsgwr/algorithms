//第K小数 方法二
#include <iostream>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include <algorithm>
using namespace std;
int a[100001],b[100001];
int i,j,m,k,l;

int cmp(int x,int y)
{
  return x<y;
}

void Swap()
{
  swap(a[i],a[j]);
  swap(i,j);
}

void Operation(int START,int END)
{
  i=START;
  j=END;
  while(i!=j)
  {
 	if(i<j)
    {
	  if(a[i]>a[j])
	    Swap();
  	  else
  	    j--;
    }
    else
    {
   	  if(a[i]<a[j])
   	    Swap();
   	  else
   	    j++;
    }
  }
  if(i<k)
    Operation(i+1,END);
  else if(i==k)
  {
    for(l=1;l<=m;l++)
      if(b[l]==a[i])
      {
        cout<<l<<"\n";
        break;								   
      }
  }
  else
    Operation(START,i-1);
}

int main()
{
  freopen("k1.in","r",stdin);
  freopen("k1.out","w",stdout);      
  scanf("%d%d",&m,&k);
  for(i=1;i<=m;i++)
  {
    scanf("%d",&a[i]);
    b[i]=a[i];
  }
  for(i=6;i<=m+1;i=i+5)
    sort(a+i-5,a+i,cmp);
  for(i=1;i<=m/5;i++)
    swap(a[i],a[i*5-2]);
  sort(a+1,a+i,cmp);
  swap(a[1],a[i/2]);
  Operation(1,m);
  return 0;
}
