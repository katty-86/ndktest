#include "com_example_kaky_ndktest_MainActivity.h"
#include <iostream>
#include <vector>
#include <functional>   // std::greater
#include <algorithm>


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


}
