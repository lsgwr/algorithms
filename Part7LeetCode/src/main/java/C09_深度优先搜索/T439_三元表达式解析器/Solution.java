package C09_深度优先搜索.T439_三元表达式解析器;

class Solution {
    public String parseTernary(String expression) {
      return dfs(expression);
    }

    /**
     * "T?T?F:5:3" ==> "F"没过
     */
    private String dfs(String expression) {
        int n = expression.length();

        int checkLevel = 0;
        // 当只剩一个字符时，直接不会进入下面的for循环就把当前的结果返回了
        for (int j = 1; j < n; j++) {
            if (expression.charAt(j) == '?') {
                checkLevel++;
            }
            if (expression.charAt(j) == ':') {
                checkLevel--;
            }
            if (checkLevel == 0) {
                // 通过checkLevel的++和--实现了寻找当前表达式的和第1个?匹配的:的下标j，然后根据三木表达式的True或False决定下一层递归的的三木表达式
                return (expression.charAt(0) == 'T') ? parseTernary(expression.substring(2, j)) : parseTernary(expression.substring(j+1, n));
            }
        }
        return expression;
    }

    /***
     * "T?2:3"  => "2"
     * "F?1:T?4:5"  ==> "4"
     * "T?T?F:5:3" ==> "F"
     * "F?F?3:8:T?F:T?0:F?8:T" ==> "F"
     */
    public static void main(String[] args) {
        String expression = "T?T?F:5:3";
        System.out.println(new Solution().parseTernary(expression));
    }
}
