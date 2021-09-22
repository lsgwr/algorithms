#ifndef _STUDENT_H_
#define _STUDENT_H_
#include <iostream>
using namespace std;
struct Student{

    string name;
    int score;

    /* 重载比较运算符 */
    bool operator<(const Student &otherStudent){
        // 优先比较分数；分数相等的情况下看名字的字典序；
        return score != otherStudent.score ? score > otherStudent.score : name < otherStudent.name;
    }
    
    /* 重载输出运算符 */
    friend ostream& operator<<(ostream &os, const Student &student){
        os<<"Student: "<< student.name<<" "<<student.score<<endl;
        return os;
    }
};
#endif // _STUDENT_H_