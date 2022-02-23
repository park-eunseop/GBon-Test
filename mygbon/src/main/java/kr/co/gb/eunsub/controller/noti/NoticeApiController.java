package kr.co.gb.eunsub.controller.noti;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.gb.eunsub.model.noti.Notice;
import kr.co.gb.eunsub.service.noti.NoticeService;

/**
 * 공지사항 관련 API Controller
 * @author USER
 * @since 1.0.0
 *
 */

@RestController
public class NoticeApiController {

	@Autowired
	private NoticeService noticeService;
	
	
	/**
	 * 공지사항 추가
	 * 제목 title, 내용 content, 팝업여부 popYn, 중요여부 importantYn,
	 */
	@PostMapping("/notice/addNotice")
	public Notice addNotice(@RequestBody Notice nt) {
		
		System.out.println("notice Title:"+nt.getTitle());
		System.out.println("notice Content:"+nt.getContent());
		System.out.println("notice popYn:"+nt.getPopYn());
		System.out.println("notice importantYn:"+nt.getImportantYn());
		//return nt;
		return noticeService.addNotice(nt);
	}
	
	/**
	 * 공지사항 수정  (seq로 찾아서)
	 * 제목 title, 내용 content, 팝업여부 popYn, 중요여부 importantYn,
	 * 수정일시 updatedAt 추가 
	 */
	@PutMapping("/notice/updateNotice")
	public Notice updateNotice(@RequestBody Notice nt) {
		return noticeService.updateNotice(nt);
	}
	
	/**
	 * 전체 공지사항 조회
	 */
	@GetMapping("/notice/getAllNotice")
	public List<Notice> getAllNotice(){
		
		return noticeService.getAllNotice();
	}
	
	/**
	 * 특정 공지사항 조회
	 * seq로 조회
	 */
	@GetMapping("/notice/getNotice/{seq}")
	public Notice getNotice(@PathVariable Long seq) {
		System.out.println("조회할 공지사항 seq: "+seq);
		
		return noticeService.getNotice(seq);
	}
	
	/**
	 * 특정 공지사항 삭제
	 */
	@DeleteMapping("/notice/deleteNotice/{seq}")
	public Notice deleteNotice(@PathVariable Long seq) {
		
		return noticeService.deleteNotice(seq);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
