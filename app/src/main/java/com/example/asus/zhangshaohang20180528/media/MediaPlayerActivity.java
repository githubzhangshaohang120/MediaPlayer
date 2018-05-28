package com.example.asus.zhangshaohang20180528.media;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.asus.zhangshaohang20180528.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
                   _ooOoo_
                  o8888888o
                  88" . "88
                  (| -_- |)
                  O\  =  /O
               ____/`---'\____
             .'  \\|     |//  `.
            /  \\|||  :  |||//  \
           /  _||||| -:- |||||-  \
           |   | \\\  -  /// |   |
           | \_|  ''\---/''  |   |
           \  .-\__  `-`  ___/-. /
         ___`. .'  /--.--\  `. . __
      ."" '<  `.___\_<|>_/___.'  >'"".
     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
     \  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
                   `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         佛祖保佑       永无BUG
*/
public class MediaPlayerActivity extends AppCompatActivity{


    private MediaPlayer mp;//mediaPlayer对象
    private Button play,pause,stop;//播放 暂停/继续 停止 按钮
    private TextView hint;//显示当前播放状态
    private boolean isPause=false;//是否暂停
    private GramophoneView gramophoneView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        play=(Button) findViewById(R.id.button1);
        pause=(Button) findViewById(R.id.button2);
        stop=(Button) findViewById(R.id.button3);
        hint=(TextView) findViewById(R.id.hint);
        hint.setTextSize(20);
        mp=MediaPlayer.create(MediaPlayerActivity.this, R.raw.aa);//创建mediaplayer对象

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer arg0) {
                // TODO Auto-generated method stub
                play();//重新开始播放
            }
        });

        play.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                play();
                if(isPause){
                    pause.setText("暂停");
                    isPause=false;
                }
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(mp.isPlaying()&&!isPause){
                    mp.pause();
                    isPause=true;
                    pause.setText("继续");
                    hint.setText("暂停播放音频...");
                    play.setEnabled(true);
                    if(gramophoneView.getPlaying()){
                        play.setText("点击播放");

                    }

                }else{
                    mp.start();
                    pause.setText("暂停");
                    hint.setText("继续播放音频...");
                    isPause=false;
                    play.setEnabled(false);
                    if(!gramophoneView.getPlaying()){
                        stop.setText("点击播放");

                    }
                }
            }
        });


        stop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                mp.stop();
                hint.setText("停止播放音频...");
                pause.setEnabled(false);
                stop.setEnabled(false);
                play.setEnabled(true);
                if(!gramophoneView.getPlaying()){
                    stop.setText("点击停止");

                }
            }
        });


        modela();
    }

    private void modela() {
        gramophoneView = (GramophoneView)findViewById(R.id.gramophone_view);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gramophoneView.getPlaying()){
                    play.setText("点击播放");

                }
                gramophoneView.setPlaying(!gramophoneView.getPlaying());
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!gramophoneView.getPlaying()){
                    stop.setText("点击暂停");
                }else{

                }
                gramophoneView.setPlaying(!gramophoneView.getPlaying());
            }
        });
    }

    private void play(){
        if(gramophoneView.getPlaying()){
            play.setText("点击播放");

        }
        try{
            mp.reset();
            mp=MediaPlayer.create(MediaPlayerActivity.this, R.raw.aa);//重新设置要播放的音频
            mp.start();//开始播放
            hint.setText("正在播放音频...");
            play.setEnabled(false);
            pause.setEnabled(true);
            stop.setEnabled(true);
        }catch(Exception e){
            e.printStackTrace();//输出异常信息
        }
    }

    protected void onDestroy() {
        // TODO Auto-generated method stub
        if(mp.isPlaying()){
            mp.stop();
        }
        mp.release();//释放资源
        super.onDestroy();
    }
}
