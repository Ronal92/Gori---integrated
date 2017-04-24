[ Date : 2017. 04. 16(일) ]

#Done

1. 어신크와 쓰레드 api2

어신크 api 하나만 했을 때, 시간 차이가 없다

그리고 asynctask에서 get을 사용할 경우 컴퓨터에서 다 계산할 때까지 ui를 보여주지 않기 때문에 progressdialog가 보여주지 않는다. 

2. progressDialog를 asyncTask 내부에서 하면
android.view.windowleaked 에러가 발생함( 클래스(AsyncTask)를 생성해서 실행시킨 Activity가 onPuase()나 onClose() 상태가 되면서 finish되는 상태가 되고, 이 상태에서 AsyncTask의 결과를 처리하는 스레드(UI 스레드)가 아직 실행 중인 상태에서 발생한다) --> Activity의 OnStop()에서 액티비티가 멈출때 progressdialog가 종료되도록 설정

결론>> initial data는 api 하나로 몽땅 받고 추후 업데이트 되는 정보는 다른 api로 올린다음 백엔드에서 다시 initial data로 가공해주어야 할듯......
 

#Todo
