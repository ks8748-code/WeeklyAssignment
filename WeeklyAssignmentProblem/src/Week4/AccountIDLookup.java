package Week4;
import java.util.*;

class Main1 {

    // -------- LINEAR SEARCH (first & last occurrence + comparisons) --------
    static void linearSearch(String[] arr, String target) {
        int first = -1, last = -1;
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                if (first == -1) first = i;
                last = i;
            }
        }

        System.out.println("Linear first " + target + ": index " + first +
                " (" + comparisons + " comparisons)");
        System.out.println("Linear last " + target + ": index " + last);
    }

    // -------- BINARY SEARCH (first occurrence) --------
    static int binaryFirst(String[] arr, String target, int[] comp) {
        int low = 0, high = arr.length - 1, result = -1;

        while (low <= high) {
            comp[0]++;
            int mid = (low + high) / 2;

            if (arr[mid].equals(target)) {
                result = mid;
                high = mid - 1; // move left
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // -------- BINARY SEARCH (last occurrence) --------
    static int binaryLast(String[] arr, String target, int[] comp) {
        int low = 0, high = arr.length - 1, result = -1;

        while (low <= high) {
            comp[0]++;
            int mid = (low + high) / 2;

            if (arr[mid].equals(target)) {
                result = mid;
                low = mid + 1; // move right
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {

        String[] logs = {"accB", "accA", "accB", "accC"};

        // Sort for binary search
        Arrays.sort(logs);
        System.out.println("Sorted logs: " + Arrays.toString(logs));

        String target = "accB";

        // Linear Search
        linearSearch(logs, target);

        // Binary Search
        int[] comp = {0};
        int first = binaryFirst(logs, target, comp);
        int last = binaryLast(logs, target, comp);

        int count = (first == -1) ? 0 : (last - first + 1);

        System.out.println("Binary " + target + ": index " + first +
                " (" + comp[0] + " comparisons), count=" + count);
    }
}