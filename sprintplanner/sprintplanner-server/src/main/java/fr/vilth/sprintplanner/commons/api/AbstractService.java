package fr.vilth.sprintplanner.commons.api;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Abstract implementation of a service, to be implemented by every service
 * implementation.
 * <p>
 * provides convenient bean injection of a {@code ModelMapper}.
 * 
 * @author vilth
 */
@Service
public class AbstractService {

    @Autowired
    protected ModelMapper modelMapper;
}
