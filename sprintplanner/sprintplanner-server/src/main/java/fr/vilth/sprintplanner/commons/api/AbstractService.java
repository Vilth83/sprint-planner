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

    /**
     * Convert a {@code List} of objects into given destination {@code Class}.
     * <p>
     * utility generic method to handle {@code List} conversion.
     * 
     * @param <S> source type
     * @param <D> destination class
     * @param source the list to convert
     * @param destination the type in which the source is mapped
     * @return a {@code List} of given destination type.
     */
    public <S, D> List<D> convertList(List<S> source, Class<D> destination) {
	return source.stream()// -
		.map(elt -> convert(elt, destination))// -
		.collect(Collectors.toList());
    }

    /**
     * Convert a {@code List} of objects into given destination type object in a
     * {@code Set}.
     * <p>
     * utility generic method to handle {@code List} conversion.
     * 
     * @param <S> source type
     * @param <D> destination class
     * @param source the list to convert
     * @param destination the type in which the source is mapped
     * @return a {@code Set} of given destination type.
     */
    public <S, D> Set<D> convertToSet(List<S> source, Class<D> destination) {
	return source.stream()// -
		.map(elt -> convert(elt, destination))// -
		.collect(Collectors.toSet());
    }

    public <S, D> List<D> convertSetToList(Set<S> source,
	    Class<D> destination) {
	return source.stream()// -
		.map(elt -> convert(elt, destination))// -
		.collect(Collectors.toList());
    }

    /**
     * Convert a source into given destination type.
     * <p>
     * utility generic method to handle DTO conversion.
     * 
     * @param <S> source type
     * @param <D> destination class
     * @param source the object to convert
     * @param destination the type in which the source is mapped
     * @return an object of given destination type
     */
    public <S, D> D convert(S source, Class<D> destination) {
	return modelMapper.map(source, destination);
    }
}
