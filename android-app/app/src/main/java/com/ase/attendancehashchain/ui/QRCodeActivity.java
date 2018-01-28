package com.ase.attendancehashchain.ui;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.ase.attendancehashchain.R;
import com.ase.attendancehashchain.service.qrcode.QRCodeGenerator;
import com.ase.attendancehashchain.service.qrcode.imp.QRCodeGeneratorImp;
import com.google.zxing.WriterException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class QRCodeActivity extends AppCompatActivity {

    QRCodeGenerator qrCodeGenerator = new QRCodeGeneratorImp();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        ImageView imageView = findViewById(R.id.myImage);

        String username = getIntent().getStringExtra("username");
        String weekOfYear = getIntent().getStringExtra("weekOfYear");
        String tutorialGroup = getIntent().getStringExtra("tutorialGroup");

        JSONObject attendanceCodeContent = new JSONObject();

        try {
            attendanceCodeContent.put("username", username);
            attendanceCodeContent.put("weekOfYear", weekOfYear);
            attendanceCodeContent.put("tutorialGroup", tutorialGroup);
        } catch (JSONException jEx) {
            jEx.printStackTrace();
        }

        try {
            Bitmap bitmap = qrCodeGenerator.encodeAsBitmap(attendanceCodeContent.toString());
            imageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
