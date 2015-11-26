//
// Created by namh on 2015-11-26.
//

#include "com_namh_ndkhelloworld2_MainActivity.h"
JNIEXPORT jstring JNICALL Java_com_namh_ndkhelloworld2_MainActivity_getStringNative(JNIEnv *env, jobject obj){
    return (*env)->NewStringUTF(env, "Hello from JNI!");

}