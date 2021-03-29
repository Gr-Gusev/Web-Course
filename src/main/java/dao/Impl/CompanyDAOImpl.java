package dao.Impl;

import dao.CompanyDAO;
import entity.Company;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateSessionFactoryUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class CompanyDAOImpl implements CompanyDAO {

    public void addCompany(Company company) throws SQLException {
        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(company);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    public void updateCompany(Company company) throws SQLException {
        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(company);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    public Company getCompanyById(Long company_id) throws SQLException {
        Session session = null;
        Company company = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        company = (Company) session.load(Company.class, company_id);
        if (session.isOpen()) {
            session.close();
        }
        return company;
    }

    public Collection<Company> getAllCompanies() throws SQLException {
        Session session = null;
        List<Company> companies = new ArrayList<Company>();
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Company> query = session.createQuery("select d from Company d", Company.class);
        companies = (List<Company>)query.list();
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return companies;
    }

    public void deleteCompany(Company company) throws SQLException {
        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(company);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    public Collection<Company> getCompaniesByName(String name) throws SQLException {
        Collection<Company> companies = this.getAllCompanies();
        Collection<Company> ret = new HashSet<Company>(5);
        for (Company company : companies) {
            if (name.equals(company.getName()))
                ret.add(company);
        }
        return ret;
    }
}
