//五分制 
#include <iostream>
using namespace std;

int main()
{
  int grade;//注意此处是整数 
  cin>>grade;
  switch(grade)  //比较grade值，grade值为一个字符
  {
    case 5:
      cout<<"优秀\n"; break; 
    case 4:
      cout<<"良\n";  break; 
    case 3:
      cout<<"及格\n";  break; 
    case 2:
      cout<<"不及格\n";  break; 
    default:cout<<"太糟糕了\n"; // 此处可以不加break语句
  }
  system("pause");
  return 0;  
}
