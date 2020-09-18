package com.rolex.microlabs.licence.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rolex.microlabs.licence.enums.BusinessExceptionException;
import com.rolex.microlabs.licence.dto.*;
import com.rolex.microlabs.licence.entity.Licence;
import com.rolex.microlabs.licence.mapper.LicenceMapper;
import com.rolex.microlabs.licence.service.ILicenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author rolex
 * @since 2020-06-20
 */
@Service
public class LicenceServiceImpl extends ServiceImpl<LicenceMapper, Licence> implements ILicenceService {
    @Autowired
    private OrganizationClient organizationClient;

    /**
     * 查询{@link Licence} 详情
     *
     * @param licenceId
     * @return
     */
    @Override
    public LicenceDTO queryDetail(Long licenceId) {
        Licence licence = this.getById(licenceId);
        checkNotNull(licence);

        OrganizationDTO org = null;//ClientUtil.execute(() -> organizationClient.getOrganization(licence.getOrganizationId()));
        return toLicenceDTO(licence, org);
    }

    /**
     * 分页获取
     *
     * @param licenceParam 分页查询参数
     * @return
     */
    @Override
    public QueryData<SimpleLicenceDTO> getLicences(LicenceParam licenceParam) {
        String licenceType = licenceParam.getLicenceType();
        LicenceTypeEnum licenceTypeEnum = LicenceTypeEnum.parseOfNullable(licenceType);
        // 断言, 非空
        BusinessExceptionException.BAD_LICENCE_TYPE.assertNotNull(licenceTypeEnum);

        LambdaQueryWrapper<Licence> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Licence::getLicenceType, licenceType);
        IPage<Licence> page = null;
        return null;
    }

    /**
     * 新增{@link Licence}
     *
     * @param request 请求体
     * @return
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public LicenceAddRespData addLicence(LicenceAddRequest request) {
        Licence licence = new Licence();
        licence.setOrganizationId(request.getOrganizationId());
        licence.setLicenceType(request.getLicenceType());
        licence.setProductName(request.getProductName());
        licence.setLicenceMax(request.getLicenceMax());
        licence.setLicenceAllocated(request.getLicenceAllocated());
        licence.setComment(request.getComment());
        this.save(licence);

        return new LicenceAddRespData(licence.getLicenceId());
    }

    /**
     * entity -> simple dto
     *
     * @param licence {@link Licence} entity
     * @return {@link SimpleLicenceDTO}
     */
    private SimpleLicenceDTO toSimpleLicenceDTO(Licence licence) {
        // 省略
        return null;
    }

    /**
     * entity -> dto
     *
     * @param licence {@link Licence} entity
     * @param org     {@link OrganizationDTO}
     * @return {@link LicenceDTO}
     */
    private LicenceDTO toLicenceDTO(Licence licence, OrganizationDTO org) {
        // 省略
        return null;
    }

    /**
     * 校验{@link Licence}存在
     *
     * @param licence
     */
    private void checkNotNull(Licence licence) {
        BusinessExceptionException.LICENCE_NOT_FOUND.assertNotNull(licence);
    }
}
