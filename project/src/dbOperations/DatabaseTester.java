package dbOperations;

/**
 * Class to test database connection
 * @author Sebastian Greenholtz
 */
public class DatabaseTester {

    public static void main(String[] args) {
        DatabaseHandler handler = new DatabaseHandler("root", "student",
                "jdbc:mysql://localhost:3306/eventspider?useSSL=false");
        System.out.println(handler.getConnection());
    }
}
