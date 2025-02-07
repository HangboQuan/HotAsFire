package com.alibaba.javabase.work;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author quanhangbo
 * @date 2025-01-20 23:11
 */
@Mapper(componentModel = "spring")
public interface PublishExtendAssembler {

    @Mappings({

    })
    PublishExtendInfo assemblePublish(PaxPublishJourneyRequest request);
}
