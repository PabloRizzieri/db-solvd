package dao.MyBatis;

import dao.ISupermarketsDAO;
import dao.ISupervisorsDAO;
import model.Supermarkets;
import model.Supervisors;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class SupervisorsDAO implements ISupervisorsDAO {
    private static final Logger LOGGER = LogManager.getLogger(SupervisorsDAO.class);
    private static ISupervisorsDAO supervisorMapper;
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
    public Supervisors getEntityById(int id) {
        supervisorMapper = sqlSessionFactory.openSession().getMapper(ISupervisorsDAO.class);
        Supervisors supervisors = supervisorMapper.getEntityById(id);
        return supervisors;
    }

    @Override
    public void insertEntity(Supervisors entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.insert("insertSupervisors", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void updateEntity(Supervisors entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.update("updateSupervisors", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void removeEntity(Supervisors entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.delete("removeSupervisors", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public List<Supervisors> getEntities() {
        List<Supervisors> supervisors;
        try {
            sqlSession = sqlSessionFactory.openSession();
            supervisors = sqlSession.selectList("getSupervisors");
        } finally {
            sqlSession.close();
        }
        return supervisors;
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
