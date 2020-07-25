//剔除多余括号-二分法 
#include <iostream>
#include <cstdio>
#include <cstdlib>
#include <cstring>
using namespace std;

int a[1024];//a,s两数组元素一一对应，a[i]标记s[i]是否多余 
char s[1024];

int cal(int begin, int end, int prev_min)
{
  int t, min=4, min_i;
  for (int i=begin; i<=end; i++)//扫描整个字符串,找出最低运算符 
  {
    switch (s[i])
    {
      case '^':
		if (min>3) 
          min=3, min_i=i;//标记"^"运算符优先级为3 
        break;
      case '*': 
      case '/':
		if (min>2) 
          min=2, min_i=i;//标记"*"和"/"运算符优先级为2 
		break;
      case '+': 
      case '-':
	    if (min>1) 
          min=1, min_i=i;//标记"+"和"-"运算符优先级为1 
		break;
      case '('://遇到左括号，则找到与之匹配的右括号，并跳过括号内的字符 
		i++;
		for (t=1;t!=0;i++)
		{
		  if (s[i]=='(') //对括号内多重左括号和右括号的处理 
            t++;
		  if (s[i]==')') 
            t--;
		}
		i--;
		break;
    };
  }

  if (min==4)//去括号操作 
  {
     if (s[begin]=='(' && s[end]==')')//如果首尾即为括号 
     {
        t=cal(begin+1,end-1,0);//求出除去首尾括号继续递归的返回值min 
        if (t>=prev_min)
        {
          a[begin]=a[end]=1;//将首尾的括号标记为多余 
          return t;
        }
     }
     return 4;
  }

  cal(begin,min_i-1,min);//递归最低运算符前端的式子 
  if (s[min_i]=='+' || s[min_i]=='*')
    cal(min_i+1,end,min);//递归最低运算符后端的式子 
  else
    cal(min_i+1,end,min+1);//递归最低去处符后端的式子，但运算优先级+1 
  return min;
}

int main()
{
  freopen("bracket.in","r",stdin);
  freopen("bracket.out","w",stdout);  
  cin>>s;
  int sc=strlen(s);
  cal(0,sc-1,0);
  for (int i=0;i<sc;i++) 
    if (!a[i])//当前位标记为0，则输出 
      cout<<s[i];   
  cout<<'\n';
  return 0;
}
