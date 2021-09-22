#include <iostream>
using namespace std;

int main()
{
  char ch[2][5]={"6937","8254"},*p[2];
  int i,j,s=0;
  for(i=0;i<2;i++)
    p[i]=ch[i];
  for(i=0;i<2;i++)
    for(j=0;p[i][j]>'\0';j+=2)
      s=10*s+p[i][j]-'0';
  cout<<s<<endl;      
  getchar();     
}
