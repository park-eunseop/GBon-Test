package kr.co.gb.eunsub.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import kr.co.gb.eunsub.model.user.User;

/**
 * 사용자 기본 repository
 * @author park
 * @since 1.0.0
 *
 */
//JpaSpecificationExecutor : 동적인 쿼리를 작성할 때 도움이 된다. 이걸 안쓰면 쿼리가 계속 길어진다.
@Repository
public interface UserRepository extends JpaRepository<User, Long>,JpaSpecificationExecutor<User> {

}
