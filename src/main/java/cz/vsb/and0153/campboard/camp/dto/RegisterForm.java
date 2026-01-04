package cz.vsb.and0153.campboard.camp.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterForm {
	@NotNull(message = "{validation.person.required}")
	private Long person;

	@NotNull(message = "{validation.payment.required}")
	private PaymentMethod payment;

	private String info;
}
