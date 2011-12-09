package net.sf.mp.demo.conference.dao.face.admin;

import net.sf.mp.demo.conference.domain.admin.Country;
import java.util.List;
import net.sf.minuteProject.architecture.filter.data.Criteria;
import net.sf.minuteProject.architecture.bsla.dao.face.DataAccessObject;

/**
 *
 * <p>Title: CountryExtDao</p>
 *
 * <p>Description: Interface of a Data access object dealing with CountryExtDao
 * persistence. It offers extended DAO functionalities</p>
 *
 */
public interface CountryExtDao extends DataAccessObject {
     
     /**
     * Inserts a Country entity with cascade of its children
     * @param Country country
     */
    public void insertCountryWithCascade(Country country) ;
    
    /**
     * Inserts a list of Country entity with cascade of its children
     * @param List<Country> countrys
     */
    public void insertCountrysWithCascade(List<Country> countrys) ;        
   
    /**
     * lookup Country entity Country, criteria and max result number
     */
    public List<Country> lookupCountry(Country country, Criteria criteria, Integer numberOfResult);
	
	public Integer updateNotNullOnlyCountry (Country country, Criteria criteria);

	/**
	 * Affect the first country retrieved corresponding to the country criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 */
    public Country affectCountry (Country country);
    
    public Country affectCountryUseCache (Country country);
    	
	/**
	 * Assign the first country retrieved corresponding to the country criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no country corresponding in the database. Then country is inserted and returned with its primary key(s). 
	 */
    public Country assignCountry (Country country);
    
    public Country assignCountryUseCache (Country country);
         
    /**
    * return the first Country entity found
    */           
    public Country getFirstCountry (Country country);
    
    /**
    * checks if the Country entity exists
    */           
    public boolean existsCountry (Country country);    
    
    public boolean existsCountryWhereConditionsAre (Country country);

    /**
    * partial load enables to specify the fields you want to load explicitly
    */            
    public List<Country> partialLoadCountry(Country country, Country positiveCountry, Country negativeCountry);

    /**
    * partial load with parent entities
    * variation (list, first, distinct decorator)
    * variation2 (with cache)
    */
    public List<Country> partialLoadWithParentCountry(Country country, Country positiveCountry, Country negativeCountry);

    public List<Country> partialLoadWithParentCountryUseCache(Country country, Country positiveCountry, Country negativeCountry, Boolean useCache);

    public List<Country> partialLoadWithParentCountryUseCacheOnResult(Country country, Country positiveCountry, Country negativeCountry, Boolean useCache);

    /**
    * variation first
    */
    public Country partialLoadWithParentFirstCountry(Country countryWhat, Country positiveCountry, Country negativeCountry);
    
    public Country partialLoadWithParentFirstCountryUseCache(Country countryWhat, Country positiveCountry, Country negativeCountry, Boolean useCache);

    public Country partialLoadWithParentFirstCountryUseCacheOnResult(Country countryWhat, Country positiveCountry, Country negativeCountry, Boolean useCache);

    /**
    * variation distinct
    */
    public List<Country> getDistinctCountry(Country countryWhat, Country positiveCountry, Country negativeCountry);

    //
    public List partialLoadWithParentForBean(Object bean, Country country, Country positiveCountry, Country negativeCountry);

    /**
    * search on prototype with cache
    */
    public List<Country> searchPrototypeWithCacheCountry (Country country);
      
    
    /**
     * Searches a list of distinct Country entity based on a Country mask and a list of Country containing Country matching criteria
     * @param Country country
     * @param List<Country> countrys
     * @return List<Country>
     */
    public List<Country> searchDistinctPrototypeCountry(Country countryMask, List<Country> countrys) ;    

	public List<Country> countDistinct (Country whatMask, Country whereEqCriteria);
	
	public Long count (Country whereEqCriteria);
	
	public List<Country> loadGraph(Country graphMaskWhat, List<Country> whereMask);  
	
	public List<Country> loadGraphFromParentKey (Country graphMaskWhat, List<Country> parents); 
	
    /**
     * generic to move after in superclass
     */
    public List<Object[]> getSQLQueryResult(String query);     

	
}

