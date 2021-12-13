package class16;

import java.util.HashSet;
import java.util.Stack;

/**
 * 深度优先遍历
 */
public class Code2_DFS {

    public static void dfs(Node node){
        if (node==null){
            return;
        }
        Stack<Node> stack=new Stack<>();
        HashSet<Node> set=new HashSet<>();
        stack.add(node);
        set.add(node);
        System.out.println(node.value);
        while (!stack.isEmpty()){
            Node cur=stack.pop();
            for (Node next:cur.nexts){
                if (!set.contains(next)){
                    stack.push(cur);
                    stack.push(next);
                    set.add(node);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }
}
