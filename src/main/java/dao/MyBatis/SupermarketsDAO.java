package dao.MyBatis;

import dao.ISupermarketsDAO;
import model.Supermarkets;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class SupermarketsDAO implements ISupermarketsDAO {
    private static final Logger LOGGER = LogManager.getLogger(SupermarketsDAO.class);
    private static ISupermarketsDAO supermarketMapper;
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
    public Supermarkets getEntityById(int id) {
        supermarketMapper = sqlSessionFactory.openSession().getMapper(ISupermarketsDAO.class);
        Supermarkets supermarkets = supermarketMapper.getEntityById(id);
        return supermarkets;
    }

    @Override
    public void insertEntity(Supermarkets entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.insert("insertSupermarkets", entity);
            sqlSession.commit();
            System.out.println("Inserting");
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void updateEntity(Supermarkets entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.update("updateSupermarkets", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void removeEntity(Supermarkets entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.delete("removeSupermarkets", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public List<Supermarkets> getEntities() {
        List<Supermarkets> supermarkets;
        try {
            sqlSession = sqlSessionFactory.openSession();
            supermarkets = sqlSession.selectList("getSupermarkets");
        } finally {
            sqlSession.close();
        }
        return supermarkets;
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
