package com.zscms.user.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zscms.exception.AppException;
import com.zscms.exception.SysException;
import com.zscms.user.bean.DepBean;
import com.zscms.user.bean.UserBean;
import com.zscms.util.DButil;

/**
 * 
 * @author Administrator 用户的访问层
 */
public class UserDao {
	// 创建DBUtil对象 用来操作数据库
	DButil db = new DButil();

	/**
	 * Map转成UserBean专用方法
	 * 
	 * @param maps
	 * @return
	 */
	private List<UserBean> mapToBean(List<Map<String, String>> maps) {
		// 创建list 集合用户储存UserBean
		List<UserBean> users = new ArrayList<>();
		// 变量集合，将map集合中的数据取出封装到javabean中
		for (Map<String, String> map : maps) {
			// 创建用来封装每一个用户信息的bean
			UserBean user = new UserBean();
			// map集合根据key来取value
			if (map.get("id") != null) {
				user.setId(Integer.parseInt(map.get("id")));
			}
			if (map.get("dep") != null) {
				user.setDep(Integer.parseInt(map.get("dep")));
			}
			if (map.get("enable") != null) {
				user.setEnable(Integer.parseInt(map.get("enable")));
			}
			user.setEmail(map.get("email"));
			user.setLoginname(map.get("loginname"));
			user.setPassword(map.get("password"));
			user.setRealname(map.get("realname"));
			user.setSex(map.get("sex"));
			// 新增加的显示部门中文的属性
			user.setDepText(map.get("name"));
			user.setBirthday(map.get("birthday"));

			// 把封装好的Bean 放集合中
			users.add(user);
		}

		return users;

	}

	/**
	 * 验证登录的方法
	 * 
	 * @param user 从Service 传入的登录信息
	 * @throws SysException
	 * @return数据库查询的结果
	 */
	public List<UserBean> login(UserBean user) throws SysException {

		// 登录查询的SQL语句,要求登录和密码同时陪陪，账号为可用状态
		String sql = "select*from tuser where loginname=?and password=?";
		// 给SQL语句添加参数 设置值
		Object[] objs = { user.getLoginname(), user.getPassword() };
		// 执行查询方法，返回集合
		List<Map<String, String>> list = db.execQuery(sql, objs);
		// 调用map转Bean方法
		List<UserBean> mapToBean = this.mapToBean(list);
		// 把封装好的集合返回
		return mapToBean;
	}

	/**
	 * 新增用户的信息
	 * 
	 * @param user
	 * @return
	 * @throws SysException
	 */
	public int addUser(UserBean user) throws SysException {
		// 拼SQL
		String sql = "insert into tuser values(null,?,?,?,?,?,?,?,?,null,null,null,null)";
		// 设置SQL参数
		Object[] objs = { user.getLoginname(), user.getPassword(), user.getRealname(), user.getSex(),
				user.getBirthday(), user.getDep(), user.getEmail(), user.getEnable() };
		// 调用更新数据库方法
		return db.execUpdate(sql, objs);
	}

	/**
	 * 查询 按倒序显示
	 * 
	 * @return
	 * @throws SysException
	 */
	public List<UserBean> queryUsersAll() throws SysException {
		// 拼SQL
		String sql = "select*from tuser order by id desc";
		// 设置参数
		Object[] objs = {};

		return this.mapToBean(db.execQuery(sql, objs));

	}

	/**
	 * 通过id查询用户
	 * 
	 * @param id用户信息的唯一标示
	 * @return 当前用户
	 * @throws SysException
	 */
	public List<UserBean> queryUserById(int id) throws SysException {
		// 拼sql
		String sql = "select*from tuser where id=?";
		// 设置参数
		Object[] objs = { id };
		// 调用方法
		return this.mapToBean(db.execQuery(sql, objs));
	}

	/**
	 * 修改的方法 因为不知道修改了那几个值，所以set的时候重新设置全部属性 没有修改的使用老的值，修改过的使用新值就可 param user
	 * 封装了用户的信息，包含了没有修改的老值和已经修改的值
	 * 
	 * @throws SysException
	 */
	public int updateUser(UserBean user) throws SysException, AppException {
		// 拼sql

		String sql = "update tuser set  loginname=?,password=?,realname=?,"
				+ "sex=?,birthday=?,dep=?,enable=?,email=? where id=?";
		// 设置参数
		Object[] objs = { user.getLoginname(), user.getPassword(), user.getRealname(), user.getSex(),
				user.getBirthday(), user.getDep(), user.getEnable(), user.getEmail(), user.getId() };

		// 调用更新方法

		return db.execUpdate(sql, objs);
	}

	/**
	 * 删除的方法根据id删除的
	 * 
	 * @param id 用户的身份标识
	 * @return 影响数据库的条数
	 * @throws SysException
	 */
	public int deleteUser(int id) throws SysException {
		// 拼sql
		String sql = "delete from tuser where id = ?";
		// 设置参数
		Object[] objs = { id };
		// 调用更新的方法

		return db.execUpdate(sql, objs);

	}

	/**
	 * 部门
	 * 
	 * @return
	 * @throws SysException
	 */
	public List<DepBean> getDep() throws SysException {
		// 拼sql
		String sql = "select*from tdep";
		// 设置参数
		Object[] objs = {};
		// 更新数据库的方法
		List<Map<String, String>> maps = db.execQuery(sql, objs);
		// 创建list<depbean> 来接收全部部门信息
		List<DepBean> deps = new ArrayList<>();
		// maptpbean
		for (Map<String, String> map : maps) {
			DepBean dep = new DepBean();
			if (map.get("id") != null) {
				dep.setId(Integer.parseInt(map.get("id")));
			}
			dep.setDepname(map.get("name"));
			deps.add(dep);
		}
		return deps;

	}

	/**
	 * 分页查询的方法
	 * 
	 * @param start 起始数
	 * @param num   每页条数
	 * @return 分页查询的信息
	 * @throws SysException
	 */
	public List<UserBean> queryByPage(int start, int num) throws SysException {
		// 拼sql
		String sql = "select*from userdep limit ?,?";
		// 设置参数
		Object[] objs = { start, num };
		// 调用方法并返回结果
		return this.mapToBean(db.execQuery(sql, objs));
	}

	/**
	 * 查询的方法
	 * 
	 * @return 返回的是查询的结果集封装的map
	 * @throws SysException
	 */
	public int getCount() throws SysException {
		// 取出总条数sql语句 给总条数的字段起别名ct
		String sql = "select count(1) ct from tuser";
		// 设置参数
		Object[] objs = {};
		// 调用方法
		List<Map<String, String>> count = db.execQuery(sql, objs);
		// 如果集合空
		if (count == null || count.size() != 1) {
			return 0;
		}
		// 获得map中的第一个元素
		Map<String, String> map = count.get(0);
		// 第一个元素中获得ct 对象的值
		String string = map.get("ct");
		// 把string类型的总条数 转成int返回
		return Integer.parseInt(string);

	}

	/**
	 * 模糊分页查询的方法
	 * 
	 * @param start 起始数
	 * @param num   每页条数
	 * @return 分页查询的信息
	 * @throws SysException
	 */
	public List<UserBean> queryByPageLike(String like, int start, int num) throws SysException {
		// 拼sql
		String sql = "select*from userdep where loginname like ? limit ?,?";
		// 设置参数
		Object[] objs = { "%" + like + "%", start, num };
		// 调用方法并返回结果
		return this.mapToBean(db.execQuery(sql, objs));
	}

	/**
	 * 模糊查询的方法
	 * 
	 * @return 返回的是查询的结果集封装的map
	 * @throws SysException
	 */
	public int getCountLike(String like) throws SysException {
		// 取出总条数sql语句 给总条数的字段起别名ct
		String sql = "select count(1) ct from tuser where loginname like ?";
		// 设置参数
		Object[] objs = { "%" + like + "%" };
		// 调用方法
		List<Map<String, String>> count = db.execQuery(sql, objs);
		// 如果集合空
		if (count == null || count.size() != 1) {
			return 0;
		}
		// 获得map中的第一个元素
		Map<String, String> map = count.get(0);
		// 第一个元素中获得ct 对象的值
		String string = map.get("ct");
		// 把string类型的总条数 转成int返回
		return Integer.parseInt(string);

	}

	/**
	 * 验证用户名存是否重复
	 * @param loginname
	 * @return
	 * @throws SysException
	 */
	public List<UserBean> chkExistLoginname(String loginname) throws SysException {
		// 拼SQL
		String sql = "select * from tuser where loginname=?";
		// 设置参数
		Object[] objs = { loginname };
		// 返回查询结果
		return this.mapToBean(db.execQuery(sql, objs));
	}
	/**
	 * 这个是查询邮箱的方法
	 * @param args
	 * @throws SysException 
	 */
	public List<UserBean>chkExistEmail(String email) throws SysException{
		//拼sql
		 String  sql="select * from tuser where email=?";
		 //设置参数
		 Object []objs= {email};
		 //返回查询结果
		return this.mapToBean(db.execQuery(sql, objs));
	}

	public static void main(String[] args) {
		UserDao dao = new UserDao();

		try {
			List<UserBean> users = dao.queryUsersAll();
			System.out.println(users);
		} catch (SysException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
