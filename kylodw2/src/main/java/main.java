import java.util.ArrayList;
import java.util.Random;

public class main {
    public static void main(String[] args) {
//        int[] nums = {5, 3, 6, 2, 4, 8};
//        BST<Integer> integerBST = new BST<>();
//        for (int i = 0; i < nums.length; i++) {
//            integerBST.add(nums[i]);
//        }
//
//        integerBST.perOrder();
//        System.out.println("");
//        integerBST.inOrder();
//        System.out.println("");
//        integerBST.perOrderNR();
//        System.out.println("层序遍历");
//        integerBST.levalOrder();
        BST<Integer> integerBST = new BST<>();
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            integerBST.add(random.nextInt(10000));
        }
        ArrayList<Integer> integers = new ArrayList<>();
        while (!integerBST.isEmpty()) {
            integers.add(integerBST.removeMin());
        }
        System.out.println(integers);
    }
}
