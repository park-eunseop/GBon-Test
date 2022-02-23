package kr.co.gb.eunsub.model.contract;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import kr.co.gb.eunsub.model.client.ClientInfo;
import kr.co.gb.eunsub.model.product.ProductInfo;
import org.hibernate.annotations.CreationTimestamp;

//import kr.co.gb.eunsub.model.client.ClientInfo;
//import kr.co.gb.eunsub.model.product.ProductInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * 계약정보 테이블
 * @author park
 *
 */
@Table(name = "contract_info")
@Entity
public class ContractInfo {

	
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
	 * 고객사 일련번호
	 */
	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_seq")	
	private ClientInfo client;



	/**
	 * 상품 일련번호
	 */
	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_seq")	
	private ProductInfo product;

	
	/**
	 * 연관관계 명시
	 * 연관관계의 주인은 contract history
	 */
	@Getter
	@Setter
	@OneToMany(mappedBy = "contractInfo")
	private List<ContractHistory> historys= new ArrayList<>();
	
	
	/**
	 * 사용자수
	 */
	@Getter
	@Setter
	@Column(name = "user_num",nullable = true)
	private Integer userNum;
	
	
	/**
	 * 계약금액
	 */
	@Getter
	@Setter
	@Column(name = "contract_amount",nullable = false)
	//@NotBlank(message = "not null and not blank")
	private Integer contractAmount;
	
	/**
	 * 계약 시작 일자
	 */
	@Getter
	@Setter
	@Column(name = "contract_start_date",nullable = false)
	private Timestamp contractStartDate;
	
	/**
	 * 계약 종료 일자
	 */
	@Getter
	@Setter
	@Column(name = "contract_end_date")
	private Timestamp contractEndDate;
	
	/**
	 * 생성 일자
	 */
	@Getter
	@Setter
	@Column(name = "created_at", nullable = false)
	@CreationTimestamp
	private Timestamp createdAt;
	
	/**
	 * 수정 일자
	 */
	@Getter
	@Setter
	@Column(name = "updated_at")
	private Timestamp updatedAt;
	
	
}
