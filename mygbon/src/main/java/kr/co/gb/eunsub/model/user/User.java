package kr.co.gb.eunsub.model.user;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import kr.co.gb.eunsub.model.common.ColumnLength;
import kr.co.gb.eunsub.model.common.YnType;
import kr.co.gb.eunsub.model.common.embedded.Remark;
import lombok.Getter;
import lombok.Setter;

/**
 * 사용자 기본 정보, 고객 담당자 및 근로자 등 모든 사용자의 기본 정보를 말한다.
 * @author Park
 *
 */

@Table(name = "usr_info")
@Entity
public class User implements Serializable{
/*
 * Serializable을 상속하는 Class의 경우 Class의 versioning 용도로 serialVersionUID 변수를 사용한다.
 * serialVersionUID 값을 명시적으로 지정하지 않으면 Compiler가 계사한 값을 부여하는데
 * Serializable Class 또는 Outer Class에 변경이 있으면 serialVersionUID값이 다르면
 * InvalidClassExceptions이 발생하여 저장된 값을 객체로 Restore할 수 없다.
 * 
 * 객체를 저장하고 불러올때 이 serialVersionUID 값을 가지고 불러오는데 직접할당하지 않고
 * 컴파일러에게 맡긴다면 아래의 경우 제대로 객체를 불러 올 수 없다.
 * 1. 저장하는 쪽의 컴파일과 불러오는 쪽의 컴파일이 다를 경우
 * 2. 저장하는 시기의 클래스 내용과 불러오는 시기의 클래스 내용이 다를 경우
 * 
 */

	private static final long serialVersionUID = 6385332160788768238L;
	
	/**
	 * 일련번호
	 */
	/*
	 * @Id 어노테이션의 위치가 필드 > JPA는 필드 접근 방식을 따른다.
	 */
	@Id
	@Getter
	@Setter
	@Column(name = "seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@NotNull(message = "일련번호는 필수입니다.")
	private Long sequence;
	
	
	/*
	 * mappedBy , 나는 FK가 아니야
	 * cascadeType 종류
	 * @OneToMany- @ManyToOne 관계에서 영속성 관리에서 문제가 발생하는데
	 * 영속성에서 참조된 객체를 지워주지 않았는데, 규칙을 정해줘야해
	 * 1)PERSIST : 엔티티를 영속화할때 이 필드에 보유된 엔티티도 유지
	 * 2)MERGE : 엔티티를 병합할때 이 필드에 보유된 엔티티도 병합
	 * 3)REFRESH : 엔티티를 새로 고칠때 이 필드에 보유된 엔티티도 새로 고침
	 * 4)REMOVE : 엔티티를 삭제할때, 이 필드에 보유된 엔티티도 삭제
	 * 5)ALL : 모든 Cascade 적용
	 */
	/**
	 * 근로자 부가 정보
	 */
	@Getter
	@Setter
	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "user")
	private Worker worker;
	
	/**
	 * 로그인 아이디
	 * null이여도 안되고, 빈 문자열이여도 안된다.
	 */
	@Getter
	@Setter
	@Column(name = "id",nullable = false,length = ColumnLength.LOGIN_ID)
	@NotBlank(message = "null 과 빈 문자열 , 공백 다 허용하지 않는다.")
	private String loginId;
	
	/**
	 * 비밀번호
	 */
	@Getter
	@Setter
	@Column(name = "pwd",nullable = false, length= ColumnLength.PASSWD)
	@NotBlank(message = "null 과 빈 문자열 , 공백 다 허용하지 않는다.")
	private String password;
	
	/**
	 * 비밀번호 확인
	 */
	@Getter
	@Setter
	@Transient
	private String confirmPassword;
	/*
	 * @Transient : 엔티티 객체의 데이터와 테이블의 컬럼과 매핑하고 있는 관계를 제외하기 위해 사용
	 * 컬럼을 제외하기 위해 사용 >> 영속 대상에서 제외
	 * 필드, 메서드에서 사용할 수 있어. 
	 * 
	 */
	
	/**
	 * 사용자명
	 */
	@Getter
	@Setter
	@Column(name = "usr_name", nullable = false, length = ColumnLength.SHORT_NAME)
	@NotBlank(message = "사용자 이름을 입력하세요")
	private String name;
	
	
	/**
	 * 휴대전화번호
	 */
	@Getter
	@Setter
	@Column(name = "cell_no",nullable = false,length = ColumnLength.PHONE_NO)
	private String cellphoneNo;
	
	/**
	 * 이메일
	 */
	@Getter
	@Setter
	@Column(name = "email", length = ColumnLength.EMAIL)
	private String email;
	
	
	/*
	 * @Converter 
	 * 컨버터를 사용하면 엔티티의 데이터를 변환해서 데이터베이스에 젖아할수있다.
	 * false , true  -> 0,1
	 * 
	 */
	/**
	 * 사용여부
	 */
	@Getter
	@Setter
	@Column(name = "use_yn", length = 1)
	@Enumerated(EnumType.STRING)
	private YnType use;
	
	/**
	 * SMS 수신 여부
	 */
	@Getter
	@Setter
	@Column(name = "sms_recv_yn", length = 1)
	@Enumerated(EnumType.STRING)
	private YnType receiveSms;
	
	
	/**
	 * Email 수신여부
	 */
	@Getter
	@Setter
	@Column(name = "email_recv_yn", length=1)
	@Enumerated(EnumType.STRING)
	private YnType receiveEmail;
	
	
	/**
	 * Push notification 수신여부
	 */
	@Getter
	@Setter
	@Column(name = "push_recv_yn", length = 1)
	@Enumerated(EnumType.STRING)
	private YnType receivePushNotification;
	
	/*
	 * @embedded : 값 타입을 사용하는곳에 표시
	 * @embeddable : 값 타입을 정의하는 곳에 표시
	 * 새로운 값 타입을 직접 정의해서 사용할 수 있어
	 */	
	/**
	 * 비고
	 * @see Remark
	 */
	@Getter
	@Setter
	@Embedded
	@AttributeOverride(name = "value",column = @Column(name="usr_rmk"))
	private Remark remark;
	
	/*
	@Getter
	@Setter
	@Embedded
	@AttributeOverride(name="value",column = @Column(name="usr1_rmk"))
	private Remark remark1;
	*/
	
	/*
	 * @AttributeOverride : 속성 재정의
	 * 임베디드 타칩에 정의한 매핑 정보를 재정의하려면 엔티티에 @AttributeOverride를 사용 
	 * 매핑 정보 재정의  (위에 주석 보고 참고하세요)
	 */
	/**
	 * 마지막 로그인 일시
	 * 
	 */
	@Getter
	@Setter
	@Column(name="last_login_at")
	private Timestamp lastLoginAt;
	
	
	
	/**
	 * 등록자 정보
	 * @see User
	 */
	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "register_seq",nullable = false,updatable = false)
	private User createdBy;
	
	/**
	 * 등록일시
	 */
	@Getter
	@Setter
	@Column(name = "created_at")
	private Timestamp createdAt;
	
	/**
	 * 수정자 정보
	 * @see User
	 */
	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "modifier_seq",nullable = true)
	private User updatedBy;
	
	
	/**
	 * 수정일시
	 * 
	 */
	@Getter
	@Setter
	@Column(name = "updated_at")
	private Timestamp updatedAt;
	
	
	
}
