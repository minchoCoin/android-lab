package com.pnu.fashion.classify;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;

import androidx.core.util.Pair;

import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.Tensor;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;

public class Classifier {
    private static final String MODEL_NAME = "keras_model_cnn.tflite";

    Context context;
    Interpreter interpreter = null;
    int modelInputWidth, modelInputHeight, modelInputChannel;
    int modelOutputClasses;
    HashMap<Integer, String> modelLables = new HashMap<>();

    public Classifier(Context context) {
        this.context = context;
    }

    public void init() throws IOException {
        ByteBuffer model = loadModelFile(MODEL_NAME);
        model.order(ByteOrder.nativeOrder());
        interpreter = new Interpreter(model);

        initModelLables();
        initModelShape();
    }

    public Pair<String, Float> classify(Bitmap bitmap) {
        ByteBuffer buffer = convertBitmapToGrayByteBuffer(resizeBitmap(bitmap));

        float[][] result = new float[1][modelOutputClasses];

        interpreter.run(buffer, result);

        return argmax(result[0]);
    }

    private Pair<String, Float> argmax(float[] array) {
        int argmax = 0;
        float max = array[0];
        for (int i = 0; i < array.length; i++) {
            float f = array[i];
            if (f > max) {
                argmax = i;
                max = f;
            }
        }

        return new Pair<>(modelLables.get(argmax), max);
    }

    private ByteBuffer loadModelFile(String modelName) throws IOException {
        AssetManager am = context.getAssets();
        AssetFileDescriptor afd = am.openFd(modelName);
        FileInputStream fis = new FileInputStream(afd.getFileDescriptor());
        FileChannel fc = fis.getChannel();
        long startOffset = afd.getStartOffset();
        long declaredLength = afd.getDeclaredLength();

        return fc.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

    private void initModelShape() {
        Tensor inputTensor = interpreter.getInputTensor(0);
        int[] inputShape = inputTensor.shape();
        modelInputChannel = inputShape[0];
        modelInputWidth = inputShape[1];
        modelInputHeight = inputShape[2];

        Tensor outputTensor = interpreter.getOutputTensor(0);
        int[] outputShape = outputTensor.shape();
        modelOutputClasses = outputShape[1];
    }

    // https://keras.io/api/datasets/fashion_mnist/
    private void initModelLables() {
        String[] lables = {
            "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9"
        };

        for (int i=0; i<lables.length; i++) {
            modelLables.put(i, lables[i]);
        }
    }

    private Bitmap resizeBitmap(Bitmap bitmap) {
        return Bitmap.createScaledBitmap(bitmap, modelInputWidth, modelInputHeight, false);
    }

    private ByteBuffer convertBitmapToGrayByteBuffer(Bitmap bitmap) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(bitmap.getByteCount());
        byteBuffer.order(ByteOrder.nativeOrder());

        int[] pixels = new int[bitmap.getWidth() * bitmap.getHeight()];
        bitmap.getPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());

        for (int pixel : pixels) {
            int r = pixel >> 16 & 0xFF;
            int g = pixel >> 8 & 0xFF;
            int b = pixel & 0xFF;

            float avgPixelValue = (r + g + b) / 3.0f;
            float normalizedPixelValue = avgPixelValue / 255.0f;

            byteBuffer.putFloat(normalizedPixelValue);
        }

        return byteBuffer;
    }

    public void finish() {
        if (interpreter != null)
            interpreter.close();
    }
}