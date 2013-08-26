package com.androidtest.test1;

import android.text.TextUtils;

import android.view.View;

import android.view.View;

import android.os.SystemClock;

import android.util.Log;

import android.os.Looper;

import android.widget.TextView;

import android.widget.Button;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.message_activity)
public class MessageActivity extends RoboActivity {

  private class WorkerThread extends Thread {

    private final String TAG = WorkerThread.class.getName();

    private Handler handler;
    private Looper looper;

    public WorkerThread() {
      start();
    }

    public void executeTask(String text) {
      if (looper == null || handler == null) {
        Message message = Message.obtain();
        message.obj = "Sorry man, it is out of service";

        mainHandler.sendMessage(message);
        return;
      }
      Message message = Message.obtain();
      message.obj = text;
      handler.sendMessage(message);
    }

    public void exit() {
      if (looper == null) {
        return;
      }
      looper.quit();
      looper = null;
    }

    @Override
    public void run() {
      Looper.prepare();

      looper = Looper.myLooper();

      handler = new Handler(looper) {
        @Override
        public void handleMessage(Message msg) {
          StringBuffer sb = new StringBuffer();
          sb.append("it is my please to serve you, please be patient to wait!\n");
          Log.e(TAG, "workerthread, it is my please to serve you, please be patient to wait!");
          for (int i = 0; i < 100; i++) {
            sb.append(".");
            Message message = Message.obtain();
            message.obj = sb.toString();
            mainHandler.sendMessage(message);
            Log.e(TAG, "workthread, working" + sb.toString());
            SystemClock.sleep(1000);
          }
          Log.e(TAG, "workerthread, your work is done.");
          sb.append("\nyour work is done");
          Message newMsg = Message.obtain();
          newMsg.obj = sb.toString();
          mainHandler.sendMessage(newMsg);
        }
      };

      Looper.loop();
    }
  }

  private Handler mainHandler;
  private WorkerThread workerThread;

  @InjectView(R.id.action)
  Button actionButton;
  @InjectView(R.id.end)
  Button endButton;

  @InjectView(R.id.display)
  TextView displayView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mainHandler = new Handler(new Handler.Callback() {

      @Override
      public boolean handleMessage(Message msg) {
        String str = (String) msg.obj;
        if (TextUtils.isEmpty(str)) {
          return false;
        }
        displayView.setText(str);
        return false;
      }
    });

    workerThread = new WorkerThread();
    actionButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        workerThread.executeTask("please do me a favor");
      }
    });
    endButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        workerThread.exit();
      }
    });
    displayView.setText("Press 'do me a favor' to execute a task, press 'end of service' to stop looper thread");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    workerThread.exit();
    workerThread = null;
  }

}
