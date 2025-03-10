package com.example.RestCrudWithJpa;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/channels")
@RestController
public class ChannelController {

      List<Channel> channels = new ArrayList<>();

    @PostMapping("/{id}/{name}") //localhost:8080/channels/1/name
    public void createChannel(@PathVariable Long id,  @PathVariable String name){

        Channel c = new Channel(id, name);
        System.out.println(c.toString());
    }

    @PostMapping //localhost:8080/channels
    public void createChannelByRequestBody(@RequestBody Channel channel){
        channels.add(channel);

        System.out.println(channel.toString());
    }

    @GetMapping //localhost:8080/channels
    public List<Channel> getAllChannels(){
        return channels;
    }

    @GetMapping("/{id}")
    public Channel getChannelById(@PathVariable Long id){
       Optional<Channel> channel =channels.stream().filter(c -> c.getId() == id).findFirst();

       return channel.orElse(null);

    }

    @PutMapping
    public Channel updateChannelById(@RequestBody Channel newChannel){
        Optional<Channel> channel = channels.stream().filter(c -> c.getId() == newChannel.getId()).findFirst();

       channel.ifPresent(c -> {
           c.setName(newChannel.getName());
       });

       return  channel.orElse(null);


    }

    @DeleteMapping("/{id}")
    public void deleteChannelById(@PathVariable Long id){
        channels.removeIf(c -> c.getId() == id);
    }


}
