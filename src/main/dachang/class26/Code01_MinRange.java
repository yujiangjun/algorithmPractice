package dachang.class26;

import java.util.*;

public class Code01_MinRange {

    private static class Node {
        private int value;
        private int arrIndex;
        private int valueIndex;

        public Node(int value, int arrIndex, int valueIndex) {
            this.value = value;
            this.arrIndex = arrIndex;
            this.valueIndex = valueIndex;
        }
    }

    public static class NodeComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.value != o2.value ? o1.value - o2.value : o1.arrIndex - o2.arrIndex;
        }
    }

    public static int[] smallestRange(List<List<Integer>> nums) {
        int n = nums.size();
        TreeSet<Node> set = new TreeSet<>(new NodeComparator());
        for (int i = 0; i < nums.size(); i++) {
            set.add(new Node(nums.get(i).get(0), i, 0));
        }
        int min = Integer.MAX_VALUE;
        int a = 0, b = 0;
        while (set.size() == n) {
            Node last = set.last();
            Node first = set.pollFirst();
            int cur = last.value - first.value;
            if (cur < min) {
                a = first.value;
                b = last.value;
                min=cur;
            }
            if (first.valueIndex < nums.get(first.arrIndex).size() - 1) {
                set.add(new Node(nums.get(first.arrIndex).get(first.valueIndex + 1), first.arrIndex, first.valueIndex + 1));
            }
        }
        return new int[]{a, b};
    }

    public static void main(String[] args) {
        Integer[] s1={1,1,1};
        Integer[] s2={2,2,2};
        Integer[] s3={3,3,3};

        List<List<Integer>> data = new ArrayList<>();
        data.add(Arrays.asList(s1));
        data.add(Arrays.asList(s2));
        data.add(Arrays.asList(s3));
        System.out.println(Arrays.toString(smallestRange(data)));
    }
}
