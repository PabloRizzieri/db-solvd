package dao.MyBatis;


import dao.IProvidersDAO;
import model.Providers;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class ProvidersDAO implements IProvidersDAO {
    private static final Logger LOGGER = LogManager.getLogger(ProvidersDAO.class);
    private static IProvidersDAO providersMapper;
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
    public Providers getEntityById(int id) {
        providersMapper = sqlSessionFactory.openSession().getMapper(IProvidersDAO.class);
        Providers providers = providersMapper.getEntityById(id);
        return providers;
    }

    @Override
    public void insertEntity(Providers entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.insert("insertProviders", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void updateEntity(Providers entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.update("updateProviders", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void removeEntity(Providers entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.delete("removeProviders", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public List<Providers> getEntities() {
        List<Providers> providers;
        try {
            sqlSession = sqlSessionFactory.openSession();
            providers = sqlSession.selectList("getProviders");
        } finally {
            sqlSession.close();
        }
        return providers;
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