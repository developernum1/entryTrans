import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class MockDataGenerator {

    private static final String URL = "jdbc:mysql://localhost:3306/fyg?useSSL=false";
    private static final String USER = "entrydemo";
    private static final String PASSWORD = "password";
    private static final int BATCH_SIZE = 1000; // Adjusted batch size
    private static final int NUM_THREADS = 10; // Number of threads to use
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        int numToGenerate = 100000;

        // Check if at least one argument is provided
        if (args.length > 0) {
            try {
                // Retrieve the argument
                String input = args[0];
                numToGenerate = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid integer
                System.out.println("Invalid number format. Please enter a valid integer.");
                return;
            }
        } else {
            System.out.println("No arguments provided. Default number to generate records is: " + numToGenerate);
        }

        int recordsPerThread = numToGenerate / NUM_THREADS; // Number of records to generate for each thread

        // Record start time to see how long the execution will take
        System.out.println("Start time: " + LocalDateTime.now());

        // Create a thread pool with NUM_THREADS threads
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        // Submit tasks to the executor
        for (int i = 0; i < NUM_THREADS; i++) {
            int start = i * recordsPerThread; // Start number for each thread
            int end = (i == NUM_THREADS - 1) ? numToGenerate : start + recordsPerThread; // End number for each thread

            long baseId = getMaxId() + (start % NUM_THREADS) * recordsPerThread;

            executor.submit(() -> generateMockData(start, end, baseId));
        }

        // Shut down the executor and wait for tasks to complete
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Record end time to see how long the execution will take
        System.out.println("End   time: " + LocalDateTime.now());
        System.out.println("Now there are " + getMaxId() + " records in Mock dataset (t_entrytransaction table)!");
    }

    private static long getMaxId() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT MAX(id) FROM T_EntryTransaction";
            try (PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Default to 0 if there's an issue
    }

    private static void generateMockData(int numStart, int numEnd, long baseId) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            conn.setAutoCommit(false);

            String sql = "INSERT INTO T_EntryTransaction (taccId, entryId, type, amount, transactionDate, fundId) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                for (int i = numStart; i < numEnd; i++) {
                    // Generate random data
                    long taccId = RANDOM.nextLong(1000); // Random taccId
                    long entryId = baseId + i; // Unique entryId based on baseId
                    String type = "Type" + RANDOM.nextInt(5); // Random type
                    double amount = RANDOM.nextDouble() * 1000; // Random amount
                    Date transactionDate = new Date(); // Current date
                    String fundId = "Fund" + RANDOM.nextInt(1000); // Random fundId

                    // Set parameters
                    pstmt.setLong(1, taccId);
                    pstmt.setLong(2, entryId);
                    pstmt.setString(3, type);
                    pstmt.setDouble(4, amount);
                    pstmt.setDate(5, new java.sql.Date(transactionDate.getTime()));
                    pstmt.setString(6, fundId);

                    pstmt.addBatch();

                    if ((i + 1) % BATCH_SIZE == 0) { // Execute batch every BATCH_SIZE records
                        pstmt.executeBatch();
                        conn.commit();
                    }
                }
                pstmt.executeBatch();
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.rollback(); // Rollback in case of error
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
