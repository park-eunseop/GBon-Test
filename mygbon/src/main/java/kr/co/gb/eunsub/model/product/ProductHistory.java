package kr.co.gb.eunsub.model.product;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import kr.co.gb.eunsub.model.common.HistoryType;

/**
 * 상품에 대한 이력 정보 테이블
 * @author park
 *
 */


@Table(name = "product_history")
@Entity
public class ProductHistory {
	
	
	
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
	 * 상품정보일련번호
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "info_seq")
	private ProductInfo productInfo;
	
	
	
	
	/**
	 * 상품이름 ??  상품정보일련번호를 FK로 가지는데 필요한지???
	 */
	
	
	
	/**
	 * 상품내용
	 */
	
	
	
	/**
	 * 이력유형
	 */
	@Getter
	@Setter
	@Column(name = "history_type")
	@Enumerated(EnumType.STRING)
	private HistoryType historyType;
	
	
	
	/**
	 * 비고????
	 */
	@Getter
	@Setter
	@Column(name = "remark", nullable = true)
	private String remark;
	
	
	/**
	 * 등록일시
	 */
	@Getter
	@Setter
	@Column(name = "created_at")
	@CreationTimestamp
	private Timestamp createdAt;
	

}
