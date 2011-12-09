package net.sf.mp.demo.conference.dao.face.conference;

import net.sf.mp.demo.conference.domain.conference.Speaker;
import java.util.List;
import net.sf.minuteProject.architecture.filter.data.Criteria;
import net.sf.minuteProject.architecture.bsla.dao.face.DataAccessObject;

/**
 *
 * <p>Title: SpeakerExtDao</p>
 *
 * <p>Description: Interface of a Data access object dealing with SpeakerExtDao
 * persistence. It offers extended DAO functionalities</p>
 *
 */
public interface SpeakerExtDao extends DataAccessObject {
     
     /**
     * Inserts a Speaker entity with cascade of its children
     * @param Speaker speaker
     */
    public void insertSpeakerWithCascade(Speaker speaker) ;
    
    /**
     * Inserts a list of Speaker entity with cascade of its children
     * @param List<Speaker> speakers
     */
    public void insertSpeakersWithCascade(List<Speaker> speakers) ;        
   
    /**
     * lookup Speaker entity Speaker, criteria and max result number
     */
    public List<Speaker> lookupSpeaker(Speaker speaker, Criteria criteria, Integer numberOfResult);
	
	public Integer updateNotNullOnlySpeaker (Speaker speaker, Criteria criteria);

	/**
	 * Affect the first speaker retrieved corresponding to the speaker criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 */
    public Speaker affectSpeaker (Speaker speaker);
    
    public Speaker affectSpeakerUseCache (Speaker speaker);
    	
	/**
	 * Assign the first speaker retrieved corresponding to the speaker criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no speaker corresponding in the database. Then speaker is inserted and returned with its primary key(s). 
	 */
    public Speaker assignSpeaker (Speaker speaker);
    
    public Speaker assignSpeakerUseCache (Speaker speaker);
         
    /**
    * return the first Speaker entity found
    */           
    public Speaker getFirstSpeaker (Speaker speaker);
    
    /**
    * checks if the Speaker entity exists
    */           
    public boolean existsSpeaker (Speaker speaker);    
    
    public boolean existsSpeakerWhereConditionsAre (Speaker speaker);

    /**
    * partial load enables to specify the fields you want to load explicitly
    */            
    public List<Speaker> partialLoadSpeaker(Speaker speaker, Speaker positiveSpeaker, Speaker negativeSpeaker);

    /**
    * partial load with parent entities
    * variation (list, first, distinct decorator)
    * variation2 (with cache)
    */
    public List<Speaker> partialLoadWithParentSpeaker(Speaker speaker, Speaker positiveSpeaker, Speaker negativeSpeaker);

    public List<Speaker> partialLoadWithParentSpeakerUseCache(Speaker speaker, Speaker positiveSpeaker, Speaker negativeSpeaker, Boolean useCache);

    public List<Speaker> partialLoadWithParentSpeakerUseCacheOnResult(Speaker speaker, Speaker positiveSpeaker, Speaker negativeSpeaker, Boolean useCache);

    /**
    * variation first
    */
    public Speaker partialLoadWithParentFirstSpeaker(Speaker speakerWhat, Speaker positiveSpeaker, Speaker negativeSpeaker);
    
    public Speaker partialLoadWithParentFirstSpeakerUseCache(Speaker speakerWhat, Speaker positiveSpeaker, Speaker negativeSpeaker, Boolean useCache);

    public Speaker partialLoadWithParentFirstSpeakerUseCacheOnResult(Speaker speakerWhat, Speaker positiveSpeaker, Speaker negativeSpeaker, Boolean useCache);

    /**
    * variation distinct
    */
    public List<Speaker> getDistinctSpeaker(Speaker speakerWhat, Speaker positiveSpeaker, Speaker negativeSpeaker);

    //
    public List partialLoadWithParentForBean(Object bean, Speaker speaker, Speaker positiveSpeaker, Speaker negativeSpeaker);

    /**
    * search on prototype with cache
    */
    public List<Speaker> searchPrototypeWithCacheSpeaker (Speaker speaker);
      
    
    /**
     * Searches a list of distinct Speaker entity based on a Speaker mask and a list of Speaker containing Speaker matching criteria
     * @param Speaker speaker
     * @param List<Speaker> speakers
     * @return List<Speaker>
     */
    public List<Speaker> searchDistinctPrototypeSpeaker(Speaker speakerMask, List<Speaker> speakers) ;    

	public List<Speaker> countDistinct (Speaker whatMask, Speaker whereEqCriteria);
	
	public Long count (Speaker whereEqCriteria);
	
	public List<Speaker> loadGraph(Speaker graphMaskWhat, List<Speaker> whereMask);  
	
	public List<Speaker> loadGraphFromParentKey (Speaker graphMaskWhat, List<Speaker> parents); 
	
    /**
     * generic to move after in superclass
     */
    public List<Object[]> getSQLQueryResult(String query);     

	
}

