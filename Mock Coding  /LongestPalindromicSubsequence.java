
/*
Given a string 'S', find the length of the Longest Palindromic Subsequence in it.
The Longest Palindromic Subsequence (LPS) is the problem of finding a maxium-length subsequence of a given string that is also a Palindrome.
Input: S = “BBABCBCAB”
Output: 7
Explanation: As “BABCBAB” is the longest palindromic subsequence in it. “BBBBB” and “BBCBB” are also palindromic subsequences of the given sequence, but not the longest ones.
*/

/*
Time Complexity: O(n): in the while loop of minDeletions method, two pointers iterate half of all elements; in the countDeletions method, two pointers iterate half of elements as well, in this process, time complexity is O(n/2). Therefore, the entire time complexity is O(n)
Space Complexity: O(1): new memory location for Integer variable 'deletions' and two pointers.. 
*/
class Solution {
    public static void main(String[] args){
        
        String str1 = "BBABCBCAB";
        int res = minDeletions(str1);
        System.out.println(res);
        
        String str2 = "aebcbda";
        int ans = minDeletions(str2);
        System.out.println(ans);
    }
    
    public static int minDeletions(String s) {
        int len = s.length();
        int left = 0, right = len - 1;
        int deletions = 0;

        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                int deleteLeft = countDeletions(s, left + 1, right);
                int deleteRight = countDeletions(s, left, right - 1);
                deletions += Math.min(deleteLeft, deleteRight);
                break;
            }
        }
        
        return len - deletions;
    }
    
    public static int countDeletions(String s, int left, int right) {
        int deletions = 0;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                deletions++;
            }
            left++;
            right--;
        }
        return deletions;
    }

}
