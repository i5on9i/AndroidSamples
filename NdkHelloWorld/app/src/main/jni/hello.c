#include "com_namh_ndkhelloworld_MainActivity.h"

JNIEXPORT jstring JNICALL Java_com_namh_ndkhelloworld_MainActivity_stringFromJNI
  (JNIEnv * env, jobject obj)
  {
    return (*env)->NewStringUTF(env, "hello jni !");
  }
