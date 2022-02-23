package kr.co.gb.eunsub.controller.contract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import kr.co.gb.eunsub.model.client.ClientInfo;
import kr.co.gb.eunsub.model.contract.ContractInfo;
import kr.co.gb.eunsub.model.product.ProductInfo;
import kr.co.gb.eunsub.service.contract.ContractService;

/**
 * 계약 정보 관련 API Controller
 * @author park
 *
 */


@RestController
public class ContractApiController {

	@Autowired
	private ContractService contractService;
	
	/**
	 * 계약 추가
	 * 고객사 일련번호 client, 상품일련번호 (나중에)
	 * 사용자수 userNum, 계약금액 contractAmount,
	 * 시작일자 contractStartDate, 종료일자 contractEndDate
	 */
	@PostMapping("/contract/addContract/{seq1}/{seq2}")
	public HashMap<String,Object> addContract(@RequestBody ContractInfo ci,@PathVariable ClientInfo seq1,
			@PathVariable ProductInfo seq2) {

		ci.setClient(seq1);
		System.out.println(ci.getClient().getName() );
		ci.setProduct(seq2);
		System.out.println(ci.getProduct().getName());

		ContractInfo contract = contractService.addContract(ci);
		HashMap<String,Object> contract_arrangement = new HashMap<>();
		contract_arrangement.put("sequence",contract.getSequence());
		contract_arrangement.put("clientName",contract.getClient().getName());
		contract_arrangement.put("userNum", contract.getUserNum());
		contract_arrangement.put("contractAmount",contract.getContractAmount());
		contract_arrangement.put("startDate",contract.getContractStartDate());
		contract_arrangement.put("endDate",contract.getContractEndDate());
		contract_arrangement.put("productName",contract.getProduct().getName());


		return contract_arrangement;
	}
	
	
	
	/**
	 * 계약 전체 조회
	 */
	@GetMapping("/contract/getAllList")
	public List<HashMap<String, Object>> getAllList(){

		/*
		 전체 계약 리스트를 가져올때는 자식 계약 이력까지 가져올 필요는 없어.
		 양방향 매핑이기 때문에 새로운 map에다가 넣어서 리턴해준다.
		 */
		List<ContractInfo> lists =  contractService.getAllList();
		//HashMap<String, Object> contract = new HashMap<String, Object>();
		List<HashMap<String, Object>> result_list = new ArrayList<>();
		for (int i=0;i<lists.size();i++){
			HashMap<String, Object> contract = new HashMap<String, Object>();
			contract.put("Sequence",lists.get(i).getSequence());
			contract.put("Amount",lists.get(i).getContractAmount());
			contract.put("StartDate",lists.get(i).getContractStartDate());
			contract.put("EndDate",lists.get(i).getContractEndDate());
			result_list.add(contract);
		}
		
		/**
		 * controller에서 가져온 row 데이터를 새로운 데이터 형태로 변경해주거나 수정해서 return해줘
		 * @RestController에서 반환하는 값을 json 형태로 변환해서 반환해준다.
		 */
		
		return result_list;
		
	}
	/**
	 * 특정 고객사 계약 조회
	 */
	@GetMapping("/contract/getContract/{seq}")
	public HashMap<String, Object> getContract(@PathVariable Long seq){
		ContractInfo contract = contractService.getContract(seq);

		HashMap<String,Object> contract_ = new HashMap<>();

		contract_.put("Sequence",contract.getSequence());
		contract_.put("Amount",contract.getContractAmount());
		contract_.put("StartDate",contract.getContractStartDate());
		contract_.put("EndDate",contract.getContractEndDate());

		return contract_;
	}
	/**
	 * 특정 조건에 해당하는 계약 정보 가져오기
	 */
	@GetMapping("/contract/getContractList")
	public List<HashMap<String,Object>> getContractList(
								@RequestParam Long product_seq,
								@RequestParam String client_name
														){
		System.out.println(product_seq);
		System.out.println(client_name);

		List<ContractInfo> list = contractService.getContractList(client_name,product_seq);

		List<HashMap<String, Object>> result_list = new ArrayList<>();
		for(int i=0;i<list.size();i++){
			HashMap<String, Object> contract = new HashMap<String, Object>();
			contract.put("contract_seq",list.get(i).getSequence());
			contract.put("product_name",list.get(i).getProduct().getName());
			contract.put("client_name",list.get(i).getClient().getName());
			contract.put("contract_amount",list.get(i).getContractAmount());
			contract.put("contract_useNum",list.get(i).getUserNum());
			contract.put("contract_start_date",list.get(i).getContractStartDate());
			contract.put("contract_end_date",list.get(i).getContractEndDate());
			result_list.add(contract);
		}

		return result_list;

	}
	/**
	 * 전체 사용자 수 가져오기
	 */
	@GetMapping("/contract/getUserSum")
	public int getUserSum(){
		int sum =0;
		for (ContractInfo contract : contractService.getAllList()){
			sum += contract.getUserNum();
		}
		return sum;
	}



	/**
	 * 계약 수정
	 */
	@PutMapping("/contract/updateContract")
	public HashMap<String,Object> updateContract(@RequestBody ContractInfo ci){

		ContractInfo contract = contractService.updateContract(ci);

		HashMap<String,Object> contract_ = new HashMap<>();
		contract_.put("Info","수정된 계약정보입니다.");
		contract_.put("Sequence",contract.getSequence());
		contract_.put("Amount",contract.getContractAmount());
		contract_.put("StartDate",contract.getContractStartDate());
		contract_.put("EndDate",contract.getContractEndDate());

		return contract_;
	}


	
	/**
	 * 계약 삭제
	 */
	@DeleteMapping("/contract/delete")
	public boolean deleteContract(@RequestBody HashMap<String,Object> data){
		contractService.deleteContract(data);
		return true;
	}

	
}
