package com.example.myapplication;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 * This class allows users to read a file containing a list of whitespace delimited words
 * and returns all the words of a specified length. All words will be made lower case, and each 
 * word can only appear once in the dictionary. 
 * @author Josiah Cherbony
 * @author Logan Beaver
 * @version Fall 2021 
 */
public class DictionaryBuilder{
	
	/**The scanner that will read the words from a specified file*/
	private BufferedReader reader;

	/**The constructor which attaches the inputed file to the scanner*/
	public DictionaryBuilder(InputStream file) {
		InputStreamReader inputReader = new InputStreamReader(file);
		reader = new BufferedReader(inputReader);
	}


	/**Gets all the words of a specified length in the dictionary and returns them in
	 * a collection. 
	 * @param length The length of the words that should be returned.
	 * @return dictionary. A collection containing all the words of a specified length 
	 * in the dictionary.
	 */
	public Collection<String> getWordsOfLength(int length) throws IOException {
		String str;
		HashSet<String> dictionary = new HashSet<>();

		while((str = reader.readLine()) != null) {//While the scanner has a next word
			String next = str;//We retrieve that word
			if(next.length() == length) {//If the word from the scanner has the same length as our inputed length
				next = next.toLowerCase();//It's converted to lower case
				if(!dictionary.contains(next)) {//If the dictionary doesn't contain the word 
					dictionary.add(next);//The word is added to the dictionary
				}
			}
		}
		return dictionary;
	}




}
