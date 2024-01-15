package dao.MyBatis;

import dao.IFruitsSectorDAO;
import model.FruitsSector;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class FruitsSectorDAO implements IFruitsSectorDAO {
    private static final Logger LOGGER = LogManager.getLogger(FruitsSectorDAO.class);
    private static IFruitsSectorDAO fruitsSectorMapper;
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
    public FruitsSector getEntityById(int id) {
        fruitsSectorMapper = sqlSessionFactory.openSession().getMapper(IFruitsSectorDAO.class);
        FruitsSector fruitsSector = fruitsSectorMapper.getEntityById(id);
        return fruitsSector;
    }

    @Override
    public void insertEntity(FruitsSector entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.insert("insertFruitsSector", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void updateEntity(FruitsSector entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.update("updateFruitsSector", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void removeEntity(FruitsSector entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.delete("removeFruitsSector", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public List<FruitsSector> getEntities() {
        List<FruitsSector> fruitsSectors;
        try {
            sqlSession = sqlSessionFactory.openSession();
            fruitsSectors = sqlSession.selectList("getFruitsSector");
        } finally {
            sqlSession.close();
        }
        return fruitsSectors;
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
