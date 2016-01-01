package ch.fhnw.imvs;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import ch.fhnw.imvs.dto.UserDTO;
import ch.fhnw.imvs.model.Rental;
import ch.fhnw.imvs.model.User;

public class Test1 {
	
	public static void main(String[] args) {
		List<String> mappingFiles = new ArrayList<String>();
		mappingFiles.add("dozer.xml");

		Mapper mapper = new DozerBeanMapper(mappingFiles);

		User user = new User();
		user.setId(1234L);
		user.setName("Mueller");
		user.setPreName("Peter");

		Rental rental = new Rental(444L, 14);
		rental.setUser(user);
		user.getRentals().add(rental);

		UserDTO dto = mapper.map(user, UserDTO.class);
		System.out.println(dto);
	}

}
