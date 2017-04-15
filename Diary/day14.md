[ Date : 2017. 04. 12(수) ]

#Done

-Retrofit 동기 문제 'NetworkOnMainThreadException' 어신크에서 doinbackgound에서 네트워크 통신하게 함. 즉 백 스레드에서 다 끝내게 한 다음.  asynchronous.get()으로 받으면 된다.

-안드로이드에서는 메모리가 부족하게되면 액티비티, 프래그먼트를 파기하여 메모리를 확보합니다
프래그먼트 재생성시에 발생되는 빈 생성자 때문에 필요한 값이 액티비티에서 프래그먼트로 전달이 안될 수도 있다.

setter는? http://www.androidside.com/bbs/board.php?bo_table=B56&wr_id=30667

-장소/시간 탭에서 백엔드 정보를 가져와서 보여주는 거 성공



#Todo


serializable 때문에 느리다-->parceable로 바꿔야되나??
백엔드 정보를 바로 사용하였을 때, 중첩되는 버튼이 나왔다. 따로 class와 ArrayList를 따로 만들어서 중첩되는 데이터를 없애야됨.