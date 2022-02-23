package kr.co.gb.eunsub.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import kr.co.gb.eunsub.dto.ResponseDto;


@ControllerAdvice //모든 Exception이 발생하면 여기로 들어와
@RestController
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	private ResponseDto<String> handleArgumentException(Exception e){
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
	}
}
