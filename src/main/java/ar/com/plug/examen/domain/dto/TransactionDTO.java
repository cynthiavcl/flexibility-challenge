package ar.com.plug.examen.domain.dto;

import java.time.LocalDate;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**se considera transacciones por un unico producto**/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
	
	private Long id;
	private Long customer;
	private Long product;
	private Integer quantity;
	private Double amount;
	private LocalDate date;
	private boolean approved;

}
