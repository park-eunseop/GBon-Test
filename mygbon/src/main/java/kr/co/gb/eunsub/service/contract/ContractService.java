package kr.co.gb.eunsub.service.contract;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import kr.co.gb.eunsub.specification.contract.ContractSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.gb.eunsub.model.common.HistoryType;
import kr.co.gb.eunsub.model.contract.ContractHistory;
import kr.co.gb.eunsub.model.contract.ContractInfo;
import kr.co.gb.eunsub.repository.contract.ContractHistoryRepository;
import kr.co.gb.eunsub.repository.contract.ContractInfoRepository;

/**
 * 계약 정보 서비스 
 * @author park
 *
 */

@Service
public class ContractService {
	
	
	@Autowired
	private ContractInfoRepository contractInfoRepository;

	@Autowired
	private ContractHistoryRepository contractHistoryRepository;
	
	
	/**
	 * 계약 추가
	 * 
	 * 1) contract_info insert
	 * 2) contract_history insert (create)
	 * 
	 */
	@Transactional
	public ContractInfo addContract(ContractInfo ci) {		
		//계약정보 추가
		ContractInfo created_ci = contractInfoRepository.save(ci);
		
		//이력 테이블 추가
		ContractHistory created_ch = new ContractHistory();
		created_ch.setContractInfo(created_ci);
		created_ch.setUserNum(ci.getUserNum());
		created_ch.setContractAmount(ci.getContractAmount());
		created_ch.setContractStartDate(ci.getContractStartDate());
		created_ch.setContractEndDate(ci.getContractEndDate());
		created_ch.setHistoryType(HistoryType.create);


		
		contractHistoryRepository.save(created_ch);
		
		
		return created_ci;
	}
	
	/**
	 * 계약 전체 조회
	 */
	@Transactional(readOnly = true)
	public List<ContractInfo> getAllList(){
		List<ContractInfo> lists = contractInfoRepository.findAll();
		/**
		 * 서비스에서는 row 데이터만 가져오는 용도로 써야해
		 */
		return lists;
	}
	
	
	/**
	 * 특정 고객사 계약 조회
	 */
	@Transactional(readOnly = true)
	public ContractInfo getContract(Long seq){
		ContractInfo contract = contractInfoRepository.findById(seq).orElseThrow(()->{
			return new IllegalArgumentException("조회 실패");
		});

		return contract;
	}
	
	
	/**
	 * 계약 수정
	 */
	@Transactional
	public ContractInfo updateContract(ContractInfo ci){
		//1) 계약 수정
		ContractInfo contract =  contractInfoRepository.findById(ci.getSequence()).orElseThrow(()->{
			return new IllegalArgumentException("수정 실패");
		});
		/*
			이렇게 하니깐 포함되지 않은 정보들이 들어가면 null로 들어가네;;;
		 */
		contract.setContractAmount(ci.getContractAmount());
		contract.setContractStartDate(ci.getContractStartDate());
		contract.setContractEndDate(ci.getContractEndDate());
		contract.setUserNum(ci.getUserNum());
		contract.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

		//2) 이력에 수정내용 추가
		ContractHistory history = new ContractHistory();
		history.setContractInfo(contract);
		history.setContractAmount(contract.getContractAmount());
		history.setContractStartDate(contract.getContractStartDate());
		history.setContractEndDate(contract.getContractEndDate());
		history.setUserNum(contract.getUserNum());
		history.setHistoryType(HistoryType.update);

		contractHistoryRepository.save(history);

		return contract;
	}
	
	
	
	
	/**
	 * 계약 삭제
	 */
	@Transactional
	public void deleteContract(HashMap<String,Object> data){
		//getclass()로 변수의 타입을 확인할 수 있음.
		// object 타입을 integer로 변경 후 long으로 변경
		Integer i = (Integer) data.get("sequence");
		Long seq = Long.valueOf(i.longValue());
		//1) 존재하는 계약인지 확인한다.
		ContractInfo contract =  contractInfoRepository.findById(seq).orElseThrow(()->{
			return new IllegalArgumentException("삭제 실패");
		});
		//2) 자식들을 먼저 삭제한다. (계약 이력 테이블)
		//delete by contractinfo
		contractHistoryRepository.deleteByContractInfo(contract);
		//3) 계약을 삭제한다.
		contractInfoRepository.deleteById(seq);

	}


	/**
	 * 조건에 맞는 계약 조건 검색
	 */
	public List<ContractInfo> getContractList(String client_name,Long product_seq){
		System.out.println("here");

		/**
		 * 조건문을 걸어서 product_seq = 0인 경우 전체 , client_name ='' 인 경우 전체로 검색
		 */
		Specification<ContractInfo> spec = Specification.where(ContractSpecification.likeClientName(client_name));

		System.out.println(spec);


		//System.out.println(product_seq.getClass()); //long
		//System.out.println(product_seq.equals(0l));
		// Long 에서 0이랑 같은지 비교할려면 0을 l type으로 만들어서 equals 메서드를 사용하면 됩니다.
		if(!product_seq.equals(0l))
			spec = spec.and(ContractSpecification.equalProduct(product_seq));

		//spec = spec.and(ContractSpecification.sumUser());

		List<ContractInfo> list =  contractInfoRepository.findAll(spec);

 		System.out.println(list.size());

		return list;
	}





}
