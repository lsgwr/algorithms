#include <iostream>
#include <cmath>
using namespace std;

template<typename Item>
class MaxHeap{

private:
    // 数据内容
    Item* data;
    // 元素数量
    int count;
    // 数组容量
    int capacity;
    // 上浮的私有函数
    void shiftUp(int k){
        // k > 1是因为到根节点时就没父节点了，不需要再比较了
        while(k > 1 && data[k / 2] < data[k]){
            // 如果当前元素的父元素(在数组中的下表为整除2)比当前元素还小，
            // 则不符合最大堆的定义，那么就交换一下
            swap(data[k / 2], data[k]);
            k /= 2;
        }
    }
    // 把移上来的最后一个元素下移到合适位置
    void shiftDown(int k){
        // 当当前k节点有子节点的时候(有左孩子不一定有右孩子，但有右孩子一定有左孩子。因为生成堆得时候是从左向右地)
        // 等于号别漏
        while(2 * k <= count ){
            // 获取子孩子下标
            int j = 2 * k;
            // 存在右孩子并且右孩子大于左孩子，那么右孩子有父节点交换
            if(j+1 <= count && data[j+1] > data[j]){
                // 选择右节点作为下面与k处节点进行交换
                j++;
            }
            // 父节点比两个子节点的较大值还大，那么不需要交换
            if(data[k] >= data[j]){
                break;
            }
            // 父节点小于孩子节点的较大值，那么就和较大值的子节点交换
            swap(data[k], data[j]);
            // 把换后的子节点作为父节点，接着往下走
            k = j;
        }
    }
public:
    // 构造函数
    MaxHeap(int capacity){
        data = new Item[capacity + 1];
        count = 0;
        this->capacity = capacity;
    }
    // 析构函数
    ~MaxHeap(){
        delete[] data;
    }

    // 获取最大堆的大小
    int size(){
        return count;
    }

    // 判断最大堆是否为空
    bool isEmpty(){
        return count == 0;
    }

    // 插入元素
    void insert(Item item){
        // 防止后面的++count越界
        assert( count + 1 <= capacity);
        // 插入新元素，元素数加1,之所以用++count而不用count++是因为数组下标从1开始
        data[++count] = item;
        // 把新加入的元素向上浮动到合适位置
        shiftUp(count);
    }

    // 弹出最大值(根节点的对象)
    Item popMax(){
        // 保证堆不为空
        assert( count >0);
        // 最大元素为第一个元素
        Item max = data[1];
        // 移出最大元素后，需要把最下面的元素移到上面去
        swap(data[1], data[count]);
        // 少了最后一个元素
        count--;
        // 将换上去(到最上面了,根元素，下标为1)的最后一个元素下移
        shiftDown(1);
        return max;
    }

// 下面是以树状进行打印的函数
public:
    // 以树状打印整个堆结构
    void testPrint(){

        // 我们的testPrint只能打印100个元素以内的堆的树状信息
        if( size() >= 100 ){
            cout<<"This print function can only work for less than 100 int";
            return;
        }

        // 我们的testPrint只能处理整数信息
        if( typeid(Item) != typeid(int) ){
            cout <<"This print function can only work for int item";
            return;
        }

        cout<<"The max heap size is: "<<size()<<endl;
        cout<<"Data in the max heap: ";
        for( int i = 1 ; i <= size() ; i ++ ){
            // 我们的testPrint要求堆中的所有整数在[0, 100)的范围内
            assert( data[i] >= 0 && data[i] < 100 );
            cout<<data[i]<<" ";
        }
        cout<<endl;
        cout<<endl;

        int n = size();
        int max_level = 0;
        int number_per_level = 1;
        while( n > 0 ) {
            max_level += 1;
            n -= number_per_level;
            number_per_level *= 2;
        }

        int max_level_number = int(pow(2, max_level-1));
        int cur_tree_max_level_number = max_level_number;
        int index = 1;
        for( int level = 0 ; level < max_level ; level ++ ){
            string line1 = string(max_level_number*3-1, ' ');

            int cur_level_number = min(count-int(pow(2,level))+1,int(pow(2,level)));
            bool isLeft = true;
            for( int index_cur_level = 0 ; index_cur_level < cur_level_number ; index ++ , index_cur_level ++ ){
                putNumberInLine( data[index] , line1 , index_cur_level , cur_tree_max_level_number*3-1 , isLeft );
                isLeft = !isLeft;
            }
            cout<<line1<<endl;

            if( level == max_level - 1 )
                break;

            string line2 = string(max_level_number*3-1, ' ');
            for( int index_cur_level = 0 ; index_cur_level < cur_level_number ; index_cur_level ++ )
                putBranchInLine( line2 , index_cur_level , cur_tree_max_level_number*3-1 );
            cout<<line2<<endl;

            cur_tree_max_level_number /= 2;
        }
    }

private:
    void putNumberInLine( int num, string &line, int index_cur_level, int cur_tree_width, bool isLeft){

        int sub_tree_width = (cur_tree_width - 1) / 2;
        int offset = index_cur_level * (cur_tree_width+1) + sub_tree_width;
        assert(offset + 1 < line.size());
        if( num >= 10 ) {
            line[offset + 0] = '0' + num / 10;
            line[offset + 1] = '0' + num % 10;
        }
        else{
            if( isLeft)
                line[offset + 0] = '0' + num;
            else
                line[offset + 1] = '0' + num;
        }
    }

    void putBranchInLine( string &line, int index_cur_level, int cur_tree_width){

        int sub_tree_width = (cur_tree_width - 1) / 2;
        int sub_sub_tree_width = (sub_tree_width - 1) / 2;
        int offset_left = index_cur_level * (cur_tree_width+1) + sub_sub_tree_width;
        assert( offset_left + 1 < line.size() );
        int offset_right = index_cur_level * (cur_tree_width+1) + sub_tree_width + 1 + sub_sub_tree_width;
        assert( offset_right < line.size() );

        line[offset_left + 1] = '/';
        line[offset_right + 0] = '\\';
    }
};

int main(){
    MaxHeap<int> maxHeap = MaxHeap<int>(100);
    cout << maxHeap.size() << endl;
    srand(time(NULL));
    for(int i = 0; i < 15; i++){
        // 插入0~100之间的随机数
        maxHeap.insert(rand() % 100);
    }
    // 打印当前的最大堆
    maxHeap.testPrint();
    // 最大堆不断弹出元素
    while(!maxHeap.isEmpty()){
        cout << maxHeap.popMax() << " ";
    }
    cout << endl;
    return 0;
}