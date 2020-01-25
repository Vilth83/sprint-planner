package fr.vilth.sprintplanner.commons.api;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Abstract implementation of a service, to be implemented by every service
 * implementation.
 * <p>
 * provides convenient bean injection of a {@code ModelMapper}.
 * 
 * @author Thierry VILLEPREUX
 */
@Service
public abstract class AbstractService {

    @Autowired
    protected ModelMapper modelMapper;

    public <S, D> List<D> convertList(List<S> source, Class<D> destination) {
	return source.stream()// -
		.map(elt -> convert(elt, destination))// -
		.collect(Collectors.toList());
    }

    public <S, D> Set<D> convertToSet(List<S> source, Class<D> destination) {
	return source.stream()// -
		.map(elt -> convert(elt, destination))// -
		.collect(Collectors.toSet());
    }

    public <S, D> D convert(S source, Class<D> destination) {
	return modelMapper.map(source, destination);
    }
}
