package net.sf.mp.demo.conference.dao.face.conference;

import net.sf.mp.demo.conference.domain.conference.Sponsor;
import java.util.List;
import net.sf.minuteProject.architecture.bsla.bean.criteria.PaginationCriteria;
import net.sf.minuteProject.architecture.bsla.dao.face.DataAccessObject;


/**
 *
 * <p>Title: SponsorDao</p>
 *
 * <p>Description: Interface of a Data access object dealing with SponsorDao
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching sponsor objects</p>
 *
 */
public interface SponsorDao extends DataAccessObject {

    /**
     * Inserts a Sponsor entity 
     * @param Sponsor sponsor
     */
    public void insertSponsor(Sponsor sponsor) ;
 
    /**
     * Inserts a list of Sponsor entity 
     * @param List<Sponsor> sponsors
     */
    public void insertSponsors(List<Sponsor> sponsors) ;
        
    /**
     * Updates a Sponsor entity 
     * @param Sponsor sponsor
     */
    public Sponsor updateSponsor(Sponsor sponsor) ;

	 /**
     * Updates a Sponsor entity with only the attributes set into Sponsor.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is to be done
   * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
   * @param Sponsor sponsor
   */
    public int updateNotNullOnlySponsor(Sponsor sponsor) ;
	 
	public int updateNotNullOnlyPrototypeSponsor(Sponsor sponsor, Sponsor prototypeCriteria);
	
     /**
     * Saves a Sponsor entity 
     * @param Sponsor sponsor
     */
    public void saveSponsor(Sponsor sponsor);
    
    /**
     * Deletes a Sponsor entity 
     * @param Sponsor sponsor
     */
    public void deleteSponsor(Sponsor sponsor) ;
 
    /**
     * Loads the Sponsor entity which is related to an instance of
     * Sponsor
     * @param Long id
     * @return Sponsor The Sponsor entity
     
    public Sponsor loadSponsor(Long id);
*/
    /**
     * Loads the Sponsor entity which is related to an instance of
     * Sponsor
     * @param java.lang.Long Id
     * @return Sponsor The Sponsor entity
     */
    public Sponsor loadSponsor(java.lang.Long id);    

    /**
     * Loads a list of Sponsor entity 
     * @param List<java.lang.Long> ids
     * @return List<Sponsor> The Sponsor entity
     */
    public List<Sponsor> loadSponsorListBySponsor (List<Sponsor> sponsors);
    
    /**
     * Loads a list of Sponsor entity 
     * @param List<java.lang.Long> ids
     * @return List<Sponsor> The Sponsor entity
     */
    public List<Sponsor> loadSponsorListById(List<java.lang.Long> ids);
    
    /**
     * Loads the Sponsor entity which is related to an instance of
     * Sponsor and its dependent one to many objects
     * @param Long id
     * @return Sponsor The Sponsor entity
     */
    public Sponsor loadFullFirstLevelSponsor(java.lang.Long id);
    
    /**
     * Loads the Sponsor entity which is related to an instance of
     * Sponsor
     * @param Sponsor sponsor
     * @return Sponsor The Sponsor entity
     */
    public Sponsor loadFullFirstLevelSponsor(Sponsor sponsor);    
    
    
    /**
     * Loads the Sponsor entity which is related to an instance of
     * Sponsor and its dependent objects one to many
     * @param Long id
     * @return Sponsor The Sponsor entity
     */
    public Sponsor loadFullSponsor(Long id) ;

    /**
     * Searches a list of Sponsor entity based on a Sponsor containing Sponsor matching criteria
     * @param Sponsor sponsor
     * @return List<Sponsor>
     */
    public List<Sponsor> searchPrototypeSponsor(Sponsor sponsor) ;
    
    /**
     * Searches a list of Sponsor entity based on a list of Sponsor containing Sponsor matching criteria
     * @param List<Sponsor> sponsors
     * @return List<Sponsor>
     */
    public List<Sponsor> searchPrototypeSponsor(List<Sponsor> sponsors) ;    
    	
	/**
     * Searches a list of Sponsor entity 
     * @param Sponsor sponsor
     * @return List
     */
    public List<Sponsor> searchPrototypeSponsor(Sponsor sponsorPositive, Sponsor sponsorNegative) ;
        
    /**
     * Load a paginated list of Sponsor entity dependent of pagination criteria
     * @param PaginationCriteria paginationCriteria
     * @return List
     */
    public List<Sponsor> loadPaginatedSponsor (Sponsor sponsor, PaginationCriteria paginationCriteria) ;
            
}
