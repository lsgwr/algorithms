//ÓÑºÃ³ÇÊÐ 
#include<iostream>
#include<fstream>
#define M 10000
using namespace std;

ifstream fin("city.in");
ofstream fout("city.out");

int a[M+1];
int L[M+1];
int m,n;
struct pp
{
    int n,s;
}s[M+1];

int cmp(const void *p,const void *q)
{
    pp *a=(pp *)p,*b=(pp *)q;
    if(a->n > b->n) return 1;
    if(a->n < b->n) return -1;
    return 0;
}

int half(int k)
{
    int begin,end,mid;
    begin=0;end=m;
    while(begin<=end)
    {
        mid=begin+end>>1;
        if(s[L[mid]].s>=k) end=mid-1;
        else begin=mid+1;
    }
    if(s[L[mid]].s>=k) return mid-1;
    else return mid;
}

void init()
{
    fin>>m>>m;
    fin>>n;
    for(int i=1;i<=n;++i)
        fin>>s[i].n>>s[i].s;
}

void dp()
{
    int i,j,k=0;
    m=1;
    L[1]=1;
    a[1]=1;
    for(i=2;i<=n;++i)
    {
        j=half(s[i].s);
        a[i]=a[L[j]]+1;
        L[a[i]]=i;
        if(a[i]>m) m=a[i];
    }
    for(i=1;i<=n;++i)
        if(a[i]>k)
            k=a[i];
    fout<<k<<endl;
}

int main()
{
    init();
    s[0].n=-1;
    qsort(s,n+1,sizeof(s[1]),cmp);
    dp();
    
    fin.close();
    fout.close();
    return 0;
}
