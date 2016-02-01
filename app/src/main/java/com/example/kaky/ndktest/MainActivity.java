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
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Random;
import java.text.DecimalFormat;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

public class MainActivity extends AppCompatActivity {


    int c_number = 100;
    int mg=100;
    int gen_number=10;
    long start, end;
    double diff;
    MatrixMultiplication multimat = new MatrixMultiplication(mg, gen_number);
    Sorting sort =new Sorting(c_number,gen_number );
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
    public native long jjCMul(int[] A, int[] B, int n, int m, int k);

    public native String CPPDESencryptDecrypt();


   /* public  int[] jcountingSort(int [] test); */


    //matrix








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
        String s_result="Matrix "+multimat.m_number+" x "+multimat.m_number+"\n";
        int [][] Amatrix = new int[multimat.m_number][multimat.m_number];
        int [][] Bmatrix = new int[multimat.m_number][multimat.m_number];
        int [][] cAmatrix = new int[multimat.m_number][multimat.m_number];
        int [][] cBmatrix = new int[multimat.m_number][multimat.m_number];
        multimat.genMatrix(Amatrix, multimat.m_number, multimat.m_number);
        multimat.genMatrix(Bmatrix, multimat.m_number, multimat.m_number);

        cAmatrix =Amatrix.clone();
        cBmatrix =Bmatrix.clone();
        start= System.nanoTime();
        //int[][] jmresultArr =
        multimat.jmatrixMultiplication(Amatrix, Bmatrix);
        end= System.nanoTime();
        diff=(end-start)/1000000000.0;
        s_result+="multi matrix(java):"+new DecimalFormat("##.#########").format(diff)+" s\n";

        Integer [][] AmatrixInteger = new Integer[multimat.m_number][multimat.m_number];
        Integer [][] BmatrixInteger = new Integer[multimat.m_number][multimat.m_number];
        multimat.genMatrixInteger(AmatrixInteger, multimat.m_number, multimat.m_number);
        multimat.genMatrixInteger(BmatrixInteger, multimat.m_number, multimat.m_number);

        start= System.nanoTime();
        //int[][] jmresultArr =
        multimat.jmatrixMultiplicationInteger(AmatrixInteger,BmatrixInteger );
        end= System.nanoTime();
        diff=(end-start)/1000000000.0;
        s_result+="multimatInteger(j):"+new DecimalFormat("##.#########").format(diff)+" s\n";


        ArrayList<Integer> tabA = new ArrayList<>();
        ArrayList<Integer> tabB = new ArrayList<>();
        for(int i=0; i<(multimat.m_number*multimat.m_number); ++i ){
            Random randomGenerator = new Random();
            tabA.add(randomGenerator.nextInt(gen_number));
            tabB.add(randomGenerator.nextInt(gen_number));

        }
        start= System.nanoTime();
        MatrixMultiplication.JMul(tabA, tabB,multimat.m_number, multimat.m_number, multimat.m_number );
        end= System.nanoTime();
        diff=(end-start)/1000000000.0;
        s_result+="jj:multi matrix(jav):"+new DecimalFormat("##.#########").format(diff)+" s\n";

        int[] jjctAtab = new int[multimat.m_number*multimat.m_number];
        int[] jjctBtab = new int[multimat.m_number*multimat.m_number];
        sort.generTab(jjctAtab, multimat.m_number*multimat.m_number);
        sort.generTab(jjctBtab, multimat.m_number*multimat.m_number);

        start= System.nanoTime();
        jjCMul(jjctAtab, jjctBtab, multimat.m_number,multimat.m_number, multimat.m_number);
        end= System.nanoTime();
        diff=(end-start)/1000000000.0;
        s_result+="jj:multi matrix(c++):"+new DecimalFormat("##.#########").format(diff)+" s\n";


        start= System.nanoTime();
        int[][] cmresultArr = matrixMultiplication(cAmatrix, cBmatrix);
        end= System.nanoTime();
        diff=(end-start)/1000000000.0;
        s_result+="multi matrix(c++ ):"+new DecimalFormat("##.#########").format(diff)+" s\n";

        //



       /* if(multimat.compareTwoDimTab(jmresultArr, cmresultArr)){
            s_result+="compare matrix ok\n ";
        }*/

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
        int[] jqtestarray = new int[c_number];
        System.arraycopy(testarray, 0, ctestarray, 0, testarray.length);
        System.arraycopy(testarray,0,stltestarray,0,testarray.length);
        System.arraycopy(testarray,0,jqtestarray,0,testarray.length);
        String s_result="Sorting "+c_number+"\n";;
        //  s_result+="org tab:"+ createString(testarray)+"\n";
        //Timestamp czas = ;
        start= System.nanoTime();
      //  int[] jresultArr = jbubbleSort(testarray);
        end= System.nanoTime();
        diff=(end-start)/1000000000.0;
        s_result+="Bubble sort(java): "+new DecimalFormat("##.#########").format(diff)+" s\n";
       // s_result+="org tab:"+ createString(ctestarray)+"\n";

        start = System.nanoTime();
        int[] cresultArr = bubbleSort(ctestarray);
        end= System.nanoTime();
        diff=(end-start)/1000000000.0;
        s_result+="Bubble sort(c++): "+new DecimalFormat("##.#########").format(diff)+" s\n";

        start= System.nanoTime();
      //  jquickSort(jqtestarray, 0,c_number);
        end= System.nanoTime();
        diff=(end-start)/1000000000.0;
        s_result+="guick sort(java): "+new DecimalFormat("##.#########").format(diff)+" s\n";

        start = System.nanoTime();
        int[] stlresultArr = stlsort(stltestarray);
        end= System.nanoTime();
        diff=(end-start)/1000000000.0;
        s_result+="stl sort(c++): "+new DecimalFormat("##.#########").format(diff)+" s\n";

        s_result+="compare: ";
      /*  if(compareTab(jresultArr,cresultArr )){
            s_result+="j+c ok ";
        }
        if(compareTab(jresultArr,stlresultArr )){
            s_result+="j+stl ok ";
        }*/
        if(sort.compareTab(cresultArr, stlresultArr)){
            s_result+="c+stl ok ";
        }
        s_result+="\n";

        TextView tv = (TextView) findViewById(R.id.my_tekst);
        tv.setText(s_result);
    }

    public void testingDES(View view) {
        DES des =new DES();
        String encryptText=des.genString(2147483); //et1.getText().toString();
        start = System.nanoTime();
        String s_result =des.DESencryptDecrypt(encryptText);
        end= System.nanoTime();
        diff=(end-start)/1000000000.0;
        s_result="DES(java): "+new DecimalFormat("##.#########").format(diff)+" s\n";
        TextView tv2 = (TextView) findViewById(R.id.my_tekst);
        tv2.setText(s_result);


    }
}
