package net.sf.mp.demo.conference.enumeration.conference;

import java.util.ArrayList;
import java.util.List;

public enum SponsorPrivilegeTypeEnum {

    GOLDEN("Golden"),
    SILVER("Silver"),
    BRONZE("Bronze");

    private final String value;

    SponsorPrivilegeTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }
    
    
    public static SponsorPrivilegeTypeEnum fromValue(String v) {
        for (SponsorPrivilegeTypeEnum c : SponsorPrivilegeTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        return null;
    }


    /**
    * Return a list that contains all the enumeration values      
    * @return List<SponsorPrivilegeTypeEnum> the that contains all the enumeration values  
    */     
    public static List<SponsorPrivilegeTypeEnum> getList() {
	    List<SponsorPrivilegeTypeEnum> list = new ArrayList<SponsorPrivilegeTypeEnum>();
	    for (SponsorPrivilegeTypeEnum c : SponsorPrivilegeTypeEnum.values()) {
		   list.add(c);
	 	}
	    return list;
    }

    /**
    * @param value 
    * @return boolean : true if the value exist in the enum
    */
    public static boolean contains (String value) {
       for (SponsorPrivilegeTypeEnum c : SponsorPrivilegeTypeEnum.values()) {
          if (c.toString().equals(value))
             return true;
          }    	 
       return false;
    }     

    public static boolean containsValue (String value) {
        for (SponsorPrivilegeTypeEnum c : SponsorPrivilegeTypeEnum.values()) {
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