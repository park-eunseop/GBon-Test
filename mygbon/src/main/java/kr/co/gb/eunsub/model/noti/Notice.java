package kr.co.gb.eunsub.model.noti;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import kr.co.gb.eunsub.model.common.YnType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;


@Data
@Table(name = "notice_board")
@Entity
public class Notice {

	/**
	 * 일련번호
	 */
	@Id
	@Column(name = "seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sequence;
	
	
	/**
	 * 제목
	 */
	@Column(name = "title")
	private String title;
	
	
	
	/**
	 * 내용
	 */
	@Lob //대용량 데이터(Large Object), 거의 4GB, 데이터의 안전한 보호, 저장의 일관성
	@Column(name = "content")
	private String content;
	
	
	/**
	 * 공지대상 ?? 
	 */
	
	
	/**
	 * 팝업여부
	 */
	@Column(name = "popYn", length = 1)
	@Enumerated(EnumType.STRING)
	private YnType popYn;
	
	
	/**
	 * 중요여부
	 */
	@Column(name = "importantYn", length = 1)
	@Enumerated(EnumType.STRING)
	private YnType importantYn;
	
	
	/**
	 * 등록자
	 */
	
	
	/**
	 * 등록일시
	 */
	@Column(name = "created_at", nullable = false)
	@CreationTimestamp
	private Timestamp createdAt;
	
	/**
	 * 수정자
	 * 
	 */
	
	
	/**
	 * 수정일시
	 */
	@Column(name = "updated_at", nullable = true)
	@UpdateTimestamp
	private Timestamp updatedAt;
	
	
	
	
}
