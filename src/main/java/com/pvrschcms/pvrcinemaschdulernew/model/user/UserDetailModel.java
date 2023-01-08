//package com.pvrschcms.PVRCinemaSchdulerNew.model.user;
//
//import javax.persistence.*;
//
//import com.pvrschcms.PVRCinemaSchdulerNew.model.audit.UserDataAudit;
//import com.pvrschcms.PVRCinemaSchdulerNew.model.constantenum.Gender;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import java.util.Date;
//@Getter
//@Setter
//@Entity
//@Table(name = "user_detail")
//public class UserDetailModel extends UserDataAudit {
//
//    private static final long serialVersionUID = 1001L;
//
//    @Id
//    private String id;
//    private String firstName;
//
//    private String lastName;
//
//    @Enumerated(EnumType.STRING)
//    @Column(length = 10)
//    private Gender gender;
//
//    private String address1;
//
//    private String address2;
//
//    private Date dob;
//
//
//    private String pinCode;
//
//
//    private String country;
//
//
//    private String countryCode;
//
//
//    private String state;
//
//
//    private String stateCode;
//
//
//    private String city;
//
//    private String cityCode;
//
//    private String panNo;
//
//    private String aadharNo;
//
//    @OneToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "user_id", nullable = false)
//    private UserModel userModel;
//
//    public UserDetailModel() {
//    }
//
//	public UserDetailModel(String id, String firstName, String lastName, Gender gender, String address1,
//			String address2, Date dob, String pinCode, String country, String countryCode, String state,
//			String stateCode, String city, String cityCode, String panNo, String aadharNo, UserModel userModel) {
//		super();
//		this.id = id;
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.gender = gender;
//		this.address1 = address1;
//		this.address2 = address2;
//		this.dob = dob;
//		this.pinCode = pinCode;
//		this.country = country;
//		this.countryCode = countryCode;
//		this.state = state;
//		this.stateCode = stateCode;
//		this.city = city;
//		this.cityCode = cityCode;
//		this.panNo = panNo;
//		this.aadharNo = aadharNo;
//		this.userModel = userModel;
//	}
//
//}
