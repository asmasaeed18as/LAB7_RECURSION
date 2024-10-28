package lab7recursion.test;

import lab7recursion.main.RecursiveFileSearch;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class RecursiveFileSearchTest {

    @Before
    public void setUp() {
        RecursiveFileSearch.setCount(0); // Reset count before each test
    }

    @After
    public void tearDown() {
        RecursiveFileSearch.setCount(0); // Reset count after each test
    }

    @Test
    public void testFileFoundSingleFile() throws IOException {
        File dir = Files.createTempDirectory("testDir").toFile();
        File file = new File(dir, "asmaFile.txt");
        file.createNewFile();
        
        RecursiveFileSearch.searchFiles(dir, Arrays.asList("asmaFile.txt"), true);
        
        assertEquals(1, RecursiveFileSearch.getCount());
        
        file.delete();
        dir.delete();
    }

    @Test
    public void testFileNotFound() {
        File dir = new File("/non/existent/path");
        RecursiveFileSearch.searchFiles(dir, Arrays.asList("nofilefound.txt"), true);
        assertEquals(0, RecursiveFileSearch.getCount());
    }

    @Test
    public void testCaseInsensitiveSearch() throws IOException {
        File dir = Files.createTempDirectory("testDir").toFile();
        File file = new File(dir, "asmaFile.txt");
        file.createNewFile();
        
        RecursiveFileSearch.searchFiles(dir, Arrays.asList("asmafile.txt"), false);
        
        assertEquals(1, RecursiveFileSearch.getCount());
        
        file.delete();
        dir.delete();
    }

    @Test
    public void testMultipleFileSearch() throws IOException {
        File dir = Files.createTempDirectory("testDir").toFile();
        File file1 = new File(dir, "asmaFile1.txt");
        File file2 = new File(dir, "asmaFile2.txt");
        file1.createNewFile();
        file2.createNewFile();
        
        RecursiveFileSearch.searchFiles(dir, Arrays.asList("asmaFile1.txt", "asmaFile2.txt"), true);
        
        assertEquals(2, RecursiveFileSearch.getCount());
        
        file1.delete();
        file2.delete();
        dir.delete();
    }
}
