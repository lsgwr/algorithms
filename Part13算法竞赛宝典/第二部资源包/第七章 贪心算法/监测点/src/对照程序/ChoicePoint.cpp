#include <stdio.h>
#include <algorithm>
using namespace std;
struct Extent
{
  int a,b;
  bool operator < (const Extent& S)const
  {
    return b < S.b || b == S.b && a > S.a;
  }
}A[10002];

int main()
{
    freopen("ChoicePoint.in","r",stdin);
    freopen("ChoicePoint.out","w",stdout);
    int z,n,cnt,end;
    scanf("%d",&z);
    while(z--)
    {
        cnt = 0;
        end = -1;
        scanf("%d",&n);
        for(int i=0;i<n;i++)
            scanf("%d%d",&A[i].a,&A[i].b);
        sort(A,A+n);
        for(int i=0;i<n;i++)
        {
            if(end < A[i].a)
            {
                end = A[i].b;
                cnt++;
            }
        }
        printf("%d\n",cnt);
    }
	return 0;
}
