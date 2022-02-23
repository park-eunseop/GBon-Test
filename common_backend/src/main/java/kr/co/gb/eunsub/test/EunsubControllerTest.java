package kr.co.gb.eunsub.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EunsubControllerTest {

	@GetMapping("/test/hello")
	public String hello() {
		return "hello";
	}
}
