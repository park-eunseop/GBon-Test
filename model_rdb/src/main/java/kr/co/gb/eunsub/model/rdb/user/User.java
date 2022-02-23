package kr.co.gb.eunsub.model.rdb.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usr_info")
public class User implements Serializable{

	@Id
	@Getter
	@Setter
	@Column(name = "seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull(message = "일련번호는 필수입니다.")
	private Long seq;
}
