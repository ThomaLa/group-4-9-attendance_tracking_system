package com.ase.attendancehashchain.ui;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.ase.attendancehashchain.R;
import com.ase.attendancehashchain.service.qrcode.QRCodeGenerator;
import com.ase.attendancehashchain.service.qrcode.imp.QRCodeGeneratorImp;
import com.google.zxing.WriterException;

public class QRCodeActivity extends AppCompatActivity {

    QRCodeGenerator qrCodeGenerator = new QRCodeGeneratorImp();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        ImageView imageView = findViewById(R.id.myImage);

        try {
            Bitmap bitmap = qrCodeGenerator.encodeAsBitmap("Encoding a random string is working!");
            imageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
