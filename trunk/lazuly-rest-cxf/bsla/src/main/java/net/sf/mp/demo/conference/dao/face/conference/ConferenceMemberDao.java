package net.sf.mp.demo.conference.dao.face.conference;

import net.sf.mp.demo.conference.domain.conference.ConferenceMember;
import java.util.List;
import net.sf.minuteProject.architecture.bsla.bean.criteria.PaginationCriteria;
import net.sf.minuteProject.architecture.bsla.dao.face.DataAccessObject;


/**
 *
 * <p>Title: ConferenceMemberDao</p>
 *
 * <p>Description: Interface of a Data access object dealing with ConferenceMemberDao
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching conferenceMember objects</p>
 *
 */
public interface ConferenceMemberDao extends DataAccessObject {

    /**
     * Inserts a ConferenceMember entity 
     * @param ConferenceMember conferenceMember
     */
    public void insertConferenceMember(ConferenceMember conferenceMember) ;
 
    /**
     * Inserts a list of ConferenceMember entity 
     * @param List<ConferenceMember> conferenceMembers
     */
    public void insertConferenceMembers(List<ConferenceMember> conferenceMembers) ;
        
    /**
     * Updates a ConferenceMember entity 
     * @param ConferenceMember conferenceMember
     */
    public ConferenceMember updateConferenceMember(ConferenceMember conferenceMember) ;

	 /**
     * Updates a ConferenceMember entity with only the attributes set into ConferenceMember.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is to be done
   * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
   * @param ConferenceMember conferenceMember
   */
    public int updateNotNullOnlyConferenceMember(ConferenceMember conferenceMember) ;
	 
	public int updateNotNullOnlyPrototypeConferenceMember(ConferenceMember conferenceMember, ConferenceMember prototypeCriteria);
	
     /**
     * Saves a ConferenceMember entity 
     * @param ConferenceMember conferenceMember
     */
    public void saveConferenceMember(ConferenceMember conferenceMember);
    
    /**
     * Deletes a ConferenceMember entity 
     * @param ConferenceMember conferenceMember
     */
    public void deleteConferenceMember(ConferenceMember conferenceMember) ;
 
    /**
     * Loads the ConferenceMember entity which is related to an instance of
     * ConferenceMember
     * @param Long id
     * @return ConferenceMember The ConferenceMember entity
     
    public ConferenceMember loadConferenceMember(Long id);
*/
    /**
     * Loads the ConferenceMember entity which is related to an instance of
     * ConferenceMember
     * @param java.lang.Long Id
     * @return ConferenceMember The ConferenceMember entity
     */
    public ConferenceMember loadConferenceMember(java.lang.Long id);    

    /**
     * Loads a list of ConferenceMember entity 
     * @param List<java.lang.Long> ids
     * @return List<ConferenceMember> The ConferenceMember entity
     */
    public List<ConferenceMember> loadConferenceMemberListByConferenceMember (List<ConferenceMember> conferenceMembers);
    
    /**
     * Loads a list of ConferenceMember entity 
     * @param List<java.lang.Long> ids
     * @return List<ConferenceMember> The ConferenceMember entity
     */
    public List<ConferenceMember> loadConferenceMemberListById(List<java.lang.Long> ids);
    
    /**
     * Loads the ConferenceMember entity which is related to an instance of
     * ConferenceMember and its dependent one to many objects
     * @param Long id
     * @return ConferenceMember The ConferenceMember entity
     */
    public ConferenceMember loadFullFirstLevelConferenceMember(java.lang.Long id);
    
    /**
     * Loads the ConferenceMember entity which is related to an instance of
     * ConferenceMember
     * @param ConferenceMember conferenceMember
     * @return ConferenceMember The ConferenceMember entity
     */
    public ConferenceMember loadFullFirstLevelConferenceMember(ConferenceMember conferenceMember);    
    
    
    /**
     * Loads the ConferenceMember entity which is related to an instance of
     * ConferenceMember and its dependent objects one to many
     * @param Long id
     * @return ConferenceMember The ConferenceMember entity
     */
    public ConferenceMember loadFullConferenceMember(Long id) ;

    /**
     * Searches a list of ConferenceMember entity based on a ConferenceMember containing ConferenceMember matching criteria
     * @param ConferenceMember conferenceMember
     * @return List<ConferenceMember>
     */
    public List<ConferenceMember> searchPrototypeConferenceMember(ConferenceMember conferenceMember) ;
    
    /**
     * Searches a list of ConferenceMember entity based on a list of ConferenceMember containing ConferenceMember matching criteria
     * @param List<ConferenceMember> conferenceMembers
     * @return List<ConferenceMember>
     */
    public List<ConferenceMember> searchPrototypeConferenceMember(List<ConferenceMember> conferenceMembers) ;    
    	
	/**
     * Searches a list of ConferenceMember entity 
     * @param ConferenceMember conferenceMember
     * @return List
     */
    public List<ConferenceMember> searchPrototypeConferenceMember(ConferenceMember conferenceMemberPositive, ConferenceMember conferenceMemberNegative) ;
        
    /**
     * Load a paginated list of ConferenceMember entity dependent of pagination criteria
     * @param PaginationCriteria paginationCriteria
     * @return List
     */
    public List<ConferenceMember> loadPaginatedConferenceMember (ConferenceMember conferenceMember, PaginationCriteria paginationCriteria) ;
            
}
