//Longest Word in Dictionary(https://leetcode.com/problems/longest-word-in-dictionary/)

// Time Complexity : O(n*k)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach
// we build the trie data structure for every word given then on it use dfs with backtracking and keeping track of maxString
// or use bfs by reverse adding children if isEnd is true;

//dfs
class Solution {
    class TrieNode {
        boolean isEnd;
        TrieNode children [];
        public TrieNode(){
            this.children=new TrieNode[26];
        }
    }
    public void insert(TrieNode root, String word){
        TrieNode current = root;
        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if(current.children[c-'a']==null) current.children[c-'a'] = new TrieNode();
            current=current.children[c-'a'];
        }
        current.isEnd=true;
    }
    private String maxString;
    public String longestWord(String[] words) {
        TrieNode root=new TrieNode();
        for(String word: words){
            insert(root,word); //n*k
        }
        this.maxString="";
        //seach for longest
        backtrack(root, new StringBuilder());
        return maxString;
    }
    private void backtrack(TrieNode current, StringBuilder path ){
        //base
        if(path.length()>maxString.length()) maxString=path.toString();

        //logic
        for(int i=0;i<26;i++){
            if(current.children[i]!=null && current.children[i].isEnd){
                int pathLength=path.length();
                //action
                path.append((char)(i+'a'));
                //recurse
                backtrack(current.children[i], path);
                //backtrack
                path.setLength(pathLength);
            }
        }
    }
}

//bfs
class Solution {
    class TrieNode {
        boolean isEnd;
        TrieNode children [];
        public TrieNode(){
            this.children=new TrieNode[26];
        }
    }
    public void insert(TrieNode root, String word){
        TrieNode current = root;
        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if(current.children[c-'a']==null) current.children[c-'a'] = new TrieNode();
            current=current.children[c-'a'];
        }
        current.isEnd=true;
    }
    public String longestWord(String[] words) {
        TrieNode root=new TrieNode();
        for(String word: words){
            insert(root,word); //n*k
        }
        String str = "";
        Queue<TrieNode> q= new LinkedList<>();
        Queue<String> word = new LinkedList<>();
        q.add(root);
        word.add("");
        while(!q.isEmpty()){
            TrieNode current = q.poll();
            str=word.poll();
            for(int i=25;i>=0;i--){
                if(current.children[i]!=null && current.children[i].isEnd){
                    q.add(current.children[i]);
                    String newString = str+ (char) (i+'a');
                    word.add(newString);
                }
            }
        }
        return str;
    }
}