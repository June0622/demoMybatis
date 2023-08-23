package com.example.demo.mapper;

import com.example.demo.Pojo.Emp;
import org.apache.ibatis.annotations.*;

/*@Mapper注解：表示当前接口为mybatis中的Mapper接口
  程序运行时会自动创建接口的实现类对象(代理对象)，并交给Spring的IOC容器管理
*/
@Mapper
public interface EmpMapper {

    //@Delete("delete from emp where id = 17")
    //public void delete();
    //以上delete操作的SQL语句中的id值写成固定的17，就表示只能删除id=17的用户数据
    //SQL语句中的id值不能写成固定数值，需要变为动态的数值
    //解决方案：在delete方法中添加一个参数(用户id)，将方法中的参数，传给SQL语句

    /**
     * 根据id删除数据
     * @param id    用户id
     */
    @Delete("delete from emp where id = #{id}")//使用#{key}方式获取方法中的参数值
    public void delete(Integer id);


    //insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time) values
    // ('songyuanqiao','宋远桥',1,'1.jpg',2,'2012-10-09',2,'2022-10-01 10:00:00','2022-10-01 10:00:00');
    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time) values" +
            " (#{username}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime})")
    public void insert(Emp emp);


   //update emp set username = 'linghushaoxia', name = '令狐少侠', gender = 1 , image = '1.jpg' , job = 2,
    // entrydate = '2012-01-01', dept_id = 2, update_time = '2022-10-01 12:12:12'
    // here id = 18;
    /**
     * 根据id修改员工信息
     * @param emp
     */
    @Update("update emp set username=#{username}, name=#{name}, gender=#{gender}, image=#{image}, job=#{job}, entrydate=#{entrydate}, " +
            "dept_id=#{deptId}, update_time=#{updateTime} " +
            "where id=#{id}")
    public void update(Emp emp);

    //select id, username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time from emp;
    //dept_id、create_time、update_time实体类属性名和数据库表查询返回的字段名不一致，不能自动封装。需要起别名或者驼峰命名
    @Select("select id, username, password, name, gender, image, job, entrydate, dept_id as deptId, create_time as createTime, update_time as updateTime from emp where id=#{id}")
    public Emp getById(Integer id);
}