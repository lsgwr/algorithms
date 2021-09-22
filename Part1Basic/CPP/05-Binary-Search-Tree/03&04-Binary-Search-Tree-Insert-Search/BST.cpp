

#include <iostream>
#include "FileOps.h"
#include <vector>
#include <string>
using namespace std;

template<typename Key, typename Value>
class BST{

private:
    struct Node{
        Key key;
        Value value;
        // 左子节点
        Node* left;
        // 右子节点
        Node* right;
        // 构造函数
        Node(Key key, Value value){
            // key是唯一地，不能重复
            this->key = key;
            this->value = value;
            this->left = NULL;
            this->right = NULL;
        }
    };

    // 根节点 
    Node* root;
    // 节点数
    int count;

public:
    BST(){
        root = NULL;
        count = 0;
    }

    ~BST(){
        // TODO: ~BST()
    }

    int size(){
        return count;
    }

    bool isEmpty(){
        return count == 0;
    }

    void insert(Key key, Value value){
        root = insert(root, key, value);
    }

    bool contain(Key key){
        return contain(root, key);
    }

    Value* search(Key key){
        return search(root, key);
    }

private:
    // 向以node为根节点的二叉树搜索树种，插入节点(key,value)
    // 返回插入新节点后的二叉搜索树的根
    Node* insert(Node* node, Key key, Value value){
        // 处理最基本事件，即可以退出递归的事件
        if(node == NULL){
            // 递归到没有子树了，就在这个位置插入。
            count++; // 元素数先+1
            // 把当前要插入的节点作为根节点返回，一层层递归后会跟新树路径上的节点关系地
            return new Node(key, value);
        }
        if(key < node->key){
            // 小于根节点的键，往左边子树插入
            node->left = insert(node->left, key, value);
        }else if(key > node->key){
            // 大于根节点的键，往右边子树插入
            node->right = insert(node->right, key, value);
        }else{ // 如果key相等就直接更新(注意二叉搜索树的键是唯一地)
            node->value = value;
        }

        return node;
    }

    // 被递归调用来判断是否包含指定key的函数
    bool contain(Node* node, Key key){
        // 递归退出条件.最后到达树底，下面没节点了(NULL),返回false
        if(node == NULL){
            return false;
        }
        if(key > node->key){
            // 大于就在右边找
            return contain(node->right, key);
        }else if(key < node->key){
            // 小于就在左边找
            return contain(node->left, key);
        }else{
            // 等于就返回true,代表找到含有这个key地节点了
            return true;
        }

    }

    // 在以node为根的二叉搜索树种查找key对应的value
    Value* search(Node* node, Key key){
        // 递归退出条件.最后到达树底，返回NULL
        if(node == NULL){
            return NULL;
        }
        if(key > node->key){
            // 大于就在右边找
            return search(node->right, key);
        }else if(key < node->key){
            // 小于就在左边找
            return search(node->left, key);
        }else{
            // 等于就返回true,代表找到含有这个key地节点了,返回Value的地址
            return &(node->value);
        }
    }
};

int main(void){
        // 使用圣经作为我们的测试用例
    string filename = "/Users/liangshanguang/Program/Algorithm/Play-with-Algorithms/05-Binary-Search-Tree/Course Code (C++)/04-Binary-Search-Tree-Search/bible.txt";
    vector<string> words;
    if( FileOps::readFile(filename, words) ) {

        cout << "There are totally " << words.size() << " words in " << filename << endl;
        cout << endl;


        // 测试 BST
        time_t startTime = clock();

        // 统计圣经中所有词的词频
        // 注: 这个词频统计法相对简陋, 没有考虑很多文本处理中的特殊问题
        // 在这里只做性能测试用
        BST<string, int> bst = BST<string, int>();
        for (vector<string>::iterator iter = words.begin(); iter != words.end(); iter++) {
            int *res = bst.search(*iter);
            if (res == NULL)
                bst.insert(*iter, 1);
            else
                (*res)++;
        }

        // 输出圣经中god一词出现的频率
        if(bst.contain("god"))
            cout << "'god' : " << *bst.search("god") << endl;
        else
            cout << "No word 'god' in " << filename << endl;

        time_t endTime = clock();

        cout << "BST , time: " << double(endTime - startTime) / CLOCKS_PER_SEC << " s." << endl;
        cout << endl;
    }
    return 0;
}
