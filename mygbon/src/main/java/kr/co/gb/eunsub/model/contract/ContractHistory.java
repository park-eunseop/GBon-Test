package kr.co.gb.eunsub.model.contract;

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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import kr.co.gb.eunsub.model.common.HistoryType;
import lombok.Data;

/**
 * 계약정보 이력 테이블
 * @author park
 *
 */

@Data
@Table(name = "contract_history")
@Entity
public class ContractHistory {
	
	
	/**
	 * 일련번호
	 */
	@Id
	//@Getter
	//@Setter
	@Column(name = "seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sequence;
	
	
	
	/**
	 * 계약정보 일련번호
	 */
	//@Getter
	//@Setter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contract_seq")
	private ContractInfo contractInfo;
	
	
	
	/**
	 * 사용자수
	 */
	//@Getter
	//@Setter
	@Column(name = "user_num",nullable = true)
	private Integer userNum;
	
	
	
	/**
	 * 계약 금액
	 */
	//@Getter
	//@Setter
	@Column(name = "contract_amount", nullable = false)
	//@NotBlank(message = "not null and not blank")   integer에서 쓸수없어
	private Integer contractAmount;
	
	
	
	/**
	 * 계약 시작 일자
	 */
//	@Getter
//	@Setter
	@Column(name="contract_start_date", nullable = false)
	private Timestamp contractStartDate;
	
	
	/**
	 * 계약 종료 일자
	 */
//	@Getter
//	@Setter
	@Column(name = "contract_end_date")
	private Timestamp contractEndDate;
	
	
	
	/**
	 * 이력 유형  //create, update, delete
	 */
//	@Getter
//	@Setter
	@Column(name = "history_type")
	@Enumerated(EnumType.STRING)
	private HistoryType historyType;
	
	/**
	 * 비고??
	 */
//	@Getter
//	@Setter
	@Lob
	@Column(name = "remark", nullable = true)
	private String remark;
	
	
	/**
	 * 등록일시
	 */
//	@Getter
//	@Setter
	@Column(name = "created_at")
	@CreationTimestamp
	private Timestamp createdAt;
	
	
}
