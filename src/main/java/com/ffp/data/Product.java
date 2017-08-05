package com.ffp.data;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Product generated by hbm2java
 */
@Entity
@Table(name = "product", catalog = "crm")
public class Product implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private UserProfile userProfile;
	private String name;
	private String type;
	private String imagePath;
	private String productInfo;
	private String productDesc;
	private String grade;
	private String size;
	private String color;
	private String minOrderQty;
	private String quantityAvailable;
	private String price;
	private Integer priceNegotiable;
	private String measurement;
	private Date available;
	private String packaging;
	private int isActive;
//	private Set orders = new HashSet(0);

	public Product() {
	}

	public Product(UserProfile userProfile, String name, String quantityAvailable, String price, String measurement,
			int isActive) {
		this.userProfile = userProfile;
		this.name = name;
		this.quantityAvailable = quantityAvailable;
		this.price = price;
		this.measurement = measurement;
		this.isActive = isActive;
	}

	public Product(UserProfile userProfile, String name, String type, String imagePath, String productInfo,
			String productDesc, String grade, String size, String color, String minOrderQty, String quantityAvailable,
			String price, Integer priceNegotiable, String measurement, Date available, String packaging, int isActive) {
		
//			Set orders) {
		this.userProfile = userProfile;
		this.name = name;
		this.type = type;
		this.imagePath = imagePath;
		this.productInfo = productInfo;
		this.productDesc = productDesc;
		this.grade = grade;
		this.size = size;
		this.color = color;
		this.minOrderQty = minOrderQty;
		this.quantityAvailable = quantityAvailable;
		this.price = price;
		this.priceNegotiable = priceNegotiable;
		this.measurement = measurement;
		this.available = available;
		this.packaging = packaging;
		this.isActive = isActive;
//		this.orders = orders;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SELLER_ID", nullable = false)
	public UserProfile getUserProfile() {
		return this.userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	@Column(name = "NAME", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "TYPE")
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "IMAGE_PATH")
	public String getImagePath() {
		return this.imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Column(name = "PRODUCT_INFO")
	public String getProductInfo() {
		return this.productInfo;
	}

	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}

	@Column(name = "PRODUCT_DESC")
	public String getProductDesc() {
		return this.productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	@Column(name = "GRADE", length = 45)
	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Column(name = "SIZE", length = 45)
	public String getSize() {
		return this.size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Column(name = "COLOR", length = 45)
	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Column(name = "MIN_ORDER_QTY", length = 45)
	public String getMinOrderQty() {
		return this.minOrderQty;
	}

	public void setMinOrderQty(String minOrderQty) {
		this.minOrderQty = minOrderQty;
	}

	@Column(name = "QUANTITY_AVAILABLE", nullable = false)
	public String getQuantityAvailable() {
		return this.quantityAvailable;
	}

	public void setQuantityAvailable(String quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	@Column(name = "PRICE", nullable = false)
	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Column(name = "PRICE_NEGOTIABLE")
	public Integer getPriceNegotiable() {
		return this.priceNegotiable;
	}

	public void setPriceNegotiable(Integer priceNegotiable) {
		this.priceNegotiable = priceNegotiable;
	}

	@Column(name = "MEASUREMENT", nullable = false)
	public String getMeasurement() {
		return this.measurement;
	}

	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "AVAILABLE", length = 10)
	public Date getAvailable() {
		return this.available;
	}

	public void setAvailable(Date available) {
		this.available = available;
	}

	@Column(name = "PACKAGING", length = 45)
	public String getPackaging() {
		return this.packaging;
	}

	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}

	@Column(name = "IS_ACTIVE", nullable = false)
	public int getIsActive() {
		return this.isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
//	public Set getOrders() {
//		return this.orders;
//	}
//
//	public void setOrders(Set orders) {
//		this.orders = orders;
//	}

}

