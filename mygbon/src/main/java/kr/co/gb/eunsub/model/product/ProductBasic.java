package kr.co.gb.eunsub.model.product;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;


/**
 * 상품기초정보 테이블
 * @author park
 *
 */
@Table(name = "product_basic")
@Entity
public class ProductBasic {

	
	/**
	 * 일련번호
	 * 
	 */
	@Id
	@Getter
	@Setter
	@Column(name = "seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sequence;
	
	
	/**
	 * 연관관계 명시
	 * mappedBy = 주인의 FK 변수명, 연관관계의 주인은 ProductInfo야
	 */
	@Getter
	@Setter
	@OneToMany(mappedBy = "productBasic",fetch = FetchType.LAZY)
	private List<ProductInfo> products;
	
	
	
	/**
	 * 상품 이름
	 */
	@Getter
	@Setter
	@Column(name = "name")
	@NotBlank(message = "null이거나 공백일수 없음!")
	private String name;
	
	
	/**
	 * 등록일시
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
