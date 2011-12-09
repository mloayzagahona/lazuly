package net.sf.mp.demo.conference.dao.face.conference;

import net.sf.mp.demo.conference.domain.conference.ConferenceFeedback;
import java.util.List;
import net.sf.minuteProject.architecture.filter.data.Criteria;
import net.sf.minuteProject.architecture.bsla.dao.face.DataAccessObject;

/**
 *
 * <p>Title: ConferenceFeedbackExtDao</p>
 *
 * <p>Description: Interface of a Data access object dealing with ConferenceFeedbackExtDao
 * persistence. It offers extended DAO functionalities</p>
 *
 */
public interface ConferenceFeedbackExtDao extends DataAccessObject {
     
     /**
     * Inserts a ConferenceFeedback entity with cascade of its children
     * @param ConferenceFeedback conferenceFeedback
     */
    public void insertConferenceFeedbackWithCascade(ConferenceFeedback conferenceFeedback) ;
    
    /**
     * Inserts a list of ConferenceFeedback entity with cascade of its children
     * @param List<ConferenceFeedback> conferenceFeedbacks
     */
    public void insertConferenceFeedbacksWithCascade(List<ConferenceFeedback> conferenceFeedbacks) ;        
   
    /**
     * lookup ConferenceFeedback entity ConferenceFeedback, criteria and max result number
     */
    public List<ConferenceFeedback> lookupConferenceFeedback(ConferenceFeedback conferenceFeedback, Criteria criteria, Integer numberOfResult);
	
	public Integer updateNotNullOnlyConferenceFeedback (ConferenceFeedback conferenceFeedback, Criteria criteria);

	/**
	 * Affect the first conferenceFeedback retrieved corresponding to the conferenceFeedback criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 */
    public ConferenceFeedback affectConferenceFeedback (ConferenceFeedback conferenceFeedback);
    
    public ConferenceFeedback affectConferenceFeedbackUseCache (ConferenceFeedback conferenceFeedback);
    	
	/**
	 * Assign the first conferenceFeedback retrieved corresponding to the conferenceFeedback criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no conferenceFeedback corresponding in the database. Then conferenceFeedback is inserted and returned with its primary key(s). 
	 */
    public ConferenceFeedback assignConferenceFeedback (ConferenceFeedback conferenceFeedback);
    
    public ConferenceFeedback assignConferenceFeedbackUseCache (ConferenceFeedback conferenceFeedback);
         
    /**
    * return the first ConferenceFeedback entity found
    */           
    public ConferenceFeedback getFirstConferenceFeedback (ConferenceFeedback conferenceFeedback);
    
    /**
    * checks if the ConferenceFeedback entity exists
    */           
    public boolean existsConferenceFeedback (ConferenceFeedback conferenceFeedback);    
    
    public boolean existsConferenceFeedbackWhereConditionsAre (ConferenceFeedback conferenceFeedback);

    /**
    * partial load enables to specify the fields you want to load explicitly
    */            
    public List<ConferenceFeedback> partialLoadConferenceFeedback(ConferenceFeedback conferenceFeedback, ConferenceFeedback positiveConferenceFeedback, ConferenceFeedback negativeConferenceFeedback);

    /**
    * partial load with parent entities
    * variation (list, first, distinct decorator)
    * variation2 (with cache)
    */
    public List<ConferenceFeedback> partialLoadWithParentConferenceFeedback(ConferenceFeedback conferenceFeedback, ConferenceFeedback positiveConferenceFeedback, ConferenceFeedback negativeConferenceFeedback);

    public List<ConferenceFeedback> partialLoadWithParentConferenceFeedbackUseCache(ConferenceFeedback conferenceFeedback, ConferenceFeedback positiveConferenceFeedback, ConferenceFeedback negativeConferenceFeedback, Boolean useCache);

    public List<ConferenceFeedback> partialLoadWithParentConferenceFeedbackUseCacheOnResult(ConferenceFeedback conferenceFeedback, ConferenceFeedback positiveConferenceFeedback, ConferenceFeedback negativeConferenceFeedback, Boolean useCache);

    /**
    * variation first
    */
    public ConferenceFeedback partialLoadWithParentFirstConferenceFeedback(ConferenceFeedback conferenceFeedbackWhat, ConferenceFeedback positiveConferenceFeedback, ConferenceFeedback negativeConferenceFeedback);
    
    public ConferenceFeedback partialLoadWithParentFirstConferenceFeedbackUseCache(ConferenceFeedback conferenceFeedbackWhat, ConferenceFeedback positiveConferenceFeedback, ConferenceFeedback negativeConferenceFeedback, Boolean useCache);

    public ConferenceFeedback partialLoadWithParentFirstConferenceFeedbackUseCacheOnResult(ConferenceFeedback conferenceFeedbackWhat, ConferenceFeedback positiveConferenceFeedback, ConferenceFeedback negativeConferenceFeedback, Boolean useCache);

    /**
    * variation distinct
    */
    public List<ConferenceFeedback> getDistinctConferenceFeedback(ConferenceFeedback conferenceFeedbackWhat, ConferenceFeedback positiveConferenceFeedback, ConferenceFeedback negativeConferenceFeedback);

    //
    public List partialLoadWithParentForBean(Object bean, ConferenceFeedback conferenceFeedback, ConferenceFeedback positiveConferenceFeedback, ConferenceFeedback negativeConferenceFeedback);

    /**
    * search on prototype with cache
    */
    public List<ConferenceFeedback> searchPrototypeWithCacheConferenceFeedback (ConferenceFeedback conferenceFeedback);
      
    
    /**
     * Searches a list of distinct ConferenceFeedback entity based on a ConferenceFeedback mask and a list of ConferenceFeedback containing ConferenceFeedback matching criteria
     * @param ConferenceFeedback conferenceFeedback
     * @param List<ConferenceFeedback> conferenceFeedbacks
     * @return List<ConferenceFeedback>
     */
    public List<ConferenceFeedback> searchDistinctPrototypeConferenceFeedback(ConferenceFeedback conferenceFeedbackMask, List<ConferenceFeedback> conferenceFeedbacks) ;    

	public List<ConferenceFeedback> countDistinct (ConferenceFeedback whatMask, ConferenceFeedback whereEqCriteria);
	
	public Long count (ConferenceFeedback whereEqCriteria);
	
	public List<ConferenceFeedback> loadGraph(ConferenceFeedback graphMaskWhat, List<ConferenceFeedback> whereMask);  
	
	public List<ConferenceFeedback> loadGraphFromParentKey (ConferenceFeedback graphMaskWhat, List<ConferenceFeedback> parents); 
	
    /**
     * generic to move after in superclass
     */
    public List<Object[]> getSQLQueryResult(String query);     

	
}

