package fr.vilth.sprintplanner.configuration.api;

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
     * Convert a {@code Set} of objects into given destination {@code Class}.
     * <p>
     * utility generic method to handle {@code Set} conversion.
     * 
     * @param <S> source type
     * @param <D> destination class
     * @param source the set to convert
     * @param destination the type in which the source is mapped
     * @return a {@code Set} of given destination type.
     */
    public <S, D> Set<D> convertSet(Set<S> source, Class<D> destination) {
	return source.stream()// -
		.map(elt -> convert(elt, destination))// -
		.collect(Collectors.toSet());
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

    /**
     * Receive a {@code Set} of {@code Object} and convert it to a {@code List}
     * of another type
     * 
     * @param <S> the given source type
     * @param <D> the given destination type
     * @param source the {@code Set} to be converted
     * @param destination the {@code Class} type to convert {@code Set} elements
     *        into
     * @return a {@code List} of objects of given destination type
     */
    public <S, D> List<D> convertSetToList(Set<S> source,
	    Class<D> destination) {
	return source.stream()
		.map(elt -> convert(elt, destination))
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

    /**
     * Merge update {@code Dto} to given {@code Object}.
     * 
     * @param <S> source type
     * @param <D> destination {@code Object}
     * @param source the {@code Dto} containing fields to merge
     * @param destination the {@code Object} to update
     */
    public <S, D> void merge(S source, D destination) {
	modelMapper.map(source, destination);
    }
}
