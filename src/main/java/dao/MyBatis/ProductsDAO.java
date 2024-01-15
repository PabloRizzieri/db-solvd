package dao.MyBatis;

import dao.IProductsDAO;
import model.Products;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class ProductsDAO implements IProductsDAO {
    private static final Logger LOGGER = LogManager.getLogger(ProductsDAO.class);
    private static IProductsDAO productsMapper;
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
    public Products getEntityById(int id) {
        productsMapper = sqlSessionFactory.openSession().getMapper(IProductsDAO.class);
        Products products = productsMapper.getEntityById(id);
        return products;
    }

    @Override
    public void insertEntity(Products entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.insert("insertProducts", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void updateEntity(Products entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.update("updateProducts", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void removeEntity(Products entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.delete("removeProducts", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public List<Products> getEntities() {
        List<Products> products;
        try {
            sqlSession = sqlSessionFactory.openSession();
            products = sqlSession.selectList("getProducts");
        } finally {
            sqlSession.close();
        }
        return products;
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
