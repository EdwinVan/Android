#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_ndkt_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT jint JNICALL
Java_com_example_ndkt_MainActivity_getMaxPrime(
        JNIEnv* env,
        jobject /* this */,jint n) {
    int i,j,max=0;
    int m;
    for (int i = 1; i <= n; i++) {
        m=1;
        for (int j = 2; j <i ; j++) {
            if(i%j==0){
                m=0;
                break;
            }
        }
        if (m==1){
            max=i;
        }
    }
    return (jint)max;
}