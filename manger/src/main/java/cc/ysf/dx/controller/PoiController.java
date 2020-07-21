package cc.ysf.dx.controller;

import cc.ysf.dx.base.baseController.BaseController;
import cc.ysf.dx.pojo.entity.Role;
import cc.ysf.dx.pojo.entity.User;
import cc.ysf.dx.service.RoleService;
import cc.ysf.dx.service.UserSerivce;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller("poiController")
@RequestMapping("/poi")
public class PoiController extends BaseController {
	@Autowired
	private UserSerivce userSerivce;
	@Autowired
	private RoleService roleService;
	/**
	 * >>> 转发到POI页面
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/poi")
	public String forWordPoiPage()throws Exception{
		return "poi/poi_index";
	}

	/**
	 * >>> 接受上传文件，并且解析
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/import")
	@ResponseBody
	public boolean importExcelFile(@RequestParam(value = "uploadFile") MultipartFile uploadFile) throws Exception{

		//通过使用BOOT封装对象 MultipartFile 类获得上传对象
		//获得输入流对象
		InputStream inputStream = uploadFile.getInputStream();
		//通过输入流对象 获得POI 操作EXCEL 的对象HSSFWorkbook()
		Workbook workbook = new HSSFWorkbook(inputStream);
		//通过workbook获得第一个sheet对象
		Sheet sheet = workbook.getSheetAt(0);

			//创建用户集合
			List<User> userList = new ArrayList<>();
			//创建用户，将用户信息导入
			User user = new User();
			//创建保存用户布尔类型对象
			boolean saveUser = false;
		//通过当前sheet获得当前共有几行数据
		int rows = sheet.getPhysicalNumberOfRows();
		//进行数据循环
		for(int i=1 ;i<=rows ; i++){
			//获得每一行的数据
			Row row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
						//获得每一行拥有的单元格数量
							//int cells = row.getPhysicalNumberOfCells();
						//获得每一个单元格的内容
							/*for(int j = 0;j<cells; j++){
								Cell cell = row.getCell(j);
								if (cell.getStringCellValue() == null) {
									continue;
								}*/
							// if( cell.getCellType().equals(CellType.STRING)){
							// } else if ( cell.getCellType().equals(CellType.NUMERIC)){
							//	System.out.println(cell.getNumericCellValue()+"\t");
							// }
			//姓名
				user.setUsername(sheet.getRow(i).getCell(0).getStringCellValue());
			System.out.println(user.getUsername());
			//电话
				user.setCellphone(sheet.getRow(i).getCell(1).getStringCellValue());
	        //性别
				if("男".equals(sheet.getRow(i).getCell(2).getStringCellValue())){
					user.setGender(1);
				}else {
					user.setGender(0);
				}
			//职能
				Role role = new Role();
				if (sheet.getRow(i).getCell(3).getStringCellValue().equals("超级管理员")){
					role.setId(1L);
					user.setRole(role);
				}else if(sheet.getRow(i).getCell(3).getStringCellValue().equals("管理员")){
					role.setId(2L);
					user.setRole(role);
				}else if(sheet.getRow(i).getCell(3).getStringCellValue().equals("经理")){
					role.setId(3L);
					user.setRole(role);
				}else if(sheet.getRow(i).getCell(3).getStringCellValue().equals("员工")){
					role.setId(4L);
					user.setRole(role);
				}
			//身份证
				user.setIdcard(sheet.getRow(i).getCell(4).getStringCellValue());
			//电子邮件
				user.setEmail(sheet.getRow(i).getCell(5).getStringCellValue());

				userList.add(user);
				saveUser = userSerivce.save(user);
		}
		System.out.println(userList.size());

		//将用户进行保存
		if(saveUser){
			return true;
		}
		return false;
	}
}
