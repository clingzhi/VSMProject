package cc.ysf.dx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * >>> 车辆管理系统启动器
 */
@MapperScan("cc.ysf.dx.dao")
@SpringBootApplication
public class VmsStarter {
	public static void main(String[] args) {
		SpringApplication.run(VmsStarter.class, args);
	}
}
