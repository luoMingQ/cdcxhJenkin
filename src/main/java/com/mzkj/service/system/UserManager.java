package com.mzkj.service.system;

import com.github.pagehelper.PageInfo;
import com.mzkj.vo.system.UserQueryVo;
import com.mzkj.vo.system.UserVo;

/** 
 * 说明： 用户管理接口
 * 创建人：CDCXH
 * 创建时间：2019-04-18
 * @version
 */
public interface UserManager{

	/**新增
	 * @param userVo
	 * @throws Exception
	 */
	public UserVo save(UserVo userVo)throws Exception;
	
	/**删除
	 * @param USER_ID
	 * @throws Exception
	 */
	public void delete(String USER_ID)throws Exception;
	
	/**修改
	 * @param userVo
	 * @throws Exception
	 */
	public void edit(UserVo userVo)throws Exception;
	
	/**列表
	 * @param userQueryVo
	 * @throws Exception
	 */
	public PageInfo<UserQueryVo> list(UserQueryVo userQueryVo)throws Exception;
	
}
