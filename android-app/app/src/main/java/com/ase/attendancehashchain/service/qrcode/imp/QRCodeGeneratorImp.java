package com.ase.attendancehashchain.service.qrcode.imp;

import android.graphics.Bitmap;
import android.util.Log;

import com.ase.attendancehashchain.service.qrcode.QRCodeGenerator;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import static com.ase.attendancehashchain.util.Constants.*;

/**
 * Created by MarcoSalazar on 21/01/18.
 *
 *
 */

public class QRCodeGeneratorImp implements QRCodeGenerator {

    @Override
    public Bitmap encodeAsBitmap(String message) throws WriterException {

        BitMatrix result;

        try {
            result = new MultiFormatWriter().encode(message, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, null);
        } catch (IllegalArgumentException iaEx) {
            Log.e(QRCODE_LOG_TAG, "Invalid qr code format. Error: " + iaEx.getMessage());
            return null;
        }

        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[width * height];

        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);

        return bitmap;
    }
}
