package kr.co.gb.eunsub.controller.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.gb.eunsub.dto.ResponseDto;
import kr.co.gb.eunsub.model.product.ProductBasic;
import kr.co.gb.eunsub.model.product.ProductInfo;
import kr.co.gb.eunsub.service.product.ProductService;



/**
 * 상품관리 관련 API Controller
 * @author park
 *
 */




@RestController
public class ProductApiController {

	
	@Autowired
	private ProductService productService;
	
	
	/**
	 * 상품 추가 ( 상품 이름 name , 상품내용 content)
	 * @param
	 * @return
	 */
	@PostMapping("/product/create")
	public boolean createProduct(@RequestBody ProductInfo pi) {
		productService.addProduct(pi);
		return false;
	}
	
	/**
	 * 상품 수정( seq,name,content, useYn) + basic seq
	 * @param pi
	 * @return
	 */
	@PutMapping("/product/update/{seq}")
	public boolean updateProduct(@RequestBody ProductInfo pi,@PathVariable ProductBasic seq) {
		pi.setProductBasic(seq);
		productService.updateProduct(pi);
		return false;
	}
	
	/**
	 * 상품조회 (가장 최근에 수정한 1개 가져오기)
	 */
	@GetMapping("/product/getProductInfo/{seq}")
	public boolean getProductInfo(@PathVariable ProductBasic seq) {
		productService.getProductInfo(seq);
		return true;
	}
	
	/**
	 * 전체 상품 리스트 조회
	 */
	@GetMapping("/product/getAllList")
	public ResponseDto<List<ProductBasic>> getAllList() {
		System.out.println(productService.getProductList().size());
		
		//return productService.getProductList();
		return new ResponseDto<List<ProductBasic>>(200,productService.getProductList());
	}
	
	
}
