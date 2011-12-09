package net.sf.mp.demo.conference.dao.impl.jpa.conference;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Date;
import java.sql.Timestamp;

import javax.persistence.Query;
import javax.persistence.EntityManager;

import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import net.sf.minuteProject.architecture.bsla.bean.criteria.PaginationCriteria;
import net.sf.mp.demo.conference.dao.face.conference.AddressDao;
import net.sf.mp.demo.conference.domain.conference.Address;
import net.sf.mp.demo.conference.domain.admin.Country;

/**
 *
 * <p>Title: AddressJPAImpl</p>
 *
 * <p>Description: Interface of a Data access object dealing with AddressJPAImpl
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching AddressJPAImpl objects</p>
 *
 */

public class AddressJPAImpl extends JpaDaoSupport implements AddressDao {

	public AddressJPAImpl () {}

    /**
     * Inserts a Address entity 
     * @param Address address
     */
    public void insertAddress(Address address) {
       convertTransientReferenceToNull (address);
       getJpaTemplate().persist(address);
    }

    protected void insertAddress(EntityManager emForRecursiveDao, Address address) {
       emForRecursiveDao.persist(address);
    } 
    /**
     * Inserts a list of Address entity 
     * @param List<Address> addresss
     */
    public void insertAddresss(List<Address> addresss) {
    	//TODO
    }
    /**
     * Updates a Address entity 
     * @param Address address
     */
    public Address updateAddress(Address address) {
       convertTransientReferenceToNull (address);
       return getJpaTemplate().merge(address);
    }

	/**
     * Updates a Address entity with only the attributes set into Address.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is issued
     * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
     * @param Address address
    */ 
    @Transactional
    public int updateNotNullOnlyAddress(Address address) {
        Query jpaQuery = getEntityManager().createQuery(getUpdateNotNullOnlyAddressQueryChunk(address));
        if (address.getId() != null) {
           jpaQuery.setParameter ("id", address.getId());
        }   
        if (address.getStreet1() != null) {
           jpaQuery.setParameter ("street1", address.getStreet1());
        }   
        if (address.getStreet2() != null) {
           jpaQuery.setParameter ("street2", address.getStreet2());
        }   
        if (address.getCountryId() != null) {
           jpaQuery.setParameter ("countryId", address.getCountryId());
        }   
		return jpaQuery.executeUpdate();
    }

    protected String getUpdateNotNullOnlyAddressQueryChunkPrototype (Address address) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update Address address ");
        if (address.getStreet1() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" address.street1 = :street1");
        }
        if (address.getStreet2() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" address.street2 = :street2");
        }
        if (address.getCountryId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" address.countryId = :countryId");
        }
        return query.toString();
    }
    
    protected String getUpdateNotNullOnlyAddressQueryChunk (Address address) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer(getUpdateNotNullOnlyAddressQueryChunkPrototype(address));
        if (address.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
			     query.append(" address.id = :id");
        }
        return query.toString();
    }
    
                
	protected Address assignBlankToNull (Address address) {
	    if (address==null)
			return null;
        if (address.getStreet1()!=null && address.getStreet1().equals(""))
           address.setStreet1((String)null);
        if (address.getStreet2()!=null && address.getStreet2().equals(""))
           address.setStreet2((String)null);
		return address;
	}
	
	protected boolean isAllNull (Address address) {
	    if (address==null)
			return true;
        if (address.getId()!=null) 
            return false;
        if (address.getStreet1()!=null) 
            return false;
        if (address.getStreet2()!=null) 
            return false;
        if (address.getCountryId()!=null) 
            return false;
		return true;
	}
		
	@Transactional
    public int updateNotNullOnlyPrototypeAddress(Address address, Address prototypeCriteria) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update Address address ");
        if (address.getId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" address.id = "+ address.getId() + " ");
        }
        if (address.getStreet1() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" address.street1 = '"+ address.getStreet1()+"' ");
        }
        if (address.getStreet2() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" address.street2 = '"+ address.getStreet2()+"' ");
        }
        if (address.getCountryId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" address.countryId = "+ address.getCountryId() + " ");
        }
		isWhereSet = false; 
        if (prototypeCriteria.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" address.id = "+ prototypeCriteria.getId() + " ");
        }
        if (prototypeCriteria.getStreet1() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" address.street1 = '"+ prototypeCriteria.getStreet1()+"' ");
        }
        if (prototypeCriteria.getStreet2() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" address.street2 = '"+ prototypeCriteria.getStreet2()+"' ");
        }
        if (prototypeCriteria.getCountryId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" address.countryId = "+ prototypeCriteria.getCountryId() + " ");
        }
        Query jpaQuery = getEntityManager().createQuery(query.toString());
		return jpaQuery.executeUpdate();
    }
     
     /**
     * Saves a Address entity 
     * @param Address address
     */
    public void saveAddress(Address address) {
       //getJpaTemplate().persist(address);
       convertTransientReferenceToNull (address);
       if (getJpaTemplate().contains(address)) {
          getJpaTemplate().merge(address);
       } else {
          getJpaTemplate().persist(address);
       }
       getJpaTemplate().flush(); 
    }
       
    /**
     * Deletes a Address entity 
     * @param Address address
     */
    public void deleteAddress(Address address) {
      getJpaTemplate().remove(address);
    }
    
    /**
     * Loads the Address entity which is related to an instance of
     * Address
     * @param Long id
     * @return Address The Address entity
     
    public Address loadAddress(Long id) {
    	return (Address)getJpaTemplate().get(Address.class, id);
    }
*/
  
    /**
     * Loads the Address entity which is related to an instance of
     * Address
     * @param java.lang.Long Id
     * @return Address The Address entity
     */
    public Address loadAddress(java.lang.Long id) {
    	return (Address)getJpaTemplate().find(Address.class, id);
    }
    
    /**
     * Loads a list of Address entity 
     * @param List<java.lang.Long> ids
     * @return List<Address> The Address entity
     */
    public List<Address> loadAddressListByAddress (List<Address> addresss) {
       return null;
    }
    
    /**
     * Loads a list of Address entity 
     * @param List<java.lang.Long> ids
     * @return List<Address> The Address entity
     */
    public List<Address> loadAddressListById(List<java.lang.Long> ids){
       return null;
    }
    
    /**
     * Loads the Address entity which is related to an instance of
     * Address and its dependent one to many objects
     * @param Long id
     * @return Address The Address entity
     */
    public Address loadFullFirstLevelAddress(java.lang.Long id) {
        List list = getJpaTemplate().find(
                     "SELECT address FROM Address address "
                     + " LEFT JOIN address.conferenceAddressIds "   
                     + " LEFT JOIN address.conferenceMemberAddressIds "   
                     + " LEFT JOIN address.sponsorAddressIds "   
                     + " WHERE address.id = "+id
               );
         if (list!=null && !list.isEmpty())
            return (Address)list.get(0);
         return null;
    	//return null;//(Address) getJpaTemplate().queryForObject("loadFullFirstLevelAddress", id);
    }

    /**
     * Loads the Address entity which is related to an instance of
     * Address
     * @param Address address
     * @return Address The Address entity
     */
    public Address loadFullFirstLevelAddress(Address address) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append ("SELECT address FROM Address address ");
        query.append (" LEFT JOIN address.conferenceAddressIds ");
        query.append (" LEFT JOIN address.conferenceMemberAddressIds ");
        query.append (" LEFT JOIN address.sponsorAddressIds ");
        if (address.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" address.id = "+ address.getId() + " ");
         }
	        	List list = getJpaTemplate().find(query.toString());
        if (list!=null && !list.isEmpty())
           return (Address)list.get(0);    
        return null;
    }  
     
    /**
     * Loads the Address entity which is related to an instance of
     * Address and its dependent objects one to many
     * @param Long id
     * @return Address The Address entity
     */
    public Address loadFullAddress(Long id) {
    	return null;//(Address)getJpaTemplate().queryForObject("loadFullAddress", id);
    }

    /**
     * Searches a list of Address entity 
     * @param Address address
     * @return List
     */
    public List<Address> searchPrototypeAddress(Address address) {      
       return searchPrototype (address, null);            
    }  
    
    protected List<Address> searchPrototype (Address address, Integer maxResults) { 
       Query hquery = getEntityManager().createQuery(getAddressSearchEqualQuery (address));
       if (maxResults!=null)
          hquery.setMaxResults(maxResults);
       return hquery.getResultList();         
    }        
    
    public List<Address> searchPrototypeAddress (List<Address> addresss) {
       return searchPrototype (addresss, null);  
    }

    protected List<Address> searchPrototype (List<Address> addresss, Integer maxResults) {    
       //TODO convert setMaxResults in JPA if (maxResults!=null)
       //   getJpaTemplate().setMaxResults(maxResults);
       return getJpaTemplate().find(getAddressSearchEqualQuery (null, addresss));            
    }    

    public List<Address> searchDistinctPrototypeAddress (Address addressMask, List<Address> addresss) {
        return getJpaTemplate().find(getAddressSearchEqualQuery (addressMask, addresss));    
    }
         
	/**
     * Searches a list of Address entity 
     * @param Address addressPositive
     * @param Address addressNegative
     * @return List
     */
    public List<Address> searchPrototypeAddress(Address addressPositive, Address addressNegative) {
	    return getJpaTemplate().find(getAddressSearchEqualQuery (addressPositive, addressNegative));  
    }

    /**
    * return a jql query search on a Address prototype
    */
    protected String getAddressSearchEqualQuery (Address address) {
        StringBuffer query = new StringBuffer();
        StringBuffer queryWhere = new StringBuffer();
        query.append ("SELECT address FROM Address address ");
        queryWhere.append (getAddressSearchEqualWhereQueryChunk(address, false));   
	    return getHQuery(query.toString(), queryWhere.toString());
    }


    /**
    * return a jql search for a list of Address prototype
    */
    protected String getAddressSearchEqualQuery (Address addressMask, List<Address> addresss) {
        boolean isOrSet = false;
        StringBuffer query = new StringBuffer();
        if (addressMask !=null)
           query.append (getAddressMaskWhat (addressMask));
        query.append (" FROM Address address ");
        StringBuffer queryWhere = new StringBuffer();
        for (Address address : addresss) {
           if (!isAllNull(address)) {        
	           queryWhere.append (getQueryOR (isOrSet));
	           isOrSet = true;        
	           queryWhere.append (" ("+getAddressSearchEqualWhereQueryChunk(address, false)+") ");
           }
        }
	    return getHQuery(query.toString(), queryWhere.toString());
    }	
    
    protected String getAddressMaskWhat (Address addressMask) {
        boolean isCommaSet = false;
        StringBuffer query = new StringBuffer("SELECT DISTINCT ");
        if (addressMask.getId() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" id ");
        }
        if (addressMask.getStreet1() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" street1 ");
        }
        if (addressMask.getStreet2() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" street2 ");
        }
        if (addressMask.getCountryId() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" countryId ");
        }
        if (!isCommaSet)
           return "";
	    return query.toString();        
    }
    
    protected String getAddressSearchEqualWhereQueryChunk (Address address, boolean isAndSet) {
        StringBuffer query = new StringBuffer();
        if (address.getId() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" address.id = "+ address.getId() + " ");
        }
        if (address.getStreet1() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" address.street1 = '"+ address.getStreet1()+"' ");
        }
        if (address.getStreet2() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" address.street2 = '"+ address.getStreet2()+"' ");
        }
        if (address.getCountryId() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" address.countryId = "+ address.getCountryId() + " ");
        }
	    return query.toString();
    }   
     
    /**
    * return a jql search on a Address prototype with positive and negative beans
    */
    protected String getAddressSearchEqualQuery (Address addressPositive, Address addressNegative) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" SELECT address FROM Address address ");
        if (addressPositive!=null && addressPositive.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" address.id = "+ addressPositive.getId() + " ");
        } else if (addressNegative.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" address.id is null ");
        }
        if (addressPositive!=null && addressPositive.getStreet1() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" address.street1 = '"+ addressPositive.getStreet1()+"' ");
        } else if (addressNegative.getStreet1() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" address.street1 is null ");
        }
        if (addressPositive!=null && addressPositive.getStreet2() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" address.street2 = '"+ addressPositive.getStreet2()+"' ");
        } else if (addressNegative.getStreet2() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" address.street2 is null ");
        }
        if (addressPositive!=null && addressPositive.getCountryId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" address.countryId = "+ addressPositive.getCountryId() + " ");
        } else if (addressNegative.getCountryId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" address.countryId is null ");
        }
	    return query.toString();
    }
    
    /**
     * Load a paginated list of Address entity dependent of pagination criteria
     * @param PaginationCriteria paginationCriteria
     * @return List
     */
    public List <Address> loadPaginatedAddress (Address address, PaginationCriteria paginationCriteria) {
	    List<Long> page = loadPaginatedAddressIdentitiesFromStartPositionId(address, paginationCriteria);
    	int start = paginationCriteria.getNumberOfRowsReturned()*(paginationCriteria.getPageNumber());
    	int max = page.size();
    	if (start<max) {
    	   List<Long> returnPage = page.subList(start, max);	
           StringBuffer query = new StringBuffer();
           query.append (" SELECT address FROM Address address ");      
	       query.append(" where address.id in (");
	       for (Iterator iter = returnPage.iterator(); iter.hasNext();) {
			  Long id = (Long) iter.next();
			  query.append(id.toString());
		      if (iter.hasNext())
			     query.append(",");
		   }
	       query.append(") ");
	       return getJpaTemplate().find(query.toString()); 
    	} 
        return new ArrayList<Address>();
    }      

    protected List<Long> loadPaginatedAddressIdentitiesFromStartPositionId (Address address, PaginationCriteria paginationCriteria) {
       boolean isWhereSet = false;
       StringBuffer query = new StringBuffer();
       query.append ("select address.id ");
       query.append (getAddressSearchEqualQuery (address));
       if (paginationCriteria.getOrderList()!=null) {
    	   query.append(" order by "+paginationCriteria.getOrderList());
       }
       int maxResult = paginationCriteria.getNumberOfRowsReturned()*(1+paginationCriteria.getPageNumber());
       List<Long> set = getEntityManager().createNamedQuery(query.toString()).setMaxResults(maxResult).getResultList();
       return set;
    }

    //TODO put in upper class
    
	protected String getQueryWHERE_AND(boolean isWhereSet) {
		if (isWhereSet)
			return " AND ";
		return " WHERE ";
	}

	protected String getQueryCommaSet(boolean isWhereSet) {
		if (isWhereSet)
			return " , ";
		return " SET ";
	}

	protected static String getQuerySelectComma(boolean isSelectSet) {
		if (isSelectSet)
			return " , ";
		return " select ";
	}

	protected static String getQueryBLANK_AND(boolean isBlankSet) {
		if (isBlankSet)
			return " AND ";
		return " ";
	}

	protected static String getQueryBLANK_COMMA(boolean isBlankSet) {
		if (isBlankSet)
			return " , ";
		return " ";
	}

	protected String getQueryComma(boolean isCommaSet) {
		return (isCommaSet) ? " , " : "";
	}

	protected String getQueryOR(boolean isOrSet) {
		return (isOrSet) ? " OR " : "";
	}

	protected String getQueryAND(boolean isAndSet) {
		return (isAndSet) ? " AND " : "";
	}

	protected String getHQuery (String what, String where) {
        String whereWord = getWhere (where);
	    return what+whereWord+where;
    }
    
    protected String getWhere (String where) {
        return (where==null || where.trim().equals("") || where.trim()==null)?"":" WHERE ";
    }   
    protected EntityManager getEntityManager () {
        return EntityManagerFactoryUtils.getTransactionalEntityManager(getJpaTemplate().getEntityManagerFactory());    
    }
    
    protected void convertTransientReferenceToNull (Address address) {
	   address.setCountryId ((Country)null);
    }
}
