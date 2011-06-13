package net.sf.mp.demo.conference.enumeration.conference;

import java.util.ArrayList;
import java.util.List;

public enum SponsorStatusEnum {

    PENDING("PENDING"),
    ACTIVE("ACTIVE");

    private final String value;

    SponsorStatusEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }
    
    
    public static SponsorStatusEnum fromValue(String v) {
        for (SponsorStatusEnum c : SponsorStatusEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        return null;
    }


    /**
    * Return a list that contains all the enumeration values      
    * @return List<SponsorStatusEnum> the that contains all the enumeration values  
    */     
    public static List<SponsorStatusEnum> getList() {
	    List<SponsorStatusEnum> list = new ArrayList<SponsorStatusEnum>();
	    for (SponsorStatusEnum c : SponsorStatusEnum.values()) {
		   list.add(c);
	 	}
	    return list;
    }

    /**
    * @param value 
    * @return boolean : true if the value exist in the enum
    */
    public static boolean contains (String value) {
       for (SponsorStatusEnum c : SponsorStatusEnum.values()) {
          if (c.toString().equals(value))
             return true;
          }    	 
       return false;
    }     

    public static boolean containsValue (String value) {
        for (SponsorStatusEnum c : SponsorStatusEnum.values()) {
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