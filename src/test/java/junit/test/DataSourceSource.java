package junit.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.atguigu.jf.console.baseapi.user.SysFuncMapper;
import com.atguigu.jf.console.role.bean.pojo.SysRoleFunc;
import com.atguigu.jf.console.role.service.SysRoleService;
import com.atguigu.jf.console.user.bean.bo.SysFuncBeanForRoleTree;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class DataSourceSource {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SysFuncMapper funcMapper;
	
	@Autowired
	private SysRoleService sysRoleService;
	
	@Test
	public void test01() throws SQLException{
		System.out.println(dataSource.getConnection());
	}
	
	@Test
	public void test02(){
		List<SysFuncBeanForRoleTree> list = funcMapper.getRolFuncTree(new Long("3"));
		System.out.println(JSON.toJSON(list));
	}
	
	@Test
	public void test03(){
		SysRoleFunc sysRoleFunc = new SysRoleFunc();
		SysRoleFunc sysRoleFunc2 = new SysRoleFunc();
		sysRoleFunc.setRoleId(new Long("1"));
		sysRoleFunc2.setRoleId(new Long("1"));
		
		sysRoleFunc.setFuncId(new Long("25"));
		sysRoleFunc2.setFuncId(new Long("26"));
		List<SysRoleFunc> list = new ArrayList<>();
		list.add(sysRoleFunc);
		list.add(sysRoleFunc2);
		
		sysRoleService.batchUpdateRoleFunc(list, new Long("1"));
		
	}
}
