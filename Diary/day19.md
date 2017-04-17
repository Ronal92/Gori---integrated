[ Date : 2017. 04. 17() ]

#Done

1. 앱 처음 실행시, Activity has leaked window 에러가 발생한다. progressDialog가 메인 액티비티의 context를 사용하는 context가 종료된 상태에서 progressDialog가 돌아가기 때문이다. 따라서 Asynctask에서 progressDialog의 초기화를 onPreExcute()가 아닌 생성자에서 해주었다.

2. Retrofit 전송의 데이터 방식 form-encoded(x-www-form-urlencoded) vs multipart(form-data) POST에서 데이터 전송 방식 때문에 500에러가 계속 발생함. 정확한 원인은 모르겠지만 content-type을 맞추기 위해서 multipart를 사용해야 한다.
 

#Todo
없습니다......