package c_monk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CountingSort {

    public static void main(String[] args) {
        List<Integer> vet = new ArrayList<>();
        System.out.println("oi");
        for (int i = 10; i > 0; i--) {
            vet.add(i);
        }
        
        System.out.println(vet.size());
        
        Collections.sort(vet);    
        
        for(int i = 0; i < vet.size(); i++) {
            System.out.print(vet.get(i) + "\t");
        }
    }

   static void countSort(int[] arr)
    {
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();
        int range = max - min + 1;
        int count[] = new int[range];
        int output[] = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i] - min]++;
        }
 
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
 
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }
 
        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
        }
    }
}
