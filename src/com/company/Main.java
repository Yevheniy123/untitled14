package com.company;
import java.util.Arrays;
import java.util.Scanner;
import static com.company.BubbleSortExample.bubbleSort;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // Объявляем Scanner
        System.out.println("Enter array length: ");
        int size = input.nextInt(); // Читаем с клавиатуры размер массива и записываем в size
        int array[] = new int[size]; // Создаём массив int размером в size
        for (int i = 0; i < size; i++) {
            array[i] = ((int)(Math.random() * 10)); // Заполняем массив элементами, введёнными с клавиатуры
        }
        System.out.print ("Inserted array elements:");
        for (int i = 0; i < size; i++) {
            System.out.print (" " + array[i]); // Выводим на экран, полученный массив
        }
        System.out.println();


        long startTime = System.nanoTime();

        size = array.length;

        CountingSort cs = new CountingSort();
        cs.countSort(array, size);
        System.out.println("Counting Sort : ");
        System.out.println(Arrays.toString(array));

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Time:"+ duration);
        System.out.println(".......................");

        startTime = System.nanoTime();
        bubbleSort(array);
        System.out.println("Bubble Sort: ");
        System.out.println(Arrays.toString(array));
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("Time:"+ duration);
        System.out.println(".......................");

        startTime = System.nanoTime();
        HeapSort ob = new HeapSort();
        ob.sort(array);
        System.out.println("Sorted array is");
        System.out.println(Arrays.toString(array));
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("Time:"+ duration);
        System.out.println(".......................");
    }


}

class HeapSort {
    public void sort(int arr[]) {
        int n = arr.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    void heapify(int arr[], int n, int i) {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < n && arr[l] > arr[largest])
            largest = l;

        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;

        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }
}

class BubbleSortExample {
    static void bubbleSort(int[] arr) {
        int n = arr.length;
        int temp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (arr[j - 1] > arr[j]) {
                    //swap elements
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }

            }
        }

    }
}


class CountingSort {
    void countSort(int array[], int size) {

        int[] output = new int[size + 1];
        int max = array[0];
        for (int i = 1; i < size; i++) {
            if (array[i] > max) {
                max = array[i];

            }

        }
        int[] count = new int[max + 1];
        for (int i = 0; i < max; ++i) {
            count[i] = 0;
        }
        for (int i = 0; i < size; i++) {
            count[array[i]]++;

        }
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }
        for (int i = size - 1; i >= 0; i--) {
            output[count[array[i]] - 1] = array[i];
            count[array[i]]--;


        }
        for (int i = 0; i < size; i++) {
            array[i] = output[i];

        }

    }
}

