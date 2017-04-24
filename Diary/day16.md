[ Date : 2017. 04. 14(금) ]

#Done

상세 화면의 리뷰에서 사용자가 리뷰를 하였을 때, 서버로 post 통신외 되게 하였다. 이 때, header에서 사용자 인증으로 사용할 token을 Sharedpreference에서 가지고 와서 사용하였다.

SignInActivty에서 코드 리팩토링이 필요하여 일부를 재수정하였다.
SharedPreference를 사용하는 코드에서 editor.putString("autologin", key); 부분을 수정함. 사용자의 자동 로그인 상태와 키값이 잘못 연결 되어 있어서 코드를 이해하기 어려움. 따라서 이 부분을 editor.putBoolean("isAutologin", cb_login.isChecked()).commit(); editor.putString("token", key).commit();로 바꾸어 사용자의 자동 로그인 상태와 키의 저장을 분리해 주었다.   



#Todo

상세 화면의 문의 부분 post 통신을 추가하고 Mypage에서 사용자 정보를 화면에 띄워주어야 한다.