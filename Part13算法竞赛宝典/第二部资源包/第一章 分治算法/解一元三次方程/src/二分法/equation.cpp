//一元三次方程－二分法 
#include<fstream>
#include<iomanip>
using namespace std;
ifstream cin("equation.in");
ofstream cout("equation.out");
float a,b,c,d;
int n;
float ans[4];

float Equation(float x)
{
  return ((a*x+b)*x+c)*x+d;
}

void solve(float l,float r)
{
    if(Equation(l)*Equation(r)>0 && ((r-l)<1 || n>=2))
        return ;
    float mid=(l+r)/2;
    
    if(Equation(mid)<=1e-4 && Equation(mid)>=-1e-4)
    {
        ans[++n]=mid;
        return ;
    }
    solve(l,mid),solve(mid,r);
}

int main()
{
  cin>>a>>b>>c>>d;
  solve(-100,100);
  cout<<fixed<<setprecision(2)<<ans[1]<<' '<<ans[2]<<' '<<ans[3]<<'\n';
  return 0;
}
