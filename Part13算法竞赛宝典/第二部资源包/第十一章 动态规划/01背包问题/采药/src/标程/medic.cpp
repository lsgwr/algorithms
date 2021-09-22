//≤…“© 
#include <stdio.h>
#include <stdlib.h>

main()
{   
  freopen("medic.in","r",stdin);
  freopen("medic.out","w",stdout);     
  int t,m,i,j,a[101],b[101],f[101][1001];      
  scanf("%d%d",&t,&m);      
  for (i=1;i<=m;i++) 
    scanf("%d%d",&a[i],&b[i]);      
  for (i=0;i<=t;i++) 
    f[0][i]=0;      
  for (i=1;i<=m;i++)          
    for (j=0;j<=t;j++)              
    {              
      f[i][j]=f[i-1][j];              
      if ((j>=a[i])&&(f[i][j]<f[i-1][j-a[i]]+b[i])) 
        f[i][j]=f[i-1][j-a[i]]+b[i];               
    }      
  printf("%d",f[m][t]);      
  return(0);
}
