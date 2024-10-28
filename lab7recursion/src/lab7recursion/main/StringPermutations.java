package lab7recursion.main;
import java.util.*;

public class StringPermutations {

    public static void main(String[] args) {
        String input = "momo"; // Change this for different input
        boolean excludeDuplicates = false; // Change to true to exclude duplicates
        
        List<String> permutationsRecursive = generatePermutations(input, excludeDuplicates);
        System.out.println("Recursive Permutations: " + permutationsRecursive);
        
        List<String> permutationsNonRecursive = generatePermutationsNonRecursive(input, excludeDuplicates);
        System.out.println("Non-Recursive Permutations: " + permutationsNonRecursive);
    }

    // Recursive function to generate permutations
    public static List<String> generatePermutations(String str, boolean excludeDuplicates) {
        Set<String> resultSet = new HashSet<>();
        generatePermutations("", str, resultSet);
        return excludeDuplicates ? new ArrayList<>(resultSet) : new ArrayList<>(resultSet);
    }

    // Helper method for recursion
    private static void generatePermutations(String prefix, String str, Set<String> result) {
        int n = str.length();
        if (n == 0) {
            result.add(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                generatePermutations(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n), result);
            }
        }
    }

    // Non-recursive function to generate permutations
    public static List<String> generatePermutationsNonRecursive(String str, boolean excludeDuplicates) {
        Set<String> resultSet = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(str);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.length() == 1) {
                resultSet.add(current);
            } else {
                for (int i = 0; i < current.length(); i++) {
                    String newPerm = current.charAt(i) + current.substring(0, i) + current.substring(i + 1);
                    queue.add(newPerm);
                }
            }
        }

        return excludeDuplicates ? new ArrayList<>(resultSet) : new ArrayList<>(resultSet);
    }

   
}
