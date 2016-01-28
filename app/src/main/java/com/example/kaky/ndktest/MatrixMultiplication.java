package com.example.kaky.ndktest;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by kaky on 28.01.16.
 */

public class MatrixMultiplication {

    int m_number;
    int gen_number;
    private static final String TAG = "MyActivity";
    MatrixMultiplication(int m_number, int gen_number){
        this.gen_number=gen_number;
        this.m_number=m_number;
    }


    public static long JMul(ArrayList<Integer> A, ArrayList<Integer> B, int n, int m, int k) {
        ArrayList<Integer> C = new ArrayList<>(n*k);

        Integer tmp;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < k; j++) {
                tmp = 0;
                for(int x = 0; x < m; x++) {
                    tmp += A.get(i * m + x) * B.get(x * k + j);
                }
                //C.set(i * k + j, tmp);
                C.add(tmp);
            }
        }

        return C.get(0);
    }

    public  int[][] jmatrixMultiplication(int[][] A, int[][] B){
        int m1ColLength = A[0].length; // m1 columns length
        int m2RowLength = B.length;    // m2 rows length
        if(m1ColLength != m2RowLength) return null;
        int mRRowLength = A.length;    // m result rows length
        int mRColLength = B[0].length; // m result columns length
        int[][] mResult = new int[mRRowLength][mRColLength];
        for(int i = 0; i < mRRowLength; i++) {         // rows from m1
            for(int j = 0; j < mRColLength; j++) {     // columns from m2
                for(int k = 0; k < m1ColLength; k++) { // columns from m1
                    mResult[i][j]+= A[i][k] * B[k][j];
                }
            }
        }

        return mResult;

    }

    public  Integer[][] jmatrixMultiplicationInteger(Integer[][] A, Integer[][] B){
        Integer m1ColLength = A[0].length; // m1 columns length
        Integer m2RowLength = B.length;    // m2 rows length
        if(m1ColLength != m2RowLength) return null;
        Integer mRRowLength = A.length;    // m result rows length
        Integer mRColLength = B[0].length; // m result columns length
        Integer[][] mResult = new Integer[mRRowLength][mRColLength];
        for(int i = 0; i < mRRowLength; i++) {         // rows from m1
            for(int j = 0; j < mRColLength; j++) {     // columns from m2
                mResult[i][j]=0;
                for(int k = 0; k < m1ColLength; k++) { // columns from m
                    mResult[i][j]+= A[i][k] * B[k][j];
                }
            }
        }

        return mResult;

    }

    public boolean compareTwoDimTab(int[][] A, int[][] B){
        boolean flag =true;
        for(int i = 0; (i<A.length)&&(flag==true); i++) {         // rows from m1
            for(int j = 0; (j<A.length)&&(flag==true); j++) {     // columns from m2
                if(A[i][j]!=B[i][j]){
                    flag=false;
                }
            }
        }

        return flag;

    }

    public String createString2dim(int[][] testarray ){
        String s_result = "";
        for (int i = 0; i < testarray.length; i++) {
            for (int j = 0; j < testarray.length; j++){
                s_result += testarray[i][j] + " ";
            }
            s_result +="\n";
        }
        return s_result;
    }
    public void genMatrix(int tab [][], int x, int y){
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                Random randomGenerator = new Random();
                tab[i][j] = randomGenerator.nextInt(gen_number);
            }
        }
    }

    public void genMatrixInteger( Integer tab [][], int x, int y){
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                Random randomGenerator = new Random();
                tab[i][j] = randomGenerator.nextInt(gen_number);
            }
        }
    }
}
