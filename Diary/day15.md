[ Date : 2017. 04. 13(목) ]

#Done

상세 화면으로 넘어갈 때, talent_list를 위한 정보와 review 정보를 위한 api들을 각각 호출해 주어야 한다. 이 때, api 하나만 호출해도 시간 소요가 크기 때문에 thread를 사용하였다.
api 2개를 사용했을 때, 최대한 빠른 시간 안에 화면 전환이 필요하기 때문에 여러 시도를 해보았다. 
asynctask + thread = 1.7초
thread + thread = 1.9초
asyntask + asynctask = 2.8초
결론적으로 네트워크 통신에서 asynctask와 thread를 같이 사용 할 때가 가장 시간이 덜 걸렸다. 


#Todo

api통신과 쓰레드 사용을 많이 할수록 애플리케이션의 속도가 느려진다. 기능 완성 후, 리팩토링을 하면서 고민해야 될 문제.....