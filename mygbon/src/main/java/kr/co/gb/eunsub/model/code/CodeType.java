package kr.co.gb.eunsub.model.code;
/**
 * 코드유형 테이블
 * @author Park
 *
 */

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

import kr.co.gb.eunsub.model.common.YnType;
import lombok.Getter;
import lombok.Setter;

@Table(name = "code_type")
@Entity
public class CodeType {

	/**
	 * 일련번호
	 */
	@Id
	@Getter
	@Setter
	@Column(name = "seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@NotNull(message = "일련번호는 필수입니다.")
	private Long sequence;
	
	/**
	 * 연관관계, FK가 아니야, 양방향 연관관계에서 연관 관계의 주인을 명시해줘야해
	 * mappedBy = "연과관계주인"   (주인의 FK 변수명)
	 */
	@OneToMany(mappedBy = "codeType")
	private List<Code> codes;
	
	
	/**
	 * 유형이름
	 */
	@Getter
	@Setter
	@Column(name = "type_name")
	@NotBlank(message = "null이랑 빈 공백을 허용하지 않습니다.")
	private String typeName;
	
	
	/**
	 * 언어코드
	 * 우선 생략
	 */
	
	
	
	/**
	 * 사용여부
	 */
	@Getter
	@Setter
	@Column(name = "useYn", length = 1)
	@Enumerated(EnumType.STRING)
	private YnType useYn;
	
	
	/**
	 * 등록자
	 * 우선 생략
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
	 * 우선 생략
	 */
	
	
	
	/**
	 * 수정일시
	 */
	@Getter
	@Setter
	@Column(name = "updated_at",nullable = true)
	private Timestamp updatedAt;
	
	
	
}
