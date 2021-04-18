package com.bss.uis.ui;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.bss.uis.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class UIUtil {
    public static Button getButtonWithImg(Context object, Drawable icon, String btnText, int bkg_color, int height, int width) {
        Button button = new Button(object);
        button.setText(btnText);
        button.setBackgroundResource(bkg_color);
        button.setHeight(height);
        button.setWidth(width);
        button.setPadding(5, 5, 5, 5);
        button.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
        return button;
    }
    public static TextInputLayout getTextInputLayout(Context object, int id, int height, int width, int inputtType, String hint) {
        TextInputLayout textInputLayout = new TextInputLayout(object);
        LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        editTextParams.topMargin = 20;
        textInputLayout.setHint(hint);
        TextInputEditText textInputEditText = new TextInputEditText(object);
        textInputEditText.setHeight(height);
        textInputEditText.setWidth(width);
        textInputEditText.setGravity(Gravity.CENTER);
        textInputEditText.setId(id);
        textInputEditText.setInputType(inputtType);
        textInputLayout.addView(textInputEditText, editTextParams);
        return textInputLayout;
    }
    public static int getscreenheight(Window window) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        window.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        return height;
    }

   public static int getscreenwidth(Window window) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
       window.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        return width;
    }
    public static Drawable decodeDrawable(Context context, String name,String base64) {
        Drawable ret = null;
        if (!base64.equals("")) {
            ByteArrayInputStream bais = new ByteArrayInputStream(
                    Base64.decode(base64.getBytes(), Base64.DEFAULT));
            ret = Drawable.createFromResourceStream(context.getResources(),
                    null, bais, null, null);
            try {
                bais.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }
    public static ImageView getImageViewBitMap(Context context, Bitmap bitmap)
    {
        ImageView imageView = new ImageView(context);
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams( LayoutParams.WRAP_CONTENT,  LayoutParams.WRAP_CONTENT);
        imageParams.setMargins(10,5,10,10);
        imageView.setLayoutParams(imageParams);
        imageView.setImageBitmap(bitmap);
        return imageView;
    }
    public static LinearLayout getCloseableLinearLayout(Context context, int orientation, int index, int bgResource, int xHeight, int xWidth)
    {
       final LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(orientation);
        linearLayout.setId(index);
        if (bgResource > -1)
            linearLayout.setBackgroundResource(bgResource);
        LinearLayout.LayoutParams paramsLinearLayout = new LinearLayout.LayoutParams(xWidth, xHeight);
        paramsLinearLayout.setMargins(1, 1, 1, 1);
        linearLayout.setLayoutParams(paramsLinearLayout);
        LinearLayout imageLayout = new LinearLayout(context);
        imageLayout.setGravity(Gravity.RIGHT);
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(R.drawable.ic_close);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(30, 30));
        imageLayout.addView(imageView);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.removeAllViews();

            }
        });
        linearLayout.addView(imageLayout);
        return linearLayout;
    }
    public static LinearLayout getLinearLayout(Context context, int orientation, int index, int bgResource, int xHeight, int xWidth) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(orientation);
        linearLayout.setId(index);
        if (bgResource > -1)
            linearLayout.setBackgroundResource(bgResource);
        LinearLayout.LayoutParams paramsLinearLayout = new LinearLayout.LayoutParams(xWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
        paramsLinearLayout.setMargins(10, 5, 10, 15);
        linearLayout.setLayoutParams(paramsLinearLayout);
        return linearLayout;
    }
    public static Dialog getPopupDialog(Context context)
    {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_address);
        dialog.getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
        return dialog;
    }
    public static Bitmap getScaledBitmap(Bitmap bitmap,int newWidth,int newHeight)
    {
        Bitmap scaledBitmap = Bitmap.createBitmap(newWidth, newHeight, Config.ARGB_8888);

        float ratioX = newWidth / (float) bitmap.getWidth();
        float ratioY = newHeight / (float) bitmap.getHeight();
        float middleX = newWidth / 2.0f;
        float middleY = newHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bitmap, middleX - bitmap.getWidth() / 2, middleY - bitmap.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

        return scaledBitmap;
    }
    public static Dialog getImagePopupDialog(Context context)
    {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.image_viewer);
        ImageView closeImageView = dialog.findViewById(R.id.closeImageViewer);
        closeImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
        return dialog;
    }
}
