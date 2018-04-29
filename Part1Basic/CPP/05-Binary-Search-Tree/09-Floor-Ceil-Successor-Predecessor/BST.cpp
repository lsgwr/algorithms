
// 删除最大最小值
#include <iostream>
#include <queue>

using namespace std;

template<typename Key, typename Value>
class BST {

private:
    struct Node {
        Key key;
        Value value;
        // 左子节点
        Node *left;
        // 右子节点
        Node *right;

        // 构造函数
        Node(Key key, Value value) {
            // key是唯一地，不能重复
            this->key = key;
            this->value = value;
            this->left = NULL;
            this->right = NULL;
        }

        Node(Node *node) {
            // key是唯一地，不能重复
            this->key = node->key;
            this->value = node->value;
            this->left = node->left;
            this->right = node->right;
        }
    };

    // 根节点
    Node *root;
    // 节点数
    int count;

public:
    BST() {
        root = NULL;
        count = 0;
    }

    ~BST() {
        // TODO: ~BST()
        destroy(root);
    }

    int size() {
        return count;
    }

    bool isEmpty() {
        return count == 0;
    }

    void insert(Key key, Value value) {
        root = insert(root, key, value);
    }

    bool contain(Key key) {
        return contain(root, key);
    }

    Value *search(Key key) {
        return search(root, key);
    }

    /*****************************************************************************
     * 前、中、后序遍历的私有函数：前中后序遍历的差别在于访问当前节点是在一开始、中间还是最后
     * ***************************************************************************/

    //前序遍历
    void preOrder() {
        preOrder(root);
    }

    // 中序遍历
    void inOrder() {
        inOrder(root);
    }

    // 后序遍历
    void postOrder() {
        postOrder(root);
    }

    // 层序遍历(Level Order),又称广度优先遍历
    void levelOrder() {
        queue < Node * > queue;
        // 先推入根节点
        queue.push(root);
        while (!queue.empty()) {
            Node *node = queue.front();
            queue.pop();
            cout << node->key << endl;
            if (node->left != NULL) {
                queue.push(node->left);
            }
            if (node->right != NULL) {
                queue.push(node->right);
            }
        }
    }

    // 寻找最小的键值
    Key min() {
        assert(count > 0);
        Node *minNode = min(root);
        return minNode->key;
    }

    // 寻找最大的键值
    Key max() {
        assert(count > 0);
        Node *maxNode = max(root);
        return maxNode->key;
    }

    // 删除最小值对应的节点
    void deleteMin() {
        if (root != NULL) {
            root = deleteMin(root);
        }
    }

    // 删除最大值对应的节点
    void deleteMax() {
        if (root != NULL) {
            root = deleteMax(root);
        }
    }


    // 从二叉树中删除键值为key的节点
    void deleteNode(Key key) {
        root = deleteNode(root, key);
    }


    // 寻找key的floor值(小于key的最大值)，递归算法
    // 如果不存在的话(key比BST中的最小值还小)，返回NULL
    Key *floor(Key key) {
        // BST为空或者小于BST中的最小值，直接返回NULL，因为肯定不用找了
        if (count == 0 || key < min()) {
            return NULL;
        }
        Node *floorNode = floor(root, key);
        return &(floorNode->key);
    }

    // 寻找key的ceil值(大于key的最小值)，递归算法
    // 如果不存在的话(key比BST中的最大值还大)，返回NULL
    Key *ceil(Key key) {
        // BST为空或者大于BST中的最大值，直接返回NULL，因为肯定不用找了
        if (count == 0 || key > max()) {
            return NULL;
        }
        Node *ceilNode = ceil(root, key);
        return &(ceilNode->key);
    }

private:
    // 向以node为根节点的二叉树搜索树种，插入节点(key,value)
    // 返回插入新节点后的二叉搜索树的根
    Node *insert(Node *node, Key key, Value value) {
        // 处理最基本事件，即可以退出递归的事件
        if (node == NULL) {
            // 递归到没有子树了，就在这个位置插入。
            count++; // 元素数先+1
            // 把当前要插入的节点作为根节点返回，一层层递归后会跟新树路径上的节点关系地
            return new Node(key, value);
        }
        if (key < node->key) {
            // 小于根节点的键，往左边子树插入
            node->left = insert(node->left, key, value);
        } else if (key > node->key) {
            // 大于根节点的键，往右边子树插入
            node->right = insert(node->right, key, value);
        } else { // 如果key相等就直接更新(注意二叉搜索树的键是唯一地)
            node->value = value;
        }

        return node;
    }

    // 被递归调用来判断是否包含指定key的函数
    bool contain(Node *node, Key key) {
        // 递归退出条件.最后到达树底，下面没节点了(NULL),返回false
        if (node == NULL) {
            return false;
        }
        if (key > node->key) {
            // 大于就在右边找
            return contain(node->right, key);
        } else if (key < node->key) {
            // 小于就在左边找
            return contain(node->left, key);
        } else {
            // 等于就返回true,代表找到含有这个key地节点了
            return true;
        }

    }

    // 在以node为根的二叉搜索树种查找key对应的value
    Value *search(Node *node, Key key) {
        // 递归退出条件.最后到达树底，返回NULL
        if (node == NULL) {
            return NULL;
        }
        if (key > node->key) {
            // 大于就在右边找
            return search(node->right, key);
        } else if (key < node->key) {
            // 小于就在左边找
            return search(node->left, key);
        } else {
            // 等于就返回true,代表找到含有这个key地节点了,返回Value的地址
            return &(node->value);
        }
    }


    /*****************************************************************************
     * 前、中、后序遍历的私有函数：前中后序遍历的差别在于访问当前节点是在一开始、中间还是最后
     * ***************************************************************************/

    //对以node为根的二叉搜索树进行前序遍历
    void preOrder(Node *node) {
        if (node != NULL) {
            cout << node->key << endl;
            preOrder(node->left);
            preOrder(node->right);
        }
    }

    //对以node为根的二叉搜索树进行中序遍历
    void inOrder(Node *node) {
        if (node != NULL) {
            inOrder(node->left);
            cout << node->key << endl;
            inOrder(node->right);
        }
    }

    //对以node为根的二叉搜索树进行后序遍历
    void postOrder(Node *node) {
        if (node != NULL) {
            postOrder(node->left);
            postOrder(node->right);
            cout << node->key << endl;
        }
    }

    //对以node为根的二叉搜索树进行后序遍历,在这个过程中进行析构
    void destroy(Node *node) {
        if (node != NULL) {
            destroy(node->left);
            destroy(node->right);
            delete node;
            count--;
        }
    }

    // 在以node为根的二叉搜索树中，返回最小键值的点
    Node *min(Node *node) {
        // 没有左子节点的时候就到了最小点了
        if (node->left == NULL) {
            return node;
        }
        return min(node->left);
    }

    // 在以node为根的二叉搜索树中，返回最大键值的点
    Node *max(Node *node) {
        // 没有左子节点的时候就到了最小点了
        if (node->right == NULL) {
            return node;
        }
        return min(node->right);
    }

    // 删除以Node为根节点的子树中的最小值
    Node *deleteMin(Node *node) {
        if (node->left == NULL) {
            // 没有左节点了，就要看看右节点是否存在
            Node *rightNode = node->right;
            // 删除最小节点node
            delete node;
            count--;
            // 不管rightNode是否为空，都可以直接返回地
            return rightNode;
        }
        // 好好体验递归
        node->left = deleteMin(node->left);
        return node;
    }

    // 删除以Node为根节点的子树中的最大值
    Node *deleteMax(Node *node) {
        if (node->right == NULL) {
            // 没有右节点了，就要看看左节点是否存在
            Node *leftNode = node->left;
            // 删除最小节点node
            delete node;
            count--;
            // 不管rightNode是否为空，都可以直接返回地
            return leftNode;
        }
        // 好好体验递归
        node->right = deleteMax(node->right);
        return node;
    }

    // 删除指定节点作为树的根的二叉搜索树中键值为Key的节点
    // 返回删除节点后的新的二分搜索树的根。
    // 删除最小节点后的替换值实际从删除节点的右子树中找最小节点即可
    Node *deleteNode(Node *node, Key key) {
        // 到节点为NULL时，就可以退出了
        if (node == NULL) {
            return NULL;
        }
        if (key < node->key) {
            node->left = deleteNode(node->left, key);
            return node;
        } else if (key > node->key) {
            node->right = deleteNode(node->right, key);
            return node;
        } else {
            // 关键之处，循环到某阶段key = node->key了，最为复杂
            if (node->left == NULL) {
                // 左子树为空，看右子树，删除节点后，所有右子树节点前提一位
                Node *rightNode = node->right;
                delete node;
                count--;
                // 右子树也为空的话也不影响，会返回NULL，不影响地
                return rightNode;
            }
            if (node->right == NULL) {
                // 右子树为空，看左子树，删除节点后，所有左子树节点前提一位
                Node *leftNode = node->left;
                delete node;
                count--;
                return leftNode;
            }

            // 左右子树都不为空，找右子树的最小节点即可，然后把左右子树更新一下
            // 找到右子树的最小值.注意要新建一个Node,不然下面的
            Node *successor = new Node(min(node->right));
            // 这里++，在下面的deleteMin会--地
            count++;
            // 更新右子树指针,注意deleteMin返回的node->right作为根节点的树的最小值的父亲节点
            successor->right = deleteMin(node->right);
            successor->left = node->left;
            // 删除指定节点
            delete node;
            count--;
            // 返回新的二叉搜索树的根节点
            return successor;
        }
    }

    // 在以node为根的二叉搜索树中，寻找key的floor值所处的节点，递归算法
    Node *floor(Node *node, Key key) {
        if (node == NULL) {
            return NULL;
        }

        // 如果node的key值与要找的key值相等，则node本身就是key的floor节点
        if (node->key == key) {
            return node;
        }
        // 如果node的值比要寻找的key值大，则要寻找的key的floor节点一定在node的左子树中
        if (node->key > key) {
            return floor(node->left, key);
        }

        // 如果node->key < key，则node可能是key的floor节点，也有可能不是，因为node的右子树中可能还存在比node-key)
        // 大但是小于key的其余节点),需要尝试着在node的右子树中寻找一下
        Node *tmpNode = floor(node->right, key);
        if (tmpNode != NULL) {
            return tmpNode;
        }
        return node;
    }


    // 在以node为根的二叉搜索树中，寻找key的ceil值所处的节点，递归算法
    Node *ceil(Node *node, Key key) {
        if (node == NULL) {
            return NULL;
        }

        // 如果node的key值与要找的key值相等，则node本身就是key的ceil节点
        if (node->key == key) {
            return node;
        }
        // 如果node的值比要寻找的key值小，则要寻找的key的floor节点一定在node的右子树中
        if (node->key < key) {
            return ceil(node->right, key);
        }

        // 如果node->key > key，则node可能是key的ceil节点，也有可能不是，因为node的左子树中可能还存在比node-key
        // 小但是大于key的其余节点),需要尝试着在node的左子树中寻找一下
        Node *tmpNode = ceil(node->left, key);
        if (tmpNode != NULL) {
            return tmpNode;
        }
        return node;
    }
};

// 随机打乱数组
void shuffle(int arr[], int n) {

    srand(time(NULL));
    for (int i = n - 1; i >= 0; i--) {
        int x = rand() % (i + 1);
        swap(arr[i], arr[x]);
    }
}

void shuffle(vector<int> &vec) {

    srand(time(NULL));
    for (int i = vec.size() - 1; i >= 0; i--) {
        int x = rand() % (i + 1);
        swap(vec[i], vec[x]);
    }
}

int main(void) {
    BST<int, int> bst;

    //将[0, N)之间的偶数保存在nums中
    int N = 1000;
    vector<int> nums;
    for (int i = 0; i < N; i += 2)
        nums.push_back(i);
    int minNum = nums[0];
    int maxNum = nums[nums.size() - 1];

    // 将nums乱序处理
    shuffle(nums);

    // 向二分搜索树中插入[0, N)之间的所有偶数
    for (int i = 0; i < nums.size(); i++)
        bst.insert(nums[i], nums[i]);

    // 对[0...N]区间里的N+1个数, 调用二分搜索树的floor和ceil, 查看其结果
    for (int i = 0; i <= N; i++) {

        // 测试floor
        int *floorKey = bst.floor(i);
        if (i % 2 == 0) {
            if (i >= 0 && i < N) assert(*floorKey == i);
            else if (i < 0) assert(floorKey == NULL);
            else
                assert(*floorKey == maxNum);
        } else {
            if (i - 1 >= 0 && i - 1 < N) assert(*floorKey == i - 1);
            else if (i - 1 < 0) assert(floorKey == NULL);
            else
                assert(*floorKey == maxNum);
        }

        cout << "the floor of " << i << " is ";
        if (floorKey == NULL)
            cout << "NULL" << endl;
        else
            cout << *floorKey << endl;


        // 测试ceil
        int *ceilKey = bst.ceil(i);
        if (i % 2 == 0) {
            if (i >= 0 && i < N) assert(*ceilKey == i);
            else if (i < 0) assert(*ceilKey == minNum);
            else
                assert(ceilKey == NULL);
        } else {
            if (i + 1 >= 0 && i + 1 < N) assert(*ceilKey == i + 1);
            else if (i + 1 < 0) assert(*ceilKey == minNum);
            else
                assert(ceilKey == NULL);
        }

        cout << "the ceil of " << i << " is ";
        if (ceilKey == NULL)
            cout << "NULL" << endl;
        else
            cout << *ceilKey << endl;


        cout << endl;
    }

    return 0;

}
