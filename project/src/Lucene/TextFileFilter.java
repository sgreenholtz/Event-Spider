package Lucene;

import java.io.*;

/**
 * A Txt file filter from Tutorialpoint
 * @author Sebastian Greenholtz
 */
public class TextFileFilter implements FileFilter {

    @Override
    public boolean accept(File pathname) {
        return pathname.getName().toLowerCase().endsWith(".txt");
    }
}
