import java.util.concurrent.DelayQueue;

public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        LinkedListDeque res = new LinkedListDeque();
        int len = word.length();
        for (int i= 0; i<len; i++){
            char nc = word.charAt(i);
            res.addLast(nc);
        }
        return res;
    }

    public boolean isPalindrome(String word){
        Deque<Character> deq = wordToDeque(word);
        while (deq.size()>1){
            char first = deq.removeFirst();
            char last = deq.removeLast();
            if (first != last){
                return false;
            }
        }
        return true;
    }


    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> deq = wordToDeque(word);
        while (deq.size()>1){
            char first = deq.removeFirst();
            char last = deq.removeLast();
            if(! cc.equalChars(first,last)){
                return false;
            }
        }
        return true;
    }



}
