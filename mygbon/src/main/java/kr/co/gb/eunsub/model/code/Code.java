package kr.co.gb.eunsub.model.code;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import kr.co.gb.eunsub.model.common.YnType;
import lombok.Getter;
import lombok.Setter;

/**
 * 코드정보 테이블
 * @author Park
 *
 */

@Table(name = "code_info")
@Entity
public class Code {

	
	/**
	 *  일련번호
	 */
	@Id
	@Getter
	@Setter
	@Column(name = "seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@NotNull(message = "일련번호는 필수입니다.")
	private Long sequence;
	
	
	
	/**
	 * 코드유형
	 */
	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type_seq")
	private CodeType codeType;
	
	
	/**
	 * 코드이름 
	 */
	@Getter
	@Setter
	@Column(name = "code_name")
	@NotBlank(message = "null, 공백 X")
	private String codeName;
	
	
	/**
	 * 언어코드 잠시 생략
	 */
	
	
	
	/**
	 * 정렬순서
	 * 
	 */
	@Getter
	@Setter
	@Column(name = "sort_num")
	@NotNull(message = "not null!")
	private Integer sortNum;
	
	
	/**
	 * 사용여부
	 * 
	 * 
	 */
	@Getter
	@Setter
	@Column(name = "useYn", length = 1)
	@Enumerated(EnumType.STRING)
	private YnType useYn;
	
	
	
	/**
	 * 등록자
	 */
	
	
	/**
	 * 등록일시
	 */
	@Getter
	@Setter
	@Column(name = "created_at",nullable = false)
	@CreationTimestamp
	private Timestamp createdAt;
	
	
	/**
	 * 수정자
	 */
	
	
	/**
	 * 수정일시
	 */
	@Getter
	@Setter
	@Column(name = "updated_at",nullable = true)
	private Timestamp updatedAt;
	
	
}
