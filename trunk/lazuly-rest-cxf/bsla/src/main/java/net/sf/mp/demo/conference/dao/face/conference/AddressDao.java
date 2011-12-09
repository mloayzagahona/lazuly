package net.sf.mp.demo.conference.dao.face.conference;

import net.sf.mp.demo.conference.domain.conference.Address;
import java.util.List;
import net.sf.minuteProject.architecture.bsla.bean.criteria.PaginationCriteria;
import net.sf.minuteProject.architecture.bsla.dao.face.DataAccessObject;


/**
 *
 * <p>Title: AddressDao</p>
 *
 * <p>Description: Interface of a Data access object dealing with AddressDao
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching address objects</p>
 *
 */
public interface AddressDao extends DataAccessObject {

    /**
     * Inserts a Address entity 
     * @param Address address
     */
    public void insertAddress(Address address) ;
 
    /**
     * Inserts a list of Address entity 
     * @param List<Address> addresss
     */
    public void insertAddresss(List<Address> addresss) ;
        
    /**
     * Updates a Address entity 
     * @param Address address
     */
    public Address updateAddress(Address address) ;

	 /**
     * Updates a Address entity with only the attributes set into Address.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is to be done
   * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
   * @param Address address
   */
    public int updateNotNullOnlyAddress(Address address) ;
	 
	public int updateNotNullOnlyPrototypeAddress(Address address, Address prototypeCriteria);
	
     /**
     * Saves a Address entity 
     * @param Address address
     */
    public void saveAddress(Address address);
    
    /**
     * Deletes a Address entity 
     * @param Address address
     */
    public void deleteAddress(Address address) ;
 
    /**
     * Loads the Address entity which is related to an instance of
     * Address
     * @param Long id
     * @return Address The Address entity
     
    public Address loadAddress(Long id);
*/
    /**
     * Loads the Address entity which is related to an instance of
     * Address
     * @param java.lang.Long Id
     * @return Address The Address entity
     */
    public Address loadAddress(java.lang.Long id);    

    /**
     * Loads a list of Address entity 
     * @param List<java.lang.Long> ids
     * @return List<Address> The Address entity
     */
    public List<Address> loadAddressListByAddress (List<Address> addresss);
    
    /**
     * Loads a list of Address entity 
     * @param List<java.lang.Long> ids
     * @return List<Address> The Address entity
     */
    public List<Address> loadAddressListById(List<java.lang.Long> ids);
    
    /**
     * Loads the Address entity which is related to an instance of
     * Address and its dependent one to many objects
     * @param Long id
     * @return Address The Address entity
     */
    public Address loadFullFirstLevelAddress(java.lang.Long id);
    
    /**
     * Loads the Address entity which is related to an instance of
     * Address
     * @param Address address
     * @return Address The Address entity
     */
    public Address loadFullFirstLevelAddress(Address address);    
    
    
    /**
     * Loads the Address entity which is related to an instance of
     * Address and its dependent objects one to many
     * @param Long id
     * @return Address The Address entity
     */
    public Address loadFullAddress(Long id) ;

    /**
     * Searches a list of Address entity based on a Address containing Address matching criteria
     * @param Address address
     * @return List<Address>
     */
    public List<Address> searchPrototypeAddress(Address address) ;
    
    /**
     * Searches a list of Address entity based on a list of Address containing Address matching criteria
     * @param List<Address> addresss
     * @return List<Address>
     */
    public List<Address> searchPrototypeAddress(List<Address> addresss) ;    
    	
	/**
     * Searches a list of Address entity 
     * @param Address address
     * @return List
     */
    public List<Address> searchPrototypeAddress(Address addressPositive, Address addressNegative) ;
        
    /**
     * Load a paginated list of Address entity dependent of pagination criteria
     * @param PaginationCriteria paginationCriteria
     * @return List
     */
    public List<Address> loadPaginatedAddress (Address address, PaginationCriteria paginationCriteria) ;
            
}
