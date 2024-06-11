package ar.com.plug.examen.domain.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
	
	private Long id;
	private String name;
	private String surname;
	private LocalDate birth;
	private String address;
	private Long province;
	private String email;
	private String city;
	

}
