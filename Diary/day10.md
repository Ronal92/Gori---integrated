[ Date : 2017. 04. 06(목) ]

#Done

마이페이지 완성. 액티비티 위에 프래그먼트를 생성하면서  java.lang.IllegalStateException: Fragment no longer exists for key f0 에러가 발생하였다.
원인 : 수강생 프래그먼트는 stack에 올려져서 사용자가 다시 불렀을 때 저장되어있는 걸 가져오지만,
        튜터 프래그먼트는 stack에 올려져 있기 않기에 다시 불렀을 때 항상 새로만들었다.
 	  따라서 여러 튜터 프래그먼트가 생성되면서 setAdapter()에서 문제가 발생하였다.

해결 : 수강생 프래그먼트와 튜터 프래그먼트 모두를 stack에 올렸다. 
		replace()//바꿀프래그먼트는 remove()// 현재 프래그먼트 + add()//바꿀프래그먼트 이다.


#Todo

서버 연동을 빨리 시작해야겠다.........
최종 코드와 합쳐야한다.
