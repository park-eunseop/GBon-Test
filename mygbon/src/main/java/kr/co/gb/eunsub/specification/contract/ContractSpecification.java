package kr.co.gb.eunsub.specification.contract;

import kr.co.gb.eunsub.model.contract.ContractInfo;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class ContractSpecification {

    /**
     * 고객사 이름으로 찾기
     * @param client_name
     */
    public static Specification<ContractInfo> likeClientName(String client_name){
        return new Specification<ContractInfo>() {
            @Override
            public Predicate toPredicate(Root<ContractInfo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("client").get("name"),"%"+client_name+"%");
            }
        };
    }

    /**
     * 상품으로 찾기
     * @param product_seq
     */
    public static Specification<ContractInfo> equalProduct(Long product_seq){
        return new Specification<ContractInfo>() {
            @Override
            public Predicate toPredicate(Root<ContractInfo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("product").get("sequence"),product_seq);
            }
        };
    }

    /**
     * 모든 사용자 수 Sum >> 모르겠다.
     */
    public static Specification<ContractInfo> sumUser(){
        return new Specification<ContractInfo>() {
            @Override
            public Predicate toPredicate(Root<ContractInfo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Expression<Number> userNum = criteriaBuilder.sum(root.get("userNum"));
                return null;
            }
        };
    }
}
