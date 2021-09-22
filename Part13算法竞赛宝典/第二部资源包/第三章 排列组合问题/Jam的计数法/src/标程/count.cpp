//jam的计数法 
#include <iostream>
#include <fstream>
#define cin fin
#define cout fout
using namespace std;
int i,p,q,s,t,w;
string a;

int main()
{
  ifstream fin("count.in");
  ofstream fout("count.out");
  cin>>s>>t>>w;
  cin>>a;
  for(p=0;p<5 && (a[0]-'a'<t-w); p++)//最多只输出５个 
  {
    for(q=w-1;q>=0 && (a[q]-'a'==t-w+q);q--);//第q位的最大值为 t-（w-q）
    if(q>=0)
    {
      a[q]++;
      for(i=q+1;i<w;i++)
        a[i]=a[i-1]+1;
      cout<<a<<endl;
    }
  }  
  return 0;
}
