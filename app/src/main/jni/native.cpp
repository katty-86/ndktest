#include "com_example_kaky_ndktest_MainActivity.h"
#include <iostream>
#include <vector>
#include <functional>   // std::greater
#include <algorithm>
#include <android/log.h>


JNIEXPORT jstring JNICALL Java_com_example_kaky_ndktest_MainActivity_getStringFromNative
  (JNIEnv *env, jobject o){
  //jstring test=jstring("test");
  return env->NewStringUTF("test");
//  return test;
}

JNIEXPORT jintArray JNICALL Java_com_example_kaky_ndktest_MainActivity_sortVector
        (JNIEnv *env, jobject o, jintArray oldArray){
    const jsize length = env->GetArrayLength(oldArray);
    jintArray newArray = env->NewIntArray(length);
    jint *oarr = env->GetIntArrayElements(oldArray, NULL);
    jint *narr = env->GetIntArrayElements(newArray, NULL);
    for (int o = 0; o < length; o++) {
        narr[o] = oarr[o]+1;
    }
    env->SetIntArrayRegion(newArray, 0, length,narr );
    env->ReleaseIntArrayElements(newArray, narr, 0);
  //  env->ReleaseIntArrayElements(oldArray, oarr, NULL);

    return newArray;
}

JNIEXPORT jintArray JNICALL Java_com_example_kaky_ndktest_MainActivity_bubbleSort
        (JNIEnv *env, jobject o, jintArray oldArray){
    int hold;
    int flag=1;
    const jsize length = env->GetArrayLength(oldArray);
    jintArray newArray = env->NewIntArray(length);
    jint *oarr = env->GetIntArrayElements(oldArray, NULL);
  //  jint *narr = env->GetIntArrayElements(newArray, NULL);
    for (int i = 0; (i < length) &&(flag==1); i++) {
        flag=0;
        for (int j = 0; j < (length-1); j++) {
            if (oarr[j+1] > oarr[j ]) {
                hold = oarr[j];
                oarr[j] = oarr[j + 1];
                oarr[j + 1] = hold;
                flag=1;
            }
        }
    }
    env->SetIntArrayRegion(newArray, 0, length,oarr );
    env->ReleaseIntArrayElements(newArray, oarr, 0);
    //  env->ReleaseIntArrayElements(oldArray, oarr, NULL);

    return newArray;

}


JNIEXPORT jintArray JNICALL Java_com_example_kaky_ndktest_MainActivity_stlsort
        (JNIEnv *env, jobject o, jintArray oldArray){
    int hold;
    int flag=1;
    const jsize length = env->GetArrayLength(oldArray);
    jintArray newArray = env->NewIntArray(length);
    jint *oarr = env->GetIntArrayElements(oldArray, NULL);
    std::vector<int> stlvec(oarr, oarr+length);
    std::sort(stlvec.begin(), stlvec.end(), std::greater<int>());
  //  jint *oarr1 = stlvec.begin();


    env->SetIntArrayRegion(newArray, 0, stlvec.size() ,(jint *)&(stlvec[0]) );
 //   env->ReleaseIntArrayElements(newArray, oarr, 0);
    //  env->ReleaseIntArrayElements(oldArray, oarr, NULL);

    return newArray;
}

//http://adndevblog.typepad.com/cloud_and_mobile/2013/08/android-ndk-passing-complex-data-to-jni.html
JNIEXPORT jobjectArray JNICALL Java_com_example_kaky_ndktest_MainActivity_matrixMultiplication
        (JNIEnv *env, jobject o, jobjectArray A, jobjectArray B){

    int size=env->GetArrayLength(A);
    jclass claz = env->FindClass( "[I");
    jobjectArray result = env->NewObjectArray( size, claz, NULL);
    int **Bptr =new int*[size];
    for(int i=0; i<size; ++i){
        jintArray BoneDim= (jintArray)env->GetObjectArrayElement(B, i);
        jint *Belement=env->GetIntArrayElements(BoneDim, 0);
        Bptr[i]=new int [size];
        for(int j=0; j<size; j++){
            Bptr[i][j]=Belement[j];
        }
        delete Belement;
    }

    for(int i=0; i<size; ++i){
        jintArray AoneDim= (jintArray)env->GetObjectArrayElement(A, i);
        jint *Aelement=env->GetIntArrayElements(AoneDim, 0);
        jintArray inner = env->NewIntArray( size);
        int localArray[size];// = new int[size];
        for(int j=0; j<size; ++j) {
            localArray[j]=0;
            for(int k=0; k<size; ++k){

                int multi=0;
                multi= Aelement[k] * (Bptr[k][j]);
              //  __android_log_print(ANDROID_LOG_VERBOSE, "MyApp", "multiply A %d B %d result %d", Aelement[k], Bptr[k][j], sum);
                localArray[j]=localArray[j] + multi;
              //  __android_log_print(ANDROID_LOG_VERBOSE, "MyApp", "The value of n is %d", localArray[j]);
            }
          //  __android_log_print(ANDROID_LOG_VERBOSE, "MyApp", "End %d", localArray[j]);


        }

        env->SetIntArrayRegion( inner, 0, size, localArray);
        env->SetObjectArrayElement( result, i, inner);
        env->DeleteLocalRef( inner);
       // delete [] localArray;
        delete Aelement;
      //  delete Belement;

    }
    for (int i = 0; i < size; ++i)
        delete [] Bptr[i];
    delete [] Bptr;

    return result;
}

JNIEXPORT jintArray JNICALL Java_com_example_kaky_ndktest_MainActivity_jjCMul
(JNIEnv *env, jclass clazz, jintArray A, jintArray B, jint n, jint m, jint k) {
    jint *C = new jint[n*k];
    jint *Aarray, *Barray;
    jboolean isCopy;
    jint i = 0, j = 0, x = 0;

  //  std::vector<jint> v;
    jintArray newArray = env->NewIntArray(n*k);

    Aarray = (env)->GetIntArrayElements(A, &isCopy);
    Barray = (env)->GetIntArrayElements(B, &isCopy);

  /*  if(Aarray == 0 || Barray == 0) {
        return -1;
    }*/

    for(i = 0; i < n; i++) {
        for(j = 0; j < k; j++) {
            jint tmp = 0;
            for(x = 0; x < m; x++) {
                tmp += Aarray[i * m + x] * Barray[x * k + j];
            }
        C[i * k + j] = tmp;
        }
    }

    (env)->ReleaseIntArrayElements(A, Aarray, JNI_ABORT);
    (env)->ReleaseIntArrayElements(B, Barray, JNI_ABORT);

    env->SetIntArrayRegion(newArray, 0, n*k,C );
    env->ReleaseIntArrayElements(newArray, C, 0);

   // delete C;
    return newArray;
}