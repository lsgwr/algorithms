//ÂóÉ­Êý
#include <iostream>
#include <math.h>
#include <string.h>
#define Maxn 500
#define Base 10
using namespace std;
struct hugeint{
    int a[Maxn+1],len;
    void clear(){
        memset(a,0,sizeof(a));
        len=0;
    }
    void out(){
        for(int i=1;i<=Maxn;i++) 
        cout<<int(a[i]);
        cout<<endl;
    }
}ans,T;
int p;
hugeint mul(hugeint a,hugeint b){
    hugeint ans;    ans.clear();
    for(int i=Maxn;i>=Maxn-b.len+1 && i>0;i--)
        for(int j=Maxn;j>=Maxn-a.len+1 && i+j-Maxn>0;j--)
            ans.a[i+j-Maxn]+=a.a[j]*b.a[i];
    int i=Maxn;
    for(i=Maxn;i>=Maxn-(a.len+b.len) && i>=0;i--){
        ans.a[i-1]+=ans.a[i]/Base;
        ans.a[i]%=Base;
    }
    while(ans.a[i]==0)  i++;
    ans.len=Maxn-i+1;
    return ans;
}
hugeint sub(hugeint a){
    int i;
    a.a[Maxn]--;
    for(int i=Maxn;i>=1;i--)
        if(a.a[i]<0){
            a.a[i-1]--;
            a.a[i]+=10;
        }
        else
            return a;
}
void s(int x){
    if((1<<x)>p) return ;
    if(p&(1<<x)) ans=mul(ans,T);
    T=mul(T,T);
//    T.out();
    s(x+1);
}
int main(){
    cin>>p;
    cout<<int(p*(log(2)/log(10)))+1<<endl;
    ans.len=1;  ans.a[Maxn]=1;
    T.len=1;    T.a[Maxn]=2;
    s(0);
    ans=sub(ans);
    ans.out();
    return 0;
}
