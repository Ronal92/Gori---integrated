package goriproject.ykjw.com.myapplication.Util;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by Younkyu on 2017-04-13.
 */

public class DipCal {

    public static int convertPixelsToDp(float px, Context context) {
        final int value = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, context.getResources().getDisplayMetrics());
        return value;
    }

}
