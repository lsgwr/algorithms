/***********************************************************
 * @Description : 自己创建的数据类型
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/5 19:14
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter14HashTable.Section3HashCodeInJDK;

public class Student {
    int grade;
    int cls;
    String firstName;
    String lastName;

    public Student(int grade, int cls, String firstName, String lastName) {
        this.grade = grade;
        this.cls = cls;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public int hashCode() {
        // B只要是素数即可，素数取值参考网上经验总结
        int B = 31;
        int hash = 0;
        hash = hash * B + grade;
        hash = hash * B + cls;
        hash = hash * B + firstName.hashCode();
        hash = hash * B + lastName.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Student)) {
            return false;
        }
        Student student = (Student) o;
        return grade == student.grade &&
                cls == student.cls &&
                firstName.equals(student.firstName) &&
                lastName.equals(student.lastName);
    }
}
