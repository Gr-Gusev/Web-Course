package dao;

import entity.Company;

import java.sql.SQLException;
import java.util.Collection;

public interface CompanyDAO {

    public void addCompany(Company company) throws SQLException;

    public void updateCompany(Company company) throws SQLException;

    public Company getCompanyById(Long company_id) throws SQLException;

    public Collection<Company> getAllCompanies() throws SQLException;

    public void deleteCompany(Company company) throws SQLException;

    public Collection<Company> getCompaniesByName(String name) throws SQLException;

}
