package dachang.class1;

import java.io.File;
import java.util.Stack;

public class Code02_CountFiles {

    /**
     * 深度优先遍历
     */
    public static int getFileNumber(String folderPath){
        File root =new File(folderPath);
        if (!root.isDirectory()&&!root.isFile()){
            return 0;
        }
        if (root.isFile()){
            return 1;
        }
        Stack<File> stack = new Stack<>();
        stack.add(root);
        int files=0;
        while (!stack.isEmpty()){
            File file = stack.pop();
            File[] list = file.listFiles();
            for (File son : list) {
                if (son.isFile()){
                    files++;
                }else {
                    stack.push(son);
                }
            }
        }
        return files;
    }

    public static void main(String[] args) {
        String path="D:\\文档\\算法课资料\\算法课资料\\ppt\\大厂刷题班";
        System.out.println(getFileNumber(path));
    }
}
