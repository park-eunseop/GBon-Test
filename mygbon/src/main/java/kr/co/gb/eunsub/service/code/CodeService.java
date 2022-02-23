package kr.co.gb.eunsub.service.code;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
/**
 * 코드 관련 서비스 정의
 * @author park
 * @since 1.0.0
 */

import kr.co.gb.eunsub.dto.ResponseDto;
import kr.co.gb.eunsub.model.code.Code;
import kr.co.gb.eunsub.model.code.CodeType;
import kr.co.gb.eunsub.repository.code.CodeRepository;
import kr.co.gb.eunsub.repository.code.CodeTypeRepository;


/*
 * 서비스가 왜 필요한지
 * 1) 트랜잭션을 관리
 * 2) 서비스의 의미: 여러가지 로직들이 묶여서 하나의 기능, 하나의 서비스가 된다.
 *  			 그래서 한 기능이라도 실패하면 롤백해야해
 */
@Service
public class CodeService {

	@Autowired
	private CodeTypeRepository codeTypeRepository;
	
	@Autowired
	private CodeRepository codeRepository;
	
	/**
	 * 코드유형을 추가
	 */
	public ResponseDto<CodeType> addCodeType(CodeType ct) {
		try {
			codeTypeRepository.save(ct);
			return new ResponseDto<CodeType>(HttpStatus.OK.value(),ct);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseDto<CodeType>(HttpStatus.INTERNAL_SERVER_ERROR.value(),null);
		}
	}
	/**
	 * 코드 추가
	 */
	public ResponseDto<Code> addCode(Code cd) {
		/*
		 * 1.코드타입으로 조회한다.
		 * 2.조회한 리스트에서 정렬순서가 겹치는지 확인한다.
		 * 3 코드 정렬 순서가 같으면 그 다음 코드부터 정렬 순서 +1
		 */	
		try {
			List<Code> code_list = codeRepository.findAllByCodeTypeOrderBySortNum(cd.getCodeType());
			for(int i=cd.getSortNum();i<=code_list.size();i++) {
				code_list.get(i-1).setSortNum(i+1);
				codeRepository.save(code_list.get(i-1));
			}
			codeRepository.save(cd);
			return new ResponseDto<Code>(HttpStatus.OK.value(),cd);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseDto<Code>(HttpStatus.INTERNAL_SERVER_ERROR.value(),null);
		}
	}
	
	/**
	 * 코드유형 전체 조회
	 */
	public List<CodeType> getAllCodeType() {
		return codeTypeRepository.findAll();
	}
	
	/**
	 * 코드유형별 코드 전체 조회
	 */
	public List<Code> getAllCode(CodeType seq){
		return codeRepository.findAllByCodeType(seq);
	}
	
	/**
	 * 코드유형 수정
	 */
	public ResponseDto<CodeType> updateCodeType(CodeType ct) {
		/*
		 * 1. 수정할 seq로 db에서 영속성 컨텍스트로 올린다.
		 * 2. 수정할 seq로 update를 한다.
		 * 3. commit한다.
		 */
		
		CodeType code_type = codeTypeRepository.findById(ct.getSequence()).orElseThrow(()->{
			return new IllegalArgumentException("수정 실패");
		});
		
		try {
			code_type.setTypeName(ct.getTypeName());
			code_type.setUseYn(ct.getUseYn());
			code_type.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
			codeTypeRepository.save(code_type);
			return new ResponseDto<CodeType>(HttpStatus.OK.value(),code_type);
		}catch (Exception e) {
			return new ResponseDto<CodeType>(HttpStatus.INTERNAL_SERVER_ERROR.value(),null);
		}
	}
	
	/**
	 * 코드 수정
	 * 코드이름, 코드유형, 사용여부 수정
	 * 수정시 수정일시 추가 또는 수정
	 */
	public ResponseDto<Code> updateCode(Code cd){
		//수정하려는 코드가 있는지 확인
		Code code = codeRepository.findById(cd.getSequence()).orElseThrow(()->{
			return new IllegalArgumentException("수정 실패");
		});

		boolean flag = code.getCodeType().getSequence().equals(cd.getCodeType().getSequence());
		//코드유형을 변경하는지 확인
		if(flag) {
			//System.out.println("코드유형변경X");
			// 코드유형은 변경하지 않아, 기존에서 수정을해야해
			// 2가지로 나뉜다. 1) 기존순서 > 변경순서, 2) 기존순서 < 변경순서
			//1)기존>변경
			List<Code> codes = codeRepository.findAllByCodeTypeOrderBySortNum(cd.getCodeType());
			if(code.getSortNum()>cd.getSortNum()) {
				for(int i=cd.getSortNum();i<code.getSortNum();i++) {
					codes.get(i-1).setSortNum(i+1);
					codeRepository.save(codes.get(i-1));
				}
			}
			else { //2)기존<변경
				for(int i = code.getSortNum();i<cd.getSortNum();i++) {
					codes.get(i).setSortNum(i);
					codeRepository.save(codes.get(i));
				}
			}
		}else {			
			//System.out.println("코드유형변경");
			//코드유형을 변경한다면 변경하려는 코드유형이 있는지 확인
			codeTypeRepository.findById(cd.getCodeType().getSequence()).orElseThrow(()->{
				return new IllegalArgumentException("수정 실패");
			});
			//변경할 코드유형이 다를경우
			//cd.getCodeType 에는 변경할 코드유형, code.getCodeType 에는 기존 코드유형
			//1) 먼저 변경할 코드유형을 재정렬하자(추가)
			List<Code> nowCodes = codeRepository.findAllByCodeTypeOrderBySortNum(cd.getCodeType());
			for(int i=cd.getSortNum();i<=nowCodes.size();i++) {
				nowCodes.get(i-1).setSortNum(i+1);
				codeRepository.save(nowCodes.get(i-1));
			}
			//2) 기존 코드유형에서 재정렬하자(삭제)
			List<Code> exCodes = codeRepository.findAllByCodeTypeOrderBySortNum(code.getCodeType());
			for(int i=cd.getSortNum();i<exCodes.size();i++) {
				exCodes.get(i).setSortNum(i);
				codeRepository.save(exCodes.get(i));
			}
		}//else
		
		//update
		try {
			code.setCodeName(cd.getCodeName());
			code.setCodeType(cd.getCodeType());
			code.setUseYn(cd.getUseYn());
			code.setSortNum(cd.getSortNum());
			code.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
			codeRepository.save(code);
			return new ResponseDto<Code>(HttpStatus.OK.value(),code);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseDto<Code>(HttpStatus.INTERNAL_SERVER_ERROR.value(),null); 
		}	
	}
	
	/**
	 * 코드 유형 삭제
	 */
	@Transactional  //2개이상 crud 실행 시 도중 오류발생하면 롤백시켜주는거야
	public ResponseDto<CodeType> deleteCodeType(CodeType ct){
		// 1) 삭제하려는 코드가 있는지 확인
		CodeType code_type = codeTypeRepository.findById(ct.getSequence()).orElseThrow(()->{
			return new IllegalArgumentException("삭제 실패");
		});
		// 2) 코드유형에 해당하는 코드들 삭제	  ,try catch 잡으면 롤백이 안돼 (런타임 exception) >>try catch를 빼거나 따로 runtime 예외를 잡아야해
		try {
			codeRepository.deleteAllByCodeType(code_type);
			codeTypeRepository.deleteById(ct.getSequence());
			return new ResponseDto<CodeType>(HttpStatus.OK.value(),code_type);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseDto<CodeType>(HttpStatus.INTERNAL_SERVER_ERROR.value(),null);
		}
	}
		
	
	/**
	 * 코드 삭제
	 */
	public ResponseDto<Code> deleteCode(Code cd){
		//1) 삭제하려는 코드가 있는지 확인
		Code code = codeRepository.findById(cd.getSequence()).orElseThrow(()->{
			return new IllegalArgumentException("삭제 실패");
		});
		//2) 코드 삭제
		try {
			//코드재정렬 후 삭제			
			List<Code> codes =  codeRepository.findAllByCodeTypeOrderBySortNum(code.getCodeType());
			for(int i=code.getSortNum();i<codes.size();i++) {
				codes.get(i).setSortNum(i);
				codeRepository.save(codes.get(i));
			}
			codeRepository.deleteById(code.getSequence());	
			return new ResponseDto<Code>(HttpStatus.OK.value(),code);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseDto<Code>(HttpStatus.INTERNAL_SERVER_ERROR.value(),null);
		}
	}
	
	
	/**
	 * 코드정렳 테스트
	 */
	public List<Code> sortList(CodeType ct){
		
		List<Code> codes = codeRepository.findAllByCodeTypeOrderBySortNum(ct);
		int a = 1;
		for(Code c : codes) {
			c.setSortNum(a);
			codeRepository.save(c);
		}
		//codeRepository.save(codes);
		return codes;
		
	}
	
	
	
	
	
}//class
