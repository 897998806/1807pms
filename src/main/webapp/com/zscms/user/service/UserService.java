package com.zscms.user.service;

import java.util.List;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.zscms.exception.AppException;
import com.zscms.exception.SysException;
import com.zscms.user.bean.DepBean;
import com.zscms.user.bean.UserBean;
import com.zscms.user.dao.UserDao;
import com.zscms.util.Constants;

/**
 * @author Administrator 用户的业务逻辑
 */
public class UserService {
	// 创建数据访问层对象
	UserDao dao = new UserDao();

	/**
	 * 验证登录的逻辑
	 * @param user Servlet 传入的用户登录信息
	 * @return 匹配用户信息
	 * @throws AppException
	 * @throws SysException
	 */
	public UserBean chklogin(UserBean user) throws AppException, SysException {
		// 根据用户名和密码查询用户
		List<UserBean> users = dao.login(user);
		// 当user为空或者大小不为1的时候，相当于账号密码没有查询到对应的用户，登录失败
		if (users == null || users.size() != 1) {
			// 抛出应用异常
			throw new AppException(1001, "账号密码不匹配请重新输入");
		}
		// 当有用户时如果enable 不为1时账号被锁定状态
		if (users.get(0).getEnable() != 1) {
			throw new AppException(1002, "账号已经被锁定不能被登录请联系管理员");
		}
		// 返回查询到的用户
		return users.get(0);

	}

	/**
	 * 用户新增的业务
	 * @param user
	 */
	public void addUser(UserBean user) throws SysException, AppException {
		int result = dao.addUser(user);
		if (result < 1) {
			throw new AppException(1003, "用户新增失败");
		}
	}

	/**
	 * 查询全部用户的方法
	 * @return 全部用户的信息
	 * @throws SysException
	 */
	public List<UserBean> queryUsersAll() throws SysException {
		// 调用dao的查询全部的方法，把结果返回给servlet
		return dao.queryUsersAll();

	}

	/**
	 * 通过id查询用户的方法
	 * @param id 是用户的唯一标示
	 * @return
	 * @throws SysException
	 * @throws AppException
	 */
	public UserBean queryUserById(int id) throws SysException, AppException {
		// 调用Dao的通过 id查询的方法
		List<UserBean> users = dao.queryUserById(id);
		// 如果查询的结果为空时跑出异常标示没有找到对应的用户
		if (users == null || users.size() == 0) {
			throw new AppException(1004, "通过ID没有查询到对应的用户信息");
		}
		// 返回集合中的第一个信息
		return users.get(0);
	}

	/**
	 * 修改用户的
	 * @param user
	 * @return
	 * @throws SysException
	 * @throws AppException
	 */
	public void updateuser(UserBean user) throws SysException, AppException {
		//调用dao层的方法 修改用户方法
		int result = dao.updateUser(user);
		//如果修改的返回的结果返回1说明没有影响数据库信息 ，修改失败
		if (result <1) {
			throw new AppException(1005, "用户修改失败");
		}

	}
/**
 * 通过id 删除用户的方法
 * @param id  是用户的身份唯一标示
 * @return
 * @throws SysException
 * @throws AppException
 */
	public void deleteUser(int id) throws SysException, AppException {
		//调用dao 删除用户的方法
		int result = dao.deleteUser(id);
		//小于1 表示删除失败
		if (result <1) {
			throw new AppException(1006, "删除用户失败");
		}
	/*return result;*/
	}
	/**
	 * 部门的service方法
	 * @return
	 * @throws SysException
	 */
	public List<DepBean> getDep() throws SysException{
		return dao.getDep();
		
	}
	/**
	 * 分页公式
	 * @param page
	 * @param num
	 * @return
	 * @throws SysException
	 */
	public List<UserBean>queryByPage(int page, int num) throws SysException{
		//limit (page-1)x每页显示的条数，每页显示的条数
		return dao.queryByPage((page-1)*num, num);
	}
	/**
	 * 查询总条数的方法
	 * @return 返回的是总条数
	 * @throws SysException
	 */
	public int getCount() throws SysException {
		//调用DAO的方法获得数据
		return dao.getCount();
		
	}
	/**
	 * 获得总页数的方法
	 * @return 
	 * @throws SysException
	 */
	public int getCountPage() throws SysException {
	//调用方法获得总条数
		int count=this.getCount();
		//判断页面是否能被整除
		if (count%Constants.NUM==0) {
			//被整除直接返回两者相除
			return count/Constants.NUM;
		}else {
			//不被整除时，返回两者相除+1
			return	count/Constants.NUM+1;
		}
	}
	/**
	 * 模糊分页公式
	 * @param page
	 * @param num
	 * @return
	 * @throws SysException
	 */
	public List<UserBean>queryByPageLike(String like,int page, int num) throws SysException{
		//limit (page-1)x每页显示的条数，每页显示的条数
		//like中存放的是关键字
		return dao.queryByPageLike(like,(page-1)*num, num);
	}
	/**
	 * 模糊查询总条数的方法
	 * @return 返回的是总条数
	 * @throws SysException
	 */
	public int getCountLike(String like) throws SysException {
		//调用DAO的方法获得数据
		return dao.getCountLike(like);
		
	}
	/**
	 * 模糊获得总页数的方法
	 * @return 
	 * @throws SysException
	 */
	public int getCountPageLike(String like) throws SysException {
	//调用方法获得总条数
		int count=this.getCountLike(like);
		//判断页面是否能被整除
		if (count%Constants.NUM==0) {
			//被整除直接返回两者相除
			return count/Constants.NUM;
		}else {
			//不被整除时，返回两者相除+1
			return	count/Constants.NUM+1;
		}
	}
	
	
	//批量删除的逻辑
	public void deleteUsersByIds(String[] ids) throws NumberFormatException, SysException ,AppException{
		//用来计数的变量，保存的值为成功删除了多少次
		int sum=0;
		//遍历页面传入的id数组
		for (String id:ids) {
			//使用每个id去调用删除的方法
			int result=dao.deleteUser(Integer.parseInt(id));
			//如果返回值>o 说明删除成功，sum自增1
			if (result>0) {
				sum++;
			}
		}
		//循环结果后判断删除成功的个数和网页传入的id的个数是否一致
		if (sum!=ids.length) {
			//不一致抛出异常
			throw new AppException(1008, "批量删除失败");
		}
	}
	
	/**
	 * 判断用户名是否重复的逻辑
	 * @param loginname
	 * @return
	 * @throws SysException
	 */
	public boolean chkExistLoginname(String loginname) throws SysException {
		//调用dao的检测方法来获得查询结果
		List<UserBean> chkExistLoginname = dao.chkExistLoginname(loginname);
	//如果结果不为空时，说明查到同名用户返回true，否则为没有找到同名的用户 返回false
		if (chkExistLoginname!=null&&chkExistLoginname.size()>0) {
			return true;
		}else {
			return false;
		}
	
	}
	/**
	 * 这是验证邮箱是否重复的逻辑
	 * @param email
	 * @return
	 * @throws SysException
	 */
	public boolean chkExistEmail(String email) throws SysException {
		//调用dao的检测方法来获得查询结果
		List<UserBean> chkExistEmail = dao.chkExistEmail(email);
		//如果结果不为空时，说明查到同名邮箱返回true，否则为没有找到同名的邮箱 返回false
		if (chkExistEmail!=null&&chkExistEmail.size()>0) {
			return true;
		} else {
			return false;
		}
	}
	
	
	//测试的
	public static void main(String[] args) {
		UserService userService=new UserService();
		
		try {
			List<UserBean> queryByPage = userService.queryByPage(1, 3);
			for(UserBean userBean:queryByPage) {
				System.err.println(userBean);
			}
		} catch (SysException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
