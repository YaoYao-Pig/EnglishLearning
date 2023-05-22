package com.example.englishlearning.Controller;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class DownLoadController {


    private void downLoad(Context context,String str_url, String filename){
        Uri uri=Uri.parse(str_url);
        DownloadManager downloadManager= (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request=new DownloadManager.Request(uri);

        request.setAllowedNetworkTypes(request.NETWORK_MOBILE | request.NETWORK_WIFI);
        //设置是否允许漫游
        request.setAllowedOverRoaming(true);
        //设置文件类型
        //request.setMimeType("video/*");//表示视频类型
        //在通知栏中显示
        request.setNotificationVisibility(request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setTitle(filename);
        request.setDescription("视频正在下载");
        request.setVisibleInDownloadsUi(true);
        // 设置为可被媒体扫描器找到
        request.allowScanningByMediaScanner();
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename);



        // 将下载请求放入队列
        long   downloadId = downloadManager.enqueue(request);


        System.out.println(downloadId);
        System.out.println(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));



//
    }

    public boolean downLoadBookContent(Context context, Integer id){
        BookController bookController=new BookController();
        String url=bookController.getContentUrl(id);
        String filename=bookController.getTitle(id)+".zip";


        downLoad(context,url,filename);
        return true;
    }


}





//广播接受者，接收下载状态
//        BroadcastReceiver receiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                String action = intent.getAction();
//                if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
//                    long downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);
//                    //检查下载状态
//                    checkDownloadStatus(downloadId );
//                }
//            }
//
//            //检查下载状态
//            private void checkDownloadStatus(long downloadId ) {
//                DownloadManager.Query query = new DownloadManager.Query();
//                query.setFilterById(downloadId );//筛选下载任务，传入任务ID，可变参数
//                Cursor c = downloadManager.query(query);
//                if (c.moveToFirst()) {
//                    @SuppressLint("Range") int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
//                    switch (status) {
//                        case DownloadManager.STATUS_PAUSED:
//                            Log.i("1",">>>下载暂停");
//                        case DownloadManager.STATUS_PENDING:
//                            Log.i("1",">>>下载延迟");
//                        case DownloadManager.STATUS_RUNNING:
//                            Log.i("1",">>>正在下载");// 此处无法监听到
//                            break;
//                        case DownloadManager.STATUS_SUCCESSFUL:
//                            Log.i("1",">>>下载完成");
//                            break;
//                        case DownloadManager.STATUS_FAILED:
//                            Log.i("1",">>>下载失败");
//                            break;
//                    }
//                }
//            }
//        };
//        // 刷新下载进度
//        Timer timer = new Timer();
//        TimerTask task = new TimerTask() {
//            @SuppressLint("Range")
//            @Override
//            public void run() {
//                DownloadManager.Query query = new DownloadManager.Query();
//                Cursor cursor = downloadManager.query(query.setFilterById(downloadId ));
//                if (cursor != null && cursor.moveToFirst()) {
//                    if (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
//                        // 成功后取消监听
//                        task.cancel();
//                    }
//
//                    int bytesDownloaded = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
//                    int bytesTotal = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
//                    int pro = (bytesDownloaded * 100) / bytesTotal;
//                    Log.i("1","下载进度 bytesDownloaded" + bytesDownloaded + "总文件大小：bytesTotal" + bytesTotal);
//                    Log.i("1",">>>下载进度 pro" + pro);
//                }
//                cursor.close();
//            }
//        };
//        timer.schedule(task, 0, 1000);