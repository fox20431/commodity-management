package test;

import dao.impl.BasicDaoImpl;
import dao.impl.GoodsDaoImpl;
import entity.Users;
import org.junit.Test;
import util.Tools;

public class DaoTest {

    @Test
    // 通过删除操作对数据库内容进行更改
    // 返回更改条目为1，说明数据库连接成功
    // 该操作说明BaseDao内的BaseDao方法是正确的

    public void baseDaoTest(){
        int num = BasicDaoImpl.executeSql("DELETE FROM goods WHERE id=1;",null);
        System.out.println(num);
    }

    @Test
    public void dateTest(){
        System.out.println(Tools.DateTime());
    }

    @Test
    public void getAllPageTest(){
        GoodsDaoImpl gDao = new GoodsDaoImpl();
        Users users = new Users();
        int a = gDao.getAllPage(1,3);
        System.out.println(a);
    }

}
