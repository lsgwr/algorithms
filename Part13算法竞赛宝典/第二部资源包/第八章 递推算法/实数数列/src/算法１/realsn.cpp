//实数数列算法1 
#include<iostream>
using namespace std;
int i,j,k,n,d,m;
long long p[100],q[100],r[100];  
double a[100];

int main()
{
 freopen("realsn.in","r",stdin);
 freopen("realsn.out","w",stdout);
 cin>>n>>d>>m>>a[1]>>a[n];
 p[1]=0;
 r[1]=1;
 q[1]=0;
 p[2]=1;
 r[2]=0;
 q[2]=0; //初始化 
 for (i=3;i<=n;i++)
  {
   p[i]=-2*p[i-1]+p[i-2];
   r[i]=-2*r[i-1]+r[i-2];
   q[i]=-2*q[i-1]+q[i-2]+2; //递推系数 
  }
 for (i=2;i<n;i++)
  {
   a[i]=(a[n]-r[n-i+2]*a[i-1]-q[n-i+2]*d)/p[n-i+2]; //递推每个元素 
  }
 cout<<int(a[m]); //输出强制转换 
}

 
