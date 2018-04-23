package com.jish.daoyin.tan;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import static android.app.Activity.RESULT_CANCELED;
import static android.support.v4.app.ActivityCompat.startActivityForResult;

/**
 * Created by Adminjs on 2018/4/9.
 */

public class DiaLogTan extends Dialog implements View.OnClickListener {
    private Intent data;
    private int resultCode = 1;
    private static final int FILE_SELECT_CODE = 0;
    private static final int TAKE_ALBUM_REQUEST_THREE = 0;
    private final AlertDialog dialog;
    private static final int IMAGE = 1;

    public DiaLogTan(@NonNull final Context context) {
        super(context);
        dialog = new AlertDialog.Builder(context, R.style.CustomDialog).create();
        dialog.show();

        //加载自己的布局
        dialog.getWindow().setContentView(R.layout.popup_window);
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();

        //这里设置居中
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);
    //    dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        LinearLayout phote = dialog.findViewById(R.id.photo);
        LinearLayout filen = dialog.findViewById(R.id.filen);
        LinearLayout tvfile = dialog.findViewById(R.id.tvfile);
        LinearLayout musicfile = dialog.findViewById(R.id.musicfile);
        LinearLayout shootphoto =  dialog.findViewById(R.id.shootphoto);
        LinearLayout shoottv = dialog.findViewById(R.id.shoottv);
        LinearLayout muscord = dialog.findViewById(R.id.muscord);
        phote.setOnClickListener(this);
        filen.setOnClickListener(this);
        tvfile.setOnClickListener(this);
        musicfile.setOnClickListener(this);
        shootphoto.setOnClickListener(this);
        shoottv.setOnClickListener(this);
        muscord.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.photo:
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
               getContext().startActivity(intent);
                ((Activity)getContext()).startActivityForResult(intent, TAKE_ALBUM_REQUEST_THREE);
                break;
            case R.id.filen:
                Intent intentt = new Intent(Intent.ACTION_GET_CONTENT);
                intentt.setType("*/*");
                intentt.addCategory(Intent.CATEGORY_OPENABLE);
                getContext().startActivity(intentt);
                break;
            case R.id.tvfile:
                Intent intenta = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                getContext().startActivity(intenta);
                break;
            case R.id.musicfile:
                Intent intentn = new Intent(Intent.ACTION_PICK,
                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                getContext().startActivity(intentn);
                break;
            case R.id.shootphoto:
                Intent intento = new Intent("android.media.action.IMAGE_CAPTURE");
                getContext().startActivity(intento);
                break;
            case R.id.shoottv:
                Intent intenten = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                getContext().startActivity(intenten);
                break;
            case R.id.muscord:
                Intent intentan = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
                getContext().startActivity(intentan);
                break;
        }
    }


}

