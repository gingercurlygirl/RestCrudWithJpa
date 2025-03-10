package com.example.RestCrudWithJpa;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequestMapping("/channels")
@RestController
public class ChannelController {

    ChannelService channelService;

    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @PostMapping     //localhost:8080/channels
    public ResponseEntity<Channel> createChannelByRequestBody(@Valid @RequestBody Channel channel) {

        Channel result = channelService.addChannel(channel);

        return ResponseEntity.ok(result);
    }

    @GetMapping //localhost:8080/channels
    public List<ChannelDTO> getAllChannels(){
        return channelService.getAllChannels();
    }

    @PostMapping("/{cId}/messages")
    public ResponseEntity<ChannelDTO> addMessagesToChannel(@PathVariable Long cId, @RequestBody Set<Long> messageIds){
        return ResponseEntity.ok(channelService.addMessagesToChannel(cId, messageIds));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChannelDTO> getChannelById(@PathVariable Long id){
      ChannelDTO dto = channelService.getChannelById(id);
      return ResponseEntity.ok(dto);

    }

    @PutMapping
    public ResponseEntity<Channel> updateChannel(@Valid @RequestBody Channel newChannel) throws Exception {


       Channel channel = channelService.updateChannel(newChannel);

        if (channel != null) {
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
