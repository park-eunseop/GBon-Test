package kr.co.gb.eunsub.repository.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.gb.eunsub.model.product.ProductBasic;
import kr.co.gb.eunsub.model.product.ProductInfo;

/**
 * 상품정보 repository
 * @author park
 *
 */

@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo, Long>{

	
	public List<ProductInfo> findAllByProductBasic(ProductBasic pb);
	
	
	public ProductInfo findFirstByProductBasicOrderBySequenceDesc(ProductBasic pb);
}
