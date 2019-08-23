package com.wong.newfixedthreadpooldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
           final int index = i;
           newFixedThreadPool.execute(new Runnable() {
               @Override
               public void run() {
                   Thread.currentThread().setName("Thread ID#"+Thread.currentThread().getId()+"#Thread i ="+ index);
                   Log.d("newFixedThreadPool#",Thread.currentThread().getName()+" index = "+ index);
                   try{
                       Thread.sleep(500);
                   }catch (Exception e){
                       e.printStackTrace();
                   }
               }
           });
        }

        // 一定要手动关闭线程池中的线程，否则，它们会一直存在
        newFixedThreadPool.shutdown();
        // 测试        // 2019-08-23 10:42:10.681 25505-25665/com.wong.newfixedthreadpooldemo D/newFixedThreadPool#: Thread ID#96406#Thread i =2 index = 2
        // 2019-08-23 10:42:10.681 25505-25664/com.wong.newfixedthreadpooldemo D/newFixedThreadPool#: Thread ID#96405#Thread i =1 index = 1
        // 2019-08-23 10:42:10.683 25505-25663/com.wong.newfixedthreadpooldemo D/newFixedThreadPool#: Thread ID#96404#Thread i =0 index = 0
        // 2019-08-23 10:42:11.195 25505-25665/com.wong.newfixedthreadpooldemo D/newFixedThreadPool#: Thread ID#96406#Thread i =3 index = 3
        // 2019-08-23 10:42:11.195 25505-25663/com.wong.newfixedthreadpooldemo D/newFixedThreadPool#: Thread ID#96404#Thread i =4 index = 4
        // 2019-08-23 10:42:11.221 25505-25664/com.wong.newfixedthreadpooldemo D/newFixedThreadPool#: Thread ID#96405#Thread i =5 index = 5
        // 2019-08-23 10:42:11.723 25505-25664/com.wong.newfixedthreadpooldemo D/newFixedThreadPool#: Thread ID#96405#Thread i =6 index = 6
        // 2019-08-23 10:42:11.724 25505-25665/com.wong.newfixedthreadpooldemo D/newFixedThreadPool#: Thread ID#96406#Thread i =7 index = 7
        // 2019-08-23 10:42:11.732 25505-25663/com.wong.newfixedthreadpooldemo D/newFixedThreadPool#: Thread ID#96404#Thread i =8 index = 8
        // 2019-08-23 10:42:12.228 25505-25665/com.wong.newfixedthreadpooldemo D/newFixedThreadPool#: Thread ID#96406#Thread i =9 index = 9
    }
}
