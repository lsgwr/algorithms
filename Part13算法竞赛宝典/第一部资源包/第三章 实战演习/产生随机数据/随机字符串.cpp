//产生指定长度的随机字符串 
#include <iostream>
using namespace std;

int main()
{
  int i,n,m;//输出n行m个字符的随机字符串
  string str;
  cin>>n>>m; 
  srand((unsigned)time(NULL));
  for(i=1;i<=n;i++)
  {
    str="";//清空字符串
    for(int j=1;j<=m;j++)
    {
      int temp=rand()%2;//随机决定输出大写或小写字母
	  if (temp==0)		
        str+=(char)(rand()%(26)+1+64);//'A'=65,'Z'=90
      else
	    str+=(char)(rand()%(26)+1+96);//'a'=97,'z'=122
    }
    cout<<str<<endl;
  }	
  system("pause");
  return 0;
}
