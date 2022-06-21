package com.MyHotel.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;



import com.MyHotel.model.Stanza;
import com.MyHotel.service.StanzaService;



@Component
public class StanzaValidator implements Validator {
	@Autowired
	private StanzaService stanzaService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Stanza.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
//		if (this.stanzaService.aldreadyExist((Stanza)target))
//			errors.reject("stanza.duplicato");
//		
	}
}
