package com.MyHotel.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.MyHotel.model.Hotel;
import com.MyHotel.service.HotelService;







@Component
public class HotelValidator implements Validator {
	@Autowired
	private HotelService hotelService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Hotel.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		if (this.hotelService.aldreadyExist((Hotel)target))
			errors.reject("hotel.duplicato");
		
	}

}
