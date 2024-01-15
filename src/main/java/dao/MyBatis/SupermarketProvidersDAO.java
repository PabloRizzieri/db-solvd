package dao.MyBatis;

import dao.ISupermarketProvidersDAO;
import model.SupermarketProviders;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class SupermarketProvidersDAO implements ISupermarketProvidersDAO {
    private static final Logger LOGGER = LogManager.getLogger(SupermarketProvidersDAO.class);
    private static ISupermarketProvidersDAO supermarketProvidersMapper;
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
    public SupermarketProviders getEntityById(int id) {
        supermarketProvidersMapper = sqlSessionFactory.openSession().getMapper(ISupermarketProvidersDAO.class);
        SupermarketProviders supermarketProviders = supermarketProvidersMapper.getEntityById(id);
        return supermarketProviders;
    }

    @Override
    public void insertEntity(SupermarketProviders entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.insert("insertSupermarketProviders", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void updateEntity(SupermarketProviders entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.update("updateSupermarketProviders", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void removeEntity(SupermarketProviders entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.delete("removeSupermarketProviders", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public List<SupermarketProviders> getEntities() {
        List<SupermarketProviders> supermarketProviders;
        try {
            sqlSession = sqlSessionFactory.openSession();
            supermarketProviders = sqlSession.selectList("getSupermarketProviders");
        } finally {
            sqlSession.close();
        }
        return supermarketProviders;
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

    @Override
    public List<SupermarketProviders> getProvidersBySupermarketID(int id){
        return null;
    }
}