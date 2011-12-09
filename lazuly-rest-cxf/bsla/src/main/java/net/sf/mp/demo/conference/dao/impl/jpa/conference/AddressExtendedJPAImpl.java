package net.sf.mp.demo.conference.dao.impl.jpa.conference;

import java.lang.reflect.InvocationTargetException;
import java.sql.Clob;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.persistence.Query;
import javax.persistence.EntityManager;

import net.sf.minuteProject.architecture.query.QueryWhatInit;
import net.sf.minuteProject.architecture.query.impl.QueryCountInit;
import net.sf.minuteProject.architecture.query.impl.QuerySelectCountInit;
import net.sf.minuteProject.architecture.query.impl.QuerySelectInit;
import net.sf.minuteProject.architecture.query.impl.QuerySelectDistinctInit;
import net.sf.minuteProject.architecture.cache.SimpleCache;
import net.sf.minuteProject.architecture.filter.data.ClauseCriterion;
import net.sf.minuteProject.architecture.filter.data.Criteria;
import net.sf.minuteProject.architecture.filter.data.Criterion;
import net.sf.minuteProject.architecture.filter.data.InCriterion;
import net.sf.minuteProject.architecture.filter.data.OrderCriteria;
import net.sf.minuteProject.architecture.utils.BeanUtils;
import net.sf.mp.demo.conference.dao.face.conference.AddressExtDao;
import net.sf.mp.demo.conference.domain.conference.Address;
import net.sf.mp.demo.conference.dao.impl.jpa.conference.AddressJPAImpl;

import net.sf.mp.demo.conference.domain.conference.Conference;
import net.sf.mp.demo.conference.dao.impl.jpa.conference.ConferenceExtendedJPAImpl;
import net.sf.mp.demo.conference.domain.conference.ConferenceMember;
import net.sf.mp.demo.conference.dao.impl.jpa.conference.ConferenceMemberExtendedJPAImpl;
import net.sf.mp.demo.conference.domain.conference.Sponsor;
import net.sf.mp.demo.conference.dao.impl.jpa.conference.SponsorExtendedJPAImpl;
import net.sf.mp.demo.conference.dao.impl.jpa.admin.CountryExtendedJPAImpl;
/**
 *
 * <p>Title: AddressExtendedJPAImpl</p>
 *
 * <p>Description: Interface of a Data access object dealing with AddressExtendedJPAImpl
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching AddressExtendedJPAImpl objects</p>
 *
 */

public class AddressExtendedJPAImpl extends AddressJPAImpl implements AddressExtDao {

    private Logger log = Logger.getLogger(this.getClass());
    
    private SimpleCache simpleCache = new SimpleCache();
    private ConferenceExtendedJPAImpl conferenceextendedjpaimpl;
    private ConferenceMemberExtendedJPAImpl conferencememberextendedjpaimpl;
    private SponsorExtendedJPAImpl sponsorextendedjpaimpl;
    private EntityManager emForRecursiveDao; // dao that needs other dao in a recursive manner not support by spring configuration

    public AddressExtendedJPAImpl () {}

    /**
     * generic to move after in superclass
     */
    public AddressExtendedJPAImpl (EntityManager emForRecursiveDao) {
       this.emForRecursiveDao = emForRecursiveDao;
    }
            
    /**
     * generic to move after in superclass
     */
    public List<Object[]> getSQLQueryResult(String query) {
		   Query queryJ = getEntityManager().createNativeQuery(query);
		   return queryJ.getResultList();		        
    }


    /**
     * Inserts a Address entity with cascade of its children
     * @param Address address
     */
    public void insertAddressWithCascade(Address address) {
    	AddressExtendedJPAImpl addressextendedjpaimpl = new AddressExtendedJPAImpl(getEntityManager());
    	addressextendedjpaimpl.insertAddressWithCascade(addressextendedjpaimpl.getEntityManagerForRecursiveDao(), address);
    }
     
    public void insertAddressWithCascade(EntityManager emForRecursiveDao, Address address) {
       insertAddress(emForRecursiveDao, address);
       if (!address.getConferenceAddressViaAddressId().isEmpty()) {
          ConferenceExtendedJPAImpl conferenceextendedjpaimpl = new ConferenceExtendedJPAImpl (emForRecursiveDao);
          for (Conference _conferenceAddressViaAddressId : address.getConferenceAddressViaAddressId()) {
             conferenceextendedjpaimpl.insertConferenceWithCascade(emForRecursiveDao, _conferenceAddressViaAddressId);
          }
       } 
       if (!address.getConferenceMemberAddressViaAddressId().isEmpty()) {
          ConferenceMemberExtendedJPAImpl conferencememberextendedjpaimpl = new ConferenceMemberExtendedJPAImpl (emForRecursiveDao);
          for (ConferenceMember _conferenceMemberAddressViaAddressId : address.getConferenceMemberAddressViaAddressId()) {
             conferencememberextendedjpaimpl.insertConferenceMemberWithCascade(emForRecursiveDao, _conferenceMemberAddressViaAddressId);
          }
       } 
       if (!address.getSponsorAddressViaAddressId().isEmpty()) {
          SponsorExtendedJPAImpl sponsorextendedjpaimpl = new SponsorExtendedJPAImpl (emForRecursiveDao);
          for (Sponsor _sponsorAddressViaAddressId : address.getSponsorAddressViaAddressId()) {
             sponsorextendedjpaimpl.insertSponsorWithCascade(emForRecursiveDao, _sponsorAddressViaAddressId);
          }
       } 
    }
        
    /**
     * Inserts a list of Address entity with cascade of its children
     * @param List<Address> addresss
     */
    public void insertAddresssWithCascade(List<Address> addresss) {
       for (Address address : addresss) {
          insertAddressWithCascade(address);
       }
    } 
        
    /**
     * lookup Address entity Address, criteria and max result number
     */
    public List<Address> lookupAddress(Address address, Criteria criteria, Integer numberOfResult, EntityManager em) {
		boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append ("SELECT address FROM Address address ");
        for (Criterion criterion : criteria.getClauseCriterions()) {
            query.append (getQueryWHERE_AND (isWhereSet));
            isWhereSet = true;   
            query.append(criterion.getExpression());			
        }
        OrderCriteria orderCriteria = criteria.getOrderCriteria();
        if (criteria.getOrderCriteria()!=null)
        	query.append(orderCriteria.getExpression());		
        Query hquery = em.createQuery(query.toString());
        if (numberOfResult!=null)
            hquery.setMaxResults(numberOfResult);
        return hquery.getResultList();    
    }
    
    public List<Address> lookupAddress(Address address, Criteria criteria, Integer numberOfResult) {
		return lookupAddress(address, criteria, numberOfResult, getEntityManager());
    }

    public Integer updateNotNullOnlyAddress (Address address, Criteria criteria) {
        String queryWhat = getUpdateNotNullOnlyAddressQueryChunkPrototype (address);
        StringBuffer query = new StringBuffer (queryWhat);
        boolean isWhereSet = false;
        for (Criterion criterion : criteria.getClauseCriterions()) {
            query.append (getQueryWHERE_AND (isWhereSet));
            isWhereSet = true;   
            query.append(criterion.getExpression());			
        }  
        Query jpaQuery = getEntityManager().createQuery(query.toString());
        isWhereSet = false;
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
	
	public Address affectAddress (Address address) {
	    return referAddress (address, false);		    
	}
		
	/**
	 * Assign the first address retrieved corresponding to the address criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no address corresponding in the database. Then address is inserted and returned with its primary key(s). 
	 */
	public Address assignAddress (Address address) {
	    return referAddress (address, true);	
	}
	
	public Address referAddress (Address address, boolean isAssign) {
		address = assignBlankToNull (address);
		if (isAllNull(address))
			return null;
		else {
			List<Address> list = searchPrototypeAddress(address);
			if (list.isEmpty()) {
			    if (isAssign)
			       insertAddress(address);
			    else
				   return null;
			}
			else if (list.size()==1)
				address.copy(list.get(0));
			else 
				//TODO log error
				address.copy(list.get(0));
		}
		return address;		    
	}

   public Address assignAddressUseCache (Address address) {
      return referAddressUseCache (address, true);
   }
      		
   public Address affectAddressUseCache (Address address) {
      return referAddressUseCache (address, false);
   }
      		
   public Address referAddressUseCache (Address address, boolean isAssign) {
	  String key = getCacheKey(null, address, null, "assignAddress");
      Address addressCache = (Address)simpleCache.get(key);
      if (addressCache==null) {
         addressCache = referAddress (address, isAssign);
         if (key!=null)
         	simpleCache.put(key, addressCache);
      }
      address.copy(addressCache);
      return addressCache;
   }	

	private String getCacheKey (Address addressWhat, Address positiveAddress, Address negativeAddress, String queryKey) {
	    StringBuffer sb = new StringBuffer();
	    sb.append(queryKey);
	    if (addressWhat!=null)
	       sb.append(addressWhat.toStringWithParents());
	    if (positiveAddress!=null)
	       sb.append(positiveAddress.toStringWithParents());
	    if (negativeAddress!=null)
	       sb.append(negativeAddress.toStringWithParents());
	    return sb.toString();
	}
	
    public Address partialLoadWithParentFirstAddress(Address addressWhat, Address positiveAddress, Address negativeAddress){
		List <Address> list = partialLoadWithParentAddress(addressWhat, positiveAddress, negativeAddress);
		return (!list.isEmpty())?(Address)list.get(0):null;
    }
    
    public Address partialLoadWithParentFirstAddressUseCache(Address addressWhat, Address positiveAddress, Address negativeAddress, Boolean useCache){
		List <Address> list = partialLoadWithParentAddressUseCache(addressWhat, positiveAddress, negativeAddress, useCache);
		return (!list.isEmpty())?(Address)list.get(0):null;
    }
	
	public Address partialLoadWithParentFirstAddressUseCacheOnResult(Address addressWhat, Address positiveAddress, Address negativeAddress, Boolean useCache){
		List <Address> list = partialLoadWithParentAddressUseCacheOnResult(addressWhat, positiveAddress, negativeAddress, useCache);
		return (!list.isEmpty())?(Address)list.get(0):null;
    }
	//
	protected List<Address> partialLoadWithParentAddress(Address addressWhat, Address positiveAddress, Address negativeAddress, Integer nbOfResult, Boolean useCache) {
		 return partialLoadWithParentAddress(addressWhat, positiveAddress, negativeAddress, new QuerySelectInit(), nbOfResult, useCache);
	}	  

	protected List partialLoadWithParentAddressQueryResult (Address addressWhat, Address positiveAddress, Address negativeAddress, Integer nbOfResult, Boolean useCache) {
		 return partialLoadWithParentAddressQueryResult (addressWhat, positiveAddress, negativeAddress, new QuerySelectInit(), nbOfResult, useCache);
	}	
    
    public List<Address> getDistinctAddress(Address addressWhat, Address positiveAddress, Address negativeAddress) {
		 return partialLoadWithParentAddress(addressWhat, positiveAddress, negativeAddress, new QuerySelectDistinctInit(), null, false);
	}
	
	public List<Address> partialLoadWithParentAddress(Address addressWhat, Address positiveAddress, Address negativeAddress) {
		 return partialLoadWithParentAddress(addressWhat, positiveAddress, negativeAddress, new QuerySelectInit(), null, false);
	}	
  
	public List<Address> partialLoadWithParentAddressUseCacheOnResult(Address addressWhat, Address positiveAddress, Address negativeAddress, Boolean useCache) {
		String key = getCacheKey(addressWhat, positiveAddress, negativeAddress, "partialLoadWithParentAddress");
		List<Address> list = (List<Address>)simpleCache.get(key);
		if (list==null || list.isEmpty()) {
			list = partialLoadWithParentAddress(addressWhat, positiveAddress, negativeAddress);
			if (!list.isEmpty())
			   simpleCache.put(key, list);
		}
		return list;	
	}	

	public List<Address> partialLoadWithParentAddressUseCache(Address addressWhat, Address positiveAddress, Address negativeAddress, Boolean useCache) {
		String key = getCacheKey(addressWhat, positiveAddress, negativeAddress, "partialLoadWithParentAddress");
		List<Address> list = (List<Address>)simpleCache.get(key);
		if (list==null) {
			list = partialLoadWithParentAddress(addressWhat, positiveAddress, negativeAddress);
			simpleCache.put(key, list);
		}
		return list;	
	}	
	
	private List<Address> handleLoadWithParentAddress(Map beanPath, List list, Address addressWhat) {
	    return handleLoadWithParentAddress(beanPath, list, addressWhat, true);  
	}
	
	private List<Address> handleLoadWithParentAddress(Map beanPath, List list, Address addressWhat, boolean isHql) {
		if (beanPath.size()==1) {
			return handlePartialLoadWithParentAddressWithOneElementInRow(list, beanPath, addressWhat, isHql);
		}
		return handlePartialLoadWithParentAddress(list, beanPath, addressWhat, isHql);	
	}
	
    	// to set in super class	
	protected void populateAddress (Address address, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	   BeanUtils.populateBeanObject(address, beanPath, value);
	}
	
	protected void populateAddressFromSQL (Address address, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	   BeanUtils.populateBeanObject(address, beanPath, value);
	}
    	// to set in super class BEWARE: genericity is only one level!!!!! first level is a copy second level is a reference!!! change to address.clone() instead
	private Address cloneAddress (Address address) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		//return (Address) BeanUtils.cloneBeanObject(address);
	   if (address==null) return new Address();
	   return address.clone();
	}
    
    // to set in super class
	private Object getBeanObjectInstance (Object bean) throws SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
       return BeanUtils.getBeanObjectInstance(bean);	
	}
	// to set in super class
	protected void populateObject (Object bean, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException {
       BeanUtils.populateObject(bean, value, beanPath);
	}
	
	protected void populateObjectFromSQL (Object bean, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException {
       BeanUtils.populateObject(bean, value, beanPath);
	}
	
    public List<Address> countDistinct (Address whatMask, Address whereEqCriteria) {
       return partialLoadWithParentAddress(whatMask, whereEqCriteria, null, new QuerySelectCountInit("address"), null, false);
    }   
    	
    public Long count(Address whereEqCriteria) {
        List<Address> list = partialLoadWithParentAddress(null, whereEqCriteria, null, new QueryCountInit("address"), null, false);    
    	if (!list.isEmpty())
    		return list.get(0).getCount__();
    	return 0L;
    }
		
		  		
   public Address getFirstAddressWhereConditionsAre (Address address) {
      List<Address> list = partialLoadWithParentAddress(getDefaultAddressWhat(), address, null, 1, false);
      if (list.isEmpty()) {
         return null;
      }
      else if (list.size()==1)
         return list.get(0);
      else 
      //TODO log error
         return list.get(0);	
	}

   private List getFirstResultWhereConditionsAre (Address address) {
      return  partialLoadWithParentAddressQueryResult(getDefaultAddressWhat(), address, null, 1, false);	
   }
   
   protected Address getDefaultAddressWhat() {
      Address address = new Address();
      address.setId(Long.valueOf("-1"));
      return address;
   }
   
	public Address getFirstAddress (Address address) {
		if (isAllNull(address))
			return null;
		else {
			List<Address> list = searchPrototype (address, 1);
			if (list.isEmpty()) {
				return null;
			}
			else if (list.size()==1)
				return list.get(0);
			else 
				//TODO log error
				return list.get(0);	
		}
	}
	
    /**
    * checks if the Address entity exists
    */           
    public boolean existsAddress (Address address) {
       if (getFirstAddress(address)!=null)
          return true;
       return false;  
    }
        
    public boolean existsAddressWhereConditionsAre (Address address) {
       if (getFirstResultWhereConditionsAre (address).isEmpty())
          return false;
       return true;  
    }


	
	private int countPartialField (Address address) {
	   int cpt = 0;
       if (address.getId() != null) {
          cpt++;
       }
       if (address.getStreet1() != null) {
          cpt++;
       }
       if (address.getStreet2() != null) {
          cpt++;
       }
       if (address.getCountryId() != null) {
          cpt++;
       }
       return cpt;
	}   
  	
	public List<Address> partialLoadWithParentAddress(Address addressWhat, Address positiveAddress, Address negativeAddress, QueryWhatInit queryWhatInit, Integer nbOfResult, Boolean useCache) {
		Map beanPath = new Hashtable();
		List list = partialLoadWithParentAddressJPAQueryResult (addressWhat, positiveAddress, negativeAddress, queryWhatInit, beanPath, nbOfResult, useCache);
		if (beanPath.size()==1) {
			return handlePartialLoadWithParentAddressWithOneElementInRow(list, beanPath, addressWhat, true);
		}
		return handlePartialLoadWithParentAddress(list, beanPath, addressWhat, true);
	}	

	private List partialLoadWithParentAddressQueryResult(Address addressWhat, Address positiveAddress, Address negativeAddress, QueryWhatInit queryWhatInit, Integer nbOfResult, Boolean useCache) {
		return partialLoadWithParentAddressJPAQueryResult (addressWhat, positiveAddress, negativeAddress, queryWhatInit, new Hashtable(), nbOfResult, useCache);
  }	
  
	private List partialLoadWithParentAddressJPAQueryResult(Address addressWhat, Address positiveAddress, Address negativeAddress, QueryWhatInit queryWhatInit, Map beanPath, Integer nbOfResult, Boolean useCache) {
		Query hquery = getPartialLoadWithParentAddressJPAQuery (addressWhat, positiveAddress, negativeAddress, beanPath, queryWhatInit, nbOfResult);
		return hquery.getResultList();
  }	
   /**
    * @returns an JPA Hsql query based on entity Address and its parents and the maximum number of result
    */
	protected Query getPartialLoadWithParentAddressJPAQuery (Address addressWhat, Address positiveAddress, Address negativeAddress, Map beanPath, QueryWhatInit queryWhatInit, Integer nbOfResult) {
	   Query query = getEntityManager().createQuery(getPartialLoadWithParentAddressHsqlQuery (addressWhat, positiveAddress, negativeAddress, beanPath, queryWhatInit));
	   if (nbOfResult!=null)
	      query.setMaxResults(nbOfResult);
	   return query;
  }
  
	private List<Address> handlePartialLoadWithParentAddress(List<Object[]> list, Map<Integer, String> beanPath, Address addressWhat, boolean isJql) {
		try {
			return convertPartialLoadWithParentAddress(list, beanPath, addressWhat);
		} catch (Exception ex) {
			log.error("Error conversion list from handlePartialLoadWithParentAddress, message:"+ex.getMessage());
			return new ArrayList<Address>();
		}
    }

	private List<Address> handlePartialLoadWithParentAddressWithOneElementInRow(List<Object> list, Map<Integer, String> beanPath, Address addressWhat, boolean isJql) {
		try {
			return convertPartialLoadWithParentAddressWithOneElementInRow(list, beanPath, addressWhat);
		} catch (Exception ex) {
			log.error("Error conversion list from handlePartialLoadWithParentAddressWithOneElementInRow, message:"+ex.getMessage());
			return new ArrayList<Address>();
		}
    }
    	
	 private List<Address> convertPartialLoadWithParentAddress(List<Object[]> list, Map<Integer, String> beanPath, Address addressWhat) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		 List<Address> resultList = new ArrayList<Address>();
		 for (Object[] row : list) {		
		    Address address = cloneAddress (addressWhat);
		    Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		    while (iter.hasNext()) {
		       Entry entry = iter.next();
		       populateAddress (address, row[(Integer)entry.getKey()], (String)entry.getValue());
		    }
		    resultList.add(address);
		 }
		 return resultList;		
	 }	
    
	 private List<Address> convertPartialLoadWithParentAddressWithOneElementInRow(List<Object> list, Map<Integer, String> beanPath, Address addressWhat) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		 List<Address> resultList = new ArrayList<Address>();
		 for (Object row : list) {		
		    Address address = cloneAddress (addressWhat);
		    Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		    while (iter.hasNext()) {
		       Entry entry = iter.next();
		       populateAddress (address, row, (String)entry.getValue());
		    }
		    resultList.add(address);
		 }		 
		 return resultList;		
	 }
   
	public List partialLoadWithParentForBean(Object bean, Address addressWhat, Address positiveAddress, Address negativeAddress) {
		Map beanPath = new Hashtable();
		Query hquery = getPartialLoadWithParentAddressJPAQuery (addressWhat, positiveAddress, negativeAddress, beanPath, new QuerySelectInit(), null);
        List<Object[]> list = hquery.getResultList();
		return handlePartialLoadWithParentForBean(list, beanPath, bean);
    }	
//	 to set in super class
	private List handlePartialLoadWithParentForBean(List<Object[]> list, Map<Integer, String> beanPath, Object bean) {
		try {
			return convertPartialLoadWithParentForBean(list, beanPath, bean);
		} catch (Exception ex) {
			//TODO log ex
			return new ArrayList();
		}
    }
	// to set in super class
	private List convertPartialLoadWithParentForBean(List<Object[]> list, Map<Integer, String> beanPath, Object bean) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		List resultList = new ArrayList();
		for (Object[] row : list) {		
		   Object result = getBeanObjectInstance(bean);
		   Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		   while (iter.hasNext()) {
			  Entry entry = iter.next();
			  populateObject (result, row[(Integer)entry.getKey()], getFieldFromBeanPath((String)entry.getValue()));
			}
			resultList.add(result);
		}
		return resultList;		
    }	
	

	
	// to set in super class
	private String getFieldFromBeanPath(String beanPath) {
	   String result = StringUtils.substringAfterLast(beanPath, ".");
	   if (result.equals(""))
		 return beanPath;
	   return result;
	}

   /**
	  * 
    * partial on entity and its parents load enables to specify the fields you want to load explicitly
    */
	public String getPartialLoadWithParentAddressHsqlQuery (Address address, Address positiveAddress, Address negativeAddress, Map beanPath, QueryWhatInit queryWhatInit) {
		Hashtable aliasWhatHt = new Hashtable();
		String what = getPartialLoadWithParentAddressQuery (address, null, aliasWhatHt, null, null, beanPath, "", queryWhatInit);
		Hashtable aliasWhereHt = new Hashtable();
		String where = getPartialLoadWithParentWhereAddressQuery (positiveAddress, null, aliasWhatHt, aliasWhereHt, null, null);
		String whereHow = reconciliateWherePath(aliasWhatHt, aliasWhereHt);
		String how = reconciliateHowPath(aliasWhatHt, aliasWhereHt);
		String whereConcat = "";
		if (where!=null && !where.equals("") && whereHow!=null && !whereHow.equals(""))
			whereConcat=" AND ";
		return what+" FROM "+how +" WHERE "+whereHow+ whereConcat +where;
	}
    
	/**
    * partial on a single entity load enables to specify the fields you want to load explicitly
    */         
	public List<Address> partialLoadAddress(Address address, Address positiveAddress, Address negativeAddress) {
	    Query hquery = getEntityManager().createQuery(getPartialLoadAddressQuery (address, positiveAddress, negativeAddress));
		int countPartialField = countPartialField(address);
		if (countPartialField==0) 
		   return new ArrayList<Address>();
		List list = hquery.getResultList();
		Iterator iter = list.iterator();
		List<Address> returnList = new ArrayList<Address>();
		while(iter.hasNext()) {
	       int index = 0;
	       Object[] row;
	       if (countPartialField==1) {
	    	  row = new Object[1];
	    	  row[0] = iter.next();
	       } else 
		      row = (Object[]) iter.next();
		   Address addressResult = new Address();
           if (address.getId() != null) {
			  addressResult.setId((Long) row[index]);
			  index++;
           }
           if (address.getStreet1() != null) {
			  addressResult.setStreet1((String) row[index]);
			  index++;
           }
           if (address.getStreet2() != null) {
			  addressResult.setStreet2((String) row[index]);
			  index++;
           }
           if (address.getCountryId() != null) {
			  addressResult.setCountryId_((Integer) row[index]);
			  index++;
           }
           returnList.add(addressResult);
        }
	    return returnList;
	}

	public static String getPartialLoadWithParentWhereAddressQuery (
	   Address address, Boolean isWhereSet, Hashtable aliasHt, Hashtable aliasWhereHt, String childAlias, String childFKAlias) {
	   if (address==null)
	      return "";
	   String alias = null;
	   if (aliasWhereHt == null) {
	      aliasWhereHt = new Hashtable();
	   } 
	   if (isLookedUp(address)){
	      alias = getNextAlias (aliasWhereHt, address);
		  aliasWhereHt.put (getAliasKey(alias), getAliasConnection(alias, childAlias, childFKAlias));
	   }
	   if (isWhereSet == null)
          isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (address.getId() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".id = "+ address.getId() + " ");
       }
       if (address.getStreet1() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".street1 = '"+ address.getStreet1()+"' ");
       }
       if (address.getStreet2() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".street2 = '"+ address.getStreet2()+"' ");
       }
       if (address.getCountryId() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".countryId = "+ address.getCountryId() + " ");
       }
       if (address.getCountryId()!=null) {
	      String chunck = net.sf.mp.demo.conference.dao.impl.jpa.admin.CountryExtendedJPAImpl.getPartialLoadWithParentWhereCountryQuery(
		      address.getCountryId(), 
			  isWhereSet, aliasHt, aliasWhereHt, alias, "countryId");
		  if (chunck!=null && !chunck.equals("")) {
		     query.append(chunck);
		     isWhereSet=true;
		  }  	  
	   }
	   return query.toString(); 
    }
	
	public static String reconciliateWherePath(Hashtable aliasWhatHt, Hashtable aliasWhereHt) {
	   StringBuffer sb = new StringBuffer();
	   boolean isBlankSet = false;
	   aliasWhatHt.putAll(aliasWhereHt);
	   Enumeration<String> elements = aliasWhatHt.elements();
	   while (elements.hasMoreElements()) {
		  String element = elements.nextElement();
		  if (!element.equals("")) {
		     sb.append (getQueryBLANK_AND (isBlankSet));
		     isBlankSet=true;
		     sb.append(element);
		  }
	   }
	   return sb.toString();
	}
	
	public static String reconciliateHowPath(Hashtable aliasWhatHt, Hashtable aliasWhereHt) {
	   StringBuffer sb = new StringBuffer();
	   boolean isBlankSet = false;
	   aliasWhatHt.putAll(aliasWhereHt);
	   Enumeration<String> keys = aliasWhatHt.keys();
	   while (keys.hasMoreElements()) {
		  String key = keys.nextElement();
		  sb.append (getQueryBLANK_COMMA (isBlankSet));
		  isBlankSet = true;
		  sb.append(getAliasKeyDomain(key)+" "+getAliasKeyAlias(key));
	   }
	   return sb.toString();
	}
	
	protected static String getRootDomainName (String domainName) {
		return StringUtils.substringBefore(domainName, "_");
	}
	
    public static String getPartialLoadWithParentAddressQuery (
	   Address address, Boolean isWhereSet, Hashtable aliasHt, String childAlias, String childFKAlias, Map beanPath, String rootPath, QueryWhatInit queryWhatInit) {
	   if (address==null)
	      return "";
	   String alias = null;
	   if (aliasHt == null) {
	      aliasHt = new Hashtable();
	   } 
	   if (isLookedUp(address)){
	      alias = getNextAlias (aliasHt, address);
		  aliasHt.put (getAliasKey(alias), getAliasConnection(alias, childAlias, childFKAlias));
	   }
	   if (isWhereSet == null)
          isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (address.getId() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"id");
          query.append(" "+alias+".id ");
       }
       if (address.getStreet1() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"street1");
          query.append(" "+alias+".street1 ");
       }
       if (address.getStreet2() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"street2");
          query.append(" "+alias+".street2 ");
       }
       if (address.getCountryId() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"countryId");
          query.append(" "+alias+".countryId ");
       }
       if (address.getCountryId()!=null) {
	      String chunck = net.sf.mp.demo.conference.dao.impl.jpa.admin.CountryExtendedJPAImpl.getPartialLoadWithParentCountryQuery(
		      address.getCountryId(), 
			  isWhereSet, aliasHt, alias, "countryId", beanPath, rootPath+"countryId.", queryWhatInit);
		  if (chunck!=null && !chunck.equals("")) {
		     query.append(chunck);
		     isWhereSet=true;
		  } 
	   }  
//       query.append(getAddressSearchEqualQuery (positiveAddress, negativeAddress));
	   return query.toString(); 
    }
	
	protected static String getAliasConnection(String existingAlias, String childAlias, String childFKAlias) {
		if (childAlias==null)
		   return "";
		return childAlias+"."+childFKAlias+" = "+existingAlias+"."+"id";
	}
	
	protected static String getAliasKey (String alias) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
		return "Address|"+alias;
	}
	
	protected static String getAliasKeyAlias (String aliasKey) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
		return StringUtils.substringAfter(aliasKey, "|");
	}
	
	protected static String getAliasKeyDomain (String aliasKey) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
	  return StringUtils.substringBefore(aliasKey, "|");
	}
	
	protected static String getNextAlias (Hashtable aliasHt, Address address) {
		int cptSameAlias = 0;
		Enumeration<String> keys = aliasHt.keys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			if (key.startsWith("address"))
				cptSameAlias++;
		}
		if (cptSameAlias==0)
			return "address";
		else
			return "address_"+cptSameAlias;
	}
	
	
	protected static boolean isLookedUp (Address address) {
	   if (address==null)
		  return false;
       if (address.getId() != null) {
	      return true;
       }
       if (address.getStreet1() != null) {
	      return true;
       }
       if (address.getStreet2() != null) {
	      return true;
       }
       if (address.getCountryId() != null) {
	      return true;
       }
       if (address.getCountryId()!=null) {
	      return true;
	   }  
       return false;   
	}
	
    public String getPartialLoadAddressQuery(
	   Address address, 
	   Address positiveAddress, 
	   Address negativeAddress) {
       boolean isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (address.getId() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" id ");
       }
       if (address.getStreet1() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" street1 ");
       }
       if (address.getStreet2() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" street2 ");
       }
       if (address.getCountryId() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" countryId ");
       }
       query.append(getAddressSearchEqualQuery (positiveAddress, negativeAddress));
	   return query.toString(); 
    }
	
	public List<Address> searchPrototypeWithCacheAddress(Address address) {
		SimpleCache simpleCache = new SimpleCache();
		List<Address> list = (List<Address>)simpleCache.get(address.toString());
		if (list==null) {
			list = searchPrototypeAddress(address);
			simpleCache.put(address.toString(), list);
		}
		return list;
	}

    public List<Address> loadGraph(Address graphMaskWhat, List<Address> whereMask) {
        return loadGraphOneLevel(graphMaskWhat, whereMask);
    }
 
    public List<Address> loadGraphOneLevel(Address graphMaskWhat, List<Address> whereMask) {
        //first get roots element from where list mask
        // this brings the level 0 of the graph (root level)
        graphMaskWhat.setId(graphMaskWhat.longMask__);
        List<Address> addresss = searchPrototypeAddress (whereMask);
        // for each sub level perform the search with a subquery then reassemble
        // 1. get all sublevel queries
        // 2. perform queries on the correct dao
        // 3. reassemble
        return getLoadGraphOneLevel (graphMaskWhat, addresss);
    }

	private List<Address> copy(List<Address> inputs) {
		List<Address> l = new ArrayList<Address>();
		for (Address input : inputs) {
			Address copy = new Address();
			copy.copy(input);
			l.add(copy);
		}
		return l;
	}
	   
	private List<Address> getLoadGraphOneLevel (Address graphMaskWhat, List<Address> parents) {
	    return loadGraphFromParentKey (graphMaskWhat, parents);
	} 
	
	public List<Address> loadGraphFromParentKey (Address graphMaskWhat, List<Address> parents) {
		//foreach children:
		//check if not empty take first
		parents = copy (parents); //working with detached entities to avoid unnecessary sql calls
		if (parents==null || parents.isEmpty())
		   return parents;
		List<String> ids = getPk (parents);
		if (graphMaskWhat.getConferenceAddressViaAddressId()!=null && !graphMaskWhat.getConferenceAddressViaAddressId().isEmpty()) {
			for (Conference childWhat : graphMaskWhat.getConferenceAddressViaAddressId()) {
				childWhat.setAddressId_(graphMaskWhat.longMask__); // add to the what mask, usefull for reconciliation
				ConferenceExtendedJPAImpl conferenceextendedjpaimpl = new ConferenceExtendedJPAImpl ();
				List<Conference> children = conferenceextendedjpaimpl.lookupConference(childWhat, getFkCriteria(" id ", ids), null, getEntityManager());
				reassembleConference (children, parents);				
				break;
			}
		}
		if (graphMaskWhat.getConferenceMemberAddressViaAddressId()!=null && !graphMaskWhat.getConferenceMemberAddressViaAddressId().isEmpty()) {
			for (ConferenceMember childWhat : graphMaskWhat.getConferenceMemberAddressViaAddressId()) {
				childWhat.setAddressId_(graphMaskWhat.longMask__); // add to the what mask, usefull for reconciliation
				ConferenceMemberExtendedJPAImpl conferencememberextendedjpaimpl = new ConferenceMemberExtendedJPAImpl ();
				List<ConferenceMember> children = conferencememberextendedjpaimpl.lookupConferenceMember(childWhat, getFkCriteria(" id ", ids), null, getEntityManager());
				reassembleConferenceMember (children, parents);				
				break;
			}
		}
		if (graphMaskWhat.getSponsorAddressViaAddressId()!=null && !graphMaskWhat.getSponsorAddressViaAddressId().isEmpty()) {
			for (Sponsor childWhat : graphMaskWhat.getSponsorAddressViaAddressId()) {
				childWhat.setAddressId_(graphMaskWhat.longMask__); // add to the what mask, usefull for reconciliation
				SponsorExtendedJPAImpl sponsorextendedjpaimpl = new SponsorExtendedJPAImpl ();
				List<Sponsor> children = sponsorextendedjpaimpl.lookupSponsor(childWhat, getFkCriteria(" id ", ids), null, getEntityManager());
				reassembleSponsor (children, parents);				
				break;
			}
		}
		return parents;
	}
	
	private void reassembleConference (List<Conference> children, List<Address> parents) {
		for (Conference child : children) {
			for (Address parent : parents) {
				if (parent.getId()!=null && parent.getId().toString().equals(child.getAddressId()+"")) {
					parent.addConferenceAddressViaAddressId(child); //ConferenceAddressViaAddressId
					child.setAddressId(parent);
					break;
				}
			}
		}
	}
	
	private void reassembleConferenceMember (List<ConferenceMember> children, List<Address> parents) {
		for (ConferenceMember child : children) {
			for (Address parent : parents) {
				if (parent.getId()!=null && parent.getId().toString().equals(child.getAddressId()+"")) {
					parent.addConferenceMemberAddressViaAddressId(child); //ConferenceMemberAddressViaAddressId
					child.setAddressId(parent);
					break;
				}
			}
		}
	}
	
	private void reassembleSponsor (List<Sponsor> children, List<Address> parents) {
		for (Sponsor child : children) {
			for (Address parent : parents) {
				if (parent.getId()!=null && parent.getId().toString().equals(child.getAddressId()+"")) {
					parent.addSponsorAddressViaAddressId(child); //SponsorAddressViaAddressId
					child.setAddressId(parent);
					break;
				}
			}
		}
	}
	
	private List<String> getPk(List<Address> input) {
		List<String> s = new ArrayList<String>();
		for (Address t : input) {
			s.add(t.getId()+"");
		}
		return s;
	}

	private Criteria getFkCriteria(String field, List<String> ids) {
		Criteria criteria = new Criteria();
		criteria.addCriterion(getInPk(field, ids));
		return criteria;
	}

	private ClauseCriterion getInPk(String field, List<String> ids) {
		InCriterion inCriterion = new InCriterion(field, ids, true);
		return inCriterion;
	}	    
    
    
    public EntityManager getEntityManagerForRecursiveDao() {
		return emForRecursiveDao;
	}

	public void setEntityManagerForRecursiveDao(EntityManager emForRecursiveDao) {
		this.emForRecursiveDao = emForRecursiveDao;
	}
	
    public void setConferenceExtendedJPAImpl (ConferenceExtendedJPAImpl conferenceextendedjpaimpl) {
       this.conferenceextendedjpaimpl = conferenceextendedjpaimpl;
    }
    
    public ConferenceExtendedJPAImpl getConferenceExtendedJPAImpl () {
       return conferenceextendedjpaimpl;
    }
    
    public void setConferenceMemberExtendedJPAImpl (ConferenceMemberExtendedJPAImpl conferencememberextendedjpaimpl) {
       this.conferencememberextendedjpaimpl = conferencememberextendedjpaimpl;
    }
    
    public ConferenceMemberExtendedJPAImpl getConferenceMemberExtendedJPAImpl () {
       return conferencememberextendedjpaimpl;
    }
    
    public void setSponsorExtendedJPAImpl (SponsorExtendedJPAImpl sponsorextendedjpaimpl) {
       this.sponsorextendedjpaimpl = sponsorextendedjpaimpl;
    }
    
    public SponsorExtendedJPAImpl getSponsorExtendedJPAImpl () {
       return sponsorextendedjpaimpl;
    }
    
    
}
