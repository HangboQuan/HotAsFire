package com.alibaba.javabase.work;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author quanhangbo
 * @date 2025-01-20 22:05
 */
@Mapper(componentModel = "spring")
public abstract class PaxOrderAssembler {

    @Mappings({
    })
    public abstract PaxOrder assembler(PaxPublishJourneyRequest request);
}
