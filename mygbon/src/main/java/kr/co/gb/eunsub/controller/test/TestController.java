package kr.co.gb.eunsub.controller.test;

import kr.co.gb.eunsub.model.todo.Todo;
import kr.co.gb.eunsub.service.todo.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  테스트 용도로 만든 컨트롤러입니다.
 *
 */
@RestController
public class TestController {

    @Autowired
    private TodoService todoService;


    @GetMapping("/test/query")
    public boolean checkQuery(){
        Todo todo = new Todo();
        todoService.specificationTest(todo);

        return true;
    }
}
