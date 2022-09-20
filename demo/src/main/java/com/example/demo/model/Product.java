package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter @Setter
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pid;
	
	private String pname; // �긽�뭹紐�
	
	private String ptype; // 移댄뀒怨좊━
	
	private Integer pprice; // 媛�寃�
	
	private Integer pstock; // �닔�웾
	
	private String pimg1; // �씠誘몄� �뙆�씪 寃쎈줈
//	
//	private String pImg2; // 占쏙옙품占싱뱄옙占쏙옙2
//	
//	private String pImg3; // 占쏙옙품占싱뱄옙占쏙옙3
	
	@Column(columnDefinition = "TEXT")
	private String pdetail; // �긽�뭹 �긽�꽭�젙蹂�
	
	public Product() {
		
	}
	
	public Product(String pname, String ptype, Integer pprice, Integer pstock, String pimg1, String pdetail) {
		this.pname = pname;
		this.ptype = ptype;
		this.pprice = pprice;
		this.pstock = pstock;
		this.pimg1 = pimg1;
		this.pdetail = pdetail;
	}
	
}
