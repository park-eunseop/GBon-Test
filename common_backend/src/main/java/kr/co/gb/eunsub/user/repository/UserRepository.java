package kr.co.gb.eunsub.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import kr.co.gb.eunsub.model.rdb.user.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>,JpaSpecificationExecutor<User> {

}
