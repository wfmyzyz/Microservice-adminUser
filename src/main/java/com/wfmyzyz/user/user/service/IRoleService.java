package com.wfmyzyz.user.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wfmyzyz.user.user.domain.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wfmyzyz.user.user.vo.role.AddRoleVo;
import com.wfmyzyz.user.user.vo.role.SearchRoleVo;
import com.wfmyzyz.user.user.vo.role.TreeRoleVo;
import com.wfmyzyz.user.user.vo.role.UpdateRoleVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author auto
 * @since 2020-03-16
 */
public interface IRoleService extends IService<Role> {

    /**
     * 添加用户角色
     * @param addRoleVo
     * @return
     */
    boolean addRole(AddRoleVo addRoleVo);

    /**
     * 修改角色
     * @param updateRoleVo
     * @return
     */
    boolean updateRole(UpdateRoleVo updateRoleVo);

    /**
     * 分页查询角色列表
     * @param searchRoleVo
     * @return
     */
    IPage<Role> getRoleList(SearchRoleVo searchRoleVo);

    /**
     * 获取树形角色列表
     * @return
     */
    List<TreeRoleVo> getRoleList();

    /**
     * 根据创建时间获取角色列表
     * @return
     */
    List<Role> listByOrderCreateTimeAsc();

    /**
     * 删除角色与子角色
     * @param ids
     * @return
     */
    boolean removeByIdsAndSon(List<Integer> ids);
}
