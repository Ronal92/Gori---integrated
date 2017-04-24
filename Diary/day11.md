[ Date : 2017. 04. 07(금) ]

#Done

서버 연동 성공(시뮬레이션)

문제점 : java.lang.IllegalStateException: Expected BEGIN_OBJECT but was BEGIN_ARRAY
Retrofit을 사용하여 서버접속까지 성공하였지만 데이터를 받아오지 못했다. 원인은 GSON이 json 스트링을 자바 객체로 변환시킬 때 발생하였다.
즉 Gson은 JSON OBject를 받아올 걸 기대하였지만 팀프로젝트에서 사용하는 json의 root는 {}가 아닌 []로써 JSON array 형식을 주었기 때문에 convert가 될 수 없었다. 

해결방법 :
 Call<SecondViewData> result = service.getSecondViewData(); 를
 Call<List<Class>> result = service.getSecondViewData(); 로 바꿈 SecondViewData로 받으면 객체 타입으로 받으려 하기 때문에 문제가 발생함.

