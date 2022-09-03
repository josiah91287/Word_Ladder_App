package com.example.myapplication;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;


/**
 * Class that created a word-ladder between two words (if possible)
 * @author Dr. Dalton
 * @author Dr. Kreahling
 * @author Josiah Cherbony
 * @author Logan Beaver
 * @version Fall 2021
 */
public class LadderBuilder {

   /**The dictionary of words used to build the word ladder.*/
   private Collection<String> dictionary;

   /**The words already used in the word ladder*/
   private Collection<String> usedWords;

   /**The final wordLadder we will return*/
   private Deque<String> wordLadder = null;

   /**A constructor for the LadderBuilder class that requires a dictionary to build from*/
   public LadderBuilder(Collection<String> dictionary) {
       this.dictionary = dictionary;
       this.usedWords = new HashSet<>();
   }

   /**
    * This method starts by finding all the words one letter away from our starting word. For
    * each word found, a stack is created with our starting word and the new word which is pushed onto
    * a queue of stacks. If our end word is not at the top of one of these stacks we repeat the process
    * until the top of one of the stacks matches our end word or the stack is empty (meaning there is no
    * ladder between those two words).
    * @param start The starting word in the ladder.
    * @param end The end word in the ladder.
    * @return wordLadder. Returns the wordLadder if it exist, and null if not.
    */
   public Deque<String> buildLadder(String start, String end){
       if(start.length() != end.length()) {
           throw new IllegalArgumentException();
       }

       Deque<Deque<String>> queue = new Deque<>();//The queue that holds all the stacks
       Deque<String> stack = new Deque<>();//The initial stack that is added to the queue
       boolean running = true;
       stack.addFirst(start);//The first word is added to the first stack
       queue.addFirst(stack);//The first stack is added to the queue
       usedWords.add(start);//Our starting word is added to the list of used words

       while(queue.size() != 0 && running == true) {//While queue isn't empty and running is equal to true

           int size = queue.size();//Gets the current size of the queue (i.e. how may stacks are in it).

           outerloop:
           for(int index = 0; index < size; index++) {//For each stack in the queue
               Deque<String> innerStack = queue.removeFirst();//We remove the stack at the end of the queue
               String topWord = innerStack.peekLast();//We look at the top word in the stack
               LinkedList<String> listOfOneAway = getWordsOneAway(topWord);//We get a list of words one character away
               //for the top word

               for(String word: listOfOneAway) {//For each word in listOfOneAway
                   Deque<String> stackClone = innerStack.clone();//Makes a clone of our current stack
                   stackClone.addLast(word);//Adds the word from listOfOneAway to the top of the stack
                   usedWords.add(word);//The top word is added to the list of used words

                   if(word.equals(end)) {//If the word one away is equal to the end word
                       wordLadder = stackClone;//The word ladder we return is the current stackClone
                       running = false;//Running is set to false to break out of the while loop
                       break outerloop;
                   }
                   queue.addLast(stackClone);//Adds the new stack to the queue
               }
           }
       }
       return wordLadder;
    }

   /**
    * Returns all the words in the dictionary that are one letter away from
    * the inputed word.
    * @param word The target word.
    * @return list. A list of all words one letter away from out inputed word.
    */
   private LinkedList<String> getWordsOneAway(String word){
       Iterator<String> it = dictionary.iterator();//An iterator of the dictionary
       LinkedList<String> list = new LinkedList<>();

       while(it.hasNext()) {//While the dictionary has words
           String currentWord = it.next();//Get the next word
           //The word from the dictionary can't be contained in our list of used words and must be the same
           //length as our target word
           if(!usedWords.contains(currentWord) && currentWord.length() == word.length()) {
               if(isOneAway(word, currentWord) == true) {//If the word is one character away
                  list.add(currentWord);//We add it to our list of words one character away
               }
           }
       }
       return list;
   }

   /**
    * Returns whether or not the starting word is one character away from the currentWord
    * @param startingWord The word we're comparing against current word.
    * @param currentWord A word compared against the startingWord.
    * @return isOneLetterAway. True if startingWord is one character away from
    * currentWord, false if not.
    */
   private boolean isOneAway(String startingWord, String currentWord) {
       int differences = 0;//The number of different characters between the words
       boolean isOneLetterAway = true;

           for(int index = 0; index < startingWord.length(); index++) {//For each character in the starting word
        	 //If the word's character at 'index' does not equal the currentWords character at 'index'
               if(startingWord.charAt(index) != currentWord.charAt(index)) {
                   differences++;//A difference is added.
                   if(differences > 1) {//If the number of different characters is more than one
                       isOneLetterAway = false;//The words cannot be one character away
                       break;//Breaks out of the for loop
                   }
               }
           }

       return isOneLetterAway;
   }


}

