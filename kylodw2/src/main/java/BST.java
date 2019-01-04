import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二分搜索树有顺序性
 * 拿到前去和后即
 * floor 最小的最大
 * ceil
 *
 *
 *
 * <p>
 * rank
 * <p>
 * select
 * 每个节点维护size（select rank）
 *
 * 每个节点维护depatch 深度
 *
 * 支持重复元素的二分搜索树（小于等于  或者存储一个count字段）
 * @param <E>
 */
public class BST<E extends Comparable<E>> {
    private class Node {
        private E e;
        private Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    //    public void add(E e) {
//        if (root == null) {
//            root = new Node(e);
//            size++;
//        } else {
//            add(root, e);
//        }
//    }
//
//    /**
//     * 递归实现左右子树比较 然后插入
//     *
//     * @param node
//     * @param e
//     */
//    private void add(Node node, E e) {
//        //不存在重复元素
//        //递归终结条件
//        if (e.equals(node.e)) {
//            return;
//        } else if (e.compareTo(node.e) < 0 && node.left == null) {
//            node.left = new Node(e);
//            size++;
//        } else if (e.compareTo(node.e) > 0 && node.right == null) {
//            node.right = new Node(e);
//            size++;
//        }
//        //二叉树的左子树都要小于其父亲
//        if (e.compareTo(node.e) < 0) {
//            add(node.left, e);
//        } else {
//            add(node.right, e);
//        }
//
//    }
    //优化后的add
    public void add(E e) {
        root = add(root, e);
    }

    private Node add(Node node, E e) {
        if (node == null) { //为空进行创建节点
            size++;
            return new Node(e);
        }
        if (e.compareTo(node.e) < 0) {
            //赋值给左子树
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }
        return node;
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }

    //前序遍历

    /**
     * 1，前序遍历
     * <p>
     * 访问该节点
     * 访问该节点的左子树
     * 访问该节点的右子树
     * </p>
     * 2，中序遍历    一个性质 ：就是排出的东西是顺序的
     * <p>
     * 访问该节点的左子树
     * 访问该节点
     * 访问该节点的右子树
     * </p>
     * 3,后序遍历   用处：内存释放
     * <p>
     * 访问该节点的左子树
     * 访问该节点的右子树
     * 访问该节点
     * </p>
     * <p>
     * 5
     * /   \
     * 3     6
     * / \     \
     * 2   4     8
     */
    public void perOrder() {
        perOrder(root);
    }

    /**
     * 从根部开始遍历   深度优先遍历
     * 分为一小块一小块的   将一个大的二叉树分为多个小的二叉树 进行递归调用
     *
     * @param node
     */
    private void perOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.e);
        perOrder(node.left);
        perOrder(node.right);
    }

    /**
     * 前序遍历，非递归
     */
    public void perOrderNR() {
        Stack<Node> nodeStack = new Stack<>();
        nodeStack.push(root);
        while (!nodeStack.isEmpty()) {
            Node currentNode = nodeStack.pop();
            System.out.println(currentNode.e);
            //因为栈是先进后出，所以先添加右节点，再添加左节点
            if (currentNode.right != null) {
                nodeStack.push(currentNode.right);
            }
            if (currentNode.left != null) {
                nodeStack.push(currentNode.left);
            }
        }
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    /**
     * 层次优先遍历，  广度优先遍历   借助队列 这个数据结构（先进先出，从左到右  ）
     * 搜索策略
     */
    public void levalOrder() {
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            Node currentNode = nodes.remove();
            System.out.println(currentNode.e);
            if (currentNode.left != null) {
                ((LinkedList<Node>) nodes).add(currentNode.left);
            }
            if (currentNode.right != null) {
                ((LinkedList<Node>) nodes).add(currentNode.right);
            }
        }
    }

    public E removeMin() {
        E ret = minMum();
        root = removeMin(root);
        return ret;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;//存储右节点
            node.right = null;  //这里进行了删除操作
            size--;
            return rightNode; //返回新的根节点
        }
        node.left = removeMin(node.left);
        return node;
    }

    private E minMum() {

        return minMum(root).e;
    }

    private Node minMum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minMum(node.left);
    }
}
