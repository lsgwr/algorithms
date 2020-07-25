//简单高精度乘法 
#include<iostream>
#define MAXN 5001
using namespace std;

char str1[MAXN],str2[MAXN];
int a[MAXN],b[MAXN];

void multiply(int a[],int b[],int la,int lb) 
{
  int c[MAXN]={0},len,i,j,k,MaxLen=la+lb;
  for(i=0;i<lb;++i)       //第一个乘数
  { 
    for(j=la-1;j>=0;--j) //第二个乘数
      c[j+i]+=b[i]*a[j]; //注意乘积保存的位置 
      
    for(k=0;k<MaxLen;++k)//如某位数>=10，则需进行整理，此处还可优化 
      if(c[k]>=10)
      {
         c[k+1]+=c[k]/10;
         c[k]%=10; 
      }       
  } 
   
  for(i=k;c[i]==0;--i);//查找最高位数，注意此处有个分号 
  len=i+1;//此句与for循环无关 

  for(i=len-1;i>=0;--i) //输出结果 
    cout<<c[i]; 
  cout<<'\n';       
}

void init(int x[],char str[],int len)//字符串转为整型数组 
{
  for(int i=0;i<len;i++)
    x[len-i-1]=str[i]-'0';
}

int main()
{
  freopen("mul.in","r",stdin);
  freopen("mul.out","w",stdout);     
  int la,lb;
  std::ios::sync_with_stdio(false);//取消cin与stdin的同步以加快读取速度 
  cin>>str1>>str2;
  la=strlen(str1);
  lb=strlen(str2);
  if((la==1 && str1[0]=='0')||(lb==1 && str2[0]=='0')) 
    cout<<"0\n";
  else
  {  
    init(a,str1,la);//初始化为整型数组 
    init(b,str2,lb);
    multiply(a,b,la,lb);
  }
  return 0;
}
