/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/29 18:04
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
#ifndef HELLOWORLD_UNIONFIND1_H
#define HELLOWORLD_UNIONFIND1_H

#include <iostream>
#include <cassert>

using namespace std;

namespace UF1 {
    class UnionFind {

    private:
        // 数据之间连接的情况，这里是数组指针.id[i]标是下标为i的元素所属的组id
        int *id;
        // 并查集有多少元素
        int count;

    public:
        // 构造函数
        UnionFind(int n) {
            count = n;
            // 初始化id数组
            id = new int[n];
            for (int i = 0; i < n; ++i) {
                id[i] = i;
            }
        }

        ~UnionFind() {
            delete[] id;
        }

        // 返回id组
        int find(int p) {
            assert(p >= 0 && p < count);
            return id[p];
        }

        // 判断下标为p和q的点之间是否存在连接
        bool isConnected(int p, int q) {
            return find(p) == find(q);
        }

        // 连接两个元素p和q
        void unionElements(int p, int q) {
            int pID = find(p);
            int qID = find(q);
            // 组id相等说明两个元素已经在同一个组了，是相互连接地了
            if (pID == qID) {
                return;
            }

            for (int i = 0; i < count; ++i) {
                // 找到p所在的组了，为了把q加进来，所有=pID的id[i]改成qID即可，注意p也在这次循环修改的范围内了
                if (id[i] == pID) {
                    id[i] = qID;
                }
            }
        }
    };
}

#endif //HELLOWORLD_UNIONFIND1_H
