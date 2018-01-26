package com.ase.attendancehashchain.service.qrcode;

import android.graphics.Bitmap;

import com.google.zxing.WriterException;

/**
 * Created by MarcoSalazar on 21/01/18.
 */

public interface QRCodeGenerator {
    Bitmap encodeAsBitmap(String str) throws WriterException;
}
