package kr.co.gb.eunsub.specification.todo;

import kr.co.gb.eunsub.model.todo.Todo;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;

/**
 * 검색 조건을 모아 놓은 클래스 만들기
 *
 *
 */
public class TodoSpecification {

    /**
     * criteriaBuilder.equal(root.get("todoID"),todoID)
     */
    public static Specification<Todo> equalTodoId(Long todoId){

        return new Specification<Todo>() {
            @Override
            public Predicate toPredicate(Root<Todo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                /**
                 * order by, group by, having절 은  CriteriaQuery에서 설정한다.
                 */
                //id를 order by 한다.
                query.orderBy(criteriaBuilder.desc(root.get("todoId")));


                // equal
                return criteriaBuilder.equal(root.get("todoId"),todoId);
            }
        };
    }
    /**
     * criteriaBuilder.like(root.get("content"),"%"+contents+"%")
     */
    public static Specification<Todo> likeContent(String contents){
        return new Specification<Todo>() {
            @Override
            public Predicate toPredicate(Root<Todo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                // like

                query.orderBy(criteriaBuilder.asc(root.get("contents")));
                query.orderBy(criteriaBuilder.desc(root.get("todoId"))).orderBy(criteriaBuilder.asc(root.get("contents")))
                        .groupBy(root.get("completeYn"));


                return criteriaBuilder.like(root.get("contents"),"%"+contents+"%");
            }
        };
    }
    /**
     * criteriaBuilder.between(root.get
     */
    public static Specification<Todo> betweenCreatedDateTime(LocalDateTime startDatetime, LocalDateTime endDatetime){
        return new Specification<Todo>() {
            @Override
            public Predicate toPredicate(Root<Todo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                // between
                return criteriaBuilder.between(root.get("createdDatetime"),startDatetime,endDatetime);
            }
        };
    }





}
