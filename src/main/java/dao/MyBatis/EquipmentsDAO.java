package dao.MyBatis;

import dao.IEquipmentsDAO;
import model.Equipments;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class EquipmentsDAO implements IEquipmentsDAO {
    private static final Logger LOGGER = LogManager.getLogger(EquipmentsDAO.class);
    private static IEquipmentsDAO equipmentsMapper;
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
    public Equipments getEntityById(int id) {
        equipmentsMapper = sqlSessionFactory.openSession().getMapper(IEquipmentsDAO.class);
        Equipments equipments = equipmentsMapper.getEntityById(id);
        return equipments;
    }

    @Override
    public void insertEntity(Equipments entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.insert("insertEquipments", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void updateEntity(Equipments entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.update("updateEquipments", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void removeEntity(Equipments entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.delete("removeEquipments", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public List<Equipments> getEntities() {
        List<Equipments> equipments;
        try {
            sqlSession = sqlSessionFactory.openSession();
            equipments = sqlSession.selectList("getEquipments");
        } finally {
            sqlSession.close();
        }
        return equipments;
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
