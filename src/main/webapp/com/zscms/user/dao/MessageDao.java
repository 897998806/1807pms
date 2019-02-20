package com.zscms.user.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zscms.exception.AppException;
import com.zscms.exception.SysException;
import com.zscms.user.bean.ArticleBean;
import com.zscms.user.bean.ChannelBean;
import com.zscms.user.bean.MessageBean;
import com.zscms.util.DButil;

public class MessageDao {
	// 创建DBUtil对象 用来操作数据库
		DButil db = new DButil();

		private List<MessageBean> mapToBean(List<Map<String, String>> maps) {
			// 创建list 集合用户储存UserBean
			List<MessageBean>  messages = new ArrayList<>();
			// 变量集合，将map集合中的数据取出封装到javabean中
			for (Map<String, String> map : maps) {
				// 创建用来封装每一个用户信息的bean
				MessageBean message = new MessageBean();
				// map集合根据key来取value
				if (map.get("id") != null) {
					message.setId(Integer.parseInt(map.get("id")));
				}
				message.setTitle(map.get("title"));
				message.setContent(map.get("content"));
				message.setCtime(map.get("ctime"));
				message.setCman(map.get("cman"));
				// 把封装好的Bean 放集合中
				messages.add(message);
			}

			return messages;

		}
		/**
		 * 通过id查询广告
		 * @param id广告信息的唯一标示
		 * @return 当前广告
		 * @throws SysException
		 */
		public List<MessageBean> queryMessageById(int id) throws SysException {
			// 拼sql
			String sql = "select*from tmessage where id=?";
			// 设置参数
			Object[] objs = {id};
			// 调用方法
			return this.mapToBean(db.execQuery(sql, objs));
		}
		
		public List<MessageBean> queryMessagelAll() throws SysException {
			// 拼SQL
			String sql = "select*from tmessage ";
			// 设置参数
			Object[] objs = {};

			return this.mapToBean(db.execQuery(sql, objs));

		}
		
		/**
		 * 新增广告的信息 的方法
		 * @param user
		 * @return
		 * @throws SysException
		 */
		public int addMessage(MessageBean message) throws SysException {
			// 拼SQL
			String sql = "insert into tmessage values(null,?,?,?,?,null,null,null,null)";
			// 设置SQL参数
			Object[] objs = {message.getTitle(),message.getContent(),message.getCtime(),message.getCman()};
			// 调用更新数据库方法
			return db.execUpdate(sql, objs);
		}
		/**
		 * 删除的方法根据id删除的
		 * @param id 是广告的身份标识
		 * @return 影响数据库的条数
		 * @throws SysException
		 */
		public int deleteMessage(int id) throws SysException {
			//拼sql
			String sql="delete from tmessage where id = ?";
			//设置参数
			Object[]objs= {id};
			//调用更新的方法
			
			return db.execUpdate(sql, objs);
			
		}

			
		/**
		 * 修改的方法 因为不知道修改了那几个值，所以set的时候重新设置全部属性
		 * 没有修改的使用老的值，修改过的使用新值就可
		 * param user 封装了用户的信息，包含了没有修改的老值和已经修改的值
		 * @throws SysException 
		 */
		public int  updateMessage(MessageBean message) throws SysException ,AppException{
			//拼sql
			String sql="update tmessage set title=?,content=?,ctime=?,cman=? where id=?";
			//设置参数
			Object[]objs= {
					message.getTitle(),message.getContent(),message.getCtime(),message.getCman(),message.getId()
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
		public List<MessageBean>queryByPage(int start,int num) throws SysException{
			//拼sql 
			String sql="select*from tmessage order by id desc limit ?,?";
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
			String  sql="select count(1) ct from tmessage";
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
		public List<MessageBean>queryByPageLike(String like,int start,int num ) throws SysException{
			//拼sql
			String sql="select * from tmessage where title like ? limit ?,?";
			//设置参数
			Object[]objs= {"%"+like+"%",start,num};
			//调用方法并返回结果
			return this.mapToBean(db.execQuery(sql, objs));
		}
		/**
		 * 模糊查询的方法
		 * @return  返回的是查询的结果集封装的map
		 * @throws SysException
		 */
		public int getCountLike(String like) throws SysException {
			//取出总条数sql语句 给总条数的字段起别名ct
			String  sql="select count(1) ct from tmessage where title like ?";
			//设置参数
			Object[]objs= {"%"+like+"%"};
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
		 * 验证标题存是否重复
		 * @param loginname
		 * @return
		 * @throws SysException
		 */
		public List<MessageBean> chkExistTitle(String title) throws SysException {
			// 拼SQL
			String sql = "select * from tmessage where title=?";
			// 设置参数
			Object[] objs = {title};
			// 返回查询结果
			return this.mapToBean(db.execQuery(sql, objs));
		}
		/**
		 * 测试
		 * @param args
		 * @throws SysException 
		 */
		public static void main(String[] args) throws SysException {
						
		}

}