package Week3;
import java.util.*;

class Trade {
    String id;
    int volume;

    Trade(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }

    public String toString() {
        return id + ":" + volume;
    }
}

class Main2 {

    // -------- MERGE SORT (ASC by volume) --------
    static void mergeSort(List<Trade> list, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(list, left, mid);
            mergeSort(list, mid + 1, right);
            merge(list, left, mid, right);
        }
    }

    static void merge(List<Trade> list, int left, int mid, int right) {
        List<Trade> temp = new ArrayList<>();

        int i = left, j = mid + 1;

        while (i <= mid && j <= right) {
            if (list.get(i).volume <= list.get(j).volume) {
                temp.add(list.get(i++));
            } else {
                temp.add(list.get(j++));
            }
        }

        while (i <= mid) temp.add(list.get(i++));
        while (j <= right) temp.add(list.get(j++));

        for (int k = 0; k < temp.size(); k++) {
            list.set(left + k, temp.get(k));
        }
    }

    // -------- QUICK SORT (DESC by volume) --------
    static void quickSort(List<Trade> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    static int partition(List<Trade> list, int low, int high) {
        int pivot = list.get(high).volume; // pivot = last element
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (list.get(j).volume > pivot) { // DESC
                i++;
                swap(list, i, j);
            }
        }

        swap(list, i + 1, high);
        return i + 1;
    }

    static void swap(List<Trade> list, int i, int j) {
        Trade temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    // -------- MERGE TWO SORTED LISTS --------
    static List<Trade> mergeLists(List<Trade> a, List<Trade> b) {
        List<Trade> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < a.size() && j < b.size()) {
            if (a.get(i).volume <= b.get(j).volume) {
                result.add(a.get(i++));
            } else {
                result.add(b.get(j++));
            }
        }

        while (i < a.size()) result.add(a.get(i++));
        while (j < b.size()) result.add(b.get(j++));

        return result;
    }

    // -------- TOTAL VOLUME --------
    static int totalVolume(List<Trade> list) {
        int sum = 0;
        for (Trade t : list) {
            sum += t.volume;
        }
        return sum;
    }

    public static void main(String[] args) {

        List<Trade> trades = new ArrayList<>();
        trades.add(new Trade("trade3", 500));
        trades.add(new Trade("trade1", 100));
        trades.add(new Trade("trade2", 300));

        // Merge Sort (ASC)
        mergeSort(trades, 0, trades.size() - 1);
        System.out.println("MergeSort: " + trades);

        // Quick Sort (DESC)
        quickSort(trades, 0, trades.size() - 1);
        System.out.println("QuickSort (desc): " + trades);

        // Merge two lists example
        List<Trade> morning = new ArrayList<>();
        morning.add(new Trade("m1", 100));
        morning.add(new Trade("m2", 300));

        List<Trade> afternoon = new ArrayList<>();
        afternoon.add(new Trade("a1", 500));

        List<Trade> merged = mergeLists(morning, afternoon);
        System.out.println("Merged: " + merged);

        // Total volume
        System.out.println("Total volume: " + totalVolume(merged));
    }
}