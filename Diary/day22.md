[ Date : 2017. 04. 20(목) ]

#Done

Second_threeFragment에서 받는 api get통신 때문에 앱 속도가 느려졌다. 이 부분을 MainListAdapter에서 처리하게 하였다.
즉 한번에 모든 데이터를 받게 하였다.

#Todo
Second_threeFragment에서 약간 화면 전환이 느린데, Serializable 때문인 거 같다. Object를 통째로 넘기면서 느리다. 다른 방법을 찾아야겠다.