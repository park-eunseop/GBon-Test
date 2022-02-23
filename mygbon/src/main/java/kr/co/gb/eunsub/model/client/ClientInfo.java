package kr.co.gb.eunsub.model.client;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import kr.co.gb.eunsub.model.contract.ContractInfo;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

/**
 * 고객사 정보 테이블
 * @author park
 *
 */



@Table(name = "client_info")
@Entity
public class ClientInfo {
	
	/**
	 * 일련번호
	 */
	@Id
	@Getter
	@Setter
	@Column(name = "seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sequence;
	
	/**
	 * 계약 연관관계 명시
	 */
	@Getter
	@Setter
	@OneToMany(mappedBy = "client")
	private List<ContractInfo> contractInfo;

	
	/**
	 * 고객타입 ???
	 */
	
	
	/**
	 * 고객사 이름
	 */
	@Getter
	@Setter
	@Column(name = "name")
	@NotBlank(message = "not null and not blank")
	private String name;
	
	
	/**
	 * 사업자번호
	 */
	@Getter
	@Setter
	@Column(name = "business_number", nullable = true)
	private String businessNumber;
	
	
	/**
	 * 책임자 이름
	 */
	@Getter
	@Setter
	@Column(name = "manager_name")
	@NotBlank(message = "not null and not blank")
	private String managerName;
	
	
	/**
	 * 책임자 전화번호
	 */
	@Getter
	@Setter
	@Column(name = "manager_num", nullable = true)
	private String managerNum;
	
	
	
	
	/**
	 * 생성일
	 */
	@Getter
	@Setter
	@Column(name = "created_at")
	@CreationTimestamp
	private Timestamp createdAt;
	
	/**
	 * 수정일
	 */
	@Getter
	@Setter
	@Column(name = "updated_at")
	//@UpdateTimestamp 
	private Timestamp updatedAt;
}
