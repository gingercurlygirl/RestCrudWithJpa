package com.example.RestCrudWithJpa;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/channels")
@RestController
public class ChannelController {

    ChannelService channelService;

    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @PostMapping     //localhost:8080/channels
    public Optional<Channel> createChannelByRequestBody(@Valid @RequestBody Channel channel) {

        Optional<Channel> result = channelService.addChannel(channel);
        return result;
    }

    @GetMapping //localhost:8080/channels
    public List<Channel> getAllChannels(){
        return channelService.getAllChannels();
    }

    @GetMapping("/{id}")
    public Channel getChannelById(@PathVariable Long id){
       Optional<Channel> channel = channelService.getChannelById(id);

       return channel.orElse(null);

    }

    @PutMapping
    public ResponseEntity<Channel> updateChannel(@Valid @RequestBody Channel newChannel) throws Exception {

        Optional<Channel> oc = channelService.updateChannel(newChannel);

        if (oc.isPresent()) {
            return ResponseEntity.accepted().body(newChannel);
        } else {
            return  ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public void deleteChannelById(@PathVariable Long id){
        channelService.deleteChannel(id);
    }


}
