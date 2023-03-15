/**
 * @author yyb
 * leetcode_tag_graph
 * leetcode 标签 图论
 */
public class TargetGraph {

    //region    20230315    1615. 最大网络秩

    /**
     * https://leetcode.cn/problems/maximal-network-rank/
     * @param n 整数 n
     * @param roads 数组 roads
     * @return  整个基础设施网络的 最大网络秩
     */
    public int maximalNetworkRank(int n, int[][] roads) {
        boolean[][] connect=new boolean[n][n];
        int[] degree=new int[n];
        for (int[] v:roads) {
            connect[v[0]][v[1]]=true;
            connect[v[1]][v[0]]=true;
            degree[v[0]]++;
            degree[v[1]]++;
        }
        int maxRank=0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                int rank=degree[i]+degree[j]-(connect[i][j]?1:0);
                maxRank=Math.max(rank,maxRank);
            }
        }
        return maxRank;
    }
    //endregion

}
