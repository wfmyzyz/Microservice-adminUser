package com.wfmyzyz.user.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wfmyzyz.user.config.MybatisPlusConfig;
import com.wfmyzyz.user.config.ProjectConfig;
import com.wfmyzyz.user.enums.ProjectResEnum;
import com.wfmyzyz.user.user.domain.Authority;
import com.wfmyzyz.user.user.domain.Role;
import com.wfmyzyz.user.user.enums.AuthorityDisplayEnum;
import com.wfmyzyz.user.user.enums.AuthorityTypeEnum;
import com.wfmyzyz.user.user.mapper.AuthorityMapper;
import com.wfmyzyz.user.user.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wfmyzyz.user.user.vo.authority.AddAuthorityVo;
import com.wfmyzyz.user.user.vo.authority.SearchAuthorityVo;
import com.wfmyzyz.user.user.vo.authority.TreeAuthorityVo;
import com.wfmyzyz.user.user.vo.authority.UpdateAuthorityVo;
import com.wfmyzyz.user.user.vo.user.MenuListVo;
import com.wfmyzyz.user.user.vo.user.MenuMetaVo;
import com.wfmyzyz.user.utils.Msg;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author auto
 * @since 2020-03-16
 */
@Service
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, Authority> implements IAuthorityService {

    @Autowired
    private IRoleAuthorityService roleAuthorityService;
    @Autowired
    private IUserRoleService userRoleService;

    @Override
    public Msg addAuthority(AddAuthorityVo addAuthorityVo) {
        if (StringUtils.isNotBlank(addAuthorityVo.getUrl())){
            Authority authorityByUrl = this.getAuthorityByUrl(addAuthorityVo.getUrl());
            if (authorityByUrl != null){
                return Msg.error(ProjectResEnum.AUTHORITY_ALREADY_EXISTED);
            }
        }
        if (Objects.equals(addAuthorityVo.getFAuthorityId(),ProjectConfig.AUTHORITY_ROOT_ID) && Objects.equals(addAuthorityVo.getType(), AuthorityTypeEnum.按钮.toString())){
            return Msg.error(ProjectResEnum.AUTHORITY_ROOT_ERROR);
        }
        boolean flag = this.save(createAuthorityByVo(addAuthorityVo));
        if (!flag){
            return Msg.error(ProjectResEnum.AUTHORITY_ADD_FAIL);
        }
        return Msg.success(ProjectResEnum.AUTHORITY_ADD_SUCCESS);
    }

    @Override
    public Authority getAuthorityByUrl(String url) {
        return this.lambdaQuery().eq(Authority::getUrl,url).last(MybatisPlusConfig.LIMIT_ONE).one();
    }

    /**
     * 根据vo创建权限
     * @param addAuthorityVo
     * @return
     */
    private Authority createAuthorityByVo(AddAuthorityVo addAuthorityVo){
        Authority authority = new Authority();
        authority.setName(addAuthorityVo.getName());
        authority.setUrl(addAuthorityVo.getUrl());
        authority.setType(addAuthorityVo.getType());
        authority.setDisplay(addAuthorityVo.getDisplay());
        authority.setfAuthorityId(addAuthorityVo.getFAuthorityId());
        authority.setSort(addAuthorityVo.getSort());
        authority.setIcon(addAuthorityVo.getIcon());
        return authority;
    }

    @Override
    public Msg updateAuthority(UpdateAuthorityVo updateAuthorityVo) {
        if (StringUtils.isNotBlank(updateAuthorityVo.getUrl())){
            Authority authorityByUrl = this.getAuthorityByUrl(updateAuthorityVo.getUrl());
            if (authorityByUrl != null && !Objects.equals(authorityByUrl.getAuthorityId(),updateAuthorityVo.getAuthorityId())){
                return Msg.error(ProjectResEnum.AUTHORITY_ALREADY_EXISTED);
            }
        }
        Authority authority = this.getById(updateAuthorityVo.getAuthorityId());
        if (Objects.equals(authority.getfAuthorityId(),ProjectConfig.AUTHORITY_ROOT_ID) && Objects.equals(updateAuthorityVo.getType(), AuthorityTypeEnum.按钮.toString())){
            return Msg.error(ProjectResEnum.AUTHORITY_ROOT_ERROR);
        }
        BeanUtils.copyProperties(updateAuthorityVo,authority);
        authority.setUpdateTime(null);
        boolean flag = this.updateById(authority);
        if (!flag){
            return Msg.error(ProjectResEnum.AUTHORITY_UPDATE_FAIL);
        }
        return Msg.success(ProjectResEnum.AUTHORITY_UPDATE_SUCCESS);
    }

    @Override
    public IPage<Authority> getAuthorityList(SearchAuthorityVo searchAuthorityVo) {
        LambdaQueryChainWrapper<Authority> lambdaQuery = this.lambdaQuery();
        if (StringUtils.isNotBlank(searchAuthorityVo.getName())){
            lambdaQuery.like(Authority::getName,searchAuthorityVo.getName());
        }
        if (StringUtils.isNotBlank(searchAuthorityVo.getUrl())){
            lambdaQuery.like(Authority::getUrl,searchAuthorityVo.getUrl());
        }
        if (StringUtils.isNotBlank(searchAuthorityVo.getDisplay())){
            lambdaQuery.eq(Authority::getDisplay,searchAuthorityVo.getDisplay());
        }
        if (StringUtils.isNotBlank(searchAuthorityVo.getType())){
            lambdaQuery.eq(Authority::getType,searchAuthorityVo.getType());
        }
        if (searchAuthorityVo.getAuthorityId() != null){
            lambdaQuery.eq(Authority::getAuthorityId,searchAuthorityVo.getAuthorityId());
        }
        if (searchAuthorityVo.getFAuthorityId() != null){
            lambdaQuery.eq(Authority::getfAuthorityId,searchAuthorityVo.getFAuthorityId());
        }
        lambdaQuery.orderByDesc(Authority::getCreateTime);
        return lambdaQuery.page(new Page<>(searchAuthorityVo.getPage(),searchAuthorityVo.getPageSize()));
    }



    @Override
    public List<MenuListVo> getMenuList(Integer userId) {
        Set<Integer> authorityIdListSet = this.listAuthorityIdByUserId(userId);
        List<Authority> authorityList = this.listByType(authorityIdListSet,AuthorityTypeEnum.页面);
        List<MenuListVo> menuListVoList = new ArrayList<>();
        authorityList.forEach(authority -> {
            if (Objects.equals(authority.getfAuthorityId(),ProjectConfig.AUTHORITY_ROOT_ID)){
                List<MenuListVo> sonMenuList = findSonMenu(authorityList, authority.getAuthorityId(), authority.getUrl());
                MenuListVo oneMenu;
                if (sonMenuList.size() > 0){
                    oneMenu = createMenuListVo(authority, "Layout", "noRedirect");
                    oneMenu.setChildren(sonMenuList);
                }else {
                    oneMenu = createNoChildrenOneMenu(authority);
                }
                menuListVoList.add(oneMenu);
            }
        });
        return menuListVoList;
    }

    @Override
    public Set<Integer> listAuthorityIdByUserId(Integer userId) {
        List<Integer> roleIdList = userRoleService.listRoleIdByUserId(userId);
        List<Integer> authorityIdList = roleAuthorityService.listAuthorityIdByRoleIds(roleIdList);
        return new HashSet<>(authorityIdList);
    }

    /**
     * 根据权限创建有子菜单的一级菜单
     * @return
     */
    private MenuListVo createMenuListVo(Authority authority,String component,String redirect){
        MenuListVo menuListVo = new MenuListVo();
        menuListVo.setComponent(component);
        menuListVo.setPath(authority.getUrl());
        menuListVo.setRedirect(redirect);
        menuListVo.setHidden(Objects.equals(authority.getDisplay(), AuthorityDisplayEnum.显示.name()) ? false : true);
        menuListVo.setName(subLastChangeBig(authority.getUrl()));
        MenuMetaVo menuMetaVo = new MenuMetaVo();
        menuMetaVo.setTitle(authority.getName());
        menuMetaVo.setIcon(authority.getIcon());
        menuListVo.setMeta(menuMetaVo);
        return menuListVo;
    }

    /**
     * 根据权限创建没有子菜单的一级菜单
     * @return
     */
    private MenuListVo createNoChildrenOneMenu(Authority authority){
        String url = authority.getUrl();
        MenuListVo menuListVo = new MenuListVo();
        menuListVo.setComponent("Layout");
        menuListVo.setPath(url.substring(0,url.lastIndexOf("/")));
        menuListVo.setHidden(Objects.equals(authority.getDisplay(), AuthorityDisplayEnum.显示.name()) ? false : true);
        MenuListVo sonMenuListVo = new MenuListVo();
        sonMenuListVo.setPath(url.substring(url.lastIndexOf("/")+1));
        sonMenuListVo.setName(subLastChangeBig(authority.getUrl()));
        sonMenuListVo.setComponent(authority.getUrl());
        sonMenuListVo.setChildren(null);
        MenuMetaVo menuMetaVo = new MenuMetaVo();
        menuMetaVo.setTitle(authority.getName());
        menuMetaVo.setIcon(authority.getIcon());
        sonMenuListVo.setMeta(menuMetaVo);
        List<MenuListVo> menuListVoList = new ArrayList<>();
        menuListVoList.add(sonMenuListVo);
        menuListVo.setChildren(menuListVoList);
        return menuListVo;
    }

    /**
     * 截取最后/后面的地址转大写为名字
     * @param url
     * @return
     */
    private String subLastChangeBig(String url){
        String lastUrl = url.substring(url.lastIndexOf("/") + 1);
        return com.wfmyzyz.user.utils.StringUtils.indexCodeChangeBig(lastUrl);
    }

    /**
     * 递归寻找子菜单
     * @param authorityList
     * @param fid
     * @return
     */
    private List<MenuListVo> findSonMenu(List<Authority> authorityList,Integer fid,String path){
        List<MenuListVo> menuListVoList = new ArrayList<>();
        authorityList.forEach(authority -> {
            if (Objects.equals(authority.getfAuthorityId(),fid)){
                String sonPath = path + "/" + authority.getUrl();
                List<MenuListVo> sonMenu = findSonMenu(authorityList, authority.getAuthorityId(),sonPath);
                MenuListVo menuListVo;
                if (sonMenu.size() > 0){
                    menuListVo = createMenuListVo(authority, "EmptyLayout", "noRedirect");
                    menuListVo.setChildren(sonMenu);
                }else{
                    menuListVo = createMenuListVo(authority, path + "/" + authority.getUrl(), null);
                }
                menuListVoList.add(menuListVo);
            }
        });
        return menuListVoList;
    }

    @Override
    public List<Authority> listByType(Set<Integer> authorityIdList,AuthorityTypeEnum authorityTypeEnum) {
        return this.lambdaQuery().in(Authority::getAuthorityId,authorityIdList).eq(Authority::getType,authorityTypeEnum.name()).orderByAsc(Authority::getSort).list();
    }

    @Override
    public List<TreeAuthorityVo> getTreeAuthorityList() {
        List<Authority> authorityList = this.listByOrderSortAsc();
        List<TreeAuthorityVo> treeAuthorityVoList = findSonAuthority(authorityList, ProjectConfig.AUTHORITY_ROOT_ID);
        return treeAuthorityVoList;
    }



    /**
     * 递归寻找树形菜单
     * @return
     */
    private List<TreeAuthorityVo> findSonAuthority(List<Authority> authorityList,Integer fId){
        List<TreeAuthorityVo> treeAuthorityVoList = new ArrayList<>();
        authorityList.forEach(authority -> {
            if (Objects.equals(authority.getfAuthorityId(),fId)){
                List<TreeAuthorityVo> sonAuthority = findSonAuthority(authorityList, authority.getAuthorityId());
                TreeAuthorityVo treeAuthorityVo = new TreeAuthorityVo();
                BeanUtils.copyProperties(authority,treeAuthorityVo);
                treeAuthorityVo.setChildren(sonAuthority);
                treeAuthorityVoList.add(treeAuthorityVo);
            }
        });
        return treeAuthorityVoList;
    }

    @Override
    public boolean removeByIdsAndSon(List<Integer> ids) {
        List<Authority> authorityList = this.list();
        List<Integer> delIdList = new ArrayList<>();
        ids.forEach(id -> {
            List<Integer> sonIdList = getSonAuthorityIdById(authorityList, id);
            delIdList.addAll(sonIdList);
            delIdList.add(id);
        });
        //删除角色权限绑定数据
        roleAuthorityService.removeByAuthorityIds(delIdList);
        return this.removeByIds(delIdList);
    }

    @Override
    public List<Authority> listByOrderSortAsc() {
        return this.lambdaQuery().orderByAsc(Authority::getSort).list();
    }

    @Override
    public boolean judgeAuthByUserIdAndUrl(String url, Integer userId) {
        Set<Integer> authorityIdListSet = this.listAuthorityIdByUserId(userId);
        List<Authority> authorityList = this.listByType(authorityIdListSet, AuthorityTypeEnum.按钮);
        List<String> urlList = authorityList.stream().map(Authority::getUrl).collect(Collectors.toList());
        for (String urlStr: urlList){
            String repUrlStr = urlStr.replaceAll("\\*", "[^/]*");
            if (url.matches(repUrlStr)){
                return true;
            }
        }
        return false;
    }

    /**
     * 根据权限ID获取子权限
     * @param authorityList
     * @param fId
     * @return
     */
    private List<Integer> getSonAuthorityIdById(List<Authority> authorityList,Integer fId){
        List<Integer> idList = new ArrayList<>();
        authorityList.forEach(authority -> {
            if (Objects.equals(authority.getfAuthorityId(),fId)){
                List<Integer> sonIdList = getSonAuthorityIdById(authorityList, authority.getAuthorityId());
                idList.addAll(sonIdList);
                idList.add(authority.getAuthorityId());
            }
        });
        return idList;
    }
}
