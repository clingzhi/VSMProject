package cc.ysf.dx.util;

import java.util.Properties;

public class ConstantUtil {
	private static final Properties props = new Properties();

	static {
		try {
			props.load(ConstantUtil.class.getClassLoader().getResourceAsStream("props/vms.properties"));
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * >>> 分页：当前页码
	 */
	public static final Integer PAGE_NUM = Integer.parseInt(props.getProperty("page.num"));
	/**
	 * >>>分页信息：每页显示数量
	 */
	public static final Integer PAGE_SIZE = Integer.parseInt(props.getProperty("page.size"));

}
