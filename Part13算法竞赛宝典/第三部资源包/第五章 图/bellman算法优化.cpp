bool bellman_ford(int x)
{
  int i,j,k;
  bool relaxed;//标记是否进行了松弛操作 
  for(i=1;i<=n;i++)//初始化 
    d[i]=INT_MAX;
  d[x]=0;
  for(i=1;i<n;i++)
  {
    relaxed=0;//默认未进行松驰操作
    for(j=1;j<=n;j++)
      for(k=1;k<=n;k++)
        if(w[j][k]!=INT_MAX && d[k]!=INT_MAX && d[j]>d[k]+w[j][k])
        {
          d[j]=d[k]+w[j][k];
          relaxed=1;//进行了松弛操作 
        }
    if(!relaxed)//若未进行松弛操作，跳出循环 
    　break;
  }
  for(i=1;i<=n;i++)//验证是否有负环
    for(j=1;j<=n;j++)
      if(w[j][k]!=INT_MAX && d[k]!=INT_MAX && d[j]>d[k]+w[j][k])
        return 1;//有负环 
  return 0;//无负环      
}
