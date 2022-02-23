package kr.co.gb.eunsub.model.item;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)  //상속 구현 전략 선택(조인전략,단일테이블전략,클래스마다 테이블생성전략)
@DiscriminatorColumn  // 이 어노테이션을 선언하지 않으면 DTYPE 컬럼이 생성되지 않는다.
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private int price;

}
