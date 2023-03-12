package com.wyu.plato.link.api.v1;


import com.wyu.plato.common.util.Resp;
import com.wyu.plato.link.api.v1.request.LinkGroupCreateRequest;
import com.wyu.plato.link.service.LinkGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author novo
 * @since 2023-03-11
 */
@RestController
@RequestMapping("/group/v1")
public class LinkGroupController {

    @Autowired
    private LinkGroupService linkGroupService;

    /**
     * 创建短链分组
     *
     * @param createRequest
     * @return
     */
    @PostMapping("/create")
    public Resp create(@RequestBody @Validated LinkGroupCreateRequest createRequest) {
        this.linkGroupService.create(createRequest);
        return Resp.success();
    }
}
