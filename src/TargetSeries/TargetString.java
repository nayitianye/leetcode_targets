//系列相同或问题进阶的分类
package TargetSeries;

/**
 * @author yyb
 * leetcode_tag_String
 * leetcode 标签 字符串
 */
public class TargetString {

    //region    20230422    243. 最短单词距离

    /**
     * https://leetcode.cn/problems/shortest-word-distance/
     *
     * @param wordsDict 字符串数组 wordDict
     * @param word1     字符串 word1
     * @param word2     字符串 word2
     * @return 返回列表中这两个单词之间的最短距离
     */
    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        int index1 = -1;
        int index2 = -1;
        int minDistance = wordsDict.length;
        for (int i = 0; i < wordsDict.length; i++) {
            if (word1.equals(wordsDict[i])) {
                index1 = i;
            }
            if (word2.equals(wordsDict[i])) {
                index2 = i;
            }
            if (index1 != -1 && index2 != -1) {
                minDistance = Math.min(minDistance, Math.abs(index1 - index2));
            }
        }
        return minDistance;
    }
    //endregion

    //region    20230422    244. 最短单词距离 II
    class WordDistance {

        private String[] wordsDictionary;

        public WordDistance(String[] wordsDict) {
            wordsDictionary = wordsDict;
        }

        public int shortest(String word1, String word2) {
            int index1 = -1;
            int index2 = -1;
            int minDistance = wordsDictionary.length;
            for (int i = 0; i < wordsDictionary.length; i++) {
                if (word1.equals(wordsDictionary[i])) {
                    index1 = i;
                }
                if (word2.equals(wordsDictionary[i])) {
                    index2 = i;
                }
                if (index1 != -1 && index2 != -1) {
                    minDistance = Math.min(minDistance, Math.abs(index1 - index2));
                }
            }
            return minDistance;
        }
    }
    //endregion\\

    //region    20230422    245. 最短单词距离 III

    /**
     * https://leetcode.cn/problems/shortest-word-distance-iii/
     *
     * @param wordsDict 一个字符串数组 wordsDict
     * @param word1     字符串 word1
     * @param word2     字符串 word2
     * @return 这两个单词在列表中出现的最短距离
     */
    public int shortestWordDistance(String[] wordsDict, String word1, String word2) {
        int index1 = -1;
        int index2 = -1;
        int minDistance = wordsDict.length;
        for (int i = 0; i < wordsDict.length; i++) {
            if (wordsDict[i].equals(word1)) {
                index1 = i;
                if (index2 >= 0) {
                    minDistance = Math.min(minDistance, Math.abs(index2 - index1));
                }
            }
            if (wordsDict[i].equals(word2)) {
                index2 = i;
                if (index1 >= 0 && index1 != index2) {
                    minDistance = Math.min(minDistance, Math.abs(index2 - index1));
                }
            }
        }
        return minDistance;
    }
    //endregion你

    public static void main(String[] args) {
        new TargetString().shortestWordDistance(new String[]{"b","a"},"a","b");
    }
}
