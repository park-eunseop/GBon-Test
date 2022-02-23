package kr.co.gb.eunsub.model.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import kr.co.gb.eunsub.model.common.BloodType;
import lombok.Getter;
import lombok.Setter;

/**
 * 근로자 정보, 근로자에 특정된 데이터를 처리한다.
 * @author park
 * @see User
 * @since 1.0.0
 *
 */
@Table(name = "worker_info")
@Entity
public class Worker implements Serializable{

	
	private static final long serialVersionUID = -3965601544208705291L;
	
	/**
	 * 일련번호
	 */
	@Id
	@Getter
	@Setter
	@Column(name = "worker_seq")
	private Long sequence;
	
	/**
	 * 사용자 정보
	 * @see User
	 */
	@Getter
	@Setter
	@OneToOne
	@MapsId("sequence")
	@JoinColumn(name = "user_seq",nullable = false)
	private User user;
	
	
	
	
	/*
	 *  @Temporal : 날짜타입(java.util.Date, java.util.Calendar)을 매핑할때 사용
	 *  @Temporal should only be set on a java.util.Date or java.util.Calendar
	 */
	/**
	 * 생년월일
	 */
	@Getter
	@Setter
	@Column(name = "birthday",nullable = false)
	@Temporal(TemporalType.DATE)
	private java.util.Date birthday;
	
	
	
	/**
	 * 혈액형 유형
	 */
	@Getter
	@Setter
	@Column(name = "blood_type")
	@Enumerated(EnumType.STRING)
	private BloodType bloodType;
	
	
	
}
