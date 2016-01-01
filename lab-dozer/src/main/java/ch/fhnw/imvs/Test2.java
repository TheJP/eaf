package ch.fhnw.imvs;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import ch.fhnw.imvs.dto.RentalDTO;
import ch.fhnw.imvs.model.Rental;
import ch.fhnw.imvs.model.User;

public class Test2 {

	public static void main(String[] args) {
		List<String> mappingFiles = new ArrayList<String>();
		mappingFiles.add("dozer.xml");

		Mapper mapper = new DozerBeanMapper(mappingFiles);

		User user = new User();
		user.setId(1234L);
		user.setName("Mueller");
		user.setPreName("Peter");

		Rental rental = new Rental(555L, 15);
		rental.setUser(user);
		user.getRentals().add(rental);

		RentalDTO dto = mapper.map(rental, RentalDTO.class);
		System.out.println(dto);
	}

}
