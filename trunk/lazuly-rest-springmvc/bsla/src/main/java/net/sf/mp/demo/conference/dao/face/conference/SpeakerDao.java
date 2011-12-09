package net.sf.mp.demo.conference.dao.face.conference;

import net.sf.mp.demo.conference.domain.conference.Speaker;
import java.util.List;
import net.sf.minuteProject.architecture.bsla.bean.criteria.PaginationCriteria;
import net.sf.minuteProject.architecture.bsla.dao.face.DataAccessObject;


/**
 *
 * <p>Title: SpeakerDao</p>
 *
 * <p>Description: Interface of a Data access object dealing with SpeakerDao
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching speaker objects</p>
 *
 */
public interface SpeakerDao extends DataAccessObject {

    /**
     * Inserts a Speaker entity 
     * @param Speaker speaker
     */
    public void insertSpeaker(Speaker speaker) ;
 
    /**
     * Inserts a list of Speaker entity 
     * @param List<Speaker> speakers
     */
    public void insertSpeakers(List<Speaker> speakers) ;
        
    /**
     * Updates a Speaker entity 
     * @param Speaker speaker
     */
    public Speaker updateSpeaker(Speaker speaker) ;

	 /**
     * Updates a Speaker entity with only the attributes set into Speaker.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is to be done
   * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
   * @param Speaker speaker
   */
    public int updateNotNullOnlySpeaker(Speaker speaker) ;
	 
	public int updateNotNullOnlyPrototypeSpeaker(Speaker speaker, Speaker prototypeCriteria);
	
     /**
     * Saves a Speaker entity 
     * @param Speaker speaker
     */
    public void saveSpeaker(Speaker speaker);
    
    /**
     * Deletes a Speaker entity 
     * @param Speaker speaker
     */
    public void deleteSpeaker(Speaker speaker) ;
 
    /**
     * Loads the Speaker entity which is related to an instance of
     * Speaker
     * @param Long id
     * @return Speaker The Speaker entity
     
    public Speaker loadSpeaker(Long id);
*/
    /**
     * Loads the Speaker entity which is related to an instance of
     * Speaker
     * @param java.lang.Long Id
     * @return Speaker The Speaker entity
     */
    public Speaker loadSpeaker(java.lang.Long id);    

    /**
     * Loads a list of Speaker entity 
     * @param List<java.lang.Long> ids
     * @return List<Speaker> The Speaker entity
     */
    public List<Speaker> loadSpeakerListBySpeaker (List<Speaker> speakers);
    
    /**
     * Loads a list of Speaker entity 
     * @param List<java.lang.Long> ids
     * @return List<Speaker> The Speaker entity
     */
    public List<Speaker> loadSpeakerListById(List<java.lang.Long> ids);
    
    /**
     * Loads the Speaker entity which is related to an instance of
     * Speaker and its dependent one to many objects
     * @param Long id
     * @return Speaker The Speaker entity
     */
    public Speaker loadFullFirstLevelSpeaker(java.lang.Long id);
    
    /**
     * Loads the Speaker entity which is related to an instance of
     * Speaker
     * @param Speaker speaker
     * @return Speaker The Speaker entity
     */
    public Speaker loadFullFirstLevelSpeaker(Speaker speaker);    
    
    
    /**
     * Loads the Speaker entity which is related to an instance of
     * Speaker and its dependent objects one to many
     * @param Long id
     * @return Speaker The Speaker entity
     */
    public Speaker loadFullSpeaker(Long id) ;

    /**
     * Searches a list of Speaker entity based on a Speaker containing Speaker matching criteria
     * @param Speaker speaker
     * @return List<Speaker>
     */
    public List<Speaker> searchPrototypeSpeaker(Speaker speaker) ;
    
    /**
     * Searches a list of Speaker entity based on a list of Speaker containing Speaker matching criteria
     * @param List<Speaker> speakers
     * @return List<Speaker>
     */
    public List<Speaker> searchPrototypeSpeaker(List<Speaker> speakers) ;    
    	
	/**
     * Searches a list of Speaker entity 
     * @param Speaker speaker
     * @return List
     */
    public List<Speaker> searchPrototypeSpeaker(Speaker speakerPositive, Speaker speakerNegative) ;
        
    /**
     * Load a paginated list of Speaker entity dependent of pagination criteria
     * @param PaginationCriteria paginationCriteria
     * @return List
     */
    public List<Speaker> loadPaginatedSpeaker (Speaker speaker, PaginationCriteria paginationCriteria) ;
            
}
