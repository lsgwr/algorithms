//组合问题-组合公式法 
#include <stdio.h>
#include <stdlib.h>
#include <iostream>
using namespace std;

int C(int N,int M)
{
  if(M==N ||M==0)
    return 1;
  else
    return C(N-1,M)+C(N-1,M-1);      
}

int main()
{
  freopen("combination.in","r",stdin);
  freopen("combination.out","w",stdout);
  int M,N;
  scanf("%d",&N);
  scanf("%d",&M);
  printf("%d\n",C(N,M));        
  return 0; 
}
