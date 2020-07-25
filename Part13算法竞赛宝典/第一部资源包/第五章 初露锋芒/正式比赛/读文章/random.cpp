#include <iostream>
using namespace std;
#define random(num)     (rand() % (num))
#define randomize()     srand((unsigned)time(NULL))

int main()
{
  freopen("article12.in","w",stdout);
  int i,n,m;//输出n行m个字符的随机字符串
  string str;
  //cin>>n>>m; 
  randomize();
  for(i=1;i<=3;i++)
  {
    str="";//清空字符串
    for(int j=1;j<=80;j++)
    {
      int temp=random(5);//随机决定输出大写或小写字母
	  if (temp==0)		
        str+=(char)(random(26)+1+64);//'A'=65,'Z'=90
      else if(temp==1)
	    str+=(char)(random(26)+1+96);//'a'=97,'z'=122
      else if(temp==2)
        str+=' ';
      else if(temp==3)
        str+='*'; 
      else if(temp==4)
        str+=(char)(random(10)+48);   
    }
    cout<<str<<endl;
  }	
 // system("pause");
}

