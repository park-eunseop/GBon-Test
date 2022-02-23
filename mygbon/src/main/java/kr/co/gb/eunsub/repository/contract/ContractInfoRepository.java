package kr.co.gb.eunsub.repository.contract;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import kr.co.gb.eunsub.model.contract.ContractInfo;

/**
 * 계약 정보 repository
 * @author park
 *
 */

@Repository
public interface ContractInfoRepository extends JpaRepository<ContractInfo, Long>, JpaSpecificationExecutor<ContractInfo> {

}
