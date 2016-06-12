package testing;

import java.sql.*;
import java.util.ArrayList;

/**
 * Quick testing in command line
 * @author Sebastian Greenholtz
 */
public class QuickTest {

    public static void main(String[] args)  {
            ArrayList<String> term = new ArrayList<>();
            ArrayList<ArrayList<String>> searchStringsSingleWord = new ArrayList<ArrayList<String>>();

            term.add("singing");
            searchStringsSingleWord.add(term);
            term = new ArrayList<>();
        System.out.println(searchStringsSingleWord);

            term.add("1234");
            searchStringsSingleWord.add(term);
        term = new ArrayList<>();
        System.out.println(searchStringsSingleWord);

            term.add("1234567");
            searchStringsSingleWord.add(term);
        term = new ArrayList<>();
        System.out.println(searchStringsSingleWord);

            term.add("sing");
            searchStringsSingleWord.add(term);
        term = new ArrayList<>();
        System.out.println(searchStringsSingleWord);

            term.add("!@#$%^&*");
            searchStringsSingleWord.add(term);
        term = new ArrayList<>();
        System.out.println(searchStringsSingleWord);

            term.add("w8nt");
            searchStringsSingleWord.add(term);
        System.out.println(searchStringsSingleWord);
    }
}

