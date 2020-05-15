package com.wfmyzyz.user.utils;

import com.wfmyzyz.user.config.ProjectConfig;
import com.wfmyzyz.user.enums.ProjectResEnum;
import com.wfmyzyz.user.user.domain.Role;
import com.wfmyzyz.user.user.domain.UserRole;

import java.util.*;

/**
 * @author admin
 */
public class RoleUtils {
    private List<Integer> topList = new ArrayList<>();
    private Map<Integer,Integer> roleIdMap = new HashMap<>();
    private List<Role> roleList;
    private Integer roleId;

    public RoleUtils(List<Role> roleList, List<Integer> roleIdList){
        roleIdList.forEach(roleId -> {
            roleList.forEach(role -> {
                if (Objects.equals(roleId,role.getRoleId())){
                    roleIdMap.put(roleId,role.getfRoleId());
                }
            });
        });
        this.roleList = roleList;
    }

    public Set<Integer> getTopList(){
        for (Integer roleId: this.roleIdMap.keySet()){
            this.roleId = roleId;
            this.findFRole(roleList,roleId);
        }
        return new HashSet<>(this.topList);
    }

    private void findFRole(List<Role> roleList,Integer fRoleId){
        for (Role role:roleList){
            if (Objects.equals(role.getRoleId(),fRoleId)){
                if (Objects.equals(role.getfRoleId(), ProjectConfig.ROLE_ROOT_ID)){
                    this.topList.add(this.roleIdMap.get(this.roleId));
                }else {
                    if (this.roleIdMap.get(role.getfRoleId()) != null){
                        break;
                    }
                    this.findFRole(roleList,role.getfRoleId());
                }
                break;
            }
        }
    }

}
