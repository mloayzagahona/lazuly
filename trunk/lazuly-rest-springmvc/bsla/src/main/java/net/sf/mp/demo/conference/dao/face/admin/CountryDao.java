package net.sf.mp.demo.conference.dao.face.admin;

import net.sf.mp.demo.conference.domain.admin.Country;
import java.util.List;
import net.sf.minuteProject.architecture.bsla.bean.criteria.PaginationCriteria;
import net.sf.minuteProject.architecture.bsla.dao.face.DataAccessObject;


/**
 *
 * <p>Title: CountryDao</p>
 *
 * <p>Description: Interface of a Data access object dealing with CountryDao
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching country objects</p>
 *
 */
public interface CountryDao extends DataAccessObject {

    /**
     * Inserts a Country entity 
     * @param Country country
     */
    public void insertCountry(Country country) ;
 
    /**
     * Inserts a list of Country entity 
     * @param List<Country> countrys
     */
    public void insertCountrys(List<Country> countrys) ;
        
    /**
     * Updates a Country entity 
     * @param Country country
     */
    public Country updateCountry(Country country) ;

	 /**
     * Updates a Country entity with only the attributes set into Country.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is to be done
   * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
   * @param Country country
   */
    public int updateNotNullOnlyCountry(Country country) ;
	 
	public int updateNotNullOnlyPrototypeCountry(Country country, Country prototypeCriteria);
	
     /**
     * Saves a Country entity 
     * @param Country country
     */
    public void saveCountry(Country country);
    
    /**
     * Deletes a Country entity 
     * @param Country country
     */
    public void deleteCountry(Country country) ;
 
    /**
     * Loads the Country entity which is related to an instance of
     * Country
     * @param Long id
     * @return Country The Country entity
     
    public Country loadCountry(Long id);
*/
    /**
     * Loads the Country entity which is related to an instance of
     * Country
     * @param java.lang.Integer Id
     * @return Country The Country entity
     */
    public Country loadCountry(java.lang.Integer id);    

    /**
     * Loads a list of Country entity 
     * @param List<java.lang.Integer> ids
     * @return List<Country> The Country entity
     */
    public List<Country> loadCountryListByCountry (List<Country> countrys);
    
    /**
     * Loads a list of Country entity 
     * @param List<java.lang.Integer> ids
     * @return List<Country> The Country entity
     */
    public List<Country> loadCountryListById(List<java.lang.Integer> ids);
    
    /**
     * Loads the Country entity which is related to an instance of
     * Country and its dependent one to many objects
     * @param Long id
     * @return Country The Country entity
     */
    public Country loadFullFirstLevelCountry(java.lang.Integer id);
    
    /**
     * Loads the Country entity which is related to an instance of
     * Country
     * @param Country country
     * @return Country The Country entity
     */
    public Country loadFullFirstLevelCountry(Country country);    
    
    
    /**
     * Loads the Country entity which is related to an instance of
     * Country and its dependent objects one to many
     * @param Long id
     * @return Country The Country entity
     */
    public Country loadFullCountry(Long id) ;

    /**
     * Searches a list of Country entity based on a Country containing Country matching criteria
     * @param Country country
     * @return List<Country>
     */
    public List<Country> searchPrototypeCountry(Country country) ;
    
    /**
     * Searches a list of Country entity based on a list of Country containing Country matching criteria
     * @param List<Country> countrys
     * @return List<Country>
     */
    public List<Country> searchPrototypeCountry(List<Country> countrys) ;    
    	
	/**
     * Searches a list of Country entity 
     * @param Country country
     * @return List
     */
    public List<Country> searchPrototypeCountry(Country countryPositive, Country countryNegative) ;
        
    /**
     * Load a paginated list of Country entity dependent of pagination criteria
     * @param PaginationCriteria paginationCriteria
     * @return List
     */
    public List<Country> loadPaginatedCountry (Country country, PaginationCriteria paginationCriteria) ;
            
}
