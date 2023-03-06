package com.pvrschcms.pvrcinemaschdulernew.utils.mapper;

import com.pvrschcms.pvrcinemaschdulernew.utils.constant.Constant;
import com.pvrschcms.pvrcinemaschdulernew.utils.constant.Utility;
import com.pvrschcms.pvrcinemaschdulernew.user.model.RoleModel;
import com.pvrschcms.pvrcinemaschdulernew.user.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
@Component
public class RollMapper {
    Logger logger = LoggerFactory.getLogger("ws");
    @Autowired
    private Utility utility;
    @Autowired
    private RoleRepository roleRepository;
    public Set<RoleModel> getRollCreateCustomer(String roles) {
        Set<RoleModel> role = new HashSet<>();
        try {
            if (roles.equalsIgnoreCase("")) {
                role.add(roleRepository.findByName(Constant.RoleName.ROLE_USER.toString()));
            } else {
                String[] assignrole = roles.split("\\|");
                for (String role1 : assignrole) {
                    RoleModel rl = roleRepository.findByName(role1);
                    if (rl!=null) {
                        role.add(rl);
                    }
                }
            }
        }catch (Exception e){
            logger.debug("CREATE USER PROCESS MAP ROLE :: EXCEPTION {}", utility.error(e));
        }
        return role;
    }
}
