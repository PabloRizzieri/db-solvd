package dao.MyBatis;

import dao.ICleaningsSectorDAO;
import model.CleaningsSector;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class CleaningsSectorDAO implements ICleaningsSectorDAO {
    private static final Logger LOGGER = LogManager.getLogger(CleaningsSectorDAO.class);
    private static ICleaningsSectorDAO cleaningsSectorMapper;
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
    public CleaningsSector getEntityById(int id) {
        cleaningsSectorMapper = sqlSessionFactory.openSession().getMapper(ICleaningsSectorDAO.class);
        CleaningsSector cleaningsSector = cleaningsSectorMapper.getEntityById(id);
        return cleaningsSector;
    }

    @Override
    public void insertEntity(CleaningsSector entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.insert("insertCleaningsSector", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void updateEntity(CleaningsSector entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.update("updateCleaningsSector", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void removeEntity(CleaningsSector entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.delete("removeCleaningsSector", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public List<CleaningsSector> getEntities() {
        List<CleaningsSector> cleaningsSectors;
        try {
            sqlSession = sqlSessionFactory.openSession();
            cleaningsSectors = sqlSession.selectList("getCleaningsSector");
        } finally {
            sqlSession.close();
        }
        return cleaningsSectors;
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

