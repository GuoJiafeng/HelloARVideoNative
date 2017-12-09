package cn.easyar.samples.helloarvideo.network;

/**
 * Created by BlackBeard丶 on 2017/5/6.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 文件下载utils
 * Created by JIANG on 2016/10/19.
 */
public class HttpClientUtils {
    private static final java.lang.String TAG ="HttpClientUtils" ;
    private String urlPath ;
    private String outPath;
    private String fileName;

    /**
     *
     * @param urlPath
     * @param outPath
     * @param fileName
     */
    public HttpClientUtils(String urlPath, String outPath, String fileName) {
        this.fileName = fileName;
        this.outPath = outPath;
        this.urlPath = urlPath;
    }

    /**
     * 下载文件
     */
    public void downloadFile()
    {
        // 启动新线程下载软件
        new downloadFileThread().start();
    }
    /**
     * 下载文件线程
     */
    private class downloadFileThread extends Thread {
        @Override
        public void run() {
            try {

                // 判断SD卡是否存在，并且是否具有读写权限
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

                    BufferedOutputStream os = null;
                    Bitmap bitmap;

                    // 获得存储卡的路径
                    //http://182.92.158.132/cloud/cloudfile?path=/public/notice/201606101737302282/16-131109141345 (1)_20160610173730880.jpg
                    //http://182.92.158.132/cloud/cloudfile?path=/public/notice/201606101737302282/16-131109141345%20(1)_20160610173730880.jpg
                    Log.d("ggggg", Environment.getExternalStorageState().toString());
                    Log.d("ggggg","我是路径-===="+urlPath);
                    Log.d("ggggg","下载开始");
                    URL url = new URL(urlPath);
                    // 创建连接
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.connect();
                    Log.d("ggggg","头："+conn.getHeaderFields());;
                    // 获取文件大小
                    int length = conn.getContentLength();
                    Log.d("ggggg","length .." + length);
                    // 创建输入流
                    InputStream is = conn.getInputStream();
                    bitmap= BitmapFactory.decodeStream(is);

                    File file = new File("/sdcard/gjf");
                    // 判断文件目录是否存在
                    if (!file.exists()) {
                        Log.d("ggggg","草泥马");
                        file.mkdirs();
                    }
                    File apkFile = new File("/sdcard/gjf",fileName);
                    FileOutputStream fos = new FileOutputStream(apkFile);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100,fos);
//                    int count = 0;
//                    // 缓存
//                    byte buf[] = new byte[1024];
//                    // 写入到文件中
//
//                    int numread = is.read(buf);
//                    count += numread;
//                    L.d("ggggg","count .."+ count +"  numread .." + numread);
//                    // 写入文件
//                    fos.write(buf, 100, numread);
                    fos.close();
                    is.close();

                }else {
                    //Log.d("ggggg","没有开始下载");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public HttpClientUtils writeSDFile(final String str, final String fileName) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Log.d("ggggg","开始");
                    FileWriter fw = new FileWriter("/storage/emulated/0/gjf" + "//" + fileName);
                    File f = new File("/storage/emulated/0/gjf" + "//" + fileName);
                    fw.write(str);
                    FileOutputStream os = new FileOutputStream(f);
                    DataOutputStream out = new DataOutputStream(os);
                    out.writeShort(2);
                    out.writeUTF("");
                    System.out.println(out);
                    fw.flush();
                    fw.close();
                    System.out.println(fw);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

        return null;
    }
}
