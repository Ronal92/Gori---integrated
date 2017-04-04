[ Date : 2017. 04. 04(월) ]

#Done

 - 뷰페이저 문제 해결!-->MyPage에서 tab과 프래그먼트를 연결한 다음 프래그먼트 안에 또다른 탭과 뷰페이저를 만듦,
 - 리사이클러뷰에서 이미지 처리를 glide로 사용

## 탭과 프래그먼트 연결하기 without ViewPager

				public class MainActivity extends AppCompatActivity {
				
				    private  MypageTuteeFragment TuteeFragment = null;
				    private MypageTutorFragment TutorFragment = null;
				
				    @Override
				    protected void onCreate(Bundle savedInstanceState) {
				        super.onCreate(savedInstanceState);
				        setContentView(R.layout.activity_main);
						
						//---------------- 프래그먼트와 탭 초기화------------//
				        TuteeFragment = new MypageTuteeFragment();
				        TutorFragment = new MypageTutorFragment();
				
				        TabLayout outerTab = (TabLayout)findViewById(R.id.outerTab);
				        outerTab.addTab(outerTab.newTab().setText("수강생"));
				        outerTab.addTab(outerTab.newTab().setText("튜터"));
						
						//---------------아래 부분이 핵심 -----------------------//
				        outerTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
				            @Override
				            public void onTabSelected(TabLayout.Tab tab) {
				                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
				                if(tab.getPosition() == 0){
				                    transaction.replace(R.id.mypageFrame, TuteeFragment);
				                } else if(tab.getPosition() == 1) {
				                    transaction.replace(R.id.mypageFrame, TutorFragment);
				                }
				
				                transaction.commit();
				            }
				
				            @Override
				            public void onTabUnselected(TabLayout.Tab tab) {
				
				            }
				
				            @Override
				            public void onTabReselected(TabLayout.Tab tab) {
				
				            }
				        });
				
				        init();
				    }
				
					//---------------- 프래그먼트를 프레임아웃에 붙이는 작업-----------//
				    private void init(){
				        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
				        transaction.add(R.id.mypageFrame, TuteeFragment);
				        transaction.addToBackStack(null);
				        transaction.commit();
				
				    }
				
				}

#Todo

 - 다른 탭에 있는 콘텐츠들을 채워야 한다.
 - 총 5개의 프래그먼트(뷰페이저에서) 모두 Recyclerview를 사용하는데, 어뎁터 하나로 어떻게 각각의 프래그먼트에 적용할 수 있을지 고민이다.....List<?> datas와 같은 형태 generic을 사용해 볼 것.