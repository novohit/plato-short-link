package com.wyu.plato.link.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.wyu.plato.common.LocalUserThreadHolder;
import com.wyu.plato.common.enums.BizCodeEnum;
import com.wyu.plato.common.exception.BizException;
import com.wyu.plato.link.api.v1.request.LinkGroupCreateRequest;
import com.wyu.plato.link.api.v1.request.LinkGroupUpdateRequest;
import com.wyu.plato.link.model.LinkGroupDO;
import com.wyu.plato.link.mapper.LinkGroupMapper;
import com.wyu.plato.link.service.LinkGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author novo
 * @since 2023-03-11
 */
@Service
public class LinkGroupServiceImpl extends ServiceImpl<LinkGroupMapper, LinkGroupDO> implements LinkGroupService {

    @Autowired
    private LinkGroupMapper linkGroupMapper;

    @Override
    public void create(LinkGroupCreateRequest createRequest) {
        Long accountNo = LocalUserThreadHolder.getLocalUserNo();
        LinkGroupDO linkGroupDO = new LinkGroupDO();
        linkGroupDO.setTitle(createRequest.getTitle());
        linkGroupDO.setAccountNo(accountNo);
        int rows = linkGroupMapper.insert(linkGroupDO);
        if (rows <= 0) {
            throw new BizException(BizCodeEnum.GROUP_CREATE_ERROR);
        }
    }

    @Override
    public void delete(Long groupId) {
        Long accountNo = LocalUserThreadHolder.getLocalUserNo();
        int rows = this.linkGroupMapper.deleteGroup(groupId, accountNo);
        //.delete(new QueryWrapper<LinkGroupDO>().lambda().eq(LinkGroupDO::getAccountNo, accountNo).eq(LinkGroupDO::getId, groupId));
        if (rows <= 0) {
            throw new BizException(BizCodeEnum.GROUP_DELETE_ERROR);
        }
    }

    @Override
    public LinkGroupDO findOne(Long groupId) {
        Long accountNo = LocalUserThreadHolder.getLocalUserNo();
        return this.linkGroupMapper
                .selectOne(new QueryWrapper<LinkGroupDO>().lambda().eq(LinkGroupDO::getAccountNo, accountNo).eq(LinkGroupDO::getId, groupId));
    }

    @Override
    public List<LinkGroupDO> findAll() {
        Long accountNo = LocalUserThreadHolder.getLocalUserNo();
        return this.linkGroupMapper
                .selectList(new QueryWrapper<LinkGroupDO>().lambda().eq(LinkGroupDO::getAccountNo, accountNo));
    }

    @Override
    public void update(LinkGroupUpdateRequest updateRequest) {
        Long accountNo = LocalUserThreadHolder.getLocalUserNo();
        LinkGroupDO groupDO = new LinkGroupDO();
        BeanUtils.copyProperties(updateRequest, groupDO);
        int rows = this.linkGroupMapper
                .update(groupDO, new QueryWrapper<LinkGroupDO>().lambda().eq(LinkGroupDO::getAccountNo, accountNo).eq(LinkGroupDO::getId, updateRequest.getId()));
        if (rows <= 0) {
            throw new BizException(BizCodeEnum.GROUP_OPER_ERROR);
        }
    }
}
