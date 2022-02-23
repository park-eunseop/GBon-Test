package kr.co.gb.eunsub.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.gb.eunsub.model.product.ProductHistory;

/**
 * 상품 이력 Repository
 * @author park
 *
 */
@Repository
public interface ProductHistoryRepository extends JpaRepository<ProductHistory, Long> {

}
