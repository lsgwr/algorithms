//输入若干个姓名，在其中查找
#include <iostream>
#include <cstdlib>
#include <string.h>
using namespace std;
int main()
{
    freopen("name.in","r",stdin);
    freopen("name.out","w",stdout);
    int num,i,j;
    cin>>num;
    getchar();//除去第一行的回车 
    char name[num+1][50];
    for(i=0;i<=num;++i)
      gets(name[i]);//注意查找的姓名存在name[num]中 
    for(i=0;i<=num;++i)
      if(strcmp(name[i],name[num])==0)
        break;
    if(i==num)
      cout<<-1<<endl;
    else
      cout<<i+1<<endl;
    return 0;
}
