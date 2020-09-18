package com.rolex.microlabs.licence.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rolex.microlabs.licence.dto.*;
import com.rolex.microlabs.licence.entity.Licence;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author rolex
 * @since 2020-06-20
 */
public interface ILicenceService extends IService<Licence> {
    LicenceDTO queryDetail(Long licenceId);

    QueryData<SimpleLicenceDTO> getLicences(LicenceParam licenceParam);

    LicenceAddRespData addLicence(LicenceAddRequest request);
}
