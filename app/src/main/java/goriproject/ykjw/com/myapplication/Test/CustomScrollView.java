package goriproject.ykjw.com.myapplication.Test;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;

/**
 *  스크롤이 특정 위치를 지났을 때 반응하게 하는 커스텀뷰
 */

public class CustomScrollView extends ScrollView {
    private static final String TAG = "RAPSTAR";


    OnAppearListener appearListener;
    OnDestroyListener destroyListener;
    OnTabAppearListener tabAppearListener;
    OnTabDestroyListener tabDestroyListener;

    float txtTitle_y_position = 0;
    float tab_y_position = 0;

    public CustomScrollView(Context context) {
        super(context);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // 기준이 되는 텍스트뷰 지점의 y값 가져오기
    public void setTxtTitleForY(float txtTitle_y_position ){
        this.txtTitle_y_position = txtTitle_y_position;
    }

    // 기준이 되는 탭 지점의 y값 가져오기
    public void setTabForY(float tab_y_position){
        this.tab_y_position = tab_y_position ;
    }


    // 스크롤이 바뀌었을 때 반응하기.
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        View view = (View) getChildAt(getChildCount()-1);
        //int diff = (view.getBottom()-(getHeight()+getScrollY())); // 바닥에 닿았을 때 기준
        int diff_txtTitle = getScrollY() - (int)txtTitle_y_position;
        int diff_tab = getScrollY() - (int)tab_y_position;

        Log.i(TAG,"================================  getScrollY() : " + getScrollY() + ", diff_txtTitle : " + diff_txtTitle + ", diff_tab : " + diff_tab);
        if ( diff_txtTitle > 0 && appearListener != null) {
            appearListener.onAppearReached();
        } else if( diff_txtTitle < 0 && destroyListener != null){
            destroyListener.onDestroyReached();
        }

        if( diff_tab > 20 && tabAppearListener != null){
            tabAppearListener.onTabAppearReached();
        } else if (diff_tab < 20 && tabAppearListener != null){
            tabDestroyListener.onTabDestroyReached();
        }

        super.onScrollChanged(l, t, oldl, oldt);
    }


    // Getters & Setters

    public OnAppearListener getOnAppearListener() {
        return appearListener;
    }

    public void setOnAppearListener(OnAppearListener OnAppearListener) {
        appearListener = OnAppearListener;
    }

    public OnDestroyListener getOnDestroyListener(){
        return destroyListener;
    }

    public void setOnDestroyListener(OnDestroyListener OnDestroyListener){
        destroyListener = OnDestroyListener;
    }
    public OnTabAppearListener getTabAppearListener(){
        return tabAppearListener;
    }
    public void setTabAppearListener(OnTabAppearListener OnTabAppearListener){
        tabAppearListener = OnTabAppearListener;
    }
    public OnTabDestroyListener getTabDestroyListener(){
        return tabDestroyListener;
    }
    public void setTabDestroyListener(OnTabDestroyListener OnTabDestroyListener){
        tabDestroyListener = OnTabDestroyListener;
    }
    /**
     * 스크롤이 특정 위치를 지났을 때, 상단의 텍스트를 보이게 하는 리스너
     */
    public interface OnAppearListener{
        public void onAppearReached();
    }

    /**
     * 스크롤이 특정 위치를 지났을 때, 상단의 텍스트를 사라지게 하는 리스너
     */
    public interface OnDestroyListener{
        public void onDestroyReached();
    }
    /**
     * 스크롤이 특정 위치를 지났을 때, 상단의 탭을 보이게 하는 리스너
     */

    public interface OnTabAppearListener{
        public void onTabAppearReached();
    }
    /**
     * 스크롤이 특정 위치를 지났을 때, 상단의 탭을 사라지게 하는 리스너
     */
    public interface OnTabDestroyListener{
        public void onTabDestroyReached();
    }
}
