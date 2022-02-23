package kr.co.gb.eunsub.model.product;

import java.sql.Timestamp;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import kr.co.gb.eunsub.model.contract.ContractInfo;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import kr.co.gb.eunsub.model.common.YnType;

/**
 * 상품 상세 정보 테이블
 * @author park
 * 
 *
 */


@Table(name = "product_info")
@Entity
public class ProductInfo {
	
	
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
	 * 상품기초정보 일련번호
	 */
	//fetchType을 EAGER로 주면 조인된 모든 정보를 가져와
	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "basic_seq")
	private ProductBasic productBasic;
	
	/**
	 * 연관관계 명시
	 * mappedBy = productInfo
	 */
	@Getter
	@Setter
	@OneToMany(mappedBy = "productInfo",fetch = FetchType.LAZY)
	private List<ProductHistory> historys;
	
	/**
	 * 연관관계 명시
	 */
	@Getter
	@Setter
	@OneToMany(mappedBy = "product")
	private List<ContractInfo> contractInfo;

	
	
	/**
	 * 상품이름
	 */
	@Getter
	@Setter
	@Column(name = "name")
	@NotBlank(message = "null이거나 공백일수 없음")
	private String name;
	
	
	/**
	 * 상품내용
	 */
	@Lob
	@Getter
	@Setter
	@Column(name = "content")
	private String content;
	
	/**
	 * 사용여부
	 */
	@Getter
	@Setter
	@Column(name = "useYn")
	@Enumerated(EnumType.STRING)
	private YnType useYn;
	
	
	
	/**
	 * 추가일시
	 */
	@Getter
	@Setter
	@Column(name = "created_at",nullable = false)
	@CreationTimestamp
	private Timestamp createdAt;
	
	
	/**
	 * 수정일시
	 */
	@Getter
	@Setter
	@Column(name = "updated_at", nullable = true)
	//@UpdateTimestamp
	private Timestamp updatedAt;
	

}
