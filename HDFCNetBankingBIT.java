public class HDFCNetBankingBIT {

    static int SIZE = 15;
    static int[] bit = new int[SIZE + 1];

    // Update BIT
    static void update(int index, int value) {
        while (index <= SIZE) {
            bit[index] += value;
            index += index & (-index);
        }
    }

    // Prefix Sum
    static int prefixSum(int index) {
        int sum = 0;

        System.out.print("Visited BIT cells: ");

        while (index > 0) {
            System.out.print(index + " ");
            sum += bit[index];
            index -= index & (-index);
        }

        System.out.println();
        return sum;
    }

    // Range Sum
    static int rangeSum(int left, int right) {
        return prefixSum(right) - prefixSum(left - 1);
    }

    public static void main(String[] args) {

        int[] spend = {
            0,
            1200, 800, 0, 2400, 1500,
            600, 0, 0, 3500, 0,
            1100, 950, 700, 0, 0
        };

        // Build BIT
        for (int i = 1; i <= SIZE; i++) {
            update(i, spend[i]);
        }

        // Display BIT Array
        System.out.println("BIT Array:");

        for (int i = 1; i <= SIZE; i++) {
            System.out.print(bit[i] + " ");
        }

        System.out.println("\n");

        // Query
        int result = rangeSum(5, 12);

        System.out.println("\nTotal Spend from Day 5 to Day 12 = " + result);
    }
}