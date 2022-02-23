package kr.co.gb.eunsub.service.product;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.gb.eunsub.model.common.HistoryType;
import kr.co.gb.eunsub.model.common.YnType;
import kr.co.gb.eunsub.model.product.ProductBasic;
import kr.co.gb.eunsub.model.product.ProductHistory;
import kr.co.gb.eunsub.model.product.ProductInfo;
import kr.co.gb.eunsub.repository.product.ProductBasicRepository;
import kr.co.gb.eunsub.repository.product.ProductHistoryRepository;
import kr.co.gb.eunsub.repository.product.ProductInfoRepository;

/**
 * 상품관련 서비스 정의
 * @author park
 *
 */
@Service
public class ProductService {

	@Autowired
	private ProductBasicRepository productBasicRepository;
	
	@Autowired
	private ProductInfoRepository productInfoRepository; 
	
	@Autowired
	private ProductHistoryRepository productHistoryRepository;
	
	
	
	/**
	 * 상품추가
	 * 
	 * 1) basic에 추가
	 * 2) info에 추가
	 * 3) history에 추가
	 * 
	 */
	
	public boolean addProduct(ProductInfo pi) {
		//pi.name 에 상품이름,  pi.content 에 상품내용이 들어가 있음.	
	
		//1) basic에 추가
		ProductBasic pb = new ProductBasic();
		pb.setName(pi.getName());
		ProductBasic saved_pb = productBasicRepository.save(pb);
		
		//2) info에 추가
		pi.setUseYn(YnType.y);
		pi.setProductBasic(saved_pb);
		ProductInfo saved_pi = productInfoRepository.save(pi);
		
		//3) history 추가
		ProductHistory ph = new ProductHistory();
		ph.setProductInfo(saved_pi);
		ph.setHistoryType(HistoryType.create);
		productHistoryRepository.save(ph);
		
		
		
		return true;
	}
	
	/**
	 * 상품수정 (상품 basic seq로 수정)
	 * 
	 * 1) basic -> updated 추가 또는 수정
	 * 2) info -> 추가
	 * 3) history -> 추가
	 */
	
	public boolean updateProduct(ProductInfo pi) {
		
		//1) basic updatedat 추가 또는 수정
		ProductBasic pb = productBasicRepository.findById(pi.getProductBasic().getSequence()).orElseThrow(()->{
			return new IllegalArgumentException("수정 실패");
		});
		pb.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		productBasicRepository.save(pb);
		
		//2) info 추가....
		pi.setSequence(null);  //pk를 null로 만들면 save시에 insert됩니다.
		ProductInfo saved_pi = productInfoRepository.save(pi);
		
		//3) history 추가
		ProductHistory ph = new ProductHistory();
		ph.setProductInfo(saved_pi);
		ph.setHistoryType(HistoryType.update);
		productHistoryRepository.save(ph);	
		
		return true;
	}
	
	/**
	 * 상품 조회 : 가장 최근 상품 1개 조회
	 */
	public ProductInfo getProductInfo(ProductBasic pb) {
		
		ProductInfo pi = productInfoRepository.findFirstByProductBasicOrderBySequenceDesc(pb);
		System.out.println(pi.getContent());
		return null;
	}
	
	/**
	 * 상품리스트 조회
	 */
	public List<ProductBasic> getProductList(){
		
		return productBasicRepository.findAll();
	}
	
	
	
}
