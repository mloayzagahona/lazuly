package net.sf.mp.demo.conference.dao.face.statistics;

import net.sf.mp.demo.conference.domain.statistics.StatMbPerCtryConf;
import java.util.List;
import net.sf.minuteProject.architecture.filter.data.Criteria;
import net.sf.minuteProject.architecture.bsla.dao.face.DataAccessObject;

/**
 *
 * <p>Title: StatMbPerCtryConfExtDao</p>
 *
 * <p>Description: Interface of a Data access object dealing with StatMbPerCtryConfExtDao
 * persistence. It offers extended DAO functionalities</p>
 *
 */
public interface StatMbPerCtryConfExtDao extends DataAccessObject {
     
     /**
     * Inserts a StatMbPerCtryConf entity with cascade of its children
     * @param StatMbPerCtryConf statMbPerCtryConf
     */
    public void insertStatMbPerCtryConfWithCascade(StatMbPerCtryConf statMbPerCtryConf) ;
    
    /**
     * Inserts a list of StatMbPerCtryConf entity with cascade of its children
     * @param List<StatMbPerCtryConf> statMbPerCtryConfs
     */
    public void insertStatMbPerCtryConfsWithCascade(List<StatMbPerCtryConf> statMbPerCtryConfs) ;        
   
    /**
     * lookup StatMbPerCtryConf entity StatMbPerCtryConf, criteria and max result number
     */
    public List<StatMbPerCtryConf> lookupStatMbPerCtryConf(StatMbPerCtryConf statMbPerCtryConf, Criteria criteria, Integer numberOfResult);
	
	public Integer updateNotNullOnlyStatMbPerCtryConf (StatMbPerCtryConf statMbPerCtryConf, Criteria criteria);

	/**
	 * Affect the first statMbPerCtryConf retrieved corresponding to the statMbPerCtryConf criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 */
    public StatMbPerCtryConf affectStatMbPerCtryConf (StatMbPerCtryConf statMbPerCtryConf);
    
    public StatMbPerCtryConf affectStatMbPerCtryConfUseCache (StatMbPerCtryConf statMbPerCtryConf);
    	
	/**
	 * Assign the first statMbPerCtryConf retrieved corresponding to the statMbPerCtryConf criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no statMbPerCtryConf corresponding in the database. Then statMbPerCtryConf is inserted and returned with its primary key(s). 
	 */
    public StatMbPerCtryConf assignStatMbPerCtryConf (StatMbPerCtryConf statMbPerCtryConf);
    
    public StatMbPerCtryConf assignStatMbPerCtryConfUseCache (StatMbPerCtryConf statMbPerCtryConf);
         
    /**
    * return the first StatMbPerCtryConf entity found
    */           
    public StatMbPerCtryConf getFirstStatMbPerCtryConf (StatMbPerCtryConf statMbPerCtryConf);
    
    /**
    * checks if the StatMbPerCtryConf entity exists
    */           
    public boolean existsStatMbPerCtryConf (StatMbPerCtryConf statMbPerCtryConf);    
    
    public boolean existsStatMbPerCtryConfWhereConditionsAre (StatMbPerCtryConf statMbPerCtryConf);

    /**
    * partial load enables to specify the fields you want to load explicitly
    */            
    public List<StatMbPerCtryConf> partialLoadStatMbPerCtryConf(StatMbPerCtryConf statMbPerCtryConf, StatMbPerCtryConf positiveStatMbPerCtryConf, StatMbPerCtryConf negativeStatMbPerCtryConf);

    /**
    * partial load with parent entities
    * variation (list, first, distinct decorator)
    * variation2 (with cache)
    */
    public List<StatMbPerCtryConf> partialLoadWithParentStatMbPerCtryConf(StatMbPerCtryConf statMbPerCtryConf, StatMbPerCtryConf positiveStatMbPerCtryConf, StatMbPerCtryConf negativeStatMbPerCtryConf);

    public List<StatMbPerCtryConf> partialLoadWithParentStatMbPerCtryConfUseCache(StatMbPerCtryConf statMbPerCtryConf, StatMbPerCtryConf positiveStatMbPerCtryConf, StatMbPerCtryConf negativeStatMbPerCtryConf, Boolean useCache);

    public List<StatMbPerCtryConf> partialLoadWithParentStatMbPerCtryConfUseCacheOnResult(StatMbPerCtryConf statMbPerCtryConf, StatMbPerCtryConf positiveStatMbPerCtryConf, StatMbPerCtryConf negativeStatMbPerCtryConf, Boolean useCache);

    /**
    * variation first
    */
    public StatMbPerCtryConf partialLoadWithParentFirstStatMbPerCtryConf(StatMbPerCtryConf statMbPerCtryConfWhat, StatMbPerCtryConf positiveStatMbPerCtryConf, StatMbPerCtryConf negativeStatMbPerCtryConf);
    
    public StatMbPerCtryConf partialLoadWithParentFirstStatMbPerCtryConfUseCache(StatMbPerCtryConf statMbPerCtryConfWhat, StatMbPerCtryConf positiveStatMbPerCtryConf, StatMbPerCtryConf negativeStatMbPerCtryConf, Boolean useCache);

    public StatMbPerCtryConf partialLoadWithParentFirstStatMbPerCtryConfUseCacheOnResult(StatMbPerCtryConf statMbPerCtryConfWhat, StatMbPerCtryConf positiveStatMbPerCtryConf, StatMbPerCtryConf negativeStatMbPerCtryConf, Boolean useCache);

    /**
    * variation distinct
    */
    public List<StatMbPerCtryConf> getDistinctStatMbPerCtryConf(StatMbPerCtryConf statMbPerCtryConfWhat, StatMbPerCtryConf positiveStatMbPerCtryConf, StatMbPerCtryConf negativeStatMbPerCtryConf);

    //
    public List partialLoadWithParentForBean(Object bean, StatMbPerCtryConf statMbPerCtryConf, StatMbPerCtryConf positiveStatMbPerCtryConf, StatMbPerCtryConf negativeStatMbPerCtryConf);

    /**
    * search on prototype with cache
    */
    public List<StatMbPerCtryConf> searchPrototypeWithCacheStatMbPerCtryConf (StatMbPerCtryConf statMbPerCtryConf);
      
    
    /**
     * Searches a list of distinct StatMbPerCtryConf entity based on a StatMbPerCtryConf mask and a list of StatMbPerCtryConf containing StatMbPerCtryConf matching criteria
     * @param StatMbPerCtryConf statMbPerCtryConf
     * @param List<StatMbPerCtryConf> statMbPerCtryConfs
     * @return List<StatMbPerCtryConf>
     */
    public List<StatMbPerCtryConf> searchDistinctPrototypeStatMbPerCtryConf(StatMbPerCtryConf statMbPerCtryConfMask, List<StatMbPerCtryConf> statMbPerCtryConfs) ;    

	public List<StatMbPerCtryConf> countDistinct (StatMbPerCtryConf whatMask, StatMbPerCtryConf whereEqCriteria);
	
	public Long count (StatMbPerCtryConf whereEqCriteria);
	
	public List<StatMbPerCtryConf> loadGraph(StatMbPerCtryConf graphMaskWhat, List<StatMbPerCtryConf> whereMask);  
	
	public List<StatMbPerCtryConf> loadGraphFromParentKey (StatMbPerCtryConf graphMaskWhat, List<StatMbPerCtryConf> parents); 
	
    /**
     * generic to move after in superclass
     */
    public List<Object[]> getSQLQueryResult(String query);     

	
}

