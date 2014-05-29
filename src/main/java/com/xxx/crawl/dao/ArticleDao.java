package com.xxx.crawl.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SharedSessionContract;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.xxx.crawl.common.dto.ArticleReportDTO;
import com.xxx.crawl.common.dto.QueryCreteria;
import com.xxx.crawl.domain.Demo;
import com.xxx.crawl.dto.ArticleInfoDTO;

@Repository("articleDao")
public class ArticleDao extends BaseHibernateDao<Demo, Long> {
	public void saveArticles(List<ArticleInfoDTO> articles ) {
		for(ArticleInfoDTO article : articles) {
			this.getSession().saveOrUpdate(article);
		}
	}
	public List<ArticleInfoDTO> queryReportData(QueryCreteria dto) {
	SharedSessionContract session=this.getSession();
	/*	Query  query = session.createQuery("select this_.devGroup as groupName, sum(this_.readTimes) as sumReadTimes,  count(this_.articleId) as articleSum from cw_article this_ group by this_.devGroup");
		query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		List results = query.list();*/
	
		
	/*ProjectionList projList = Projections.projectionList();
      projList.add(Projections.sum("readTimes"));
        projList.add(Projections.sum("commentTimes"));
        projList.add(Projections.count("articleId"));
        crit.setProjection(projList);*/
        @SuppressWarnings("unchecked")
        Criteria crit = session.createCriteria(ArticleInfoDTO.class);
        if(dto==null){
            crit.setFetchMode("categorys", org.hibernate.FetchMode.JOIN);
    		List<ArticleInfoDTO> results= crit.list();
    		return results;
        }
        if(dto.getAuthor()!=null){
        	 crit.add(Restrictions.eq("author",dto.getAuthor()));
        }
        if(dto.getGroup()!=null){
        	crit.add(Restrictions.eq("devGroup", dto.getGroup()));
        }
        if(dto.getPublishDateFrom()!=null&&dto.getPublishDateTo()!=null){
        	crit.add(Expression.between("updateDate", dto.getPublishDateFrom(), dto.getPublishDateTo()));
        }
        crit.setFetchMode("categorys", org.hibernate.FetchMode.JOIN);
		List<ArticleInfoDTO> results= crit.list();
        return results;
	}
	public List<ArticleReportDTO> queryArticleData() {
		SharedSessionContract session=this.getSession();
			Criteria crit = session.createCriteria(ArticleInfoDTO.class);
	        ProjectionList projList = Projections.projectionList();
	        projList.add(Projections.groupProperty("devGroup"),"group");
	        projList.add(Projections.sum("readTimes"),"sumReadTimes");
	      //  projList.add(Projections.sum("commentTimes"),);
	        projList.add(Projections.count("articleId"),"articleSum");
	        crit.setProjection(projList);
	        @SuppressWarnings("unchecked")
			List results= crit.list();
	        System.out.println(results.size());
	        System.out.println(results);
	      
	        return results;
		}
}
