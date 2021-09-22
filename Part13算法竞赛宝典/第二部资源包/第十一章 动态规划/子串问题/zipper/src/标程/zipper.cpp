//zipper
#include <iostream>
#include <cstdlib>
#define M 201
using namespace std;

string s1,s2,s3;
int l1,l2,l3;
int a[M][M];

void dp(int data)
{
  int i,j,k,t1,t2;
  for(i=1;i<=l2;++i)//边界条件 
    a[0][i]=a[0][i-1]+(s2[i-1]==s3[i-1]);
  for(i=1;i<=l1;++i)//边界条件 
    a[i][0]=a[i-1][0]+(s1[i-1]==s3[i-1]);
  for(i=1;i<=l1;++i)
    for(j=1;j<=l2;++j)
      a[i][j]=max(a[i-1][j]+(s1[i-1]==s3[i+j-1]),
                        a[i][j-1]+(s2[j-1]==s3[i+j-1]));
  cout<<"Data set "<<data<<':';
  if(a[l1][l2]==l3) 
    cout<<"yes";
  else 
    cout<<"no";
  cout<<endl;
}

void init()
{
  cin>>s1>>s2>>s3;
  l1=s1.length();
  l2=s2.length();
  l3=s3.length();
}

int main()
{
  freopen("zipper.in","r",stdin);
  freopen("zipper.out","w",stdout);  
  int n;
  cin>>n;
  for(int i=1;i<=n;++i)
  {
    init();
    dp(i);
  }
  return 0;
}
