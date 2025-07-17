//Replace Words (https://leetcode.com/problems/replace-words/)

// Time Complexity : O(n*k)
// Space Complexity : O(n*k)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach
// we are storing each word from the dictionary in a trienode ds
//for every word in the sentence see if its root is present in the trienode
//replace the work with shortest root.

class Solution {
    class TrieNode{
        boolean isEnd;
        TrieNode[] children;
        public TrieNode(){
            this.children = new TrieNode[26];
        }
    }
    public void insert(TrieNode root, String word){
        TrieNode current = root;
        for(int i=0;i<word.length();i++){
            char c= word.charAt(i);
            if(current.children[c-'a']==null) current.children[c-'a'] = new TrieNode();
            current=current.children[c-'a'];
        }
        current.isEnd=true;
    }
    public String replaceWords(List<String> dictionary, String sentence) {
        TrieNode root = new TrieNode();
        for(String str:  dictionary){
            insert(root,str); //space n*k time n*k
        }
        String [] strarray = sentence.split(" ");
        StringBuilder result = new StringBuilder();
        for(int j=0;j<strarray.length;j++){
            String word=strarray[j];
            if(j>0) result.append(" ");
            TrieNode current = root;
            StringBuilder replacement = new StringBuilder();
            for(int i=0; i<word.length();i++){
                char c = word.charAt(i);
                if(current.children[c-'a']==null || current.isEnd) break;
                current=current.children[c-'a'];
                replacement.append(c);
            }
            if(current.isEnd) result.append(replacement);
            else result.append(word);
        }
        return result.toString();
    }
}