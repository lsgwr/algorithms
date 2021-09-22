/***********************************************************
 * @Description : 测试类
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/30 12:38
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
#include "DenseGraph.h"
#include "SparseGraph.h"

using namespace std;

int main(void) {
    int N = 20;
    int M = 100;

    srand(time(NULL));


    // Sparse Graph
    SparseGraph g1(N, false);
    for (int i = 0; i < M; i++) {
        int a = rand() % N;
        int b = rand() % N;
        g1.addEdge(a, b);
    }

    // O(E)
    for (int v = 0; v < N; v++) {
        cout << v << " : ";
        SparseGraph::adjIterator adj(g1, v);
        for (int w = adj.begin(); !adj.end(); w = adj.next())
            cout << w << " ";
        cout << endl;
    }

    cout << endl;

    // Dense Graph
    DenseGraph g2(N , false);
    for( int i = 0 ; i < M ; i ++ ){
        int a = rand()%N;
        int b = rand()%N;
        g2.addEdge( a , b );
    }

    // O(V^2)
    for( int v = 0 ; v < N ; v ++ ){
        cout<<v<<" : ";
        DenseGraph::adjIterator adj( g2 , v );
        for( int w = adj.begin() ; !adj.end() ; w = adj.next() )
            cout<<w<<" ";
        cout<<endl;
    }
    return 0;
}
 