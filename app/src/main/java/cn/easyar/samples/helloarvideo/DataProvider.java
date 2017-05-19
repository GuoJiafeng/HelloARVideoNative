package cn.easyar.samples.helloarvideo;

/**
 * Created by BlackBeard丶 on 2017/5/2.
 */


public class DataProvider {

    /**
     * C调用java空方法
     */
    public void nullMethod() {
        System.out.println("hello from java");
    }
    /**
     * C调用java中的带两个int参数的方法
     *
     * @param x
     * @param y
     * @return
     */
    public int Add(int x, int y) {
        int result = x + y;
        System.out.println("result in java " + result);
        return result;
    }
    /**
     * C调用java中参数为String的方法
     *
     * @param s
     */
    public void printString(String s) {
        System.out.println("java " + s);
    }

    // 本地方法
    public native void callMethod1();
    public native void callMethod2();
    public native void callMethod3();
}
