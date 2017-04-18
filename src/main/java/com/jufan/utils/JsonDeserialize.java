package com.jufan.utils;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;
import java.util.Date;


public class JsonDeserialize extends JsonSerializer<Date>{
//	private  final static Logger logger = LoggerFactory.getLogger("JsonDeserialize");

	@Override
	public void serialize(Date value, JsonGenerator jgen, SerializerProvider arg2)
			throws IOException, JsonProcessingException {
		if(value!=null){
			if(jgen!=null)jgen.writeString(TimeUtil.getHmsTime(value));
		}

	}

}
