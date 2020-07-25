//√Ê ‘ 
#include <bits/stdc++.h>
using namespace std;
#define maxn 105
#define p 1000000009
#define mod 1000000007
#define num 20000
char ch[maxn];
int cnt[num];
long long Hash[maxn];
int idx[260];

void init_idx()
{
  for (int i='a'; i<='z'; ++i)
    idx[i]=i-'a'+1;
  for (int i='A'; i<='Z'; ++i)
    idx[i]=i-'A'+27;
  for (int i='0'; i<='9'; ++i)
    idx[i]=i-'0'+53;
}

int main()
{
  init_idx();
  for (int i = 0; i < num; ++i)
  {
    scanf("%s",ch);
    int n = strlen(ch);
    for (int j=1; j<=n;++j)
      Hash[j]=(Hash[j-1]*p+idx[ch[j-1]]) % mod;
    cnt[i]=Hash[n];
  }
  sort(cnt, cnt + num);
  printf("%d\n",unique(cnt,cnt+num)-cnt);
  return 0;
}

