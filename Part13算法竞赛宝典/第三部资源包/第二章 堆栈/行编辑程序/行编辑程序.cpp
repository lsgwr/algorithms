//ÐÐ±à¼­Æ÷ 
#include<fstream>
#include<string>
using namespace std;
ifstream cin("LineEditor.in");
ofstream cout("LineEditor.out");
string s;
char a[5005];
int p;
int main()
{
    int i,len;
    cin>>s;
    len=s.size();
    for(i=0;i<len;++i)
    {
        if(s[i]=='@')
            p=0;
        else if(s[i]=='#' && p>0)
            --p;
        else if(s[i]!='#')
            a[++p]=s[i];
    }
    for(i=1;i<=p;++i)
        cout<<a[i];
    cout<<'\n';
    return 0;
}
