//理想收入问题－优化算法３ 
#include <fstream>
#include <iostream>
#include <iomanip>
using namespace std;
#define N 100001
double q[N];
double v[N];

double MAX(double a,double b)
{
  return a>b?a:b;
}

int main()
{
  ifstream fin("stock.in");
  ofstream fout("stock.out");
  int i,j,n;
  fin>>n;
  for(i=1;i<=n;i++)
    fin>>v[i];
  q[1]=1;  
  for(i=1;i<=n;i++)
    for(j=1;j<i;j++)
      q[i]=MAX(q[i-1],q[j]/v[j]*v[i]);
  fout<<setprecision(4)<<fixed<<q[n]<<'\n';
  fin.close();
  fout.close();
  return 0;
}
