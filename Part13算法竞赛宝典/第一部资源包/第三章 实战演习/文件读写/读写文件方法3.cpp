//读写文件方法3 
#include <iostream>
#include<fstream>
using namespace std;
int main()
{
  int a,b,c;  
  ifstream fin("in.txt");
  ofstream fout("out.txt");
  fin>>a>>b>>c;
  fout<<a*b*c<<endl;//注意cin与cout的变化
  fin.close();
  fout.close();
  return 0;
}
