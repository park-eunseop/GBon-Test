package kr.co.gb.eunsub.repository.code;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import kr.co.gb.eunsub.model.code.Code;
import kr.co.gb.eunsub.model.code.CodeType;

/**
 * 코드 repository
 * @author park
 *
 */

@Repository
public interface CodeRepository extends JpaRepository<Code, Long>,JpaSpecificationExecutor<Code>{

	//단순 조건
	public List<Code> findAllByCodeType(CodeType seq);
	
	public List<Code> findAllByCodeTypeOrderBySortNum(CodeType seq);
	
	public void deleteAllByCodeType(CodeType seq);
}
