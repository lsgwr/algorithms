//fin.get()使用 
#include <iostream>
#include<fstream>
using namespace std;
int main()
{  
  int a,b,c,space;
  ifstream fin("in.txt");  //输入形式：a b c
  ofstream fout("out.txt");  //输出三个个位数的积
  a=fin.get()-'0'; //特别注意该函数是获取一个字符包括空格符
  space=fin.get();
  b=fin.get()-'0';
  space=fin.get();
  c=fin.get()-'0';
  fout<<a*b*c;
  fin.close();
  fout.close();
  return 0;
}
