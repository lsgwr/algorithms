//Ô¤Ëã 
#include <fstream>
using namespace std;

int main() 
{
  ifstream fin ("budget.in");
  ofstream fout ("budget.out");
  int n,m_sum,m[60],v[60],p[60],v_max[3200],link[60][3];
  fin >> m_sum >> n;
  m_sum/=10;
  for (int i=1;i<=n;i++) 
  {
    fin >> m[i] >> v[i] >> p[i];
    m[i]/=10;
    if (p[i]!=0) 
      link[p[i]][++link[p[i]][0]]=i;
  }
  memset(v_max,0,sizeof(v_max));
  for (int i=1;i<=n;i++) 
    for (int j=m_sum;j>=1;j--) 
      if (p[i]==0 && j>=m[i]) 
      {
        v_max[j]=max(v_max[j],v_max[j-m[i]]+m[i]*v[i]);
        int a=link[i][1],b=link[i][2];
        if (link[i][0]>=1 && j-m[i]>=m[a]) 
          v_max[j]=max(v_max[j],v_max[j-m[i]-m[a]]+m[i]*v[i]+m[a]*v[a]);
        if (link[i][0]==2) 
        {
          if (j-m[i]>=m[b]) 
            v_max[j]=max(v_max[j],v_max[j-m[i]-m[b]]+m[i]*v[i]+m[b]*v[b]);
          if (j-m[i]>=m[a]+m[b]) 
            v_max[j]=max(v_max[j],v_max[j-m[i]-m[a]-m[b]]+m[i]*v[i]+m[a]*v[a]+m[b]*v[b]);
        }
    }
  fout << v_max[m_sum]*10 << endl;
  fin.close();
  fout.close();
  return 0;
}
