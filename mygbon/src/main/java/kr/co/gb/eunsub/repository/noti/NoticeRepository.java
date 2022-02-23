package kr.co.gb.eunsub.repository.noti;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.gb.eunsub.model.noti.Notice;

/**
 * 공지사항 repository
 * @author park
 *
 */

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long>{

	
}
