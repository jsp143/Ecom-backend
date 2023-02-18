package com.pvrschcms.pvrcinemaschdulernew.utils.mapper;

import com.pvrschcms.pvrcinemaschdulernew.utils.constant.Constant;
import com.pvrschcms.pvrcinemaschdulernew.utils.constant.Utility;
import com.pvrschcms.pvrcinemaschdulernew.user.model.RoleModel;
import com.pvrschcms.pvrcinemaschdulernew.user.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class RollMapper {
    @Autowired
    private Utility utility;
    @Autowired
    private RoleRepository roleRepository;
    public Set<RoleModel> getRollCreateCustomer(String roles) {
        Set<RoleModel> role = new HashSet<>();
        if(roles.equalsIgnoreCase("")){
            role.add(roleRepository.findByName(Constant.RoleName.ROLE_USER.toString()));
        }else {
            String[] assignrole = roles.split("\\|");
            for (String role1 : assignrole) {
                Optional<RoleModel> rl = roleRepository.findById(Integer.parseInt(role1));
                if (rl.isPresent()) {
                    role.add(rl.get());
                }
            }
        }
        return role;
    }
}
