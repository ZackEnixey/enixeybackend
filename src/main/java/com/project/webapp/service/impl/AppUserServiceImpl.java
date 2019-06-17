package com.project.webapp.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.webapp.dto.request.ChangePassDTO;
import com.project.webapp.dto.request.InviteDTO;
import com.project.webapp.dto.request.LoginDTO;
import com.project.webapp.dto.response.AppUserDto;
import com.project.webapp.enums.RoleEnum;
import com.project.webapp.model.AppUser;
import com.project.webapp.model.Role;
import com.project.webapp.repository.AppUserRepository;
import com.project.webapp.repository.CompanyRepository;
import com.project.webapp.repository.RoleRepository;
import com.project.webapp.security.SecurityConfiguration;
import com.project.webapp.service.AppUserService;
import com.project.webapp.util.ConfigureMessage;
import com.project.webapp.util.ConfigureTemplate;
import com.project.webapp.util.TokenUtil;
import com.project.webapp.util.WebShopException;

@Service
public class AppUserServiceImpl implements AppUserService {

	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	AppUserRepository appUserRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public AppUserDto createAppUser(AppUserDto appUserDto) {
		AppUser appUser = new AppUser(appUserDto);
		appUserRepository.save(appUser);
		return new AppUserDto(appUser);
	}

	@Override
	public List<AppUserDto> findAllAppUsers() {
		List<AppUser> appUsers = appUserRepository.findAll();
		List<AppUserDto> appUsersDto = new ArrayList<AppUserDto>();
		for (AppUser appUser : appUsers) {
			appUsersDto.add(new AppUserDto(appUser));
		}
		return appUsersDto;
	}

	@Override
	public AppUserDto getAppUserById(int id) {
		AppUser appUser = appUserRepository.findById(id).orElseThrow(() -> new WebShopException(HttpStatus.BAD_REQUEST, "there is no appUser with id: " + id));
		return new AppUserDto(appUser);
	}

	@Override
	public AppUserDto editAppUser(int id, AppUserDto appUserDto) {
		AppUser appUser = appUserRepository.findById(id).orElseThrow(() -> new WebShopException(HttpStatus.BAD_REQUEST, "there is no appUser with id: " + id));
		if(appUserDto.getName() != null ) 		{ appUser.setName(appUserDto.getName()); }
		if(appUserDto.getLastName() != null ) 	{ appUser.setLastName(appUserDto.getLastName()); }
		if(appUserDto.getCountry() != null ) 	{ appUser.setCountry(appUserDto.getCountry()); }
		if(appUserDto.getCity() != null ) 		{ appUser.setCity(appUserDto.getCity()); }
		if(appUserDto.getEmail() != null ) 		{ appUser.setEmail(appUserDto.getEmail()); }
		if(appUserDto.getPass() != null ) 		{ appUser.setPass(appUserDto.getPass()); }
		if(appUserDto.getPersonalId() != null ) { appUser.setPersonalId(appUserDto.getPersonalId()); }
		if(appUserDto.getRegisterToken() != null ) { appUser.setRegisterToken(appUserDto.getRegisterToken()); }
		if(appUserDto.isComfirmed()) 			{ appUser.setComfirmed(appUserDto.isComfirmed()); }
		if(appUserDto.isDeleted()) 				{ appUser.setDeleted(appUserDto.isDeleted()); }
		if(appUserDto.isPasswordChanged()) 		{ appUser.setPasswordChanged(appUserDto.isPasswordChanged()); }
		
		appUser = appUserRepository.saveAndFlush(appUser);// saveAndFlush
		return new AppUserDto(appUser);
	}

	@Override
	public AppUserDto deleteAppUserById(int id) {
		AppUser appUser = appUserRepository.findById(id).orElseThrow(() -> new WebShopException(HttpStatus.BAD_REQUEST, "there is no appUser with id: " + id));
		appUser.setDeleted(true);		
		appUser = appUserRepository.saveAndFlush(appUser); // logicko brisanje, is deactivated polje
		return new AppUserDto(appUser);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////   LOGING   /////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////

	@Override
	public AppUser findByEmail(String email) {
		return appUserRepository.findByEmail(email);
	}

	@Override
	public AppUserDto inviteCompanyAdmin(InviteDTO invite) {
		AppUser appUser = appUserRepository.findByEmail(invite.getEmail());
		if(appUser != null) {
			throw new WebShopException(HttpStatus.BAD_REQUEST, "Email already exist!");
		}
		appUser = new AppUser();
		appUser.setEmail(invite.getEmail());
		appUser.setName(invite.getEmail());
		String temporaryPassword = TokenUtil.generateTemporaryPassword();
		String token = TokenUtil.generateToken();
		appUser.setPass(SecurityConfiguration.passwordEncoder().encode(temporaryPassword));
		System.out.println(invite.getCompanyId() );
		appUser.setCompany(companyRepository.findById(invite.getCompanyId()).orElse(null));
		
		appUser.setRegisterToken(token);
		appUser.setComfirmed(false);
		appUser.setPasswordChanged(false);
		appUser.setDeleted(false);
		Role role = roleRepository.findById(RoleEnum.COMPANYADMIN.getValue()).orElse(null);
		appUser.setRoles(new ArrayList<Role>(Arrays.asList(role)));
		appUserRepository.saveAndFlush(appUser);
		
		  try {
	            Message message = ConfigureMessage.message(invite.getEmail(), "", "", "Confirm Registration");
	            Map<String, String> paramMap = new HashMap<>();
	            paramMap.put("name", invite.getName());
	            paramMap.put("token", token);
	            paramMap.put("password", temporaryPassword);
	            boolean b = ConfigureTemplate.template(message, SendEmailExample.class, "confirmRegistrationTemplate.ftl", paramMap);

	            if (b) {
	                System.out.print("Success");
	            }
	        }catch (Exception e){
	            e.printStackTrace();
	        }

		return new AppUserDto(appUser);
	}

	@Override
	public void comfirmEmail(String token) {
		AppUser appUser = appUserRepository.findByRegisterToken(token);
		if(appUser == null) {
			throw new WebShopException(HttpStatus.BAD_REQUEST, "Bad request, wrong token!");
		}
		appUser.setComfirmed(true);
		appUserRepository.save(appUser);		
	}

	@Override
	public void changeTemporaryPassword(ChangePassDTO changePassDTO) {
		AppUser appUser = appUserRepository.findByEmail(changePassDTO.getEmail());
		if(appUser == null) {
			throw new WebShopException(HttpStatus.BAD_REQUEST, "User does not exist.");
		}
		if(!SecurityConfiguration.passwordEncoder().matches(changePassDTO.getTemporaryPassword(), appUser.getPass())) {
			throw new WebShopException(HttpStatus.FORBIDDEN, "Passwords do not match.");
		}
		if(!appUser.isComfirmed()) {
			throw new WebShopException(HttpStatus.FORBIDDEN, "You must comfirm your email.");
		}
		if(appUser.isPasswordChanged()) {
			throw new WebShopException(HttpStatus.FORBIDDEN, "Password already changed.");
		}
		
		appUser.setPasswordChanged(true);
		appUser.setPass(SecurityConfiguration.passwordEncoder().encode(changePassDTO.getNewPassword()));
		appUserRepository.save(appUser);
	}

	@Override
	public AppUserDto getAppUserByToken(String token) {
		AppUser appUser = appUserRepository.findByRegisterToken(token);
		if(appUser == null) {
			throw new WebShopException(HttpStatus.BAD_REQUEST, "Bad request, wrong token!");
		}
		appUserRepository.save(appUser);
		appUser.setRegisterToken(null);
		appUserRepository.save(appUser);
		return new AppUserDto(appUser);
	}

	@Override
	public AppUserDto signUp(LoginDTO user) {
		AppUser appUser = appUserRepository.findByEmail(user.getEmail());
		if(appUser != null) {
			throw new WebShopException(HttpStatus.BAD_REQUEST, "Email already exists!");
		}
		appUser = new AppUser();
		appUser.setEmail(user.getEmail());
		appUser.setName(user.getPassword());
		appUser.setPass(SecurityConfiguration.passwordEncoder().encode(user.getPassword()));
		
		String token = TokenUtil.generateToken();		
		appUser.setRegisterToken(token);
		
		appUser.setComfirmed(false);
		appUser.setPasswordChanged(true);
		appUser.setDeleted(false);
		
		Role role = roleRepository.findById(RoleEnum.USER.getValue()).orElse(null);
		appUser.setRoles(new ArrayList<Role>(Arrays.asList(role)));
		appUserRepository.saveAndFlush(appUser);
		
	  try {
            Message message = ConfigureMessage.message(user.getEmail(), "", "", "Confirm Registration");
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("name", user.getEmail());
            paramMap.put("token", token);
            paramMap.put("password", user.getPassword());
            boolean b = ConfigureTemplate.template(message, SendEmailExample.class, "confirmRegistrationTemplate.ftl", paramMap);

            if (b) {
                System.out.print("Success");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

		return new AppUserDto(appUser);
	}
	
	

}
