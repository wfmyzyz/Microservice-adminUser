package com.wfmyzyz.user.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wfmyzyz.user.user.domain.Authority;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wfmyzyz.user.user.enums.AuthorityTypeEnum;
import com.wfmyzyz.user.user.vo.authority.AddAuthorityVo;
import com.wfmyzyz.user.user.vo.authority.SearchAuthorityVo;
import com.wfmyzyz.user.user.vo.authority.TreeAuthorityVo;
import com.wfmyzyz.user.user.vo.authority.UpdateAuthorityVo;
import com.wfmyzyz.user.user.vo.user.MenuListVo;
import com.wfmyzyz.user.utils.Msg;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author auto
 * @since 2020-03-16
 */
public interface IAuthorityService extends IService<Authority> {

    /**
     * 新增权限
     * @param addAuthorityVo
     * @return
     */
    Msg addAuthority(AddAuthorityVo addAuthorityVo);

    /**
     * 根据url获取权限
     * @param url
     * @return
     */
    Authority getAuthorityByUrl(String url);

    /**
     * 修改权限
     * @param updateAuthorityVo
     * @return
     */
    Msg updateAuthority(UpdateAuthorityVo updateAuthorityVo);

    /**
     * 分页获取权限列表
     * @param searchAuthorityVo
     * @return
     */
    IPage<Authority> getAuthorityList(SearchAuthorityVo searchAuthorityVo);

    /**
     * 根据用户Id获取菜单列表
     * @param userId
     * @return
     */
    List<MenuListVo> getMenuList(Integer userId);

    /**
     * 通过用户id获取权限id
     * @param userId
     * @return
     */
    Set<Integer> listAuthorityIdByUserId(Integer userId);

    /**
     * 根据权限类型获取权限
     * @param authorityIdList
     * @param authorityTypeEnum
     * @return
     */
    List<Authority> listByType(Set<Integer> authorityIdList, AuthorityTypeEnum authorityTypeEnum);

    /**
     * 获取树形权限列表
     * @return
     */
    List<TreeAuthorityVo> getTreeAuthorityList();

    /**
     * 连子权限一起删除
     * @param ids
     * @return
     */
    boolean removeByIdsAndSon(List<Integer> ids);

    /**
     * 根据排序获取所有列表
     * @return
     */
    List<Authority> listByOrderSortAsc();

    /**
     * 判断用户是否能访问该权限
     * @param url
     * @param userId
     * @return
     */
    boolean judgeAuthByUserIdAndUrl(String url, Integer userId);
}
