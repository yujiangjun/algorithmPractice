package class16;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Dijkstra算法
 * 1）Dijkstra算法必须指定一个源点
 * 2）生成一个源点到各个点的最小距离表，一开始只有一条记录，即原点到自己的最小距离为0，源点到其他所有点的最小距离都为正无穷大
 * 3）从距离表中拿出没拿过记录里的最小记录，通过这个点发出的边，更新源点到各个点的最小距离表，不断重复这一步
 * 4）源点到所有的点记录如果都被拿过一遍，过程停止，最小距离表得到了
 */
public class Code6_Dijkstra {

    public static HashMap<Node,Integer> dijkstra1(Node from){
        HashMap<Node,Integer> distanceMap=new HashMap<>();
        distanceMap.put(from,0);

        HashSet<Node> selectNodes=new HashSet<>();
        Node minNode=getMinDistanceUnselectedNode(distanceMap,selectNodes);
        while (minNode!=null){
            int distance=distanceMap.get(minNode);
            for (Edge edge:minNode.edges){
                Node toNode=edge.to;
                if (!distanceMap.containsKey(toNode)){
                    distanceMap.put(toNode,distance+edge.weight);
                }else {
                    distanceMap.put(edge.to,Math.min(distanceMap.get(toNode),distance+edge.weight));
                }
            }
            selectNodes.add(minNode);
            minNode=getMinDistanceUnselectedNode(distanceMap,selectNodes);
        }
        return distanceMap;
    }

    public static Node getMinDistanceUnselectedNode(HashMap<Node,Integer> distanceMap,HashSet<Node> touchedNodes){
        Node minNode=null;
        int minDistance=Integer.MAX_VALUE;
        for (Map.Entry<Node,Integer> entry: distanceMap.entrySet()){
            Node node=entry.getKey();
            int distance=entry.getValue();
            if (!touchedNodes.contains(node)&&distance<minDistance){
                minNode=node;
                minDistance=distance;
            }
        }
        return minNode;
    }

    public static class NodeRecord{
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static class NodeHeap{
        private Node[] nodes;
        private HashMap<Node,Integer> headIndexMap;
        private HashMap<Node,Integer> distanceMap;
        private int size;

        public NodeHeap(int size) {
            nodes=new Node[size];
            headIndexMap=new HashMap<>();
            distanceMap=new HashMap<>();
            this.size = 0;
        }

        public boolean isEmpty(){
            return size==0;
        }

        public void addOrUpdateOrIgnore(Node node,int distance){
            if (inHeadp(node)){
                distanceMap.put(node,Math.min(distanceMap.get(node),distance));
                insertHeapify(node,headIndexMap.get(node));
            }
            if (!isEntered(node)){
                nodes[size]=node;
                headIndexMap.put(node,size);
                distanceMap.put(node,distance);
                insertHeapify(node,size++);
            }
        }
        public NodeRecord pop(){
            NodeRecord nodeRecord=new NodeRecord(nodes[0],distanceMap.get(nodes[0]));
            swap(0,size-1);
            headIndexMap.put(nodes[size-1],-1);
            distanceMap.remove(nodes[size-1]);
            nodes[size-1]=null;
            heapify(0,--size);
            return nodeRecord;
        }

        public void insertHeapify(Node node,int index){
            while (distanceMap.get(nodes[index])<distanceMap.get(nodes[(index-1)/2])){
                swap(index,(index-1)/2);
            }
        }

        private void heapify(int index,int size){
            int left=index*2+1;
            while (left<size){
                int smallest=left+1<size&&distanceMap.get(nodes[left+1])<distanceMap.get(nodes[left])?left+1:left;
                smallest=distanceMap.get(nodes[smallest])<distanceMap.get(nodes[index])?smallest:index;
                if (smallest==index){
                    break;
                }
                swap(smallest,index);
                index=smallest;
                left=index*2+1;
            }
        }

        private boolean isEntered(Node node){
            return headIndexMap.containsKey(node);
        }
        private boolean inHeadp(Node node){
            return isEntered(node)&&headIndexMap.get(node)!=-1;
        }

        private void swap(int index1,int index2){
            headIndexMap.put(nodes[index1],index2);
            headIndexMap.put(nodes[index2],index1);
            Node tmp=nodes[index1];
            nodes[index1]=nodes[index2];
            nodes[index2]=tmp;
        }
    }

    public static HashMap<Node,Integer> dijkstra2(Node head,int size){
        NodeHeap nodeHeap=new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(head,0);
        HashMap<Node,Integer> result=new HashMap<>();
        while (!nodeHeap.isEmpty()){
            NodeRecord record=nodeHeap.pop();
            Node cur=record.node;
            int distance = record.distance;
            for (Edge edge:cur.edges){
                nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight+distance);
            }
            result.put(cur,distance);
        }
        return result;
    }
}
