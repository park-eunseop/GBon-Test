package kr.co.gb.eunsub.repository.contract;

import kr.co.gb.eunsub.model.contract.ContractInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.gb.eunsub.model.contract.ContractHistory;

/**
 * 계약정보 이력 repository
 * @author park
 *
 */
public interface ContractHistoryRepository extends JpaRepository<ContractHistory, Long> {

    //	public ProductInfo findFirstByProductBasicOrderBySequenceDesc(ProductBasic pb);
    public void deleteByContractInfo(ContractInfo ci);
}
