package kr.co.gb.eunsub.controller.code;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.gb.eunsub.dto.ResponseDto;
import kr.co.gb.eunsub.model.code.Code;
import kr.co.gb.eunsub.model.code.CodeType;
import kr.co.gb.eunsub.model.common.YnType;
import kr.co.gb.eunsub.service.code.CodeService;


/**
 * 코드관련 API controller
 * @author park
 * @since 1.0.0
 * 
 */
@RestController
public class CodeApiController {

	@Autowired
	private CodeService codeService;
	
	
	/**
	 * 코드유형 추가 (코드유형이름 typeName)
	 */
	@PostMapping("/code/type")
	public ResponseDto<CodeType> addCodeType(@RequestBody CodeType ct) {
		ct.setUseYn(YnType.y);
		return codeService.addCodeType(ct);
	}//addCodeType	
	
	/**
	 * 코드 추가 (코드유형 codeType, 코드이름 codeName, 정렬순서 sortNum 입력)
	 * 코드 유형마다 정렬 순서가 겹치는지 확인해야해
	 */
	@PostMapping("/code/newcode/{codeType}")
	public ResponseDto<Code> addCode(@PathVariable CodeType codeType,@RequestBody Code cd) {
		cd.setCodeType(codeType);
		cd.setUseYn(YnType.y);
		return codeService.addCode(cd);
		
	}//addCode
	
	/**
	 * 코드유형 전체 조회
	 */
	@GetMapping("/code/allType")
	public ResponseDto<List<CodeType>> getAllCodeType(){
		try {
			List<CodeType> list = codeService.getAllCodeType();
			return new ResponseDto<List<CodeType>>(HttpStatus.OK.value(),list);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseDto<List<CodeType>>(HttpStatus.INTERNAL_SERVER_ERROR.value(),null);
		}
	}
	
	/**
	 * 코드유형별 코드 조회
	 */
	@GetMapping("/code/all/{seq}")
	public ResponseDto<List<Code>> getAllCode(@PathVariable CodeType seq){
		try {
			List<Code> list = codeService.getAllCode(seq);
			return new ResponseDto<List<Code>>(HttpStatus.OK.value(),list);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseDto<List<Code>>(HttpStatus.INTERNAL_SERVER_ERROR.value(),null);
		}
	}
	
	/**
	 * 코드유형 수정(seq를 받아서, 유형이름 typeName, 사용여부 useYn 수정)
	 * 수정시 수정일시 updateAt 추가 또는 수정
	 */
	@PutMapping("/code/updateType/{seq}")
	public ResponseDto<CodeType> updateCodeType(@PathVariable Long seq,@RequestBody CodeType ct){
		ct.setSequence(seq);
		return codeService.updateCodeType(ct);
	}
	
	/**
	 * 코드 수정(seq를 받아서, 코드이름 codeName, 코드유형 codeType, 사용여부 useYn, 정렬순서 sortNum 수정
	 * 수정시 수정일시 updateAt 추가 또는 수정
	 */
	@PutMapping("/code/updateCode/{code_seq}/{type_seq}")
	public ResponseDto<Code> updateCode(@PathVariable Long code_seq,@PathVariable CodeType type_seq,@RequestBody Code cd){
		cd.setCodeType(type_seq);
		cd.setSequence(code_seq);		
		return codeService.updateCode(cd);
	}
	
	
	/**
	 * 코드유형 삭제
	 */
	@DeleteMapping("/code/deleteType")
	public ResponseDto<CodeType> deleteCodeType(@RequestBody CodeType ct){
		return codeService.deleteCodeType(ct);
	}
	
	/**
	 * 코드 삭제
	 */
	@DeleteMapping("/code/deleteCode")
	public ResponseDto<Code> deleteCode(@RequestBody Code cd){
		return codeService.deleteCode(cd);
	}
	
	/**
	 * 코드정렬 테스트
	 */
	@GetMapping("/code/sort/{seq}")
	public List<Code> sortTest(@PathVariable CodeType seq){
		return codeService.sortList(seq);
	}
	
	
}
