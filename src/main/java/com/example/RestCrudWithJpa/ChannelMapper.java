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

    default List<String> mapStrings(Set<Message> messages){
        return messages.stream().map(m -> m.getMessage()).collect(Collectors.toList());
    }

}
