//翻硬币2
#include<iostream>
#define maxx(a,b) (a>b?a:b)
using namespace std;

int n,m,ans,sum;
int num[1024];
int hun[50000]; //每行状态 
bool a;

int main()
{
    cin>>n>>m;
    for(int i=0;i<(1<<m);++i)
        for(int j=i;j>0;j>>=1)
            num[i]+=j&1;    //算出1到1023二进制1个数 
    for(int i=0;i<n;++i)
        for(int j=0;j<m;++j)
        {
            cin>>a;
            hun[i]=(hun[i]<<1)+a;
        }
    for(int i=0;i<(1<<m);++i)//枚举列 
    {
        for(int j=0;j<n;++j)//改变状态 
            hun[j]^=i;
        sum=0;
        for(int j=0;j<n;++j)//计算最大值 
            sum+=maxx(num[hun[j]],num[hun[j]]^((1<<m)-1));
        ans=maxx(ans,sum);
        for(int j=0;j<n;++j)//状态改回 
            hun[j]^=i;
    }
    cout<<ans<<"\n";
    return 0;
}
