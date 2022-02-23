package kr.co.gb.eunsub.service.code;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import kr.co.gb.eunsub.model.code.Code;
import kr.co.gb.eunsub.model.code.CodeType;
import kr.co.gb.eunsub.model.common.YnType;
import kr.co.gb.eunsub.repository.code.CodeRepository;
import kr.co.gb.eunsub.repository.code.CodeTypeRepository;

@RestController
public class CodeTypeService {

	@Autowired
	private CodeTypeRepository codeTypeRepository;
	
	@Autowired
	private CodeRepository codeRepository;
	
	/**
	 * 코드 유형 추가 (코드유형 이름 typeName )
	 * @param ct
	 * @return
	 */
	//@PostMapping("/code/type")
	public CodeType addCodeType(CodeType ct) {
		ct.setUseYn(YnType.y);		
		codeTypeRepository.save(ct);
		return ct;
	}
	
	
	/**
	 * 코드 추가 (코드유형 codeType, 코드이름 codeName, 정렬순서 sortNum 입력)
	 * 코드 유형마다 정렬 순서가 겹치는지 확인해야해
	 * @param cd
	 * @return
	 */
	//@PostMapping("/code/code")
	public Code addCode(Code cd) {
		cd.setUseYn(YnType.y);
		/*
		 * 1.코드타입으로 조회한다.
		 * 2.조회한 리스트에서 정렬순서가 겹치는지 확인한다.
		 * 3.조회 순서가 같으면 null 반환
		 */
		List<Code> list = codeRepository.findAllByCodeType(cd.getCodeType());
		for(Code s : list) {
			if(cd.getSortNum().equals(s.getSortNum())) return null;					
		}
		
		System.out.println("it's possible to save data");
		codeRepository.save(cd);		
		return cd;
	}
	
	/**
	 * 코드유형 전체 조회
	 */
	//@GetMapping("/code/allType")
	private List<CodeType> getAllCodeType(){
		return codeTypeRepository.findAll();
	}

	/**
	 * 코드유형별 코드 전체 조회
	 */
	//@GetMapping("/code/all/{seq}")
	private List<Code> getAllCode(@PathVariable CodeType seq){
		
		return codeRepository.findAllByCodeType(seq);
	}
	
	
	/**
	 * 코드유형 수정 (seq를 받아야해)
	 * 유형이름 typeName, 사용여부 useYn 수정가능
	 * 수정시 수정일시 updatedAt insert
	 */
	//@Transactional 더티체킹할려고 썼는데 잘 안되네.......
	//@PutMapping("/code/updateType")
	private CodeType updateCodeType(CodeType ct) {		
		/*
		 * 1. 해당 seq로 db에서 영속성 컨텍스트로 올린다.
		 * 2. 해당 seq 데이터가 있는경우 update를 한다.
		 * 3. commit한다. (더티체킹)
		 */
		CodeType code_type = codeTypeRepository.findById(ct.getSequence()).orElseThrow(()->{
			return new IllegalArgumentException("수정 실패");
		});
		
		code_type.setTypeName(ct.getTypeName());
		code_type.setUseYn(ct.getUseYn());
		code_type.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		codeTypeRepository.save(code_type);
		return code_type;		
	}
	
	
	/**
	 * 코드 수정
	 * 코드이름, 코드유형, 사용여부 수정가능
	 * 수정시 수정일시 insert
	 * 
	 * case 1) 코드 이름, 사용여부 수정시 단순하게 코드정보만 수정 >> sortNum이 겹치는지 확인
	 * case 2) 코드 유형 수정시
	 *         2-1) 변경하는 코드유형이 있는지 확인
	 *         2-2) 변경하는 코드유형에서 sortNum이 겹치는지 확인
	 *              겹친다면 수정실패
	 *              겹치지 않는다면 수정 성공
	 * 
	 * 재정렬??????
	 */		
	//@PutMapping("/code/updateCode")
	private Code updateCode(Code cd) {
		//수정하려는 코드가 있는지 확인
		Code code = codeRepository.findById(cd.getSequence()).orElseThrow(()->{
			return new IllegalArgumentException("수정 실패");
		});
		
		if(!code.getCodeType().getSequence().equals(cd.getCodeType().getSequence())) {
			//변경할려는 코드유형이 있는지 확인. sortNum이 겹치는지 확인
			CodeType code_type = codeTypeRepository.findById(cd.getCodeType().getSequence()).orElseThrow(()->{
				return new IllegalArgumentException("수정 실패");
			});
			//여기까지 오면 존재하는 코드유형이야			
			System.out.println("코드유형이 변경되었습니다.");
		}
		//겹치는게 있는지 확인		
		List<Code> list = codeRepository.findAllByCodeType(cd.getCodeType());
		//겹치면 null 반환
		for(Code s : list) {
			if(cd.getSortNum().equals(s.getSortNum())) return null;					
		}
		
		code.setCodeName(cd.getCodeName());
		code.setCodeType(cd.getCodeType());
		code.setSortNum(cd.getSortNum());
		code.setUseYn(cd.getUseYn());
		code.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

		codeRepository.save(code);
		return cd;
	}
	
	
	
	
	
}
