package kr.co.gb.eunsub.repository.todo;

import kr.co.gb.eunsub.model.todo.Todo;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long> , JpaSpecificationExecutor<Todo> {

    /**
     * 검색 조건을 조합한 Specification 인스턴스를 이용해서 검색하기
     *
     * 02.22 발생 오류
     * 내가 직접 findAll 메서드를 정의해서 사용할려고 했는데 (List<Specification<Todo>> findall(Specification<Todo> spec);)
     * JpaSpecificationExecutor 인터페이스를 보면 정의되어 있는 메서드가 있어.
     * 아니면 직접 쓸려면 오버라이드 해야지
     */
    //List<Specification<Todo>> findall(Specification<Todo> spec);
    //List<Specification> findall(Specification spec)
    /**
     * JPA에서 limit 사용하고 싶은 경우에 Pageable을 사용하게 된다.
     * findAll에서 반환 타입이 Page인 메서드를 사용하면 된다.
     * Page<T> findAll(@Nullable Specification<T> spec, Pageable pageable);
     */

}


/**
 * Spring Data JPA가 Repository를 정의하면 알아서 구현체를 만들어 준다.
 * 그리고 검색조건을 추상화한 Specification 을 지원해준다.
 *
 * Specification 사용방법
 * 1. Specification을 입력 받도록 Repository 인터페이스를 정의하기
 * 2. 검색조건을 모아 놓은 클래스 만들기
 * 3. 검색 조건을 조합한 Specification 인스턴스를 이용해서 검색하기
 *
 *
 * 장점?
 * 검색 조건을 생성할 때 도메인의 용어를 사용할 수 있게 된다.
 * 검색 조건을 조합하는 코드에서 Criteria와 같은 실제 구현 기술의 타입을 사용하지 않는다.
 * 쉽다.
 *
 *
 * https://groti.tistory.com/49  여기에서 도움 많이 받음
 *
 */
