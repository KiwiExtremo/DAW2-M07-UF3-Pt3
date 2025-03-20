package cat.institutmarianao.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyJoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	// NOTE: status
	public enum Status {
		PENDING, TRANSIT, DELIVERY, ABSENT, PENDING_COLLECTION, RETURNED
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reference;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private User client;

//  NOTE: Alternative as an embedded object
//	@Embedded
	@NotNull
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "delivery_address_id")
	private Address deliveryAddress;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "delivery_date")
	private Date deliveryDate;

	@NotEmpty
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "orders_items", joinColumns = @JoinColumn(name = "order_reference"))
	@MapKeyJoinColumn(name = "item_reference")
	@Column(name = "quantity")
	private Map<Item, Integer> items;

//  NOTE: The following fields are not needed anymore as they are calculated on the fly
//	@Positive
//	@Formula("(SELECT SUM(oi.quantity) FROM orders_items oi WHERE oi.order_reference=reference)")
//	private Integer totalQuantity = 0;

//	@Formula("(SELECT sum(i.price * oi.quantity) "
//			+ "FROM orders_items oi INNER JOIN items i ON oi.item_reference=i.reference "
//			+ "WHERE oi.order_reference=reference)")
//	private Double totalAmount = 0d;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Status status = Status.PENDING;

	public Long getReference() {
		return reference;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	@Enumerated(EnumType.STRING)
	public Status getStatus() {
		return status;
	}

	@Enumerated(EnumType.STRING)
	public void setStatus(Status status) {
		this.status = status;
	}

	public Map<Item, Integer> getItems() {
		if (items == null) {
			items = new HashMap<>();
		}
		return items;
	}

	public void setItems(Map<Item, Integer> items) {
		this.items = Objects.requireNonNullElse(items, new HashMap<>());
	}

	public Integer getTotalQuantity() {
		return items.values().stream().reduce(0, Integer::sum);
	}

	public Double getTotalAmount() {
		return items.entrySet().stream().map(e -> e.getKey().getPrice() * e.getValue()).reduce(0d, Double::sum);
	}

	@Override
	public int hashCode() {
		return Objects.hash(reference);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object instanceof Order order) {
			return Objects.equals(reference, order.reference);
		}
		return false;
	}

}
