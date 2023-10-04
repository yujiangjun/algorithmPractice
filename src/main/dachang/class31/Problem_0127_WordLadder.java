package dachang.class31;

import java.util.*;

public class Problem_0127_WordLadder {

    public static int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        wordList.add(beginWord);
        Map<String, ArrayList<String>> nexts = getNexts(wordList);
        Map<String, Integer> distanceMap = new HashMap<>();
        distanceMap.put(beginWord, 1);
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        Set<String> set = new HashSet<>();
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            Integer distance = distanceMap.get(cur);
            for (String next : nexts.get(cur)) {
                if (next.equals(endWord)) {
                    return distance + 1;
                }
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                    distanceMap.put(next, distance + 1);
                }
            }
        }
        return 0;
    }

    public static Map<String, ArrayList<String>> getNexts(List<String> wordList) {
        HashSet<String> dict = new HashSet<>(wordList);
        Map<String, ArrayList<String>> ans = new HashMap<>();
        for (String word : wordList) {
            ans.put(word, getNext(word, dict));
        }
        return ans;
    }

    public static ArrayList<String> getNext(String word, HashSet<String> dict) {
        ArrayList<String> ans = new ArrayList<>();
        char[] str = word.toCharArray();
        for (int c = 0; c < str.length; c++) {
            for (char i = 'a'; i <= 'z'; i++) {
                if (i == str[c]) {
                    continue;
                }
                char t = str[c];
                str[c] = i;
                if (dict.contains(String.valueOf(str))) {
                    ans.add(String.valueOf(str));
                }
                str[c] = t;
            }
        }
        return ans;
    }

    public static int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) {
            return 0;
        }
        /*
        从beginWord开始，开始发散
         */
        HashSet<String> startSet = new HashSet<>();
        /*
        从endWord开始发散
         */
        HashSet<String> endSet = new HashSet<>();
        startSet.add(beginWord);
        endSet.add(endWord);
        HashSet<String> visited = new HashSet<>();
        for (int len = 2; !startSet.isEmpty(); len++) {
            /*
            每一次循环，得到nexts,比较nexts和endSet
            谁少从谁开始向另一个方想开始找它的的next
            如果某一个next在另一方中，立即return.
            说明找个一个最短路径
             */
            HashSet<String> nexts = new HashSet<>();
            for (String cur : startSet) {
                char[] str = cur.toCharArray();
                for (int j = 0; j < str.length; j++) {
                    for (char k = 'a'; k <= 'z'; k++) {
                        if (k == str[j]) {
                            continue;
                        }
                        char t = str[j];
                        str[j] = k;
                        String next = String.valueOf(str);
                        if (endSet.contains(next)) {
                            return len;
                        }
                        if (!visited.contains(next) && dict.contains(next)) {
                            nexts.add(next);
                            visited.add(next);
                        }
                        str[j] = t;
                    }
                }
            }
            startSet = (nexts.size() < endSet.size()) ? nexts : endSet;
            endSet = (startSet == nexts) ? endSet : nexts;
        }
        return 0;
    }


    public static int ladderLength3(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) {
            return 0;
        }
        HashSet<String> startSet = new HashSet<>();
        HashSet<String> endSet = new HashSet<>();
        HashSet<String> visitSet = new HashSet<>();

        startSet.add(beginWord);
        endSet.add(endWord);
        visitSet.add(beginWord);
        visitSet.add(endWord);

        for (int len = 2; !startSet.isEmpty(); len++) {
            HashSet<String> nextMap = new HashSet<>();
            for (String cur : startSet) {
                char[] str = cur.toCharArray();
                for (int i = 0; i < str.length; i++) {
                    for (char j = 'a'; j <= 'z'; j++) {
                        if (j == str[i]) {
                            continue;
                        }
                        char t = str[i];
                        str[i] = j;
                        String next = String.valueOf(str);
                        if (endSet.contains(next)) {
                            return len;
                        }
                        if (!visitSet.contains(next) && dict.contains(next)) {
                            nextMap.add(next);
                            visitSet.add(next);
                        }
                        str[i] = t;
                    }
                }
            }
            startSet = (nextMap.size() < endSet.size()) ? nextMap : endSet;
            endSet = (startSet == nextMap) ? endSet : nextMap;
        }
        return 0;
    }

    public static void main(String[] args) {
        String begin = "hit";
        String end = "cog";
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
        System.out.println(ladderLength2(begin, end, Arrays.asList(words)));
        System.out.println(ladderLength3(begin, end, Arrays.asList(words)));
    }
}
