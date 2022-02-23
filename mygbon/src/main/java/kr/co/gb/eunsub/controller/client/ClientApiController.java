package kr.co.gb.eunsub.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.gb.eunsub.model.client.ClientInfo;
import kr.co.gb.eunsub.service.client.ClientService;

/**
 * 고객사 정보 관련 API Controller
 * @author park
 *
 */



@RestController
public class ClientApiController {
	
	private final ClientService clientService;

	public ClientApiController(ClientService clientService) {
		this.clientService = clientService;
	}

	/**
	 * 고객사 추가
	 * 이름 name, 사업자번호 businessNumber, 책임자이름 managerName, managerNum
	 */
	@PostMapping("/client/addClient")
	public ClientInfo addClient(@RequestBody ClientInfo ci) {
		return clientService.addClient(ci);
	}
	
	/**
	 * 고객사 전체 조회
	 */
	@GetMapping("/client/getAllList")
	public List<ClientInfo> getAllList(){
		return clientService.getAllList();
	}
	
	/**
	 * 특정 고객사 조회
	 */
	@GetMapping("/client/getClient/{seq}")
	public ClientInfo getClient(@PathVariable Long seq) {
		return clientService.getClient(seq);
	}
	
	/**
	 * 고객사 수정
	 */
	@PutMapping("/client/updateClient")
	public ClientInfo updateClient(@RequestBody ClientInfo ci) {
		return clientService.updateClient(ci);
	}
	
	/**
	 * 고객사 삭제
	 */
	@DeleteMapping("/client/deleteClient/{seq}")
	public ClientInfo deleteClient(@PathVariable Long seq) {
		
		return clientService.deleteClient(seq);
	}
	
	/**
	 * 고객사 전체 삭제
	 */
	@DeleteMapping("/client/deleteAllClient")
	public boolean deleteAllClient() {
		return clientService.deleteAllClient();
	}
	

}
