package Leetcode;


import java.util.ArrayList;
import java.util.List;

public class Trie {

    public static void main(String[] args) {
        String word = "ant";
        Trie trie = new Trie();
        trie.addWord(word);

        word = "apt";
        trie.addWord(word);

        word = "dog";
        trie.addWord(word);

        word = "diet";
        trie.addWord(word);

        word = "dogs";
        trie.addWord(word);
        word = "dots";
        trie.addWord(word);

        System.out.println(trie.searchWord("an"));
        word = "an";
        trie.addWord(word);
        System.out.println(trie.searchWord("an"));
        System.out.println(trie.searchWord("dogs"));
        System.out.println(trie.searchWord("ant"));
        System.out.println(trie.searchWord("sant"));
    }
    Node root = new Node();
    void addWord(String str){
        char[] chars = str.toCharArray();
        Node node = root;
        for (char ch : chars) {
            int index = ch - 'a';
            if (node.children[index] == null || node.children[index].value == '\0') {
                Node childNode = new Node(ch);
                node.children[index] = childNode;
            }
            node = node.children[index];
        }
        node.isWordEnd = true;
    }

    boolean searchWord(String str){
        char[] chars = str.toCharArray();
        Node node = root;
        for(char ch: chars){
            int index = ch - 'a';
            if(node.children[index] == null || node.children[index].value == '\0') {
                return false;
            }
            node = node.children[index];
        }
        return (node.isWordEnd);
    }

    List<String> wordsWithPrefix(){
        List<String> result = new ArrayList<>();

        return result;
    }

    static class Node{
        Node[] children = new Node[26];
        boolean isWordEnd;
        char value;
        Node(char ch){
            this.value = ch;
        }
        Node(){

        }
    }

}
