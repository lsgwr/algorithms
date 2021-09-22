

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
        destroy(root);
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

    /*****************************************************************************
     * 前、中、后序遍历的私有函数：前中后序遍历的差别在于访问当前节点是在一开始、中间还是最后
     * ***************************************************************************/

    //前序遍历
    void preOrder(){
        preOrder(root);
    }

    // 中序遍历
    void inOrder(){
        inOrder(root);
    }

    // 后序遍历
    void postOrder(){
        postOrder(root);
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


    /*****************************************************************************
     * 前、中、后序遍历的私有函数：前中后序遍历的差别在于访问当前节点是在一开始、中间还是最后
     * ***************************************************************************/

    //对以node为根的二叉搜索树进行前序遍历
    void preOrder(Node* node){
        if(node != NULL){
            cout << node->key << endl;
            preOrder(node->left);
            preOrder(node->right);
        }
    }

    //对以node为根的二叉搜索树进行中序遍历
    void inOrder(Node* node){
        if(node != NULL){
            inOrder(node->left);
            cout << node->key << endl;
            inOrder(node->right);
        }
    }

    //对以node为根的二叉搜索树进行后序遍历
    void postOrder(Node* node){
        if(node != NULL){
            postOrder(node->left);
            postOrder(node->right);
            cout << node->key << endl;
        }
    }

    //对以node为根的二叉搜索树进行后序遍历,在这个过程中进行析构
    void destroy(Node* node){
        if(node != NULL){
            destroy(node->left);
            destroy(node->right);
            delete node;
            count--;
        }
    }
};

int main(void){
    srand(time(NULL));
    BST<int,int> bst = BST<int,int>();

    // 取n个取值范围在[0...m)的随机整数放进二分搜索树中
    int N = 10;
    int M = 100;
    for( int i = 0 ; i < N ; i ++ ){
        int key = rand()%M;
        // 为了后续测试方便,这里value值取和key值一样
        int value = key;
        cout<<key<<" ";
        bst.insert(key,value);
    }
    cout<<endl;

    // 测试二分搜索树的size()
    cout<<"size: "<<bst.size()<<endl<<endl;

    // 测试二分搜索树的前序遍历 preOrder
    cout<<"preOrder: "<<endl;
    bst.preOrder();
    cout<<endl;

    // 测试二分搜索树的中序遍历 inOrder 中序排序可用于排序(升序)
    cout<<"inOrder: "<<endl;
    bst.inOrder();
    cout<<endl;

    // 测试二分搜索树的后序遍历 postOrder 后序遍历可用于释放二叉搜索树
    cout<<"postOrder: "<<endl;
    bst.postOrder();
    cout<<endl;

    return 0;
}
