import java.util.HashMap;
import java.util.TreeSet;

/**
 * @author yyb
 * leetcode_tag_string
 * leetcode 标签 字符串
 */
public class TargetString {

    //region 804. 唯一摩尔斯密码词  2019/10/3   集合
    /**
     * 国际摩尔斯密码定义一种标准编码方式，将每个字母对应于一个由一系列点和短线组成的字符串， 比如: "a" 对应 ".-", "b" 对应 "-...", "c" 对应 "-.-.", 等等。
     * 为了方便，所有26个英文字母对应摩尔斯密码表如下：
     * [".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]
     * 给定一个单词列表，每个单词可以写成每个字母对应摩尔斯密码的组合。例如，"cab" 可以写成 "-.-..--..."，(即 "-.-." + "-..." + ".-"字符串的结合)。我们将这样一个连接过程称作单词翻译。
     * 返回我们可以获得所有词不同单词翻译的数量。
     * 例如:
     * 输入: words = ["gin", "zen", "gig", "msg"]
     * 输出: 2
     *
     * 解释:
     * 各单词翻译如下:
     * "gin" -> "--...-."
     * "zen" -> "--...-."
     * "gig" -> "--...--."
     * "msg" -> "--...--."
     * 共有 2 种不同翻译, "--...-." 和 "--...--.".
     * 注意:
     * 单词列表words 的长度不会超过 100。
     * 每个单词 words[i]的长度范围为 [1, 12]。
     * 每个单词 words[i]只包含小写字母。
     * @param words
     * @return
     */
    public int uniqueMorseRepresentations(String[] words) {
        String[] codes={".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        TreeSet<String> set=new TreeSet<>();
        for(String word:words){
            StringBuilder res=new StringBuilder();
            for(int i=0;i<word.length();i++){
                res.append(codes[word.charAt(i)-'a']);
            }
            set.add(res.toString());
        }
        return set.size();
    }
    //endregion

    //region 1119. 删去字符串中的元音
    /**
     * 给你一个字符串 S，请你删去其中的所有元音字母（ 'a'，'e'，'i'，'o'，'u'），并返回这个新字符串。
     *
     * 示例 1：
     * 输入："leetcodeisacommunityforcoders"
     * 输出："ltcdscmmntyfrcdrs"
     *
     * 示例 2：
     * 输入："aeiou"
     * 输出：""
     *
     * 提示：
     * S 仅由小写英文字母组成。
     * 1 <= S.length <= 1000
     * @param S
     * @return
     */
    private String removeVowels(String S) {
        StringBuilder res=new StringBuilder();
        for(int i=0;i<S.length();i++){
            if(S.charAt(i)!='a'&&S.charAt(i)!='e'&&S.charAt(i)!='i'&&S.charAt(i)!='o'&&S.charAt(i)!='u'){
                res.append(S.charAt(i));
            }
        }
        return res.toString();
    }
    //endregion

    //region 1165. 单行键盘
    /**
     * 我们定制了一款特殊的力扣键盘，所有的键都排列在一行上。
     * 我们可以按从左到右的顺序，
     * 用一个长度为 26 的字符串 keyboard （索引从 0 开始，到 25 结束）来表示该键盘的键位布局。
     * 现在需要测试这个键盘是否能够有效工作，那么我们就需要个机械手来测试这个键盘。
     * 最初的时候，机械手位于左边起第一个键（也就是索引为 0 的键）的上方。
     * 当机械手移动到某一字符所在的键位时，就会在终端上输出该字符。
     * 机械手从索引 i 移动到索引 j 所需要的时间是 |i - j|。
     *
     * 当前测试需要你使用机械手输出指定的单词 word，
     * 请你编写一个函数来计算机械手输出该单词所需的时间。
     *
     * 示例 1：
     * 输入：keyboard = "abcdefghijklmnopqrstuvwxyz", word = "cba"
     * 输出：4
     * 解释：
     * 机械手从 0 号键移动到 2 号键来输出 'c'，又移动到 1 号键来输出 'b'，接着移动到 0 号键来输出 'a'。
     * 总用时 = 2 + 1 + 1 = 4.
     *
     * 示例 2：
     * 输入：keyboard = "pqrstuvwxyzabcdefghijklmno", word = "leetcode"
     * 输出：73
     *
     * 提示：
     * keyboard.length == 26
     * keyboard 按某种特定顺序排列，并包含每个小写英文字母一次。
     * 1 <= word.length <= 10^4
     * word[i] 是一个小写英文字母
     *
     * @param keyboard
     * @param word
     * @return
     */
    private int calculateTime(String keyboard, String word) {
        HashMap<Character,Integer> map=new HashMap<>();
        for(int i=0;i<keyboard.length();i++){
            map.put(keyboard.charAt(i),i);
        }
        int sum=0;
        int start=0;
        for(int i=0;i<word.length();i++){
            int end=map.get(word.charAt(i));
            sum=sum+Math.abs(end-start);
            start=end;
        }
        return sum;
    }
    //endregion

    public static void main(String[] args){

    }
}
