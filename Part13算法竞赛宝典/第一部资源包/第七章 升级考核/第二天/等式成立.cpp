//等式成立 
#include <iostream>
#include <cstdio>
#include <cstdlib>
using namespace std;
int date[9]={0};
int N,T,P,L,I,J,M,K;

inline int compare()
{
  if (++date[N]>1) 
    return 0;
  if (++date[T]>1) 
    return 0;
  if (++date[P]>1) 
    return 0;
  if (++date[L]>1) 
    return 0;
  if (++date[I]>1) 
    return 0;
  if (++date[J]>1) 
    return 0;
  if (++date[M]>1) 
    return 0;
  if (++date[K]>1) 
    return 0;
  return 1;
}

int main()
{
  for (N=2;N<=4;N++)
    for (T=2;T<=4;T++)
      if (T!=N &&(P=T*N)<=8)
        for (L=2;L<=4;L++)
          if ((I=N*L)<=8)
            for (K=1;I>K && P>K;K++)
            {
              J=I-K;
              M=P-K;
              if (compare())
              {
                cout<<I<<"-"<<J<<"="<<K<<endl;
                cout<<K<<"+"<<M<<"="<<P<<endl;
                cout<<N<<"*"<<T<<"="<<P<<endl;
                cout<<I<<"/"<<L<<"="<<N<<endl;
                //cout<<clock()<<"ms"<<endl;
              }
              else
              {
                for (int i=1;i<=8;i++) 
                  date[i]=0;
              }
            }
    system("pause");
    return 0;
}
