//等级划分 
#include <iostream>
using namespace std;

int main()
{
  char grade;//注意此处是字符型 
  cin>>grade;
  switch(grade)  //比较grade值，grade值为一个字符
  {
    case 'A':
      cout<<"优秀\n"; break; 
    case 'B':
      cout<<"良\n";  break; 
    case 'C':
      cout<<"及格\n";  break; 
    case 'D':
      cout<<"不及格\n";  break; 
    default:cout<<"太糟糕了\n"; // 此处可以不加break语句
  }
  system("pause");
  return 0;  
}
