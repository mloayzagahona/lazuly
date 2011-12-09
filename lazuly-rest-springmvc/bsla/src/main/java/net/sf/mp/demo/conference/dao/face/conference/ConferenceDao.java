package net.sf.mp.demo.conference.dao.face.conference;

import net.sf.mp.demo.conference.domain.conference.Conference;
import java.util.List;
import net.sf.minuteProject.architecture.bsla.bean.criteria.PaginationCriteria;
import net.sf.minuteProject.architecture.bsla.dao.face.DataAccessObject;


/**
 *
 * <p>Title: ConferenceDao</p>
 *
 * <p>Description: Interface of a Data access object dealing with ConferenceDao
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching conference objects</p>
 *
 */
public interface ConferenceDao extends DataAccessObject {

    /**
     * Inserts a Conference entity 
     * @param Conference conference
     */
    public void insertConference(Conference conference) ;
 
    /**
     * Inserts a list of Conference entity 
     * @param List<Conference> conferences
     */
    public void insertConferences(List<Conference> conferences) ;
        
    /**
     * Updates a Conference entity 
     * @param Conference conference
     */
    public Conference updateConference(Conference conference) ;

	 /**
     * Updates a Conference entity with only the attributes set into Conference.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is to be done
   * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
   * @param Conference conference
   */
    public int updateNotNullOnlyConference(Conference conference) ;
	 
	public int updateNotNullOnlyPrototypeConference(Conference conference, Conference prototypeCriteria);
	
     /**
     * Saves a Conference entity 
     * @param Conference conference
     */
    public void saveConference(Conference conference);
    
    /**
     * Deletes a Conference entity 
     * @param Conference conference
     */
    public void deleteConference(Conference conference) ;
 
    /**
     * Loads the Conference entity which is related to an instance of
     * Conference
     * @param Long id
     * @return Conference The Conference entity
     
    public Conference loadConference(Long id);
*/
    /**
     * Loads the Conference entity which is related to an instance of
     * Conference
     * @param java.lang.Long Id
     * @return Conference The Conference entity
     */
    public Conference loadConference(java.lang.Long id);    

    /**
     * Loads a list of Conference entity 
     * @param List<java.lang.Long> ids
     * @return List<Conference> The Conference entity
     */
    public List<Conference> loadConferenceListByConference (List<Conference> conferences);
    
    /**
     * Loads a list of Conference entity 
     * @param List<java.lang.Long> ids
     * @return List<Conference> The Conference entity
     */
    public List<Conference> loadConferenceListById(List<java.lang.Long> ids);
    
    /**
     * Loads the Conference entity which is related to an instance of
     * Conference and its dependent one to many objects
     * @param Long id
     * @return Conference The Conference entity
     */
    public Conference loadFullFirstLevelConference(java.lang.Long id);
    
    /**
     * Loads the Conference entity which is related to an instance of
     * Conference
     * @param Conference conference
     * @return Conference The Conference entity
     */
    public Conference loadFullFirstLevelConference(Conference conference);    
    
    
    /**
     * Loads the Conference entity which is related to an instance of
     * Conference and its dependent objects one to many
     * @param Long id
     * @return Conference The Conference entity
     */
    public Conference loadFullConference(Long id) ;

    /**
     * Searches a list of Conference entity based on a Conference containing Conference matching criteria
     * @param Conference conference
     * @return List<Conference>
     */
    public List<Conference> searchPrototypeConference(Conference conference) ;
    
    /**
     * Searches a list of Conference entity based on a list of Conference containing Conference matching criteria
     * @param List<Conference> conferences
     * @return List<Conference>
     */
    public List<Conference> searchPrototypeConference(List<Conference> conferences) ;    
    	
	/**
     * Searches a list of Conference entity 
     * @param Conference conference
     * @return List
     */
    public List<Conference> searchPrototypeConference(Conference conferencePositive, Conference conferenceNegative) ;
        
    /**
     * Load a paginated list of Conference entity dependent of pagination criteria
     * @param PaginationCriteria paginationCriteria
     * @return List
     */
    public List<Conference> loadPaginatedConference (Conference conference, PaginationCriteria paginationCriteria) ;
            
}
