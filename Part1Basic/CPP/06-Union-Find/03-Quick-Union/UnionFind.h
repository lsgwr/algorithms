/***********************************************************
 * @Description : 利用parent数组把上一节Union操作的效率由N降到logN
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/29 18:59
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
#ifndef UNIONFIND_H
#define UNIONFIND_H

#include <iostream>
#include <cassert>

using namespace std;

namespace UF {
    class UnionFind {

    private:
        // 数据之间连接的情况，这里是数组指针.parent[i]标是下标为i的元素的父元素下标
        int *parent;
        // 并查集有多少元素
        int count;

    public:
        // 构造函数
        UnionFind(int n) {
            count = n;
            // 初始化id数组
            parent = new int[n];
            for (int i = 0; i < n; ++i) {
                // 初始化的时候每个元素的父元素指向自己.这个特征也是每个并查集的根节点的特征
                parent[i] = i;
            }

        }

        ~UnionFind() {
            delete[] parent;
        }

        // 返回p所在的根节点的下标(找到并查集的根节点)
        int find(int p) {
            assert(p >= 0 && p < count);
            // 只要节点不指向自身，说明还没到根节点
            while (p != parent[p]) {
                // 不断刷新p直接遇到并查集的根节点
                p = parent[p];
            }
            return p;
        }

        // 判断下标为p和q的点是否有同样的根，就可以判断p和q是否是连接在一起地了
        bool isConnected(int p, int q) {
            return find(p) == find(q);
        }

        // 连接两个元素p和q
        void unionElements(int p, int q) {
            // 找到p元素对应的根
            int pRoot = find(p);
            int qRoot = find(q);
            // 根元素相等说明两个元素已经在同一个并查集内了，是相互连接地了。直接返回
            if (pRoot == qRoot) {
                return;
            }
            // 不在一个并查集内的话，只需要把两个根节点连接起来即可
            parent[pRoot] = qRoot;
        }
    };
}

#endif //UNIONFIND_H
