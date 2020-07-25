// 统计逆序对 
#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#define Max 1001
using namespace std;
int n,a[Max],temp[Max];//个数n,数字数组a,临时数组temp
int ans=0;//结果

void sort(int i,int j)//归并过程 
{
  if(i==j)
    return;
  int mid=(i+j)/2;
  sort(i,mid);
  sort(mid+1,j);
  int l,r,k;//l为左半部指针，r为右半部指针 
  for(l=i,r=mid+1,k=i;k<=j;k++)//k从i扫描到j 
  {
    if(l>mid)//如果左指针已到正中 
    {
      temp[k]=a[r];//加右半部数字 
      r++;
    }
    else if(r>j)//如果右指针已到末尾 
    {
      temp[k]=a[l];//加左半部数字 
      l++;
    }
    else if(a[l]<=a[r])//如果左数不大于右数 
    {
      temp[k]=a[l];//加左数 
      l++;
    }
    else//如果右数小于左数 
    {
      temp[k]=a[r];//加右数 
      r++;
      ans+=mid-l+1;//统计逆序对数
    }
  }
  for(k=i;k<=j;k++)
    a[k]=temp[k];//复制数组temp到a
}

int main()
{
  freopen("reverse.in","r",stdin);
  freopen("reverse.out","w",stdout);
  scanf("%d",&n);
  for(int i=0;i<n;i++)
    scanf("%d",&a[i]);
  sort(0,n-1);
  printf("%d\n",ans);
  return 0;
}
