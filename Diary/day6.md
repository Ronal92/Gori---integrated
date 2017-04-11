[ Date : 2017. 04. 01(토)]

#Done

뷰페이저의 각 페이지마다 크기가 다르기 때문에 페이저 크기 동적할당 하기(But, 터치 슬라이드할 때 뷰가 이상하게 나타남.......)

## 뷰페이저 동적할당

Reference : [https://github.com/vabhishek/WrapContentViewPagerDemo/blob/master/DynamicViewPager-master/app/src/main/java/com/example/vihaan/dynamicviewpager/MyPagerAdapter.java](https://github.com/vabhishek/WrapContentViewPagerDemo/blob/master/DynamicViewPager-master/app/src/main/java/com/example/vihaan/dynamicviewpager/MyPagerAdapter.java)

###1. 뷰페이저를 커스텀해야한다.

<CustomPager>

			public class CustomPager extends ViewPager {
			
			    private View mCurrentView;
			
			    public CustomPager(Context context) {
			        super(context);
			    }
			
			    public CustomPager(Context context, AttributeSet attrs) {
			        super(context, attrs);
			    }
			
			
			    @Override
			    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			        if (mCurrentView == null) {
			            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
			            return;
			        }
			        int height = 0;
			        mCurrentView.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
			        int h = mCurrentView.getMeasuredHeight();
			        if (h > height) height = h;
			        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
			
			        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
			    }
			
			    public void measureCurrentView(View currentView) {
			        mCurrentView = currentView;
			        requestLayout();
			    }
			
			    public int measureFragment(View view) {
			        if (view == null)
			            return 0;
			
			        view.measure(0, 0);
			        return view.getMeasuredHeight();
			    }
			
			
			}

###2. 메인엑티비티의 뷰페이저 어뎁터에 아래 코드 추가

< MainActivity.java >

	    class PagerAdapter extends FragmentStatePagerAdapter {
	    
		final int PAGE_COUNT = 4;
	    private int mCurrentPosition = -1;

       .................
	   .................

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, position, object);
            if (position != mCurrentPosition) {
                Fragment fragment = (Fragment) object;
                CustomPager pager = (CustomPager) container;
                if (fragment != null && fragment.getView() != null) {
                    mCurrentPosition = position;
                    pager.measureCurrentView(fragment.getView());
                }
            }
        }

    }

#Todo

- 버튼의 동적 할당
- MyPage를 시작해야한다.