#include <iostream>
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
    }

    // 根节点 
    Node* root;
    // 节点数
    int count；

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
};

