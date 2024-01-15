package dao.MyBatis;

import dao.IFruitsSectorDAO;
import dao.IMeatsSectorDAO;
import model.FruitsSector;
import model.MeatsSector;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class MeatsSectorDAO implements IMeatsSectorDAO {
    private static final Logger LOGGER = LogManager.getLogger(MeatsSectorDAO.class);
    private static IMeatsSectorDAO meatsSectorMapper;
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
    public MeatsSector getEntityById(int id) {
        meatsSectorMapper = sqlSessionFactory.openSession().getMapper(IMeatsSectorDAO.class);
        MeatsSector meatsSector = meatsSectorMapper.getEntityById(id);
        return meatsSector;
    }

    @Override
    public void insertEntity(MeatsSector entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.insert("insertMeatsSector", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void updateEntity(MeatsSector entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.update("updateMeatsSector", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void removeEntity(MeatsSector entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.delete("removeMeatsSector", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public List<MeatsSector> getEntities() {
        List<MeatsSector> meatsSectors;
        try {
            sqlSession = sqlSessionFactory.openSession();
            meatsSectors = sqlSession.selectList("getMeatsSector");
        } finally {
            sqlSession.close();
        }
        return meatsSectors;
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
