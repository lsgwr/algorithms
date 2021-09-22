//神族文字－ＳＴＬ版 
#include<iostream>
#include<string>
#include<map>
using namespace std;

int main(void)
{
  freopen("dictionary.in","r",stdin);
  freopen("dictionary.out","w",stdout);    
  char english[11],foreign[11];
  map<string,bool>appear;  //记录foreign与engliash的配对映射是否出现
  map<string,string>translate; //记录foreign到engliash的映射

  while(true)//输入字典 
  {
    char t;  //临时变量 
    if((t=getchar())=='\n')  //判定是否输入了空行
      break;
    else     //输入english
    {
      english[0]=t;
      int i=1;
      while(true)
      {
		t=getchar();
		if(t==' ')
		{
		  english[i]='\0';
		  break;
		}
		else
		  english[i++]=t;
      }
    }
    cin>>foreign;
    getchar();  //处理 输入foreign后的 回车符

    appear[foreign]=true;
    translate[foreign]=english;
  }

  char word[11];
  while(cin>>word)//开始翻译 
  {
    if(appear[word])
      cout<<translate[word]<<endl;
    else
      cout<<"eh"<<endl;
  }
  return 0;
}
