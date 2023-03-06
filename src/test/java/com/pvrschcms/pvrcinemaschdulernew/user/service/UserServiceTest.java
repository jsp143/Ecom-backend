package com.pvrschcms.pvrcinemaschdulernew.user.service;
import com.pvrschcms.pvrcinemaschdulernew.config.JwtTokenProvider;
import com.pvrschcms.pvrcinemaschdulernew.user.model.RoleModel;
import com.pvrschcms.pvrcinemaschdulernew.user.model.UserModel;
import com.pvrschcms.pvrcinemaschdulernew.user.model.request.SignUpRequest;
import com.pvrschcms.pvrcinemaschdulernew.user.repository.RoleRepository;
import com.pvrschcms.pvrcinemaschdulernew.user.repository.UserModelRepository;
import com.pvrschcms.pvrcinemaschdulernew.utils.constant.Constant;
import com.pvrschcms.pvrcinemaschdulernew.utils.constant.Utility;
import com.pvrschcms.pvrcinemaschdulernew.utils.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class UserServiceTest {


    @Mock
    private UserModelRepository userRepository;
    @Mock
    private JwtTokenProvider jwtTokenProvider;
    @Mock
    private UserMapper userMapper;
    @Mock
    private RoleRepository roleRepository;
    @InjectMocks
    private UserService userService;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void findAllCoustomer() {
        userService.findAllCoustomer();
        verify(userRepository).findAllByIsDeletedFalse();
    }

    @Test
    void createCustomer() {
        String fname ="jai";
        String lname ="pandey";
        String email ="jaishankarpandey329@gmail.com";
        String mobile ="9479275716";
        String password ="jsp@1234";
        String dob = "1993-03-15";
        String ccode ="IND";
        String pan ="cerdsas";
        String gender ="MALE";
        String aadhar ="tyuiogp";
        String role = Constant.RoleName.ROLE_USER.toString();
        SignUpRequest request = new SignUpRequest(fname,lname,email,mobile, BCrypt.hashpw(password, BCrypt.gensalt()),dob,ccode,pan,gender,aadhar,role);
        Date dateob= Utility.stringtodate(dob);
        UserModel userModel = new UserModel(UUID.randomUUID().toString(),fname,lname,fname+" "+lname,mobile,email,mobile,ccode, BCrypt.hashpw(password, BCrypt.gensalt()),gender, Constant.TypeUser.TYPE_COUSTOMER.toString(),Constant.RoleName.ROLE_USER.toString(),true,false, dateob,pan,aadhar);
        Set<RoleModel> rolenew = new HashSet<>();
        rolenew.add(roleRepository.findByName(Constant.RoleName.ROLE_USER.toString()));
        userModel.setRoles(rolenew);
        when(userRepository.save(userModel)).thenReturn(userModel);
        assertEquals(userModel.getEmailId(),userService.createCustomer(request).getEmailId());
        //assertEquals(userModel,userService.createCustomer(request));
    }


    @Test
    void findUserDetail() {
        String fname ="jai";
        String lname ="pandey";
        String email ="jaishankarpandey329@gmail.com";
        String mobile ="9479275716";
        String password ="jsp@1234";
        String dob = "1993-03-15";
        String ccode ="IND";
        String pan ="cerdsas";
        String gender ="MALE";
        String aadhar ="tyuiogp";
        String userid = UUID.randomUUID().toString();
        Date dateob=Utility.stringtodate(dob);
        UserModel userModel = new UserModel(userid,fname,lname,fname+" "+lname,mobile,email,mobile,ccode, BCrypt.hashpw(password, BCrypt.gensalt()),gender, Constant.TypeUser.TYPE_COUSTOMER.toString(),Constant.RoleName.ROLE_USER.toString(),true,false, dateob,pan,aadhar);
        Set<RoleModel> rolenew = new HashSet<>();
        rolenew.add(roleRepository.findByName(Constant.RoleName.ROLE_USER.toString()));
        userModel.setRoles(rolenew);

        //userService.findUserDetail();
        verify(userRepository).findById(userid);
    }

    @Test
    void findmyDetail() {
        String fname ="jai";
        String lname ="pandey";
        String email ="jaishankarpandey329@gmail.com";
        String mobile ="9479275716";
        String password ="jsp@1234";
        String dob = "1993-03-15";
        String ccode ="IND";
        String pan ="cerdsas";
        String gender ="MALE";
        String aadhar ="tyuiogp";
        String userid = UUID.randomUUID().toString();
        Date dateob=Utility.stringtodate(dob);
        UserModel userModel = new UserModel(userid,fname,lname,fname+" "+lname,mobile,email,mobile,ccode, BCrypt.hashpw(password, BCrypt.gensalt()),gender, Constant.TypeUser.TYPE_COUSTOMER.toString(),Constant.RoleName.ROLE_USER.toString(),true,false, dateob,pan,aadhar);
        Set<RoleModel> rolenew = new HashSet<>();
        rolenew.add(roleRepository.findByName(Constant.RoleName.ROLE_USER.toString()));
        userModel.setRoles(rolenew);


        //userService.findmyDetail();
        verify(userRepository).findById(userid);
    }
}
