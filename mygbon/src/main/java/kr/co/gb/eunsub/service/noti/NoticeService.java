package kr.co.gb.eunsub.service.noti;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.gb.eunsub.model.noti.Notice;
import kr.co.gb.eunsub.repository.noti.NoticeRepository;

/**
 * 공지사항 관련된 서비스 정의
 * @author park
 *
 */
@Service
public class NoticeService {

	@Autowired
	private NoticeRepository noticeRepository;
	
	
	/**
	 * 공지사항 추가
	 */
	public Notice addNotice(Notice nt) {
		
		Notice add_notice = noticeRepository.save(nt);	
		
		return add_notice;
	}
	
	
	/**
	 * 공지사항 수정
	 */
	@Transactional
	public Notice updateNotice(Notice nt) {
		/*
		 * try catch를 쓰면 보통 에러가 runtime Exception에 발생하는데 
		 * checked (runtime을 제외한 예외들) 는 롤백을 안하고
		 * unchecked (runtime 예외)는 롤백을 해준다.
		 *  
		 */
		Notice find_noti = noticeRepository.findById(nt.getSequence()).orElseThrow(()->{
			return new IllegalArgumentException("수정 실패");
		});
		try {
			find_noti.setTitle(nt.getTitle());
			find_noti.setPopYn(nt.getPopYn());
			return find_noti;
			
		}catch (RuntimeException e) {
			
			
			return null;
			//throw new RuntimeException("수정실패");
			
		}
	}
	
	/**
	 * 전체 공지사항 조회
	 */
	public List<Notice> getAllNotice(){
		return noticeRepository.findAll();
	}
	
	
	/**
	 * 특정 공지사항 조회
	 */
	public Notice getNotice(Long seq) {
		return noticeRepository.findById(seq).orElseThrow(()->{
			return new IllegalArgumentException("조회 실패");
		});
	}
	
	
	/**
	 * 특정 공지사항 삭제
	 */
	public Notice deleteNotice(Long seq) {
		//삭제할 공지사항이 있는지 확인
		Notice notice = noticeRepository.findById(seq).orElseThrow(()->{
			return new IllegalArgumentException("삭제 실패");
		});
		
		noticeRepository.deleteById(seq);
		
		return notice;			
	}

	
	
}//class
