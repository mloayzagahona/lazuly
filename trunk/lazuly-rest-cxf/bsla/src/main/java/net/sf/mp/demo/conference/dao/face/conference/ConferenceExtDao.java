package net.sf.mp.demo.conference.dao.face.conference;

import net.sf.mp.demo.conference.domain.conference.Conference;
import java.util.List;
import net.sf.minuteProject.architecture.filter.data.Criteria;
import net.sf.minuteProject.architecture.bsla.dao.face.DataAccessObject;

/**
 *
 * <p>Title: ConferenceExtDao</p>
 *
 * <p>Description: Interface of a Data access object dealing with ConferenceExtDao
 * persistence. It offers extended DAO functionalities</p>
 *
 */
public interface ConferenceExtDao extends DataAccessObject {
     
     /**
     * Inserts a Conference entity with cascade of its children
     * @param Conference conference
     */
    public void insertConferenceWithCascade(Conference conference) ;
    
    /**
     * Inserts a list of Conference entity with cascade of its children
     * @param List<Conference> conferences
     */
    public void insertConferencesWithCascade(List<Conference> conferences) ;        
   
    /**
     * lookup Conference entity Conference, criteria and max result number
     */
    public List<Conference> lookupConference(Conference conference, Criteria criteria, Integer numberOfResult);
	
	public Integer updateNotNullOnlyConference (Conference conference, Criteria criteria);

	/**
	 * Affect the first conference retrieved corresponding to the conference criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 */
    public Conference affectConference (Conference conference);
    
    public Conference affectConferenceUseCache (Conference conference);
    	
	/**
	 * Assign the first conference retrieved corresponding to the conference criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no conference corresponding in the database. Then conference is inserted and returned with its primary key(s). 
	 */
    public Conference assignConference (Conference conference);
    
    public Conference assignConferenceUseCache (Conference conference);
         
    /**
    * return the first Conference entity found
    */           
    public Conference getFirstConference (Conference conference);
    
    /**
    * checks if the Conference entity exists
    */           
    public boolean existsConference (Conference conference);    
    
    public boolean existsConferenceWhereConditionsAre (Conference conference);

    /**
    * partial load enables to specify the fields you want to load explicitly
    */            
    public List<Conference> partialLoadConference(Conference conference, Conference positiveConference, Conference negativeConference);

    /**
    * partial load with parent entities
    * variation (list, first, distinct decorator)
    * variation2 (with cache)
    */
    public List<Conference> partialLoadWithParentConference(Conference conference, Conference positiveConference, Conference negativeConference);

    public List<Conference> partialLoadWithParentConferenceUseCache(Conference conference, Conference positiveConference, Conference negativeConference, Boolean useCache);

    public List<Conference> partialLoadWithParentConferenceUseCacheOnResult(Conference conference, Conference positiveConference, Conference negativeConference, Boolean useCache);

    /**
    * variation first
    */
    public Conference partialLoadWithParentFirstConference(Conference conferenceWhat, Conference positiveConference, Conference negativeConference);
    
    public Conference partialLoadWithParentFirstConferenceUseCache(Conference conferenceWhat, Conference positiveConference, Conference negativeConference, Boolean useCache);

    public Conference partialLoadWithParentFirstConferenceUseCacheOnResult(Conference conferenceWhat, Conference positiveConference, Conference negativeConference, Boolean useCache);

    /**
    * variation distinct
    */
    public List<Conference> getDistinctConference(Conference conferenceWhat, Conference positiveConference, Conference negativeConference);

    //
    public List partialLoadWithParentForBean(Object bean, Conference conference, Conference positiveConference, Conference negativeConference);

    /**
    * search on prototype with cache
    */
    public List<Conference> searchPrototypeWithCacheConference (Conference conference);
      
    
    /**
     * Searches a list of distinct Conference entity based on a Conference mask and a list of Conference containing Conference matching criteria
     * @param Conference conference
     * @param List<Conference> conferences
     * @return List<Conference>
     */
    public List<Conference> searchDistinctPrototypeConference(Conference conferenceMask, List<Conference> conferences) ;    

	public List<Conference> countDistinct (Conference whatMask, Conference whereEqCriteria);
	
	public Long count (Conference whereEqCriteria);
	
	public List<Conference> loadGraph(Conference graphMaskWhat, List<Conference> whereMask);  
	
	public List<Conference> loadGraphFromParentKey (Conference graphMaskWhat, List<Conference> parents); 
	
    /**
     * generic to move after in superclass
     */
    public List<Object[]> getSQLQueryResult(String query);     

	
}

