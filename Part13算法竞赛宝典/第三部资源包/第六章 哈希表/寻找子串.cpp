//寻找子串 
#include <bits/stdc++.h>
using namespace std;
#define ull unsigned long long
const ull P1=1000000007,P2=1000000007;
char a[100001],b[100001*100];

int Hash()
{
  int len1=strlen(a);
  int len2=strlen(b);
  if(len1>len2) return -1;
  ull Pow1=1,Pow2=1,ah1=0,ah2=0,bh1=0,bh2=0;
  for(int i=0; i<len1; i++)//计算子串哈希
  {
    Pow1*=P1;//计算出p^(R-L+1)的值
    Pow2*=P2;
    ah1=ah1*P1+a[i];
    ah2=ah2*P2+a[i];
    bh1=bh1*P1+b[i];
    bh2=bh2*P2+b[i];
  }
  int ans=0;
  for(int i=0; i+len1<=len2; i++)
  {
    if(ah1==bh1 && ah2==bh2) ans++;
    if(i+len1<len2)
      bh1=bh1*P1+b[i+len1]-b[i]*Pow1;
    if(i+len1<len2)
      bh2=bh2*P2+b[i+len1]-b[i]*Pow2;
  }
  return ans;
}

int main()
{
  int t;
  scanf("%d",&t);
  getchar();
  while(t--)
  {
    gets(a);
    gets(b);
    cout<<Hash()<<endl;
  }
  return 0;
}

