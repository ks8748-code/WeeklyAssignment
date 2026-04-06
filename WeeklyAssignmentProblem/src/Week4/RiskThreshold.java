package Week4;
import java.util.*;

class Main2 {

    // -------- LINEAR SEARCH (unsorted, find threshold match) --------
    static void linearSearch(int[] arr, int target) {
        int comparisons = 0;
        boolean found = false;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == target) {
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Linear: threshold=" + target + " found (" + comparisons + " comps)");
        } else {
            System.out.println("Linear: threshold=" + target + " -> not found (" + comparisons + " comps)");
        }
    }

    // -------- BINARY SEARCH FLOOR (largest <= target) --------
    static int floor(int[] arr, int target, int[] comp) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            comp[0]++;
            int mid = (low + high) / 2;

            if (arr[mid] <= target) {
                result = arr[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // -------- BINARY SEARCH CEILING (smallest >= target) --------
    static int ceiling(int[] arr, int target, int[] comp) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            comp[0]++;
            int mid = (low + high) / 2;

            if (arr[mid] >= target) {
                result = arr[mid];
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {

        int[] risks = {10, 25, 50, 100};
        int threshold = 30;

        // Linear Search (unsorted example)
        linearSearch(risks, threshold);

        // Binary Search (sorted array)
        int[] comp = {0};

        int f = floor(risks, threshold, comp);
        int c = ceiling(risks, threshold, comp);

        System.out.println("Binary floor(" + threshold + "): " + f +
                ", ceiling: " + c + " (" + comp[0] + " comps)");
    }
}
