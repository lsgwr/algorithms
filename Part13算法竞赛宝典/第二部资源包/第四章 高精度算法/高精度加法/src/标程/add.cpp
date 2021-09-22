//简单高精度加法 
#include <iostream>
#include <fstream>
#define MAXN 5001
using namespace std;
ifstream fin("add.in");
ofstream fout("add.out");

int add(int x[],int y[],int z[],int len)
{
  int i,j,r;
  for(j=0;j<len;++j)//从下标0开始逐位相加到len-1 
  { 
    z[j]+=x[j]+y[j];//相加结果存到z[] 
    for(i=j;i<len;++i)
    {
       if(z[i]>=10)//如果当前位数的值超过了10，则要进位处理 
       {	   
	      z[i+1]+=z[i]/10;//此处如改为++z[i+1]，效率更高 
	      z[i]-=10;       //或者是z[i]%=10,但速度稍慢 
          if(z[len]>0)//最高位如果进位了，则最大位数加1 
	        len++;
	      if(z[i+1]<10)//小优化 
	        break;
       }
    }
  }
  return len;//返回位数 
}

void init(int x[],string str, int len)//字符串转为整型数组 
{
  for(int i=0;i<len;i++) 
    x[len-i-1]=str[i]-'0';//倒序转换     
}

void output(int z[],int len)//输出相加的结果
{
  for(int i=len-1;i>=0;i--)  
    fout<<z[i];
  fout<<"\n";
}

int main()
{
  string str1,str2;
  int a[MAXN]={0},b[MAXN]={0},z[MAXN]={0};//初始化为0 
  int la,lb,len;
  fin>>str1>>str2;
  la=str1.size();
  lb=str2.size();
  init(a,str1,la);//初始化为整型数组 
  init(b,str2,lb);

  if(la>=lb)//确定a和b的最大位数
    len=add(a,b,z,la); //取最长位数la
  else
    len=add(b,a,z,lb);//取最长位数lb
  output(z,len);
  fin.close();
  fout.close();
  return 0;
}
