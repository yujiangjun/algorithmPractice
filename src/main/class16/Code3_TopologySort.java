package class16;

import java.util.*;

/**
 * 拓扑排序
 */
public class Code3_TopologySort {

    public static List<Node> sortedTopology(Graph graph){
        HashMap<Node,Integer> inMap=new HashMap<>();
        Queue<Node> zeroInQueue=new LinkedList<>();
        for (Node node:graph.nodes.values()){
            inMap.put(node,node.in);
            if (node.in==0){
                zeroInQueue.add(node);
            }
        }
        List<Node> result=new ArrayList<>();
        while (!zeroInQueue.isEmpty()){
            Node cur=zeroInQueue.poll();
            result.add(cur);
            for (Node next:cur.nexts){
                inMap.put(next,inMap.get(next)-1);
                if (inMap.get(next)==0){
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }
}
