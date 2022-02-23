package kr.co.gb.eunsub.service.client;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.gb.eunsub.model.client.ClientInfo;
import kr.co.gb.eunsub.repository.client.ClientInfoRepository;

/**
 * 고객사 정보 서비스 정의
 * @author park
 *
 */

@Service
public class ClientService {

	@Autowired
	private ClientInfoRepository clientInfoRepository;
	
	
	/**
	 * 고객사 추가
	 */
	@Transactional
	public ClientInfo addClient(ClientInfo ci) {
		return clientInfoRepository.save(ci);
	}
	
	/**
	 * 고객사 전체 조회
	 */
	@Transactional(readOnly = true)
	public List<ClientInfo> getAllList(){
		return clientInfoRepository.findAll();
	}
	
	
	/**
	 * 특정 고객사 조회
	 */
	@Transactional(readOnly = true)
	public ClientInfo getClient(Long seq) {
		ClientInfo ci = clientInfoRepository.findById(seq).orElseThrow(()->{
			return new IllegalArgumentException("조회 실패");
		});
		
		return ci;
	}
	
	
	/**
	 * 고객사 수정
	 */
	@Transactional  //변경감지(더티체킹)
	public ClientInfo updateClient(ClientInfo ci) {
		//존재하는 고객사인지 확인
		ClientInfo found_ci = clientInfoRepository.findById(ci.getSequence()).orElseThrow(()->{
			return new IllegalArgumentException("수정 실패");
		});
		found_ci.setName(ci.getName());
		found_ci.setBusinessNumber(ci.getBusinessNumber());
		found_ci.setManagerName(ci.getManagerName());
		found_ci.setManagerNum(ci.getManagerNum());
		found_ci.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		
		return found_ci;
	}
	
	
	/**
	 * 고객사 삭제
	 * 삭제한 고객사정보 리턴
	 */
	@Transactional
	public ClientInfo deleteClient(Long seq) {
		//존재하는 고객사인지 확인
		ClientInfo ci = clientInfoRepository.findById(seq).orElseThrow(()->{
			return new IllegalArgumentException("삭제 실패");
		});
		clientInfoRepository.deleteById(seq);
		
		return ci;
	}
	
	
	/**
	 * 전체 고객사 삭제
	 */
	@Transactional
	public boolean deleteAllClient() {
		clientInfoRepository.deleteAll();
		return true;
	}
}
