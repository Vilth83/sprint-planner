package fr.vilth.sprintplanner.commons.api;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AbstractService {

    @Autowired
    protected ModelMapper modelMapper;
}
