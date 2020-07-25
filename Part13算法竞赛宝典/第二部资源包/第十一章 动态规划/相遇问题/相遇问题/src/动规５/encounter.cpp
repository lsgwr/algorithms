#include<fstream>
#include<iomanip>
#define MAX 1577
using namespace std;
ifstream cin("encounter.in");
ofstream cout("encounter.out");
int n,m;
double f[MAX][MAX>>1];//为方便动规，将方格坐标从1开始 
int inline d(int x,int y){return 2+(x>1 && x<n)+(y<m);}//在(x,y)的选择数 
int inline minn(int ax,int bx){return ax<=bx?ax:bx;} //取两者最小值 
int inline maxn(int ax,int bx){return ax>=bx?ax:bx;}//取两者最大值 
int inline absn(int ax){return ax>=0?ax:-ax;}
double Dp(){
    if(m&1^1)//初始奇偶判断 
        return 0;
    int i,j,k,end_k,end_j;
    for(i=n;i>=0;f[i--][0]=1)//逆推   
        for(j=maxn(i,m+1-i),end_j=minn(n,m+1+i);j<=end_j;++j){//优化6、优化7 
            if(j-i&1^1)//对中轴线上的格子的转移 
                f[j][0]=(f[j][1]+f[j][1]+f[j+1][0]+f[j-1][0])/d(j,0);
            for(k=2-(j-i&1),end_k=minn(minn(j-i,m),i-absn(m+1-j));k<=end_k;k+=2)
            //k=2-(j-i&1)找到同色位置;j-i为优化7 ; m限制镜像优化后的矩形;
            // i-absn(m+1-j)为优化6;步长设置为2,优化3 
                f[j][k]=(f[j+1][k]+f[j-1][k]+f[j][k+1]+f[j][k-1])/d(j,k);
                //非中轴线上的格子的转移 
        }
    return f[m+1][0];
}
int main(){
    cin>>n;
    m=n>>1;
    cout<<fixed<<setprecision(4)<<Dp()<<'\n';
    return 0;
}
