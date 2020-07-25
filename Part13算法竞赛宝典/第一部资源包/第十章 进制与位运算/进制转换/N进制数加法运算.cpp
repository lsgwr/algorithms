//N进制数加法运算
#include <iostream>
#include <string>
using namespace std;

int main()
{
  string a,b,w;
  int x[100],y[100];
  int i,k,N;
  cin>>N;
  for(i=0;i<=N-1;i++)
  {// 
    if(i<10)
     w=w+char(i+48);//数字的处理 
    else
     w=w+char(55+i);//字母的处理                    
  }
  //cout<<w<<endl;//输出0123...ABC...序列 
  cin>>a>>b;
  while(a.length()<b.length())
    a='0'+a;
  while(b.length()<a.length())
    b='0'+b;
  a='0'+a;//前面多加一位，用于进位    
  b='0'+b;
  //cout<<a<<endl<<b<<endl<<endl;//检查输出是否正确 
  for(i=a.length()-1;i>=0;i--)
  {  
    x[i]=w.find(a[i],0);//查找a[i]在W中的位置获得真实的数字 
    y[i]=w.find(b[i],0);//转换到x,y数组中准备相加 
  } 

  for(i=a.length()-1;i>=0;i--)//进位加法 
  {     
    x[i]=x[i]+y[i];
    if(x[i]>=N)
    {
      k=i;         
      while(x[k]>=N)
      {
        x[k]=x[k]-N;
        x[k-1]++;
        k--;
      }                           
    }  
  }  

  for(i=a.length()-1;i>=0;i--)
    a[i]=w[x[i]];//转换为N进制数，a即是加法又是总数 
  
  while(a[0]=='0')
     a.erase(0,1);  //删除前导0 
  cout<<a<<endl;  
  system("pause");  
}
