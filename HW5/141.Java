/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
 //定义一个链表
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode fast=head;//定义一个速度快的链表指针
        ListNode slow=head;//定义一个速度慢的链表指针
        //if(fast==null)
        //return false;
        while(fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;//两倍的速度来走
            if(fast==slow)
               return true;
        }
        return false;
    }
}