package io.nology.employeeCreatorBackend.converters;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class StringTrimConverter implements Converter<String, String> {

	@Override
	public String convert(MappingContext<String, String> context) {
		// TODO Auto-generated method stub
		if(context.getSource() == null) {
			return null;
		}
		return context.getSource().trim();
	}

}
