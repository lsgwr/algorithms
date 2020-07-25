//理想收入问题－优化算法２ 
#include <fstream>
#include <iostream>
#include <iomanip>
using namespace std;
#define N 100001
double v[N];
double p[N]; 

double MAX(double a,double b)
{
  return a>b?a:b;             
}

int main()
{
  ifstream fin("stock.in");
  ofstream fout("stock.out");
  int i,j,k,n;
  fin>>n;
  for(i=1;i<=n;i++)
    fin>>v[i];
  p[1]=1.0/v[1]; 
  
  for(i=1;i<=n;i++)
    for(j=1;j<i;j++)      
       p[i]=MAX(p[i-1],p[j]*v[j]/v[i]);  
 
  fout<<setprecision(4)<<fixed<<p[n]*v[n]<<'\n'; 
  fin.close();
  fout.close();
  return 0; 
}
