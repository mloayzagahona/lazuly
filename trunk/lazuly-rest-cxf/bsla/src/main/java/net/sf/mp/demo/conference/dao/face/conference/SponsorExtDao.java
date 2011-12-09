package net.sf.mp.demo.conference.dao.face.conference;

import net.sf.mp.demo.conference.domain.conference.Sponsor;
import java.util.List;
import net.sf.minuteProject.architecture.filter.data.Criteria;
import net.sf.minuteProject.architecture.bsla.dao.face.DataAccessObject;

/**
 *
 * <p>Title: SponsorExtDao</p>
 *
 * <p>Description: Interface of a Data access object dealing with SponsorExtDao
 * persistence. It offers extended DAO functionalities</p>
 *
 */
public interface SponsorExtDao extends DataAccessObject {
     
     /**
     * Inserts a Sponsor entity with cascade of its children
     * @param Sponsor sponsor
     */
    public void insertSponsorWithCascade(Sponsor sponsor) ;
    
    /**
     * Inserts a list of Sponsor entity with cascade of its children
     * @param List<Sponsor> sponsors
     */
    public void insertSponsorsWithCascade(List<Sponsor> sponsors) ;        
   
    /**
     * lookup Sponsor entity Sponsor, criteria and max result number
     */
    public List<Sponsor> lookupSponsor(Sponsor sponsor, Criteria criteria, Integer numberOfResult);
	
	public Integer updateNotNullOnlySponsor (Sponsor sponsor, Criteria criteria);

	/**
	 * Affect the first sponsor retrieved corresponding to the sponsor criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 */
    public Sponsor affectSponsor (Sponsor sponsor);
    
    public Sponsor affectSponsorUseCache (Sponsor sponsor);
    	
	/**
	 * Assign the first sponsor retrieved corresponding to the sponsor criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no sponsor corresponding in the database. Then sponsor is inserted and returned with its primary key(s). 
	 */
    public Sponsor assignSponsor (Sponsor sponsor);
    
    public Sponsor assignSponsorUseCache (Sponsor sponsor);
         
    /**
    * return the first Sponsor entity found
    */           
    public Sponsor getFirstSponsor (Sponsor sponsor);
    
    /**
    * checks if the Sponsor entity exists
    */           
    public boolean existsSponsor (Sponsor sponsor);    
    
    public boolean existsSponsorWhereConditionsAre (Sponsor sponsor);

    /**
    * partial load enables to specify the fields you want to load explicitly
    */            
    public List<Sponsor> partialLoadSponsor(Sponsor sponsor, Sponsor positiveSponsor, Sponsor negativeSponsor);

    /**
    * partial load with parent entities
    * variation (list, first, distinct decorator)
    * variation2 (with cache)
    */
    public List<Sponsor> partialLoadWithParentSponsor(Sponsor sponsor, Sponsor positiveSponsor, Sponsor negativeSponsor);

    public List<Sponsor> partialLoadWithParentSponsorUseCache(Sponsor sponsor, Sponsor positiveSponsor, Sponsor negativeSponsor, Boolean useCache);

    public List<Sponsor> partialLoadWithParentSponsorUseCacheOnResult(Sponsor sponsor, Sponsor positiveSponsor, Sponsor negativeSponsor, Boolean useCache);

    /**
    * variation first
    */
    public Sponsor partialLoadWithParentFirstSponsor(Sponsor sponsorWhat, Sponsor positiveSponsor, Sponsor negativeSponsor);
    
    public Sponsor partialLoadWithParentFirstSponsorUseCache(Sponsor sponsorWhat, Sponsor positiveSponsor, Sponsor negativeSponsor, Boolean useCache);

    public Sponsor partialLoadWithParentFirstSponsorUseCacheOnResult(Sponsor sponsorWhat, Sponsor positiveSponsor, Sponsor negativeSponsor, Boolean useCache);

    /**
    * variation distinct
    */
    public List<Sponsor> getDistinctSponsor(Sponsor sponsorWhat, Sponsor positiveSponsor, Sponsor negativeSponsor);

    //
    public List partialLoadWithParentForBean(Object bean, Sponsor sponsor, Sponsor positiveSponsor, Sponsor negativeSponsor);

    /**
    * search on prototype with cache
    */
    public List<Sponsor> searchPrototypeWithCacheSponsor (Sponsor sponsor);
      
    
    /**
     * Searches a list of distinct Sponsor entity based on a Sponsor mask and a list of Sponsor containing Sponsor matching criteria
     * @param Sponsor sponsor
     * @param List<Sponsor> sponsors
     * @return List<Sponsor>
     */
    public List<Sponsor> searchDistinctPrototypeSponsor(Sponsor sponsorMask, List<Sponsor> sponsors) ;    

	public List<Sponsor> countDistinct (Sponsor whatMask, Sponsor whereEqCriteria);
	
	public Long count (Sponsor whereEqCriteria);
	
	public List<Sponsor> loadGraph(Sponsor graphMaskWhat, List<Sponsor> whereMask);  
	
	public List<Sponsor> loadGraphFromParentKey (Sponsor graphMaskWhat, List<Sponsor> parents); 
	
    /**
     * generic to move after in superclass
     */
    public List<Object[]> getSQLQueryResult(String query);     

	
}

