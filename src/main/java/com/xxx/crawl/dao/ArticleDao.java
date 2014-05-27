package com.xxx.crawl.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SharedSessionContract;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.mapping.Map;
import org.springframework.stereotype.Repository;

import com.xxx.crawl.common.dto.ArticleReportDTO;
import com.xxx.crawl.domain.Demo;
import com.xxx.crawl.dto.ArticleInfoDTO;

@Repository("articleDao")
public class ArticleDao extends BaseHibernateDao<Demo, Long> {
	public void saveArticles(List<ArticleInfoDTO> articles ) {
		for(ArticleInfoDTO article : articles) {
			this.getSession().saveOrUpdate(article);
		}
	}
	public List<ArticleInfoDTO> queryReportData() {
	SharedSessionContract session=this.getSession();
	/*	Query  query = session.createQuery("select this_.devGroup as groupName, sum(this_.readTimes) as sumReadTimes,  count(this_.articleId) as articleSum from cw_article this_ group by this_.devGroup");
		query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		List results = query.list();*/
	
		
		
        /*   projList.add(Projections.groupProperty("devGroup"),"group");
      projList.add(Projections.sum("readTimes"),"sumReadTimes");
        projList.add(Projections.sum("commentTimes"),);
        projList.add(Projections.count("articleId"),"articleSum");
        crit.setProjection(projList);*/
        @SuppressWarnings("unchecked")
        Criteria crit = session.createCriteria(ArticleInfoDTO.class);
        ProjectionList projList = Projections.projectionList();
		List<ArticleInfoDTO> results= crit.list();
        System.out.println(results.get(0).getTitle());
        System.out.println(results);
      
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
