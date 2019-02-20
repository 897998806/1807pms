package com.zscms.user.service;

import java.util.List;

import com.zscms.exception.AppException;
import com.zscms.exception.SysException;
import com.zscms.user.bean.ChannelBean;
import com.zscms.user.bean.DepBean;
import com.zscms.user.bean.MessageBean;
import com.zscms.user.bean.UserBean;
import com.zscms.user.dao.ChannelDao;
import com.zscms.util.Constants;

public class ChannelService {
	// 创建数据访问层对象
	ChannelDao dao = new ChannelDao();

	/**
	 * 查询全部用户的方法
	 * 
	 * @return 全部用户的信息
	 * @throws SysException
	 */
	public List<ChannelBean> queryChannelAll() throws SysException {
		// 调用dao的查询全部的方法，把结果返回给servlet
		return dao.queryChannelAll();

	}

	/**
	 * 新增
	 * 
	 * @param channel
	 * @throws SysException
	 * @throws AppException
	 */
	public void addChannel(ChannelBean channel) throws SysException, AppException {
		int result = dao.addChannel(channel);
		if (result < 1) {
			throw new AppException(3003, "用户新增失败");
		}
	}

	/**
	 * 通过id 删除用户的方法
	 * 
	 * @param id 是用户的身份唯一标示
	 * @return
	 * @throws SysException
	 * @throws AppException
	 */
	public void deleteChannel(int id) throws SysException, AppException {
		// 调用dao 删除用户的方法
		int result = dao.deleteChannel(id);
		// 小于1 表示删除失败
		if (result < 1) {
			throw new AppException(3006, "删除栏目失败");
		}
		/* return result; */
	}
	
	/**
	 * 批量删除的逻辑
	 */
	//批量删除的逻辑
		public void deleteChannelByIds(String[] ids) throws NumberFormatException, SysException ,AppException{
			//用来计数的变量，保存的值为成功删除了多少次
			int sum=0;
			//遍历页面传入的id数组
			for (String id:ids) {
				//使用每个id去调用删除的方法
				int result=dao.deleteChannel(Integer.parseInt(id));
				//如果返回值>o 说明删除成功，sum自增1
				if (result>0) {
					sum++;
				}
			}
			//循环结果后判断删除成功的个数和网页传入的id的个数是否一致
			if (sum!=ids.length) {
				//不一致抛出异常
				throw new AppException(3008, "批量删除失败");
			}
		}
		
	

	/**
	 * 这个方法是 栏目pid的业务逻辑
	 * @return
	 * @throws SysException
	 */
	public List<ChannelBean> getPid() throws SysException {
		return dao.getPid();

	}

	/**
	 * 修改用户的
	 * 
	 * @param user
	 * @return
	 * @throws SysException
	 * @throws AppException
	 */
	public void updateChannel(ChannelBean channel) throws SysException, AppException {
		// 调用dao层的方法 修改用户方法
		int result = dao.updateChannel(channel);
		// 如果修改的返回的结果返回1说明没有影响数据库信息 ，修改失败
		if (result < 1) {
			throw new AppException(3005, "用户修改失败");
		}

	}

	/**
	 * 通过id查询用户的方法
	 * 
	 * @param id 是用户的唯一标示
	 * @return
	 * @throws SysException
	 * @throws AppException
	 */
	public ChannelBean queryChannelById(int id) throws SysException, AppException {
		// 调用Dao的通过 id查询的方法
		List<ChannelBean> channels = dao.queryChannelById(id);
		// 如果查询的结果为空时跑出异常标示没有找到对应的用户
		if (channels == null || channels.size() == 0) {
			throw new AppException(3004, "通过ID没有查询到对应的用户信息");
		}
		// 返回集合中的第一个信息
		return channels.get(0);
	}

	/**
	 * 分页公式
	 * 
	 * @param page
	 * @param num
	 * @return
	 * @throws SysException
	 */
	public List<ChannelBean> queryByPage(int page, int num) throws SysException {
		// limit (page-1)x每页显示的条数，每页显示的条数
		return dao.queryByPage((page - 1) * num, num);
	}

	/**
	 * 查询总条数的方法
	 * 
	 * @return 返回的是总条数
	 * @throws SysException
	 */
	public int getCount() throws SysException {
		// 调用DAO的方法获得数据
		return dao.getCount();

	}

	/**
	 * 获得总页数的方法
	 * 
	 * @return
	 * @throws SysException
	 */
	public int getCountPage() throws SysException {
		// 调用方法获得总条数
		int count = this.getCount();
		// 判断页面是否能被整除
		if (count % Constants.NUM == 0) {
			// 被整除直接返回两者相除
			return count / Constants.NUM;
		} else {
			// 不被整除时，返回两者相除+1
			return count / Constants.NUM + 1;
		}
	}
	/**
	 * 模糊分页公式
	 * @param page
	 * @param num
	 * @return
	 * @throws SysException
	 */
	public List<ChannelBean>queryByPageLike(String like,int page, int num) throws SysException{
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
	/**
	 * 判断标题是否重复的逻辑
	 * @param loginname
	 * @return
	 * @throws SysException
	 */
	public boolean chkExistCname(String cname) throws SysException {
		//调用dao的检测方法来获得查询结果
	 List<ChannelBean> chkExistCname = dao.chkExistCname(cname);
	//如果结果不为空时，说明查到同名用户返回true，否则为没有找到同名的用户 返回false
		if (chkExistCname!=null&&chkExistCname.size()>0) {
			return true;
		}else {
			return false;
		}
	
	}
	
}
