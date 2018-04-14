/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/14 11:10
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter2SortingBasic.Section2SortUsingTemplate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Comparable<Student> {
    String name;
    Integer score;


    /**
     * 判断对象地大小
     *
     * @param that 被比较的对象
     * @return ths < that 返回大于0的整数；等于返回0；小于返回负整数
     */
    @Override
    public int compareTo(Student that) {
        return this.score.equals(that.score) ? this.name.compareTo(that.name) : this.score.compareTo(that.score);
    }


}
