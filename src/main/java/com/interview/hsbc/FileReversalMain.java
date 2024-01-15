package main.java.com.interview.hsbc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FileReversalMain {

	private static final String WORD_SEPARATOR = " ";
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = null;
		Scanner fileScanner = null;
		try {
			scanner = new Scanner(System.in);

			String infilename = readInputFileName(scanner);
			String outfilename = readOutputFileName(scanner);
			File inFile = new File(infilename);
			fileScanner = new Scanner(inFile);
			Stack<String> inputFileStack = readFile(fileScanner);
			List<String> reversedFileContent = reverseFileContent(inputFileStack);
			writeIntoFile(reversedFileContent, outfilename);
		}
		finally{
			if(scanner != null){
				scanner.close();
			}
			if(fileScanner != null){
				fileScanner.close();
			}
		}
	}

	private static String readInputFileName(Scanner scanner) {
		scanner = new Scanner(System.in);

		System.out.println("Please provide the input file name");
		String infilename = scanner.next();

		return infilename;
	}

	private static String readOutputFileName(Scanner scanner) {
		System.out.println("Please provide the output file name");
		String	outfilename = scanner.next();

		return outfilename;
	}
	public static Stack<String> readFile(Scanner scanner) {
		Stack<String> stack = new Stack<>();
		String line;
		while (scanner.hasNext()) {
			line = scanner.nextLine();
			if(line == null)
				break;
			stack.push(line);
		}
		return stack;
	}

	private static List<String> reverseFileContent(Stack<String> stack) {
		List<String> reversedFileContent = new ArrayList<>();
		while (!stack.isEmpty()) {
			reversedFileContent.add(reverseLineContent(stack.pop()));
		}
		return reversedFileContent;
	}

	public static String reverseLineContent(String line) {
		String[] words = line.split(WORD_SEPARATOR);
		String reversedLine = "";

		for (String word : words) {
			reversedLine = reverseWord(word) + " " + reversedLine;
		}
		return reversedLine.trim();
	}

	public static String reverseWord(String str) {
		String reversedWord = "";
		for (char c : str.toCharArray()) {
			reversedWord = c+reversedWord;
		}
		return reversedWord;
	}

	private static void writeIntoFile(List<String> reversedFilecontent, String outfilename) {
		FileWriter fileWriter = null;
		Iterator<String> iter = null;
		try {
			iter = reversedFilecontent.iterator();

			fileWriter = new FileWriter(outfilename);
			int lineNum = reversedFilecontent.size();
			while(iter.hasNext()) {
				fileWriter.write(iter.next());
				lineNum--;
				if(lineNum>0)
					fileWriter.write("\n");
			}
		} catch (Exception e) {
			System.out.println("Exception occurred while writing into file : "+e.getLocalizedMessage() );
		}finally {
			try {
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Exception occurred while closing the file writer : "+e.getLocalizedMessage() );
			}
		}	
	}

}
