package com.ich.admin.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalEmployee {



    private static final long serialVersionUID = -1220863141561812024L;
    //************************ 静态信息 *****************************//
    /**
     * key<HTTP-SESSION-ID>,value<员工DTO>
     * 当前登录的员工信息-Session关联
     */
    public static Map<String,LocalEmployee> sessionDtoMap = new HashMap<String,LocalEmployee>();
    /**
     * key<DTO-ID>,value<员工DTO>
     * 当前登录的员工信息-Id关联
     */
    public static Map<String,LocalEmployee> dtoIdMap = new HashMap<String,LocalEmployee>();

    /**
     * key<线程唯一ID>,value<HTTP-SESSION-ID>
     * 使用每次的唯一线程ID关联SessionID
     * 每一次需要用户信息的操作都会有一条此信息
     */
    public static Map<Integer,String> thread2SessionMap = new HashMap<Integer,String>();
    /**
     * key<HTTP-SESSION-ID>,value<List<线程唯一ID>>
     * 使用SessionID关联每次的唯一线程ID
     * 用于在失效时删除之用
     */
    public static Map<String,List<Integer>> session2ThreadMap = new HashMap<String,List<Integer>>();

    public static void main(String[] args) {
        /** 方法逻辑说明 */
        //用户在登录时，在dtoIdMap通过ID保存SessionID和DTO至静态列表
        //用户在执行需要登录的操作时，取得《线程唯一ID》和《SessionID》保存
        //在后续需要取用户时，使用《线程唯一ID》得到《SessionID》再取得《DTO》
        //在Session失效时，清除登录用户及其线程信息
    }


    //********************数据信息**********************//

    /** 员工ID */
    private String employeeId;
    /** 员工编码 */
    private String employeeCode;
    /** 员工姓名  */
    private String employeeName;
    /** 菜单权限列表 */
    private List<EmployeeMenuDto> employeeMenuDtos;
    /** 菜单权限MAP */
    private Map<String,EmployeeMenuDto> employeeMenuMap;
    /** 窗口权限列表 */
    private List<EmployeeWindowDto> employeeWindowDtos;
    /** 窗口权限MAP */
    private Map<String,EmployeeWindowDto> employeeWindowMap;
    /** 数据权限SQL片段:数据【员工ID集】 */
    private String dataPower;
    /** 数据权限SQL片段:数据【职位ID集】 */
    private String dataPosiPower;
    /** 数据权限SQL片段:数据【机构ID集】 */
    private String dataOrgPower;

    //构造员工的数据信息方法
    public LocalEmployee(){

    }


    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getDataPower() {
        return dataPower;
    }

    public void setDataPower(String dataPower) {
        this.dataPower = dataPower;
    }

    public List<EmployeeMenuDto> getEmployeeMenuDtos() {
        return employeeMenuDtos;
    }


    public void setEmployeeMenuDtos(List<EmployeeMenuDto> employeeMenuDtos) {
        this.employeeMenuDtos = employeeMenuDtos;
    }


    public List<EmployeeWindowDto> getEmployeeWindowDtos() {
        return employeeWindowDtos;
    }


    public void setEmployeeWindowDtos(List<EmployeeWindowDto> employeeWindowDtos) {
        this.employeeWindowDtos = employeeWindowDtos;
    }


    public Map<String, EmployeeMenuDto> getEmployeeMenuMap() {
        return employeeMenuMap;
    }


    public void setEmployeeMenuMap(Map<String, EmployeeMenuDto> employeeMenuMap) {
        this.employeeMenuMap = employeeMenuMap;
    }


    public Map<String, EmployeeWindowDto> getEmployeeWindowMap() {
        return employeeWindowMap;
    }


    public void setEmployeeWindowMap(
            Map<String, EmployeeWindowDto> employeeWindowMap) {
        this.employeeWindowMap = employeeWindowMap;
    }


    public String getEmployeeCode() {
        return employeeCode;
    }


    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }


    public String getDataPosiPower() {
        return dataPosiPower;
    }


    public void setDataPosiPower(String dataPosiPower) {
        this.dataPosiPower = dataPosiPower;
    }


    public String getDataOrgPower() {
        return dataOrgPower;
    }


    public void setDataOrgPower(String dataOrgPower) {
        this.dataOrgPower = dataOrgPower;
    }



//	public List<EmployeeMenuDto> getEmployeeMenus(String id,EmployeeDto employeeDto) {
//		List<EmployeeMenuDto> result = new ArrayList<EmployeeMenuDto>();
//		if(null == id){
//			for(EmployeeMenuDto employeeMenuDto : employeeDto.getEmployeeMenuDtos()){
//				if(employeeMenuDto.getParent().equals("root")){
//					result.add(employeeMenuDto);
//				}
//			}
//		}else{
//			for(EmployeeMenuDto employeeMenuDto : employeeDto.getEmployeeMenuDtos()){
//				if(employeeMenuDto.getParent().equals(id)){
//					result.add(employeeMenuDto);
//				}
//			}
//		}
//		return result;
//	}

}
