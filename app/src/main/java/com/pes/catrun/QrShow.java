package com.pes.catrun;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class QrShow extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_show);
        imageView = (ImageView) findViewById(R.id.qr_show);
        Bitmap bitmap = getIntent().getParcelableExtra("pic");
        imageView.setImageBitmap(bitmap);
    }
}
