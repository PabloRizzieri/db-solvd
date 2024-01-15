package dao.MyBatis;

import dao.IDepartmentsDAO;
import model.Departments;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class DepartmentsDAO implements IDepartmentsDAO {
    private static final Logger LOGGER = LogManager.getLogger(DepartmentsDAO.class);
    private static IDepartmentsDAO departmentsMapper;
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
    public Departments getEntityById(int id) {
        departmentsMapper = sqlSessionFactory.openSession().getMapper(IDepartmentsDAO.class);
        Departments departments = departmentsMapper.getEntityById(id);
        return departments;
    }

    @Override
    public void insertEntity(Departments entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.insert("insertDepartments", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void updateEntity(Departments entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.update("updateDepartments", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void removeEntity(Departments entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.delete("removeDepartments", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public List<Departments> getEntities() {
        List<Departments> departments;
        try {
            sqlSession = sqlSessionFactory.openSession();
            departments = sqlSession.selectList("getDepartments");
        } finally {
            sqlSession.close();
        }
        return departments;
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
