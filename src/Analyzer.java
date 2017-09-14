import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class Analyzer {

	public static List<Sentence> readFile(String filename) {
		FileReader file;
		LinkedList<Sentence> l = new LinkedList<Sentence>();

		try {
			if (filename == null) {
				return l;
			}
			file = new FileReader(filename);

			BufferedReader reader = new BufferedReader(file);
			String line = null;
			ArrayList<String> check = new ArrayList<String>();
			check.add("-2 ");
			check.add("-1 ");
			check.add("0 ");
			check.add("1 ");
			check.add("2 ");

			while ((line = reader.readLine()) != null) {
				
				if (line.length() > 2) {
					if (check.contains(line.substring(0, 2)) || check.contains(line.substring(0, 3))) {
						Scanner s = new Scanner(line);
						int num = s.nextInt();
						String text;
						if (num < 0) {
							text = line.substring(3);
						} else {
							text = line.substring(2);
						}
						l.add(new Sentence(num, text));
						s.close();
						

					}
				}
			}
			reader.close();
			return l;
		} catch (FileNotFoundException e) {

			return l;

		} catch (IOException e) {
			return l;
		}

		

	}

	
	public static Set<Word> allWords(List<Sentence> sentences) {

		Set<Word> hash = new HashSet<Word>();
		if (sentences == null || sentences.isEmpty()) {
			return hash;
		}
		for (Sentence x : sentences) {
			
             if(x!=null){
			
			String [] s =x.text.toLowerCase().split("\\s");
			
			for (String l : s) {
				if (Character.isLetter(l.charAt(0))) {
					Word w = new Word(l);
					w.increaseTotal(x.score);
					
					for (Word k : hash) {
						
						if (w.text == k.text) {
							k.increaseTotal(x.score);
						}
						
					}
					hash.add(w);

				}

			}

		}
		}
		return hash; 
	}


	public static Map<String, Double> calculateScores(Set<Word> words) {
     HashMap<String, Double> hm = new HashMap<>();
     if (words == null || words.isEmpty()) {
			return hm;
		}
     for(Word x : words){
    	 if(x!=null){
    		 hm.put(x.getText(), x.calculateScore());
    	 } 
     }

		return hm; 

	}

	
	public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {
		 if (wordScores == null || wordScores.isEmpty() || sentence==null || sentence.isEmpty()) {
				return 0;
			}
		double final_score = 0;
		 String [] s =sentence.toLowerCase().split("\\s");
		System.out.println(wordScores.get("this"));
		System.out.println(wordScores.get("is"));
		System.out.println(wordScores.get("not"));
		System.out.println(wordScores.get("me"));
		 for (String l : s) {
			 
				if (Character.isLetter(l.charAt(0))) {
					//System.out.println(l);
					Set<String> ss = wordScores.keySet();
					if(ss.contains(l)){
						final_score += wordScores.get(l);
						System.out.println("yes "+final_score);	
					}
					else{
						final_score += 0;
						System.out.println("no "+final_score);	
					}
					
					
				}
		 }
		 
		return final_score ;

	}

	
	public static void main(String[] args) {
		
		System.out.print("Please enter a sentence: ");
		Scanner in = new Scanner(System.in);
		String sentence = in.nextLine();
		in.close();
		
		List<Sentence> sentences = Analyzer.readFile("test2.txt");
		Set<Word> words = Analyzer.allWords(sentences);
		Map<String, Double> wordScores = Analyzer.calculateScores(words);
		double score = Analyzer.calculateSentenceScore(wordScores, sentence);
		System.out.println("The sentiment score is " + score);
	}
}
