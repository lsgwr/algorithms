//极值问题 
#include<iostream>
using namespace std;
int k,i,j,m,ml,mll,temp;

int main()
{
 freopen("mn.in","r",stdin);
 freopen("mn.out","w",stdout);
 cin>>k;
 mll=1;
 ml=1;
 while (mll+ml<k)
  {
   m=ml+mll;
   temp=ml;
   ml=m;
   mll=temp;
   //cout<<m<<' '<<ml<<' '<<mll<<endl;
  }
 cout<<mll<<' '<<ml;
 return 0;
}
