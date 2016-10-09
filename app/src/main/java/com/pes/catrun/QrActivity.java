package com.pes.catrun;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QrActivity extends AppCompatActivity {

    private Button button, button2;
    private EditText text1, text2;
    private ImageView qrImage;
    final Context context = this;
    final Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        button = (Button) this.findViewById(R.id.f_qr_readqr);
        button2 = (Button) this.findViewById(R.id.f_qr_generate_qr);
        text1 = (EditText) this.findViewById(R.id.editText1);
        text2 = (EditText) this.findViewById(R.id.editText2);
        qrImage = (ImageView) this.findViewById(R.id.imageView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //Toast.makeText(getContext(), "Not implemented", Toast.LENGTH_SHORT).show();
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String textToEncode = "Hola MUNDOOO";
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try{
                    BitMatrix bitMatrix = multiFormatWriter.encode(textToEncode, BarcodeFormat.QR_CODE,200,200);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    Intent intent = new Intent(context, QrShow.class);
                    intent.putExtra("pic",bitmap);
                    context.startActivity(intent);

                } catch (WriterException e) {
                        e.printStackTrace();
                }
            }
        });




    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null){
            if(result.getContents() == null){
                Log.d("MainActivity","Cancelled Scan");
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
            } else {
                Log.d("MainActivity","Scanned");
                Toast.makeText(this, result.getContents() , Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
