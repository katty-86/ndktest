package com.example.kaky.ndktest;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    int c_number = 10;
    int m_number=100;
    int gen_number=10;
    long start, end, diff;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });





        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

   /* public native String getStringFromNative();

    public native int[] sortVector(int[] test);*/

    public native int[] bubbleSort(int[] test);

   /* public native int[] quickSort(int[] test);

    public native int[] countingSort(int[] test); */

    public native int[] stlsort(int[] test);


    //matrix
    public native int[][] matrixMultiplication(int[][] A, int[][] b);


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
   /* public  int[]jbubbleSort(int [] test);
    public  int[] jquickSort(int [] test);
    public  int[] jcountingSort(int [] test); */


    //matrix
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
    public boolean compareTab(int A[], int B[]){
        boolean flag =true;
        for(int i=0; (i<A.length)&&(flag==true); i++){
            if(A[i]!=B[i]){
                flag=false;
            }
        }
        return flag;
    }
    public String createString(int[] testarray ){
        String s_result = "";
        for (int i = 0; i < testarray.length; i++) {
            s_result += testarray[i] + " ";
        }
        return s_result;
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

    static {
        System.loadLibrary("NativeLib");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. Tlshe action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.kaky.ndktest/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.kaky.ndktest/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


    public void printMatrix(View view) {
        //matrix test
        String s_result="";
        int [][] Amatrix = new int[m_number][m_number];
        int [][] Bmatrix = new int[m_number][m_number];
        int [][] cAmatrix = new int[m_number][m_number];
        int [][] cBmatrix = new int[m_number][m_number];
        for (int i = 0; i < m_number; i++) {
            for (int j = 0; j < m_number; j++) {
                Random randomGenerator = new Random();
                Amatrix[i][j] = randomGenerator.nextInt(gen_number);
                Bmatrix[i][j] = randomGenerator.nextInt(gen_number);
            }
        }
        cAmatrix =Amatrix.clone();
        cBmatrix =Bmatrix.clone();
        start= System.nanoTime();
        int[][] jmresultArr = jmatrixMultiplication(Amatrix, Bmatrix);
        end= System.nanoTime();
        diff=end-start;
        s_result+="multi matrix(java): "+diff+" ns\n";


        start= System.nanoTime();
        int[][] cmresultArr = matrixMultiplication(cAmatrix, cBmatrix);
        end= System.nanoTime();
        diff=end-start;
        s_result+="multi matrix(c++ ): "+diff+" ns\n";
        if(compareTwoDimTab(jmresultArr,cmresultArr )){
            s_result+="compare matrix ok\n ";
        }

        TextView tv = (TextView) findViewById(R.id.my_tekst);
        tv.setText(s_result);
    }

    public void printSort(View view) {

        int[] testarray = new int[c_number];

        for (int i = 0; i < c_number; i++) {
            Random randomGenerator = new Random();
            testarray[i] = randomGenerator.nextInt(c_number);
        }
        int[] ctestarray = new int[c_number];
        int[] stltestarray = new int[c_number];
        System.arraycopy(testarray,0,ctestarray,0,testarray.length);
        System.arraycopy(testarray,0,stltestarray,0,testarray.length);
        String s_result="";
        //  s_result+="org tab:"+ createString(testarray)+"\n";
        //Timestamp czas = ;
        start= System.nanoTime();
        int[] jresultArr = jbubbleSort(testarray);
        end= System.nanoTime();
        diff=end-start;
        s_result+="Bubble sort(java): "+diff+" ns\n";
       // s_result+="org tab:"+ createString(ctestarray)+"\n";

       start = System.nanoTime();
        int[] cresultArr = bubbleSort(ctestarray);
        end= System.nanoTime();
        diff=end-start;
        s_result+="Bubble sort(c++): "+diff+" ns\n";

        start = System.nanoTime();
        int[] stlresultArr = stlsort(stltestarray);
        end= System.nanoTime();
        diff=end-start;
        s_result+="stl sort(c++): "+diff+" ns\n";

        s_result+="compare: ";
        if(compareTab(jresultArr,cresultArr )){
            s_result+="j+c ok ";
        }
        if(compareTab(jresultArr,stlresultArr )){
            s_result+="j+stl ok ";
        }
        if(compareTab(cresultArr,stlresultArr )){
            s_result+="c+stl ok ";
        }
        s_result+="\n";

        TextView tv = (TextView) findViewById(R.id.my_tekst);
        tv.setText(s_result);
    }
}
