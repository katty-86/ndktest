package com.example.kaky.ndktest;

import java.util.Random;

/**
 * Created by kaky on 28.01.16.
 */
public class Sorting {

    int elementCount;
    int gen_number;

    Sorting(int e, int gen_number){
        this.elementCount =e;
        this.gen_number=gen_number;
    }

    public String createString(int[] testarray ){
        String s_result = "";
        for (int i = 0; i < testarray.length; i++) {
            s_result += testarray[i] + " ";
        }
        return s_result;
    }

    public int[] jbubbleSort(int[] array) {
        int hold;
        int flag=1;
        int lenght = array.length;
        // System.out.print(lenght);
        for (int i = 0; (i < lenght) &&(flag==1); i++) {
            flag=0;
            for (int j = 0; j < (lenght-1); j++) {
                if (array[j+1] > array[j]) {
                    hold = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = hold;
                    flag=1;
                }
            }
        }
        return array;
    }

    public void jquickSort(int [] arr, int left, int right){
        int i = left, j = right;
        int tmp;
        int pivot = arr[(left + right) / 2];
      /* partition */
        while (i <= j) {
            while (arr[i] < pivot)
                i++;
            while (arr[j] > pivot)
                j--;
            if (i <= j) {
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        };
      /* recursion */
        if (left < j)
            jquickSort(arr, left, j);
        if (i < right)
            jquickSort(arr, i, right);
    }

    public boolean compareTab(int A[], int B[]){
        boolean flag =true;
        for(int i=0; (i<A.length)&&(flag==true); i++){
            if(A[i]!=B[i]){
                flag=false;
            }
        }
        return flag;
    }
    void generTab(int []tab, int size) {
        for (int i = 0; i < size; i++) {
            Random randomGenerator = new Random();
            tab[i] = randomGenerator.nextInt(gen_number);
        }
    }
}
