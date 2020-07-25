//密码锁
#include <iostream>
using namespace std;

int main()
{
  char a[9];//存储八位的3进制数，a[0]为终止标记 
  int i,j,N,s;
  char fuhao[3]={'+','-','*'};
  bool f=0;//是否有解的标记 
  memset(a,0,sizeof(a));
  cin>>N;
  while(a[0]!=1)//当最高位为1时，则说明该三进制数均为3，则结束 
  {
    s=1;
    for(i=1;i<=8;i++)//代入八个运算符，运算符由a[i]决定 
    {
      if(a[i]==0)//为加号时 
        s+=i+1;
      else if(a[i]==1)//为减号时 
        s-=i+1;
      else if(a[i]==2)//为乘号时 
        s*=i+1;                     
    }          
    if(s==N)//如相等，则打印结果 
    {
      f=1;//标记为有解 
      cout<<1;
      for(i=1;i<=8;i++)
        cout<<fuhao[a[i]]<<i+1;                 
      cout<<'='<<N<<endl;        
    }  
    a[8]++;//最后一位数累加1
    j=8;
    while(a[j]==3)//循环进位 
    {
      a[j]=0;//本位设为零 
      a[j-1]++;//上一位加一 
      j--;     //指针指到上一位         
    } 
  }
  if(f==0)
    cout<<"No answer!";
  system("pause");  
}
