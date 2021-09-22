//组合数的高精度算法1
#include <iostream>
#include <fstream>
using namespace std;
long long save[10000][10000];

long long  combin(int m,int n)
{ 
  long long temp; 
  if(n==0||m==n ||m==0)
    return 1; 
  if (save[m][n]>0)//优化，若已求出c(n,m),则停止深度递归，直接引用即可 
    return save[m][n];  
  temp=combin(m-1,n)+combin(m-1,n-1);
  save[m][n]=temp;
  return temp;
}

main()
{
  ifstream fin("combin.in");
  ofstream fout("combin.out");    
  int m,n,t;
  fin>>m>>n;
  m+=n-2;
  n-=1;
  if(m-n<n)//利用组合性质C(m,n)=C(m,m-n),此处无明显优化效果 
    fout<<combin(m,m-n)<<endl;  
  else  
    fout<<combin(m,n)<<endl;
  fin.close();
  fout.close();  
  return 0;  
}
