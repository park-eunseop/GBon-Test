package kr.co.gb.eunsub.model.item;

import javax.persistence.Entity;

@Entity
public class Book extends Item {

	private String author;
	private String isbn;
}
