 [ Date : 2017. 03. 30(목) ]

#DONE
- 스크롤바를 내렸을 때, 텍스트뷰의 visibility를 바꾸는 거 성공

## 스크롤바를 내렸을 때, 텍스트의 뷰 바꾸는 법

###1. 스크롤뷰를 커스텀한다.

<CustomScrollView>


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


###2. 메인엑티비티 : 반드시 onWindowFocusChanged()에서 바꾸려는 위젯의 높이값을 얻어와야한다.

<MainActivity.java>

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        txtTitle_y_position = txtTitle.getTop();
        tab_y_position = tab.getTop();
        Log.i(TAG,"=========================txtTitle_y_position : " + txtTitle_y_position + ", tab_y_position : " + tab_y_position);
        scrollView.setTxtTitleForY(txtTitle_y_position + 180);
        scrollView.setTabForY(tab_y_position + 120);
        super.onWindowFocusChanged(hasFocus);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
		scrollView = (CustomScrollView)findViewById(R.id.scrollView);

        scrollView.setOnAppearListener(new CustomScrollView.OnAppearListener() {
            @Override
            public void onAppearReached() {
                subTitle.setVisibility(View.VISIBLE);
            }
        });
        scrollView.setOnDestroyListener(new CustomScrollView.OnDestroyListener(){
            @Override
            public void onDestroyReached() {
                subTitle.setVisibility(View.GONE);
            }
        });
        scrollView.setTabAppearListener(new CustomScrollView.OnTabAppearListener(){
            @Override
            public void onTabAppearReached() {
                subTab.setVisibility(View.VISIBLE);
            }
        });
        scrollView.setTabDestroyListener(new CustomScrollView.OnTabDestroyListener(){
            @Override
            public void onTabDestroyReached(){
                subTab.setVisibility(View.GONE);
            }
        });

	}
#TODO
-내일 할 일 : 화면 하단 버튼 생성하기, 화면 상단 menu bar 생성하기 