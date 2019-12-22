import java.util.*;

public class LeeCode {
    /**
     * 字符串的第一个唯一的子字符
     */
    public int firstUniqChar(String s) {
        Map<Character,Integer> map = new HashMap<>();
        char[]chars  = s.toCharArray();
        for(char ch : chars){
            map.put(ch,map.getOrDefault(ch,0)+1);
        }
        for(int i =0  ; i < chars.length ; i++){
            if(map.get(chars[i]) ==1)
                return i;
        }
        return -1;
    }
    public int firstUniqChar1(String s) {
        int []freq = new int[26];
        char [] chars = s.toCharArray();
        for(char ch : chars){
            freq[ch-'a']++;
        }
        for(int i = 0 ; i < chars.length ; i++){
            if(freq[chars[i] -'a'] ==1)
                return i;
        }
        return -1;
    }
    public int firstUniqChar3(String s) {
        for(int i = 0 ; i < s.length();  i++){
            char ch = s.charAt(i);
            if(s.indexOf(ch) == s.lastIndexOf(ch))
                return i;
        }
        return -1;
    }
    /**
     * 常数时间插入、删除和获取随机元素
     */
  static   class RandomizedSet{
        Map<Integer,Integer> map;
        Random random;
        List<Integer> collector;
        public RandomizedSet(){
            map = new HashMap<>();
            collector = new ArrayList<>();
            random = new Random(System.currentTimeMillis());
        }
        //当元素val不存在的时候，向几个中插入该项
        public boolean inset(int val){
            if(map.containsKey(val))
                return false;
            map.put(val,collector.size());
            collector.add(val);
            return true;
        }
        //元素存在的时候，从集合中移除该项
        public boolean remove(int val){
            if(!map.containsKey(val))
                return false;
            //获取当前元素的索引
            Integer index = map.get(val);
            Integer last = collector.get(collector.size()-1);

            // 使用最后一个元素替换当前的元素
            collector.set(index,last);
            map.put(last,index);
            collector.remove(val);
            //删除最后一个元素
            map.remove(val);
            return true;
        }
        //随机返回现有集合中的一项
        public int getRandom(){
            return collector.get(random.nextInt(collector.size()));
        }
    }

    public static void main(String[] args) {
        LeeCode l = new LeeCode();
        System.out.println(l.firstUniqChar("leecode"));

        RandomizedSet randomizedSet = new RandomizedSet();
        System.out.println(randomizedSet.inset(1));
        System.out.println(randomizedSet.inset(2));
        System.out.println(randomizedSet.inset(3));
        System.out.println(randomizedSet.inset(4));
        System.out.println(randomizedSet.remove(2));
        System.out.println(randomizedSet.getRandom());
        System.out.println(randomizedSet.getRandom());

    }
}
