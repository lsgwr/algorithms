//fread读取文件2
#include <iostream>
using namespace std;
FILE *in=fopen("i.txt","rb");
int mark=0;
char a[10];

int getnum()//将char类型转换为int类型
{
    int obj=0;
    while(!(a[mark]>='0' && a[mark]<='9'))
      mark++;
    while(a[mark]>='0' && a[mark]<='9')
        obj=obj*10+a[mark++]-'0';
    return obj;
}

int main()
{
  freopen("p.txt","w",stdout); //文件输出
  int s=1,i;
  fread(a,6,1,in); //第二、三个参数依据数组大小而定，够用就好
  for(i=0;i<3;i++)
    s*=getnum();
  cout<<s<<endl;
  return 0;
}
