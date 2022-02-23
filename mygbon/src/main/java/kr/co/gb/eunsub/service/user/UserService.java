package kr.co.gb.eunsub.service.user;

import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.co.gb.eunsub.model.user.User;
import kr.co.gb.eunsub.repository.user.UserRepository;

/**
 * 사용자 관련 service
 * 
 * @author park
 * @see UserRepository
 * @since 1.0.0
 *
 */
@Service
public class UserService {
	/**
	 * 사용자 기본 repository
	 */
	private final UserRepository repository;
	
	/**
	 * 생성자 Constructor
	 * @param repository
	 */
	public UserService(UserRepository repository) {
		this.repository = repository;
	}
	
	/**
	 * 일련번호에 해당하는 사용자 반환
	 * @param sequence 사용자 일련번호
	 * @return 사용자 정보
	 */
	public Optional<User> getUser(final Long sequence){
		return repository.findById(sequence);
	}
}
