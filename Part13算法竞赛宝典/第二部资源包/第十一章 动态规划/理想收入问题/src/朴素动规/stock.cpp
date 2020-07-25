//理想收入问题－朴素动规 
#include <stdio.h>
#include <stdlib.h>

FILE *in=fopen("stock.in","rb");
FILE *out=fopen("stock.out","w");

int main()
{
    int n;
    fscanf(in,"%d",&n);
    
    double v[n+1],f[n+1];
    int i,j,k;
    for(i=1;i<=n;i++)
        fscanf(in,"%lf",&v[i]),f[i]=0;
    
    /*f[i]=max{f[j]/v[k]*v[i]} (1<=j<i , j<k<=i , f[0]=1)*/
    f[0]=1;
    for(i=1;i<=n;i++)
    for(j=0;j<i;j++)
    for(k=j+1;k<=i;k++)
    {
        double t=f[j]/v[k]*v[i];
        if(t>f[i])
            f[i]=t;
    }
    
    fprintf(out,"%.4lf\n",f[n]);
    return 0;
}
