//
// Created by BlackBeard丶 on 2017/5/3.
//

#include<stdio.h>
#include<jni.h>
#include"cn_easyar_samples_helloarvideo_DataProvider.h"
#include<android/log.h>
#define LOG_TAG "System.out.c"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
JNIEXPORT void JNICALL Java_cn_easyar_samples_helloarvideo_DataProvider_callMethod3
(JNIEnv * env, jobject obj){
//1.找到java代码native方法所在的字节码文件
//jclass (*FindClass)(JNIEnv*, const char*);
jclass clazz = (*env)->FindClass(env, "cn/easyar/samples/helloarvideo/DataProvider");
if(clazz == 0){
LOGD("find class error");
return;
}
LOGD("find class");
//2.找到class里面对应的方法
// jmethodID (*GetMethodID)(JNIEnv*, jclass, const char*, const char*);
jmethodID method3 = (*env)->GetMethodID(env,clazz,"printString","(Ljava/lang/String;)V");
if(method3 == 0){
LOGD("find method3 error");
return;
}
LOGD("find method3");
//3.调用方法
//void (*CallVoidMethod)(JNIEnv*, jobject, jmethodID, ...);
(*env)->CallVoidMethod(env, obj, method3,(*env)->NewStringUTF(env,"haha in C ."));
LOGD("method3 called");


}
