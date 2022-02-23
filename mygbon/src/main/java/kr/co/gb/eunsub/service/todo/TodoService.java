package kr.co.gb.eunsub.service.todo;


import kr.co.gb.eunsub.model.todo.Todo;
import kr.co.gb.eunsub.repository.todo.TodoRepository;
import kr.co.gb.eunsub.specification.todo.TodoSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TodoService {
    /**
     * Bean 주입 방법중 비권장방식인 Field Injection 방법을 사용했어 warning이 뜬다.
     * 1) 생성자 기반 주입
     * 2) Setter 기반 주입 
     * 두가지 방식으로 대체하자
     */
    @Autowired
    private TodoRepository todoRepository;



    public void specificationTest(Todo todo){
        System.out.println("here1");
        Specification<Todo> spec = Specification.where(TodoSpecification.equalTodoId(todo.getTodoId()));
        //todoRepository.findall(spec);
        //todoRepository.findall(spec);


        /**
         *  검색 조건 객체 (Specification)에 하나씩 검색 조건을 추가하는거야.
         *  지금까지    id = ?
         */
        //System.out.println("here2");
        spec = spec.and(TodoSpecification.likeContent(todo.getContents()));
        /**
         *   지금까지 id=?  and  contents like ?
         */
        //System.out.println("here3");
        spec = spec.and(TodoSpecification.betweenCreatedDateTime(LocalDateTime.now(),LocalDateTime.now()));
        /**
         *   지금까지 id=?  and contents like ?  and  startdate between enddate
         */


        Page<Todo> lists =  todoRepository.findAll(spec, PageRequest.of(1,10));
        //System.out.println(lists);

       // odoRepository.findall()



    }


}
