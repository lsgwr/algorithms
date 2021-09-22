#include <iostream>
#include <cstdlib>
using namespace std;
int main()
{
    freopen("word.in","r",stdin);
    freopen("word.out","w",stdout);
    char str[100],c;
    int i,n=0;
    gets(str);
    for(i=0;(c=str[i])!='\0';i++)
    {
       if(str[i]!=' '&&str[i+1]==' ') n++;
       if(str[i]==' '&&str[i+1]=='\0') n--;
    }
    n++;
    cout<<n<<endl;
    return 0;
}
