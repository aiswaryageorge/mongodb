package com.stackroute.muzixservice.contoller;


import com.stackroute.muzixservice.domain.Track;
import com.stackroute.muzixservice.exceptions.TrackNotFoundException;
import com.stackroute.muzixservice.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class TrackController {
    @Autowired
    private TrackService trackService;

    public TrackController(TrackService trackService)
    {
        this.trackService=trackService;
    }
    public void setTrackService(TrackService trackService)
    {
        this.trackService=trackService;
    }

    @PostMapping("track")
    public ResponseEntity<Track> saveTrack(@RequestBody Track track)
    {
        ResponseEntity responseEntity;
        try {
            Track track1=trackService.saveTrack(track);
            responseEntity=new ResponseEntity<String>("Successfully Added Track", HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }


    @GetMapping("track")
    public ResponseEntity<List<Track>> getAllTrack()
    {
        List<Track> track1=trackService.getAllTrack();
        return new ResponseEntity<List<Track>>(track1,HttpStatus.OK);
    }
    @PutMapping("track/{id}")
    public ResponseEntity<Track> updateTrack(@RequestBody Track track)
    {
        ResponseEntity responseEntity;
        try {
            Track track1=trackService.updateComments(track);
            return new ResponseEntity<Track>(track1,HttpStatus.OK);
        }
        catch (Exception e)
        {
            responseEntity=new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            e.printStackTrace();
        }
        return responseEntity;
    }


    @DeleteMapping("track/{id}")
    public ResponseEntity<String> deleteTrack(@RequestBody Track track)
    {
        ResponseEntity responseEntity;
        try {
            trackService.deleteTrack(track);
            return new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
        }
        catch (TrackNotFoundException e)
        {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

}



