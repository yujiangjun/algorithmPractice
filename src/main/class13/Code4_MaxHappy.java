package class13;

import java.util.ArrayList;
import java.util.List;

/**
 * 员工信息的定义如下:
 * class Employee {
 *     public int happy; // 这名员工可以带来的快乐值
 *     List<Employee> subordinates; // 这名员工有哪些直接下级
 * }
 * 派对的最大快乐值
 *  公司的每个员工都符合 Employee 类的描述。整个公司的人员结构可以看作是一棵标准的、
 *  没有环的多叉树。树的头节点是公司唯一的老板。
 *  除老板之外的每个员工都有唯一的直接上级。 叶节点是没有任何下属的基层员工(subordinates列表为空)，
 *  除基层员工外，每个员工都有一个或多个直接下级。
 */
public class Code4_MaxHappy {

    public static class Employee{
        public int happy;
        public List<Employee> nexts;

        public Employee(int happy) {
            this.happy = happy;
            nexts=new ArrayList<>();
        }
    }

    public static int maxHappy1(Employee boss){
        if (boss==null){
            return 0;
        }
        return process1(boss,false);
    }

    /**
     * 当前来到的节点叫cur，
     *up表示cur的上级是否来，
     *该函数含义：
     *如果up为true，表示在cur上级已经确定来，的情况下，cur整棵树能够提供最大的快乐值是多少？
     * 如果up为false，表示在cur上级已经确定不来，的情况下，cur整棵树能够提供最大的快乐值是多少？
     * @param cur
     * @param up
     * @return
     */
    public static int process1(Employee cur,boolean up){
        if (up){
            int ans=0;
            for (Employee next:cur.nexts){
                ans+=process1(next,false);
            }
            return ans;
        }else {
            int p1=cur.happy;
            int p2=0;
            for (Employee next: cur.nexts){
                p1+=process1(next,true);
                p2+=process1(next,false);
            }
            return Math.max(p1,p2);
        }
    }

    public static int maxHappy2(Employee head){
        Info allInfo=process(head);
        return Math.max(allInfo.no,allInfo.yes);
    }

    public static class Info{
        public int no; //head不来的happy值
        public int yes;//head来的happy值

        public Info(int no, int yes) {
            this.no = no;
            this.yes = yes;
        }
    }

    public static Info process(Employee x){
        if (x==null){
            return new Info(0,0);
        }
        int no=0;
        int yes=x.happy;
        for (Employee next:x.nexts){
            Info nextInfo=process(next);
            no+=Math.max(nextInfo.no, nextInfo.yes);
            yes+= nextInfo.no;
        }
        return new Info(no,yes);
    }

    // for test
    public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
        if (Math.random() < 0.02) {
            return null;
        }
        Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
        genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
        return boss;
    }

    // for test
    public static void genarateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
        if (level > maxLevel) {
            return;
        }
        int nextsSize = (int) (Math.random() * (maxNexts + 1));
        for (int i = 0; i < nextsSize; i++) {
            Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
            e.nexts.add(next);
            genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
        }
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxNexts = 7;
        int maxHappy = 100;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);
            if (maxHappy1(boss) != maxHappy2(boss)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
