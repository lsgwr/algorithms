#include<iostream>
#include<cstring>
using namespace std;
string a[201];
bool f[200001];
string str;
int n,len;

bool IsChar(char c)//判断是否字符 
{
  if (c>='A' && c<='Z') 
    return true;
  return false;
}
bool check(int p)//查找集合中匹配的串 
{
  int t;
  for (int i=1;i<=n;i++)
  {
    t=a[i].length();
    if (p>=t)
    {
      if (a[i]==str.substr(p-t+1,t))
        if (f[p-t]) 
          return true;
    }
  }
  return false;
}

int main()
{
  freopen("Prefix.in","r",stdin);
  freopen("Prefix.out","w",stdout);
  string s; n=0;
  while (cin>>s,s!=".")//读入Ｐ集合 
  {
    n++; 
    a[n]=s;
  }
  int x1,x2;
  str=' '; len=0;
  while (cin>>s)//读入字符串的特殊处理 
  {
    x1=0;x2=s.length()-1;
    while (!IsChar(s[x1])) 
      x1++;
    while (!IsChar(s[x2])) 
      x2--;
    str+=s.substr(x1,x2-x1+1); 
    len+=x2-x1+1;
  }
  memset(f,false,sizeof(f));
  f[0]=true;
  for (int i=1;i<=len;i++)
    if (check(i)) 
      f[i]=true;
    else 
      f[i]=false;
  for (int i=len;i>=0;i--)
    if (f[i])
    {
      cout<<i<<endl; 
      break;
    }    
  return 0;
}
