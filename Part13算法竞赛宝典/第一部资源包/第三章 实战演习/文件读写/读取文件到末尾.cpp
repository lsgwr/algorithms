//读取文件到末尾 
#include <iostream>
using namespace std;

int a[500000],i,n;
int main()
{
  freopen("in.txt","r",stdin); //从in.txt中读取数据
  freopen("out.txt","w",stdout);//输出到out.txt文件
  for(i=0;scanf("%d",&a[i])!=EOF;i++);//scanf为c语言读写方式，直到读到文件末尾
    n=i;
  for(i=0;i<=n;i++)
     cout<<a[i]<<' '; 
  return 0;    
}
