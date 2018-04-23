package com.jish.daoyin.tan;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private DiaLogTan diaLogTan;
    private Button tan;
    private AlertDialog dialog;
    private static final int IMAGE = 1;
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private static final String IMAGE_FILE_NAME = "temp_head_image.jpg";

    // 裁剪后图片的宽(X)和高(Y),480 X 480的正方形。（生成bitmap貌似有时要报错？可试下把大小弄小点）
    private static int output_X = 480;
    private static int output_Y = 480;
    private ImageView headImage = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tan = findViewById(R.id.tan);
        headImage = (ImageView) findViewById(R.id.imageView);

        tan.setOnClickListener(new View.OnClickListener() {


         @Override
         public void onClick(View view) {
            diaLogTan = new DiaLogTan(MainActivity.this);
             //调用相册
 //           diaLogTan();
         }

     });

    }

//    private void diaLogTan() {
//        dialog = new AlertDialog.Builder(MainActivity.this, R.style.CustomDialog).create();
//        dialog.show();
//
//        //加载自己的布局
//        dialog.getWindow().setContentView(R.layout.popup_window);
//        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
//
//        //这里设置居中
//        lp.gravity = Gravity.CENTER;
//        dialog.getWindow().setAttributes(lp);
//        //    dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        LinearLayout phote = dialog.findViewById(R.id.photo);
//        LinearLayout filen = dialog.findViewById(R.id.filen);
//        LinearLayout tvfile = dialog.findViewById(R.id.tvfile);
//        LinearLayout musicfile = dialog.findViewById(R.id.musicfile);
//        LinearLayout shootphoto =  dialog.findViewById(R.id.shootphoto);
//        LinearLayout shoottv = dialog.findViewById(R.id.shoottv);
//        LinearLayout muscord = dialog.findViewById(R.id.muscord);
//        phote.setOnClickListener(this);
//        filen.setOnClickListener(this);
//        tvfile.setOnClickListener(this);
//        musicfile.setOnClickListener(this);
//        shootphoto.setOnClickListener(this);
//        shoottv.setOnClickListener(this);
//        muscord.setOnClickListener(this);
//
//    }
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.photo:
//                Intent intent = new Intent(Intent.ACTION_PICK,
//                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                // aspectX , aspectY :宽高的比例
//                intent.putExtra("aspectX", 1);
//                intent.putExtra("aspectY", 1);
//
//                // outputX , outputY : 裁剪图片宽高
//                intent.putExtra("outputX", output_X);
//                intent.putExtra("outputY", output_Y);
//                intent.putExtra("return-data", true);
//                //       getContext().startActivity(intent);
//
//                startActivityForResult(intent, CODE_GALLERY_REQUEST);
//                break;
//            case R.id.filen:
//                Intent intentt = new Intent(Intent.ACTION_GET_CONTENT);
//                intentt.setType("*/*");
//                intentt.addCategory(Intent.CATEGORY_OPENABLE);
//           //     getContext().startActivity(intentt);
//                break;
//            case R.id.tvfile:
//                Intent intenta = new Intent(Intent.ACTION_PICK,
//                        android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
//            //    getContext().startActivity(intenta);
//                break;
//            case R.id.musicfile:
//                Intent intentn = new Intent(Intent.ACTION_PICK,
//                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
//           //     getContext().startActivity(intentn);
//                break;
//            case R.id.shootphoto:
//                Intent intento = new Intent("android.media.action.IMAGE_CAPTURE");
//           //     getContext().startActivity(intento);
//                break;
//            case R.id.shoottv:
//                Intent intenten = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//          //      getContext().startActivity(intenten);
//                break;
//            case R.id.muscord:
//                Intent intentan = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
//           //     getContext().startActivity(intentan);
//                break;
//        }
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode,
//                                    Intent intent) {
//
//        // 用户没有进行有效的设置操作，返回
//        if (resultCode == RESULT_CANCELED) {
//            Toast.makeText(getApplication(), "取消", Toast.LENGTH_LONG).show();
//            return;
//        }
//
//        switch (requestCode) {
//            case CODE_GALLERY_REQUEST:
//                cropRawPhoto(intent.getData());
//                break;
//
//            case CODE_CAMERA_REQUEST:
//                if (hasSdcard()) {
//                    File tempFile = new File(
//                            Environment.getExternalStorageDirectory(),
//                            IMAGE_FILE_NAME);
//                    cropRawPhoto(Uri.fromFile(tempFile));
//                } else {
//                    Toast.makeText(getApplication(), "没有SDCard!", Toast.LENGTH_LONG)
//                            .show();
//                }
//
//                break;
//
//            case CODE_RESULT_REQUEST:
//                if (intent != null) {
//                    setImageToHeadView(intent);
//                }
//
//                break;
//        }
//
//        super.onActivityResult(requestCode, resultCode, intent);
//    }
//    public void cropRawPhoto(Uri uri) {
//
//        Intent intent = new Intent("com.android.camera.action.CROP");
//        intent.setDataAndType(uri, "image/*");
//
//        // 设置裁剪
//        intent.putExtra("crop", "true");
//
//        // aspectX , aspectY :宽高的比例
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
//
//        // outputX , outputY : 裁剪图片宽高
//        intent.putExtra("outputX", output_X);
//        intent.putExtra("outputY", output_Y);
//        intent.putExtra("return-data", true);
//
//        startActivityForResult(intent, CODE_RESULT_REQUEST);
//    }
//
//    /**
//     * 提取保存裁剪之后的图片数据，并设置头像部分的View
//     */
//    private void setImageToHeadView(Intent intent) {
//        Bundle extras = intent.getExtras();
//        if (extras != null) {
//            Bitmap photo = extras.getParcelable("data");
//            headImage.setImageBitmap(photo);
//            dialog.getWindow().closeAllPanels();
//        }
//    }
//
//    /**
//     * 检查设备是否存在SDCard的工具方法
//     */
//    public static boolean hasSdcard() {
//        String state = Environment.getExternalStorageState();
//        if (state.equals(Environment.MEDIA_MOUNTED)) {
//            // 有存储的SDCard
//            return true;
//        } else {
//            return false;
//        }
//    }
}
