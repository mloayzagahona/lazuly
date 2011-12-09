package net.sf.mp.demo.conference.dao.impl.jpa.conference;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Date;
import java.sql.Timestamp;

import javax.persistence.Query;
import javax.persistence.EntityManager;

import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import net.sf.minuteProject.architecture.bsla.bean.criteria.PaginationCriteria;
import net.sf.mp.demo.conference.dao.face.conference.SpeakerDao;
import net.sf.mp.demo.conference.domain.conference.Speaker;
import net.sf.mp.demo.conference.domain.conference.ConferenceMember;
import net.sf.mp.demo.conference.domain.conference.Sponsor;

/**
 *
 * <p>Title: SpeakerJPAImpl</p>
 *
 * <p>Description: Interface of a Data access object dealing with SpeakerJPAImpl
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching SpeakerJPAImpl objects</p>
 *
 */

public class SpeakerJPAImpl extends JpaDaoSupport implements SpeakerDao {

	public SpeakerJPAImpl () {}

    /**
     * Inserts a Speaker entity 
     * @param Speaker speaker
     */
    public void insertSpeaker(Speaker speaker) {
       convertTransientReferenceToNull (speaker);
       getJpaTemplate().persist(speaker);
    }

    protected void insertSpeaker(EntityManager emForRecursiveDao, Speaker speaker) {
       emForRecursiveDao.persist(speaker);
    } 
    /**
     * Inserts a list of Speaker entity 
     * @param List<Speaker> speakers
     */
    public void insertSpeakers(List<Speaker> speakers) {
    	//TODO
    }
    /**
     * Updates a Speaker entity 
     * @param Speaker speaker
     */
    public Speaker updateSpeaker(Speaker speaker) {
       convertTransientReferenceToNull (speaker);
       return getJpaTemplate().merge(speaker);
    }

	/**
     * Updates a Speaker entity with only the attributes set into Speaker.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is issued
     * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
     * @param Speaker speaker
    */ 
    @Transactional
    public int updateNotNullOnlySpeaker(Speaker speaker) {
        Query jpaQuery = getEntityManager().createQuery(getUpdateNotNullOnlySpeakerQueryChunk(speaker));
        if (speaker.getId() != null) {
           jpaQuery.setParameter ("id", speaker.getId());
        }   
        if (speaker.getConferenceMemberId() != null) {
           jpaQuery.setParameter ("conferenceMemberId", speaker.getConferenceMemberId());
        }   
        if (speaker.getBio() != null) {
           jpaQuery.setParameter ("bio", speaker.getBio());
        }   
        if (speaker.getPhoto() != null) {
           jpaQuery.setParameter ("photo", speaker.getPhoto());
        }   
        if (speaker.getWebSiteUrl() != null) {
           jpaQuery.setParameter ("webSiteUrl", speaker.getWebSiteUrl());
        }   
        if (speaker.getSponsorId() != null) {
           jpaQuery.setParameter ("sponsorId", speaker.getSponsorId());
        }   
		return jpaQuery.executeUpdate();
    }

    protected String getUpdateNotNullOnlySpeakerQueryChunkPrototype (Speaker speaker) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update Speaker speaker ");
        if (speaker.getConferenceMemberId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" speaker.conferenceMemberId = :conferenceMemberId");
        }
        if (speaker.getBio() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" speaker.bio = :bio");
        }
        if (speaker.getPhoto() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" speaker.photo = :photo");
        }
        if (speaker.getWebSiteUrl() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" speaker.webSiteUrl = :webSiteUrl");
        }
        if (speaker.getSponsorId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" speaker.sponsorId = :sponsorId");
        }
        return query.toString();
    }
    
    protected String getUpdateNotNullOnlySpeakerQueryChunk (Speaker speaker) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer(getUpdateNotNullOnlySpeakerQueryChunkPrototype(speaker));
        if (speaker.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
			     query.append(" speaker.id = :id");
        }
        return query.toString();
    }
    
                
	protected Speaker assignBlankToNull (Speaker speaker) {
	    if (speaker==null)
			return null;
        if (speaker.getBio()!=null && speaker.getBio().equals(""))
           speaker.setBio((String)null);
        if (speaker.getPhoto()!=null && speaker.getPhoto().equals(""))
           speaker.setPhoto((String)null);
        if (speaker.getWebSiteUrl()!=null && speaker.getWebSiteUrl().equals(""))
           speaker.setWebSiteUrl((String)null);
		return speaker;
	}
	
	protected boolean isAllNull (Speaker speaker) {
	    if (speaker==null)
			return true;
        if (speaker.getId()!=null) 
            return false;
        if (speaker.getConferenceMemberId()!=null) 
            return false;
        if (speaker.getBio()!=null) 
            return false;
        if (speaker.getPhoto()!=null) 
            return false;
        if (speaker.getWebSiteUrl()!=null) 
            return false;
        if (speaker.getSponsorId()!=null) 
            return false;
		return true;
	}
		
	@Transactional
    public int updateNotNullOnlyPrototypeSpeaker(Speaker speaker, Speaker prototypeCriteria) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update Speaker speaker ");
        if (speaker.getId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" speaker.id = "+ speaker.getId() + " ");
        }
        if (speaker.getConferenceMemberId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" speaker.conferenceMemberId = "+ speaker.getConferenceMemberId() + " ");
        }
        if (speaker.getBio() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" speaker.bio = '"+ speaker.getBio()+"' ");
        }
        if (speaker.getPhoto() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" speaker.photo = '"+ speaker.getPhoto()+"' ");
        }
        if (speaker.getWebSiteUrl() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" speaker.webSiteUrl = '"+ speaker.getWebSiteUrl()+"' ");
        }
        if (speaker.getSponsorId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" speaker.sponsorId = "+ speaker.getSponsorId() + " ");
        }
		isWhereSet = false; 
        if (prototypeCriteria.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" speaker.id = "+ prototypeCriteria.getId() + " ");
        }
        if (prototypeCriteria.getConferenceMemberId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" speaker.conferenceMemberId = "+ prototypeCriteria.getConferenceMemberId() + " ");
        }
        if (prototypeCriteria.getBio() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" speaker.bio = '"+ prototypeCriteria.getBio()+"' ");
        }
        if (prototypeCriteria.getPhoto() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" speaker.photo = '"+ prototypeCriteria.getPhoto()+"' ");
        }
        if (prototypeCriteria.getWebSiteUrl() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" speaker.webSiteUrl = '"+ prototypeCriteria.getWebSiteUrl()+"' ");
        }
        if (prototypeCriteria.getSponsorId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" speaker.sponsorId = "+ prototypeCriteria.getSponsorId() + " ");
        }
        Query jpaQuery = getEntityManager().createQuery(query.toString());
		return jpaQuery.executeUpdate();
    }
     
     /**
     * Saves a Speaker entity 
     * @param Speaker speaker
     */
    public void saveSpeaker(Speaker speaker) {
       //getJpaTemplate().persist(speaker);
       convertTransientReferenceToNull (speaker);
       if (getJpaTemplate().contains(speaker)) {
          getJpaTemplate().merge(speaker);
       } else {
          getJpaTemplate().persist(speaker);
       }
       getJpaTemplate().flush(); 
    }
       
    /**
     * Deletes a Speaker entity 
     * @param Speaker speaker
     */
    public void deleteSpeaker(Speaker speaker) {
      getJpaTemplate().remove(speaker);
    }
    
    /**
     * Loads the Speaker entity which is related to an instance of
     * Speaker
     * @param Long id
     * @return Speaker The Speaker entity
     
    public Speaker loadSpeaker(Long id) {
    	return (Speaker)getJpaTemplate().get(Speaker.class, id);
    }
*/
  
    /**
     * Loads the Speaker entity which is related to an instance of
     * Speaker
     * @param java.lang.Long Id
     * @return Speaker The Speaker entity
     */
    public Speaker loadSpeaker(java.lang.Long id) {
    	return (Speaker)getJpaTemplate().find(Speaker.class, id);
    }
    
    /**
     * Loads a list of Speaker entity 
     * @param List<java.lang.Long> ids
     * @return List<Speaker> The Speaker entity
     */
    public List<Speaker> loadSpeakerListBySpeaker (List<Speaker> speakers) {
       return null;
    }
    
    /**
     * Loads a list of Speaker entity 
     * @param List<java.lang.Long> ids
     * @return List<Speaker> The Speaker entity
     */
    public List<Speaker> loadSpeakerListById(List<java.lang.Long> ids){
       return null;
    }
    
    /**
     * Loads the Speaker entity which is related to an instance of
     * Speaker and its dependent one to many objects
     * @param Long id
     * @return Speaker The Speaker entity
     */
    public Speaker loadFullFirstLevelSpeaker(java.lang.Long id) {
        List list = getJpaTemplate().find(
                     "SELECT speaker FROM Speaker speaker "
                     + " LEFT JOIN speaker.speakerPresentationSpeakerIds "   
                     + " WHERE speaker.id = "+id
               );
         if (list!=null && !list.isEmpty())
            return (Speaker)list.get(0);
         return null;
    	//return null;//(Speaker) getJpaTemplate().queryForObject("loadFullFirstLevelSpeaker", id);
    }

    /**
     * Loads the Speaker entity which is related to an instance of
     * Speaker
     * @param Speaker speaker
     * @return Speaker The Speaker entity
     */
    public Speaker loadFullFirstLevelSpeaker(Speaker speaker) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append ("SELECT speaker FROM Speaker speaker ");
        if (speaker.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" speaker.id = "+ speaker.getId() + " ");
         }
	        	List list = getJpaTemplate().find(query.toString());
        if (list!=null && !list.isEmpty())
           return (Speaker)list.get(0);    
        return null;
    }  
     
    /**
     * Loads the Speaker entity which is related to an instance of
     * Speaker and its dependent objects one to many
     * @param Long id
     * @return Speaker The Speaker entity
     */
    public Speaker loadFullSpeaker(Long id) {
    	return null;//(Speaker)getJpaTemplate().queryForObject("loadFullSpeaker", id);
    }

    /**
     * Searches a list of Speaker entity 
     * @param Speaker speaker
     * @return List
     */
    public List<Speaker> searchPrototypeSpeaker(Speaker speaker) {      
       return searchPrototype (speaker, null);            
    }  
    
    protected List<Speaker> searchPrototype (Speaker speaker, Integer maxResults) { 
       Query hquery = getEntityManager().createQuery(getSpeakerSearchEqualQuery (speaker));
       if (maxResults!=null)
          hquery.setMaxResults(maxResults);
       return hquery.getResultList();         
    }        
    
    public List<Speaker> searchPrototypeSpeaker (List<Speaker> speakers) {
       return searchPrototype (speakers, null);  
    }

    protected List<Speaker> searchPrototype (List<Speaker> speakers, Integer maxResults) {    
       //TODO convert setMaxResults in JPA if (maxResults!=null)
       //   getJpaTemplate().setMaxResults(maxResults);
       return getJpaTemplate().find(getSpeakerSearchEqualQuery (null, speakers));            
    }    

    public List<Speaker> searchDistinctPrototypeSpeaker (Speaker speakerMask, List<Speaker> speakers) {
        return getJpaTemplate().find(getSpeakerSearchEqualQuery (speakerMask, speakers));    
    }
         
	/**
     * Searches a list of Speaker entity 
     * @param Speaker speakerPositive
     * @param Speaker speakerNegative
     * @return List
     */
    public List<Speaker> searchPrototypeSpeaker(Speaker speakerPositive, Speaker speakerNegative) {
	    return getJpaTemplate().find(getSpeakerSearchEqualQuery (speakerPositive, speakerNegative));  
    }

    /**
    * return a jql query search on a Speaker prototype
    */
    protected String getSpeakerSearchEqualQuery (Speaker speaker) {
        StringBuffer query = new StringBuffer();
        StringBuffer queryWhere = new StringBuffer();
        query.append ("SELECT speaker FROM Speaker speaker ");
        queryWhere.append (getSpeakerSearchEqualWhereQueryChunk(speaker, false));   
	    return getHQuery(query.toString(), queryWhere.toString());
    }


    /**
    * return a jql search for a list of Speaker prototype
    */
    protected String getSpeakerSearchEqualQuery (Speaker speakerMask, List<Speaker> speakers) {
        boolean isOrSet = false;
        StringBuffer query = new StringBuffer();
        if (speakerMask !=null)
           query.append (getSpeakerMaskWhat (speakerMask));
        query.append (" FROM Speaker speaker ");
        StringBuffer queryWhere = new StringBuffer();
        for (Speaker speaker : speakers) {
           if (!isAllNull(speaker)) {        
	           queryWhere.append (getQueryOR (isOrSet));
	           isOrSet = true;        
	           queryWhere.append (" ("+getSpeakerSearchEqualWhereQueryChunk(speaker, false)+") ");
           }
        }
	    return getHQuery(query.toString(), queryWhere.toString());
    }	
    
    protected String getSpeakerMaskWhat (Speaker speakerMask) {
        boolean isCommaSet = false;
        StringBuffer query = new StringBuffer("SELECT DISTINCT ");
        if (speakerMask.getId() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" id ");
        }
        if (speakerMask.getConferenceMemberId() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" conferenceMemberId ");
        }
        if (speakerMask.getBio() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" bio ");
        }
        if (speakerMask.getPhoto() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" photo ");
        }
        if (speakerMask.getWebSiteUrl() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" webSiteUrl ");
        }
        if (speakerMask.getSponsorId() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" sponsorId ");
        }
        if (!isCommaSet)
           return "";
	    return query.toString();        
    }
    
    protected String getSpeakerSearchEqualWhereQueryChunk (Speaker speaker, boolean isAndSet) {
        StringBuffer query = new StringBuffer();
        if (speaker.getId() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" speaker.id = "+ speaker.getId() + " ");
        }
        if (speaker.getConferenceMemberId() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" speaker.conferenceMemberId = "+ speaker.getConferenceMemberId() + " ");
        }
        if (speaker.getBio() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" speaker.bio = '"+ speaker.getBio()+"' ");
        }
        if (speaker.getPhoto() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" speaker.photo = '"+ speaker.getPhoto()+"' ");
        }
        if (speaker.getWebSiteUrl() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" speaker.webSiteUrl = '"+ speaker.getWebSiteUrl()+"' ");
        }
        if (speaker.getSponsorId() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" speaker.sponsorId = "+ speaker.getSponsorId() + " ");
        }
	    return query.toString();
    }   
     
    /**
    * return a jql search on a Speaker prototype with positive and negative beans
    */
    protected String getSpeakerSearchEqualQuery (Speaker speakerPositive, Speaker speakerNegative) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" SELECT speaker FROM Speaker speaker ");
        if (speakerPositive!=null && speakerPositive.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" speaker.id = "+ speakerPositive.getId() + " ");
        } else if (speakerNegative.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" speaker.id is null ");
        }
        if (speakerPositive!=null && speakerPositive.getConferenceMemberId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" speaker.conferenceMemberId = "+ speakerPositive.getConferenceMemberId() + " ");
        } else if (speakerNegative.getConferenceMemberId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" speaker.conferenceMemberId is null ");
        }
        if (speakerPositive!=null && speakerPositive.getBio() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" speaker.bio = '"+ speakerPositive.getBio()+"' ");
        } else if (speakerNegative.getBio() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" speaker.bio is null ");
        }
        if (speakerPositive!=null && speakerPositive.getPhoto() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" speaker.photo = '"+ speakerPositive.getPhoto()+"' ");
        } else if (speakerNegative.getPhoto() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" speaker.photo is null ");
        }
        if (speakerPositive!=null && speakerPositive.getWebSiteUrl() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" speaker.webSiteUrl = '"+ speakerPositive.getWebSiteUrl()+"' ");
        } else if (speakerNegative.getWebSiteUrl() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" speaker.webSiteUrl is null ");
        }
        if (speakerPositive!=null && speakerPositive.getSponsorId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" speaker.sponsorId = "+ speakerPositive.getSponsorId() + " ");
        } else if (speakerNegative.getSponsorId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" speaker.sponsorId is null ");
        }
	    return query.toString();
    }
    
    /**
     * Load a paginated list of Speaker entity dependent of pagination criteria
     * @param PaginationCriteria paginationCriteria
     * @return List
     */
    public List <Speaker> loadPaginatedSpeaker (Speaker speaker, PaginationCriteria paginationCriteria) {
	    List<Long> page = loadPaginatedSpeakerIdentitiesFromStartPositionId(speaker, paginationCriteria);
    	int start = paginationCriteria.getNumberOfRowsReturned()*(paginationCriteria.getPageNumber());
    	int max = page.size();
    	if (start<max) {
    	   List<Long> returnPage = page.subList(start, max);	
           StringBuffer query = new StringBuffer();
           query.append (" SELECT speaker FROM Speaker speaker ");      
	       query.append(" where speaker.id in (");
	       for (Iterator iter = returnPage.iterator(); iter.hasNext();) {
			  Long id = (Long) iter.next();
			  query.append(id.toString());
		      if (iter.hasNext())
			     query.append(",");
		   }
	       query.append(") ");
	       return getJpaTemplate().find(query.toString()); 
    	} 
        return new ArrayList<Speaker>();
    }      

    protected List<Long> loadPaginatedSpeakerIdentitiesFromStartPositionId (Speaker speaker, PaginationCriteria paginationCriteria) {
       boolean isWhereSet = false;
       StringBuffer query = new StringBuffer();
       query.append ("select speaker.id ");
       query.append (getSpeakerSearchEqualQuery (speaker));
       if (paginationCriteria.getOrderList()!=null) {
    	   query.append(" order by "+paginationCriteria.getOrderList());
       }
       int maxResult = paginationCriteria.getNumberOfRowsReturned()*(1+paginationCriteria.getPageNumber());
       List<Long> set = getEntityManager().createNamedQuery(query.toString()).setMaxResults(maxResult).getResultList();
       return set;
    }

    //TODO put in upper class
    
	protected String getQueryWHERE_AND(boolean isWhereSet) {
		if (isWhereSet)
			return " AND ";
		return " WHERE ";
	}

	protected String getQueryCommaSet(boolean isWhereSet) {
		if (isWhereSet)
			return " , ";
		return " SET ";
	}

	protected static String getQuerySelectComma(boolean isSelectSet) {
		if (isSelectSet)
			return " , ";
		return " select ";
	}

	protected static String getQueryBLANK_AND(boolean isBlankSet) {
		if (isBlankSet)
			return " AND ";
		return " ";
	}

	protected static String getQueryBLANK_COMMA(boolean isBlankSet) {
		if (isBlankSet)
			return " , ";
		return " ";
	}

	protected String getQueryComma(boolean isCommaSet) {
		return (isCommaSet) ? " , " : "";
	}

	protected String getQueryOR(boolean isOrSet) {
		return (isOrSet) ? " OR " : "";
	}

	protected String getQueryAND(boolean isAndSet) {
		return (isAndSet) ? " AND " : "";
	}

	protected String getHQuery (String what, String where) {
        String whereWord = getWhere (where);
	    return what+whereWord+where;
    }
    
    protected String getWhere (String where) {
        return (where==null || where.trim().equals("") || where.trim()==null)?"":" WHERE ";
    }   
    protected EntityManager getEntityManager () {
        return EntityManagerFactoryUtils.getTransactionalEntityManager(getJpaTemplate().getEntityManagerFactory());    
    }
    
    protected void convertTransientReferenceToNull (Speaker speaker) {
	   speaker.setConferenceMemberId ((ConferenceMember)null);
	   speaker.setSponsorId ((Sponsor)null);
    }
}
