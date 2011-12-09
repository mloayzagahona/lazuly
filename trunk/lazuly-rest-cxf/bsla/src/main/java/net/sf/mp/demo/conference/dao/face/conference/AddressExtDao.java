package net.sf.mp.demo.conference.dao.face.conference;

import net.sf.mp.demo.conference.domain.conference.Address;
import java.util.List;
import net.sf.minuteProject.architecture.filter.data.Criteria;
import net.sf.minuteProject.architecture.bsla.dao.face.DataAccessObject;

/**
 *
 * <p>Title: AddressExtDao</p>
 *
 * <p>Description: Interface of a Data access object dealing with AddressExtDao
 * persistence. It offers extended DAO functionalities</p>
 *
 */
public interface AddressExtDao extends DataAccessObject {
     
     /**
     * Inserts a Address entity with cascade of its children
     * @param Address address
     */
    public void insertAddressWithCascade(Address address) ;
    
    /**
     * Inserts a list of Address entity with cascade of its children
     * @param List<Address> addresss
     */
    public void insertAddresssWithCascade(List<Address> addresss) ;        
   
    /**
     * lookup Address entity Address, criteria and max result number
     */
    public List<Address> lookupAddress(Address address, Criteria criteria, Integer numberOfResult);
	
	public Integer updateNotNullOnlyAddress (Address address, Criteria criteria);

	/**
	 * Affect the first address retrieved corresponding to the address criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 */
    public Address affectAddress (Address address);
    
    public Address affectAddressUseCache (Address address);
    	
	/**
	 * Assign the first address retrieved corresponding to the address criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no address corresponding in the database. Then address is inserted and returned with its primary key(s). 
	 */
    public Address assignAddress (Address address);
    
    public Address assignAddressUseCache (Address address);
         
    /**
    * return the first Address entity found
    */           
    public Address getFirstAddress (Address address);
    
    /**
    * checks if the Address entity exists
    */           
    public boolean existsAddress (Address address);    
    
    public boolean existsAddressWhereConditionsAre (Address address);

    /**
    * partial load enables to specify the fields you want to load explicitly
    */            
    public List<Address> partialLoadAddress(Address address, Address positiveAddress, Address negativeAddress);

    /**
    * partial load with parent entities
    * variation (list, first, distinct decorator)
    * variation2 (with cache)
    */
    public List<Address> partialLoadWithParentAddress(Address address, Address positiveAddress, Address negativeAddress);

    public List<Address> partialLoadWithParentAddressUseCache(Address address, Address positiveAddress, Address negativeAddress, Boolean useCache);

    public List<Address> partialLoadWithParentAddressUseCacheOnResult(Address address, Address positiveAddress, Address negativeAddress, Boolean useCache);

    /**
    * variation first
    */
    public Address partialLoadWithParentFirstAddress(Address addressWhat, Address positiveAddress, Address negativeAddress);
    
    public Address partialLoadWithParentFirstAddressUseCache(Address addressWhat, Address positiveAddress, Address negativeAddress, Boolean useCache);

    public Address partialLoadWithParentFirstAddressUseCacheOnResult(Address addressWhat, Address positiveAddress, Address negativeAddress, Boolean useCache);

    /**
    * variation distinct
    */
    public List<Address> getDistinctAddress(Address addressWhat, Address positiveAddress, Address negativeAddress);

    //
    public List partialLoadWithParentForBean(Object bean, Address address, Address positiveAddress, Address negativeAddress);

    /**
    * search on prototype with cache
    */
    public List<Address> searchPrototypeWithCacheAddress (Address address);
      
    
    /**
     * Searches a list of distinct Address entity based on a Address mask and a list of Address containing Address matching criteria
     * @param Address address
     * @param List<Address> addresss
     * @return List<Address>
     */
    public List<Address> searchDistinctPrototypeAddress(Address addressMask, List<Address> addresss) ;    

	public List<Address> countDistinct (Address whatMask, Address whereEqCriteria);
	
	public Long count (Address whereEqCriteria);
	
	public List<Address> loadGraph(Address graphMaskWhat, List<Address> whereMask);  
	
	public List<Address> loadGraphFromParentKey (Address graphMaskWhat, List<Address> parents); 
	
    /**
     * generic to move after in superclass
     */
    public List<Object[]> getSQLQueryResult(String query);     

	
}

