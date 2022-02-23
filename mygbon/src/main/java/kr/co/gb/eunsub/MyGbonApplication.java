package kr.co.gb.eunsub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyGbonApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyGbonApplication.class, args);
		/*
		 * spring security dependency에 의해 스플이의 기본 보안 인증이 적용되어 기본 로그인폼화면이 
		 * redirect 된다.
		 * login: user
		 * password: 콘솔에 뜨는 값 
		 */
	}
}
