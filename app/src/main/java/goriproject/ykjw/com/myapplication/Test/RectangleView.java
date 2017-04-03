package goriproject.ykjw.com.myapplication.Test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import goriproject.ykjw.com.myapplication.R;

/**
 *  Line, text, image 가 포함된 커스텀뷰
 */

public class RectangleView extends View {

    private static final String TAG = "RAPSTAR";
    Paint grayLine = new Paint();
    Paint text_Four_Frame = new Paint();

    Context ctx;
    String text1 = "";
    String text2 = "";
    String text3 = "";
    String text4 = "";

    Bitmap imgLocation, imgNumPple, imgPrice, imgSchedule;

    public RectangleView(Context context) {
        super(context);
        ctx = context;
        grayLine.setColor(Color.LTGRAY);
        grayLine.setStrokeWidth(5);
        text_Four_Frame.setColor(Color.BLACK);
        text_Four_Frame.setAntiAlias(true);
        text_Four_Frame.setStyle(Paint.Style.FILL);
        text_Four_Frame.setTextSize(45);

        text1 = "이화여대";
        text2 = "2시간/회";
        text3 = "최대인원:1~4명";
        text4 = "15,000 / 시간";

        // drawable에서 bitmap으로 이미지 가져오기
        imgLocation = BitmapFactory.decodeResource(getResources(), R.drawable.location);
        imgLocation = Bitmap.createScaledBitmap(imgLocation, 80, 80, true);

        imgSchedule = BitmapFactory.decodeResource(getResources(), R.drawable.schedule);
        imgSchedule = Bitmap.createScaledBitmap(imgSchedule, 80, 80, true);

        imgNumPple = BitmapFactory.decodeResource(getResources(), R.drawable.numpple);
        imgNumPple = Bitmap.createScaledBitmap(imgNumPple, 80, 80, true);

        imgPrice = BitmapFactory.decodeResource(getResources(), R.drawable.price);
        imgPrice = Bitmap.createScaledBitmap(imgPrice, 80, 80, true);

    }

    @Override
    public void onDraw(Canvas canvas){

        // Line 그리기
        canvas.drawLine(0, getHeight()/2, getWidth(), getHeight()/2, grayLine );
        canvas.drawLine(getWidth()/2, 0, getWidth()/2, getHeight(), grayLine );
        //Log.i(TAG,"============================" + getWidth() + "," + getHeight() + "," + metrics.widthPixels / GROUND_SIZE + "," + metrics.heightPixels / GROUND_SIZE);

        // 텍스트 그리기
        canvas.drawText( text1, 150, getHeight()/4 + 100, text_Four_Frame);
        canvas.drawText( text2, 650, getHeight()/4 + 100, text_Four_Frame);
        canvas.drawText( text3, 150, getHeight()*3/4 + 100, text_Four_Frame);
        canvas.drawText( text4, 650, getHeight()*3/4 + 100, text_Four_Frame);

        // 이미지 그리기
        canvas.drawBitmap(imgLocation, 180, getHeight()/4 - 20 ,null);
        canvas.drawBitmap(imgSchedule, 680, getHeight()/4- 20 , null);
        canvas.drawBitmap(imgNumPple, 180, getHeight()*3/4- 20, null);
        canvas.drawBitmap(imgPrice, 680, getHeight()*3/4- 20, null);
    }
}
