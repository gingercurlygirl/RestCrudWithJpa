package com.example.RestCrudWithJpa;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface ChannelMapper {

    ChannelMapper INSTANCE = Mappers.getMapper(ChannelMapper.class);

    @Mapping(target = "messages",expression = "java(mapStrings(channel.getMessages()))")
    public ChannelDTO channelToChannelDTO(Channel channel);


    @Mapping(target = "messages", expression = "java(mapStringsToMessages(channelDTO.getMessages()))")
    public Channel channelToChannelDTO(ChannelDTO channelDTO);

    default Set<Message> mapStringsToMessages(List<String> messageNames){


        return messageNames != null ? messageNames.stream().map(name -> {
            Message message = new Message();
            message.setMessage(name);
            return message;
        }).collect(Collectors.toSet()) : null;
    }

    default List<String> mapStrings(Set<Message> messages){
        return messages.stream().map(m -> m.getMessage()).collect(Collectors.toList());
    }

}
