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
};

