#include<iostream>
#include<fstream>
 
using namespace std;
 
int dp[501][501];
int a[501],b[501];
int n,m;
 
void print(int s,int t){
    if(t==0||s==0) return;
    if(a[s]!=b[t])
        print(s,t-1);
    else
    {
        int minn=0;int j;
        for(int k=1;k<s;k++)
            if(a[k]<a[s]&&minn<dp[k][t-1])
            {
                minn=dp[k][t-1];
                j=k;
            }
        if(minn!=0)
            print(j,t-1);
        cout<<a[s]<<' ';
    }
}
 
 
void read(){
freopen("LCIS.in","r",stdin);
freopen("LCIS.out","w",stdout);
    int i,j,k;
    while(cin>>n)
    {
    for(i=1;i<=n;i++)
        cin>>a[i];
    cin>>m;
    for(j=1;j<=m;j++)
        cin>>b[j];
    memset(dp,0,sizeof(dp));
 
 
    for(i=1;i<=n;i++)
        for(j=1;j<=m;j++)
        {
            if(a[i]!=b[j])
                dp[i][j]=dp[i][j-1];
            else
            {
                int s=0;
                for(k=1;k<i;k++)
                    if(a[k]<a[i]&&s<dp[k][j-1])
                    {
                        s=dp[k][j-1];
                 
                    }
                dp[i][j]=s+1;
            }
        }
    int res=0;
    for(i=1;i<=n;i++)
    {
        if(res<dp[i][m])
        {
            res=dp[i][m];
            j=i;
        }
    }
    cout<<res<<endl;
    if(res!=0)
    {
        print(j,m);
         
    }
    cout<<endl;
    }
}
 
int main(){
    read();
    return 0;
}
