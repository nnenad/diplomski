package com.example.diplomski.commons;

import org.apache.tapestry5.ValueEncoder;

public class IntegerValueEncoder implements ValueEncoder<Integer> {

	/** 
     * @return The returned  string value is used in <option> 
     */  
    public String toClient(Integer value) {  
          
        return value.toString();  
    }  
  
      
      
    /** 
     * @param  clientValue is the value from <option> 
     */  
    public Integer toValue(String clientValue) {  
          
        return Integer.parseInt(clientValue);  
    }  

}
