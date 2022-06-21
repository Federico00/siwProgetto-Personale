package com.MyHotel.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.MyHotel.model.Pacchetto;
import com.MyHotel.service.PacchettoService;







@Component
public class PacchettoValidator implements Validator {
	@Autowired
	private PacchettoService pacchettoService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Pacchetto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		if (this.pacchettoService.aldreadyExist((Pacchetto)target))
			errors.reject("pacchetto.duplicato");
		
	}

}
