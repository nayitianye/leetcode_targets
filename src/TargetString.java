import java.util.*;

/**
 * @author yyb
 * leetcode_tag_string
 * leetcode 标签 字符串
 */
public class TargetString {

    //region 17. 电话号码的字母组合  2019/10/11 回溯求解
    //    /**
    //     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
    //     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
    //     * 2-abc 3-def 4-ghi 5-jkl 6-mno 7-pqrs 8-tuv 9-wxyz
    //     * 示例:
    //     * 输入："23"
    //     * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
    //     * @param digits
    //     * @return
    //     */
    //    public List<String> letterCombinations(String digits) {
    //        Map<String,String> map=new HashMap<>();
    //        map.put("2","abc");
    //        map.put("3","def");
    //        map.put("4","ghi");
    //        map.put("5","jkl");
    //        map.put("6","mno");
    //        map.put("7","pqrs");
    //        map.put("8","tuv");
    //        map.put("9","wxyz");
    //        List<String> res=new ArrayList<>();
    //        if(digits.length()!=0){
    //            backtrackletterCombinations("",digits,res,map);
    //        }
    //        return res;
    //    }
    //    private  void backtrackletterCombinations(String conbination,String next_digits,List<String> res,Map<String,String> map){
    //        if(next_digits.length()==0){
    //            res.add(conbination);
    //        }
    //        else{
    //            String digit=next_digits.substring(0,1);
    //            String letters=map.get(digit);
    //            for(int i=0;i<letters.length();i++){
    //                String letter=map.get(digit).substring(i,i+1);
    //                backtrackletterCombinations(conbination+letter,next_digits.substring(1),res,map);
    //            }
    //        }
    //    }
    //    //endregion

    //region 22. 括号生成  2019/10/6  回溯求解
    /**
     * 给出 n 代表生成括号的对数，请你写出一个函数，
     * 使其能够生成所有可能的并且有效的括号组合。
     * 例如，给出 n = 3，生成结果为：
     *
     * [
     *   "((()))",
     *   "(()())",
     *   "(())()",
     *   "()(())",
     *   "()()()"
     * ]
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> ans=new ArrayList<>();
        backtrack(ans,"",0,0,n);
        return ans;
    }
    private void backtrack(List<String> ans,String cur,int open,int close,int max){
        if(cur.length()==max*2){
            ans.add(cur);
            return;
        }
        if(open<max){
            backtrack(ans,cur+"(",open+1,close,max);
        }
        if(close<open){
            backtrack(ans,cur+")",open,close+1,max);
        }
    }
    //endregion

    //region 186. 翻转字符串里的单词 II  2019/10/6  字符串处理
    /**
     * 给定一个字符串，逐个翻转字符串中的每个单词。
     *
     * 示例：
     * 输入: ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
     * 输出: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
     * 注意：
     *
     * 单词的定义是不包含空格的一系列字符
     * 输入字符串中不会包含前置或尾随的空格
     * 单词与单词之间永远是以单个空格隔开的
     * 进阶：使用 O(1) 额外空间复杂度的原地解法。

     * @param s
     */
    public void reverseWords(char[] s) {
        int i=0;
        for(int j=0;j< s.length;j++){
            if(s[j]!=' '){
                continue;
            }
            reverse(s,i,j);
            i=j+1;
        }
        reverse(s,i,s.length);
        reverse(s,0,s.length);
    }
    private void reverse(char[] str,int i,int j){
        for(int k=i;k<(i+j)/2;k++){
            char temp=str[k];
            int g=j-1-k+i;
            str[k]=str[g];
            str[g]=temp;
        }
    }
    //endregion

    //region 293. 翻转游戏  2019/10/6  字符串处理
    /**
     * 你和朋友玩一个叫做「翻转游戏」的游戏，游戏规则：给定一个只有 + 和 - 的字符串。
     * 你和朋友轮流将 连续 的两个 "++" 反转成 "--"。
     * 当一方无法进行有效的翻转时便意味着游戏结束，则另一方获胜。
     * 请你写出一个函数，来计算出每个有效操作后，字符串所有的可能状态。
     *
     * 示例：
     * 输入: s = "++++"
     * 输出:
     * [
     *   "--++",
     *   "+--+",
     *   "++--"
     * ]
     * 注意：如果不存在可能的有效操作，请返回一个空列表 []。
     * @param s
     * @return
     */
    private List<String> generatePossibleNextMoves(String s) {
        List<String> res=new ArrayList<>();
        for(int i=0;i<s.length()-1;i++){
            if(s.charAt(i)=='+'&&s.charAt(i+1)=='+'){
                StringBuilder newStr=new StringBuilder(s);
                newStr.replace(i,i+2,"--");
                res.add(newStr.toString());
            }
        }
        return res;
    }
    //endregion

    //region 521. 最长特殊序列 Ⅰ  2019/10/6  文字游戏
    /**
     * 给定两个字符串，你需要从这两个字符串中找出最长的特殊序列。
     * 最长特殊序列定义如下：该序列为某字符串独有的最长子序列（即不能是其他字符串的子序列）。
     * 子序列可以通过删去字符串中的某些字符实现，但不能改变剩余字符的相对顺序。
     * 空序列为所有字符串的子序列，任何字符串为其自身的子序列。
     * 输入为两个字符串，输出最长特殊序列的长度。如果不存在，则返回 -1。
     *
     * 示例 :
     * 输入: "aba", "cdc"
     * 输出: 3
     * 解析: 最长特殊序列可为 "aba" (或 "cdc")
     * @param a
     * @param b
     * @return
     */
    public int findLUSlength(String a, String b) {
        if(a.equals(b))
            return -1;
        return a.length() > b.length() ? a.length() : b.length();
    }
    //endregion

    //region 544. 输出比赛匹配对  2019/10/6 递归
    /**
     * 在 NBA 季后赛中，我们总是安排较强的队伍对战较弱的队伍，
     * 例如用排名第 1 的队伍和第 n 的队伍对决，这是一个可以让比赛更加有趣的好策略。
     * 现在，给你 n 支队伍，你需要以字符串格式输出它们的 最终比赛配对。
     *
     * n 支队伍按从 1 到 n 的正整数格式给出，
     * 分别代表它们的初始排名（排名 1 最强，排名 n 最弱）。
     * 我们用括号（'(', ')'）和逗号（','）来表示匹配对——括号（'(', ')'）表示匹配，
     * 逗号（','）来用于分割。 在每一轮的匹配过程中，
     * 你都需要遵循将强队与弱队配对的原则。
     *
     * 示例 1：
     * 输入: 2
     * 输出: (1,2)
     * 解析:
     * 初始地，我们有队1和队2两支队伍，按照1，2排列。
     * 因此 用 '(', ')' 和 ','来将队1和队2进行配对，得到最终答案。
     *
     * 示例 2：
     * 输入: 4
     * 输出: ((1,4),(2,3))
     * 解析:
     * 在第一轮，我们将队伍1和4配对，2和3配对，以满足将强队和弱队搭配的效果。
     * 得到(1,4),(2,3).
     * 在第二轮，(1,4) 和 (2,3) 的赢家需要进行比赛以确定最终赢家，
     * 因此需要再在外面加一层括号。
     * 于是最终答案是((1,4),(2,3))。
     *
     * 示例 3：
     * 输入: 8
     * 输出: (((1,8),(4,5)),((2,7),(3,6)))
     * 解析:
     * 第一轮: (1,8),(2,7),(3,6),(4,5)
     * 第二轮: ((1,8),(4,5)),((2,7),(3,6))
     * 第三轮 (((1,8),(4,5)),((2,7),(3,6)))
     * 由于第三轮会决出最终胜者，故输出答案为(((1,8),(4,5)),((2,7),(3,6)))。
     *  
     * 注意:
     * n 的范围是 [2, 212].
     * 保证 n 可以写成 2k 的形式，其中 k 是正整数。
     *
     * @param n
     * @return
     */
    public String findContestMatch(int n) {
        List<String> lists=new ArrayList<>();
        for(int i=1;i<=n;i++){
            lists.add(i+"");
        }
        return getString(lists).get(0);
    }

    public List<String> getString(List<String> lists){
        if(lists.size()==1){
            return lists;
        }
        else{
            List<String> res=new ArrayList<>();
            for(int i=0;i<lists.size()/2;i++){
                res.add("("+lists.get(i)+","+lists.get(lists.size()-i-1)+")");
            }
            return getString(res);
        }
    }
    //endregion

    //region 557. 反转字符串中的单词 III   2019/10/6  字符串处理
    /**
     * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，
     * 同时仍保留空格和单词的初始顺序。
     *
     * 示例 1:
     *
     * 输入: "Let's take LeetCode contest"
     * 输出: "s'teL ekat edoCteeL tsetnoc" 
     * 注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
     *
     * @param s
     * @return
     */
    private String reverseWords(String s) {
        String[] strs=s.split(" ");
        StringBuilder res=new StringBuilder();
        for(int i=0;i<strs.length;i++){
            for(int j=strs[i].length()-1;j>=0;j--){
                res.append(strs[i].charAt(j));
            }
            if(i!=strs.length-1){
                res.append(" ");
            }
        }
        return res.toString();
    }
    //endregion

    //region 657. 机器人能否返回原点  2019/10/6  字符串处理
    /**
     * 在二维平面上，有一个机器人从原点 (0, 0) 开始。
     * 给出它的移动顺序，判断这个机器人在完成移动后是否在 (0, 0) 处结束。
     * 移动顺序由字符串表示。字符 move[i] 表示其第 i 次移动。
     * 机器人的有效动作有 R（右），L（左），U（上）和 D（下）。
     * 如果机器人在完成所有动作后返回原点，则返回 true。否则，返回 false。
     * 注意：机器人“面朝”的方向无关紧要。
     * “R” 将始终使机器人向右移动一次，“L” 将始终向左移动等。
     * 此外，假设每次移动机器人的移动幅度相同。
     *
     * 示例 1:
     * 输入: "UD"
     * 输出: true
     * 解释：机器人向上移动一次，然后向下移动一次。
     * 所有动作都具有相同的幅度，因此它最终回到它开始的原点。因此，我们返回 true。
     * 示例 2:
     * 输入: "LL"
     * 输出: false
     * 解释：机器人向左移动两次。它最终位于原点的左侧，距原点有两次 “移动” 的距离。
     * 我们返回 false，因为它在移动结束时没有返回原点。
     * @param moves
     * @return
     */
    private boolean judgeCircle(String moves) {
        int left=0;
        int front=0;
        for(int i=0;i<moves.length();i++){
            char ch=moves.charAt(i);
            if(ch=='R'){
                left--;
            }else if(ch=='L'){
                left++;
            }else if(ch=='U'){
                front++;
            } else if (ch == 'D') {
                front--;
            }
        }
        if(left!=0||front!=0){
            return false;
        }
        return true;
    }
    //endregion

    //region 709. 转换成小写字母   2019/10/6  字符串处理
    /**
     * 实现函数 ToLowerCase()，该函数接收一个字符串参数 str，
     * 并将该字符串中的大写字母转换成小写字母，之后返回新的字符串。
     * 示例 1：
     * 输入: "Hello"
     * 输出: "hello"
     *
     * 示例 2：
     * 输入: "here"
     * 输出: "here"
     *
     * 示例 3：
     * 输入: "LOVELY"
     * 输出: "lovely"
     * @param str
     * @return
     */
    private String toLowerCase(String str) {
        StringBuilder res=new StringBuilder();
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)>='A'&&str.charAt(i)<='Z'){
                char ch=(char)(str.charAt(i)+32);
                res.append(ch);
            }else{
                res.append(str.charAt(i));
            }
        }
        return res.toString();
    }
    //endregion

    //region 791. 自定义字符串排序   2019/10/9
    /**
     * 字符串S和 T 只包含小写字符。在S中，所有字符只会出现一次。
     * S 已经根据某种规则进行了排序。我们要根据S中的字符顺序对T进行排序。
     * 更具体地说，如果S中x在y之前出现，那么返回的字符串中x也应出现在y之前。
     * 返回任意一种符合条件的字符串T。
     * 示例:
     * 输入:
     * S = "cba"
     * T = "abcd"
     * 输出: "cbad"
     *
     * 解释:
     * S中出现了字符 "a", "b", "c", 所以 "a", "b", "c" 的顺序应该是 "c", "b", "a".
     * 由于 "d" 没有在S中出现, 它可以放在T的任意位置. "dcba", "cdba", "cbda" 都是合法的输出。
     * 注意:
     * S的最大长度为26，其中没有重复的字符。
     * T的最大长度为200。
     * S和T只包含小写字符。
     * @param S
     * @param T
     * @return
     */
    public String customSortString(String S, String T) {
        int m = S.length(),n = T.length();
        int[] map = new int[26];      //记录T中的字符个数
        int[] map1 = new int[26];   //记录S是否包含某个字符
        for(int i = 0;i < m;i++){
            char c = S.charAt(i);
            map1[c-'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < n;i++){
            char c = T.charAt(i);
            int t = c -'a';
            map[t]++;
            if(map1[t] == 0) sb.append(c);            //如果S中不包含该字符，直接加上
        }
        //按照S中的顺序，包含几次加几次
        for(int i = 0;i < m;i++){
            char c = S.charAt(i);
            int cnt = map[c-'a'];
            if(cnt > 0){
                for(int k = 0;k < cnt;k++)
                    sb.append(c);
            }
        }
        return sb.toString();
    }
    //endregion

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
    private int uniqueMorseRepresentations(String[] words) {
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

    //region 890. 查找和替换模式  2019/10/6  双映射处理
    /**
     * 你有一个单词列表 words 和一个模式  
     * pattern，你想知道 words 中的哪些单词与模式匹配。
     * 如果存在字母的排列 p ，使得将模式中的每个字母 x 替换为 p(x) 之后，
     * 我们就得到了所需的单词，那么单词与模式是匹配的。
     * （回想一下，字母的排列是从字母到字母的双射：
     * 每个字母映射到另一个字母，没有两个字母映射到同一个字母。）
     * 返回 words 中与给定模式匹配的单词列表。
     * 你可以按任何顺序返回答案。
     * 示例：
     * 输入：words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
     * 输出：["mee","aqq"]
     * 解释：
     * "mee" 与模式匹配，因为存在排列 {a -> m, b -> e, ...}。
     * "ccc" 与模式不匹配，因为 {a -> c, b -> c, ...} 不是排列。
     * 因为 a 和 b 映射到同一个字母。
     * 提示：
     * 1 <= words.length <= 50
     * 1 <= pattern.length = words[i].length <= 20
     * @param words
     * @param pattern
     * @return
     */
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> ans=new ArrayList<>();
        for(String word:words){
            if(isMatch(word,pattern)){
                ans.add(word);
            }
        }
        return ans;
    }
    private boolean isMatch(String word,String pattern){
        Map<Character,Character> m1=new HashMap<>();
        Map<Character,Character> m2=new HashMap<>();
        for(int i=0;i<word.length();i++){
            char w=word.charAt(i);
            char p=pattern.charAt(i);
            if(!m1.containsKey(w)){
                m1.put(w,p);
            }
            if (!m2.containsKey(p)) {
                m2.put(p,w);
            }
            if(m1.get(w)!=p||m2.get(p)!=w){
                return false;
            }
        }
        return true;
    }

    /**
     * 单映射方法
     * @param words
     * @param pattern
     * @return
     */
    public List<String> findAndReplacePattern1(String[] words, String pattern) {
        List<String> ans = new ArrayList();
        for (String word: words)
            if (match(word, pattern))
                ans.add(word);
        return ans;
    }

    private boolean match(String word, String pattern) {
        Map<Character, Character> M = new HashMap();
        for (int i = 0; i < word.length(); ++i) {
            char w = word.charAt(i);
            char p = pattern.charAt(i);
            if (!M.containsKey(w)) M.put(w, p);
            if (M.get(w) != p) return false;
        }

        boolean[] seen = new boolean[26];
        for (char p: M.values()) {
            if (seen[p - 'a']) return false;
            seen[p - 'a'] = true;
        }
        return true;
    }
    //endregion

    //region 929. 独特的电子邮件地址  2019/10/6  hashset
    /**
     * 每封电子邮件都由一个本地名称和一个域名组成，以 @ 符号分隔。
     * 例如，在 alice@leetcode.com中， alice 是本地名称，而 leetcode.com 是域名。
     * 除了小写字母，这些电子邮件还可能包含 '.' 或 '+'。
     * 如果在电子邮件地址的本地名称部分中的某些字符之间添加句点（'.'），
     * 则发往那里的邮件将会转发到本地名称中没有点的同一地址。
     * 例如，"alice.z@leetcode.com” 和 “alicez@leetcode.com” 
     * 会转发到同一电子邮件地址。 （请注意，此规则不适用于域名。）
     * 如果在本地名称中添加加号（'+'），则会忽略第一个加号后面的所有内容。
     * 这允许过滤某些电子邮件，例如 m.y+name@email.com 将转发到 my@email.com。
     * （同样，此规则不适用于域名。）
     * 可以同时使用这两个规则。
     * 给定电子邮件列表 emails，我们会向列表中的每个地址发送一封电子邮件。
     * 实际收到邮件的不同地址有多少？
     *
     * 示例：
     * 输入：["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
     * 输出：2
     * 解释：实际收到邮件的是 "testemail@leetcode.com" 和 "testemail@lee.tcode.com"。
     * 提示：
     * 1 <= emails[i].length <= 100
     * 1 <= emails.length <= 100
     * 每封 emails[i] 都包含有且仅有一个 '@' 字符。
     * @param emails
     * @return
     */
    private int numUniqueEmails(String[] emails) {
        HashSet<String> hashSet=new HashSet<>();
        StringBuilder newString;
        for(String email: emails){
            String[] fragement=email.split("@");
            newString =new StringBuilder();
            for(int i=0;i<fragement[0].length();i++){
                if (fragement[0].charAt(i) == '+') {
                    break;
                } else if (fragement[0].charAt(i) == '.') {
                    continue;
                }else {
                    newString.append(fragement[0].charAt(i));
                }
            }
            newString.append('@');
            newString.append(fragement[1]);
            hashSet.add(newString.toString());
        }
        return hashSet.size();
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
        String s=(new TargetString()).customSortString("cba","abcd");
    }
}
