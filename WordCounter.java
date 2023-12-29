import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordCounter
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter text or provide a file path: ");
        String userInput = scanner.nextLine();
        String text = "";
        if (isFilePath(userInput))
        {
            try
            {
                text = readFromFile(userInput);
            }
            catch (IOException e)
            {
                System.err.println("Error reading the file. Please check the file path and try again.");
                return;
            }
        }
        else
        {
            text = userInput;
        }
        String[] words = text.split("[\\s\\p{Punct}]+");
        int wordCount = 0;
        Map<String, Integer> wordFrequencies = new HashMap<>();
        for (String word : words)
        {
            String lowerCaseWord = word.toLowerCase();
            if (!isStopWord(lowerCaseWord))
            {
                wordCount++;
                wordFrequencies.put(lowerCaseWord, wordFrequencies.getOrDefault(lowerCaseWord, 0) + 1);
            }
        }
        System.out.println("Total number of words: " + wordCount);
        System.out.println("Number of unique words: " + wordFrequencies.size());
        System.out.println("Word frequencies:");
        for (Map.Entry<String, Integer> entry : wordFrequencies.entrySet())
        {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        scanner.close();
    }
    private static boolean isFilePath(String userInput)
    {
        return userInput.contains("/") || userInput.contains("\\");
    }
    private static String readFromFile(String filePath) throws IOException
    {
        File file = new File(filePath);
        return new String(Files.readAllBytes(file.toPath()));
    }

    private static boolean isStopWord(String word)
    {
        String[] stopWords = {"The","and","how","abuse","misbehave"};
        for (String stopWord : stopWords)
        {
            if (stopWord.equals(word))
            {
                return true;
            }
        }
        return false;
    }
}
