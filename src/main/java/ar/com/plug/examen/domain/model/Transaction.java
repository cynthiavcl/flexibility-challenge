package ar.com.plug.examen.domain.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**se considera transacciones por un unico producto**/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	//@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private Customer customer;
	@ManyToOne
	//@JoinColumn(name = "product_id", referencedColumnName = "id")
	private Product product;
	private Integer quantity;
	private Double amount;
	private LocalDate date;
	private boolean approved;

}
