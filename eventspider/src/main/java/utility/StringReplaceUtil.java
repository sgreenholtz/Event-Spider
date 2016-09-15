package utility;

import java.util.*;

/**
 * A simple but robust string replace utility
 * @author Sebastian Greenholtz
 */
public class StringReplaceUtil {
    private String base;
    private Character substitute;
    private ArrayList<Integer> indexes;

    /**
     * Constructor sets up the util to do the replace
     * @param base String in which to make replacements
     * @param substitute Char that needs to be replaced
     */
    public StringReplaceUtil(String base, Character substitute) {
        this.base = base;
        this.substitute = substitute;
        getReplaceList();
    }

    /**
     * Looks in the base string for the character and adds its
     * index to the list
     */
    private void getReplaceList() {
        indexes = new ArrayList<Integer>();
        indexes.add(-1);
        Integer index = 0;
        for (Character chara : base.toCharArray()) {
            if (chara.equals(substitute)) {
                indexes.add(index);
            }
            index++;
        }
    }

    /**
     * Replaces the cardinal substitute character with the given String, and
     * updates the instance list to reflect change
     * @param cardinal The cardinal representation of the string to replace.
     *                 If you want to replace the first ? then cardinal=1
     * @param replace String to replace that with
     * @return Updated string
     */
    public void setString(Integer cardinal, String replace) {
        StringBuilder str = new StringBuilder(base);
        replace = "'" + replace + "'";
        base = str.insert(cardinal, replace).toString();
        getReplaceList();
    }

    public String toString() {
        return base;
    }
}
