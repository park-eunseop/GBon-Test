package kr.co.gb.eunsub.repository.code;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.gb.eunsub.model.code.CodeType;

/**
 * 코드타입 repository
 * @author park
 *
 */

@Repository
public interface CodeTypeRepository extends JpaRepository<CodeType, Long>{

}
