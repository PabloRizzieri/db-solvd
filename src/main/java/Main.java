import com.fasterxml.jackson.databind.ObjectMapper;
import dao.*;
import dao.JDBC.*;
import dao.MyBatis.SupermarketsDAO;
import enums.DAOType;
import enums.FactoryType;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parsers.models.Employee;
import parsers.models.Supermarket;
import utils.DAOFactoryGenerator;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws SQLException {
        /* ----- JDBC OPERATIONS TEST -----

        SupermarketsDAO supermarketsDAO = new SupermarketsDAO();
        EmployeesDAO employeesDAO = new EmployeesDAO();
        DepartmentsDAO departmentsDAO = new DepartmentsDAO();
        ProductsDAO productsDAO = new ProductsDAO();
        ProvidersDAO providersDAO = new ProvidersDAO();

        // ##############################
        // Create Operations
        // ##############################

        // Create and insert a Supermarket entity into the database

        Supermarkets supermarketToAdd = new Supermarkets();
        supermarketToAdd.setName("VEA");
        supermarketsDAO.insertEntity(supermarketToAdd);

        // Create and insert a Department entity into the database

        Departments departmentsToAdd = new Departments();
        departmentsToAdd.setDepartmentName("Administration");
        departmentsToAdd.setDepartmentTask("Administrate");
        departmentsToAdd.setSupermarket_id(10);
        departmentsDAO.insertEntity(departmentsToAdd);


        // Create and insert an Employee entity into the database
        Employees employeeToAdd = new Employees();
        employeeToAdd.setFirstName("Mark");
        employeeToAdd.setLastName("Douglas");
        employeeToAdd.setAge(25);
        employeeToAdd.setSupermarket_id(10);
        employeeToAdd.setDepartment_id(3);
        employeesDAO.insertEntity(employeeToAdd);

        // Create and insert a Provider entity into the database
        Providers providersToAdd = new Providers();
        providersToAdd.setProviderName("General Provider");
        providersToAdd.setProviderBranch("General Stuff");
        providersDAO.insertEntity(providersToAdd);

        // Create and insert a Product entity into the database
        Products productsToAdd = new Products();
        productsToAdd.setProductName("Apple");
        productsToAdd.setPrice(9);
        productsToAdd.setCategory("Fruits");
        productsToAdd.setProvider_id(3);
        productsToAdd.setSupermarket_id(10);
        productsDAO.insertEntity(productsToAdd);

        // ##############################
        // Read Operations
        // ##############################

        // Reading Supermarket based on the ID

        int supermarketIdToRead = 10; // Assuming that the id of the supermarket its 10
        Supermarkets supermarketToRead = supermarketsDAO.getEntityById(supermarketIdToRead);
        System.out.println("Data of the Supermarket: " + supermarketToRead);

        // Reading Department based on the ID

        int departmentIdToRead = 3;
        Departments departmentsToRead = departmentsDAO.getEntityById(departmentIdToRead);
        System.out.println("Data of the Department: " + departmentsToRead);


        // Reading Employee based on the ID

        int employeeIdToRead = 5; //Assuming that the id of the employee its 2
        Employees employeeToRead = employeesDAO.getEntityById(employeeIdToRead);
        System.out.println("Data of the Employee: " + employeeToRead);

        // Reading Provider based on the ID

        int providerIdToRead = 1;
        Providers providerToRead = providersDAO.getEntityById(providerIdToRead);
        System.out.println("Data of the Provider: " + providerToRead);

        // Reading Product based on the ID

        int productIdToRead = 3;
        Products productsToRead = productsDAO.getEntityById(productIdToRead);
        System.out.println("Data of the Product: " + productsToRead);

        // ##############################
        // Update Operations
        // ##############################

        // Supermarket Update
        Supermarkets supermarketToUpdate = supermarketsDAO.getEntityById(1);
        if (supermarketToUpdate != null){
            supermarketToUpdate.setName("Walmart");
            supermarketsDAO.updateEntity(supermarketToUpdate);
        } else {
            System.out.println("Data of the Supermarket: " + supermarketToRead);
        }

        // Department Update

        Departments departmentsToUpdate = departmentsDAO.getEntityById(1);
        if (departmentsToUpdate != null){
            departmentsToUpdate.setDepartmentName("Administration Department");
            departmentsDAO.updateEntity(departmentsToUpdate);
            System.out.println("Check new data: " + departmentsToUpdate);
        } else {
            System.out.println("Data of the Department: " + departmentsToRead);
        }

        // Employee Update

        Employees employeeToUpdate = employeesDAO.getEntityById(2);
        if (employeeToUpdate != null){
            employeeToUpdate.setFirstName("Lucas");
            employeeToUpdate.setLastName("Marlon");
            employeesDAO.updateEntity(employeeToUpdate);
            System.out.println("New Employee: " + employeeToUpdate);
        } else {
            System.out.println("Old Employee: " + employeeToRead);
        }

        // Provider Update

        Providers providerToUpdate = providersDAO.getEntityById(1);
        if (providerToUpdate != null){
            providerToUpdate.setProviderName("New General Provider");
            providersDAO.updateEntity(providerToUpdate);
            System.out.println("New Provider name: " + providerToUpdate);
        } else {
            System.out.println("Old Provider: " + providerToRead);
        }

        // Product Update

        Products productsToUpdate = productsDAO.getEntityById(1);
        if (productsToUpdate != null) {
            productsToUpdate.setProductName("Banana");
            productsToUpdate.setPrice(15);
            productsDAO.updateEntity(productsToUpdate);
            System.out.println("New Product: " + productsToUpdate);
        } else {
            System.out.println("Old Product: " + productsToRead);
        }



        // ##############################
        // Delete Operations
        // ##############################

        // Supermarket (Deleting all the supermarkets)

        Supermarkets supermarketToDelete = supermarketsDAO.getEntityById(1);
        supermarketsDAO.removeEntity(supermarketToDelete);

        Supermarkets supermarketToDelete2 = supermarketsDAO.getEntityById(2);
        supermarketsDAO.removeEntity(supermarketToDelete2);

        Supermarkets supermarketToDelete3 = supermarketsDAO.getEntityById(3);
        supermarketsDAO.removeEntity(supermarketToDelete3);

        Supermarkets supermarketToDelete4 = supermarketsDAO.getEntityById(4);
        supermarketsDAO.removeEntity(supermarketToDelete4);

        Supermarkets supermarketToDelete5 = supermarketsDAO.getEntityById(5);
        supermarketsDAO.removeEntity(supermarketToDelete5);

        Supermarkets supermarketToDelete6 = supermarketsDAO.getEntityById(6);
        supermarketsDAO.removeEntity(supermarketToDelete6);

        Supermarkets supermarketToDelete7 = supermarketsDAO.getEntityById(7);
        supermarketsDAO.removeEntity(supermarketToDelete7);

        Supermarkets supermarketToDelete8 = supermarketsDAO.getEntityById(8);
        supermarketsDAO.removeEntity(supermarketToDelete8);

        Supermarkets supermarketToDelete9 = supermarketsDAO.getEntityById(9);
        supermarketsDAO.removeEntity(supermarketToDelete9);

        List<Supermarkets> supermarkets = supermarketsDAO.getEntities();
        supermarkets.forEach(LOGGER::error);


        Employees employeesToDelete = employeesDAO.getEntityById(6);
        employeesDAO.removeEntity(employeesToDelete);

        Departments departmentToDelete = departmentsDAO.getEntityById(3);
        departmentsDAO.removeEntity(departmentToDelete);

        Products productsToDelete = productsDAO.getEntityById(3);
        productsDAO.removeEntity(productsToDelete);

        */

        /*

        ISupermarketsDAO supermarketsDAO = (ISupermarketsDAO) DAOFactoryGenerator.createFactory(FactoryType.MYBATIS).getFactory(DAOType.Supermarkets);
        IEmployeesDAO employeesDAO = (IEmployeesDAO) DAOFactoryGenerator.createFactory(FactoryType.MYBATIS).getFactory(DAOType.Employees);
        IDepartmentsDAO departmentsDAO = (IDepartmentsDAO) DAOFactoryGenerator.createFactory(FactoryType.MYBATIS).getFactory(DAOType.Departments);
        IProvidersDAO providersDAO = (IProvidersDAO) DAOFactoryGenerator.createFactory(FactoryType.MYBATIS).getFactory(DAOType.Providers);
        IProductsDAO productsDAO = (IProductsDAO) DAOFactoryGenerator.createFactory(FactoryType.MYBATIS).getFactory(DAOType.Products);

        // ###############################
        // Create Operations using MyBatis approach
        // ###############################

        Supermarkets supermarketsToCreate = new Supermarkets();
        supermarketsToCreate.setName("Jumbo");
        supermarketsDAO.insertEntity(supermarketsToCreate);

        Departments departmentsToCreate = new Departments();
        departmentsToCreate.setDepartmentName("Deposit");
        departmentsToCreate.setDepartmentTask("Deposit Things");
        departmentsToCreate.setSupermarket_id(11);
        departmentsDAO.insertEntity(departmentsToCreate);

        Employees employeesToCreate = new Employees();
        employeesToCreate.setFirstName("Killian");
        employeesToCreate.setLastName("Mbappe");
        employeesToCreate.setAge(25);
        employeesToCreate.setDepartment_id(4);
        employeesToCreate.setSupermarket_id(11);
        employeesDAO.insertEntity(employeesToCreate);

        Providers providersToCreate = new Providers();
        providersToCreate.setProviderName("Meats Provider");
        providersToCreate.setProviderBranch("Meat");
        providersDAO.insertEntity(providersToCreate);

        Products productsToCreate = new Products();
        productsToCreate.setProductName("Chicken");
        productsToCreate.setPrice(29);
        productsToCreate.setCategory("Meats");
        productsToCreate.setProvider_id(4);
        productsToCreate.setSupermarket_id(11);
        productsDAO.insertEntity(productsToCreate);


        // ###############################
        // Read Operations using MyBatis approach
        // ###############################

        int supermarketIdToRead = 11;
        Supermarkets supermarketsToRead = supermarketsDAO.getEntityById(supermarketIdToRead);
        System.out.println("Data of the Supermarket: " + supermarketsToRead);

        int departmentIdToRead = 4;
        Departments departmentsToRead = departmentsDAO.getEntityById(departmentIdToRead);
        System.out.println("Data of the Department: " + departmentsToRead);

        int employeeIdToRead = 11;
        Employees employeesToRead = employeesDAO.getEntityById(employeeIdToRead);
        System.out.println("Data of the Employee: " + employeesToRead);

        int providerIdToRead = 4;
        Providers providersToRead = providersDAO.getEntityById(providerIdToRead);
        System.out.println("Data of the Provider: " + providersToRead);

        int productIdToRead = 4;
        Products productsToRead = productsDAO.getEntityById(productIdToRead);
        System.out.println("Data of the Product: " + productsToRead);


        // ###############################
        // Update Operations using MyBatis approach
        // ###############################


        Employees employeeToUpdate = employeesDAO.getEntityById(11);
        if (employeeToUpdate != null){
            employeeToUpdate.setLastName("Murphy");
            employeeToUpdate.setAge(31);
            employeesDAO.updateEntity(employeeToUpdate);
        } else {
            System.out.println("Data of the employee: " + employeesToRead);
        }

        Products productToUpdate = productsDAO.getEntityById(4);
        if (productToUpdate != null){
            productToUpdate.setProductName("Meat");
            productsDAO.updateEntity(productToUpdate);
        } else {
            System.out.println("Data of the product: " + productsToRead);
        }

        // ###############################
        // Delete Operations using MyBatis approach
        // ###############################

        Employees employeeToDelete = employeesDAO.getEntityById(11);
        employeesDAO.removeEntity(employeeToDelete);

        Products productToDelete = productsDAO.getEntityById(4);
        productsDAO.removeEntity(productToDelete);
        */


        // ###############################
        // Parsers Tests
        // ###############################

        /*
        // JAXB Parser

        File file = new File("src/main/resources/xml/supermarketExample.xml");

        try {
            JAXBContext context = JAXBContext.newInstance(Supermarket.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Supermarket supermarket = (Supermarket) unmarshaller.unmarshal(file);
            System.out.println(supermarket);
            File outputXmlFile = new File("src/main/resources/xml/output.xml");
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(supermarket, outputXmlFile);
            System.out.println("Object successfully written to XML file.");
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

        // Jackson


        File jsonFile = new File("src/main/resources/json/jacksonExample.json");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Supermarket supermarket = objectMapper.readValue(jsonFile, Supermarket.class);
            System.out.println(supermarket);
            File outputJsonFile = new File("src/main/resources/json/output.json");
            objectMapper.writeValue(outputJsonFile, supermarket);
            System.out.println("Object successfully written to JSON file.");
        } catch (Exception e) {
            e.printStackTrace();
        }

         */

}
}
