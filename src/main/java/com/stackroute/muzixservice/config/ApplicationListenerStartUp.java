package com.stackroute.muzixservice.config;

import com.stackroute.muzixservice.domain.Track;
import com.stackroute.muzixservice.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


@Component
@PropertySource("classpath:application.properties")
public class ApplicationListenerStartUp implements ApplicationListener<ContextRefreshedEvent> {
    private TrackRepository trackRepository;
    @Autowired
    private Environment env;
    public ApplicationListenerStartUp(TrackRepository trackRepository, Environment env){
        this.trackRepository=trackRepository;

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent){

        Track track = new Track(Integer.parseInt(env.getProperty("app.trackID")),env.getProperty("app.trackname"),env.getProperty("app.trackcomments"));
        trackRepository.save(track);
    }
}
