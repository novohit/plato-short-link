package com.wyu.plato.link.api.v1.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author novo
 * @since 2023-03-12
 */
@Data
public class LinkGroupCreateRequest {

    /**
     * 分组标题
     */
    @NotBlank
    private String title;
}
