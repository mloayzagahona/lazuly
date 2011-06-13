package net.sf.mp.demo.conference.enumeration.conference;

import java.util.ArrayList;
import java.util.List;

public enum PresentationStatusEnum {

    PROPOSAL("PROPOSAL"),
    ACTIVE("ACTIVE");

    private final String value;

    PresentationStatusEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }
    
    
    public static PresentationStatusEnum fromValue(String v) {
        for (PresentationStatusEnum c : PresentationStatusEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        return null;
    }


    /**
    * Return a list that contains all the enumeration values      
    * @return List<PresentationStatusEnum> the that contains all the enumeration values  
    */     
    public static List<PresentationStatusEnum> getList() {
	    List<PresentationStatusEnum> list = new ArrayList<PresentationStatusEnum>();
	    for (PresentationStatusEnum c : PresentationStatusEnum.values()) {
		   list.add(c);
	 	}
	    return list;
    }

    /**
    * @param value 
    * @return boolean : true if the value exist in the enum
    */
    public static boolean contains (String value) {
       for (PresentationStatusEnum c : PresentationStatusEnum.values()) {
          if (c.toString().equals(value))
             return true;
          }    	 
       return false;
    }     

    public static boolean containsValue (String value) {
        for (PresentationStatusEnum c : PresentationStatusEnum.values()) {
           if (c.value().equals(value))
              return true;
           }    	 
        return false;
    }

    public boolean equals (String s) {
    	if (s==null) return false;
    	return s.equals(value);
    }   	     
    
}