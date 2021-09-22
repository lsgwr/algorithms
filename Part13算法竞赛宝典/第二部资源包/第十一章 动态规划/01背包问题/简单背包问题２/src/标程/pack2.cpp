 
#include<fstream>
using namespace std;
ifstream cin("pack2.in");
ofstream cout("pack2.out");
int v,n,f[20005];
int main()
{
    int i,j,w;
    cin>>v>>n;
    for(i=1;i<=n;++i)
        for(cin>>w,j=v;j>=w;--j)
            if(f[j]<f[j-w]+w)
                f[j]=f[j-w]+w;
    cout<<v-f[v]<<'\n';
    return 0;
}
