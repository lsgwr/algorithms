//µÚKÐ¡Êý 
#include <iostream>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include <algorithm>
using namespace std;

int a[100001],b[100001];
int i,j,m,k,l;

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
  Operation(1,m);
  return 0;
}
