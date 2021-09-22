//πÈ≤¢≈≈–Ú
#include <iostream>
#include <cstdio>
#include <cstdlib>
using namespace std;
int a[100000];
int b[100000];

void Mergesort(int L,int R)
{
  int i,j,k;
  if(L>=R)
    return;
  int mid=(L+R)/2;
  Mergesort(L,mid);
  Mergesort(mid+1,R);
  for(i=L;i<=R;i++)
    b[i]=a[i];
  i=L,j=mid+1;
  for(k=L;k<=R;k++)
  {
    if(i<=mid &&((j>R)||b[i]<b[j]))
      a[k]=b[i++];
    else
      a[k]=b[j++];
  }
}

int main()
{
  freopen("sort.in","r",stdin);  
  freopen("sort.out","w",stdout);  
  int i,j,n;
  scanf("%d",&n);
  for(i=0;i<n;++i)
    scanf("%d",&a[i]);
  Mergesort(0,n-1);
  for(i=0;i<n;++i)
    printf("%d ",a[i]);
  return 0;
}
