package test;

import org.testng.annotations.Test;
import org.testng.Assert;

import dao.CompanyDAO;
import dao.Impl.CompanyDAOImpl;
import entity.Company;

import java.sql.SQLException;
import java.util.Collection;

public class CompanyTest {

    @Test
    public void addCompany() throws SQLException {
        CompanyDAO companyDAO = new CompanyDAOImpl();
        Company newCompany = new Company("Test company.add", "Test address company.add");
        companyDAO.addCompany(newCompany);

        Company testCompany = companyDAO.getCompanyById(newCompany.getId());
        Assert.assertTrue(newCompany.fullyEquals(testCompany));
    }

    @Test
    public void updateCompany() throws SQLException {
        CompanyDAO companyDAO = new CompanyDAOImpl();
        Company newCompany = new Company("Test company.update", "Test address company.update");
        companyDAO.addCompany(newCompany);

        newCompany.setName(newCompany.getName() + " 2");
        newCompany.setAddress(newCompany.getAddress() + " 2");
        companyDAO.updateCompany(newCompany);

        Company testCompany = companyDAO.getCompanyById(newCompany.getId());
        Assert.assertTrue(newCompany.fullyEquals(testCompany));
    }

    @Test
    public void deleteCompany() throws SQLException {
        CompanyDAO companyDAO = new CompanyDAOImpl();
        Company newCompany = new Company("Test company.delete", "Test address company.delete");
        companyDAO.addCompany(newCompany);

        companyDAO.deleteCompany(newCompany);
        Collection<Company> companies = companyDAO.getAllCompanies();
        for (Company testCompany : companies) {
            Assert.assertFalse(newCompany.fullyEquals(testCompany));
        }
    }

    @Test
    public void getCompaniesByName() throws SQLException {
        CompanyDAO companyDAO = new CompanyDAOImpl();
        Company newCompany = new Company("Test company.searchByName",
                                        "Test address company.searchByName");
        companyDAO.addCompany(newCompany);

        Collection<Company> companies = companyDAO.getCompaniesByName(newCompany.getName());
        Assert.assertTrue(companies.size() == 1);
        for (Company testCompany : companies) {
            Assert.assertTrue(newCompany.fullyEquals(testCompany));
        }
    }
}
