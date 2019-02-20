package com.zscms.user.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zscms.exception.AppException;
import com.zscms.exception.SysException;
import com.zscms.user.bean.ArticleBean;
import com.zscms.user.bean.ChannelBean;
import com.zscms.user.bean.DepBean;
import com.zscms.user.bean.UserBean;
import com.zscms.util.DButil;

public class ChannelDao {
	// 创建DBUtil对象 用来操作数据库
	DButil db = new DButil();

	private List<ChannelBean> mapToBean(List<Map<String, String>> maps) {
		// 创建list 集合用户储存UserBean
		List<ChannelBean> channels = new ArrayList<>();
		// 变量集合，将map集合中的数据取出封装到javabean中
		for (Map<String, String> map : maps) {
			// 创建用来封装每一个用户信息的bean
			ChannelBean channel = new ChannelBean();
			// map集合根据key来取value
			if (map.get("id") != null) {
				channel.setId(Integer.parseInt(map.get("id")));
			}
			if (map.get("pid") != null) {
				channel.setPid(Integer.parseInt(map.get("pid")));
			}
			if (map.get("lev") != null) {
				channel.setLev(Integer.parseInt(map.get("lev")));
			}
			if (map.get("isleaf") != null) {
				channel.setIsleaf(Integer.parseInt(map.get("isleaf")));
			}
			if (map.get("sort") != null) {
				channel.setSort(Integer.parseInt(map.get("sort")));
			}
			channel.setCname(map.get("cname"));
			//这是新增加的，让上级栏目显示中文的属性
			channel.setName(map.get("name"));
			// 把封装好的Bean 放集合中
			channels.add(channel);
		}

		return channels;

	}
	/**
	 * 通过id查询栏目
	 * @param id栏目胡信息的唯一标示
	 * @return 当前栏目
	 * @throws SysException
	 */
	public List<ChannelBean> queryChannelById(int id) throws SysException {
		// 拼sql
		String sql = "select*from tchannel where id=?";
		// 设置参数
		Object[] objs = {id};
		// 调用方法
		return this.mapToBean(db.execQuery(sql, objs));
	}
	/**
	 * 查询全部栏目的信息
	 * @return
	 * @throws SysException
	 */
	public List<ChannelBean> queryChannelAll() throws SysException {
		// 拼SQL
		String sql = "select*from tchannel ";
		// 设置参数
		Object[] objs = {};
		//调用util查询方法
		return this.mapToBean(db.execQuery(sql, objs));

	}
	
	/**
	 * 新增栏目的信息 方法
	 * @param user
	 * @return
	 * @throws SysException
	 */
	public int addChannel(ChannelBean channel) throws SysException {
		// 拼SQL
		String sql = "insert into tchannel values(null,?,?,?,?,?,null,null,null,null)";
		// 设置SQL参数
		Object[] objs = {channel.getCname(),channel.getPid(),channel.getLev(),channel.getIsleaf(),channel.getSort()};
		// 调用更新数据库方法
		return db.execUpdate(sql, objs);
	}
	/**
	 * 删除的方法根据id删除的
	 * @param id是 栏目的身份标识
	 * @return 影响数据库的条数
	 * @throws SysException
	 */
	public int deleteChannel(int id) throws SysException {
		//拼sql
		String sql="delete from tchannel where id = ?";
		//设置参数
		Object[]objs= {id};
		//调用更新的方法
		
		return db.execUpdate(sql, objs);
		
	}
	/**
	 * 获得上级栏目的方法 并判断是不是叶子
	 * @return
	 * @throws SysException
	 */
	public List<ChannelBean> getPid() throws SysException {
		//拼sql
		String sql="select id,cname,pid,isleaf from tchannel  where isleaf=0";
		//设置参数
		Object[]objs= {};
		//更新数据库的方法
		List<Map<String, String>> maps=db.execQuery(sql, objs);
		//创建list<Channelbean> 来接收全部栏目信息
		List<ChannelBean> pids=new ArrayList<>();
		// 这里等我想想
		for (Map<String,String> map : maps) {
			ChannelBean cb=new ChannelBean();
			if (map.get("id")!=null) {
				cb.setId(Integer.parseInt(map.get("id")));
			}
			cb.setPid(Integer.parseInt(map.get("pid")));
			cb.setCname(map.get("cname"));
			cb.setIsleaf(Integer.parseInt(map.get("isleaf")));
			//把数据放入集合中
			pids.add(cb);
		}
		return pids;
		
	}
		
	/**
	 * 修改的方法 因为不知道修改了那几个值，所以set的时候重新设置全部属性
	 * 没有修改的使用老的值，修改过的使用新值就可
	 * param user 封装了用户的信息，包含了没有修改的老值和已经修改的值
	 * @throws SysException 
	 */
	public int  updateChannel(ChannelBean channel) throws SysException ,AppException{
		//拼sql
		String sql="update tchannel set cname=?,pid=?,lev=?,isleaf=?,sort=? where id=?";
		//设置参数
		Object[]objs= {
				channel.getCname(),channel.getPid(),channel.getLev(),channel.getIsleaf(),channel.getSort(),channel.getId()
		};
	
		//调用更新方法
		
		return db.execUpdate(sql, objs);
	}
	
	/**
	 * 分页查询的方法
	 * @param start 起始数
	 * @param num 每页条数
	 * @return 分页查询的信息
	 * @throws SysException
	 */
	public List<ChannelBean>queryByPage(int start,int num) throws SysException{
		//拼sql 
		String sql="select*from channel limit ?,?";
		//设置参数
		Object[]objs= {start,num};
		//调用方法并返回结果
		return this.mapToBean(db.execQuery(sql, objs));
	}
	/**
	 * 查询的方法
	 * @return  返回的是查询的结果集封装的map
	 * @throws SysException
	 */
	public int getCount() throws SysException {
		//取出总条数sql语句 给总条数的字段起别名ct
		String  sql="select count(1) ct from tchannel";
		//设置参数
		Object[]objs= {};
		//调用方法
		List<Map<String, String>> count = db.execQuery(sql, objs);
		//如果集合空
		if (count==null||count.size()!=1) {
			return 0;
		}
		//获得map中的第一个元素
		Map<String , String >map=count.get(0);
		//第一个元素中获得ct 对象的值
		String string=map.get("ct");
		//把string类型的总条数 转成int返回
		return Integer.parseInt(string);
		
	}
	/**
	 * 模糊分页查询的方法
	 * @param start 起始数
	 * @param num 每页条数
	 * @return 分页查询的信息
	 * @throws SysException
	 */
	public List<ChannelBean>queryByPageLike(String like, int start,int num) throws SysException{
		//拼sql
		String sql="select*from channel where cname like ? limit ?,? ";
		//设置参数
		Object[]objs= {"%"+like+"%",start,num};
		//调用方法
		return this.mapToBean(db.execQuery(sql, objs));
	
	}
	/**
	 * 模糊查询的方法
	 * @return  返回的是查询的结果集封装的map
	 * @throws SysException
	 */
	public int getCountLike(String like) throws SysException {
		//取出总条数sql语句给总条数字段其别名ct
		String  sql="select count(1) ct from tchannel where cname like ?";
		// 设置参数
		Object[]objs= {"%"+like+"%"};
		//调用方法
		List<Map<String , String >>count=db.execQuery(sql, objs);
		//如果集合空
		if (count==null||count.size()!=1) {
			return 0;
		}
		//获得map中的第一个元素
		Map<String , String >map=count.get(0);
		//第一个元素中获得ct对象的值
		String  string =map.get("ct");
		//把string类型的总条数转成int返回
		return Integer.parseInt(string);
	}
	
	/**
	 * 验证栏目名存是否重复
	 * @param loginname
	 * @return
	 * @throws SysException
	 */
	public List<ChannelBean> chkExistCname(String cname) throws SysException {
		// 拼SQL
		String sql = "select * from tchannel where cname=?";
		// 设置参数
		Object[] objs = {cname};
		// 返回查询结果
		return this.mapToBean(db.execQuery(sql, objs));
	}
	/**
	 * 测试
	 * @param args
	 * @throws SysException 
//	 */
//	public static void main(String[] args) throws SysException {
//		ChannelDao dao = new ChannelDao();
//		dao.getPid(1);
//		try {
//			List<ChannelBean> users = dao.queryChannelAll();
//			System.out.println(users);
//			System.out.println(dao.getPid(1));
//		} catch (SysException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
}
