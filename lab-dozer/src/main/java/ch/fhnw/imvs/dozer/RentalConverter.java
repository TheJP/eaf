package ch.fhnw.imvs.dozer;

import org.dozer.DozerConverter;

import ch.fhnw.imvs.model.Rental;

public class RentalConverter extends DozerConverter<Rental, Long> {

	public RentalConverter() {
		super(Rental.class, Long.class);
	}

	@Override
	public Rental convertFrom(Long id, Rental rental) {
		// return rentalService.getRentalById(id);
		throw new UnsupportedOperationException();
	}

	@Override
	public Long convertTo(Rental rental, Long id) {
		return rental.getId();
	}

}
