package C12_动态规划.T221_最大正方形;

// 暴力解法
class Solution {
    public int maximalSquare(char[][] grid) {
        // 判空
        if (grid == null) {
            return 0;
        }
        int R = grid.length;
        if (R == 0) {
            return 0;
        }
        int C = grid[0].length;
        if (C == 0) {
            return 0;
        }
        int max = 0;
        // 每个点都从左上角开始往右下角搜索
        for(int r = 0; r < R; r++){
            for(int c = 0; c < C; c++){
                if(grid[r][c] == '0'){
                    // 0地话就直接跳过
                    continue;
                }
                int len = Math.min(R - r, C - c);
                for(int l = 0; l < len; l++){
                    // 默认所有元素都是1
                    boolean allIsOne = true;
                    // 内部循环检查一个小正方形，注意是小于等于号哦
                    for(int i = r; i <= r + l; i++){
                        for(int j = c; j <= c + l; j++){
                            if(grid[i][j] == '0'){
                                // 一旦检测到不全是1，直接退出本次循环
                                allIsOne = false;
                                break;
                            }
                        }
                        // 一旦检测到不全是1，直接退出本次循环
                        if(!allIsOne){
                            break;
                        }
                    }
                    // 本次l循环下来，标志位仍然没被改为false，说明此次循环
                    if(allIsOne){
                        if(max < (l + 1) * (l + 1)){
                            max = (l + 1) * (l + 1);
                        }
                    }else {
                        break;
                    }
                }
            }
        }
        return max;
    }
}