import java.util.Deque;
import java.util.LinkedList;

public class palindrome_linked_list {
    public static boolean isPalindrome(ListNode head) {
        Deque<Integer> stack = new LinkedList<>();
        ListNode temp = head;
        while (temp != null) {
            stack.addFirst(temp.value);
            temp = temp.next;
        }
        while (head != null) {
            if (stack.removeFirst() != head.value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }
}

/*
Explanation
Use a stack, push all element onto the stack, then pop it and check it with the current value

Time: O(n)
Space: O(n)

Write the test case in LinkedListTests.java
*/