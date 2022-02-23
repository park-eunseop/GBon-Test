package kr.co.gb.eunsub.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.gb.eunsub.model.product.ProductBasic;

/**
 * 상품기초정보 repository
 * @author park
 *
 */

@Repository
public interface ProductBasicRepository extends JpaRepository<ProductBasic,Long> {

}
