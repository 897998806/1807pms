package com.zscms.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.zscms.exception.SysException;





/**
 * 操作数据库的中间件
 * @author Administrator
 *
 */
public class DButil {
	//驱动
	private String driver="com.mysql.jdbc.Driver";
	//连接地址，参数是支持中文输入
	//jdbc:mysql mmysql专用链接
	//localhost 代表本机ip 可以换成别人的ip或者自己的ip
	//3306：服务端口
	//tjzs：你要链接的数据库名字
	private String url="jdbc:mysql://localhost:3306/cms?useUnicode=true&characterEncoding=utf-8";
	//连接用户名
	private String  user="root";
	//密码
	private String password="root";
	
	/**
	 * 获得数据库连接的方法， 不允许外界调用
	 * @return
	 */
	private Connection getConn()throws SysException {
		Connection conn=null;
		try {
			//加载并注册驱动
			Class.forName(driver);
			//获得数据库连接
			conn=DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			throw new SysException(1,"驱动加载失败");
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			throw new SysException(2,"数据库异常");
		}
		return conn;
	}
	/**
	 * 关闭资源的方法
	 * @param rs  结果集
	 * @param ps 处理对象
	 * @param conn 连接
	 */
	private void close(ResultSet rs,PreparedStatement ps,Connection conn){
			
			try {
				if (rs!=null) {
					rs.close();
				}
				if (ps!=null) {
					ps.close();
				}
				if (conn!=null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
	}
	/**
	 * 增删改的方法
	 * @param sql  需要执行的带参SQL语句 
	 * @param objs 带参SQL语句内需要设置的参数
	 * @return 执行结果
	 */
	public int execUpdate(String sql,Object[] objs)throws SysException{
		//获得数据库连接
		Connection conn=this.getConn();
		//声明预编译对象
		PreparedStatement ps=null;
		//如果连接为空时不能执行以下内容
		if(conn==null){
			return 0;
		}
		try {
			//从连接中获得预 编译对象
			ps=conn.prepareStatement(sql);
			//依次将参数Set到ps中参数注入
			for (int i = 0; i < objs.length; i++) {
				//索引从1开始
				ps.setObject(i+1, objs[i]);
			}
			
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return 0;
		}finally {
			this.close(null, ps, conn);
		}
		
	}
	
	/**
	 * 查询的方法
	 * @param sql  带参的SQL语句
	 * @param objs  为SQL中的参数，因为不知道是Int 还是string 所以用object 存参数
	 * @return  list<map<string,string>> 不知道查询得到的数据为哪个Bean类型所以用Map来封装数据
	 * 	map的key为数据库字段名，values为数据库的字段值
	 */
	public List<Map<String, String>> execQuery(String sql,Object[] objs)throws SysException{
		//获得连接
		Connection conn=this.getConn();
		//定义处理对象的变量初始化为null
		PreparedStatement ps=null;
		//定义结果集初始化为null
		ResultSet rs=null;
		//定义一个用于存放封装数据的Map集合的List集合
		List<Map<String, String>>  maps=new ArrayList<>();
		
		try {
			//从数据库连接中获得处理对象
			ps=conn.prepareStatement(sql);
			//把参数注入到SQL中
			for (int i = 0; i < objs.length; i++) {
				//依次把数组内的参数取出set到ps的i+1位置
				//数组从0开始,参数的位置从1开始
				ps.setObject(i+1, objs[i]);
			}
			//获得结果集
			rs=ps.executeQuery();
			//获得结果集的结构
			ResultSetMetaData rm=rs.getMetaData();
			//依次从结果集中取出值
			while (rs.next()) {
				//map集合用于存放一条数据
				Map<String, String> map=new HashMap<>();
				//把数据封装到Map
				for (int i = 1; i <=rm.getColumnCount(); i++) {
						//结果集结构中获得字段名  rm.getColumnName(i)  ,i 从1开始
						//结果集获得字段值 rs.getString(i) ,i从1开始
						map.put(rm.getColumnName(i), rs.getString(i));
				}
				//把一条信息放入List集合
				maps.add(map);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			//调用本类的关闭资源
			this.close(rs, ps, conn);
		}
		
		
		return maps;
	}
	
	
}
