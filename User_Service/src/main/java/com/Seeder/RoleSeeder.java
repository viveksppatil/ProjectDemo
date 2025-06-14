package com.Seeder;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.Entity.Role;
import com.Enum.RolesEnum;
import com.repository.RoleRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
@Order(1)
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private RoleRepo rr;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		rolesLoader();
		log.debug("Roles Loaded..");
	}

	private void rolesLoader() {

		RolesEnum[] rolenums = new RolesEnum[] { RolesEnum.ADMIN, RolesEnum.SUPER_ADMIN, RolesEnum.USER };

		Map<RolesEnum, String> roleDescrip = new HashMap<RolesEnum, String>();
		roleDescrip.put(RolesEnum.ADMIN, "Administrator Role");
		roleDescrip.put(RolesEnum.SUPER_ADMIN, "Super Admin Role");
		roleDescrip.put(RolesEnum.USER, "Default User Role");

		for (RolesEnum re : rolenums) {

			boolean isRoleEmpty = !rr.findByName(re).isPresent();

			if (isRoleEmpty) {

				Role rol = new Role();
				rol.setName(re);
				rol.setDescription(roleDescrip.get(re));
				rr.save(rol);
				log.info("Role created with name: {}" , rol.getName());
			}
		}

	}

}
