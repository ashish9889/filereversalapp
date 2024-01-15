package test.java.com.interview.hsbc;

import main.java.com.interview.hsbc.FileReversalMain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import java.io.IOException;

import java.util.Scanner;
import java.util.Stack;

import static main.java.com.interview.hsbc.FileReversalMain.readFile;


public class FileReversalMainTest {

	//Read File test
	@Test
	public void readFileTest() throws IOException {

		Scanner scanner = Mockito.mock(Scanner.class);

		Mockito.when(scanner.nextLine()).thenReturn("ABC", "\nDEF", "G", null);
		Mockito.when(scanner.hasNext()).thenReturn(true);
		Stack<String> expected = new Stack<>();
		expected.push("ABC");
		expected.push("\nDEF");
		expected.push("G");
		Stack<String> actual = readFile(scanner);
		Assertions.assertEquals(expected.size(), actual.size());
		while(!expected.isEmpty()){
			String expectedString = expected.pop();
			String actualString = actual.pop();
			Assertions.assertEquals(expectedString, actualString);
		}
	}

	@Test
	public void reverseWordTest(){
		String expected = "FED";
		String actual = FileReversalMain.reverseWord("DEF");
		Assertions.assertEquals(expected, actual);
	}
	//Reverse word test

	//Reverse Line test
	@Test
	public void reverseLineTest(){
		StringBuilder expected = new StringBuilder("THIS IS TEST LINE");
		String reversedLine = FileReversalMain.reverseLineContent(expected.toString());
		String expectedStr = expected.reverse().toString();
		Assertions.assertEquals(reversedLine, expectedStr);
	}

}
