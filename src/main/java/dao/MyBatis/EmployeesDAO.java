package dao.MyBatis;

import dao.IDepartmentsDAO;
import dao.IEmployeesDAO;
import model.Departments;
import model.Employees;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class EmployeesDAO implements IEmployeesDAO {
    private static final Logger LOGGER = LogManager.getLogger(EmployeesDAO.class);
    private static IEmployeesDAO employeesMapper;
    private static SqlSession sqlSession;
    private static final SqlSessionFactory sqlSessionFactory;
    private static Reader reader = null;

    static {
        try {
            reader = Resources.getResourceAsReader("mybatis.config.xml");
        } catch (IOException e) {
            LOGGER.info(e);
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

    @Override
    public Employees getEntityById(int id) {
        employeesMapper = sqlSessionFactory.openSession().getMapper(IEmployeesDAO.class);
        Employees employees = employeesMapper.getEntityById(id);
        return employees;
    }

    @Override
    public void insertEntity(Employees entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.insert("insertEmployees", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void updateEntity(Employees entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.update("updateEmployees", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void removeEntity(Employees entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.delete("removeEmployees", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public List<Employees> getEntities() {
        List<Employees> employees;
        try {
            sqlSession = sqlSessionFactory.openSession();
            employees = sqlSession.selectList("getEmployees");
        } finally {
            sqlSession.close();
        }
        return employees;
    }


    //Empty implementation

    @Override
    public void insertEntity(Object o) {

    }

    @Override
    public void updateEntity(Object o, int i) {

    }

    @Override
    public void removeEntity(Object o) {

    }
}
