package com.christian.TIAMS.dao;

import com.christian.TIAMS.dao.base.AbstractGenericDAO;
import com.christian.TIAMS.model.Company;

import javax.persistence.TypedQuery;
import java.util.List;

public class CompanyDAO extends AbstractGenericDAO<Company> {
    public CompanyDAO() {
        super(Company.class);
    }

    @Override
    public Company update(Company object) {
        Company currentObject = get(object.getCompanyID());
        currentObject.setName(object.getName());
        return super.update(currentObject);
    }

    public Company getByName(String name){
        Company company = null;
        entityManager.getTransaction().begin();
        String jpql = "select c from Company c where c.name = :name";
        TypedQuery<Company> query = entityManager.createQuery(jpql, Company.class);
        query.setParameter("name", name);
        try {
            company = query.getSingleResult();
        } finally {
            entityManager.getTransaction().commit();
            return company;
        }
    }

    public List<Company> getCompanyNames(){
        String jpql = "select c.name from Company c";
        List<Company> companyList = (List<Company>)entityManager.createQuery(jpql).getResultList();
        return companyList;
    }
}
