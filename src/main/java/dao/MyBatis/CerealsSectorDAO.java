package dao.MyBatis;

import dao.ICerealsSectorDAO;
import model.CerealsSector;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class CerealsSectorDAO implements ICerealsSectorDAO {
    private static final Logger LOGGER = LogManager.getLogger(CerealsSectorDAO.class);
    private static ICerealsSectorDAO cerealSectorMapper;
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
    public CerealsSector getEntityById(int id) {
        cerealSectorMapper = sqlSessionFactory.openSession().getMapper(ICerealsSectorDAO.class);
        CerealsSector cerealsSector = cerealSectorMapper.getEntityById(id);
        return cerealsSector;
    }

    @Override
    public void insertEntity(CerealsSector entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.insert("insertCerealsSector", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void updateEntity(CerealsSector entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.update("updateCerealsSector", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void removeEntity(CerealsSector entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.delete("removeCerealsSector", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public List<CerealsSector> getEntities() {
        List<CerealsSector> cerealsSectors;
        try {
            sqlSession = sqlSessionFactory.openSession();
            cerealsSectors = sqlSession.selectList("getCerealsSector");
        } finally {
            sqlSession.close();
        }
        return cerealsSectors;
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
