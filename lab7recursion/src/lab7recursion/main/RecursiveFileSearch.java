package lab7recursion.main;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RecursiveFileSearch {
    private static int count = 0;

    /**
     * Main method that initializes the directory and file names to search, and handles case-sensitivity option.
     * @param args command-line arguments: directory path, file names to search, and optional case-sensitivity flag.
     *             The last argument is treated as case sensitivity if set to "true".
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println(" my recursive searchjava ");
            return;
        }
        
        String directoryPath = args[0];
        boolean caseSensitive = args[args.length - 1].equalsIgnoreCase("true");
        
        // Collect all file names to search for
        List<String> fileNames = new ArrayList<>();
        for (int i = 1; i < args.length - 1; i++) {
            fileNames.add(caseSensitive ? args[i] : args[i].toLowerCase());
        }
        
        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Invalid directory path given.");
            return;
        }

        searchFiles(directory, fileNames, caseSensitive);
        
        if (getCount() > 0) {
            System.out.println("Total occurrences found: " + getCount());
        } else {
            System.out.println("No file appeared during search.");
        }
    }
    
    public static void searchFiles(File dir, List<String> fileNames, boolean caseSensitive) {
        File[] files = dir.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                searchFiles(file, fileNames, caseSensitive);
            } else {
                String fileNameToCheck = caseSensitive ? file.getName() : file.getName().toLowerCase();
                
                if (fileNames.contains(fileNameToCheck)) {
                    System.out.println("I have found" + file.getAbsolutePath());
                    setCount(getCount() + 1);
                }
            }
        }
    }
	public static int getCount() {
		return count;
	}
	public static void setCount(int count) {
		RecursiveFileSearch.count = count;
	}
}
