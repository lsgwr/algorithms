#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <fstream>
#include <cmath>
using namespace std;

int main(int argc,char *argv[])
{
	double ans1, ans2;
	ifstream f1, f2;
	
    for(;;)
    {
		system("random");
		system("excision");
		system("std");
		
		f1.open("excision.out");
		f2.open("std.out");
		f1 >> ans1;
		f2 >> ans2;
		f1.close();
		f2.close();
		
		system("del excision.out");
		system("del std.out");
		
		cout << ans1 << " " << ans2 << "\n";
		if(fabs(ans1 - ans2) > 1e-5)
			system("pause");
	}

    //system("pause");
    return 0;
}
