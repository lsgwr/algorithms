
#ifndef _EDGE_H_
#define _EDGE_H_

#include <iostream>
#include <cassert>
using namespace std;

template<typename Weight>
class Edge{
private:
    // 无向边的话a、b无所谓，有向边的话代表从a指向b
    int a, b;
    // 边的权重 
    Weight weight;

public:
    Edge(int a, int b, Weight weight){
        this->a = a;
        this->b = b;
        this->weight = weight;
    }

    Edge(){}
    ~Edge(){}

    // 返回第一个顶点
    int v(){
        return a;
    }

    // 返回第二个顶点
    int w(){
        return b;
    }

    // 获取边的权重
    Weight wt(){
        return weight;
    }

    // 返回x的一个邻接点
    int other(int x){
        assert(x == a || x==b);
        return x == a ? b : a;
    }

    // 重写输出函数
    friend ostream& operator<<(ostream &os, const Edge &e){
        os<<e.a<<"-"<<e.b<<": "<< e.weight;
        return os;
    }

    bool operator<(Edge<Weight> &e){
        return weight < e.wt();
    }

    bool operator<=(Edge<Weight> &e){
        return weight <= e.wt();
    }

    bool operator>(Edge<Weight> &e){
        return weight > e.wt();
    }

    bool operator>=(Edge<Weight> &e){
        return weight >= e.wt();
    }

     bool operator==(Edge<Weight> &e){
        return weight == e.wt();
    }
};
#endif // _EDGE_H_