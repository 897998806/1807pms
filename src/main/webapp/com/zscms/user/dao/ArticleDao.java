package com.zscms.user.dao;
/**
 * 这是文章的Dao层
 * @author Administrator
 *
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zscms.exception.SysException;
import com.zscms.user.bean.ArticleBean;
import com.zscms.user.bean.GetChannelBean;
import com.zscms.user.bean.UserBean;
import com.zscms.util.DButil;

public class ArticleDao {
	// 创建util
	DButil db = new DButil();

	private List<ArticleBean> mapToBean(List<Map<String, String>> maps) {
		// 创建list 集合用户储存UserBean
		List<ArticleBean> articles = new ArrayList<>();
		// 变量集合，将map集合中的数据取出封装到javabean中
		for (Map<String, String> map : maps) {
			// 创建用来封装每一个用户信息的bean
			ArticleBean article = new ArticleBean();
			// map集合根据key来取value
			if (map.get("id") != null) {
				article.setId(Integer.parseInt(map.get("id")));
			}
			if (map.get("channel") != null) {
				article.setChannel(Integer.parseInt(map.get("channel")));
			}
			if (map.get("isremod") != null) {
				article.setIsremod(Integer.parseInt(map.get("isremod")));
			}
			if (map.get("ishot") != null) {
				article.setIshot(Integer.parseInt(map.get("ishot")));
			}
			article.setContent(map.get("content"));
			article.setTitle(map.get("title"));
			article.setAuthor(map.get("author"));
			article.setCrtime(map.get("crtime"));
			//新增加的所属栏目的中文显示
			article.setCname(map.get("cname"));
			// 把封装好的Bean 放集合中
			articles.add(article);
		}
		return articles;

	}

	/**
	 * 查询全部文章信息的方法
	 * 
	 * @return
	 * @throws SysException
	 */
	public List<ArticleBean> queryArticleAll() throws SysException {
		// 拼SQL
		String sql = "select*from tarticle";
		// 设置参数
		Object[] objs = {};
		// 调用方法util的查询方法
		return this.mapToBean(db.execQuery(sql, objs));
	}

	/**
	 * 通过id查询文章
	 * 
	 * @param id文章信息的唯一标示
	 * @return 当前文章
	 * @throws SysException
	 */
	public List<ArticleBean> queryArticleById(int id) throws SysException {
		// 拼SQL语句
		String sql = "select*from tarticle where id=?";
		// 设置参数
		Object[] objs = { id };
		// 调用方法
		return this.mapToBean(db.execQuery(sql, objs));
	}

	/**
	 * 分页查询的方法
	 * 
	 * @param start 起始数
	 * @param num   每页条数
	 * @return 分页查询的信息
	 * @throws SysException
	 */
	public List<ArticleBean> queryByPage(int start, int num) throws SysException {
		// 拼sql
		String sql = "select*from  article limit ?,?";
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
		String sql = "select count(1) ct from tarticle";
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
	 * 通过id删除文章的方法
	 * 
	 * @param id
	 * @return
	 * @throws SysException
	 */
	public int deleteArticle(int id) throws SysException {
		// 拼sql
		String sql = "delete from tarticle where id= ?";
		// 设置参数
		Object[] objs = { id };
		// 调用更新的方法
		return db.execUpdate(sql, objs);

	}

	/**
	 * 新增文章的方法
	 * 
	 * @param article
	 * @return
	 * @throws SysException
	 */
	public int addArticle(ArticleBean article) throws SysException {
		// 拼SQL
		String sql = "insert into tarticle values(null,?,?,?,?,?,?,?,null,null,null,null)";
		// 设置参数
		Object[] objs = { article.getTitle(), article.getContent(), article.getAuthor(), article.getCrtime(),
				article.getChannel(), article.getIsremod(), article.getIshot() };
		return db.execUpdate(sql, objs);

	}

	/**
	 * 获得所以栏目的方法
	 * @return
	 * @throws SysException
	 */
	public List<GetChannelBean> getChannel() throws SysException {
		// 拼SQL
		String sql = "select*from tchannel";
		// 设置参数
		Object[] objs = {};
		// 更新数据库的方法
		List<Map<String, String>> maps = db.execQuery(sql, objs);
		// 创建bean 来接收所属栏目的信息
		List<GetChannelBean> channels = new ArrayList<>();
		for (Map<String, String> map : maps) {
			GetChannelBean channel = new GetChannelBean();
			if (map.get("id") != null) {
				channel.setId(Integer.parseInt(map.get("id")));
			}
			channel.setCname(map.get("cname"));
			channels.add(channel);
		}
		return channels;
	}
	/**
	 * 修改的方法 因为不知道修改了那几个值，所以set的时候重新设置全部属性
	 * 没有修改的使用老的值，修改过的使用新值就可
	 * param user 封装了用户的信息，包含了没有修改的老值和已经修改的值
	 * @throws SysException 
	 */
	public int updateArticle(ArticleBean article) throws SysException {
		// 拼SQL
		String sql = "update tarticle set title=?,content=?, author=?,crtime=?, channel=?,isremod=?,ishot=? where id=?";
		//设置参数
		Object[]objs= {article.getTitle(),article.getContent(),article.getAuthor(),article.getCrtime(),
				article.getChannel(),article.getIsremod(),article.getIshot(),article.getId()};
		//调用更新
		return db.execUpdate(sql, objs);

	}

	/**
	 * 模糊分页查询的方法
	 * @param start 起始数
	 * @param num 每页条数
	 * @return 分页查询的信息
	 * @throws SysException
	 */
	public List<ArticleBean>queryByPageLike(String like,int start,int num) throws SysException{
		//拼sql 
		String sql="select*from article where title like ? limit ?,?";
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
		String  sql="select count(1) ct from tarticle where title like ?";
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
	public List<ArticleBean> chkExistTitle(String title) throws SysException {
		// 拼SQL
		String sql = "select * from tarticle where title=?";
		// 设置参数
		Object[] objs = {title};
		// 返回查询结果
		return this.mapToBean(db.execQuery(sql, objs));
	}
	
	// 测试能不能从数据库中取到对应的数据
	public static void main(String[] args) {

		ArticleDao dao = new ArticleDao();

		/*
		 * try { List<ArticleBean> users = dao.queryArticleAll();
		 * System.out.println(users); } catch (SysException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 */
		try {
			List<GetChannelBean> lanMuBeans = dao.getChannel();
			System.out.println(lanMuBeans);
		} catch (SysException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
