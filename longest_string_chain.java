import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class longest_string_chain {
    public int longestStrChain(String[] words) {
        Map<String, Integer> dp = new HashMap<>();
        Arrays.sort(words, (a, b)->a.length() - b.length());
        int res = 0;
        for (String word : words) {
            int best = 0;
            for (int i = 0; i < word.length(); ++i) {
                String prev = word.substring(0, i) + word.substring(i + 1);
                best = Math.max(best, dp.getOrDefault(prev, 0) + 1);
            }
            dp.put(word, best);
            res = Math.max(res, best);
        }
        return res;
    }

    public int longestStrChainMethod2(String[] words) {
        Map<String, Integer> dpMap = new HashMap<>();
        Arrays.sort(words, (a,b) -> a.length() - b.length());
        
        int sequenceLength = 1;
        for (String word : words) {
            int presentLength = 1;
            for (int i = 0; i < word.length(); i++) {
                StringBuilder sb = new StringBuilder(word);
                sb.deleteCharAt(i);
                String predecessor = sb.toString();
                int previousLength = dpMap.getOrDefault(predecessor, 0);
                presentLength = Math.max(presentLength, previousLength+1);
            }
            dpMap.put(word, presentLength);
            sequenceLength = Math.max(sequenceLength, presentLength);
        }
        return sequenceLength;
    }
}


/*abstract
Explanation

substring syntax: string.substring(int startIndex, int endIndex). startIndex is inclusive and endIndex is exclusive


Hint: Instead of adding a character, try deleting a character to form a chain in reverse.
For each word in order of length, for each word2 which is word with one character removed
length[word2] = max(length[word2], leng[word]+1)

Initializa a dp map where key is the word and value is the length of the longest word sequence with the key as the end word
Sort the word list in increasing order of the world length
sequence will be 1, and it holds the length of the longest word sequence possible
iterate over the sorted list
for each word initialize present length to 1

iterate over the entire length of each word
delete character at ith position from the current word and assign the new word to the varaible predecessor
check if predecessor is present in the list or not
if predecessor is present, then assign its mapped value to previousLength, update the presentLength if previousLength+1 is greater than the present length

assign presentLength to the current word in the map dp

update sequenceLength if the longest word sequence is longer than the previously considered word
return sequenceLength

Run Down
["a","b","ba","bca","bda","bdca"]

Loop:
word is a, prev is "". Best length is 1, dp has {a : 1}
word is b, prev is "". Best length is 1, dp has {a : 1, b : 1}
word is ba, prev is a, remove b; prev is b, remove a, Best length is 2, because of a or b, dp has {a : 1, b : 1, ba : 2}
word is bca, prev is ca, ba, or bc, best length is 3, because ba = 2, dp has {a : 1, b : 1, ba : 2, bca: 3}
word is bda, prev is da, ba, or bd, best length is 3, because ba = 2, dp has {a : 1, b : 1, ba : 2, bca : 3, bda : 3}
word is bdca, prev is dca,bca,bda, bda, best length is 4, because bda = 3, dp has {a : 1, b : 1, ba : 2, bca : 3, bda : 3, bdca : 4}

Let n be the number of words in the list, and L be the maximum possible length of a word
Time: Sorting take O(n log n), two for loops the outer loop runs for O(n) time, inner loop run O(L^2) worse case. First L for inner loop, second L for creating each substring

Time: O(n log n + (n .  L^2))) = O(n (log n + L2))
Space: O(n), hash map
*/