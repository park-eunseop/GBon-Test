package kr.co.gb.eunsub.model.common.embedded;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

/**
 * 비고 항목 정의
 * @author park
 *
 */
@Embeddable
public class Remark implements Serializable{

	private static final long serialVersionUID = -6607093172225641799L;
	
	/**
	 * 비고 내용
	 */
	@Getter
	@Setter
	@Column(name = "remark", length=500)
	private String value;
}
