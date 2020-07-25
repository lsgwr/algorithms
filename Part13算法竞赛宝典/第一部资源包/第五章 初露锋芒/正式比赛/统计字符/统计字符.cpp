//统计出其中英文字母、空格、数字和其他字符的个数
# include <iostream>
#include <cstdlib>

using namespace std;

int main()
{
  freopen("letter.in","r",stdin);
  freopen("letter.out","w",stdout);
  char c;
  int letter=0,space=0,digit=0,other=0;
  while((c=getchar())!='\n')
  {
    if(c>='a' && c<='z' || c>='A'&& c<='Z')
      letter++;
    else if(c==' ')
      space++;
    else if(c>='0' && c<='9')
      digit++;
    else	
      other++;
  }
  cout<<letter<<endl;
  cout<<space<<endl;
  cout<<digit<<endl;
  cout<<other<<endl;
  return 0;
}
