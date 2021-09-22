//剔除多余括号-非二分法 
#include <iostream>
#include <cstdio>
#include <cstring>

using namespace std;

char v;
const int MAXN=10010;
char s[MAXN];
bool vis[MAXN];

int l,k,x,a,b,z,s1,s2,s3,y;
int Right;

void op(int k)//判断的比配括号中的最低级的运输符 
{
  for(int i=k;i<=Right;i++)
  {
    v=s[i];
    if(v == '-' || v == '+')
      return;
  }
}

void FirstLeft()//从右向左查找到第一个左括号 
{
  for(int j=l-1;j>=0;j--)
    if(s[j] == '(')
    {
      s2=j;
      break;
    }
}

int FindRight(int Left)//寻找一个括号的匹配括号 
{
  int i;
  int ll;
  int count=1;
  for(i=Left+1;i<l;++i) 
  {
    if(i==l-1)
      ll=i; 
    if(!count)//找到了匹配的右括号，则退出 
    {
      ll=i-1;
      break;
    }
    else
      if(s[i] == '(')
        count++;
      else if(s[i] == ')')
        count--;
  }
  return ll;
}

void desion()//判断最内层括号是否可删除 
{
  for(int i=s2;i>=0;i--)//从最中间的括号开始向左边 
  {
     if(s[i]=='(')
     {
       Right=FindRight(i);//找到相匹配的右括号 
       op(i);//找到最低级的运算符        
       if(i==0)//特判如果第一个字符串就为括号  
       {
         vis[i]=true;//左括号可以删除 
         vis[Right]=true;//右括号可以删除 
       }
       if(s[i-1]=='/')//若左括号前为/，则不可删除 
         continue;
       else if(s[i-1]=='*' || s[i-1]=='-' && v=='+' || v=='-')
         continue;//若左括号前为*,-，且括号内最低运算符为+,-则不可删除 
       else if(s[Right+1]=='/' || s[Right+1]=='*' && v=='+' || v=='-')
         continue;//若右括号为/或*,且括号内最低运算符为+,- 则不可删除 
       else//将匹配的括号bool改为真 
       {
         vis[i]=true;
         vis[Right]=true;
       }
     }
  }      
}

int main()
{
  freopen("bracket.in","r",stdin);
  freopen("bracket.out","w",stdout);  
  scanf("%s",s);
  l=strlen(s);
  FirstLeft();
  desion();
  for(int i=0;i<l;i++)
    if(!vis[i])//如果不为真输出 
       printf("%c",s[i]); 
  cout<<endl;
  system("pause");
  return 0;    
}
