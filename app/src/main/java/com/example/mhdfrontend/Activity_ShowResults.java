package com.example.mhdfrontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class Activity_ShowResults extends AppCompatActivity {

    String filePath;
    String filePath2;
    ImageView imageView, imageView2;
    TextView textView1, textView2;
    Switch OCRSwitch;
    String printResult;
    String printResult2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_results);

        Log.v("Debug_ALL", "Activity Show Result started");

        printResult = null;
        printResult2 = null;
        imageView = (ImageView) findViewById(R.id.imageViewExample);
        imageView2 = (ImageView) findViewById(R.id.imageViewExample2);
        textView1 = (TextView) findViewById(R.id.textViewExample);
        textView2 = (TextView) findViewById(R.id.textViewExample2);
        OCRSwitch = (Switch) findViewById(R.id.switch1);

        textView2.setVisibility(View.INVISIBLE);
        OCRSwitch.setChecked(false);

        OCRSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    textView1.setVisibility(View.INVISIBLE);
                    textView2.setVisibility(View.VISIBLE);
                }else{
                    textView1.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.INVISIBLE);
                }
            }
        });

        //Writing to textview1
        ArrayList<String> myList = (ArrayList<String>) getIntent().getSerializableExtra("test3");
        for(String result : myList){
            if(printResult==null){
                printResult = result + System.getProperty("line.separator");
            }else {
                printResult = printResult + result + System.getProperty("line.separator");
                Log.v("Debug_ALL", "Result: " + result);
            }
        }
        textView1.setText(printResult);

        //Writing to textview2
        printResult2 = getIntent().getStringExtra("test4");

        Log.v("DebugStarExtra", "Got following value in new activity: " + printResult2);

        textView2.setText(printResult2);



        Intent intent = getIntent();
        filePath = intent.getStringExtra("test");
        filePath2 = intent.getStringExtra("test2");
        Log.v("Debug_ALL", "File path has been opened the path is:\n" + filePath);

        Log.v("Debug_ALL", "Opening file");
        File imgFile = new File(filePath);
        File imgFile2 = new File(filePath2);
        Log.v("Debug_ALL", "File opened");

        if(imgFile.exists()){
            Log.v("Debug_ALL", "File exists");
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            imageView.setImageBitmap(myBitmap);
        }
        if(imgFile2.exists()){
            Log.v("Debug_ALL", "File exists");
            Bitmap myBitmap2 = BitmapFactory.decodeFile(imgFile2.getAbsolutePath());
            imageView2.setImageBitmap(myBitmap2);
        }
    }
}