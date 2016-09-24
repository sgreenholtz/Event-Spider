package testing;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Simple main method testing
 * @author Sebastian Greenholtz
 */
public class PSVM {

    public static void main(String[] args) {
        System.out.println(DigestUtils.sha1Hex("lenin"));
        //498ae6f03c116d6d3bd6fa42563a2b6ae62a04e3

    }
}
