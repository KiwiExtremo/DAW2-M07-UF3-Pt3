package cat.institutmarianao.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "addresses")
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final int MAX_ADDRESS = 150;
	private static final int MAX_ZIP_CODE = 20;
	private static final int MAX_CITY = 150;
	private static final int MAX_STATE = 150;
	private static final int MAX_COUNTRY = 150;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "recipient_name")
	private String recipientName;

	@NotBlank
	@Size(max = MAX_ADDRESS)
	private String address;

	@NotBlank
	@Size(max = MAX_ZIP_CODE)
	@Column(name = "zip_code")
	private String zipCode;

	@NotBlank
	@Size(max = MAX_CITY)
	private String city;

	@NotBlank
	@Size(max = MAX_STATE)
	private String state;

	@NotBlank
	@Size(max = MAX_COUNTRY)
	private String country;

	public Long getId() {
		return id;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object instanceof Address address) {
			return Objects.equals(id, address.id);
		}
		return false;
	}

}
