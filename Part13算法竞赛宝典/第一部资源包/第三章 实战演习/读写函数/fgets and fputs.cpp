//fgets and fputs
#include <iostream>
using namespace std;

int main()
{
  FILE *in=fopen("i.txt","rb");//定义文件读入指针
  FILE *out=fopen("o.txt","wb");//定义文件输出指针
  char a[9];
  fgets(a,10,in);
  fputs(a,out);
}
