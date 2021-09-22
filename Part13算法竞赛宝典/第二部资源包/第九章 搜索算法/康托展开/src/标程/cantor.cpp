//康托展开 
#include<iostream>
using namespace std;

int a[1000];

long long Cantor(int a[], int n)
{
  long long s = 1;
  long long *p = new long long[n];//存储各个全排列的值，p[i]=i! 
  for (int i=0; i<n-1; i++)
  {
    s *= i + 1;
    p[i] = s;
  } 
  long long sum = 0;
  for (int i=0; i<n-1; i++)
  {
    s = 0;
    for (int j=i+1; j<n; j++)
    {
      if (a[i] > a[j])
        s++;
    }
    sum += s * p[n-2-i];
  }
  delete []p;
  return sum + 1;
}

int main()
{ 
  freopen("cantor.in","r",stdin);
  freopen("cantor.out","w",stdout);  
  int N;
  cin>>N;
  for (int i=0; i<N; i++)
    cin>>a[i];   
  cout<<Cantor(a,N)<<endl;
  return 0;
}


